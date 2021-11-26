package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class owner_add_product extends AppCompatActivity implements OwnerAddProductContract.OwnerAddProductView{

    OwnerAddProductContract.OwnerAddProductPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_add_product);
    }

    @Override
    public String getProductName() {
        EditText editText = findViewById(R.id.txtProductName);
        return editText.getText().toString();
    }

    @Override
    public String getBrandName() {
        EditText editText = findViewById(R.id.txtBrandName);
        return editText.getText().toString();
    }

    @Override
    public String getPrice() { //will need error testing
        EditText editText = findViewById(R.id.txtPrice);
        return editText.getText().toString();
    }

    
}