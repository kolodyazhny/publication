package ua.nure.kolodiazhny.SummaryTask04.security;

import ua.nure.kolodiazhny.SummaryTask04.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@WebServlet(value = "/LoginServlet")
public class LoginServlet extends HttpServlet{
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

    /**
     * ѕроверка введенных данных о пользователе.
     * ≈сли такой пользователь зарегистрирован в системе, то идет перенаправление на страницу каталога (дл€ читателей)
     * или администрировани€ (дл€ администратора). »нформаци€ о залогиненом пользователе сохран€етс€ в сессию.
     * @param request
     * @param response
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response){

        response.setCharacterEncoding("UTF-8");
        try{
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage());
        }

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = UserChecker.loginCheck(login, password);

        if (user != null){
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(30*60);
            if (user.isAdmin()){
                session.setAttribute("currentUser","admin");
                try{
                    response.sendRedirect("admin_page.jsp");
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }
            }else{
                session.setAttribute("currentUser",user.getName());
                session.setAttribute("currentLogin",user.getLogin());
                session.setAttribute("userID",user.getId());
                try {
                    response.sendRedirect("catalog.jsp");
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }
            }

        }
        else {
            InvalidInfo.invalid(request,response, "login.jsp", "Login or password is wrong!");
        }
    }
}
