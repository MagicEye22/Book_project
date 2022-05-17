package com.zpp.test;

import com.zpp.dao.OrderDao;
import com.zpp.dao.impl.BaseDao;
import com.zpp.dao.impl.OrderDaoImpl;
import com.zpp.pojo.Order;
import com.zpp.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author : zpp
 * @version : 1.0
 */
public class OrderDaoTest extends BaseDao {

    @Test
    public void saveOrder() {
        OrderDao orderDao=new OrderDaoImpl();
        orderDao.saveOrder(new Order("1111111",new Date(),new BigDecimal(1111),0,1));
    }

    @Test
    public void queryOrderByUserId() {
        OrderDao orderDao=new OrderDaoImpl();
        List<Order> orders = orderDao.queryOrderByUserId(1);
        orders.forEach(System.out::println);

    }

    @Test
    public void queryOrders() {
        OrderDao orderDao=new OrderDaoImpl();
        List<Order> orders = orderDao.queryOrders();
        orders.forEach(System.out::println);
    }


    @Test
    public void queryOrderItemsByOrderId() {
        OrderDao orderDao=new OrderDaoImpl();
        List<OrderItem> orderItems = orderDao.queryOrderItemsByOrderId("16515614067481");
        orderItems.forEach(System.out::println);
    }
}