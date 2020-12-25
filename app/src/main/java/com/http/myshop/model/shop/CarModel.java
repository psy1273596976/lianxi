package com.http.myshop.model.shop;

import android.util.Log;

import com.http.myshop.base.BaseModel;
import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.shop.shop.ICar;
import com.http.myshop.model.shop.bean.CarBean;
import com.http.myshop.model.shop.bean.DeleteCarBean;
import com.http.myshop.model.shop.bean.UpdateCarBean;
import com.http.myshop.net.CommonSubscriber;
import com.http.myshop.net.HttpManager;
import com.http.myshop.utils.RxUtils;

import java.util.HashMap;

public class CarModel extends BaseModel implements ICar.Model {

    /*
    * 添加购物车数据
    * */
    @Override
    public void getCarList(CallBack callBack) {
        addDisposible(HttpManager.getInstance().shopApi().getCarList().
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<CarBean>(callBack) {
                    @Override
                    public void onNext(CarBean carBean) {
                        callBack.success(carBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        Log.e("tag", "onError: 添加数据请求失败"+t.getMessage() );
                    }
                }));
    }

    /*
    * 更新购物车数据
    * */
    @Override
    public void updateCar(HashMap<String, String> map, CallBack callback) {
        addDisposible(HttpManager.getInstance().shopApi().updateCar(map).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<UpdateCarBean>(callback) {
                    @Override
                    public void onNext(UpdateCarBean updateCarBean) {
                        callback.success(updateCarBean);
                    }
                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        Log.e("tag", "onError: 更新数据请求失败"+t.getMessage() );
                    }
                }));
    }

    /*
    * 删除购物车数据
    * */
    @Override
    public void deleteCar(String pIds, CallBack callback) {
        addDisposible(HttpManager.getInstance().shopApi().deleteCar(pIds).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<DeleteCarBean>(callback) {
                    @Override
                    public void onNext(DeleteCarBean deleteCarBean) {
                        callback.success(deleteCarBean);
                    }
                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        Log.e("tag", "onError: 删除数据请求失败"+t.getMessage() );
                    }
                }));
    }
}
