package com.zpp.test;

import com.zpp.utils.JDBCUtils;
import org.junit.Test;

/**
 * @author : zpp
 * @version : 1.0
 */
public class JDBCUtilsTest {
    @Test
    public void JdbcTest(){

            System.out.println(JDBCUtils.getConnection());

    }
}
