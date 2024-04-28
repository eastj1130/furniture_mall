package com.east.furns.service.impl;

import com.east.furns.dao.OrderDao;
import com.east.furns.dao.impl.OrderDaoImpl;
import com.east.furns.pojo.*;
import com.east.furns.service.FurnitureService;
import com.east.furns.service.OrderService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = new OrderDaoImpl();
    FurnitureService furnitureService =  new FurnitureServiceImpl();

    @Override
    public boolean saveOrder(Cart cart, Integer memberId,String orderId) {
        // 判断是否超卖
        Map<Integer, CartItem> items = cart.getItems();
        for (Integer key : items.keySet()) {
            CartItem cartItem = items.get(key);
            // 得到购物车中单个商品的数量
            Integer orderFurnitureCount = cartItem.getFurnCount();
            Furniture dBFurniture = furnitureService.querySingleFurnitureById(cartItem.getFurnId());
            Integer stock = dBFurniture.getStock();
            if (orderFurnitureCount>stock){
                return false;
            }
            // 更新数据库中的库存和销量
            dBFurniture.setStock(stock-orderFurnitureCount);
            dBFurniture.setSales(dBFurniture.getSales()+orderFurnitureCount);
            furnitureService.updateSingleFurnitureById(dBFurniture,cartItem.getFurnId());
        }


        BigDecimal totalPrice = cart.getTotalPrice();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd hh:mm:ss");
        String format = simpleDateFormat.format(date);
        Order order = new Order(orderId, format, totalPrice, 0, memberId);
        return orderDao.saveOrder(order) == 1 ? true : false;
    }

    @Override
    public List<Order> queryOrderByUserId(int id) {
        return orderDao.queryOrderByUserId(id);
    }

    @Override
    public Order queryOrderByOrderId(String orderId) {
        return orderDao.queryOrderByOrderId(orderId);
    }
}
