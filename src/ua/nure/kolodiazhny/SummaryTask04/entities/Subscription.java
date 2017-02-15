package ua.nure.kolodiazhny.SummaryTask04.entities;

import java.sql.Timestamp;

/**
 * �����, ����������� �������� ������������ �� ������������� �������.
 */
public class Subscription {
    /**
     * id �������� �� ��.
     */
    private int id;
    /**
     * id ������������, ����������� ��������.
     */
    private int userID;
    /**
     * id �������������� �������, �� ������� ��������� ��������.
     */
    private int periodicalID;
    /**
     * ���� ������ �������� ��������.
     */
    private Timestamp start;
    /**
     * ���� ��������� �������� ��������.
     */
    private Timestamp end;

    public Subscription(int id, int userID, int periodicalID, Timestamp start, Timestamp end) {
        this.id = id;
        this.userID = userID;
        this.periodicalID = periodicalID;
        this.start = start;
        this.end = end;
    }

    public Subscription(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPeriodicalID() {
        return periodicalID;
    }

    public void setPeriodicalID(int periodicalID) {
        this.periodicalID = periodicalID;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }
}