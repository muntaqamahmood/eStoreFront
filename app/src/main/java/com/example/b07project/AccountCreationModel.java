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
        if(!isCustomer){
            DatabaseReference ref = database.getReference("store owners");
            ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {  //Problem, this gets called after the
                    if(!task.isSuccessful()){                               //return of usernameAlreadyExists
                        //error getting data
                    }else{
                        for(DataSnapshot child:task.getResult().getChildren()){  //getChildren could be null
                            StoreOwner storeOwner = child.getValue(StoreOwner.class);
                            if(storeOwner.getUsername().equals(username)){
                                presenter.doNotMakeAccount();
                                return;
                            }
                        }
                        presenter.makeAccount(username, password, false);
                    }
                }
            });

        }else{
            //customer version
        }

    }

    public AccountCreationModel(){
    }
    public void setPresenter(AccountCreationContract.Presenter presenter){this.presenter = presenter;}
}
