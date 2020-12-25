package com.http.myshop.adapter.home.cat.bigpic;

import android.content.Context;
import android.widget.ImageView;


import com.http.myshop.R;
import com.http.myshop.base.BaseAdapter;
import com.http.myshop.utils.ImageLoaderUtils;

import java.util.List;

public class CategoryBigImageAdapter extends BaseAdapter {

    public CategoryBigImageAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_category_bigimage_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        String bean = (String) data;

        ImageView img= (ImageView) vh.getViewById(R.id.iv_bigimage_img);
        ImageLoaderUtils.loadImage(bean,img);

    }
}
