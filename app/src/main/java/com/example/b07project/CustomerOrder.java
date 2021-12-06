package com.example.b07project;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/** this class is for customers to make orders from individual stores **/
/** this can also be used by owners to keep track of orders and mark them complete **/
public class CustomerOrder implements Serializable {
    public ArrayList<Product> items;
    public String storeOwner;
    public Boolean completed;
    public String customer;
    public int orderNumber;

    public CustomerOrder(){}

    /** a new CustomerOrder should be made whenever a customer wants to buy an item from a store**/
    public CustomerOrder(String owner, String customer){
        storeOwner = owner;
        this.customer = customer;
        items = new ArrayList<>();
        completed = false;
        orderNumber = fetchOrderCount();
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
        String output = "OrderNumber: " + orderNumber + ", By: " + customer + '\n';
        for (Product p: items){
            output += p.toString() + '\n';
        }
        output+= "Completed: " + completed + ", By: " + storeOwner;
        return output;
    }

    @Override
    public int hashCode(){
        return orderNumber;
    }

    //generates an orderNumber using the current time
    public int fetchOrderCount(){
        int time  = (int) (new Date().getTime() / 1000);
        return time;
    }

}
