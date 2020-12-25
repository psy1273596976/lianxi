package com.http.myshop.interfaces.shop.home;

import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.IBaseModel;
import com.http.myshop.interfaces.IBasePresenter;
import com.http.myshop.interfaces.IBaseView;
import com.http.myshop.model.home.bean.ChannelTypeBean;
import com.http.myshop.model.home.bean.ChannlBean;

public interface IChannel {
    interface View extends IBaseView {
        void getChannel(ChannlBean channlBean);
        void getChannelType(ChannelTypeBean channelTypeBean);
    }

    interface Presenter extends IBasePresenter<IChannel.View> {
       void getChannel(String url);
       void getChannelType(String id);
    }

    interface Model extends IBaseModel {
        void getChannle(String url,CallBack callBack);
        void getChannleType(String id,CallBack callBack);
    }
}
