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
    //sends the customer to the page where they select which store to buy from
    public void viewAllStores(View view){
        Intent intent = new Intent(this, viewStores.class);

        intent.putExtra("customer", customer);
        intent.putExtra("customer", customer);
        startActivity(intent);
    }

    //customer can see their past Orders
    public void seeOrders(View view){

        Intent intent = new Intent(this,CustomerSeeOrders.class);
        intent.putExtra("account", customer);
        startActivity(intent);
    }
    //sends customer back to landing page
    public void customerLogOut(View v){
        Intent intent = new Intent(Customer_Landing.this, MainActivity.class);
        startActivity(intent);
    }
}
