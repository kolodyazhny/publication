package ua.nure.kolodiazhny.SummaryTask04.addinfo;

import ua.nure.kolodiazhny.SummaryTask04.dao.PublicationDao;
import ua.nure.kolodiazhny.SummaryTask04.entities.Publication;
import ua.nure.kolodiazhny.SummaryTask04_2.connection.ConnectionPool;

import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(value = "/AddPeriodical")
public class NewPublicationServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(NewPublicationServlet.class);

    /**
     * Обработка данных о периодическом издании, полученных методом POST.
     * Создается новый объект периодичское издание и кладется в БД.
     * @param req
     * @param resp
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        String issn = req.getParameter("issn");
        String title = req.getParameter("title");
        String publisher = req.getParameter("publisher");
        String price = req.getParameter("price");
        String description = req.getParameter("desc");

        Publication publication = new Publication();
        publication.setISSN(issn.trim());
        publication.setTitle(title.trim());
        publication.setPublisher(publisher.trim());
        publication.setPrice(Integer.parseInt(price.trim()));
        publication.setDescription(description.trim());

        Connection con = ConnectionPool.retrieve();
        PublicationDao pd = new PublicationDao(con);
        pd.insert(publication);
        ConnectionPool.putback(con);

        try {
            resp.sendRedirect("admin_page.jsp");
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}