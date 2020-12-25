package com.http.myshop.presenter.shop;

import com.http.myshop.base.BasePresenter;
import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.shop.shop.ICar;
import com.http.myshop.model.shop.CarModel;
import com.http.myshop.model.shop.bean.CarBean;
import com.http.myshop.model.shop.bean.DeleteCarBean;
import com.http.myshop.model.shop.bean.UpdateCarBean;

import java.util.HashMap;

public class CarPresenter extends BasePresenter<ICar.View> implements ICar.Presenter {
    ICar.Model model;
    ICar.View view;

    public CarPresenter(ICar.View view){
        this.view = view;
        this.model=new CarModel();
    }

    //添加购物车数据
    @Override
    public void getCar() {
        this.model.getCarList(new CallBack() {
            @Override
            public void success(Object data) {
                view.getCarReturn((CarBean) data);
            }

            @Override
            public void fail(String err) {
                view.showToast(err);
            }
        });
    }

    //更新购物车数据
    @Override
    public void updateCar(HashMap<String, String> map) {
        this.model.updateCar(map,new CallBack() {
            @Override
            public void success(Object data) {
                view.updateCarReturn((UpdateCarBean) data);
            }

            @Override
            public void fail(String err) {
                view.showToast(err);
            }
        });
    }

    //删除购物车数据
    @Override
    public void deleteCar(String pIds) {
        this.model.deleteCar(pIds,new CallBack() {
            @Override
            public void success(Object data) {
                view.deleteCarReturn((DeleteCarBean) data);
            }

            @Override
            public void fail(String err) {
                view.showToast(err);
            }
        });
    }
}
