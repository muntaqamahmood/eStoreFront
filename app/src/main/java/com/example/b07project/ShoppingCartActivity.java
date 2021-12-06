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

        //looping through arraylist of order and adding using an adapter
        if(order.items != null) {
            for (Product p : order.items) {
                productsAdapter.add(p);
            }
        }
        // back button takes customer back to landing page onClick
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
        // order button places an order onClick
        orderBtn = (Button)findViewById(R.id.orderButton);
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when cart is not empty
                if(!order.items.isEmpty()) {
                    backBtn.setVisibility(View.VISIBLE);
                    orderBtn.setVisibility(View.INVISIBLE);
                    Context context = getApplicationContext();
                    Toast.makeText(context, "Order Placed!", Toast.LENGTH_LONG).show();
                    //assign an orderNumber
                    order.orderNumber = order.fetchOrderCount();
                    //write order to Firebase for both customers and store owners
                    customer.populateAddWriteOrders(order);
                    storeOwner.populateAddWriteOrders(order);
                }else{
                    //when cart is empty say this
                    Context context = getApplicationContext();
                    Toast.makeText(context, "Shopping Cart cannot be empty.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        setUpViewListener(productsAdapter);

    }
    //removing a product from the Cart with a LongClick
    public void setUpViewListener(ArrayAdapter<Product> productsAdapter){
        lstView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            //on LongClick item is removed
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //locate product position
                Product productToRemove = (Product) lstView.getItemAtPosition(position);
                //removes the product
                order.deleteProduct(productToRemove);
                productsAdapter.remove(productToRemove);
                //shows feedback
                feedback("Removed " + productToRemove.toString());
                return false;
            }
        });
    }
    //feedback message
    public void feedback(String msg){
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }
}