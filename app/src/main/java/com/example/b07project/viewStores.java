package com.example.b07project;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class viewStores extends AppCompatActivity {

//    private ArrayList<String> str_stores;
    private ArrayAdapter<String> storesAdapter;
    private ListView listView;
    private ArrayList<String> stores = new ArrayList<>();

//    private void items_to_str(ArrayList<Order> o){
//        str_stores = new ArrayList<String>();
//        for(Order i:o){
//            str_stores.add(i.toString());
//        }
//    }

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stores);

        listView = findViewById(R.id.listView);
       // StoreOwner owner = (StoreOwner) getIntent().getSerializableExtra("account");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("store owners");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(!task.isSuccessful()){
                    //error getting data
                }else{
                    for(DataSnapshot child:task.getResult().getChildren()){
                        StoreOwner storeOwner = child.getValue(StoreOwner.class);
                        stores.add(storeOwner.getUsername());
                    }
//                        if(!isCustomer){
//                            StoreOwner storeOwner = child.getValue(StoreOwner.class);
//                            if(storeOwner.getUsername().equals(username)){
//                                presenter.doNotMakeAccount();
//                                return;
//                            }
//                        }else{
//                            Customer customer = child.getValue(Customer.class);
//                            if(customer.getUsername().equals(username)){
//                                presenter.doNotMakeAccount();
//                                return;
//                            }
//                        }
//
//                    }
//                    presenter.makeAccount(username, password, isCustomer);

                }
            }
        });

        storesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,stores);
        listView.setAdapter(storesAdapter);


    }
}