package com.example.b07project;

import java.util.ArrayList;

public class ShoppingCart extends Order{
    String userId;
    String title;
    String currency;
    String price;
    String quantity;
    String total;

    public ShoppingCart(){
        System.out.println("Your shopping cart is empty.");
    }

    public ShoppingCart(String userId, String title, String currency, String price, String quantity, String total) {
        this.userId = userId;
        this.title = title;
        this.currency = currency;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public String getId() {
        return userId;
    }

    public void setId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSubTotal() {
        return total;
    }

    public void setSubTotal(String total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
