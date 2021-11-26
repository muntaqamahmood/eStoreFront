package com.example.b07project;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountCreationPresenter implements AccountCreationContract.Presenter{

    private AccountCreationContract.View view;
    private AccountCreationContract.Model model;

    @Override
    public void checkValidAccount(boolean isCustomer) {
        boolean valid = true;
        String username = view.getUsername();
        String password = view.getPassword();
        String message = "";

        Pattern userPattern = Pattern.compile("\\w{3,}");
        Matcher userMatcher = userPattern.matcher(username);
        Pattern passwordPattern = Pattern.compile("\\w*[A-Z]+\\w*");
        Matcher passwordMatcher = passwordPattern.matcher(password);

        if(!userMatcher.matches()) {
            message += "Username invalid, must contain at least 3 letters, numbers, or underscores. ";
            valid = false;
        }
        if(!passwordMatcher.matches()) {
            message += "Password invalid, must contain at least 1 uppercase letter.";
            valid = false;
        }
        if(!valid) {
            view.result(false, message);
            return;
        }

        model.usernameAlreadyExists(username, password, isCustomer);
    }

    @Override
    public void makeAccount(String username, String password, boolean isCustomer){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        if(!isCustomer){
            StoreOwner storeOwner = new StoreOwner();
            storeOwner.setUsername(username);
            storeOwner.setPassword(password);
            ref.child("store owners").child(username).setValue(storeOwner);
        }else{
            //customer stuff
        }
        view.result(true,"");

    }

    @Override
    public void doNotMakeAccount(){
        view.result(false, "Username already exists");
    }

    public AccountCreationPresenter(AccountCreationContract.View view, AccountCreationContract.Model model){
        this.view = view;
        this.model = model;
    }
}
