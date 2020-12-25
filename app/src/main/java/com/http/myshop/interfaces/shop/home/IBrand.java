package com.http.myshop.interfaces.shop.home;

import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.IBaseModel;
import com.http.myshop.interfaces.IBasePresenter;
import com.http.myshop.interfaces.IBaseView;
import com.http.myshop.model.home.bean.BrandBean;
import com.http.myshop.model.home.bean.BrandGoodBean;
import com.http.myshop.model.home.bean.BrandGoodsBean;

public interface IBrand {
    interface View extends IBaseView {
        void getBrandReturn(BrandBean brandBean);
        void getBrandGoodReturn(BrandGoodBean brandGoodBean);
        void getBrandGoodsReturn(BrandGoodsBean brandGoodsBean);
    }

    interface Presenter extends IBasePresenter<IBrand.View> {
        void getBrand(String page,String size);
        void getBrandGood(String id);
        void getBrandGoods(String brandId,String page,String size);
    }

    interface Model extends IBaseModel {
        void getBrandList(String page, String size, CallBack callBack);
        void getBrandGoodList(String id, CallBack callBack);
        void getBrandGoodsList(String brandId,String page, String size, CallBack callBack);
    }
}
