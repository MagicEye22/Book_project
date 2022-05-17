package com.zpp.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.apache.commons.dbutils.DbUtils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author : zpp
 * @version : 1.0
 */
public class JDBCUtils {

    //防止调用一次就获取连接数据库连接池的连接条数
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conn = new ThreadLocal();

    static {

        try {

            Properties properties = new Properties();
            //读取jdbc.properties属性配置文件
            InputStream resourceAsStream = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(resourceAsStream);
            //创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取数据库连接池中的连接
     *
     * @return 如果返回null 说明获取连接失败
     */
    public static Connection getConnection() {
        Connection connection = conn.get();
        if (connection == null) {
            try {

                connection = dataSource.getConnection();//从数据库连接池中获取连接
                conn.set(connection);//保存到ThreadLocal对象中，供后面的jdbc操作使用
                connection.setAutoCommit(false);//设置为手动提交事务

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }


    /**
     * 回滚事务，并关闭释放连接
     */
    public  static void rollbackAndClose(){
        Connection connection = conn.get();
        if (connection!=null){//如果不等于null,说明之前使用过该链接，操作过数据库
            try {
                connection.rollback();//回滚事务
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.setAutoCommit(true);//关闭前在把事务设置为自动提交
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                DbUtils.closeQuietly(connection);//关闭连接
            }
        }

        //一定要执行remove操作，否则就会出错(因为Tomcat底层使用了线程池技术)
        conn.remove();
    }


    /**
     * 提交事务，并关闭释放连接
     */
    public static  void commitAndClose(){
        Connection connection = conn.get();
        if (connection!=null){//如果不等于null,说明之前使用过该链接，操作过数据库
            try {
                connection.commit();//提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.setAutoCommit(true);//关闭前在把事务设置为自动提交
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                DbUtils.closeQuietly(connection);//关闭连接
            }
        }

        //一定要执行remove操作，否则就会出错(因为Tomcat底层使用了线程池技术)
        conn.remove();
    }




    /**
     * 关闭连接，放回数据库连接池
     *
     * @param connection
     */
   /* public static void close(Connection connection) {
      *//*  if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*//*
        DbUtils.closeQuietly(connection);
    }*/
}
