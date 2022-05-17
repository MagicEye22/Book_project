package com.zpp.Service;

import com.zpp.pojo.Cart;
import com.zpp.pojo.CartItem;
import com.zpp.pojo.Order;
import com.zpp.pojo.OrderItem;

import java.util.List;

/**
 * @author : zpp
 * @version : 1.0
 */
public interface OrderService {
    public  String createOrder(Cart cart,Integer userId);

    List<Order> showMyOrders(Integer userId);

    List<Order> showAllOrders();


    List<OrderItem> showOrderDetail(String orderId);

    void sendOrder(String orderId);

    void receiverOrder(String orderId);
}
