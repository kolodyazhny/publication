package ua.nure.kolodiazhny.SummaryTask04.dao;

import ua.nure.kolodiazhny.SummaryTask04.entities.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends DaoFactory{
    private static final Logger LOGGER = Logger.getLogger(UserDao.class);

    public UserDao(Connection con) {
        super(con);
    }

    @Override
    String getSelectQuery() {
        return "SELECT * FROM user";
    }

    @Override
    String getUpdateQuery() {
        return "UPDATE user SET name = ?, login = ?, password = ? WHERE id = ?";
    }

    @Override
    void preparStForUpdate(PreparedStatement ps, Object obj){
        User user;
        if (obj instanceof User){
            user = (User) obj;
            try{
                ps.setString(1,user.getName());
                ps.setString(2,user.getLogin());
                ps.setString(3,user.getPassword());
                ps.setInt(4,user.getId());
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }else{
            LOGGER.error("Объект не того типа");
        }
    }

    @Override
    String getDeleteQuery() {
        return "DELETE FROM user WHERE id = ?";
    }

    @Override
    void preparStForDelete(PreparedStatement ps, Object obj){
        if (obj instanceof User){
            User user = (User) obj;
            try {
                ps.setInt(1,user.getId());
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }else{
            LOGGER.error("Объект не того типа");
        }
    }

    @Override
    String getInsertQuery() {
        return "INSERT INTO user (name, login, password, is_admin) VALUES (?, ?, ?, ?)";
    }

    @Override
    void preparStForInsert(PreparedStatement ps, Object obj){
        if (obj instanceof User){
            User user = (User) obj;
            try {
                ps.setString(1,user.getName());
                ps.setString(2,user.getLogin());
                ps.setString(3,user.getPassword());
                ps.setBoolean(4,user.isAdmin());
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }else{
            LOGGER.error("Объект не того типа");
        }
    }

    @Override
    List parseResultSet(ResultSet rs){
        List<User> list = new ArrayList<User>();
        int id;
        String name;
        String login;
        String password;
        boolean isAdmin;
        try {
            while (rs.next()){
                id = rs.getInt(1);
                name = rs.getString(2);
                login = rs.getString(3);
                password = rs.getString(4);
                isAdmin = rs.getBoolean(5);
                list.add(new User(id,name,login,password,isAdmin));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return list;
    }

    @Override
    String getSubscribtionDeleteQuery() {
        return "DELETE FROM subscription WHERE user_id = ?";
    }

    /**
     * Проверка валидности пользователя.
     * @param login
     *             логин пользователя
     * @param password
     *             пароль пользователя
     * @return
     *        возвращает пользователя, зарегистрированного в БД с таким логином и паролем.
     *        null если такого пользователя нет в БД.
     */
    public User isCustomer(String login, String password){
        User user = null;
        boolean res = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM user WHERE login = ? AND password = ?";
        try {
            ps = con.prepareStatement(sql);//super.con.prepareStatement(sql);
            ps.setString(1,login);
            ps.setString(2,password);
            rs = ps.executeQuery();
            if (rs.next()){
                user = new User(rs.getInt(1),rs.getString(2),login,password,rs.getBoolean(5));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }finally {
            try{ ps.close();} catch (SQLException e) {LOGGER.error(e.getMessage());}
            try{ rs.close();} catch (SQLException e) {LOGGER.error(e.getMessage());}
        }
        return user;
    }
}