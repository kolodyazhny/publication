package ua.nure.kolodiazhny.SummaryTask04.subscribtion;

import ua.nure.kolodiazhny.SummaryTask04.entities.Subscription;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@WebServlet(value = "/Subscribe")
public class SubscribeServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(SubscribeServlet.class);

    /**
     * Создание новой подписки:
     * из request считывается информация о подписке;
     * создается новый объект Подписка;
     * перенаправление пользователя на страницу с информацией о новой подписке.
     * @param req
     * @param resp
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        int userID = (int) req.getSession().getAttribute("userID");
        String periodicalId = req.getParameter("perID");

        Date d = new Date();
        Calendar c = Calendar.getInstance();
        Timestamp start = new Timestamp(d.getTime());
        c.setTime(start);
        c.add(Calendar.YEAR,1);
        Timestamp end = new Timestamp(c.getTime().getTime());

        Subscription sub = new Subscription();
        sub.setUserID(userID);
        sub.setPeriodicalID(Integer.parseInt(periodicalId));
        sub.setStart(start);
        sub.setEnd(end);

        CreateSubscription.create(sub);
        try{
            resp.sendRedirect("newSubscription.jsp");
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
