package com.http.myshop.model.home;

import android.util.Log;

import com.http.myshop.base.BaseModel;
import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.shop.home.INewGood;
import com.http.myshop.model.home.bean.NewGoodHotBean;
import com.http.myshop.model.home.bean.NewGroodBean;
import com.http.myshop.net.CommonSubscriber;
import com.http.myshop.net.HttpManager;
import com.http.myshop.utils.RxUtils;

import java.util.HashMap;

public class NewGoodModel extends BaseModel implements INewGood.Model {
    @Override
    public void getNewGoodList(HashMap<String, String> map, CallBack callBack) {
        addDisposible(
                HttpManager.getInstance().shopApi().getNewGood(map)
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<NewGroodBean>(callBack) {
                            @Override
                            public void onNext(NewGroodBean newGroodBean) {
                                callBack.success(newGroodBean);
                                Log.e("tag", "onNext: NewGood请求成功" );
                            }

                            @Override
                            public void onError(Throwable t) {
                                super.onError(t);
                                Log.e("tag", "onError: NewGood请求失败" +t.getMessage());
                            }
                        })
        );
    }

    @Override
    public void getNewGoodHotList(CallBack callBack) {
        addDisposible(
                HttpManager.getInstance().shopApi().getNewGoodHot()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<NewGoodHotBean>(callBack) {
                            @Override
                            public void onNext(NewGoodHotBean newGoodHotBean) {
                                callBack.success(newGoodHotBean);
                            }
                        })
        );
    }
}
