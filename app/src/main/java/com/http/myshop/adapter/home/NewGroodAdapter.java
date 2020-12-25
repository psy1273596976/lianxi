package com.http.myshop.adapter.home;

import android.content.Context;
import android.widget.TextView;

import com.http.myshop.R;
import com.http.myshop.base.BaseAdapter;
import com.http.myshop.model.home.bean.NewGroodBean;

import java.util.List;

public class NewGroodAdapter extends BaseAdapter {

    private Context context;
    public NewGroodAdapter(Context context, List data) {
        super(context, data);
        this.context=context;
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_newgroodadapter_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        NewGroodBean.DataBeanX.FilterCategoryBean gooddata= (NewGroodBean.DataBeanX.FilterCategoryBean) data;
        TextView name = (TextView) vh.getViewById(R.id.txt_pw_name);
        name.setText(gooddata.getName());

    }

}
