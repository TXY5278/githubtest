package com.test;

import com.it.util.DruidUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class StuTest {
    @Test
    public void getDs() throws SQLException {
        Connection connection = DruidUtils.getConnection();
        System.out.println("connection = " + connection);
    }
}
