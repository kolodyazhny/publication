package ua.nure.kolodiazhny.SummaryTask04.security;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class InvalidInfo {
    private static final Logger LOGGER = Logger.getLogger(InvalidInfo.class);

    /**
     * Отправляет сообщение о неправельно введенных данных пользователем.
     * @param req
     * @param resp
     * @param page
     *            страница, на которую отправляется сообщение.
     * @param massege
     *            содержание сообщения.
     */
    public static void invalid(HttpServletRequest req, HttpServletResponse resp, String page, String massege){
        RequestDispatcher rd = req.getServletContext().getRequestDispatcher("/"+page);
        try(PrintWriter out = resp.getWriter()) {
            out.println("<font color=red>"+massege+"</font><br>");
            try{
                rd.include(req,resp);
            } catch (ServletException e) {
                LOGGER.error(e.getMessage());
            }
        } catch (IOException e) {
           LOGGER.error(e.getMessage());
        }
    }
}