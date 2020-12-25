package com.http.myshop.adapter.home.cat.bigpic;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;


import com.http.myshop.utils.TxtUtils;
import com.luck.picture.lib.photoview.PhotoView;

import java.util.ArrayList;

public class BigImageAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<String> list;

    public BigImageAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        PhotoView photoView = new PhotoView(context);
        TxtUtils.setPhotoView(context,photoView,list.get(position));
        container.addView(photoView);//添加进入视图
        return photoView;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
