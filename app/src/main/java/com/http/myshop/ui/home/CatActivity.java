package com.http.myshop.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.http.myshop.MainActivity;
import com.http.myshop.R;
import com.http.myshop.adapter.home.cat.CategoryButtomInfoAdapter;
import com.http.myshop.adapter.home.cat.CategoryIssueAdapter;
import com.http.myshop.adapter.home.cat.CategoryParameterAdapter;
import com.http.myshop.adapter.home.cat.bigpic.CategoryBigImageAdapter;
import com.http.myshop.base.BaseActivity;
import com.http.myshop.base.BaseAdapter;
import com.http.myshop.interfaces.shop.home.ICat;
import com.http.myshop.model.home.bean.CategoryBean;
import com.http.myshop.model.home.bean.CategoryBottomInfoBean;
import com.http.myshop.model.shop.bean.AddCarBean;
import com.http.myshop.presenter.home.CatPresenter;
import com.http.myshop.ui.home.bigpic.BigImageActivity;
import com.http.myshop.utils.ItemDecoration;
import com.luck.picture.lib.tools.ToastUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CatActivity extends BaseActivity<ICat.Presenter> implements ICat.View {
    @BindView(R.id.rlvWebView_category)
    RecyclerView mRlvWebView;
    /*@BindView(R.id.webView_category)
    WebView webView;*/
    @BindView(R.id.mRlv_category_all)
    RecyclerView mRlv_all;//底部列表数据
    @BindView(R.id.mRlv_category_issue)
    RecyclerView mRlv_issue;//常见问题
    @BindView(R.id.mRlv_category_parameter)
    RecyclerView mRlv_parameter;//商品参数
    @BindView(R.id.banner_category)
    Banner banner;//Banner
    @BindView(R.id.tv_category_info_title)
    TextView tv_title;
    @BindView(R.id.tv_category_info_desc)
    TextView tv_desc;
    @BindView(R.id.tv_category_info_price)
    TextView tv_price;

    @BindView(R.id.tv_category_info_comment_head_name)
    TextView tv_head_name;
    @BindView(R.id.tv_category_info_comment_head_desc)
    TextView tv_head_desc;
    @BindView(R.id.tv_category_info_comment_head_date)
    TextView tv_head_date;
    @BindView(R.id.iv_category_info_comment_head_img)
    ImageView iv_head_img;
    @BindView(R.id.iv_category_info_comment_img)
    ImageView iv_img;
    @BindView(R.id.tv_category_addCar)
    TextView tv_category_addCar;
    @BindView(R.id.iv_catpw_img)
    ImageView iv_catpw_img;
    @BindView(R.id.iv_category_car)
    ImageView iv_category_car;
    @BindView(R.id.tv_category_number)
    TextView txtNumber;

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

    private ArrayList<CategoryBottomInfoBean.DataBean.GoodsListBean> goodsList;//底部列表集合
    private ArrayList<CategoryBean.DataBeanX.IssueBean> issuelist;//常见问题集合
    private ArrayList<CategoryBean.DataBeanX.AttributeBean> attributeList;//商品参数集合
    private CategoryButtomInfoAdapter categoryButtomInfoAdapter;
    private CategoryIssueAdapter categoryIssueAdapter;
    private CategoryParameterAdapter categoryParameterAdapter;

    public static final int RECOMMEND_CAR = 1000; //打开购物车的指令
    private int id;
    private String retail_price;
    private String path_img;

    CategoryBean categoryBean;
    int buyNumber=1; //购买的数量

    @Override
    protected int getLayout() {
        return R.layout.activity_cat;
    }

    @Override
    protected ICat.Presenter createPrenter() {
        return new CatPresenter(this);
    }

    @Override
    protected void initView() {
        initViewIssue();//常见问题布局
        initBottomInfo();//底部列表数据
        initViewParameter();//商品参数
        initAddCar();//添加进购物车
    }

    //TODO 添加进购物车
    private void initAddCar() {
        //加入购物车图标
        tv_category_addCar.setTag(1);
        tv_category_addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = (int) tv_category_addCar.getTag();
                Log.e("tag", "onClick: "+tag);
                if (tag ==1){
                    //弹出pw
                    initPw();
                    tv_category_addCar.setTag(2);
                }else{
                    //添加进购物车
                    tv_category_addCar.setTag(1);
                    String txtNum = txtNumber.getText().toString();
                    int i = Integer.parseInt(txtNum);
                    buyNumber=i; //购买的数量
                    int newprice = Integer.parseInt(retail_price);
                    int price=i*newprice;
                    Toast.makeText(CatActivity.this, "成功添加"+i+"个,总价为:"+price+"元", Toast.LENGTH_SHORT).show();
                    //添加进购物车
                    addCar();
                }
            }
        });

        //pw关闭图标
        iv_catpw_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出pw
                initPw();
            }
        });

        //跳转到购物车界面
        iv_category_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGoodCar();
            }
        });
    }

    //跳转到购物车界面
    private void openGoodCar() {
        setResult(RECOMMEND_CAR);
        finish();
    }

    //添加进购物车
    private void addCar() {
        if (buyNumber<=0){
            ToastUtils.s(this,getString(R.string.tips_buynumber));
        }
        if (categoryBean.getData().getProductList().size()>0){
            int goodsId = this.categoryBean.getData().getProductList().get(0).getGoods_id();
            int productid = this.categoryBean.getData().getProductList().get(0).getId();
            HashMap<String,String> map = new HashMap<>();
            map.put("goodsId",String.valueOf(goodsId));
            map.put("number",String.valueOf(buyNumber));
            map.put("productId",String.valueOf(productid));
            presenter.addGoodCar(map);
        }

    }

    //弹出pw
    private void initPw() {
        tv_category_addCar.setTag(2);
        View viewpw = View.inflate(this, R.layout.layout_cat_pw, null);
        PopupWindow mPw = new PopupWindow(viewpw, GridView.LayoutParams.MATCH_PARENT, 500);
        mPw.setBackgroundDrawable(null);
        mPw.setOutsideTouchable(true);
        mPw.showAtLocation(mRlvWebView, Gravity.BOTTOM, 0, 140);

        TextView txtReduce = viewpw.findViewById(R.id.txt_reduce);
        txtNumber = viewpw.findViewById(R.id.txt_number);
        TextView txtAdd = viewpw.findViewById(R.id.txt_add);
        TextView catPwNum = viewpw.findViewById(R.id.cat_pw_num);
        TextView catPwPrice = viewpw.findViewById(R.id.cat_pw_price);
        ImageView catPwClose = viewpw.findViewById(R.id.xat_pw_close);
        ImageView catPwImg = viewpw.findViewById(R.id.cat_pw_img);

        catPwClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPw.dismiss();
            }
        });

        txtReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtNum = txtNumber.getText().toString();
                int i = Integer.parseInt(txtNum);
                i--;
                if (i>=0){
                    txtNumber.setText(i+"");
                }
            }
        });
        txtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtNum = txtNumber.getText().toString();
                int i = Integer.parseInt(txtNum);
                i++;
                txtNumber.setText(i+"");
            }
        });
        Glide.with(this).load(path_img).into(catPwImg);
        catPwPrice.setText("价格:  ￥"+retail_price);
    }

    //TODO 商品参数布局
    private void initViewParameter() {
        attributeList = new ArrayList<>();
        mRlv_parameter.setLayoutManager(new LinearLayoutManager(this));
        categoryParameterAdapter = new CategoryParameterAdapter(this, attributeList);
        mRlv_parameter.setAdapter(categoryParameterAdapter);
    }

    //TODO 常见问题布局
    private void initViewIssue() {
        issuelist = new ArrayList<>();
        mRlv_issue.setLayoutManager(new LinearLayoutManager(this));
        categoryIssueAdapter = new CategoryIssueAdapter(this, issuelist);
        mRlv_issue.setAdapter(categoryIssueAdapter);
    }

    //TODO 底部列表数据
    private void initBottomInfo() {
        goodsList = new ArrayList<>();
        mRlv_all.setLayoutManager(new GridLayoutManager(this, 2));
        mRlv_all.addItemDecoration(new ItemDecoration(this));
        categoryButtomInfoAdapter = new CategoryButtomInfoAdapter(this, goodsList);
        mRlv_all.setAdapter(categoryButtomInfoAdapter);
    }

    @Override
    protected void initData() {
        //得到回传的下标
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        if (id != 0) {
            presenter.getCar(id + "");
            presenter.getCarBottom(id + "");
        }
    }

    //居家 商品详情购买页
    @Override
    public void getCarReturn(CategoryBean categoryBean) {
        if (categoryBean != null) {
            this.categoryBean = categoryBean;
            //Banner
            initBanner(categoryBean.getData());
            //Banner下面的展示数据
            initInfo(categoryBean.getData().getInfo());
            //评论
            initComment(categoryBean.getData().getComment().getData());
            //常见问题数据
            initIssue(categoryBean.getData().getIssue());
            //h5 商品详情
            //initGoodDetail(categoryBean.getData().getInfo().getGoods_desc());
            //商品参数
            initParameter(categoryBean.getData().getAttribute());

            //展示goods_desc
            showImage(categoryBean.getData().getInfo().getGoods_desc());

            if(categoryBean.getData().getProductList().size() > 0){
                int num = categoryBean.getData().getProductList().get(0).getGoods_number();
                Log.e("tag", "1111getCarReturn: "+num );
                txtNumber.setText(num+"");
                txtNumber.setVisibility(View.VISIBLE);
            }
        }
    }

    private void showImage(String goods_desc) {
        ArrayList<String> listUrl = new ArrayList<>();
        String img = "<img[\\s\\S]*?>";
        Pattern pattern = Pattern.compile(img);
        Matcher matcher = pattern.matcher(goods_desc);

        while (matcher.find()) {
            String word = matcher.group();
            int start = word.indexOf("\"") + 1;
            int end = word.indexOf(".jpg");
            if (end > 0) {//如果是jpg格式的就截取jpg
                String url = word.substring(start, end);
                if (url != null) {
                    url = url + ".jpg";
                    listUrl.add(url);
                } else {
                    return;
                }
            } else {
                int end1 = word.indexOf(".png");//如果是png格式的就截取png
                String url = word.substring(start, end1);
                if (url != null) {
                    url = url + ".png";
                    listUrl.add(url);
                } else {
                    return;
                }
            }
        }

        mRlvWebView.setLayoutManager(new LinearLayoutManager(this));
        CategoryBigImageAdapter categoryBigImageAdapter = new CategoryBigImageAdapter(this, listUrl);
        mRlvWebView.setAdapter(categoryBigImageAdapter);

        //点击条目跳转
        categoryBigImageAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("image", listUrl);
                Intent intent = new Intent(CatActivity.this, BigImageActivity.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });

