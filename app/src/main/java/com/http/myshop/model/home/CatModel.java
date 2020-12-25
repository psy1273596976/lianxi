package com.http.myshop.model.home;

import com.http.myshop.base.BaseModel;
import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.shop.home.ICat;
import com.http.myshop.model.home.bean.CategoryBean;
import com.http.myshop.model.home.bean.CategoryBottomInfoBean;
import com.http.myshop.model.shop.bean.AddCarBean;
import com.http.myshop.net.CommonSubscriber;
import com.http.myshop.net.HttpManager;
import com.http.myshop.utils.RxUtils;

import java.util.HashMap;
import java.util.Map;

public class CatModel extends BaseModel implements ICat.Model {

    @Override
    public void getCarList(String id, CallBack callBack) {
        addDisposible(
                HttpManager.getInstance().shopApi().getCar(id)
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<CategoryBean>(callBack) {
                            @Override
                            public void onNext(CategoryBean categoryBean) {
                                callBack.success(categoryBean);
                            }
                        })
        );
    }

    @Override
    public void getCarBottomList(String id, CallBack callBack) {
        addDisposible(
                HttpManager.getInstance().shopApi().getCategoryBottomInfo(id)
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<CategoryBottomInfoBean>(callBack) {
                            @Override
                            public void onNext(CategoryBottomInfoBean categoryBottomInfoBean) {
                                callBack.success(categoryBottomInfoBean);
                            }
                        })
        );
    }

    //添加进购物车
    @Override
    public void addGoodCar(HashMap<String, String> map, CallBack callback) {
        addDisposible(
                HttpManager.getInstance().shopApi().addCar(map)
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<AddCarBean>(callback) {
                            @Override
                            public void onNext(AddCarBean addCarBean) {
                                callback.success(addCarBean);
                            }
                        })
        );
    }
}
