package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OwnerLanding extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_landing);
    }



    //addProduct will send the user to the UI for adding a product
    public void addProduct(View view){
        //retrieve the store owner
        StoreOwner owner = (StoreOwner) getIntent().getSerializableExtra("account");

        Intent intent = new Intent(this, owner_add_product.class);
        intent.putExtra("store_owner", owner);
        startActivity(intent);
    }

    public void seePage(View view){
        //send us to the store page!
    }
}