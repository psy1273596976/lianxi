package com.http.myshop.presenter.home;

import com.http.myshop.base.BasePresenter;
import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.shop.home.IBrand;
import com.http.myshop.model.home.bean.BrandBean;
import com.http.myshop.model.home.bean.BrandGoodBean;
import com.http.myshop.model.home.bean.BrandGoodsBean;
import com.http.myshop.model.home.BrandModel;

public class BrandPresenter extends BasePresenter<IBrand.View> implements IBrand.Presenter {
    IBrand.Model model;
    IBrand.View view;

    public BrandPresenter(IBrand.View view){
        this.model=new BrandModel();
        this.view=view;
    }

    @Override
    public void getBrand(String page, String size) {
        if(mView != null) {
            model.getBrandList(page, size, new CallBack() {
                @Override
                public void success(Object data) {
                    view.getBrandReturn((BrandBean) data);
                }

                @Override
                public void fail(String err) {
                    view.showToast(err);
                }
            });
        }
    }

    @Override
    public void getBrandGood(String id) {
        if(mView != null) {
           model.getBrandGoodList(id, new CallBack() {
               @Override
               public void success(Object data) {
                   view.getBrandGoodReturn((BrandGoodBean) data);
               }

               @Override
               public void fail(String err) {
                    view.showToast(err);
               }
           });
        }
    }

    @Override
    public void getBrandGoods(String brandId, String page, String size) {
        if(mView != null) {
            model.getBrandGoodsList(brandId, page, size, new CallBack() {
                @Override
                public void success(Object data) {
                    view.getBrandGoodsReturn((BrandGoodsBean) data);
                }

                @Override
                public void fail(String err) {
                    view.showToast(err);
                }
            });
        }
    }
}
