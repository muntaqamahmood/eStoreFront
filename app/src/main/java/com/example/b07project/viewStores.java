package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class viewStores extends AppCompatActivity {

    private ListView listView;
    ArrayList<StoreOwner> stores;
    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_orders);

        listView = findViewById(R.id.listView);

        stores = new ArrayList<StoreOwner>();

//        stores_to_str(stores);
//
//        ordersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,str_orders);
//        listView.setAdapter(ordersAdapter);
//
////        setUpListViewListener();


    }
}