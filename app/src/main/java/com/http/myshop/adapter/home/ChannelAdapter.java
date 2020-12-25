package com.http.myshop.adapter.home;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.http.myshop.R;
import com.http.myshop.base.BaseAdapter;
import com.http.myshop.model.home.bean.ChannelTypeBean;
import com.http.myshop.utils.TxtUtils;

import java.util.List;

public class ChannelAdapter extends BaseAdapter {

    private Context context;
    public ChannelAdapter(Context context, List data) {
        super(context, data);
        this.context=context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_fragment_channel;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        ChannelTypeBean.DataBeanX.GoodsListBean gooddata= (ChannelTypeBean.DataBeanX.GoodsListBean) data;
        ImageView img = (ImageView) vh.getViewById(R.id.frag_list_pic_url);
        TextView name = (TextView) vh.getViewById(R.id.frag_retail_name);
        TextView price = (TextView) vh.getViewById(R.id.frag_retail_price);

        Glide.with(context).load(gooddata.getList_pic_url()).into(img);
        TxtUtils.setTextView(name,gooddata.getName());
        TxtUtils.setTextView(price,gooddata.getRetail_price());
    }
}
