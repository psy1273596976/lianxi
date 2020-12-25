package com.http.myshop.adapter.shop;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.http.myshop.R;
import com.http.myshop.base.BaseAdapter;
import com.http.myshop.model.shop.bean.CarBean;
import com.http.myshop.utils.ImageLoader;
import com.http.myshop.widget.NumberSelect;

import java.util.List;

public class CarListAdapter extends BaseAdapter<CarBean.DataBean.CartListBean> {
    private boolean isEdit; //是否是编辑状态
    private UpdateItem updateItem;

    public void setUpdateItem(UpdateItem item){
        this.updateItem = item;
    }

    public void setEditState(boolean bool){
        isEdit = bool;
    }

    public CarListAdapter(Context context, List<CarBean.DataBean.CartListBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_car_item;
    }

    @Override
    protected void bindData(CarBean.DataBean.CartListBean data, VH vh) {
        //找控件
        CheckBox checkBox = (CheckBox) vh.getViewById(R.id.cb_checkbox);
        ImageView imgItem = (ImageView) vh.getViewById(R.id.img_item);
        TextView txtName = (TextView) vh.getViewById(R.id.txt_name);
        TextView txtPrice = (TextView) vh.getViewById(R.id.txt_price);
        TextView txtNumber = (TextView) vh.getViewById(R.id.txt_number);
        TextView txtEditTitle = (TextView) vh.getViewById(R.id.txt_edit_title);
        NumberSelect numberSelect = (NumberSelect) vh.getViewById(R.id.layout_change);

        // 设置选中状态
        txtName.setVisibility(isEdit? View.GONE:View.VISIBLE);
        txtNumber.setVisibility(isEdit?View.GONE:View.VISIBLE);
        txtEditTitle.setVisibility(isEdit?View.VISIBLE:View.GONE);
        numberSelect.setVisibility(isEdit?View.VISIBLE:View.GONE);
        checkBox.setChecked(isEdit?data.selectEdit:data.selectOrder);

        //赋值
        Glide.with(context).load(data.getList_pic_url()).into(imgItem);
        txtName.setText(data.getGoods_name());
        txtPrice.setText("￥"+data.getRetail_price());
        txtNumber.setText("X "+data.getNumber());
        numberSelect.addPage(R.layout.layout_number_change);
        numberSelect.setNumber(data.getNumber());
        numberSelect.addChangeNumber(new NumberSelect.ChangeNumber() {
            @Override
            public void change(int number) {
                //修改本地数据的值
                data.setNumber(number);
                if (updateItem!=null){
                    updateItem.updateItemDate(data);
                }
            }
        });

        //checkBox.setText(data.getId()+"");
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (iItemViewClick!=null){
                    int id = data.getId();
                    iItemViewClick.itemViewClick(id,isChecked);
                }
            }
        });
    }

    public interface UpdateItem{
        void updateItemDate(CarBean.DataBean.CartListBean data);
    }
}
