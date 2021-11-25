package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AccountCreationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation);

    }

    public void createAccount(View view){
        Intent intent = getIntent();
        EditText userText = (EditText)findViewById(R.id.txtUsername);
        EditText passText = (EditText)findViewById(R.id.txtPassword);
        String username = userText.getText().toString();
        String password = passText.getText().toString();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        if(!intent.getBooleanExtra("account_type",true)){  //Store Owner
            StoreOwner storeOwner = new StoreOwner(username, password);
            ref.child("store owners").child(username).setValue(storeOwner);
        }

    }
}