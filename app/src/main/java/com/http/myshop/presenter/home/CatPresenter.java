package com.http.myshop.presenter.home;

import com.http.myshop.base.BasePresenter;
import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.shop.home.ICat;
import com.http.myshop.model.home.bean.CategoryBean;
import com.http.myshop.model.home.CatModel;
import com.http.myshop.model.home.bean.CategoryBottomInfoBean;
import com.http.myshop.model.shop.bean.AddCarBean;

import java.util.HashMap;
import java.util.Map;


public class CatPresenter extends BasePresenter<ICat.View> implements ICat.Presenter {

    ICat.Model model;
    ICat.View view;

    public CatPresenter(ICat.View view) {
        this.view=view;
        this.model=new CatModel();
    }

    @Override
    public void getCar(String id) {
        if(mView != null) {
            model.getCarList(id, new CallBack() {
                @Override
                public void success(Object data) {
                    view.getCarReturn((CategoryBean) data);
                }

                @Override
                public void fail(String err) {
                    view.showToast(err);
                }
            });

        }
    }

    @Override
    public void getCarBottom(String id) {
        if(mView != null) {
            model.getCarBottomList(id, new CallBack() {
                @Override
                public void success(Object data) {
                    view.getCatBottom((CategoryBottomInfoBean) data);
                }

                @Override
                public void fail(String err) {
                    view.showToast(err);
                }
            });

        }
    }

    @Override
    public void addGoodCar(HashMap<String, String> map) {
        model.addGoodCar(map, new CallBack() {
            @Override
            public void success(Object data) {
                view.addGoodCarReturn((AddCarBean) data);
            }

            @Override
            public void fail(String err) {
                view.showToast(err);
            }
        });
    }
}
