package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    boolean isCustomer;
    LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginContract.Model model = new LoginModel();
        presenter = new LoginPresenter(this, model);
        model.setPresenter(presenter);
        Intent intent = getIntent();
        isCustomer = intent.getBooleanExtra("account_type", true);

        TextView lbl = findViewById(R.id.lblLogin);
        lbl.setText("LOGIN");
    }

    public void checkLogin(View view){                          //enter button
        presenter.checkValidLogin(isCustomer);
    }   //login button

    @Override
    public String getUsername() {
        //return username entered
        TextView textView = findViewById(R.id.txtUname);
        return textView.getText().toString();
    }

    @Override
    public String getPassword() {
        //return password entered
        TextView textView = findViewById(R.id.txtPword);
        return textView.getText().toString();
    }

    @Override
    public void result(String message) {
        //displays the result based on what presenter passed in
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void startOwnerLanding(StoreOwner storeOwner) {
       //start Owner Landing
       Intent intent = new Intent(this, OwnerLanding.class);
       intent.putExtra("account", storeOwner);
       startActivity(intent);
    }

    @Override
    public void startCustomerLanding(Customer customer) {
        //start Customer Landing
        Intent intent = new Intent(this, Customer_Landing.class);
        intent.putExtra("account", customer);
        startActivity(intent);
    }
}