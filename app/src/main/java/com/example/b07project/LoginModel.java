package com.example.b07project;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginModel implements LoginContract.Model{
    LoginContract.Presenter presenter;


    //checks if correct credentials matched
    @Override
    public void correctCredentials(String username, String password, boolean isCustomer) {
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
                    //error
                }else{
                    if(task.getResult().hasChild(username)){
                        if(!isCustomer){
                            StoreOwner storeOwner = task.getResult().child(username).getValue(StoreOwner.class);
                            if(password.equals(storeOwner.getPassword()) && username.equals(storeOwner.getUsername())) {
                                presenter.login(storeOwner);
                                return;
                            }
                        }else{
                            Customer customer = task.getResult().child(username).getValue(Customer.class);
                            if(password.equals(customer.getPassword()) && username.equals(customer.getUsername())) {
                                presenter.login(customer);
                                return;
                            }
                        }
                        presenter.doNotLogin();
                    }else{
                        presenter.doNotLogin();
                    }
                }
            }
        });
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public LoginModel(){};
}
