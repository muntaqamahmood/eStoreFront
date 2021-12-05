package com.example.b07project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CustomerViewStore extends AppCompatActivity {

    private StoreOwner owner;
    private ListView lstView;
    private Customer customer;
    private CustomerOrder order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_store);

        //receive intents from previous activity
        //get the accounts from previous activity
        owner = (StoreOwner) getIntent().getSerializableExtra("store_owner");
        customer = (Customer) getIntent().getSerializableExtra("customer");
        //set the label to be the username
        TextView txtStore = findViewById(R.id.lblStoreName);
        txtStore.setText(owner.getUsername());

        //preparing variables to be used by the listview
        lstView = findViewById(R.id.lstProducts);
        ArrayAdapter<Product> productsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        lstView.setAdapter(productsAdapter);

        //read the database and populate the listview with products
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("store owners").child(owner.getUsername()).child("Products");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("B07 Project", "Couldn't get data", task.getException());
                } else {
                    //go through every product under the store owner
                    //but make sure the product list is not empty
                    if (task.getResult().getChildren() != null) {
                        for (DataSnapshot child : task.getResult().getChildren()) {
                            Product p = child.getValue(Product.class);
                            productsAdapter.add(p);
                        }
                    }
                }
            }
        });
        order = new CustomerOrder(owner.getUsername(), customer.getUsername());
        setupListViewListener();
    }

    private void setupListViewListener() {
        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product productToAdd = (Product) lstView.getItemAtPosition(position);
                order.addToCart(productToAdd);
            }
        });
    }


    //feedback will display a short message to the user
    public void feedback(String msg){
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    //checkout will send the user to the checkout activity
    public void checkout(){ //uncomment when shadman connects his stuff

        Intent intent = new Intent(this, ShoppingCartActivity.class);
        intent.putExtra("customer", customer);
        intent.putExtra("store_owner", owner);
        intent.putExtra("order", order);
    }

}