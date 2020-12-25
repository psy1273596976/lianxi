package com.http.myshop.interfaces.shop.topic;

import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.IBaseModel;
import com.http.myshop.interfaces.IBasePresenter;
import com.http.myshop.interfaces.IBaseView;
import com.http.myshop.model.topic.TopicBean;
import com.http.myshop.model.topic.TopicInfoBean;

public interface ITopic {
    interface View extends IBaseView {
        void getTopicReturn(TopicBean topicBean);
        void getTopicInfoReturn(TopicInfoBean topicInfoBean);
    }

    interface Presenter extends IBasePresenter<ITopic.View> {
        void getTopic(String page,String size);
        void getTopicInfo(String id);
    }

    interface Model extends IBaseModel {
        void getTopucList(String page,String size,CallBack callBack);
        void getTopucInfoList(String id,CallBack callBack);
    }
}
