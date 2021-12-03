package com.example.b07project;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class viewOrders extends AppCompatActivity {

    private ArrayList<String> str_orders;
    private ArrayAdapter<String> ordersAdapter;
    private ListView listView;
    private ArrayList<Order> orders;

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
        StoreOwner owner = (StoreOwner) getIntent().getSerializableExtra("account");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("store owners");
//        ValueEventListener listener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                ref.child(owner.getUsername()).child("Orders").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DataSnapshot> task) {
//                        if(!task.isSuccessful()){
//                            Context context = getApplicationContext();
//                            Toast.makeText(context,"Error getting data", Toast.LENGTH_LONG).show();
//                        }else{
//                            orders = (ArrayList<Order>)task.getResult().getValue();
//                        }
//                    }
//                });
//
//                items_to_str(orders);
//
//                ordersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,str_orders);
//                listView.setAdapter(ordersAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        }
        ref.child(owner.getUsername()).child("Orders").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(!task.isSuccessful()){
                    Context context = getApplicationContext();
                    Toast.makeText(context,"Error getting data", Toast.LENGTH_LONG).show();
                }else{
                    orders = (ArrayList<Order>)task.getResult().getValue();
                }
            }
        });

        items_to_str(orders);

        ordersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,str_orders);
        listView.setAdapter(ordersAdapter);

        setUpListViewListener();


    }

    private void setUpListViewListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                StoreOwner owner = (StoreOwner) getIntent().getSerializableExtra("account");
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("store owners");
                Context context = getApplicationContext();

                ref.child(owner.getUsername()).child("Orders");

                orders.remove(position);
                Toast.makeText(context,"Item Removed", Toast.LENGTH_LONG).show();

                ref.child(owner.getUsername()).child("Orders").setValue(orders);

                ordersAdapter.notifyDataSetChanged();
                return true;

            }
        });
    }

}
