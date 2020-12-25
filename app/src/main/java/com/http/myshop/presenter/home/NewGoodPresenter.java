package com.http.myshop.presenter.home;

import com.http.myshop.base.BasePresenter;
import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.shop.home.INewGood;
import com.http.myshop.model.home.bean.NewGoodHotBean;
import com.http.myshop.model.home.NewGoodModel;
import com.http.myshop.model.home.bean.NewGroodBean;

import java.util.HashMap;

public class NewGoodPresenter extends BasePresenter<INewGood.View> implements INewGood.Presenter {
    INewGood.Model model;
    INewGood.View view;

    public NewGoodPresenter(INewGood.View view){
        this.model=new NewGoodModel();
        this.view=view;
    }

    @Override
    public void getNewGood(HashMap<String,String> map) {
        model.getNewGoodList(map, new CallBack() {
            @Override
            public void success(Object data) {
                view.getNewGroodReturn((NewGroodBean) data);
            }

            @Override
            public void fail(String err) {
                view.showToast(err);
            }
        });
    }

    @Override
    public void getNewGoodHot() {
        model.getNewGoodHotList(new CallBack() {
            @Override
            public void success(Object data) {
                view.getNewGroodHotReturn((NewGoodHotBean) data);
            }

            @Override
            public void fail(String err) {
                view.showToast(err);
            }
        });
    }
}
