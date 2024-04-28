package com.east.furns.web;

import com.east.furns.pojo.Order;
import com.east.furns.pojo.OrderItem;
import com.east.furns.service.OrderItemService;
import com.east.furns.service.OrderService;
import com.east.furns.service.impl.OrderItemServiceImpl;
import com.east.furns.service.impl.OrderServiceImpl;
import com.east.furns.utils.DataUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/customer/orderItemServlet")
public class OrderItemServlet extends BasicServlet{
    OrderItemService orderItemService = new OrderItemServiceImpl();
    OrderService orderService = new OrderServiceImpl();
    protected void orderItemList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderItemTotalCount = 0;
        String orderId = request.getParameter("orderId");
        List<OrderItem> orderItems = orderItemService.queryOrdersByOrderId(orderId);

        Order order = orderService.queryOrderByOrderId(orderId);
        request.setAttribute("orderItems",orderItems);
        request.setAttribute("order",order);
        for (OrderItem orderItem : orderItems) {
            Integer count = orderItem.getCount();
            orderItemTotalCount +=count;
        }

        request.setAttribute("orderItemTotalCount",orderItemTotalCount);
        request.getRequestDispatcher("/views/order/order_detail.jsp").forward(request,response);
    }
}
