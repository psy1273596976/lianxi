package com.http.myshop.presenter.home;

import com.http.myshop.base.BasePresenter;
import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.shop.home.IHome;
import com.http.myshop.model.home.bean.HomeBean;
import com.http.myshop.model.home.HomeModel;

public class HomePresenter extends BasePresenter<IHome.View> implements IHome.Presenter {

    IHome.Model model;

    public HomePresenter() {
        model=new HomeModel();
    }

    @Override
    public void getHome() {
        if(mView != null) {
          model.getHomeList(new CallBack() {
              @Override
              public void success(Object data) {
                  mView.getHomeReturn((HomeBean) data);
              }

              @Override
              public void fail(String err) {
                  mView.showToast(err);
              }
          });

        }
    }
}
