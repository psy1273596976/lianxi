package com.http.myshop.adapter.home.cat;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;


import com.http.myshop.R;
import com.http.myshop.base.BaseAdapter;
import com.http.myshop.model.home.bean.CategoryBottomInfoBean;
import com.http.myshop.utils.ImageLoaderUtils;
import com.http.myshop.utils.TxtUtils;

import java.util.List;

public class CategoryButtomInfoAdapter extends BaseAdapter {

    public CategoryButtomInfoAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_category_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        CategoryBottomInfoBean.DataBean.GoodsListBean bean= (CategoryBottomInfoBean.DataBean.GoodsListBean) data;

        ImageView image = (ImageView) vh.getViewById(R.id.iv_category_img);
        TextView name = (TextView) vh.getViewById(R.id.tv_category_name);
        TextView floor_price = (TextView) vh.getViewById(R.id.tv_category_price);

        ImageLoaderUtils.loadImage(bean.getList_pic_url(),image);
        TxtUtils.setTextView(name,bean.getName());
        floor_price.setText("￥"+bean.getRetail_price());
    }
}
