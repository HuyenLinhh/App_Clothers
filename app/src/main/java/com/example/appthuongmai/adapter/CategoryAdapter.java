package com.example.appthuongmai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appthuongmai.R;
import com.example.appthuongmai.model.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {

    public Context context;
    private int layout;
    private List<Category> list;

    public CategoryAdapter(Context context, int layout, List<Category> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //convertView = inflater.inflate(layout, null);
            convertView = LayoutInflater.from(context).inflate(layout, null);
            viewHolder.imgIcon = convertView.findViewById(R.id.imgIcon);
            viewHolder.txtvName = convertView.findViewById(R.id.txtvName);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Category cate = (Category) getItem(position);
        viewHolder.txtvName.setText(cate.getName());
        Picasso.get().load(cate.getAvatar())
                .placeholder(R.drawable.giphy)
                .error(R.drawable.noimage)
                .into(viewHolder.imgIcon);
        return convertView;
    }

    class ViewHolder{
        ImageView imgIcon;
        TextView txtvName;
    }
}
