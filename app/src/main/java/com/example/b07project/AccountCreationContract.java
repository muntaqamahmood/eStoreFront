package com.example.b07project;

public interface AccountCreationContract {

    public interface Model{

    }

    public interface Presenter{
        public boolean isValidAccount(String username, String password);
    }

    public interface View{
        public String getUsername();
        public String getPassword();
    }

}
