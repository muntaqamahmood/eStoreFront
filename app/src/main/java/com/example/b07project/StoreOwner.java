package com.example.b07project;

import android.util.Log;
import android.widget.ArrayAdapter;

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
    ArrayList<CustomerOrder> orders = new ArrayList<>();

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

    void populateAddWriteProducts(Product p){
        //read the database

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("store owners").child(username).child("Products");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                //in case we cant get that value
                if (!task.isSuccessful()) {
                    Log.e("B07 Project", "Couldn't get data", task.getException());
                } else {
                    wipeProducts();
                    //go through every product under the store owner
                    if (task.getResult().getChildren() != null) {
                        for (DataSnapshot child : task.getResult().getChildren()) {
                            Product p = child.getValue(Product.class);
                            products.add(p);
                        }
                    }
                    addProduct(p);
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                    ref.child("store owners").child(username).child("Products").setValue(products);
                }
            }
        });

    }


    void populateAddWriteOrders(CustomerOrder order){

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("store owners").child(username).child("Orders");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(!task.isSuccessful())
                    Log.e("B07 Project", "Couldn't get data", task.getException());
                else{
                    wipeOrders();
                    if(task.getResult().getChildren() != null) {
                        for (DataSnapshot child : task.getResult().getChildren()) {
                            CustomerOrder customerOrder = child.getValue(CustomerOrder.class);
                            orders.add(customerOrder);
                        }
                    }
                    orders.add(order);
                    DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference();
                    reference2.child("store owners").child(order.storeOwner).child("Orders").setValue(orders);
                }
            }
        });
    }

    void removeAndSetComplete(int position, ArrayAdapter<CustomerOrder> adapter){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("store owners").child(username).child("Orders");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(!task.isSuccessful())
                    Log.e("B07 Project", "Couldn't get data", task.getException());
                else{
                    wipeOrders();
                    if(task.getResult().getChildren() != null) {
                        for (DataSnapshot child : task.getResult().getChildren()) {
                            CustomerOrder customerOrder = child.getValue(CustomerOrder.class);
                            orders.add(customerOrder);
                        }
                    }
                    CustomerOrder customerOrder = orders.get(position);
                    adapter.remove(customerOrder);
                    UpdateCustomerOrder(customerOrder,customerOrder.customer);
                    orders.remove(position);
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("store owners");
                    ref.child(username).child("Orders").setValue(orders);
                }
            }
        });
    }

    void UpdateCustomerOrder(CustomerOrder order, String customer){
        DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("customers");
        ref2.child(customer).child("Orders").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!(task.isSuccessful())) {
                    //error
                } else {
                    if (task.getResult().hasChildren()) {

                        int i = 0;
                        for (DataSnapshot child : task.getResult().getChildren()) {
                            CustomerOrder order2 = child.getValue(CustomerOrder.class);
                            if (order2 != null) {
                                if (order.orderNumber == order2.orderNumber) {
                                    order2.markComplete();
                                    ref2.child(customer).child("Orders").child((String.valueOf(i))).setValue(order2);
                                }
                            }
                            i++;
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

    //remove All orders from the arraylist
    void wipeOrders(){
        orders.clear();
    }

    public void addOrder(CustomerOrder order){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        orders.add(order);
        ref.child("store owners").child(username).child("Orders").setValue(orders);

    }
}
