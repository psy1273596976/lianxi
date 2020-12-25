package com.http.myshop.ui.home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.http.myshop.R;
import com.http.myshop.adapter.home.brand.BrandAdapter;
import com.http.myshop.adapter.home.HotGoodAdapter;
import com.http.myshop.adapter.home.NewGoodAdapter;
import com.http.myshop.adapter.home.TopicAdapter;
import com.http.myshop.adapter.home.cat.CategoryAdapter;
import com.http.myshop.app.MyApp;
import com.http.myshop.base.BaseAdapter;
import com.http.myshop.base.BaseFragment;
import com.http.myshop.interfaces.MyClickItem;
import com.http.myshop.interfaces.shop.home.IHome;
import com.http.myshop.model.home.bean.HomeBean;
import com.http.myshop.presenter.home.HomePresenter;
import com.http.myshop.utils.ItemDecoration;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<IHome.Presenter> implements IHome.View {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.layout_tab)
    LinearLayout layoutTab;
    @BindView(R.id.txt_brand_title)
    TextView txtBrandTitle;
    @BindView(R.id.recy_brand)
    RecyclerView recyBrand;
    @BindView(R.id.txt_newgood_title)
    TextView txtNewgoodTitle;
    @BindView(R.id.recy_newgood)
    RecyclerView recyNewgood;
    @BindView(R.id.recy_hotgood)
    RecyclerView recyHotgood;
    @BindView(R.id.recy_topic)
    RecyclerView recyTopic;
    @BindView(R.id.txt_hotgood_title)
    TextView txtHotgoodTitle;
    @BindView(R.id.txt_topic_title)
    TextView txtTopicTitle;
    @BindView(R.id.line_category)
    LinearLayout recyCategory;
    @BindView(R.id.btn_home_sou)
    Button btnHomeSou;
    private ArrayList<HomeBean.DataBean.BrandListBean> mBrand;
    private BrandAdapter brandAdapter;
    private ArrayList<HomeBean.DataBean.NewGoodsListBean> mNewGood;
    private NewGoodAdapter newGoodAdapter;
    private ArrayList<HomeBean.DataBean.HotGoodsListBean> mHotGood;
    private HotGoodAdapter hotGoodAdapter;
    private ArrayList<HomeBean.DataBean.TopicListBean> mTopic;
    private TopicAdapter topicAdapter;
    private CategoryAdapter categoryAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter createPrenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView() {
        //品牌供应商
        //创建空集合
        mBrand = new ArrayList<>();
        recyBrand.setLayoutManager(new GridLayoutManager(mContext, 2));
        recyBrand.addItemDecoration(new ItemDecoration(getActivity()));
        //创建适配器
        brandAdapter = new BrandAdapter(mBrand, mContext);
        //设置适配器
        recyBrand.setAdapter(brandAdapter);
        txtBrandTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity,BrandActivity.class));
            }
        });

        recyBrand.setTag(0);



        //新品首发newGood
        //创建空集合
        mNewGood = new ArrayList<>();
        recyNewgood.setLayoutManager(new GridLayoutManager(mContext, 2));
        //recyNewgood.addItemDecoration(new DividerItemDecoration(mContext, LinearLayout.VERTICAL));
        //创建适配器
        newGoodAdapter = new NewGoodAdapter(mContext, mNewGood);
        //设置适配器
        recyNewgood.setAdapter(newGoodAdapter);
        txtNewgoodTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity,NewGroodActivity.class));
            }
        });

        //人气推荐hotgood
        mHotGood = new ArrayList<>();
        recyHotgood.setLayoutManager(new LinearLayoutManager(mContext));
        recyHotgood.addItemDecoration(new DividerItemDecoration(mContext, LinearLayout.VERTICAL));
        //创建适配器
        hotGoodAdapter = new HotGoodAdapter(mContext, mHotGood);
        //设置适配器
        recyHotgood.setAdapter(hotGoodAdapter);

        //专题精选
        mTopic = new ArrayList<>();
        recyTopic.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        // recyTopic.addItemDecoration(new DividerItemDecoration(mContext,LinearLayout.VERTICAL));
        //创建适配器
        topicAdapter = new TopicAdapter(mContext, mTopic);
        //设置适配器
        recyTopic.setAdapter(topicAdapter);
    }

    @Override
    protected void initData() {
        presenter.getHome(); //初始化加载数据
    }

    @Override
    public void getHomeReturn(HomeBean result) {
        if (result.getData() != null) {
            initBanner(result.getData().getBanner());
            initChannel(result.getData().getChannel());
            initBeand(result.getData().getBrandList());
            initNewGood(result.getData().getNewGoodsList());
            initHotGood(result.getData().getHotGoodsList());
            initTopic(result.getData().getTopicList());
            initCategory(result.getData().getCategoryList());
        }
    }

    /*
     * 居家 餐厨等
     * */
    private void initCategory(List<HomeBean.DataBean.CategoryListBean> data) {
        Log.e("tag", "initCategory: "+data.get(0).getGoodsList().size());
        recyCategory.removeAllViews();
        for (int i = 0; i < data.size(); i++) {
            View view = View.inflate(getContext(), R.layout.layout_category_item1, null);
            TextView text = view.findViewById(R.id.txt_category_name);
            RecyclerView mRlv = view.findViewById(R.id.rlv_category);
            //赋值
            text.setText(data.get(i).getName());

            //居家 餐厨等
            List<HomeBean.DataBean.CategoryListBean.GoodsListBean> goodsList = data.get(i).getGoodsList();
            mRlv.setLayoutManager(new GridLayoutManager(mContext, 2));
            //创建适配器
            categoryAdapter = new CategoryAdapter(mContext, goodsList);
            mRlv.setAdapter(categoryAdapter);
            recyCategory.addView(view);
            categoryAdapter.addListClick(new BaseAdapter.IListClick() {
                @Override
                public void itemClick(int pos) {
                    int id = goodsList.get(pos).getId();
                    Log.e("tag", "itemClick: "+pos + "id" + id);
                    Intent intent = new Intent(getActivity(), CatActivity.class);
                    intent.putExtra("id",id);
                    startActivityForResult(intent,100);
                }
            });
        }

    }

    /*
     * 专题精选
     * */
    private void initTopic(List<HomeBean.DataBean.TopicListBean> data) {
        mTopic.clear();
        mTopic.addAll(data);
        topicAdapter.notifyDataSetChanged();
    }

    /*
     * 人气推荐*/
    private void initHotGood(List<HomeBean.DataBean.HotGoodsListBean> data) {
        mHotGood.clear();
        mHotGood.addAll(data);
        hotGoodAdapter.notifyDataSetChanged();
        hotGoodAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                int id = data.get(pos).getId();
                Log.e("tag", "itemClick: "+pos + "id" + id);
                Intent intent = new Intent(getActivity(), CatActivity.class);
                intent.putExtra("id",id);
                startActivityForResult(intent,100);
            }
        });
    }

    /*
     * 新品首发
     * */
    private void initNewGood(List<HomeBean.DataBean.NewGoodsListBean> data) {
        mNewGood.clear();
        mNewGood.addAll(data);
        newGoodAdapter.notifyDataSetChanged();
        newGoodAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                int id = data.get(pos).getId();
                Log.e("tag", "itemClick: "+pos + "id" + id);
                Intent intent = new Intent(getActivity(), CatActivity.class);
                intent.putExtra("id",id);
                startActivityForResult(intent,100);
            }
        });
    }

    /*
     * 初始化制造商直供
     * */
    private void initBeand(List<HomeBean.DataBean.BrandListBean> data) {
        mBrand.clear();
        mBrand.addAll(data);
        brandAdapter.notifyDataSetChanged();
        brandAdapter.setMyClickItem(new MyClickItem() {
            @Override
            public void ItemClick(int position) {
                Intent intent = new Intent(getActivity(), BrandLinkActivity.class);
                int pos = mBrand.get(position).getId();
                intent.putExtra("id",pos);
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化banner
     *
     * @param list
     */
    private void initBanner(List<HomeBean.DataBean.BannerBean> list) {
        Log.e("tag", "initBanner: " + list.size());
        //设置Banner
        banner.setImages(list).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.DataBean.BannerBean img = (HomeBean.DataBean.BannerBean) path;
                Glide.with(context).load(img.getImage_url()).into(imageView);
            }
        }).start();
    }

    /**
     * 初始化channel
     */
    private void initChannel(List<HomeBean.DataBean.ChannelBean> list) {
        layoutTab.removeAllViews();
        for (HomeBean.DataBean.ChannelBean item : list) {
            View view = View.inflate(getContext(), R.layout.layout_channel_item, null);
            TextView text = view.findViewById(R.id.txt_home_title);
            ImageView image = view.findViewById(R.id.image);
            LinearLayout lineView = view.findViewById(R.id.line);
            //设置布局水平展示
            //lineView.setOrientation(LinearLayout.HORIZONTAL);
            //设置权重
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
            lineView.setLayoutParams(layoutParams);
            //赋值
            text.setText(item.getName());
            Glide.with(getActivity()).load(item.getIcon_url()).into(image);
            layoutTab.addView(view);

            view.setTag(item);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity, ChannelActivity.class);
                    String url = ((HomeBean.DataBean.ChannelBean) v.getTag()).getIcon_url();
                    MyApp.getMap().put("url", url);
                    String newname = item.getName();
                    intent.putExtra("name", newname);
                    startActivity(intent);
                }
            });
        }
    }
}
