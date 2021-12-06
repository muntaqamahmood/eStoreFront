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
        String username = view.getUsername();
        String password = view.getPassword();
        String message = "";

        Pattern userPattern = Pattern.compile("\\w{3,}");
        Matcher userMatcher = userPattern.matcher(username);
        Pattern passwordPattern = Pattern.compile("\\w*[A-Z]+\\w*");
        Matcher passwordMatcher = passwordPattern.matcher(password);

        if(!userMatcher.matches()) {
            message += "Username invalid, must contain at least 3 letters, numbers, or underscores (no spaces).";
        }
        if(!passwordMatcher.matches()) {
            message += "Password invalid, must contain at least 1 uppercase letter.";
        }
        if(message != "") {
            view.result(message);
            return;
        }
        //could check if it is store owner, then make a StoreOwner reference or Customer reference and pass in
        //a Account reference into model
        model.usernameAlreadyExists(username, password, isCustomer);
    }

    @Override
    public void makeAccount(String username, String password, boolean isCustomer){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        if(!isCustomer){                                                      //future: if(account.getClass() == StoreOwner.class()){
            StoreOwner storeOwner = new StoreOwner(username, password);       //future: storeOwner = (StoreOwner)account;
            ref.child("store owners").child(username).setValue(storeOwner);
        }else{
            Customer customer = new Customer(username, password);
            ref.child("customers").child(username).setValue(customer);
        }
        view.result("");



    }

    @Override
    public void doNotMakeAccount(){
        view.result("Username already exists");
    }

    public AccountCreationPresenter(AccountCreationContract.View view, AccountCreationContract.Model model){
        this.view = view;
        this.model = model;


    }
}
