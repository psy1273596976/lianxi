package com.http.myshop.ui.home;

import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.http.myshop.R;
import com.http.myshop.adapter.home.ChannelAdapter;
import com.http.myshop.base.BaseFragment;
import com.http.myshop.interfaces.shop.home.IChannel;
import com.http.myshop.model.home.bean.ChannelTypeBean;
import com.http.myshop.model.home.bean.ChannlBean;
import com.http.myshop.presenter.home.ChannelPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChannelFragment extends BaseFragment<IChannel.Presenter> implements IChannel.View {

    @BindView(R.id.mRlv_channelType)
    RecyclerView mRlv;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_front_name)
    TextView tvFrontName;
    private String id;
    private String front_name;
    private String name;
    List<ChannelTypeBean.DataBeanX.GoodsListBean> list;
    private ChannelAdapter channelAdapter;


    @Override
    protected int getLayout() {
        return R.layout.fragment_channel;
    }

    @Override
    protected IChannel.Presenter createPrenter() {
        return new ChannelPresenter(this);
    }

    @Override
    protected void initView() {
        mRlv.setLayoutManager(new GridLayoutManager(mContext, 2));
        //创建空集合
        list = new ArrayList<>();
        //创建适配器
        channelAdapter = new ChannelAdapter(mContext, list);
        //设置适配器
        mRlv.setAdapter(channelAdapter);

        tvName.setText(name);
        tvFrontName.setText(front_name);
    }

    @Override
    protected void initData() {
        presenter.getChannelType(id);
    }

    public void getId(String id, String name, String front_name) {
        this.id = id;
        this.name = name;
        this.front_name = front_name;
    }

    @Override
    public void getChannel(ChannlBean channlBean) {

    }

    @Override
    public void getChannelType(ChannelTypeBean channelTypeBean) {
        List<ChannelTypeBean.DataBeanX.GoodsListBean> goodsList = channelTypeBean.getData().getGoodsList();
        list.addAll(goodsList);
        channelAdapter.notifyDataSetChanged();
    }
}
