package com.example.b07project;

import java.io.Serializable;
import java.util.ArrayList;

/** this class is for customers to make orders from individual stores **/
/** this can also be used by owners to keep track of orders and mark them complete **/
public class CustomerOrder implements Serializable {
    ArrayList<Product> items = new ArrayList<>();
    public String storeOwner;
    public Boolean completed;
    public String customer;

    public CustomerOrder(){}

    /** a new CustomerOrder should be made whenever a customer wants to buy an item from a store**/
    public CustomerOrder(String owner, String customer){
        storeOwner = owner;
        this.customer = customer;
    }

    /** This is for the owner to mark complete or not **/
    public void markComplete(){completed = true;}
    public void markUncomplete(){completed = false;}

    //wipe will remove all existing elements in items
    public void wipe(){
        items.clear();
    }

    //adToCart will add a product to the customer's order
    public void addToCart(Product p){
        items.add(p);
    }
    //removes a specific product from the Customer's order
    public void deleteProduct(Product p){
        items.remove(p);
    }

    @Override
    public String toString(){
        String output = "";
        if (!items.isEmpty()){
            for (Product p: items){
                output += p.toString() + ", ";
            }
            output += "from " + storeOwner;
        }
        return output;
    }
}
