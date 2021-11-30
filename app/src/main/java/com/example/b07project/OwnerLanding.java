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

    //DONT FORGET TO GET THE INTENT SOMEWHERE (name it 'owner')
    //getIntent().getSerializableExtra("whatever the string was");

    //addProduct will send the user to the UI for adding a product
    public void addProduct(View view){
        Intent intent = new Intent(this, owner_add_product.class);//send to this class
      //  intent.putExtra("store_owner", owner);
        startActivity(intent);
    }

    public void seePage(View view){
        //send us to the store page!
    }
}