package com.example.b07project;

import java.util.ArrayList;

public class Customer extends Account {

    ArrayList<CustomerOrder> allOrders = new ArrayList<>();

    public Customer(){}

    public Customer(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){return username;}

    public String getPassword(){return password;}

    public void setUsername(String username){this.username = username;}

    public void setPassword(String password){this.password = password;}
}
