package com.http.myshop.ui.home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.http.myshop.R;
import com.http.myshop.adapter.home.NewGoodActivityAdapter;
import com.http.myshop.adapter.home.NewGroodAdapter;
import com.http.myshop.base.BaseActivity;
import com.http.myshop.base.BaseAdapter;
import com.http.myshop.interfaces.shop.home.INewGood;
import com.http.myshop.model.home.bean.NewGoodHotBean;
import com.http.myshop.model.home.bean.NewGroodBean;
import com.http.myshop.presenter.home.NewGoodPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NewGroodActivity extends BaseActivity<INewGood.Presenter> implements INewGood.View {
    @BindView(R.id.img_newgood)
    ImageView imgNewgood;
    @BindView(R.id.txt_info)
    TextView txtInfo;
    @BindView(R.id.layout_info)
    ConstraintLayout layoutInfo;
    @BindView(R.id.txt_all)
    TextView txtAll;
    @BindView(R.id.txt_price)
    TextView txtPrice;
    @BindView(R.id.img_arrow_up)
    ImageView imgArrowUp;
    @BindView(R.id.img_arrow_down)
    ImageView imgArrowDown;
    @BindView(R.id.layout_price)
    LinearLayout layoutPrice;
    @BindView(R.id.txt_sort)
    TextView txtSort;
    @BindView(R.id.layout_sort)
    ConstraintLayout layoutSort;
    @BindView(R.id.recy_newgoodList)
    RecyclerView mRlvnewgood;

    private static final String ASC = "asc";
    private static final String DESC = "desc";
    private static final String DEFAULT = "default";
    private static final String PRICE = "price";
    private static final String CATEGORY = "category";

    //是否是新品
    private int isNew = 1;
    private int page = 1;
    private int size = 1000;
    private String order;
    private String sort;
    private int categoryId;
    private ArrayList<NewGroodBean.DataBeanX.GoodsListBean> mNewGood;
    private NewGoodActivityAdapter newGoodActivityAdapter;
    private ArrayList<NewGoodHotBean.DataBean.BannerInfoBean> mNewGoodHot;
    private PopupWindow mPw;
    private HashMap<String, String> map;

    @Override
    protected int getLayout() {
        return R.layout.activity_newgood;
    }

    @Override
    protected INewGood.Presenter createPrenter() {
        return new NewGoodPresenter(this);
    }

    @SuppressLint("ResourceType")
    @Override
    protected void initView() {
        //综合
        order = ASC;
        sort = DEFAULT;
        categoryId = 0;
        layoutPrice.setTag(0);
        //初始化是将综合变为红色
        txtAll.setTextColor(Color.parseColor(NewGroodActivity.this.getString(R.color.price)));

        //创建空集合
        mNewGood = new ArrayList<>();
        mRlvnewgood.setLayoutManager(new GridLayoutManager(this, 2));
        //创建适配器
        newGoodActivityAdapter = new NewGoodActivityAdapter(this, mNewGood);
        //设置适配器
        mRlvnewgood.setAdapter(newGoodActivityAdapter);

        //新品顶部信息
        //创建空集合
        mNewGoodHot = new ArrayList<>();

        map = new HashMap<>();
    }

    @Override
    protected void initData() {
        presenter.getNewGood(map);
        presenter.getNewGoodHot();
    }


    @SuppressLint("ResourceType")
    @OnClick({R.id.txt_all, R.id.txt_price, R.id.txt_sort})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_all:
                //初始化
                resetPriceState();
                //将字体设置为选中状态
                txtAll.setTextColor(Color.parseColor(NewGroodActivity.this.getString(R.color.price)));
                sort = DEFAULT;
                categoryId = 0;
                getParam();
                break;
            case R.id.txt_price:
                int tag = (int) layoutPrice.getTag();
                if (tag == 0) {
                    //初始化
                    resetPriceState();
                    //设置升序
                    priceStateUp();
                    layoutPrice.setTag(1);
                    order = ASC;
                } else if (tag == 1) {
                    //初始化
                    resetPriceState();
                    //设置降序
                    priceStateDown();
                    layoutPrice.setTag(0);
                    order = DESC;
                }
                sort = PRICE;
                getParam();
                break;
            case R.id.txt_sort:
                //初始化
                resetPriceState();
                //设置字体状态
                txtSort.setTextColor(Color.parseColor(NewGroodActivity.this.getString(R.color.price)));

                //显示pw
                mPw.showAtLocation(mRlvnewgood, Gravity.TOP, 0 ,600);
                break;
        }
    }

    //按照价格降序排序
    @SuppressLint("ResourceType")
    private void priceStateDown() {
        //将图片设置为降序是的状态
        imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_normal);
        imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_select);
        //将字体设置为降序是的状态
        txtPrice.setTextColor(Color.parseColor(NewGroodActivity.this.getString(R.color.price)));
    }

    //按照价格升序排序
    @SuppressLint("ResourceType")
    private void priceStateUp() {
        //将图片设置为升序是的状态
        imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_normal);
        imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_select);
        //将字体设置为选中
        txtPrice.setTextColor(Color.parseColor(NewGroodActivity.this.getString(R.color.price)));
    }

    /*
     * 重置条件选择的所有状态
     * */
    @SuppressLint("ResourceType")
    private void resetPriceState() {
        //将图片替换为最原始的
        imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_normal);
        imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_normal);
        //将字体替换为最原始的颜色
        txtAll.setTextColor(Color.parseColor(NewGroodActivity.this.getString(R.color.black)));
        txtPrice.setTextColor(Color.parseColor(NewGroodActivity.this.getString(R.color.black)));
        txtSort.setTextColor(Color.parseColor(NewGroodActivity.this.getString(R.color.black)));
        //设置tag为0
        layoutPrice.setTag(0);
    }

    /*
     * 组装接口参数
     * */
    private void getParam() {
        map.clear();
        map.put("isNew", String.valueOf(isNew));
        map.put("page", String.valueOf(page));
        map.put("size", String.valueOf(size));
        map.put("order", order);
        map.put("sort", sort);
        map.put("categoryId", String.valueOf(categoryId));
        presenter.getNewGood(map);
    }

    @Override
    public void getNewGroodReturn(NewGroodBean newGroodBean) {
        List<NewGroodBean.DataBeanX.GoodsListBean> goodsList = newGroodBean.getData().getGoodsList();
        mNewGood.clear();
        mNewGood.addAll(goodsList);
        newGoodActivityAdapter.notifyDataSetChanged();
        List<NewGroodBean.DataBeanX.FilterCategoryBean> filterCategory = newGroodBean.getData().getFilterCategory();
        initPw(filterCategory);
       /* int size = newGroodBean.getData().getData().size();
        Log.e("tag", "getNewGroodReturn: " + size);*/
    }

    private void initPw(List<NewGroodBean.DataBeanX.FilterCategoryBean> goodsList) {
        View viewpw=View.inflate(this,R.layout.layout_pw_item,null);
        mPw = new PopupWindow(viewpw, GridView.LayoutParams.MATCH_PARENT, 100);
//        mPw.setOutsideTouchable(true);
        mPw.setBackgroundDrawable(null);

        RecyclerView mRlvPw = viewpw.findViewById(R.id.rlv_newgood_pw);

        mRlvPw.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        //创建适配器
        NewGroodAdapter newGroodAdapter = new NewGroodAdapter(this,goodsList);
        //设置适配器
        mRlvPw.setAdapter(newGroodAdapter);

        newGroodAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                int id = goodsList.get(pos).getId();
                Log.e("tag", "itemClick: "+"点击了"+ id);
                mPw.dismiss();
                categoryId=id;
                Log.e("tag", "itemClick: "+"赋值了"+categoryId );
                getParam();
            }
        });
    }

    @Override
    public void getNewGroodHotReturn(NewGoodHotBean newGroodHotBean) {
        mNewGoodHot.add(newGroodHotBean.getData().getBannerInfo());
        txtInfo.setText(mNewGoodHot.get(0).getName());
        Glide.with(this).load(mNewGoodHot.get(0).getImg_url()).into(imgNewgood);
    }

}
