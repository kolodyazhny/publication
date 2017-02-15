package ua.nure.kolodiazhny.SummaryTask04.subscribtion;

import ua.nure.kolodiazhny.SummaryTask04.dao.ScoreDao;
import ua.nure.kolodiazhny.SummaryTask04.entities.Score;
import ua.nure.kolodiazhny.SummaryTask04_2.connection.ConnectionPool;

import java.sql.Connection;

public class ScoreChecker {
    /**
     * �������� ��������� �����.
     * @param card
     * @return
     *        true - ���� ���������� ����� ����������������� � ��;
     *        false - ���� �� ����������������.
     */
    public static boolean check(Score score){
        Connection con = ConnectionPool.retrieve();
        ScoreDao sd = new ScoreDao(con);
        boolean valid = sd.validScore(score);
        ConnectionPool.putback(con);
        return valid;
    }
}