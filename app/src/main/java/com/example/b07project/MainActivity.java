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

    public void customerAccount(View view){                 //customer button
        isCustomer = true;
        switchVisibilities();
    }

    public void storeOwnerAccount(View view){               //store owner button
        isCustomer = false;
        switchVisibilities();
    }

    public void createNewAccount(View view){                //opens account creation activity
        Intent intent = new Intent(this, AccountCreationActivity.class);
        intent.putExtra("account_type", isCustomer);
        startActivity(intent);
    }


    public void login(View view){                           //opens login activity
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("account_type", isCustomer);
        startActivity(intent);
    }

    public void back(View view){                            //back button
        switchVisibilities();
    }

    public void switchVisibilities(){                       //switches visibilities of views
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