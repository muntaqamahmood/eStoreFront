package com.example.b07project;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class ShoppingCartActivity extends AppCompatActivity{
    TextView quantity;
    int totalQuantity = 1;
    int totalPrice = 0;
    ImageView detailedImg,addItem,removeItem;
    TextView price,rating,description;
    Button addToCart;
    Toolbar toolbar;
    //ViewAllModel viewAllModel = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
    }
}