package com.example.b07project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class StorePage extends AppCompatActivity {

    private StoreOwner owner;
    private ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_page);

        //get the owner
        owner = (StoreOwner) getIntent().getSerializableExtra("store_owner");
        //set the label to be the username
        TextView textView = findViewById(R.id.txtStorePage);
        textView.setText(owner.getUsername());

        //preparing variables to be used by the listview
        lstView = findViewById(R.id.lstProducts);
        ArrayAdapter<String> productsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        lstView.setAdapter(productsAdapter);

        //go thru the database (under this owner)
        //get the products into the arraylist using toString

        //read the database
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("store owners").child(owner.getUsername()).child("Products");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()){
                    Log.e("B07 Project", "Couldn't get data", task.getException());
                }else{
                    //go through every product under the store owner
                    for(DataSnapshot child:task.getResult().getChildren()){
                        Product p = child.getValue(Product.class);
                        String productToAdd = p.toString();

                        productsAdapter.add(productToAdd);
                    }
                }
            }
        });
    }
}