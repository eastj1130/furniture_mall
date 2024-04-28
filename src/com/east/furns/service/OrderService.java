package com.east.furns.service;

import com.east.furns.pojo.Cart;
import com.east.furns.pojo.Order;
import com.east.furns.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    boolean saveOrder(Cart cart, Integer memberId,String orderId);
    List<Order> queryOrderByUserId(int id);
    Order queryOrderByOrderId(String orderId);
}
