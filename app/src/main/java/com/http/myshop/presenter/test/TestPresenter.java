package com.http.myshop.presenter.test;

import com.http.myshop.base.BasePresenter;
import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.test.ITest;
import com.http.myshop.model.test.TestBean;
import com.http.myshop.model.test.TestModel;


public class TestPresenter extends BasePresenter<ITest.View> implements ITest.Presenter {

    ITest.Model model;

    public TestPresenter(){
        model = new TestModel();
    }

    @Override
    public void getList() {
        if(mView != null){
            model.getList(new CallBack<TestBean>() {
                @Override
                public void success(TestBean data) {
                    mView.getListReturn(data);
                }

                @Override
                public void fail(String err) {
                    mView.showToast(err);
                }
            });
        }
    }
}
