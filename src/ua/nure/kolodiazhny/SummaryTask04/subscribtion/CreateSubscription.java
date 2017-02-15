package ua.nure.kolodiazhny.SummaryTask04.subscribtion;

import ua.nure.kolodiazhny.SummaryTask04.dao.SubscriptionDao;
import ua.nure.kolodiazhny.SummaryTask04.entities.Subscription;
import ua.nure.kolodiazhny.SummaryTask04_2.connection.ConnectionPool;

import java.sql.Connection;

public class CreateSubscription {
    /**
     * Занисение информации о новой подписке в БД.
     * @param subscription
     */
    public static void create(Subscription subscription){
        Connection con = ConnectionPool.retrieve();
        SubscriptionDao sd = new SubscriptionDao(con);
        sd.insert(subscription);
        ConnectionPool.putback(con);
    }
}