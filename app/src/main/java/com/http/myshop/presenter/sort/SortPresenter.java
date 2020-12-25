package com.http.myshop.presenter.sort;

import com.http.myshop.base.BasePresenter;
import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.sort.ISort;
import com.http.myshop.model.sort.SortModel;
import com.http.myshop.model.sort.bean.CatalogBean;
import com.http.myshop.model.sort.bean.CruuentBean;
import com.http.myshop.model.sort.bean.CruuentInfoBean;

public class SortPresenter extends BasePresenter<ISort.View> implements ISort.Presenter{
    ISort.Model model;
    ISort.View view;

    public SortPresenter(ISort.View view){
        this.view=view;
        this.model=new SortModel();
    }

    @Override
    public void getCatalog() {
        model.getCatalogList(new CallBack() {
            @Override
            public void success(Object data) {
                view.getCatalogReturn((CatalogBean) data);
            }

            @Override
            public void fail(String err) {
                view.showToast(err);
            }
        });
    }

    @Override
    public void getCurrent(String id) {
        model.getCurrentList(id, new CallBack() {
            @Override
            public void success(Object data) {
                view.getCurrentReturn((CruuentBean) data);
            }

            @Override
            public void fail(String err) {
                view.showToast(err);
            }
        });
    }

    @Override
    public void getCurrentInfo(String id, String page, String size) {
        model.getCurrentInfoList(id,page,size, new CallBack() {
            @Override
            public void success(Object data) {
                view.getCurrentInfoReturn((CruuentInfoBean) data);
            }

            @Override
            public void fail(String err) {
                view.showToast(err);
            }
        });

    }
}
