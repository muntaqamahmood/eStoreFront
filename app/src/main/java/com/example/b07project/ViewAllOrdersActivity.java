package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ViewAllOrdersActivity extends AppCompatActivity {

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
    protected  void onCreate(Bundle savedInstanceState) {
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


//        ref.child(owner.getUsername()).child("Orders").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if(!task.isSuccessful()){
//                    Context context = getApplicationContext();
//                    Toast.makeText(context,"Error getting data", Toast.LENGTH_LONG).show();
//                }else{
//                    orders = (ArrayList<Order>)task.getResult().getValue();
//                }
//            }
//        });
//
//        items_to_str(orders);
//
//        ordersAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,str_orders);
//        listView.setAdapter(ordersAdapter);
//
//        setUpListViewListener();
//
//
//    }
//
//    private void setUpListViewListener() {
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//
//                StoreOwner owner = (StoreOwner) getIntent().getSerializableExtra("account");
//                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("store owners");
//                Context context = getApplicationContext();
//
//                ref.child(owner.getUsername()).child("Orders");
//
//                orders.remove(position);
//                Toast.makeText(context,"Item Removed", Toast.LENGTH_LONG).show();
//
//                ref.child(owner.getUsername()).child("Orders").setValue(orders);
//
//                ordersAdapter.notifyDataSetChanged();
//                return true;
//
//            }
//        });
    }
}