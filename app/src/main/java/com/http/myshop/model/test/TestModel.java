package com.http.myshop.model.test;


import com.http.myshop.base.BaseModel;

import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.test.ITest;
import com.http.myshop.net.CommonSubscriber;
import com.http.myshop.net.HttpManager;
import com.http.myshop.utils.RxUtils;

public class TestModel extends BaseModel implements ITest.Model {

    @Override
    public void getList(CallBack callback) {
        addDisposible(HttpManager.getInstance().getService().getList()
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new CommonSubscriber<TestBean>(callback) {
            @Override
            public void onNext(TestBean testBean) {
                callback.success(testBean);
            }
        }));
    }
}
