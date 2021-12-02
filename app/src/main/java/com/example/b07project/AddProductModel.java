package com.example.b07project;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddProductModel implements OwnerAddProductContract.OwnerAddProductModel{
    //write the product to the database under the owner
    public void addProduct(Product p, StoreOwner owner) {
        //owner.addProduct(p);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("store owners").child(owner.getUsername()).child("Products").setValue(p);
    }


}
