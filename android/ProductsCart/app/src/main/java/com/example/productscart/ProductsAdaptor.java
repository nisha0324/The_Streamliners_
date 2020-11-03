package com.example.productscart;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productscart.databinding.ProductItemBinding;
import com.example.productscart.model.Product;

import java.util.List;

public class ProductsAdaptor extends RecyclerView.Adapter<ProductsAdaptor.ViewHolder> {

    private Context context;
    private List<Product> productList;

    public ProductsAdaptor(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductItemBinding b = ProductItemBinding.inflate(LayoutInflater.from(context),
                                                                         parent,
                                                            false);

        return new ViewHolder(b);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Product product = productList.get(position);

        holder.b.name.setText(String.format("%s (Rs. %d)",product.name,product.price));
        holder.b.quantity.setText(product.quantity + "");

        holder.b.decrementBtn.setVisibility(product.quantity>0 ? View.VISIBLE: View.GONE);
        holder.b.quantity.setVisibility(product.quantity>0 ? View.VISIBLE: View.GONE);

        holder.b.incrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.quantity++ ;
                product.price = product.old_price * product.quantity;
                notifyItemChanged(position);
            }
        });

        holder.b.decrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.quantity--;
                product.price = product.old_price * product.quantity;
                notifyItemChanged(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ProductItemBinding b;

        public ViewHolder( ProductItemBinding b) {
            super(b.getRoot());
            this.b = b;
        }
    }
}
