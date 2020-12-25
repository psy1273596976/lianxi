package com.http.myshop.ui.sort;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.http.myshop.R;
import com.http.myshop.adapter.home.sort.CurrentInfoAdapter;
import com.http.myshop.base.BaseFragment;
import com.http.myshop.interfaces.sort.ISort;
import com.http.myshop.model.sort.bean.CatalogBean;
import com.http.myshop.model.sort.bean.CruuentBean;
import com.http.myshop.model.sort.bean.CruuentInfoBean;
import com.http.myshop.presenter.sort.SortPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CurrentInfoFragment extends BaseFragment<ISort.Presenter> implements ISort.View {
    @BindView(R.id.rlv_current)
    RecyclerView rlvCurrent;
    private String id;
    private ArrayList<CruuentInfoBean.DataBeanX.GoodsListBean> mCruuentInfo;
    private CurrentInfoAdapter currentInfoAdapter;

    @Override
    protected int getLayout() {
        return R.layout.layout_fragment_currentinfo;
    }

    @Override
    protected ISort.Presenter createPrenter() {
        return new SortPresenter(this);
    }

    @Override
    protected void initView() {
        mCruuentInfo = new ArrayList<>();
        //创建适配器
        rlvCurrent.setLayoutManager(new GridLayoutManager(mContext,2));
        currentInfoAdapter = new CurrentInfoAdapter(mContext, mCruuentInfo);
        rlvCurrent.setAdapter(currentInfoAdapter);
    }

    @Override
    protected void initData() {
        presenter.getCurrentInfo(id, "1", "100");

    }

    public void getId(String id) {
        this.id = id;
    }

    @Override
    public void getCurrentInfoReturn(CruuentInfoBean cruuentInfoBean) {
        List<CruuentInfoBean.DataBeanX.GoodsListBean> goodsList = cruuentInfoBean.getData().getGoodsList();
        mCruuentInfo.clear();
        mCruuentInfo.addAll(goodsList);
        currentInfoAdapter.notifyDataSetChanged();
    }

    @Override
    public void getCatalogReturn(CatalogBean catalogBean) {}

    @Override
    public void getCurrentReturn(CruuentBean catlogCurrentBean) {}
}
