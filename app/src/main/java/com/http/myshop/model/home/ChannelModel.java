package com.http.myshop.model.home;

import com.http.myshop.base.BaseModel;
import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.shop.home.IChannel;
import com.http.myshop.model.home.bean.ChannelTypeBean;
import com.http.myshop.model.home.bean.ChannlBean;
import com.http.myshop.net.CommonSubscriber;
import com.http.myshop.net.HttpManager;
import com.http.myshop.utils.RxUtils;

public class ChannelModel extends BaseModel implements IChannel.Model {
    @Override
    public void getChannle(String url, CallBack callBack) {
        addDisposible(
                HttpManager.getInstance().shopApi().getChannel(url)
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<ChannlBean>(callBack) {
                            @Override
                            public void onNext(ChannlBean channlBean) {
                                callBack.success(channlBean);
                            }
                        })
        );
    }

    @Override
    public void getChannleType(String id, CallBack callBack) {
        addDisposible(
                HttpManager.getInstance().shopApi().getChannelType(id)
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<ChannelTypeBean>(callBack) {
                            @Override
                            public void onNext(ChannelTypeBean channelTypeBean) {
                                callBack.success(channelTypeBean);
                            }
                        })
        );
    }
}
