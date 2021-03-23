package com.example.appthuongmai.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appthuongmai.R;
import com.example.appthuongmai.model.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    public Context context;
    private int layout;
    private List<Product> listProduct;

    public ProductAdapter(Context context, int layout, List<Product> listProduct) {
        this.context = context;
        this.layout = layout;
        this.listProduct = listProduct;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layout, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product p = listProduct.get(position);
        if(p.getAvatar().isEmpty()){
            holder.imgProductAvatar.setImageResource(R.drawable.giphy);
//            holder.imgProductAvatar.
        }else{
            Picasso.get().load(p.getAvatar())
                    .into(holder.imgProductAvatar);
        }

        holder.txtvProductName.setText(p.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtvProductPrice.setText("Giá: "+decimalFormat.format(p.getPrice())+" $");


    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProductAvatar;
        TextView txtvProductName, txtvProductPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProductAvatar = itemView.findViewById(R.id.imgProductAvatar);
            txtvProductName = itemView.findViewById(R.id.txtvProductName);
            txtvProductPrice = itemView.findViewById(R.id.txtvProductPrice);
        }
    }
}
