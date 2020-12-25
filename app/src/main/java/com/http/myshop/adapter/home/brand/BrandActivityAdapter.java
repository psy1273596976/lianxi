package com.http.myshop.adapter.home.brand;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.http.myshop.R;
import com.http.myshop.base.BaseAdapter;
import com.http.myshop.model.home.bean.BrandBean;

import java.util.List;

public class BrandActivityAdapter extends BaseAdapter {

    private Context context;
    public BrandActivityAdapter(Context context, List data) {
        super(context, data);
        this.context=context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_brand_item1;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        BrandBean.DataBeanX.DataBean dataBean= (BrandBean.DataBeanX.DataBean) data;
        ImageView img = (ImageView) vh.getViewById(R.id.brand_img);
        TextView title = (TextView) vh.getViewById(R.id.brand_tv_title);
        Glide.with(context).load(dataBean.getApp_list_pic_url()).into(img);
        title.setText(dataBean.getName()+" | "+dataBean.getFloor_price()+"元起");
    }
}
