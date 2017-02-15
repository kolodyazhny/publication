package ua.nure.kolodiazhny.SummaryTask04.connectionTest;

import static org.junit.Assert.*;

import org.junit.Test;

import ua.nure.kolodiazhny.SummaryTask04.dao.UserDao;
import ua.nure.kolodiazhny.SummaryTask04_2.connection.ConnectionPool;

import org.junit.Assert;


public class UserDaoTest {
    @Test
    public void chechCustomer(){
        UserDao ud = new UserDao(ConnectionPool.retrieve());
        Assert.assertEquals(ud.isCustomer("kmdkmvlmdv@meta.ua","cdpmcdpc"),false);
        Assert.assertEquals(ud.isCustomer("kolyan199410@meta.ua","1389"),true);
    }

}