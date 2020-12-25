package com.http.myshop.ui.topic;


import android.content.Intent;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.http.myshop.R;
import com.http.myshop.adapter.topic.TopicAdapter;
import com.http.myshop.base.BaseAdapter;
import com.http.myshop.base.BaseFragment;
import com.http.myshop.interfaces.shop.topic.ITopic;
import com.http.myshop.model.topic.TopicBean;
import com.http.myshop.model.topic.TopicInfoBean;
import com.http.myshop.presenter.topic.TopicPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TopicFragment extends BaseFragment<ITopic.Presenter> implements ITopic.View {
    @BindView(R.id.rlv_topic)
    RecyclerView mRlvtopic;
    @BindView(R.id.btn_top)
    TextView btnTop;
    @BindView(R.id.btn_bottom)
    TextView btnBottom;
    private String page="1";
    private ArrayList<TopicBean.DataBeanX.DataBean> mTopic;
    private TopicAdapter topicAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_topic;
    }

    @Override
    protected ITopic.Presenter createPrenter() {
        return new TopicPresenter(this);
    }

    @Override
    protected void initView() {
        mRlvtopic.setLayoutManager(new LinearLayoutManager(mContext));
        //创建一个集合
        mTopic = new ArrayList<>();
        //创建适配器
        topicAdapter = new TopicAdapter(mContext, mTopic);
        mRlvtopic.setAdapter(topicAdapter);
    }

    @Override
    protected void initData() {
        presenter.getTopic(page,"100");
    }

    @Override
    public void getTopicReturn(TopicBean topicBean) {
        List<TopicBean.DataBeanX.DataBean> data = topicBean.getData().getData();
        mTopic.clear();
        mTopic.addAll(data);
        //刷新适配器
        topicAdapter.notifyDataSetChanged();
        topicAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Intent intent = new Intent(getActivity(), TopicInfoActivity.class);
                intent.putExtra("id",topicBean.getData().getData().get(pos).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void getTopicInfoReturn(TopicInfoBean topicInfoBean) {

    }
}
