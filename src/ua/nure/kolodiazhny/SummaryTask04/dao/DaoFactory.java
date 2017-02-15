package ua.nure.kolodiazhny.SummaryTask04.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import ua.nure.kolodiazhny.SummaryTask04.entities.Subscription;

/**
 * ����������� �����, ����������� DAO ��� ������ � ��.
 * @param <T>
 *     ��� �������, ��� �������� ��������� DAO.
 */

public abstract class DaoFactory<T> {
    private static final Logger LOGGER = Logger.getLogger(DaoFactory.class);

    protected Connection con;

    public DaoFactory(Connection con){
        this.con = con;
    }

    /**
     * ���������� SQL ���������, ������� ��������� ��� ���������� �������.
     * @return
     */
    abstract String getSelectQuery();

    /**
     * ���������� SQL ���������, ������� ��������� ��� ���������� �������.
     * @return
     */
    abstract String getUpdateQuery();

    /**
     * ������������� �������� � Prepared Statement, ��������� � �������.
     * @param ps
     * @param obj
     */
    abstract void preparStForUpdate(PreparedStatement ps, T obj);

    /**
     * ���������� SQL ���������, ������� ������� ������ �� �������.
     * @return
     */
    abstract String getDeleteQuery();

    /**
     * ������������� �������� ��� �������� � Prepared Statement, ��������� � �������.
     * @param ps
     * @param obj
     */
    abstract void preparStForDelete(PreparedStatement ps, T obj);

    /**
     * ���������� SQL ���������, ������� ��������� ������ � �������.
     * @return
     */
    abstract String getInsertQuery();

    /**
     * ������������� �������� ��� ������� � Prepared Statement, ��������� � �������.
     * @param ps
     * @param obj
     */
    abstract void preparStForInsert(PreparedStatement ps, T obj);

    /**
     * ���������, ���������� ����� ���������� SQL-���������, ��������� (Result Set)
     * � ���������� ������, ��������� ��������.
     * @param rs
     * @return
     */
    abstract List parseResultSet(ResultSet rs);

    /**
     * ���������� SQL ���������, ������� ������� ������ �� ������� subscription.
     * @return
     */
    abstract String getSubscribtionDeleteQuery();

    /**
     * ��������� ��� ���������� ������� � ���������� ��� � ���� ������ ��������.
     * @return
     */

    public ArrayList<T> readAll(){
        List<T> list = new ArrayList<T>();
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
        return (ArrayList<T>) list;
    }

    /**
     * ��������� ������ � �������.
     * @param obj
     *          ������ ��� �������.
     * @return
     *        ���������� id ������, ��� ������������ ��������.
     */
    public int insert(T obj){
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

    /**
     * ���������, ���������� ������, � ��.
     * @param obj
     */
    public void update(T obj){
        PreparedStatement ps = null;
        try {
            String sql = getUpdateQuery();
            ps = con.prepareStatement(sql);
            preparStForUpdate(ps,obj);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }finally {
            try{ ps.close();} catch (SQLException e) {LOGGER.error(e.getMessage());}
        }
    }

    /**
     * ������� ����� �� ���� ������.
     * ���� ������ ������� ������������� ��� ������������� ��������, �� ������� ���������
     * ������ �� ������� subscription, ���������� id ������� �������.
     * @param obj
     */
    public void delete(T obj){
        PreparedStatement ps = null;
        if (!(obj instanceof Subscription)){
            try {
                String sql = getSubscribtionDeleteQuery();
                ps = con.prepareStatement(sql);
                preparStForDelete(ps, obj);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try{ ps.close();} catch (SQLException e) {e.printStackTrace();}
            }
        }
        try {
            String sql = getDeleteQuery();
            ps = con.prepareStatement(sql);
            preparStForDelete(ps, obj);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{ ps.close();} catch (SQLException e) {e.printStackTrace();}
        }
    }
}