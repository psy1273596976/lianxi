package com.http.myshop.model.home;

import android.util.Log;

import com.http.myshop.base.BaseModel;
import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.shop.home.IBrand;
import com.http.myshop.model.home.bean.BrandBean;
import com.http.myshop.model.home.bean.BrandGoodBean;
import com.http.myshop.model.home.bean.BrandGoodsBean;
import com.http.myshop.net.CommonSubscriber;
import com.http.myshop.net.HttpManager;
import com.http.myshop.utils.RxUtils;

public class BrandModel extends BaseModel implements IBrand.Model {
    @Override
    public void getBrandList(String page, String size, CallBack callBack) {
        addDisposible(
                HttpManager.getInstance().shopApi().getBrand(page,size)
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<BrandBean>(callBack) {
                            @Override
                            public void onNext(BrandBean brandBean) {
                                callBack.success(brandBean);
                            }
                        })
        );
    }

    @Override
    public void getBrandGoodList(String id, CallBack callBack) {
        addDisposible(
                HttpManager.getInstance().shopApi().getBrandGood(id)
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<BrandGoodBean>(callBack) {
                            @Override
                            public void onNext(BrandGoodBean brandGoodBean) {
                                callBack.success(brandGoodBean);
                            }
                        })
        );
    }

    @Override
    public void getBrandGoodsList(String brandId, String page, String size, CallBack callBack) {
        addDisposible(
                HttpManager.getInstance().shopApi().getBrandGoods(brandId,page,size)
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<BrandGoodsBean>(callBack) {
                            @Override
                            public void onNext(BrandGoodsBean brandGoodsBean) {
                                callBack.success(brandGoodsBean);
                            }

                            @Override
                            public void onError(Throwable t) {
                                super.onError(t);
                                Log.e("tagm", "onError: "+t.getMessage()+"请求失败" );
                            }
                        })
        );
    }
}
