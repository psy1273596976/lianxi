package com.http.myshop;

import android.content.Intent;

import com.http.myshop.base.BaseActivity;
import com.http.myshop.interfaces.IBasePresenter;

public class SplaceActivity extends BaseActivity {
    @Override
    protected int getLayout() {



        return R.layout.activity_splace;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        final Intent intent = new Intent(SplaceActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
