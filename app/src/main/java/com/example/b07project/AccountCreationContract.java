package com.example.b07project;

public interface AccountCreationContract {

    public interface Model{
        public void usernameAlreadyExists(String username, String password, boolean isCustomer);
        public void setPresenter(AccountCreationContract.Presenter presenter);
    }

    public interface Presenter{
        public void checkValidAccount(boolean isCustomer);
        public void makeAccount(String username, String password, boolean isCustomer);
        public void doNotMakeAccount();
    }

    public interface View{
        public String getUsername();
        public String getPassword();
        public void result(boolean validAccount, String message);
    }

}
