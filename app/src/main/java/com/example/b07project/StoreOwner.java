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

public class StoreOwner extends Account implements Serializable {

    ArrayList<Product> products = new ArrayList<>();;
    ArrayList<Order> orders = new ArrayList<>();

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

    //populateProducts will read products from the firebase and put them in products (field)
    void populateProducts() {
        //read the database

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("store owners").child(username).child("Products");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                //in case we cant get that value
                if (!task.isSuccessful()) {
                    Log.e("B07 Project", "Couldn't get data", task.getException());
                } else {
                    //go through every product under the store owner
                    if (task.getResult().getChildren() != null) {
                        for (DataSnapshot child : task.getResult().getChildren()) {
                            Product p = child.getValue(Product.class);
                            products.add(p);
                        }
                    }

                }
            }
        });
    }

    //remove ALL products from the arraylist
    void wipeProducts(){
        products.clear();
    }

    public void addOrder(Order order){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        if (!(order == null)){
            orders.add(order);
            ref.child("store owners").child(username).child("Orders").setValue(orders);
        }
    }
}
