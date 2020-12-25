package com.http.myshop.interfaces.login;


import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.IBaseModel;
import com.http.myshop.interfaces.IBasePresenter;
import com.http.myshop.interfaces.IBaseView;
import com.http.myshop.model.login.LoginBean;

public interface ILogin {
    interface View extends IBaseView {
        void loginReturn(LoginBean loginBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void login(String username, String pw);
    }


    interface Model extends IBaseModel {
        void login(String username, String pw, CallBack callback);
    }
}
