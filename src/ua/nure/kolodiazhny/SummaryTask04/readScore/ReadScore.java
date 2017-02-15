package ua.nure.kolodiazhny.SummaryTask04.readScore;

import ua.nure.kolodiazhny.SummaryTask04.dao.ScoreDao;
import ua.nure.kolodiazhny.SummaryTask04.entities.Score;
import ua.nure.kolodiazhny.SummaryTask04_2.connection.ConnectionPool;

import java.sql.Connection;
import java.util.ArrayList;
import org.apache.log4j.Logger;


public class ReadScore {
	private static final Logger LOGGER = Logger.getLogger(ReadScore.class);
    /**
     * Считываение всех периодических изданий из БД.
     * @return
     *        список периодических изданий.
     */
    public static ArrayList<Score> getList(){
        Connection con = ConnectionPool.retrieve();
        ScoreDao sd = new ScoreDao(con);
        LOGGER.error("Work");
        ArrayList<Score> res = sd.readAll();
        ConnectionPool.putback(con);
        return res;
    }
}