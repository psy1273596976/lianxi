package com.http.myshop.interfaces.test;


import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.IBaseModel;
import com.http.myshop.interfaces.IBasePresenter;
import com.http.myshop.interfaces.IBaseView;
import com.http.myshop.model.test.TestBean;

public interface ITest {

    interface View extends IBaseView {
        void getListReturn(TestBean testBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void getList();
    }


    interface Model extends IBaseModel {
        void getList(CallBack callback);
    }

}
