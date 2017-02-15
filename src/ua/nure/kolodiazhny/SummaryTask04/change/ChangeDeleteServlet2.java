package ua.nure.kolodiazhny.SummaryTask04.change;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kolodiazhny.SummaryTask04.entities.Publication;
import org.apache.log4j.Logger;

/**
 * Сервлет, обраюатывающий изменения, которые администратор внес в каталог.
 * Данные для обработки передаются методом POST.
 */

@WebServlet(value = "/ChangePublication")
public class ChangeDeleteServlet2 extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(ChangeDeleteServlet2.class);

    /**
     * Обработка дынных, полученных методом пост.
     * В зависимости от нажатой пользователем кнопки, периодическое издание либо обновляется, лиюо удаляется.
     * @param req
     * @param resp
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        String saveBut = req.getParameter("save");
        String delBut = req.getParameter("delete");

        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        int price = Integer.parseInt(req.getParameter("price"));
        String publisher = req.getParameter("publisher");
        String description = req.getParameter("desc");
        String issn = req.getParameter("issn");

        Publication pub = new Publication(id, title, price, publisher, description, issn);



        if (delBut != null){
            Change.delete(pub);
        }
        if (saveBut != null) {
            Change.update(pub);
        }
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