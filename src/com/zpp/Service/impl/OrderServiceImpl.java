package com.zpp.Service.impl;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.zpp.Service.OrderService;
import com.zpp.dao.BookDao;
import com.zpp.dao.OrderDao;
import com.zpp.dao.OrderItemDao;
import com.zpp.dao.impl.BookDaoImpl;
import com.zpp.dao.impl.OrderDaoImpl;
import com.zpp.dao.impl.OrderItemDaoImpl;
import com.zpp.pojo.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author : zpp
 * @n : 1.0
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao=new OrderDaoImpl();
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    private BookDao bookDao=new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号
        String orderId= System.currentTimeMillis()+""+userId;
        //创建一个订单对象
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        //保存订单
        orderDao.saveOrder(order);
        //遍历购物车中每一个商品项转换未订单项保存到数据库
        for (Map.Entry<Integer, CartItem> entry:cart.getItems().entrySet()){
            //获取购物车中的商品项
            CartItem cartItem=entry.getValue();
            //转化为每一个订单项
            OrderItem orderItem=new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            //保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);

            //更新库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());//销量增加
            book.setStock(book.getStock()-cartItem.getCount());//库存减少
            //保存数据
            bookDao.updateBook(book);
        }
            //清空购物车
            cart.clear();
        return orderId;
    }

    @Override
    public List<Order> showMyOrders(Integer userId) {
        return orderDao.queryOrderByUserId(userId);
    }

    @Override
    public List<Order> showAllOrders() {
        return orderDao.queryOrders();
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderDao.queryOrderItemsByOrderId(orderId);
    }

    /**
     * 管理员 发货
     * @param orderId
     */
    @Override
    public void sendOrder(String orderId) {
        orderDao.changeOrderStatus(orderId,1);
    }

    /**
     * 用户签收
     * @param orderId
     */
    @Override
    public void receiverOrder(String orderId) {
        orderDao.changeOrderStatus(orderId,2);
    }
}
