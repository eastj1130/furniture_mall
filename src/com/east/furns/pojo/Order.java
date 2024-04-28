package com.east.furns.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private String id;
    private String dateTime;
    private BigDecimal price;
    private Integer status;
    private Integer memberId;

    public Order() {
    }

    public Order(String id, String dateTime, BigDecimal price, Integer status, Integer memberId) {
        this.id = id;
        this.dateTime = dateTime;
        this.price = price;
        this.status = status;
        this.memberId = memberId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", dateTime=" + dateTime +
                ", price=" + price +
                ", status=" + status +
                ", memberId=" + memberId +
                '}';
    }
}
