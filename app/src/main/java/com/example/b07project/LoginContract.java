package com.example.b07project;

import android.content.Context;

public interface LoginContract {

    public interface Model{
        public void correctCredentials(String username, String password, boolean isCustomer);
        public void setPresenter(LoginContract.Presenter presenter);
    }

    public interface Presenter{
        public void checkValidLogin(boolean isCustomer);
        public void login(Account account);
        public void doNotLogin();
    }

    public interface View{
        public String getUsername();
        public String getPassword();
        public void result(String message);
        public void startOwnerLanding(StoreOwner storeOwner);
        public void startCustomerLanding(Customer customer);
    }

}
