package com.http.myshop.ui.home;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.http.myshop.R;
import com.http.myshop.adapter.home.brand.BrandActivityAdapter;
import com.http.myshop.base.BaseActivity;
import com.http.myshop.base.BaseAdapter;
import com.http.myshop.interfaces.shop.home.IBrand;
import com.http.myshop.model.home.bean.BrandBean;
import com.http.myshop.model.home.bean.BrandGoodBean;
import com.http.myshop.model.home.bean.BrandGoodsBean;
import com.http.myshop.presenter.home.BrandPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BrandActivity extends BaseActivity<IBrand.Presenter> implements IBrand.View {
    @BindView(R.id.brand_rlv)
    RecyclerView brandRlv;
    private ArrayList<BrandBean.DataBeanX.DataBean> mBrand;
    private BrandActivityAdapter brandActivityAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_brand;
    }

    @Override
    protected IBrand.Presenter createPrenter() {
        return new BrandPresenter(this);
    }

    @Override
    protected void initView() {
        //创建空集合
        mBrand = new ArrayList<>();
        brandRlv.setLayoutManager(new LinearLayoutManager(this));
        //创建适配器
        brandActivityAdapter = new BrandActivityAdapter(this, mBrand);
        //设置适配器
        brandRlv.setAdapter(brandActivityAdapter);
    }

    @Override
    protected void initData() {
        presenter.getBrand("1", "1000");
    }

    @Override
    public void getBrandReturn(BrandBean brandBean) {
        List<BrandBean.DataBeanX.DataBean> data = brandBean.getData().getData();
        mBrand.clear();
        mBrand.addAll(data);
        //刷新适配器
        brandActivityAdapter.notifyDataSetChanged();


        brandActivityAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Intent intent = new Intent(BrandActivity.this, BrandLinkActivity.class);
                int id = mBrand.get(pos).getId();
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void getBrandGoodReturn(BrandGoodBean brandGoodBean) {

    }

    @Override
    public void getBrandGoodsReturn(BrandGoodsBean brandGoodsBean) {

    }

}
