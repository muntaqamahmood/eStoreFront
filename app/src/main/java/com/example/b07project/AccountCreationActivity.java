package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AccountCreationActivity extends AppCompatActivity implements AccountCreationContract.View {

    private AccountCreationContract.Presenter presenter;
    boolean isCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation);

        Intent intent = getIntent();
        isCustomer = intent.getBooleanExtra("account_type",true);
        AccountCreationContract.Model model = new AccountCreationModel();
        presenter = new AccountCreationPresenter(this, model);
        model.setPresenter(presenter);
    }

    @Override
    public String getUsername(){
        EditText userText = findViewById(R.id.txtUsername);
        return userText.getText().toString();
    }

    @Override
    public String getPassword(){
        EditText passText = findViewById(R.id.txtPassword);
        return passText.getText().toString();
    }

    public void createAccount(View view){                       //create account button
        TextView result =findViewById(R.id.lblResult);
        result.setText("");
        presenter.checkValidAccount(isCustomer);
    }

    @Override
    public void result(String message){   //displays result of account creation
        if(message.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(), "account made", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            TextView result = findViewById(R.id.lblResult);
            result.setText(message);
        }
    }
}