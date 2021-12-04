package com.example.b07project.CartViewpckg;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.b07project.ProductClick;
import com.example.b07project.R;

public class ViewCart extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtProductName,txtProductPrice,txtProductBrand;
    private ProductClick itemClickListener;

    public ViewCart(View itemView) {
        super(itemView);
        txtProductName = itemView.findViewById(R.id.txtProductName);
        txtProductPrice = itemView.findViewById(R.id.txtPrice);
        txtProductBrand = itemView.findViewById(R.id.txtBrandName);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }

    public void setItemClickListner(ProductClick itemClickListner) {
        this.itemClickListener = itemClickListner;
    }
}