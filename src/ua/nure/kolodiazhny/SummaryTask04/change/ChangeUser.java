package ua.nure.kolodiazhny.SummaryTask04.change;

import ua.nure.kolodiazhny.SummaryTask04.dao.UserDao;
import ua.nure.kolodiazhny.SummaryTask04.entities.User;
import ua.nure.kolodiazhny.SummaryTask04_2.connection.ConnectionPool;

import java.sql.Connection;

public class ChangeUser {
    /**
     * Внесение изменененного периодического издания в БД
     * @param per
     */

    public static void update(User use){
        Connection con = ConnectionPool.retrieve();
        UserDao ud = new UserDao(con);
        ud.update(use);
        ConnectionPool.putback(con);
    }

    /**
     * Удаление периодического издания из БД
     * @param per
     */

    public static void delete(User use){
        Connection con = ConnectionPool.retrieve();
        UserDao pd = new UserDao(con);
        pd.delete(use);
        ConnectionPool.putback(con);
    }
}