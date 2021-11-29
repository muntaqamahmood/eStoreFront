package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean isCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void userAccount(View view){
        isCustomer = true;
        switchVisibilities();
    }

    public void storeOwnerAccount(View view){
        isCustomer = false;
        switchVisibilities();
    }

    public void createNewAccount(View view){
        Intent intent = new Intent(this, AccountCreationActivity.class);
        intent.putExtra("account_type", isCustomer);
        startActivity(intent);
    }

    public void login(View view){ //link this method to the ownerlanding UI/activity

    }

    public void back(View view){
        switchVisibilities();
    }

    public void switchVisibilities(){
        Button customerButton = findViewById(R.id.btnCustomer);
        Button storeOwnerButton = findViewById(R.id.btnStoreOwner);
        Button createAccountButton = findViewById(R.id.btnCreateAccount);
        Button loginButton = findViewById(R.id.btnLogin);
        Button backButton = findViewById(R.id.btnBack);
        TextView welcome = findViewById(R.id.lblWelcome);

        if(customerButton.getVisibility() == View.VISIBLE){
            customerButton.setVisibility(View.INVISIBLE);
            storeOwnerButton.setVisibility(View.INVISIBLE);
            welcome.setVisibility(View.INVISIBLE);
            createAccountButton.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.VISIBLE);
            backButton.setVisibility(View.VISIBLE);
        }else{
            customerButton.setVisibility(View.VISIBLE);
            storeOwnerButton.setVisibility(View.VISIBLE);
            welcome.setVisibility(View.VISIBLE);
            createAccountButton.setVisibility(View.INVISIBLE);
            loginButton.setVisibility(View.INVISIBLE);
            backButton.setVisibility(View.INVISIBLE);
        }
    }
}