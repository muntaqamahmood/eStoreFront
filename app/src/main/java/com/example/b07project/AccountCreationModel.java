package com.example.b07project;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AccountCreationModel implements AccountCreationContract.Model{

    AccountCreationContract.Presenter presenter;

    @Override
    public void usernameAlreadyExists(String username, String password, boolean isCustomer) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref;
        if(!isCustomer)
            ref = database.getReference("store owners");
        else
            ref = database.getReference("customers");

        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(!task.isSuccessful()){
                    //error getting data
                }else{
                    for(DataSnapshot child:task.getResult().getChildren()){
                        if(!isCustomer){
                            StoreOwner storeOwner = child.getValue(StoreOwner.class);
                            if(storeOwner.getUsername().equals(username)){
                                presenter.doNotMakeAccount();
                                return;
                            }
                        }else{
                            Customer customer = child.getValue(Customer.class);
                            if(customer.getUsername().equals(username)){
                                presenter.doNotMakeAccount();
                                return;
                            }
                        }
                        presenter.makeAccount(username, password, isCustomer);
                    }

                }
            }
        });
    }

    public AccountCreationModel(){
    }
    public void setPresenter(AccountCreationContract.Presenter presenter){this.presenter = presenter;}
}
