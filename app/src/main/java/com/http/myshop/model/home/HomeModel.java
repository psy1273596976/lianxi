package com.http.myshop.model.home;

import android.util.Log;

import com.http.myshop.base.BaseModel;
import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.shop.home.IHome;
import com.http.myshop.model.home.bean.HomeBean;
import com.http.myshop.net.CommonSubscriber;
import com.http.myshop.net.HttpManager;
import com.http.myshop.utils.RxUtils;

public class HomeModel extends BaseModel implements IHome.Model {
    @Override
    public void getHomeList(CallBack callBack) {
        addDisposible(HttpManager.getInstance().shopApi().getHome()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HomeBean>(callBack) {
                    @Override
                    public void onNext(HomeBean homeBean) {
                        callBack.success(homeBean);
                        Log.e("tag", "onNext: "+"收到数据" );
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        Log.e("tag", "onError: "+t.getMessage() );
                    }
                }));
    }
}
