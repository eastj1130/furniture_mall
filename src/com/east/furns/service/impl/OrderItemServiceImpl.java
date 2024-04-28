package com.east.furns.service.impl;

import com.east.furns.dao.OrderItemDao;
import com.east.furns.dao.impl.OrderItemDaoImpl;
import com.east.furns.pojo.CartItem;
import com.east.furns.pojo.OrderItem;
import com.east.furns.service.OrderItemService;

import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {
    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    @Override
    public boolean saveOrderItem(CartItem cartItem, String orderId) {
        OrderItem orderItem = new OrderItem(null, cartItem.getFurnName(), cartItem.getFurnCount(), cartItem.getFurnPrice(), cartItem.getTotalFurnPrice(), orderId);
        return orderItemDao.saveOrderItem(orderItem) == 1 ? true : false;
    }

    @Override
    public List<OrderItem> queryOrdersByOrderId(String id) {
        return orderItemDao.queryOrdersByOrderId(id);
    }
}
