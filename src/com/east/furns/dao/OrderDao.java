package com.east.furns.dao;

import com.east.furns.pojo.Order;

import java.util.List;

public interface OrderDao {

    int saveOrder(Order order);
    List<Order> queryOrderByUserId(int id);

    Order queryOrderByOrderId(String orderId);
}
