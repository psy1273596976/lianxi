package com.http.myshop.ui.sort;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;
import com.http.myshop.R;
import com.http.myshop.base.BaseActivity;
import com.http.myshop.interfaces.sort.ISort;
import com.http.myshop.model.sort.bean.CatalogBean;
import com.http.myshop.model.sort.bean.CruuentBean;
import com.http.myshop.model.sort.bean.CruuentInfoBean;
import com.http.myshop.presenter.sort.SortPresenter;
import com.http.myshop.utils.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrentInfoActivity extends BaseActivity<ISort.Presenter> implements ISort.View {
    @BindView(R.id.tab_channel)
    TabLayout mTab;
    @BindView(R.id.vp_channel)
    CustomViewPager mVp;
    private String currentid;
    private List<CruuentBean.DataBean.CurrentCategoryBean.SubCategoryListBean> mSubdata;
    private ArrayList<Fragment> fragments;
    private int infoid;
    private String name;

    @Override
    protected int getLayout() {
        return R.layout.activity_channel;
    }

    @Override
    protected ISort.Presenter createPrenter() {
        return new SortPresenter(this);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        currentid = intent.getStringExtra("currentId");
        infoid = intent.getIntExtra("infoid", 0);
        name = intent.getStringExtra("name");
        mSubdata = new ArrayList<>();
        fragments = new ArrayList<>();
    }

    @Override
    protected void initData() {
        presenter.getCurrent(currentid);
    }

    @Override
    public void getCatalogReturn(CatalogBean catalogBean) {}

    @Override
    public void getCurrentReturn(CruuentBean catlogCurrentBean) {
        List<CruuentBean.DataBean.CurrentCategoryBean.SubCategoryListBean> subCategoryList = catlogCurrentBean.getData().getCurrentCategory().getSubCategoryList();
        Log.e("taginfo", "getCurrentReturn: " + subCategoryList.size());
        mSubdata.addAll(subCategoryList);
        for (int i = 0; i < mSubdata.size(); i++) {
            int id = mSubdata.get(i).getId();
            CurrentInfoFragment currentInfoFragment = new CurrentInfoFragment();
            currentInfoFragment.getId(id + "");
            fragments.add(currentInfoFragment);
        }
        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager());
        mVp.setAdapter(vpAdapter);
        mTab.setupWithViewPager(mVp);

        for (int i = 0; i < mSubdata.size(); i++) {
            if (mSubdata.get(i).getName().equals(name)){
                mVp.setCurrentItem(i);
            }
        }
    }

    @Override
    public void getCurrentInfoReturn(CruuentInfoBean cruuentInfoBean) {}

    class VpAdapter extends FragmentStatePagerAdapter {

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
            return mSubdata.get(position).getName();
        }
    }
}
