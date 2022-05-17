package com.zpp.dao.impl;

import com.zpp.dao.OrderItemDao;
import com.zpp.pojo.Order;
import com.zpp.pojo.OrderItem;

/**
 * @author : zpp
 * @version : 1.0
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql="insert into t_order_item(name,count,price,total_price,order_id) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }
}