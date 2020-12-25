package com.http.myshop.interfaces.shop.home;

import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.IBaseModel;
import com.http.myshop.interfaces.IBasePresenter;
import com.http.myshop.interfaces.IBaseView;
import com.http.myshop.model.home.bean.HomeBean;

public interface IHome {
    interface View extends IBaseView{
        void getHomeReturn(HomeBean homeBean);
    }

    interface Presenter extends IBasePresenter<View>{
        void getHome();
    }

    interface Model extends IBaseModel{
        void getHomeList(CallBack callBack);
    }

}
