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
        orderNumber = fetchOrderCount();
        items = new ArrayList<>();
        completed = false;
        incrementOrders();
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
            output += "#" + orderNumber + ": ";
            for (Product p: items){
                output += p.toString() + ", ";
            }
            output += "from " + storeOwner;
        }
        return output;
    }

    @Override
    public int hashCode(){
        return orderNumber;
    }

    //gets the order count from firebase
    int fetchOrderCount(){
        int num = 0;
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("OrderCount");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("B07 Project", "Couldn't get data", task.getException());
                } else {
                    int num = ((Long)task.getResult().child("number").getValue()).intValue();
                }
            }
        });
        return num;
    }

    void incrementOrders(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("OrderCount").child("number").setValue(this.orderNumber + 1);
    }
}
