package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class owner_add_product extends AppCompatActivity implements OwnerAddProductContract.OwnerAddProductView{

    private OwnerAddProductContract.OwnerAddProductPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_add_product);

        presenter = new OwnerAddProductPresenter(new AddProductModel(), this);
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
    public String getPrice() {
        EditText editText = findViewById(R.id.txtPrice);
        return editText.getText().toString();
    }

    public void confirmButton(View view){
        StoreOwner owner = (StoreOwner) getIntent().getSerializableExtra("store_owner");
        presenter.checkProductInputs(getProductName(),getBrandName(),getPrice(), owner);
    }

    public void feedback(String msg){
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }
}