package com.example.b07project;


public class ShoppingCart {
    private String pid,pname,brand,price;

    // constructors for ShoppingCart
    public ShoppingCart() {
    }

    public ShoppingCart(String pid, String pname, String brand, String price) {
        this.pid = pid;
        this.pname = pname;
        this.brand = brand;
        this.price = price;
    }

    // getters and setters for ShoppingCart variables
    public String getPid() { return pid; }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}