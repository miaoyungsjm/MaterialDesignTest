package com.example.materialtest.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.materialtest.R;
import com.example.materialtest.bean.Fruit;

import java.util.List;

/**
 * Created by ggz on 2018/1/14.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context mContext;

    private List<Fruit> mList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView item_image;
        TextView item_text;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            item_image = (ImageView) view.findViewById(R.id.item_image);
            item_text = (TextView) view.findViewById(R.id.item_text);
        }
    }

    public MyAdapter(List<Fruit> list) {
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = mList.get(position);
        holder.item_text.setText(fruit.getName());
//        holder.item_image.setImageResource(fruit.getImageId());
        // 上面的加载模式卡的不行
        Glide.with(mContext).load(fruit.getImageId()).into(holder.item_image);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}















