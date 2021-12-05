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

        Intent intent = new Intent(this, viewStores.class);
        customer = (Customer) getIntent().getSerializableExtra("account");
        intent.putExtra("customers", customer);
        startActivity(intent);
    }
    public void seeOrders(View view){
        //customer can see their past Orders
        customer = (Customer) getIntent().getSerializableExtra("account");

        //wipe and repopulate the Customer's orders to make sure theyre up to date
        customer.wipeAllOrders();
        customer.populateAllorders();

        Intent intent = new Intent(this,CustomerSeeOrders.class);
        intent.putExtra("account", customer);
        startActivity(intent);
    }
}
