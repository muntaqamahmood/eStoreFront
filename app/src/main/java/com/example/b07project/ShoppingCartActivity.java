package com.example.b07project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity {
    private Customer customer;
    private StoreOwner storeOwner;
    private CustomerOrder order;
    private ArrayList<Product> products = new ArrayList<Product>();
    private ListView lstView;
    private Button orderBtn;
    private Button backBtn;
    private Button removeBtn;
    //ArrayAdapter<Product> productsAdapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1);

//   private TextView productName, productBrand, productPrice;
//   private String state = "Normal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        customer = (Customer) getIntent().getSerializableExtra("customer");
        storeOwner = (StoreOwner)getIntent().getSerializableExtra("store_owner");
        order = (CustomerOrder) getIntent().getSerializableExtra("order");

        ArrayAdapter<Product> productsAdapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1);
        lstView = findViewById(R.id.cartListView);
        lstView.setAdapter(productsAdapter);
        if(order.items != null) {
            for (Product p : order.items) {
                productsAdapter.add(p);
            }
        }

        backBtn = (Button)findViewById((R.id.btnBackToLanding));
        backBtn.setVisibility(View.INVISIBLE);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(ShoppingCartActivity.this, Customer_Landing.class);
                    intent.putExtra("account", customer);
                    startActivity(intent);
            }
        });

        orderBtn = (Button)findViewById(R.id.orderButton);
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!order.items.isEmpty()) {
                    backBtn.setVisibility(View.VISIBLE);
                    orderBtn.setVisibility(View.INVISIBLE);
                    Context context = getApplicationContext();
                    Toast.makeText(context, "Order Placed!", Toast.LENGTH_LONG).show();
                    customer.populateAddWriteOrders(order);
                    storeOwner.populateAddWriteOrders(order);
                }else{
                    Context context = getApplicationContext();
                    Toast.makeText(context, "Shopping Cart cannot be empty.",Toast.LENGTH_SHORT).show();
                }
            }
        });
//        removeBtn = (Button)findViewById(R.id.removeButton);
//        removeBtn.setOnClickListener(new View.OnClickListener(){
//
//        });
        setUpViewListener(productsAdapter);

    }
    public void setUpViewListener(ArrayAdapter<Product> productsAdapter){
        lstView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Product productToRemove = (Product) lstView.getItemAtPosition(position);
                order.deleteProduct(productToRemove);
                productsAdapter.remove(productToRemove);
                feedback("Removed " + productToRemove.toString());
//                Intent intent = new Intent(ShoppingCartActivity.this, ShoppingCartActivity.class);
//                startActivity(intent);
                return false;
            }
        });
    }
    public void feedback(String msg){
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }

}