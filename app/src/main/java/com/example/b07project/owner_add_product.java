package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class owner_add_product extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_add_product);
    }

    public String getProductName() {
        EditText editText = findViewById(R.id.txtProductName);
        return editText.getText().toString();
    }

    public String getBrandName() {
        EditText editText = findViewById(R.id.txtBrandName);
        return editText.getText().toString();
    }

    public String getPrice() {
        EditText editText = findViewById(R.id.txtPrice);
        return editText.getText().toString();
    }

    //will add the product onto the firebase and owner's product field
    public void confirmButton(View view){
        StoreOwner owner = (StoreOwner) getIntent().getSerializableExtra("store_owner");

        String productName = getProductName();
        String brandName = getBrandName();
        String price = getPrice();

        //check if the inputs are valid
        boolean isGood = checkProductInputs(productName,brandName,price);

        if(isGood){
            //create the new product and add it to firebase and owner's products
            Product p = new Product(productName, brandName, Float.parseFloat(price));
            fireBase(owner, p);
            feedback("Product Added!");

            //reset the editText
            EditText txtProduct = findViewById(R.id.txtProductName);
            txtProduct.setText("");
            EditText txtBrand = findViewById(R.id.txtBrandName);
            txtBrand.setText("");
            EditText txtPrice = findViewById(R.id.txtPrice);
            txtPrice.setText("");
        }
    }

    //displays a brief toast message
    public void feedback(String msg){
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    //firebase will add the p onto the firebase under owner's Product child
    public void fireBase(StoreOwner owner, Product p){

        owner.populateAddWriteProducts(p);
        //add the product to the owner's list of products
        //owner.addProduct(p);
        //String username = owner.getUsername();

        //write out the owner's list of products onto the firebase
        //DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        //ref.child("store owners").child(username).child("Products").setValue(owner.products);
    }

    //check if the inputs from the textviews are valid
    public boolean checkProductInputs(String product, String brand, String price){
        if(product.equals("")||brand.equals("")||price.equals("")){
            //one of the entries is empty
            feedback("Entries cannot be empty!");
            return false;
        }
        else if(!isPrice(price)) { //invalid price
            feedback("Please enter a valid price!");
            return false;
        }
        else{
            return true;
        }
    }

    //helper function to check if the input is an acceptable money value
    private boolean isPrice(String str){
        Pattern p = Pattern.compile("[1-9]\\d*.\\d\\d|0.\\d{1,2}|[1-9]\\d*");
        Matcher m = p.matcher(str);
        return m.matches();
    }
}