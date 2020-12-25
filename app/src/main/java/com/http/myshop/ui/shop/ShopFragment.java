package com.http.myshop.ui.shop;


import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.http.myshop.MainActivity;
import com.http.myshop.R;
import com.http.myshop.adapter.shop.CarListAdapter;
import com.http.myshop.base.BaseAdapter;
import com.http.myshop.base.BaseFragment;
import com.http.myshop.interfaces.shop.shop.ICar;
import com.http.myshop.interfaces.sort.ISort;
import com.http.myshop.model.shop.bean.CarBean;
import com.http.myshop.model.shop.bean.DeleteCarBean;
import com.http.myshop.model.shop.bean.UpdateCarBean;
import com.http.myshop.presenter.shop.CarPresenter;
import com.http.myshop.ui.login.LoginActivity;
import com.http.myshop.utils.SpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class ShopFragment extends BaseFragment<ICar.Presenter> implements ICar.View , View.OnClickListener{
    @BindView(R.id.recy_good)
    RecyclerView recyGood;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.checkbox_all)
    CheckBox checkboxAll;
    @BindView(R.id.txt_totalPrice)
    TextView txtTotalPrice;
    @BindView(R.id.txt_edit)
    TextView txtEdit;
    @BindView(R.id.txt_submit)
    TextView txtSubmit;
    @BindView(R.id.layout_common)
    ConstraintLayout layoutCommon;

    private CarListAdapter carListAdapter;
    private List<CarBean.DataBean.CartListBean> list;
    private CarBean carBean;
    private boolean isEdit; //是否是编辑状态

    @Override
    protected int getLayout() {
        return R.layout.fragment_shop;
    }

    @Override
    protected ICar.Presenter createPrenter() {
        return new CarPresenter(this);
    }

    @Override
    protected void initView() {

        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG","checkboxall:"+checkboxAll.isChecked());
                boolean bool = checkboxAll.isChecked();
                if(isEdit){
                    //编辑状态下的数据刷新
                    updateGoodSelectStateEdit(bool);
                }else{
                    //下单状态下的数据刷新
                    updateGoodSelectStateOrder(bool);
                }
            }
        });
        //设置点击监听
        txtEdit.setOnClickListener(this);
        txtSubmit.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        carListAdapter = new CarListAdapter(mContext,list);
        recyGood.setLayoutManager(new LinearLayoutManager(mContext));
        recyGood.setAdapter(carListAdapter);
        String token = SpUtils.getInstance().getString("token");
        if(!TextUtils.isEmpty(token)){
            presenter.getCar();
        }else{
            //跳转到登录界面
            gotoLogin();
        }

        /**
         * 监听条目元素点击的时候的接口回调
         */
        carListAdapter.addItemViewClick(new BaseAdapter.IItemViewClick() {
            @Override
            public void itemViewClick(int id, Object data) {
                for(CarBean.DataBean.CartListBean item:list){
                    if(item.getId() == id){
                        if(!isEdit){
                            item.selectOrder = (boolean) data;
                        }else{
                            item.selectEdit = (boolean) data;
                        }
                        break;
                    }
                }
                boolean isSelectAll;
                if(!isEdit){
                    isSelectAll = totalSelectOrder();
                }else{
                    isSelectAll = totalSelectEdit();
                }
                checkboxAll.setChecked(isSelectAll);
            }
        });

        // 监听编辑状态下item的数据变化
        carListAdapter.setUpdateItem(new CarListAdapter.UpdateItem() {
            @Override
            public void updateItemDate(CarBean.DataBean.CartListBean data) {
                HashMap<String,String> map = new HashMap<>();
                map.put("goodsId",String.valueOf(data.getGoods_id()));
                map.put("productId",String.valueOf(data.getProduct_id()));
                map.put("id",String.valueOf(data.getId()));
                map.put("number",String.valueOf(data.getNumber()));
                presenter.updateCar(map);
                totalSelectEdit();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.getCar();
    }

    @Override
    public void getCarReturn(CarBean carBean) {
        this.carBean = carBean;
        list.clear();
        list.addAll(carBean.getData().getCartList());
        carListAdapter.notifyDataSetChanged();
    }

    /**
     * 更新接口之后的返回
     */
    @Override
    public void updateCarReturn(UpdateCarBean result) {
        for(UpdateCarBean.DataBean.CartListBean item:result.getData().getCartList()){
            updateCartListBeanNumberById(item.getId(),item.getNumber());
        }
        //更新商品的总数和总价
        carBean.getData().getCartTotal().setGoodsCount(result.getData().getCartTotal().getGoodsCount());
        carBean.getData().getCartTotal().setGoodsAmount(result.getData().getCartTotal().getGoodsAmount());
        carListAdapter.notifyDataSetChanged();
        totalSelectEdit();
    }

    /**
     * 刷新购物车列表的数据
     */
    private void updateCartListBeanNumberById(int carId,int number){
        for(CarBean.DataBean.CartListBean item:list){
            if(item.getId() == carId){
                item.setNumber(number);
                break;
            }
        }
    }

    /**
     * 删除购物车列表返回
     * @param result
     */
    @Override
    public void deleteCarReturn(DeleteCarBean result) {
        //通过购物车返回的最新数据，同步本地列表中的数据
        int index,lg=list.size();
        for(index=0;index<lg; index++){
            CarBean.DataBean.CartListBean item = list.get(index);
            boolean bool = deleteCarListById(result.getData().getCartList(),item.getId());
            Log.i("TAG","delete bool:"+bool +" item:"+item.getId());
            if(bool){
                list.remove(index);
                index--;
                lg--;
            }

        }
        carListAdapter.notifyDataSetChanged();
        totalSelectEdit();
    }

    /**
     * 判断当前的本地列表的购物车列表数据是否在返回的最新列表中存在
     */
    private boolean deleteCarListById(List<DeleteCarBean.DataBean.CartListBean> list ,int carId){
        for(DeleteCarBean.DataBean.CartListBean item:list){
            if(item.getId() == carId){
                return false;
            }
        }
        return true;
    }

    /**
     * 下单状态的数据刷新
     */
    private void updateGoodSelectStateOrder(boolean bool){
        for(CarBean.DataBean.CartListBean item:list){
            item.selectOrder = bool;
        }
        totalSelectOrder();
        // 更新列表条目的选中状态
        carListAdapter.notifyDataSetChanged();
    }

    /**
     * 编辑状态下的数据刷新
     */
    private void updateGoodSelectStateEdit(boolean bool){
        for(CarBean.DataBean.CartListBean item:list){
            item.selectEdit = bool;
        }
        totalSelectOrder();
        carListAdapter.notifyDataSetChanged();
    }

    /**
     * 下单状态下的总数和价格的计算
     */
    private boolean totalSelectOrder(){
        int num = 0;
        int totalPrice = 0;
        boolean isSelectAll = true;
        for(CarBean.DataBean.CartListBean item:list){
            if(item.selectOrder){
                num += item.getNumber();
                totalPrice += item.getNumber()*item.getRetail_price();
            }else{
                if(isSelectAll){
                    isSelectAll = false;
                }
            }
        }
        String strAll = "全选($)";
        strAll = strAll.replace("$",String.valueOf(num));
        checkboxAll.setText(strAll);
        txtTotalPrice.setText("￥"+totalPrice);
        Log.i("TAG","num: "+num+"price："+totalPrice);
        return isSelectAll;
    }

    /**
     * 编辑状态下的
     */
    private boolean totalSelectEdit(){
        int num = 0;
        boolean isSelectAll = true;
        for(CarBean.DataBean.CartListBean item:list){
            if(item.selectEdit){
                num += item.getNumber();
            }else{
                if(isSelectAll){
                    isSelectAll = false;
                }
            }
        }
        String strAll = "全选($)";
        strAll = strAll.replace("$",String.valueOf(num));
        checkboxAll.setText(strAll);
        return isSelectAll;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_edit:
                changeEdit();
                break;
            case R.id.txt_submit:
                submit();
                break;
        }
    }

    /**
     * 修改编辑和完成的状态
     */
    private void changeEdit(){
        if("编辑".equals(txtEdit.getText().toString())){
            txtEdit.setText("完成");
            txtSubmit.setText("删除所选");
            isEdit = true;
            txtTotalPrice.setVisibility(View.GONE);
        }else if("完成".equals(txtEdit.getText().toString())){
            txtEdit.setText("编辑");
            txtSubmit.setText("下单");
            isEdit = false;
            updateGoodSelectStateEdit(false);
            txtTotalPrice.setVisibility(View.VISIBLE);
        }
        carListAdapter.setEditState(isEdit);
        carListAdapter.notifyDataSetChanged();
    }

    /**
     * 提交
     */
    private void submit(){
        if("下单".equals(txtSubmit.getText().toString())){
            //订单

        }else if("删除所选".equals(txtSubmit.getText().toString())){
            //删除购物车所选数据
            deleteCar();
        }
    }

    /**
     *删除所有选中的商品数据
     */
    private void deleteCar(){
        StringBuilder sb = new StringBuilder();
        for(CarBean.DataBean.CartListBean item:list){
            if(item.selectEdit){
                sb.append(item.getProduct_id());
                sb.append(",");
            }
        }
        //注意长度减1
        if(sb.length() > 0){
            sb.deleteCharAt(sb.length()-1);
        }
        Log.i("TAG",sb.toString());
        presenter.deleteCar(sb.toString());
    }
}
