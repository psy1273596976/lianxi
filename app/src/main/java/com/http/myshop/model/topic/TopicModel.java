package com.http.myshop.model.topic;

import android.util.Log;

import com.http.myshop.base.BaseModel;
import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.shop.topic.ITopic;
import com.http.myshop.net.CommonSubscriber;
import com.http.myshop.net.HttpManager;
import com.http.myshop.utils.RxUtils;

public class TopicModel extends BaseModel implements ITopic.Model {
    @Override
    public void getTopucList(String page, String size, CallBack callBack) {
        addDisposible(
                HttpManager.getInstance().shopApi().getTopic(page,"100")
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<TopicBean>(callBack) {
                            @Override
                            public void onNext(TopicBean topicBean) {
                                callBack.success(topicBean);
                            }

                            @Override
                            public void onError(Throwable t) {
                                super.onError(t);
                                Log.e("TopicModel", "onError: "+t.getMessage() );
                            }
                        })
        );
    }

    @Override
    public void getTopucInfoList(String id, CallBack callBack) {
        addDisposible(
                HttpManager.getInstance().shopApi().getTopicInfo(id)
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<TopicInfoBean>(callBack) {
                            @Override
                            public void onNext(TopicInfoBean topicInfoBean) {
                                callBack.success(topicInfoBean);
                            }

                            @Override
                            public void onError(Throwable t) {
                                super.onError(t);
                                Log.e("TopicModel", "onError: "+t.getMessage() );
                            }
                        })
        );
    }
}
