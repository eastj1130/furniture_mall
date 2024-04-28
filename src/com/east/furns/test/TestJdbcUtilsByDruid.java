package com.east.furns.test;

import com.east.furns.utils.JdbcUtilsByDruid;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Connection;

public class TestJdbcUtilsByDruid {
    @Test
    public void test(){
        Connection connection = JdbcUtilsByDruid.getConnection();
        System.out.println(connection);
    }
}
