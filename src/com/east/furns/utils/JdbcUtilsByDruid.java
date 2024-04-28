package com.east.furns.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtilsByDruid {
    private static DataSource dataSource;
    private static Properties info;
    public static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

    static {
        info = new Properties();
        try {
            InputStream resourceAsStream = JdbcUtilsByDruid.class.getClassLoader().getResourceAsStream("jdbc.properties");
            info.load(resourceAsStream);
//            user = info.getProperty("druid.user");
//            password = info.getProperty("druid.password");
//            driver = info.getProperty("druid.driverClassName");
//            url = info.getProperty("druid.url");
//            Class.forName(driver);
            dataSource = DruidDataSourceFactory.createDataSource(info);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
//    public static Connection getConnection(){
//
//        try {
//            return dataSource.getConnection();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static Connection getConnection() {
        Connection connection = connectionThreadLocal.get();
        if (connection == null) {
            try {
                connection = dataSource.getConnection();
                connection.setAutoCommit(false);
                connectionThreadLocal.set(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;

    }


    public static void commit() {
        Connection connection = connectionThreadLocal.get();
        if (connection != null) {

            try {
                connection.commit();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.close();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        connectionThreadLocal.remove();

    }


    public static void rollback() {
        Connection connection = connectionThreadLocal.get();
        if (connection != null) {

            try {
                connection.rollback();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.close();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        connectionThreadLocal.remove();
    }

    public static void close(ResultSet resultset, Statement statement, Connection connection) {

        try {
            if (resultset != null) {
                resultset.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (dataSource != null) {

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}
