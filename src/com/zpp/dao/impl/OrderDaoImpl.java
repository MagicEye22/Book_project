package com.zpp.dao.impl;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.zpp.dao.OrderDao;
import com.zpp.pojo.Cart;
import com.zpp.pojo.CartItem;
import com.zpp.pojo.Order;
import com.zpp.pojo.OrderItem;

import java.util.List;

/**
 * @author : zpp
 * rsion : 1.0
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int saveOrder(Order order) {
        String sql="insert into t_order(order_id,create_time ,price,status,user_id) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    /**
     * 用户 查看订单
     * @param userId
     * @return
     */
    @Override
    public List<Order> queryOrderByUserId(Integer userId) {
        //当数据库中字段名域javabean的属性不同时，要起别名不然查询为null
        String sql="select order_id orderId,create_time createTime ,price,status,user_id userId FROM t_order WHERE user_id = ? ";
        return queryForList(Order.class,sql,userId);
    }

    /**
     * 管理员 查看订单
     * @return
     */
    @Override
    public List<Order> queryOrders() {
        String sql="select order_id orderId,create_time createTime ,price,status FROM t_order";
        return queryForList(Order.class,sql);
    }

    /**
     * 查看订单详情 管理员&用户
     * @param orderId
     * @return
     */
    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        String sql="select name,count,price,total_price totalPrice from t_order_item where order_id=?";
        return queryForList(OrderItem.class,sql,orderId);
    }

    /**
     * 管理员发货 & 用户签收
     * @param orderId
     * @param i
     */
    @Override
    public void changeOrderStatus(String orderId, int i) {
        String sql="update t_order set status =? where order_id =?";
        update(sql ,i,orderId);
    }
}
