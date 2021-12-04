package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Customer_Landing extends AppCompatActivity {

    Customer customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_landing);
        //get the customer from the previous activity
        customer = (Customer) getIntent().getSerializableExtra("account");

        //display the customer's name on the textview
        TextView txtCustomerName = findViewById(R.id.txtCustomerName);
        txtCustomerName.setText(customer.getUsername());
    }
    public void viewAllStores(View view){
        customer = (Customer) getIntent().getSerializableExtra("account");

        Intent intent = new Intent(this, ShoppingCartActivity.class);//<- we need a viewAllStores page for the customer too if we dont already
        intent.putExtra("store_owner", customer);//ShoppingCartActivity ^ is just a place holder for now
        startActivity(intent);
    }
    public void seeOrders(View view){
        //customer can see their past Orders
        customer = (Customer) getIntent().getSerializableExtra("account");

        Intent intent = new Intent(this,ShoppingCartActivity.class);//<-- we need an order history page for customer
        intent.putExtra("account", customer);//ShoppingCartActivity ^ is just a place holder for now
        startActivity(intent);//gonna take a shower rn meet u guys at 10am hopefully i can work on this, commenting to lyg know before commit
    }
}
