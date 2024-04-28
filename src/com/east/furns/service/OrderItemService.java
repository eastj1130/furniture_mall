package com.east.furns.service;

import com.east.furns.pojo.CartItem;
import com.east.furns.pojo.OrderItem;

import java.util.List;

public interface OrderItemService {
    boolean saveOrderItem(CartItem cartItem,String orderId);
     List<OrderItem> queryOrdersByOrderId(String id);
}
