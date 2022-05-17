package com.zpp.test;

import com.zpp.dao.OrderItemDao;
import com.zpp.dao.impl.OrderItemDaoImpl;
import com.zpp.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author : zpp
 * @version : 1.0
 */
public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao=new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(null,"武林外传",1,new BigDecimal(100),new BigDecimal(100),"1111111"));
        orderItemDao.saveOrderItem(new OrderItem(null,"武林外传2",12,new BigDecimal(100),new BigDecimal(1200),"1111111"));
        orderItemDao.saveOrderItem(new OrderItem(null,"武林外传3",10,new BigDecimal(100),new BigDecimal(1000),"1111111"));
    }
}