package com.example.b07project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07project.CartViewpckg.UserCart;
import com.example.b07project.CartViewpckg.ViewCart;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShoppingCartActivity extends AppCompatActivity{
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button NextProcessButton;
    private TextView TotalAmount, Message1;
    private int TotalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        recyclerView = findViewById(R.id.cart_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        NextProcessButton = (Button)findViewById(R.id.next_btn);
        TotalAmount = (TextView)findViewById(R.id.total_price);
        Message1 = (TextView)findViewById(R.id.msg1);
        NextProcessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TotalAmount.setText("Total Price = CAD"+ String.valueOf(TotalPrice));
                Intent intent = new Intent(ShoppingCartActivity.this, OrderConfirmationActivity.class);
                intent.putExtra("Total Price", String.valueOf(TotalPrice));
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        CheckOrderState();
        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");
        FirebaseRecyclerOptions<ShoppingCart> options =
                new FirebaseRecyclerOptions.Builder<ShoppingCart>()
                        .setQuery(cartListRef.child("User view")
                                .child(UserCart.CurrentCustomer.getUsername()).child("Products"),ShoppingCart.class).build();
        FirebaseRecyclerAdapter<ShoppingCart, ViewCart> adapter
                = new FirebaseRecyclerAdapter<ShoppingCart, ViewCart>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewCart holder, int position, @NonNull final ShoppingCart model) {
                holder.txtProductBrand.setText("Brand = "+model.getBrand());
                holder.txtProductPrice.setText("Price = "+model.getPrice()+" Rs.");
                holder.txtProductName.setText(model.getPname());
                int oneTypeProductTPrice = ((Integer.valueOf(model.getPrice())))* Integer.valueOf(model.getBrand());
                TotalPrice += oneTypeProductTPrice;

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CharSequence options[] = new CharSequence[]
                                {
                                        "Edit",
                                        "Remove"
                                };
                        AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCartActivity.this);
                        builder.setTitle("Cart Options: ");
                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (i==0){
                                    Intent intent = new Intent(ShoppingCartActivity.this,viewStores.class);
                                    intent.putExtra("pid", model.getPid());
                                    startActivity(intent);
                                }
                                if (i==1){
                                    cartListRef.child("User view")
                                            .child(UserCart.CurrentCustomer.getUsername())
                                            .child("Products")
                                            .child(model.getPid())
                                            .removeValue()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()){
                                                        Toast.makeText(ShoppingCartActivity.this,"Item removed Successfully.",Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(ShoppingCartActivity.this,ViewAllOrdersActivity.class);
                                                        startActivity(intent);
                                                    }
                                                }
                                            });
                                }
                            }
                        });
                        builder.show();
                    }
                });
            }

            @NonNull
            @Override
            public ViewCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_owner_add_product,parent,false);
                ViewCart holder = new ViewCart(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
    private void CheckOrderState()
    {
        DatabaseReference ordersRef;
        ordersRef = FirebaseDatabase.getInstance().getReference().child("Orders").child(UserCart.CurrentCustomer.getUsername());
        ordersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String shippingState = dataSnapshot.child("state").getValue().toString();
                    String userName = dataSnapshot.child("name").getValue().toString();
                    if (shippingState.equals("Shipped")){
                        TotalAmount.setText("TDear "+userName+"\n your order has been shipped for pick up.");
                        recyclerView.setVisibility(View.GONE);
                        Message1.setVisibility(View.VISIBLE);
                        Message1.setText("Congratulations, Your Final order is ready for pickup.");
                        NextProcessButton.setVisibility(View.GONE);
                        Toast.makeText(ShoppingCartActivity.this,"You can purchase more products.",Toast.LENGTH_SHORT).show();
                    }
                    else if (shippingState.equals("Not Shipped")){
                        TotalAmount.setText("Shipping State = Not Shipped");
                        recyclerView.setVisibility(View.GONE);
                        Message1.setVisibility(View.VISIBLE);

                        NextProcessButton.setVisibility(View.GONE);
                        Toast.makeText(ShoppingCartActivity.this,"You can purchase more products while we get your order ready for pick up.",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}