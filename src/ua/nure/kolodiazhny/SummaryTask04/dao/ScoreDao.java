package ua.nure.kolodiazhny.SummaryTask04.dao;

import ua.nure.kolodiazhny.SummaryTask04.entities.Score;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO ��� ������� Score.
 */
public class ScoreDao {
    private Connection con;
    private static final Logger LOGGER = Logger.getLogger(ScoreDao.class);

    public ScoreDao(Connection con){
        this.con = con;
    }

    /**
     * �������� �� ���������� �����.
     * @param card
     *            ���������� � �����, �������� �������������.
     * @return
     *        ���������� true - ���� ����� ����� ������� � �������
     *        false - ���� ����� ����� ���
     */
    public boolean validScore(Score score){
        boolean res = false;
        String sql = "SELECT * FROM score WHERE number = ? AND cvc = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps= con.prepareStatement(sql);
            ps.setString(1,score.getCardNumber());
            ps.setInt(2,score.getCvc());
            rs = ps.executeQuery();
            if (rs.next()){
                res = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }finally {
            try{ ps.close();} catch (SQLException e) {LOGGER.error(e.getMessage());}
            try{ rs.close();} catch (SQLException e) {LOGGER.error(e.getMessage());}
        }
        return res;
    }

    /**
     * ��������� ������ � �������.
     * @param obj
     *          ������ ��� �������.
     * @return
     *        ���������� id ������, ��� ������������ ��������.
     */
    public int insert(Score obj){
        int res = 0;
        String sql = getInsertQuery();
        ResultSet rs = null;
        try(PreparedStatement ps = con.prepareStatement(sql)) {
            preparStForInsert(ps,obj);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        sql = "SELECT LAST_INSERT_ID()";
        try(PreparedStatement ps = con.prepareStatement(sql)) {
            rs = ps.executeQuery();
            rs.next();
            res = Integer.parseInt(rs.getString(1));
            if (res == 0){
                LOGGER.error("�� ������ �������� ID");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return res;
    }

    String getInsertQuery() {
        return "INSERT INTO score (name, number, cvc) VALUES (?, ?, ?)";
    }

    void preparStForInsert(PreparedStatement ps, Object obj){
        if (obj instanceof Score){
            Score score = (Score) obj;
            try {
                ps.setString(1,score.getName());
                ps.setString(2,score.getCardNumber());
                ps.setInt(3,score.getCvc());
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }else{
            LOGGER.error("������ �� ���� ����");
        }

    }

    String getSelectQuery() {
        return "SELECT * FROM score";
    }

    List parseResultSet(ResultSet rs){
        List<Score> list = new ArrayList<Score>();
        int id;
        String name;
        String cardNumber;
        int cvc;

        try {
            while (rs.next()){
                id = rs.getInt(1);
                name = rs.getString(2);
                cardNumber = rs.getString(3);
                cvc = rs.getInt(4);
                list.add(new Score(id,name,cardNumber,cvc));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return list;
    }

    public ArrayList<Score> readAll(){
        List<Score> list = new ArrayList<Score>();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(getSelectQuery());
            list = parseResultSet(rs);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }finally {
            try{ statement.close();} catch (SQLException e) {LOGGER.error(e.getMessage());}
            try{ rs.close();} catch (SQLException e) {LOGGER.error(e.getMessage());}
        }
        return (ArrayList<Score>) list;
    }


}