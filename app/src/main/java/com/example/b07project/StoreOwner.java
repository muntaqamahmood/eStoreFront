package com.example.b07project;

import java.io.Serializable;
import java.util.ArrayList;

public class StoreOwner extends Account implements Serializable {

    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Order> orders = new ArrayList<Order>();

    public StoreOwner() {
    }

    public StoreOwner(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){return username;}

    public String getPassword(){return password;}

    public void setUsername(String username){this.username = username;}

    public void setPassword(String password){this.password = password;}

    public void addProduct(Product p){
        products.add(p);
    }
}
