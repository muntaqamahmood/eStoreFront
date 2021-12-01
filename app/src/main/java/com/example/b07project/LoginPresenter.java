package com.example.b07project;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class LoginPresenter implements LoginContract.Presenter{

    LoginContract.View view;
    LoginContract.Model model;
    @Override
    public void checkValidLogin(boolean isCustomer) {
        String username = view.getUsername();
        String password = view.getPassword();
        model.correctCredentials(username, password, isCustomer);
    }

    @Override
    public void login(Account account) {
        //Intent intent = new Intent(, OwnerLanding.class);
        view.result(account);
    }


    @Override
    public void doNotLogin() {
        view.result(null);
    }

    public LoginPresenter(LoginContract.View view, LoginContract.Model model){
        this.view = view;
        this.model = model;
    }
}
