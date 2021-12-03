package com.example.b07project;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class viewOrders extends MainActivity {

    //static allOrders all_orders = new allOrders();



    private ArrayList<String> str_orders;
    private ArrayAdapter<String> ordersAdapter;
    private ListView listView;
    //String username;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();




//    public static allOrders getInstance(){
//
//        return all_orders;
//    }

    private void items_to_str(ArrayList<Order> o){
        str_orders = new ArrayList<String>();
        for(Order i:o){
            str_orders.add(i.toString());
        }
    }

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_orders);

        listView = findViewById(R.id.listView);

        ArrayList<Order> orders = new ArrayList<>();

        items_to_str(orders);

        ordersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,str_orders);
        listView.setAdapter(ordersAdapter);

//        setUpListViewListener();


    }

    private void setUpListViewListener() {
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Context context = getApplicationContext();
//                Toast.makeText(context,"Item Removed", Toast.LENGTH_LONG).show();
//                ref.child("store owners").child(username).child("Orders").
//
//                orders.remove(position);
//                ordersAdapter.notifyDataSetChanged();
//                return true;
//
//            }
//        });
    }

}
