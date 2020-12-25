package com.http.myshop.adapter.home.cat;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.http.myshop.R;
import com.http.myshop.base.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class ImgsAdapter extends BaseAdapter {

    private Context context;

    public ImgsAdapter(Context context, List data) {
        super(context, data);
        this.context=context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_category_imgs;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        String imgdata= (String) data;
        ImageView img = (ImageView) vh.getViewById(R.id.cat_imgs);
        Glide.with(context).load(imgdata).into(img);
    }
}
