package com.east.furns.web;

import com.east.furns.pojo.*;
import com.east.furns.service.OrderItemService;
import com.east.furns.service.OrderService;
import com.east.furns.service.impl.OrderItemServiceImpl;
import com.east.furns.service.impl.OrderServiceImpl;
import com.east.furns.utils.JdbcUtilsByDruid;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet("/customer/orderServlet")
public class OrderServlet extends BasicServlet {
    OrderService orderService = new OrderServiceImpl();
    OrderItemService orderItemService = new OrderItemServiceImpl();

    protected void generateOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // 判断是否登陆
        Member member = (Member) session.getAttribute("member");
        if (member == null) {
            response.sendRedirect(request.getContextPath() + "/views/member/login.jsp");
            return;
        }
        Integer memberId = member.getId();

        // 获取购物车，并判断是否购物车为空
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getItems().size() == 0) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }
        String orderId = new Date().getTime()+memberId+"";
        try{
            // 生成订单
            if (orderService.saveOrder(cart, memberId,orderId)) {
                JdbcUtilsByDruid.commit();

                //生成订单成功后，生成订单单个条目
                Map<Integer, CartItem> cartItems = cart.getItems();
                for (Integer key : cartItems.keySet()) {
                    CartItem cartItem = cartItems.get(key);
                    if (!orderItemService.saveOrderItem(cartItem,orderId)){
                        System.out.println("生成订单条目失败");
                        response.sendRedirect(request.getContextPath() + "/index.jsp");
                    }
                }
                System.out.println("线程名："+Thread.currentThread().getName()+"----连接名："+JdbcUtilsByDruid.connectionThreadLocal.get().hashCode());
                System.out.println(JdbcUtilsByDruid.connectionThreadLocal.get().hashCode());
                JdbcUtilsByDruid.commit();
                System.out.println(JdbcUtilsByDruid.connectionThreadLocal.get());

                response.sendRedirect(request.getContextPath() + "/customer/orderServlet?action=orderList");
            } else {
                System.out.println("生成订单失败");
                session.setAttribute("generateOrderMsg","库存不足");
                response.sendRedirect(request.getHeader("Referer"));
            }
        }catch (Exception e){
            JdbcUtilsByDruid.rollback();
            throw new RuntimeException(e);
        }


    }

    protected void orderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        HttpSession session = request.getSession();
        Member member = (Member)session.getAttribute("member");
        Integer memberId = member.getId();
        List<Order> orders = orderService.queryOrderByUserId(memberId);
        System.out.println("线程名："+Thread.currentThread().getName()+"----连接名："+JdbcUtilsByDruid.connectionThreadLocal.get());
        request.setAttribute("orders",orders);
        request.getRequestDispatcher("/views/order/order.jsp").forward(request,response);
    }
}
