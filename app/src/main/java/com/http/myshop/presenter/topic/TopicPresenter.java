package com.http.myshop.presenter.topic;

import com.http.myshop.base.BasePresenter;
import com.http.myshop.interfaces.CallBack;
import com.http.myshop.interfaces.shop.topic.ITopic;
import com.http.myshop.model.topic.TopicBean;
import com.http.myshop.model.topic.TopicInfoBean;
import com.http.myshop.model.topic.TopicModel;

public class TopicPresenter extends BasePresenter<ITopic.View> implements ITopic.Presenter {
    ITopic.Model model;
    ITopic.View view;
    public TopicPresenter(ITopic.View view){
        this.model=new TopicModel();
        this.view=view;
    }

    @Override
    public void getTopic(String page, String size) {
        model.getTopucList(page, size, new CallBack() {
            @Override
            public void success(Object data) {
                view.getTopicReturn((TopicBean) data);
            }

            @Override
            public void fail(String err) {
                view.showToast(err);
            }
        });
    }

    @Override
    public void getTopicInfo(String id) {
        model.getTopucInfoList(id, new CallBack() {
            @Override
            public void success(Object data) {
                view.getTopicInfoReturn((TopicInfoBean) data);
            }

            @Override
            public void fail(String err) {
                view.showToast(err);
            }
        });
    }
}
