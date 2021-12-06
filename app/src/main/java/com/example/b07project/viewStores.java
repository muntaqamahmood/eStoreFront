package com.example.b07project;

import android.content.Intent;
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

import java.util.ArrayList;

public class viewStores extends AppCompatActivity {

    private ArrayList<StoreOwner> stores = new ArrayList<StoreOwner>();
    private ListView lstView;


    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stores);

         ArrayAdapter<String> storesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
         lstView = findViewById(R.id.listView);
         lstView.setAdapter(storesAdapter);

         //reads from firebase and displays the store names on the All Stores page
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
                                stores.add(storeOwner);

                            }
                        }
                    }
                }
            }
        });
        setUpListViewListener(stores);
    }

    private void setUpListViewListener(ArrayList<StoreOwner> s) {
        //when user clicks on a store, takes them to the store
        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OpenCustomerViewStore(s.get(position));
            }
        });
    }

    public void OpenCustomerViewStore(StoreOwner store){
        Intent intent = new Intent(this, CustomerViewStore.class);
        intent.putExtra("store_owner", store);
        Customer customer = (Customer) getIntent().getSerializableExtra("customer") ;
        intent.putExtra("customer",customer);
        startActivity(intent);
    }
}