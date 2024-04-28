package com.east.furns.test;

import com.east.furns.pojo.CartItem;
import com.east.furns.service.OrderItemService;
import com.east.furns.service.impl.OrderItemServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

public class TestOrderItemServiceImpl {
    OrderItemService orderItemService = new OrderItemServiceImpl();
    @Test
    public void test(){
        CartItem cartItem = new CartItem(null, "dfs", 34, new BigDecimal("3434"), new BigDecimal("344"));
        boolean b = orderItemService.saveOrderItem(cartItem, "3423421");
        System.out.println(b);
    }
}
