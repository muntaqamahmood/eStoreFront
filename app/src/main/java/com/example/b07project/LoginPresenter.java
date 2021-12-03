package com.example.b07project;

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
    public void login(Account account) {                                //called if able to login
        view.result("Login Successful");
        if(StoreOwner.class == account.getClass()){
            StoreOwner storeOwner = (StoreOwner) account;
            view.startOwnerLanding(storeOwner);
        }else{
            Customer customer = (Customer) account;
            view.startCustomerLanding(customer);
        }
    }

    @Override
    public void doNotLogin() {                                  // called if unable to login
        view.result("Incorrect username, password, or both");
    }

    public LoginPresenter(LoginContract.View view, LoginContract.Model model){
        this.view = view;
        this.model = model;
    }
}
