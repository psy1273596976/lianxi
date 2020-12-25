package com.http.myshop.presenter.home;

import com.http.myshop.base.BasePresenter;
import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.shop.home.IChannel;
import com.http.myshop.model.home.ChannelModel;
import com.http.myshop.model.home.bean.ChannelTypeBean;
import com.http.myshop.model.home.bean.ChannlBean;

public class ChannelPresenter extends BasePresenter<IChannel.View> implements IChannel.Presenter{
    IChannel.Model model;
    IChannel.View view;

    public ChannelPresenter(IChannel.View view) {
        this.view=view;
        this.model=new ChannelModel();
    }


    @Override
    public void getChannel(String url) {
        if(mView != null) {
            model.getChannle(url, new CallBack() {
                @Override
                public void success(Object data) {
                    view.getChannel((ChannlBean) data);
                }

                @Override
                public void fail(String err) {
                    mView.showToast(err);
                }
            });

        }
    }

    @Override
    public void getChannelType(String id) {
        if(mView != null) {
            model.getChannleType(id, new CallBack() {
                @Override
                public void success(Object data) {
                    view.getChannelType((ChannelTypeBean) data);
                }

                @Override
                public void fail(String err) {
                    mView.showToast(err);
                }
            });

        }
    }
}
