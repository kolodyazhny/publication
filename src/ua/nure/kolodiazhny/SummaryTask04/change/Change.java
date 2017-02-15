package ua.nure.kolodiazhny.SummaryTask04.change;

import ua.nure.kolodiazhny.SummaryTask04.dao.PublicationDao;
import ua.nure.kolodiazhny.SummaryTask04.entities.Publication;
import ua.nure.kolodiazhny.SummaryTask04_2.connection.ConnectionPool;

import java.sql.Connection;

public class Change {
    /**
     * Внесение изменененного периодического издания в БД
     * @param per
     */

    public static void update(Publication pub){
        Connection con = ConnectionPool.retrieve();
        PublicationDao pd = new PublicationDao(con);
        pd.update(pub);
        ConnectionPool.putback(con);
    }

    /**
     * Удаление периодического издания из БД
     * @param per
     */

    public static void delete(Publication pub){
        Connection con = ConnectionPool.retrieve();
        PublicationDao pd = new PublicationDao(con);
        pd.delete(pub);
        ConnectionPool.putback(con);
    }
}