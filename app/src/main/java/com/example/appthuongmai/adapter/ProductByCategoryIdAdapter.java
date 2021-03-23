package com.example.appthuongmai.adapter;

import android.content.Context;
import android.text.TextUtils;
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

public class ProductByCategoryIdAdapter extends RecyclerView.Adapter<ProductByCategoryIdAdapter.ViewHolder>{

    public Context context;
    private int layout;
    private List<Product> list;

    public ProductByCategoryIdAdapter(Context context, int layout, List<Product> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
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
        Product  p = list.get(position);
        if(p.getAvatar().isEmpty()){
            holder.imgAvatar.setImageResource(R.drawable.giphy);
        }else{
            Picasso.get().load(p.getAvatar()).into(holder.imgAvatar);
        }

        holder.txtvName.setText(p.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtvPrice.setText(decimalFormat.format(p.getPrice()) + " Đ");
        holder.txtvDescription.setMaxLines(2);
        holder.txtvDescription.setEllipsize(TextUtils.TruncateAt.END);
        holder.txtvDescription.setText(p.getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgAvatar;
        TextView txtvName, txtvPrice, txtvDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            txtvName = itemView.findViewById(R.id.txtvName);
            txtvPrice = itemView.findViewById(R.id.txtvPrice);
            txtvDescription = itemView.findViewById(R.id.txtvDescription);
        }
    }
}
