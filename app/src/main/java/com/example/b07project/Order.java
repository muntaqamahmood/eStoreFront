package com.example.b07project;

import java.util.ArrayList;
import java.util.HashSet;

public class Order extends Product {

    ArrayList<Product> items = new ArrayList<Product>();
    boolean completed;

    public Order(){

    }

    public void addItem(Product product){
        //adds a product to the order list
        items.add(product);
    }

    //once the customer is done placing the order the order should be added to all_orders and all
    // orders should be able to be displayed upon request by store owner


    public void removeItem(Product product){
        // removes an item from the order if it exists in the order
        if(items.contains(product)){
            items.remove(product);
        }
    }

    @Override
    public String toString(){
        String order;
        order = "Order:\n";
        for(Product p:items){
            order += p.toString() + "\n";
        }

        order += "\n";
        return order;
    }




}
