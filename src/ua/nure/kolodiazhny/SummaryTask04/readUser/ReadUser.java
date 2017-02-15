package ua.nure.kolodiazhny.SummaryTask04.readUser;

import ua.nure.kolodiazhny.SummaryTask04.dao.UserDao;
import ua.nure.kolodiazhny.SummaryTask04.entities.User;
import ua.nure.kolodiazhny.SummaryTask04_2.connection.ConnectionPool;

import java.sql.Connection;
import java.util.ArrayList;


public class ReadUser {
    /**
     * Считываение всех периодических изданий из БД.
     * @return
     *        список периодических изданий.
     */
	public static ArrayList<User> getList(){
        Connection con = ConnectionPool.retrieve();
        UserDao ud = new UserDao(con);
        ArrayList<User> res = ud.readAll();
        ConnectionPool.putback(con);
        return res;
    }
}