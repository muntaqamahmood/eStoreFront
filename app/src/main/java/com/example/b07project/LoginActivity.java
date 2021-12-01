package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;

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
    }

    public void checkLogin(View view){
        presenter.checkValidLogin(isCustomer);
    }

    @Override
    public String getUsername() {
        TextView textView = findViewById(R.id.txtUname);
        return textView.getText().toString();
    }

    @Override
    public String getPassword() {
        TextView textView = findViewById(R.id.txtPword);
        return textView.getText().toString();
    }

    @Override
    public void result(Account account) {
        TextView result = findViewById(R.id.lblOutcome);
        result.setText("");
        ///want this in Presenter
        if(account != null){
            StoreOwner accountToLogin;
            Toast toast = Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG);
            toast.show();
            ///want to put intent stuff into Presenter
            if(StoreOwner.class == account.getClass()){
                Intent intent = new Intent(this, OwnerLanding.class);
                accountToLogin = (StoreOwner) account;
                intent.putExtra("account", accountToLogin);
                startActivity(intent);
            }
            else{
                //make it of class Customer
            }
            ///
        }else{
            result.setText("Incorrect username, password, or both");
        }

    }
}