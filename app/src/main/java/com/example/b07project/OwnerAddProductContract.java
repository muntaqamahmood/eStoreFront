package com.example.b07project;

public interface OwnerAddProductContract {

    public interface OwnerAddProductModel{

    }

    public interface OwnerAddProductView{
        public String getProductName();
        public String getBrandName();
        public String getPrice();
    }

    public interface OwnerAddProductPresenter{
        public void checkProductInputs(String productName, String brandName, String price);
    }
}
