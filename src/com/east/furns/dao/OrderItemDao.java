package com.east.furns.dao;

import com.east.furns.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {
    int saveOrderItem(OrderItem orderItem);
    List<OrderItem> queryOrdersByOrderId(String id);
}
