package ua.nure.kolodiazhny.SummaryTask04.change;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kolodiazhny.SummaryTask04.entities.User;
import org.apache.log4j.Logger;

/**
 * �������, �������������� ���������, ������� ������������� ���� � �������.
 * ������ ��� ��������� ���������� ������� POST.
 */

@WebServlet(value = "/ChangeUser")
public class ChangeDeleteServletUser extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(ChangeDeleteServletUser.class);

    /**
     * ��������� ������, ���������� ������� ����.
     * � ����������� �� ������� ������������� ������, ������������� ������� ���� �����������, ���� ���������.
     * @param req
     * @param resp
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        String saveBut = req.getParameter("save");
        String delBut = req.getParameter("delete");

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User use = new User(id, name, login, password, false);


     /*   if (delBut != null){
            Change.delete(use);
        }
        if (saveBut != null) {
            Change.update(use);
        }*/
        try{
            resp.sendRedirect("admin_page.jsp");
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            try{
                resp.sendError(500);
            } catch (IOException e1) {
                LOGGER.error(e.getMessage());
            }
        }

    }
}