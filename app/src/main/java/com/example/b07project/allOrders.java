package com.example.b07project;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class allOrders extends MainActivity {

    static allOrders all_orders = new allOrders();


    private ArrayList<Order> items;
    private ArrayList<String> str_items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView listView;


    private allOrders(){

    }

    public static allOrders getInstance(){

        return all_orders;
    }

    private void items_to_str(ArrayList<Order> o){
        str_items = new ArrayList<String>();
        for(Order i:o){
            str_items.add(i.toString());
        }
    }

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_orders);

        listView = findViewById(R.id.listView);

        items = new ArrayList<>();

        items_to_str(items);
        
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,str_items);
        listView.setAdapter(itemsAdapter);

        setUpListViewListener();


    }

    private void setUpListViewListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getApplicationContext();
                Toast.makeText(context,"Item Removed", Toast.LENGTH_LONG).show();

                items.remove(position);
                itemsAdapter.notifyDataSetChanged();
                return true;

            }
        });
    }





    private void addItem(Order order){

        if (!(order == null)){
            items.add(order);
        }

    }





}
