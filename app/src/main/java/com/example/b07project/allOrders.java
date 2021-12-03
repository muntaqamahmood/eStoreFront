package com.example.b07project;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class allOrders extends StoreOwner {

    private ArrayList<Order> orders;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    public allOrders(){

    }

    public allOrders(ArrayList<Order> o, String name){
        orders = o;
    }

    private void addOrder(Order order, String username){

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        if (!(order == null)){
            orders.add(order);
            ref.child("store owners").child(username).child("Orders").setValue(orders);
        }

    }
}
