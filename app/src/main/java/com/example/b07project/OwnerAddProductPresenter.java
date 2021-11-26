package com.example.b07project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OwnerAddProductPresenter implements OwnerAddProductContract.OwnerAddProductPresenter{

    OwnerAddProductContract.OwnerAddProductModel model;
    OwnerAddProductContract.OwnerAddProductView view;

    public OwnerAddProductPresenter(OwnerAddProductContract.OwnerAddProductModel model, OwnerAddProductContract.OwnerAddProductView view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void checkProductInputs(String product, String brand, String price){
        if(product.equals("")||brand.equals("")||price.equals("")){
            //one of the entries is empty
            view.displayMessage("Entries cannot be empty!");
        }
        else if(!isPrice(price)) {
            view.displayMessage("Please enter a valid price!");
        }
        else{
            view.displayMessage("Good input!");
            //now, we call the model to put this into firebase
        }
    }

    //helper function
    private boolean isPrice(String str){
        Pattern p = Pattern.compile("[1-9]\\d*.\\d\\d|0.\\d\\d|[1-9]\\d*");
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
