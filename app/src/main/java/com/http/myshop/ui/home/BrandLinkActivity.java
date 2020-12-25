package com.http.myshop.ui.home;

import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.http.myshop.R;
import com.http.myshop.adapter.home.brand.BrandGoodAdapter;
import com.http.myshop.base.BaseActivity;
import com.http.myshop.interfaces.shop.home.IBrand;
import com.http.myshop.model.home.bean.BrandBean;
import com.http.myshop.model.home.bean.BrandGoodBean;
import com.http.myshop.model.home.bean.BrandGoodsBean;
import com.http.myshop.presenter.home.BrandPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BrandLinkActivity extends BaseActivity<IBrand.Presenter> implements IBrand.View {
    @BindView(R.id.brand_good_img)
    ImageView brandGoodImg;
    @BindView(R.id.brand_good_name)
    TextView brandGoodName;
    @BindView(R.id.c)
    ConstraintLayout c;
    @BindView(R.id.rlv_brand_good)
    RecyclerView rlvBrandGood;
    @BindView(R.id.brand_good_desc)
    TextView brandGoodDesc;
    private int id;
    private ArrayList<BrandGoodsBean.DataBeanX.GoodsListBean> mBrandGoods;
    private BrandGoodAdapter brandGoodAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_brandlink;
    }

    @Override
    protected IBrand.Presenter createPrenter() {
        return new BrandPresenter(this);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        mBrandGoods = new ArrayList<>();
        rlvBrandGood.setLayoutManager(new GridLayoutManager(this, 2));
        //创建适配器
        brandGoodAdapter = new BrandGoodAdapter(this, mBrandGoods);
        rlvBrandGood.setAdapter(brandGoodAdapter);
    }

    @Override
    protected void initData() {
        presenter.getBrandGood(id + "");
        presenter.getBrandGoods(id + "", "1", "1000");
    }

    @Override
    public void getBrandReturn(BrandBean brandBean) {

    }

    @Override
    public void getBrandGoodReturn(BrandGoodBean brandGoodBean) {
        int idi = brandGoodBean.getData().getBrand().getId();
        Log.e("tag", "getBrandGoodReturn: " + idi);

        String img = brandGoodBean.getData().getBrand().getApp_list_pic_url();
        String name = brandGoodBean.getData().getBrand().getName();
        String desc = brandGoodBean.getData().getBrand().getSimple_desc();
        Glide.with(this).load(img).into(brandGoodImg);
        brandGoodName.setText(name);
        brandGoodDesc.setText(desc);
    }

    @Override
    public void getBrandGoodsReturn(BrandGoodsBean brandGoodsBean) {
        int size = brandGoodsBean.getData().getGoodsList().size();
        Log.e("tag", "getBrandGoodsReturn: " + size);
        mBrandGoods.clear();
        List<BrandGoodsBean.DataBeanX.GoodsListBean> goodsList = brandGoodsBean.getData().getGoodsList();
        mBrandGoods.addAll(goodsList);
        brandGoodAdapter.notifyDataSetChanged();
    }

}
