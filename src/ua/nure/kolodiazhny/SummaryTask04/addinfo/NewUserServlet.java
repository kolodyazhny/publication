package ua.nure.kolodiazhny.SummaryTask04.addinfo;

import ua.nure.kolodiazhny.SummaryTask04.dao.UserDao;
import ua.nure.kolodiazhny.SummaryTask04.entities.Score;
import ua.nure.kolodiazhny.SummaryTask04.entities.User;
import ua.nure.kolodiazhny.SummaryTask04_2.connection.ConnectionPool;

import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(value = "/NewUser")
public class NewUserServlet extends HttpServlet{
    private static final Logger LOGGER = Logger.getLogger(NewUserServlet.class);

    /**
     * Обработка данных о пользоветеле, полученных методом POST.
     * Создается новый объект пользователь и кладется в БД.
     * @param req
     * @param resp
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        String name = req.getParameter("name");
        String pwd = req.getParameter("password");
        String login = req.getParameter("login");
        String value = req.getParameter("value");

        User user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setPassword(pwd);
        user.setAdmin(false);

        Connection con = ConnectionPool.retrieve();
        UserDao ud = new UserDao(con);
        int id = ud.insert(user);
        ConnectionPool.putback(con);

        HttpSession session = req.getSession(true);
        session.setAttribute("currentUser",user.getName());
        session.setAttribute("currentLogin",user.getLogin());
        session.setAttribute("userID",id);
        session.setMaxInactiveInterval(30*60);
        try {
            resp.sendRedirect("catalog.jsp");
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
