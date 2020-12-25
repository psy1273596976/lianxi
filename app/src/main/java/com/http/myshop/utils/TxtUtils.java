package com.http.myshop.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.photoview.PhotoView;

public class TxtUtils {

    public static void setTextView(TextView textView,String word){
        if(textView != null && !TextUtils.isEmpty(word)){
            textView.setText(word);
        }
    }


    //手势
    public static void setPhotoView(Context context, PhotoView photoView, String image) {
        if (photoView != null && !TextUtils.isEmpty(image)) {
            Glide.with(context).load(image)
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                    .into(photoView);
        }
    }

    public static void setImageView(Context context, ImageView imageView, String image) {
        if (imageView != null) {
            Glide.with(context).load(image).into(imageView);
        }
    }

}
