package com.example.myapplication.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewAdapter;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class productAdapter extends RecyclerView.Adapter<productAdapter.productViewHolder> {
    private List<product> productList;

    public productAdapter(List<product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public productViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*return new productViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_container,
                        parent,
                        false
                )*/
        return new productViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull productViewHolder holder, int position) {
        holder.setProductData(productList.get(position));

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class productViewHolder extends RecyclerView.ViewHolder{
        private KenBurnsView kenBurnsView;
        private TextView titleproduct, txtproduct , textstart;
        productViewHolder(@NonNull View itemView) {
            super(itemView);
            kenBurnsView = itemView.findViewById(R.id.kbvproduct);
            titleproduct = itemView.findViewById(R.id.titleproduct);
            txtproduct = itemView.findViewById(R.id.txtproduct);
            textstart = itemView.findViewById(R.id.textstart);
        }
        void setProductData(product product){
            titleproduct.setText(product.title);
            kenBurnsView.setImageResource(product.image);
            //Picasso.get().load(product.imageurl).into(kenBurnsView);
            txtproduct.setText(product.product);
            textstart.setText(String.valueOf(product.star));


        }
    }
}
