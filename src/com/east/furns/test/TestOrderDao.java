package com.east.furns.test;

import com.east.furns.dao.OrderDao;
import com.east.furns.dao.impl.OrderDaoImpl;
import com.east.furns.pojo.Order;
import com.east.furns.service.OrderService;
import com.east.furns.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.util.List;

public class TestOrderDao {
    OrderDao orderDao = new OrderDaoImpl();
    OrderService orderService = new OrderServiceImpl();
    @Test
    public void test(){
        List<Order> orders = orderService.queryOrderByUserId(1);
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}
