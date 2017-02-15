package ua.nure.kolodiazhny.SummaryTask04.security;

import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(LogoutServlet.class);

    /**
     * Выход пользователя из системы.
     * Очистка сессии.
     * @param req
     * @param resp
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession(false);
        if (session != null){
            session.invalidate();
        }
        try{
            resp.sendRedirect("index.jsp");
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
