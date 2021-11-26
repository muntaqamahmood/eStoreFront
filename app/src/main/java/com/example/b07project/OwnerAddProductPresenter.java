package com.example.b07project;

public class OwnerAddProductPresenter implements OwnerAddProductContract.OwnerAddProductPresenter{

    OwnerAddProductContract.OwnerAddProductModel model;
    OwnerAddProductContract.OwnerAddProductView view;

    public OwnerAddProductPresenter(OwnerAddProductContract.OwnerAddProductModel model, OwnerAddProductContract.OwnerAddProductView view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void checkProductInputs(String product, String brand, String price){
        
    }
}
