package com.http.myshop.interfaces.shop.shop;

import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.IBaseModel;
import com.http.myshop.interfaces.IBasePresenter;
import com.http.myshop.interfaces.IBaseView;
import com.http.myshop.model.shop.bean.CarBean;
import com.http.myshop.model.shop.bean.DeleteCarBean;
import com.http.myshop.model.shop.bean.UpdateCarBean;

import java.util.HashMap;
import java.util.Map;

public interface ICar {
    interface View extends IBaseView {
        //购物车列表数据
        void getCarReturn(CarBean carBean);
        //更新 购物车
        void updateCarReturn(UpdateCarBean result);
        //删除购物车
        void deleteCarReturn(DeleteCarBean result);
    }

    interface Presenter extends IBasePresenter<View> {
        //购物车列表
        void getCar();
        //更新购物车的数据
        void  updateCar(HashMap<String,String> map);
        //删除购物车列表
        void deleteCar(String pIds);
    }

    interface Model extends IBaseModel {
        //购物车列表
        void getCarList(CallBack callBack);
        //更新购物车的数据
        void updateCar(HashMap<String,String> map, CallBack callback);
        //删除购物车列表
        void deleteCar(String pIds,CallBack callback);
    }
}
