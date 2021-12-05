package com.example.b07project;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity {
    private Customer customer;
    private StoreOwner storeOwner;
    private CustomerOrder order;
    private ArrayList<String> products = new ArrayList<String>();
    private ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        customer = (Customer) getIntent().getSerializableExtra("customer");
        storeOwner = (StoreOwner)getIntent().getSerializableExtra("store_owner");
        ArrayAdapter<Product> productsAdapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1);
        lstView = findViewById(R.id.productListView);
        lstView.setAdapter(productsAdapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("store owners").child(storeOwner.getUsername()).child("Products");
        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(!task.isSuccessful()){
                    Log.e("B07 Project", "Data retrieve unsuccessful", task.getException());
                }else{
                    if(task.getResult().getChildren()!=null){

                    }
                }
            }
        });
    }
}