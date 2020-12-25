package com.http.myshop.ui.topic;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.http.myshop.R;
import com.http.myshop.base.BaseActivity;
import com.http.myshop.interfaces.shop.topic.ITopic;
import com.http.myshop.model.topic.TopicBean;
import com.http.myshop.model.topic.TopicInfoBean;
import com.http.myshop.presenter.topic.TopicPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopicInfoActivity extends BaseActivity<ITopic.Presenter> implements ITopic.View {

    @BindView(R.id.webView_topic)
    WebView webViewTopic;
    private int id;

    private String h5 = "<html>\n" +
            "            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>\n" +
            "            <head>\n" +
            "                <style>\n" +
            "                    p{\n" +
            "                        margin:0px;\n" +
            "                    }\n" +
            "                    img{\n" +
            "                        width:100%;\n" +
            "                        height:auto;\n" +
            "                    }\n" +
            "                </style>\n" +
            "            </head>\n" +
            "            <body>\n" +
            "                word\n" +
            "            </body>\n" +
            "        </html>";

    @Override
    protected int getLayout() {
        return R.layout.activity_topicinfo;
    }

    @Override
    protected ITopic.Presenter createPrenter() {
        return new TopicPresenter(this);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

    }

    @Override
    protected void initData() {
        presenter.getTopicInfo(id + "");
    }

    @Override
    public void getTopicReturn(TopicBean topicBean) {
    }

    @Override
    public void getTopicInfoReturn(TopicInfoBean topicInfoBean) {
        int id = topicInfoBean.getData().getId();
        Log.e("topic", "getTopicInfoReturn: " + id);
        initTopicDetail(topicInfoBean.getData().getContent());
    }

    //TODO h5 商品详情数据
    private void initTopicDetail(String webData) {
        //通过h5进行展示
        String content = h5.replace("word", webData);
        Log.i("TAG", content);
        webViewTopic.loadDataWithBaseURL("about:blank", content, "text/html", "utf-8", null);
    }
}
