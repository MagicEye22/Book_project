package com.zpp.dao;

import com.zpp.pojo.Cart;
import com.zpp.pojo.CartItem;
import com.zpp.pojo.Order;
import com.zpp.pojo.OrderItem;

import java.util.List;

/**
 * @author : zpp
 * @version : 1.0
 */
public interface OrderDao {
    public  int saveOrder(Order order);


    List<Order> queryOrderByUserId(Integer userId);


    List<Order> queryOrders();


    List<OrderItem> queryOrderItemsByOrderId(String orderId);

    void changeOrderStatus(String orderId, int i);
}
