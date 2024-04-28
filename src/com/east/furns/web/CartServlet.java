package com.east.furns.web;

import com.east.furns.pojo.Cart;
import com.east.furns.pojo.CartItem;
import com.east.furns.pojo.Furniture;
import com.east.furns.pojo.Member;
import com.east.furns.service.FurnitureService;
import com.east.furns.service.impl.FurnitureServiceImpl;
import com.east.furns.utils.DataUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/cartServlet")
public class CartServlet extends BasicServlet {
    FurnitureService furnitureService = new FurnitureServiceImpl();

    protected void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = null;
        // 获取 传来的商品id
        int furnId = DataUtils.parseInt(request.getParameter("furnId"), 1);
        Furniture furniture = furnitureService.querySingleFurnitureById(furnId);
        HttpSession session = request.getSession();
        Object cartSession = session.getAttribute("cart");

        // 创建购物车
        cart = cartSession == null ? new Cart() : (Cart) cartSession;

        // 根据传过来id来看购物车是否有该商品
        CartItem cartItem = cart.getItem(furnId);
        if (cartItem == null) {
            cartItem = new CartItem(furnId, furniture.getName(), 0, furniture.getPrice(), new BigDecimal("0"));
            cart.addItem(cartItem);
        }

        // 将购物车的该条目更新
        cartItem.setFurnCount(cartItem.getFurnCount() + 1);
        cartItem.setTotalFurnPrice(cartItem.getTotalFurnPrice().add(furniture.getPrice()));

        // 将购物车重新放进session
        session.setAttribute("cart", cart);
        response.sendRedirect(request.getHeader("Referer"));


    }

    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = null;
        int furnId = DataUtils.parseInt(request.getParameter("furnId"), 0);
        HttpSession session = request.getSession();
        Object cartSession = session.getAttribute("cart");
        cart = cartSession == null ? new Cart() : (Cart) cartSession;

        cart.deleteItem(furnId);
        response.sendRedirect(request.getHeader("Referer"));

    }


    protected void updateItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = null;
        int furnId = DataUtils.parseInt(request.getParameter("furnId"), 0);
        int newVal = DataUtils.parseInt(request.getParameter("newVal"), 1);
        HttpSession session = request.getSession();
        Object cartSession = session.getAttribute("cart");
        cart = cartSession == null ? new Cart() : (Cart) cartSession;
        cart.updateCount(furnId,newVal);
        response.sendRedirect(request.getHeader("Referer"));
    }

    protected void cleanCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = null;
        HttpSession session = request.getSession();
        Object cartSession = session.getAttribute("cart");
        cart = cartSession == null ? new Cart() : (Cart) cartSession;
        cart.clear();
        session.removeAttribute("cart");
        response.sendRedirect(request.getHeader("Referer"));

    }
}
