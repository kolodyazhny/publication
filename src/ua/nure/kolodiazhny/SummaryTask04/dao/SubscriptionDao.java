package ua.nure.kolodiazhny.SummaryTask04.dao;

import ua.nure.kolodiazhny.SummaryTask04.entities.Subscription;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDao extends DaoFactory {
    private static final Logger LOGGER = Logger.getLogger(SubscriptionDao.class);
    public SubscriptionDao(Connection con) {
        super(con);
    }

    @Override
    String getSelectQuery() {return "SELECT * FROM subscription";}

    @Override
    String getUpdateQuery()  {
        return "UPDATE user SET end_date = ?, WHERE id = ?";
    }

    @Override
    void preparStForUpdate(PreparedStatement ps, Object obj){
        Subscription subsc;
        if (obj instanceof Subscription){
            subsc = (Subscription) obj;
            try{
                ps.setTimestamp(1,subsc.getEnd());
                ps.setInt(2,subsc.getId());
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }else{
            LOGGER.error("Объект не того типа");
        }
    }

    @Override
    String getDeleteQuery() {
        return "DELETE FROM subscription WHERE id = ?";
    }

    @Override
    void preparStForDelete(PreparedStatement ps, Object obj){
        if (obj instanceof Subscription){
            Subscription subsc = (Subscription) obj;
            try{
                ps.setInt(1,subsc.getId());
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }else{
            LOGGER.error("Объект не того типа");
        }
    }

    @Override
    String getInsertQuery()  {
        return "INSERT INTO subscription (user_id, periodical_id, start_date, end_date) VALUES (?, ?, ?, ?)";
    }

    @Override
    void preparStForInsert(PreparedStatement ps, Object obj){
        if (obj instanceof Subscription){
            Subscription subsc = (Subscription) obj;
            try{
                ps.setInt(1,subsc.getUserID());
                ps.setInt(2,subsc.getPeriodicalID());
                ps.setTimestamp(3,subsc.getStart());
                ps.setTimestamp(4,subsc.getEnd());
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }else{
            LOGGER.error("Объект не того типа");
        }
    }

    @Override
    List parseResultSet(ResultSet rs){
        List<Subscription> list = new ArrayList<Subscription>();
        int id;
        int userID;
        int periodicalId;
        Timestamp start;
        Timestamp end;
        try{
            while (rs.next()){
                id = rs.getInt(1);
                userID = rs.getInt(2);
                periodicalId= rs.getInt(3);
                start = rs.getTimestamp(4);
                end = rs.getTimestamp(5);
                list.add(new Subscription(id,userID,periodicalId,start,end));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return list;
    }

    @Override
    String getSubscribtionDeleteQuery() {
        return null;
    }
}