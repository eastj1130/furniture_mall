package com.east.furns.dao;

import com.east.furns.utils.JdbcUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BasicDao<T> {
    private QueryRunner queryRunner = new QueryRunner();
    // 执行dml语句  增删改
    public int update(String sql,Object... parameters){
        Connection connection = null;
        try {
            connection = JdbcUtilsByDruid.getConnection();
            return queryRunner.update(connection,sql,parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
// 执行查询语句，返回多个数据，放到集合中
    public List<T> queryMulti( String sql,Class<T> clazz,Object... parameters){
        Connection connection = null;
        try {
            connection = JdbcUtilsByDruid.getConnection();

            List<T> query = queryRunner.query(connection, sql, new BeanListHandler<>(clazz), parameters);
            JdbcUtilsByDruid.commit();
            return query;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    // 执行 查询语句，返回单个数据
    public T querySingle( String sql,Class<T> clazz,Object... parameters){
        Connection connection = null;
        try {
            connection = JdbcUtilsByDruid.getConnection();
            T query = queryRunner.query(connection, sql, new BeanHandler<>(clazz), parameters);
            JdbcUtilsByDruid.commit();
            return query;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // 执行查询语句，返回单个列数据
    public Object queryScalar(String sql,Object... parameters){
        Connection connection = null;
        try {
            connection = JdbcUtilsByDruid.getConnection();
            Object query = queryRunner.query(connection, sql, new ScalarHandler(), parameters);
            JdbcUtilsByDruid.commit();
            return query;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
