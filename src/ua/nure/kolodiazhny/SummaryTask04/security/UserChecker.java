package ua.nure.kolodiazhny.SummaryTask04.security;

import ua.nure.kolodiazhny.SummaryTask04.dao.UserDao;
import ua.nure.kolodiazhny.SummaryTask04.entities.User;
import ua.nure.kolodiazhny.SummaryTask04_2.connection.ConnectionPool;

import java.sql.Connection;

public class UserChecker {
    static Connection con = null;

    /**
     * Проверка пользовательской информации, содержащейся в БД.
     * @param login
     * @param pwd
     * @return
     */
    public static User loginCheck(String login, String pwd){
        User user = null;
        if (login == null || pwd == null){
            return null;
        }
        Connection con = ConnectionPool.retrieve();
        UserDao ud = new UserDao(con);
        user = ud.isCustomer(login,pwd);
        ConnectionPool.putback(con);
        return user;
    }
}