package com.http.myshop.model.login;

import com.http.myshop.base.BaseModel;
import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.login.ILogin;
import com.http.myshop.model.home.bean.CategoryBottomInfoBean;
import com.http.myshop.net.CommonSubscriber;
import com.http.myshop.net.HttpManager;
import com.http.myshop.utils.RxUtils;

public class LoginModel extends BaseModel implements ILogin.Model {
    @Override
    public void login(String username,String pw, CallBack callback) {
        addDisposible(
                HttpManager.getInstance().shopApi().getLogin(username,pw)
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<LoginBean>(callback) {
                            @Override
                            public void onNext(LoginBean loginBean) {
                                callback.success(loginBean);
                            }
                        })
        );
    }
}
