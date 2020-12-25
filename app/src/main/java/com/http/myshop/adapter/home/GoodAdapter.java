package com.http.myshop.adapter.home;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.http.myshop.R;
import com.http.myshop.model.home.bean.HomeBean;

import java.util.List;

public class GoodAdapter extends RecyclerView.Adapter<GoodAdapter.Holder> {
    private Context context;
    private List<HomeBean.DataBean.CategoryListBean> mGood;
    private MyClickList myClickList;

    public void setMyClickList(MyClickList myClickList) {
        this.myClickList = myClickList;
    }

    public interface MyClickList{
        void OnItemClick(int position);
    }

    public GoodAdapter(Context context, List<HomeBean.DataBean.CategoryListBean> mGood) {
        this.context = context;
        this.mGood = mGood;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.layout_newgood_item,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Glide.with(context).load(mGood.get(position).getGoodsList().get(position).getList_pic_url()).into(holder.img);
        holder.name.setText(mGood.get(position).getGoodsList().get(position).getName());
        holder.price.setText("¥"+mGood.get(position).getGoodsList().get(position).getRetail_price());

        int finalPosition = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClickList.OnItemClick(finalPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGood.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView price;
        public Holder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.list_pic_url);
            name = (TextView) itemView.findViewById(R.id.retail_name);
            price = (TextView) itemView.findViewById(R.id.retail_price);
        }
    }
}
