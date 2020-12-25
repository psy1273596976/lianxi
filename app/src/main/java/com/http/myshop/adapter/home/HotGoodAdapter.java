package com.http.myshop.adapter.home;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.http.myshop.R;
import com.http.myshop.base.BaseAdapter;
import com.http.myshop.model.home.bean.HomeBean;
import com.http.myshop.utils.TxtUtils;

import java.util.List;

public class HotGoodAdapter extends BaseAdapter {
    private Context context;
    public HotGoodAdapter(Context context, List data) {
        super(context, data);
        this.context=context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_hotgood_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        HomeBean.DataBean.HotGoodsListBean hotgood= (HomeBean.DataBean.HotGoodsListBean) data;
        ImageView imgHotGood = (ImageView) vh.getViewById(R.id.img_hotgood);
        Glide.with(context).load(hotgood.getList_pic_url()).into(imgHotGood);
        TextView txtName = (TextView) vh.getViewById(R.id.txt_hotgood_name);
        TxtUtils.setTextView(txtName,hotgood.getName());
        TextView txtDes = (TextView) vh.getViewById(R.id.txt_hotgood_des);
        TxtUtils.setTextView(txtDes,hotgood.getGoods_brief());
        String price = "￥"+hotgood.getRetail_price();
        TextView txtPrice = (TextView) vh.getViewById(R.id.txt_hotgood_price);
        TxtUtils.setTextView(txtPrice,price);
    }
}
