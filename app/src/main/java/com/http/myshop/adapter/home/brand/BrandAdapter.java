package com.http.myshop.adapter.home.brand;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.http.myshop.R;
import com.http.myshop.interfaces.MyClickItem;
import com.http.myshop.model.home.bean.HomeBean;

import java.util.ArrayList;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.Holder> {
    private ArrayList<HomeBean.DataBean.BrandListBean> mBrand;
    private Context context;
    private MyClickItem myClickItem;

    public void setMyClickItem(MyClickItem myClickItem) {
        this.myClickItem = myClickItem;
    }

    public BrandAdapter(ArrayList<HomeBean.DataBean.BrandListBean> mBrand, Context context) {
        this.mBrand = mBrand;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.layout_brand_item,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.name.setText(mBrand.get(position).getName());
        holder.price.setText(mBrand.get(position).getFloor_price()+"元起");
        Glide.with(context).load(mBrand.get(position).getNew_pic_url()).into(holder.new_pic_url);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClickItem.ItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBrand.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView new_pic_url;
        TextView name;
        TextView price;
        public Holder(@NonNull View itemView) {
            super(itemView);
            new_pic_url=itemView.findViewById(R.id.img_new_pic_url);
            name=itemView.findViewById(R.id.tv_name);
            price=itemView.findViewById(R.id.tv_price);
        }
    }
}
