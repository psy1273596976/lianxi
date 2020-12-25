package com.http.myshop.ui.home;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;
import com.http.myshop.R;
import com.http.myshop.app.MyApp;
import com.http.myshop.base.BaseActivity;
import com.http.myshop.interfaces.shop.home.IChannel;
import com.http.myshop.model.home.bean.ChannelTypeBean;
import com.http.myshop.model.home.bean.ChannlBean;
import com.http.myshop.presenter.home.ChannelPresenter;
import com.http.myshop.utils.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChannelActivity extends BaseActivity<IChannel.Presenter> implements IChannel.View {
    @BindView(R.id.tab_channel)
    TabLayout mTab;
    @BindView(R.id.vp_channel)
    CustomViewPager mVp;

    private String url;
    private List<ChannelFragment> fragments;
    private List<ChannlBean.DataBean.CategoryListBean> list;

    @Override
    protected int getLayout() {
        return R.layout.activity_channel;
    }

    @Override
    protected IChannel.Presenter createPrenter() {
        return new ChannelPresenter(this);
    }


    @Override
    protected void initView() {
        url= (String) MyApp.getMap().get("url");
        fragments=new ArrayList<>();
        list=new ArrayList<>();

        //禁止滑动
        mVp.setScanScroll(false);
    }

    @Override
    protected void initData() {
        presenter.getChannel(url);
       // presenter.getChannelType(url);
    }

    @Override
    public void getChannel(ChannlBean channlBean) {
        List<ChannlBean.DataBean.CategoryListBean> data = channlBean.getData().getCategoryList();
        list.addAll(data);
        for (int i = 0; i < list.size(); i++) {
            int id = list.get(i).getId();
            String name = list.get(i).getName();
            String front_name = list.get(i).getFront_name();
            ChannelFragment channelFragment = new ChannelFragment();
            channelFragment.getId(id+"",name,front_name);
            fragments.add(channelFragment);
        }
        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager());
        mVp.setAdapter(vpAdapter);
        mTab.setupWithViewPager(mVp);

        Intent intent = getIntent();
        String newname = intent.getStringExtra("name");

        for (int i = 0; i < list.size(); i++) {
            if(newname.equals(list.get(i).getName())){
                mVp.setCurrentItem(i);
            }
        }
    }

    @Override
    public void getChannelType(ChannelTypeBean channelTypeBean) {
    }

    class VpAdapter extends FragmentStatePagerAdapter{

        public VpAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position).getName();
        }
    }
}
