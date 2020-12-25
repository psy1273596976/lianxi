package com.http.myshop.adapter.home.cat;

import android.content.Context;
import android.widget.TextView;

import com.http.myshop.R;
import com.http.myshop.base.BaseAdapter;
import com.http.myshop.model.home.bean.CategoryBean;
import com.http.myshop.utils.TxtUtils;

import java.util.List;

public class CategoryParameterAdapter extends BaseAdapter {

    public CategoryParameterAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_category_parameter_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        CategoryBean.DataBeanX.AttributeBean bean = (CategoryBean.DataBeanX.AttributeBean) data;
        TextView key= (TextView) vh.getViewById(R.id.tv_category_parameter_key);
        TextView value= (TextView) vh.getViewById(R.id.tv_category_parameter_value);

        TxtUtils.setTextView(key,bean.getName());
        TxtUtils.setTextView(value,bean.getValue());
    }
}
