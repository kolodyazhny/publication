package ua.nure.kolodiazhny.SummaryTask04.addinfo;

import ua.nure.kolodiazhny.SummaryTask04.dao.ScoreDao;
import ua.nure.kolodiazhny.SummaryTask04.entities.Score;
import ua.nure.kolodiazhny.SummaryTask04_2.connection.ConnectionPool;

import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(value = "/NewScore")
public class NewScoreServlet extends HttpServlet{
    private static final Logger LOGGER = Logger.getLogger(NewUserServlet.class);

    /**
     * Обработка данных о пользоветеле, полученных методом POST.
     * Создается новый объект пользователь и кладется в БД.
     * @param req
     * @param resp
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
    	 String cardNumber = req.getParameter("number");
         int cvc = Integer.parseInt(req.getParameter("cvc"));
         String name = req.getParameter("name");

         Score score = new Score ();
         score.setName(name);
         score.setCardNumber(cardNumber);
         score.setCvc(cvc);


        Connection con = ConnectionPool.retrieve();
        ScoreDao sd = new ScoreDao(con);
        int id = sd.insert(score);
        ConnectionPool.putback(con);

        try {
            resp.sendRedirect("catalog.jsp");
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
