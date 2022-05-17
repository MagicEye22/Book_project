package com.zpp.dao.impl;

import com.zpp.pojo.User;
import com.zpp.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author : zpp
 * @version : 1.0
 *
 * 实现通用的表的增删改查操作
 */
public abstract class BaseDao {
    //使用DbUtils操作数据库
    private QueryRunner queryRunner = new QueryRunner();


    /**
     * update() 方法用来执行：insert/update/delete语句
     *
     * @return 如果返回-1说明执行失败, 返回其它 表示影响的行数
     */
    public int update(String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.update(connection, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new RuntimeException(e);//将异常抛出，不然在其它程序调用时会不知道发生异常，就不会执行相应的操作
        }
//        return -1;
    }

    /**
     * 查询返回一条javaBean的sql语句
     *
     * @param type 返回类型的泛型
     * @param sql  执行的sql语句
     * @param args sql对应的参数
     * @param <T>  返回的类型的泛型
     * @return
     */
    public <T> T queryForOne(Class<T> type, String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanHandler<T>(type), args);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException(e);

        }
//        return null;
    }

    /**
     * 查询返回多条javaBean的sql语句
     *
     * @param type 返回类型的泛型
     * @param sql  执行的sql语句
     * @param args sql对应的参数
     * @param <T>  返回的类型的泛型
     * @return
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();
        try {
          return queryRunner.query(connection, sql, new BeanListHandler<>(type), args);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
//        return null;
    }

    /**
     * 执行返回一列一行的sql语句
     *
     * @param sql  执行的sql语句
     * @param args sql语句对应的参数值
     * @return
     */
    public Object queryForStringValue(String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();
        try {
          return   queryRunner.query(connection, sql, new ScalarHandler(), args);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
//        return null;
    }
}
