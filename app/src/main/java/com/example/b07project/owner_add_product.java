package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class owner_add_product extends AppCompatActivity implements OwnerAddProductContract.OwnerAddProductView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_add_product);
    }

    AddProductModel model = new AddProductModel();
    OwnerAddProductPresenter presenter = new OwnerAddProductPresenter(model, this);

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

    @Override
    public void displayMessage(String msg){
        TextView textView = findViewById(R.id.txtAPStatus);
        textView.setText(msg);
    }

    public void confirmButton(View view){
        presenter.checkProductInputs(getProductName(),getBrandName(),getPrice());
    }
}