package com.east.furns.dao.impl;

import com.east.furns.dao.BasicDao;
import com.east.furns.dao.OrderDao;
import com.east.furns.pojo.Order;

import java.util.List;

public class OrderDaoImpl extends BasicDao<Order> implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into `order`(`id`,`create_time`,`price`,`status`,`member_id`) values(?,?,?,?,?)";
        return update(sql, order.getId(), order.getDateTime(), order.getPrice(), order.getStatus(), order.getMemberId());
    }

    @Override
    public List<Order> queryOrderByUserId(int id) {
        /**
         * CREATE TABLE `order` (
         *   `id` varchar(64) NOT NULL,
         *   `create_time` datetime NOT NULL,
         *   `price` decimal(11,2) NOT NULL,
         *   `status` tinyint NOT NULL,
         *   `member_id` int NOT NULL,
         *   PRIMARY KEY (`id`)
         * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3
         */
        String sql = "select `id`,`create_time` as dateTime, `price`, `status`, `member_id` as memberId from `order` where member_id = ?";
        return queryMulti(sql,Order.class,id);
    }

    @Override
    public Order queryOrderByOrderId(String orderId) {
        String sql = "select `id`,`create_time` as dateTime, `price`, `status`, `member_id` as memberId from `order` where id = ?";
        return querySingle(sql,Order.class,orderId);
    }
}
