package com.zpp.test;

import com.zpp.Service.OrderService;
import com.zpp.Service.impl.OrderServiceImpl;
import com.zpp.pojo.Cart;
import com.zpp.pojo.CartItem;
import com.zpp.pojo.Order;
import com.zpp.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author : zpp
 * @version : 1.0
 */
public class OrderServiceTest {

    @Test
    public void createOrder() {
        Cart cart =new Cart();
        cart.addItem(new CartItem(1,"西游记",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"西游记",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"水浒传",1,new BigDecimal(100),new BigDecimal(100)));

        OrderService orderService=new OrderServiceImpl();
        System.out.println("订单号是："+orderService.createOrder(cart,1));
    }

    @Test
    public void showMyOrders() {
        OrderService orderService=new OrderServiceImpl();
        List<Order> orders = orderService.showMyOrders(1);
        orders.forEach(System.out::println);
    }

    @Test
    public void showAllOrders() {
        OrderService orderService=new OrderServiceImpl();
        List<Order> orders = orderService.showAllOrders();
        orders.forEach(System.out::println);
    }


    @Test
    public void showOrderDetail() {
        OrderService orderService=new OrderServiceImpl();
        List<OrderItem> orderItems = orderService.showOrderDetail("16515614067481");
        orderItems.forEach(System.out::println);
    }
}