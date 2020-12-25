package com.http.myshop.interfaces.shop.home;

import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.IBaseModel;
import com.http.myshop.interfaces.IBasePresenter;
import com.http.myshop.interfaces.IBaseView;
import com.http.myshop.model.home.bean.NewGoodHotBean;
import com.http.myshop.model.home.bean.NewGroodBean;

import java.util.HashMap;

public interface INewGood {
    interface View extends IBaseView {
        void getNewGroodReturn(NewGroodBean newGroodBean);
        void getNewGroodHotReturn(NewGoodHotBean newGroodHotBean);
    }

    interface Presenter extends IBasePresenter<INewGood.View> {
        void getNewGood(HashMap<String,String> map);
        void getNewGoodHot();
    }

    interface Model extends IBaseModel {
        void getNewGoodList(HashMap<String,String> map, CallBack callBack);
        void getNewGoodHotList(CallBack callBack);
    }
}
