package com.http.myshop.interfaces.sort;

import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.IBaseModel;
import com.http.myshop.interfaces.IBasePresenter;
import com.http.myshop.interfaces.IBaseView;
import com.http.myshop.model.sort.bean.CatalogBean;
import com.http.myshop.model.sort.bean.CruuentBean;
import com.http.myshop.model.sort.bean.CruuentInfoBean;

public interface ISort {
    interface View extends IBaseView {
        void getCatalogReturn(CatalogBean catalogBean);
        void getCurrentReturn(CruuentBean catlogCurrentBean);
        void getCurrentInfoReturn(CruuentInfoBean cruuentInfoBean);

    }

    interface Presenter extends IBasePresenter<ISort.View> {
        void getCatalog();
        void getCurrent(String id);
        void getCurrentInfo(String id,String page,String size);

    }

    interface Model extends IBaseModel {
        void getCatalogList(CallBack callBack);
        void getCurrentList(String id,CallBack callBack);
        void getCurrentInfoList(String id,String page,String size,CallBack callBack);
    }
}
