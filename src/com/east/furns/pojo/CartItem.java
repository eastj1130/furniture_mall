package com.east.furns.pojo;

import java.math.BigDecimal;

public class CartItem {
    private Integer furnId;
    private String furnName;
    private Integer furnCount;
    private BigDecimal furnPrice;
    private BigDecimal totalFurnPrice;

    public CartItem() {
    }

    public CartItem( Integer furnId, String furnName, Integer furnCount, BigDecimal furnPrice, BigDecimal totalFurnPrice) {
        this.furnId = furnId;
        this.furnName = furnName;
        this.furnCount = furnCount;
        this.furnPrice = furnPrice;
        this.totalFurnPrice = totalFurnPrice;
    }





    public Integer getFurnId() {
        return furnId;
    }

    public void setFurnId(Integer furnId) {
        this.furnId = furnId;
    }

    public String getFurnName() {
        return furnName;
    }

    public void setFurnName(String furnName) {
        this.furnName = furnName;
    }

    public Integer getFurnCount() {
        return furnCount;
    }

    public void setFurnCount(Integer furnCount) {
        this.furnCount = furnCount;
    }

    public BigDecimal getFurnPrice() {
        return furnPrice;
    }

    public void setFurnPrice(BigDecimal furnPrice) {
        this.furnPrice = furnPrice;
    }

    public BigDecimal getTotalFurnPrice() {
        return totalFurnPrice;
    }

    public void setTotalFurnPrice(BigDecimal totalFurnPrice) {
        this.totalFurnPrice = totalFurnPrice;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "furnId=" + furnId +
                ", furnName='" + furnName + '\'' +
                ", furnCount=" + furnCount +
                ", furnPrice=" + furnPrice +
                ", totalFurnPrice=" + totalFurnPrice +
                '}';
    }
}
