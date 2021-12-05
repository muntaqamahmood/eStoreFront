package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;

public class CustomerSeeOrders extends AppCompatActivity {

    Customer customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_see_orders);
        customer = (Customer) getIntent().getSerializableExtra(Resources.getSystem().getString(R.string.customer));

    }
}