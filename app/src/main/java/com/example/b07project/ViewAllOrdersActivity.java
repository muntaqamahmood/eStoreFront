package com.example.b07project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewAllOrdersActivity extends AppCompatActivity {
    
    private ArrayAdapter<CustomerOrder> ordersAdapter;
    private ListView listView;
    private StoreOwner owner;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_orders);

        //finds the listview on the UI
        listView = findViewById(R.id.listView);
        owner = (StoreOwner) getIntent().getSerializableExtra("account");

        ordersAdapter = new ArrayAdapter<CustomerOrder>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(ordersAdapter);

        //read data from firebase and display the orders onto the listview
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("store owners").child(owner.username).child("Orders");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(!task.isSuccessful()){
                    Log.e("B07 Project", "Couldn't get data", task.getException());
                }
                else{
                    for(DataSnapshot child : task.getResult().getChildren()) {
                        CustomerOrder order = child.getValue(CustomerOrder.class);

                        //make sure it's not an empty order
                        if (order.items != null) {
                            ordersAdapter.add(order);
                        }
                    }
                }
            }
        });

        setUpListViewListener();

    }


    private void setUpListViewListener(){
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //removes the item clicked from the owner orders list and updates the orders list
                //go to the customer in the order and set its boolean completed to true

                Context context = getApplicationContext();
                Toast.makeText(context,"Order Completed!",Toast.LENGTH_LONG).show();

                owner.removeAndSetComplete(position, ordersAdapter); //removes from owner.orders and writes it

                Intent intent = new Intent(ViewAllOrdersActivity.this, OwnerLanding.class);
                intent.putExtra("account", owner);
                startActivity(intent);

                return true;

            }
        });

    }




    private String formatOrder(CustomerOrder order){
        //formats the output
        String output = "OrderNumber: " + order.orderNumber + ", From: " + order.customer + '\n';
        for (Product p: order.items){
            output += p.toString() + '\n';
        }
        output+= "Completed: " + order.completed;
        return output;
    }
}