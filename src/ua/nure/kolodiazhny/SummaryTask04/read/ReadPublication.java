package ua.nure.kolodiazhny.SummaryTask04.read;

import ua.nure.kolodiazhny.SummaryTask04.dao.PublicationDao;
import ua.nure.kolodiazhny.SummaryTask04.entities.Publication;
import ua.nure.kolodiazhny.SummaryTask04_2.connection.ConnectionPool;

import java.sql.Connection;
import java.util.ArrayList;


public class ReadPublication {
    /**
     * Считываение всех периодических изданий из БД.
     * @return
     *        список периодических изданий.
     */
    public static ArrayList<Publication> getList(){
        Connection con = ConnectionPool.retrieve();
        PublicationDao pd = new PublicationDao(con);
        ArrayList<Publication> res = pd.readAll();
        ConnectionPool.putback(con);
        return res;
    }
}