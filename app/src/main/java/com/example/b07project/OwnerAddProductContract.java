package com.example.b07project;

public interface OwnerAddProductContract {

    public interface OwnerAddProductModel{
        public void addProduct(Product p, StoreOwner owner);
    }

    public interface OwnerAddProductView{
        public String getProductName();
        public String getBrandName();
        public String getPrice();

        public void feedback(String s);
    }

    public interface OwnerAddProductPresenter{
        public void checkProductInputs(String productName, String brandName, String price, StoreOwner owner);
    }
}
