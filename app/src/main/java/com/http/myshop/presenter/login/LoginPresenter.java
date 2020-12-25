package com.http.myshop.presenter.login;


import com.http.myshop.base.BasePresenter;
import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.login.ILogin;
import com.http.myshop.model.login.LoginBean;
import com.http.myshop.model.login.LoginModel;

public class LoginPresenter extends BasePresenter<ILogin.View> implements ILogin.Presenter {
    ILogin.Model model;
    ILogin.View view;

    public LoginPresenter(ILogin.View view){
        this.view=view;
        model = new LoginModel();
    }
    @Override
    public void login(String username,String pw) {
        model.login(username, pw, new CallBack() {
            @Override
            public void success(Object data) {
                view.loginReturn((LoginBean) data);
            }

            @Override
            public void fail(String err) {
                view.showToast(err);
            }
        });
    }
}
