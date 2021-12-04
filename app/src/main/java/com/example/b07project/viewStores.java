package com.example.b07project;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class viewStores extends AppCompatActivity {

    private ArrayList<String> stores = new ArrayList<String>();
//    private ArrayList<String> str_stores;

    private ListView lstView;





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

         ArrayAdapter<String> storesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
         lstView = findViewById(R.id.listView);
         lstView.setAdapter(storesAdapter);
        //StoreOwner owner = (StoreOwner) getIntent().getSerializableExtra("account");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("store owners");

        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(!task.isSuccessful()){
                    //error getting data
                }else{
                    if (task.getResult().hasChildren()) {
                        for (DataSnapshot child : task.getResult().getChildren()) {
                            StoreOwner storeOwner = child.getValue(StoreOwner.class);
                            if (storeOwner != null) {
                                Log.i("test",storeOwner.getUsername());
                                storesAdapter.add(storeOwner.getUsername());
                                //stores.add(storeOwner.getUsername());

                            }
                        }
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

//        String[] stores_array = stores.toArray(new String[stores.size()]);
//        ArrayList<String> str = new ArrayList<String>();
//        str.add("avik");
//        str.add("shadman");
////        {"avik", "shadman"};

        setUpListViewListener();


    }

    private void setUpListViewListener() {
        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}