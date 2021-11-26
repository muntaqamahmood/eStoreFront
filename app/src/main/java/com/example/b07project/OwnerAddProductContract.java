package com.example.b07project;

public interface OwnerAddProductContract {

    public interface OwnerAddProductModel{
    //firebase stuff should be done here
    }

    public interface OwnerAddProductView{
        public String getProductName();
        public String getBrandName();
        public String getPrice();
        public void displayMessage(String msg);
    }

    public interface OwnerAddProductPresenter{
        public void checkProductInputs(String productName, String brandName, String price);
    }
}
