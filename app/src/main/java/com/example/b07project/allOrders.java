package com.example.b07project;

public class allOrders extends Order {

    static allOrders all_orders = new allOrders();

    private allOrders(){

    }

    public static allOrders getInstance(){
        return all_orders;
    }
}