//        LinearLayout id = findViewById(R.id.home__detail_info_30_ll);
//        id.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putStringArrayList("image",listUrl);
//                Intent intent = new Intent(CategoryActivity.this, BigImageActivity.class);
//                intent.putExtra("bundle", bundle);
//                startActivity(intent);
//            }
//        });
    }

    //TODO Banner数据
    private void initBanner(CategoryBean.DataBeanX data) {
        if (data!=null){
            banner.setImages(data.getGallery()).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    CategoryBean.DataBeanX.GalleryBean bean = (CategoryBean.DataBeanX.GalleryBean) path;
                    path_img = bean.getImg_url();
                    Glide.with(context).load(bean.getImg_url()).into(imageView);
                }
            }).start();
        }
    }

    //TODO Banner下面的展示数据
    private void initInfo(CategoryBean.DataBeanX.InfoBean info) {
        tv_title.setText(info.getName());
        tv_desc.setText(info.getGoods_brief());
        retail_price = info.getRetail_price();
        tv_price.setText("￥" + info.getRetail_price());
    }

    //TODO 评论
    private void initComment(CategoryBean.DataBeanX.CommentBean.DataBean data) {
        //时间
        tv_head_date.setText(data.getAdd_time());
        //名字
        tv_head_name.setText(data.getNickname());
        //评论内容
        tv_head_desc.setText(data.getContent());
        //底部图片
        //ImageLoaderUtils.loadImage(data.getPic_list().get(0).getPic_url(),iv_img);
        // Glide.with(this).load(data.getPic_list().get(0).getPic_url()).into(iv_img);
    }

    //TODO 商品参数数据
    private void initParameter(List<CategoryBean.DataBeanX.AttributeBean> attribute) {
        attributeList.addAll(attribute);
        categoryParameterAdapter.notifyDataSetChanged();
    }

    //TODO 常见问题数据
    private void initIssue(List<CategoryBean.DataBeanX.IssueBean> issue) {
        issuelist.addAll(issue);
        categoryIssueAdapter.notifyDataSetChanged();
    }

    //TODO h5 商品详情数据
    private void initGoodDetail(String webData) {
        //通过h5进行展示
        /*String content = h5.replace("word", webData);
        Log.i("TAG", content);
        webView.loadDataWithBaseURL("about:blank", content, "text/html", "utf-8", null);*/
    }

    //TODO 商品 详情购买页 底部数据列表
    @Override
    public void getCatBottom(CategoryBottomInfoBean categoryBottomInfoBean) {
        List<CategoryBottomInfoBean.DataBean.GoodsListBean> data = categoryBottomInfoBean.getData().getGoodsList();
        goodsList.addAll(data);
        categoryButtomInfoAdapter.notifyDataSetChanged();
    }

    @Override
    public void addGoodCarReturn(AddCarBean addCarBean) {
        //添加成功以后更新数量显示
        int number = addCarBean.getData().getCartTotal().getGoodsCount();
        txtNumber.setText(String.valueOf(number));
        txtNumber.setVisibility(View.VISIBLE);
    }
}
