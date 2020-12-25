package com.http.myshop.adapter.home.sort;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.http.myshop.R;
import com.http.myshop.base.BaseAdapter;
import com.http.myshop.model.sort.bean.CruuentInfoBean;

import java.util.List;

public class CurrentInfoAdapter extends BaseAdapter {

    public CurrentInfoAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_newgood_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        CruuentInfoBean.DataBeanX.GoodsListBean goodData= (CruuentInfoBean.DataBeanX.GoodsListBean) data;

        ImageView img = (ImageView) vh.getViewById(R.id.list_pic_url);
        TextView name = (TextView) vh.getViewById(R.id.retail_name);
        TextView price = (TextView) vh.getViewById(R.id.retail_price);
        //赋值
        Glide.with(context).load(goodData.getList_pic_url()).into(img);
        name.setText(goodData.getName());
        price.setText("￥"+goodData.getRetail_price()+"");
    }
}
