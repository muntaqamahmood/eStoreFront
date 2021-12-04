package com.example.b07project;


public class ShoppingCart {
    private String pid,pname,price,brand,discount;

    public ShoppingCart() {
    }

    public ShoppingCart(String pid, String pname, String price, String brand, String discount) {
        this.pid = pid;
        this.pname = pname;
        this.price = price;
        this.brand = brand;
        this.discount = discount;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setQuantity(String brand) {
        this.brand = brand;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}