package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean isCustomer;
    Button customerButton;
    Button storeOwnerButton;
    Button createAccountButton;
    Button loginButton;
    Button backButton;
    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storeOwnerButton = findViewById(R.id.btnStoreOwner);
        customerButton = findViewById(R.id.btnCustomer);
        createAccountButton = findViewById(R.id.btnCreateAccount);
        loginButton = findViewById(R.id.btnLogin);
        welcome = findViewById(R.id.lblWelcome);
        backButton = findViewById(R.id.btnBack);
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

    }

    public void login(View view){

    }

    public void back(View view){
        switchVisibilities();
    }

    public void switchVisibilities(){
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