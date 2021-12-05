package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

public class OwnerLanding extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_landing);
    }



    //addProduct will send the user to the activity for adding a product
    public void addProduct(View view){
        //retrieve the store owner
        StoreOwner owner = (StoreOwner) getIntent().getSerializableExtra(Resources.getSystem().getString(R.string.store_owner));

        Intent intent = new Intent(this, owner_add_product.class);
        intent.putExtra(Resources.getSystem().getString(R.string.store_owner), owner);
        startActivity(intent);
    }

    //seePage sends the user to view their own store
    public void seePage(View view){
        //retrieve the store owner
        StoreOwner owner = (StoreOwner) getIntent().getSerializableExtra(Resources.getSystem().getString(R.string.store_owner));
        //get products from firebase and put that into owner.products
        owner.wipeProducts();
        owner.populateProducts();

        Intent intent = new Intent(this, StorePage.class);
        intent.putExtra(Resources.getSystem().getString(R.string.store_owner), owner);
        startActivity(intent);
    }

    public void seeOrders(View view){
        //retrieve the store owner
        StoreOwner owner = (StoreOwner) getIntent().getSerializableExtra(Resources.getSystem().getString(R.string.store_owner));
        owner.wipeProducts();
        owner.populateOrders();

        Intent intent = new Intent(this,ViewAllOrdersActivity.class);
        intent.putExtra(Resources.getSystem().getString(R.string.store_owner), owner);
        startActivity(intent);
    }
}