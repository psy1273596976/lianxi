package com.http.myshop.ui.test;


import android.util.Log;

import com.http.myshop.R;
import com.http.myshop.base.BaseActivity;
import com.http.myshop.interfaces.test.ITest;
import com.http.myshop.model.test.TestBean;
import com.http.myshop.presenter.test.TestPresenter;

public class TestActivity extends BaseActivity<ITest.Presenter> implements ITest.View {
    @Override
    protected int getLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected TestPresenter createPrenter() {
        return new TestPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        presenter.getList();
    }

    @Override
    public void getListReturn(TestBean result) {
        Log.d("TAG", "getListReturn: "+  result.getData().get(0).getName());
    }
}
