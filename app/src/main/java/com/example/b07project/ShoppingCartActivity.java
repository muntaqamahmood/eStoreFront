package com.example.b07project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

//   private TextView productName, productBrand, productPrice;
//   private String state = "Normal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        customer = (Customer) getIntent().getSerializableExtra("customer");
        storeOwner = (StoreOwner)getIntent().getSerializableExtra("store_owner");
        order = (CustomerOrder) getIntent().getSerializableExtra("order");
//        productName = (TextView)findViewById(R.id.txtProductName);
//        productBrand = (TextView)findViewById(R.id.txtBrandName);
//        productPrice = (TextView)findViewById(R.id.txtPrice);
        ArrayAdapter<Product> productsAdapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1);
        lstView = findViewById(R.id.cartListView);
        lstView.setAdapter(productsAdapter);
        if(order.items != null){
            for(Product p: order.items){
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

                backBtn.setVisibility(View.VISIBLE);
                orderBtn.setVisibility(View.INVISIBLE);
                Context context = getApplicationContext();
                Toast.makeText(context,"Order Placed!",Toast.LENGTH_LONG).show();

                customer.populateAddWriteOrders(order);


                storeOwner.populateAddWriteOrders(order);
                Intent intent = new Intent(ShoppingCartActivity.this, Customer_Landing.class);
                intent.putExtra("account", customer);
                startActivity(intent);


//                customer.allOrders.add(order);
//                DatabaseReference reference =  FirebaseDatabase.getInstance().getReference();
//                reference.child("customers").child(order.customer).child("Orders").setValue(customer.allOrders);

//                storeOwner.orders.add(order);
//                DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference();
//                reference2.child("store owners").child(order.storeOwner).child("Orders").setValue(storeOwner.orders);
//


                /*if(state.equals("Order successful!")||state.equals("Order ready for pickup!")){
                    Toast.makeText(ShoppingCartActivity.this, "You can now pick up your order", Toast.LENGTH_LONG).show();
                }
                StoreOwner storeOwner = (StoreOwner)getIntent().getSerializableExtra("account");
                Intent intent = new Intent(ShoppingCartActivity.this, ViewAllOrdersActivity.class);
                intent.putExtra("order", order);
                startActivity(intent);
                finish();*/
            }
        });
    }
   /* private void addedToCart(){
        final DatabaseReference refToCartList = FirebaseDatabase.getInstance().getReference().child("Cart List");
        final HashMap<String, Object>cartMap = new HashMap<>();
        cartMap.put("name", productName.getText().toString());
        cartMap.put("brand", productBrand.getText().toString());
        cartMap.put("price", productPrice.getText().toString());
        //cartMap.put("orderNumber", productOrderNumber);

        refToCartList.child("Customer view").child(customer.getUsername()).child("Products").child(productOrderNumber).updateChildren(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    refToCartList.child("")
                }
            }
        });

    }





    private void getProductDetails(){

    }*/
}