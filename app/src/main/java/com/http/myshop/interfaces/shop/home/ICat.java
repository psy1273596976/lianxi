package com.http.myshop.interfaces.shop.home;

import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.IBaseModel;
import com.http.myshop.interfaces.IBasePresenter;
import com.http.myshop.interfaces.IBaseView;
import com.http.myshop.model.home.bean.CategoryBean;
import com.http.myshop.model.home.bean.CategoryBottomInfoBean;
import com.http.myshop.model.shop.bean.AddCarBean;

import java.util.HashMap;
import java.util.Map;

public interface ICat {

    interface View extends IBaseView {
        void getCarReturn(CategoryBean categoryBean);
        void getCatBottom(CategoryBottomInfoBean categoryBottomInfoBean);
        //添加进购物车
        void addGoodCarReturn(AddCarBean addCarBean);
    }

    interface Presenter extends IBasePresenter<ICat.View> {
        void getCar(String id);
        void getCarBottom(String id);
        //添加进购物车
        void addGoodCar(HashMap<String,String> map);
    }

    interface Model extends IBaseModel {
        void getCarList(String id,CallBack callBack);
        void getCarBottomList(String id,CallBack callBack);
        //添加进购物车
        void addGoodCar(HashMap<String,String> map,CallBack callback);
    }



}
