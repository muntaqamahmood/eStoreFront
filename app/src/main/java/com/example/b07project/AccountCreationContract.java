package com.example.b07project;

public interface AccountCreationContract {

    public interface Model{

    }

    public interface Presenter{
        public void checkValidAccount(String username, String password);
    }

    public interface View{
        public String getUsername();
        public String getPassword();
    }

}
