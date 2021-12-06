package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class OwnerLanding extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_landing);
    }

    //addProduct will send the user to the activity for adding a product
    public void addProduct(View view){
        //retrieve the store owner
        StoreOwner owner = (StoreOwner) getIntent().getSerializableExtra("account");

        owner.wipeProducts();
        owner.populateProducts();

        Intent intent = new Intent(this, owner_add_product.class);
        intent.putExtra("store_owner", owner);
        startActivity(intent);
    }

    //seePage sends the user to view their own store
    public void seePage(View view){
        //retrieve the store owner
        StoreOwner owner = (StoreOwner) getIntent().getSerializableExtra("account");


        Intent intent = new Intent(this, StorePage.class);
        intent.putExtra("store_owner", owner);
        startActivity(intent);
    }

    //sends the storeowner to see their orders to be complete
    public void seeOrders(View view){
        //retrieve the store owner
        StoreOwner owner = (StoreOwner) getIntent().getSerializableExtra("account");
        owner.wipeOrders();
        owner.populateOrders();

        Intent intent = new Intent(this,ViewAllOrdersActivity.class);
        intent.putExtra("account", owner);
        startActivity(intent);
    }
    public void storeOwnerLogOut(View view){
        Intent intent = new Intent(OwnerLanding.this, MainActivity.class);
        startActivity(intent);
    }
}