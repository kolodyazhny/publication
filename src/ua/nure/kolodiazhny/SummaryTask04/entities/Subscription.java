package ua.nure.kolodiazhny.SummaryTask04.entities;

import java.sql.Timestamp;

/**
 * Класс, описывающий подписку пользователя на периодическое издание.
 */
public class Subscription {
    /**
     * id подписки из БД.
     */
    private int id;
    /**
     * id пользователя, оформившего подписку.
     */
    private int userID;
    /**
     * id периодического издания, на которое оформлена подписка.
     */
    private int periodicalID;
    /**
     * Дата начала действия подписки.
     */
    private Timestamp start;
    /**
     * Дата окончания действия подписки.
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