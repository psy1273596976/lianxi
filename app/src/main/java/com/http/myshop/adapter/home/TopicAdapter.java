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

public class TopicAdapter extends BaseAdapter {
    private Context context;
    public TopicAdapter(Context context, List data) {
        super(context, data);
        this.context=context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_topic_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        HomeBean.DataBean.TopicListBean topicdata= (HomeBean.DataBean.TopicListBean) data;
        ImageView imgTopic = (ImageView) vh.getViewById(R.id.img_topic);
        Glide.with(context).load(topicdata.getItem_pic_url()).into(imgTopic);
        TextView txtName = (TextView) vh.getViewById(R.id.txt_topic_name);
        String name = topicdata.getTitle();
        TxtUtils.setTextView(txtName,name);
        TextView txtPrice = (TextView) vh.getViewById(R.id.txt_topic_price);
        TxtUtils.setTextView(txtPrice, " ￥"+topicdata.getPrice_info()+"元起");
        TextView txtDes = (TextView) vh.getViewById(R.id.txt_topic_des);
        TxtUtils.setTextView(txtDes,topicdata.getSubtitle());
    }
}
