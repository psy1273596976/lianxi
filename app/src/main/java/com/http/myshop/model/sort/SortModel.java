package com.http.myshop.model.sort;

import android.util.Log;

import com.http.myshop.base.BaseModel;
import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.sort.ISort;
import com.http.myshop.model.sort.bean.CatalogBean;
import com.http.myshop.model.sort.bean.CruuentBean;
import com.http.myshop.model.sort.bean.CruuentInfoBean;
import com.http.myshop.net.CommonSubscriber;
import com.http.myshop.net.HttpManager;
import com.http.myshop.utils.RxUtils;

public class SortModel extends BaseModel implements ISort.Model{
    @Override
    public void getCatalogList(CallBack callBack) {
        addDisposible(
                HttpManager.getInstance().shopApi().getCatlog()
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<CatalogBean>(callBack) {
                            @Override
                            public void onNext(CatalogBean catlogBean) {
                                callBack.success(catlogBean);
                            }
                        })
        );
    }

    @Override
    public void getCurrentList(String id, CallBack callBack) {
        addDisposible(
                HttpManager.getInstance().shopApi().getCurrent(id)
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<CruuentBean>(callBack) {
                            @Override
                            public void onNext(CruuentBean catlogCurrentBean) {
                                callBack.success(catlogCurrentBean);
                            }

                            @Override
                            public void onError(Throwable t) {
                                super.onError(t);
                                Log.e("initfocur", "onError:Current数据请求失败"+t.getMessage());
                            }
                        })
        );
    }

    @Override
    public void getCurrentInfoList(String id, String page, String size, CallBack callBack) {
        addDisposible(
                HttpManager.getInstance().shopApi().getCruuentInfo(id,page,size)
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<CruuentInfoBean>(callBack) {
                            @Override
                            public void onNext(CruuentInfoBean cruuentInfoBean) {
                                callBack.success(cruuentInfoBean);
                            }

                            @Override
                            public void onError(Throwable t) {
                                super.onError(t);
                                Log.e("initfo", "onError:Currentinfo数据请求失败"+t.getMessage());
                            }
                        })
        );
    }
}
