package com.example.b07project;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Customer_Landing extends AppCompatActivity {

    Customer customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_landing);
        //get the customer from the previous activity
        customer = (Customer) getIntent().getSerializableExtra(Resources.getSystem().getString(R.string.customer));

        //display the customer's name on the textview
        TextView txtCustomerName = findViewById(R.id.txtCustomerName);
        txtCustomerName.setText(customer.getUsername());
    }
    public void viewAllStores(View view){
        Intent intent = new Intent(this, viewStores.class);

        //customer = (Customer) getIntent().getSerializableExtra("account");
        intent.putExtra(Resources.getSystem().getString(R.string.customer), customer); //customer is NOT null
        startActivity(intent);
    }
    //customer can see their past Orders
    public void seeOrders(View view){
        //wipe and repopulate the Customer's orders to make sure theyre up to date
        customer.wipeAllOrders();
        customer.populateAllorders();

        Intent intent = new Intent(this,CustomerSeeOrders.class);
        intent.putExtra(Resources.getSystem().getString(R.string.customer), customer);
        startActivity(intent);
    }
}
