package com.http.myshop.adapter.topic;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.http.myshop.R;
import com.http.myshop.base.BaseAdapter;
import com.http.myshop.model.topic.TopicBean;
import com.http.myshop.utils.TxtUtils;

import java.util.List;

import retrofit2.http.GET;

public class TopicAdapter extends BaseAdapter {

    private Context context;

    public TopicAdapter(Context context, List data) {
        super(context, data);
        this.context=context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_fragment_topic_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TopicBean.DataBeanX.DataBean topdata= (TopicBean.DataBeanX.DataBean) data;
        ImageView img = (ImageView) vh.getViewById(R.id.topic_img);
        TextView title = (TextView) vh.getViewById(R.id.topic_title);
        TextView subtitle = (TextView) vh.getViewById(R.id.topic_subtitle);
        TextView price = (TextView) vh.getViewById(R.id.topic_price_info);

        Glide.with(context).load(topdata.getScene_pic_url()).into(img);
        TxtUtils.setTextView(title,topdata.getTitle());
        TxtUtils.setTextView(subtitle,topdata.getSubtitle());
        TxtUtils.setTextView(price,topdata.getPrice_info()+"元起");
    }
}
