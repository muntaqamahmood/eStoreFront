package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
        ArrayList<String> products = new ArrayList<>();
        ArrayAdapter<String> productsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        lstView.setAdapter(productsAdapter);

        //go thru the database (under this owner)
        //get the products into the arraylist using toString


    }
}