package com.http.myshop.adapter.home.brand;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.http.myshop.R;
import com.http.myshop.base.BaseAdapter;
import com.http.myshop.model.home.bean.BrandGoodsBean;

import java.util.List;

public class BrandGoodAdapter extends BaseAdapter {

    private Context context;

    public BrandGoodAdapter(Context context, List data) {
        super(context, data);
        this.context=context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_newgood_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        BrandGoodsBean.DataBeanX.GoodsListBean goodData= (BrandGoodsBean.DataBeanX.GoodsListBean) data;

        ImageView img = (ImageView) vh.getViewById(R.id.list_pic_url);
        TextView name = (TextView) vh.getViewById(R.id.retail_name);
        TextView price = (TextView) vh.getViewById(R.id.retail_price);
        //赋值
        Glide.with(context).load(goodData.getList_pic_url()).into(img);
        name.setText(goodData.getName());
        price.setText("￥"+goodData.getRetail_price()+"");
    }
}
