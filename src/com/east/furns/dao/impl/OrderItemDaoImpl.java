package com.east.furns.dao.impl;

import com.east.furns.dao.BasicDao;
import com.east.furns.dao.OrderItemDao;
import com.east.furns.pojo.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BasicDao<OrderItem> implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into order_item(name,price,count,total_price,order_id) values(?,?,?,?,?)";
        return update(sql, orderItem.getName(), orderItem.getPrice(),
                orderItem.getCount(), orderItem.getTotalPrice(), orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrdersByOrderId(String id) {
        String sql = "select id,name,price,count,total_price as totalPrice,order_id as orderId from order_item where order_id = ?";

        return queryMulti(sql,OrderItem.class,id);
    }
}
