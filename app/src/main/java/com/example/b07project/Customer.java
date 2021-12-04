package com.example.b07project;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    //wipeAllOrders clears allOrders
    //this is done on login so we can repopulate it without duplicating
    public void wipeAllOrders(){allOrders.clear();}

    //populateAllOrders will pull CustomerOrders from firebase and put it in allOrders
    void populateAllorders() {
        //read the database
/**
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("customers").child(username).child("Products");
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
 **/
    }

}
