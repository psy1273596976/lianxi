package com.http.myshop.ui.sort;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.http.myshop.R;
import com.http.myshop.base.BaseFragment;
import com.http.myshop.interfaces.sort.ISort;
import com.http.myshop.model.sort.bean.CatalogBean;
import com.http.myshop.model.sort.bean.CruuentBean;
import com.http.myshop.model.sort.bean.CruuentInfoBean;
import com.http.myshop.presenter.sort.SortPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;

public class SortFragment extends BaseFragment<ISort.Presenter> implements ISort.View {

    @BindView(R.id.sort_tab)
    VerticalTabLayout mTab;
    @BindView(R.id.sort_vp)
    ViewPager mVp;

    private List<CurrentFragment> fragments;
    private ArrayList<CatalogBean.DataBean.CategoryListBean> mCatalog;

    @Override
    protected int getLayout() {
        return R.layout.fragment_sort;
    }

    @Override
    protected ISort.Presenter createPrenter() {
        return new SortPresenter(this);
    }

    @Override
    protected void initView() {
        mCatalog=new ArrayList<>();
        fragments=new ArrayList<>();
    }

    @Override
    protected void initData() {

        presenter.getCatalog();
    }


    @Override
    public void getCatalogReturn(CatalogBean catalogBean) {
        List<CatalogBean.DataBean.CategoryListBean> data=catalogBean.getData().getCategoryList();
        mCatalog.addAll(data);
        for (int i = 0; i < mCatalog.size(); i++) {
            int id = mCatalog.get(i).getId();
            CurrentFragment currentFragment = new CurrentFragment();
            currentFragment.getId(id+"");
            fragments.add(currentFragment);
        }

        VpAdapter vpAdapter = new VpAdapter(getChildFragmentManager());
        mVp.setAdapter(vpAdapter);
        mTab.setupWithViewPager(mVp);
    }

    @Override
    public void getCurrentReturn(CruuentBean catlogCurrentBean) {
    }

    @Override
    public void getCurrentInfoReturn(CruuentInfoBean cruuentInfoBean) {

    }


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
            return mCatalog.get(position).getName();
        }
    }
}