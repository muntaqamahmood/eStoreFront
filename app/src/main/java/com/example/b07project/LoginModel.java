package com.example.b07project;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginModel implements LoginContract.Model{

    LoginContract.Presenter presenter;

    @Override
    public void correctCredentials(String username, String password, boolean isCustomer) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        if(!isCustomer){
            DatabaseReference ref = database.getReference("store owners");
            ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(!task.isSuccessful()){
                        //error getting data
                    }else{
                        if(task.getResult().hasChild(username)){
                            StoreOwner s = task.getResult().child(username).getValue(StoreOwner.class);

                            StoreOwner storeOwner = new StoreOwner();
                            storeOwner.setUsername(username);
                            storeOwner.setPassword(password);

                            if(storeOwner.password.equals(s.password) && storeOwner.username.equals(s.username)) {
                                presenter.login(s);
                            }else{
                                presenter.doNotLogin();
                            }
                        }else{
                            presenter.doNotLogin();
                        }
                    }
                }
            });
        }
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public LoginModel(){};
}
