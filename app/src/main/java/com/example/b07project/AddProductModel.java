package com.example.b07project;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddProductModel implements OwnerAddProductContract.OwnerAddProductModel{
    //write to the database
    void addProduct(Product p, StoreOwner owner) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child(owner.getUsername()).child("Products").setValue(p);
    }


}
