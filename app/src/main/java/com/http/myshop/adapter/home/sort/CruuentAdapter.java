package com.http.myshop.adapter.home.sort;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.http.myshop.R;
import com.http.myshop.base.BaseAdapter;
import com.http.myshop.model.sort.bean.CruuentBean;

import java.util.List;

public class CruuentAdapter extends BaseAdapter {

    public CruuentAdapter(Context context, List data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_cruuetn_adapter;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        CruuentBean.DataBean.CurrentCategoryBean.SubCategoryListBean cruudata= (CruuentBean.DataBean.CurrentCategoryBean.SubCategoryListBean) data;
        ImageView img = (ImageView) vh.getViewById(R.id.img_current_head);
        TextView title = (TextView) vh.getViewById(R.id.tv_current_title);
        Glide.with(context).load(cruudata.getWap_banner_url()).into(img);
        title.setText(cruudata.getName());

    }
}
