package com.east.furns.pojo;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Integer, CartItem> items = new HashMap<>();

    public Cart() {
    }

    public Cart(Map<Integer, CartItem> items) {
        this.items = items;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                '}';
    }

    public Cart addItem(CartItem cartItem) {
        items.put(cartItem.getFurnId(), cartItem);
        return this;
    }
    public Map<Integer, CartItem> deleteItem(int cartItemId){
        items.remove(cartItemId);
        return items;
    }
    public Map<Integer, CartItem> clear(){
        items.clear();
        return items;
    }
    public Map<Integer, CartItem> updateCount(int cartItemId,int newVal){
        CartItem cartItem = items.get(cartItemId);

        cartItem.setFurnCount(newVal);
        cartItem.setTotalFurnPrice(cartItem.getFurnPrice().multiply(new BigDecimal(cartItem.getFurnCount())));
        return items;
    }
    public int getTotalCount(){
        int sum = 0;
        for (Integer key : items.keySet()) {
            CartItem cartItem = items.get(key);
            Integer furnCount = cartItem.getFurnCount();
            sum+=furnCount;
        }
        return sum;
    }
    public BigDecimal getTotalPrice(){
        BigDecimal bigDecimal = new BigDecimal("0");
        for (Integer key : items.keySet()) {
            CartItem cartItem = items.get(key);
            System.out.println(cartItem);
            BigDecimal furnCount = cartItem.getTotalFurnPrice();
            System.out.println(furnCount);
           bigDecimal= bigDecimal.add(furnCount);
        }
        System.out.println(bigDecimal);
        return bigDecimal;
    }
    public CartItem getItem(Integer carItemId){
        return items.get(carItemId);
    }

}
