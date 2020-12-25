package com.http.myshop.api;

import com.http.myshop.model.home.bean.BrandBean;
import com.http.myshop.model.home.bean.BrandGoodBean;
import com.http.myshop.model.home.bean.BrandGoodsBean;
import com.http.myshop.model.home.bean.CategoryBean;
import com.http.myshop.model.home.bean.CategoryBottomInfoBean;
import com.http.myshop.model.home.bean.ChannelTypeBean;
import com.http.myshop.model.home.bean.ChannlBean;
import com.http.myshop.model.home.bean.HomeBean;
import com.http.myshop.model.home.bean.NewGoodHotBean;
import com.http.myshop.model.home.bean.NewGroodBean;
import com.http.myshop.model.login.LoginBean;
import com.http.myshop.model.shop.bean.AddCarBean;
import com.http.myshop.model.shop.bean.CarBean;
import com.http.myshop.model.shop.bean.DeleteCarBean;
import com.http.myshop.model.shop.bean.UpdateCarBean;
import com.http.myshop.model.sort.bean.CatalogBean;
import com.http.myshop.model.sort.bean.CruuentBean;
import com.http.myshop.model.sort.bean.CruuentInfoBean;
import com.http.myshop.model.topic.TopicBean;
import com.http.myshop.model.topic.TopicInfoBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ShopApi {
    String BASE_URL="http://cdplay.cn/";

    @GET("api/index")
    Flowable<HomeBean> getHome();

    @GET("api/catalog/index/")
    Flowable<ChannlBean> getChannel(@Query("id")String url);//这个分类

    @GET("api/goods/list")//分类数据
    Flowable<ChannelTypeBean> getChannelType(@Query("categoryId")String id);

    //专题
    @GET("api/topic/list")
    Flowable<TopicBean> getTopic(@Query("page") String page,@Query("size") String size);

    //专题详情
    @GET("api/topic/detail")
    Flowable<TopicInfoBean> getTopicInfo(@Query("id") String id);

    //制造商
    @GET("api/brand/list")
    Flowable<BrandBean> getBrand(@Query("page") String page,@Query("size") String size);

    //新品发布的条件筛选数据接口
    @GET("api/goods/list")
    Flowable<NewGroodBean> getNewGood(@QueryMap HashMap<String,String> map);

    //新品发布顶部信息
    @GET("api/goods/hot")
    Flowable<NewGoodHotBean> getNewGoodHot();

    //供应商顶部信息
    @GET("api/brand/detail")
    Flowable<BrandGoodBean> getBrandGood(@Query("id")String id);
    //供应商低部信息
    @GET("api/goods/list")
    Flowable<BrandGoodsBean> getBrandGoods(@Query("brandId") String categoryId, @Query("page")String page, @Query("size")String size);

    //商品购买详情页
    @GET("api/goods/detail")
    Flowable<CategoryBean> getCar(@Query("id") String id);

    //商品 详情购买页 底部数据列表 api/goods/related?id=1155000
    @GET("api/goods/related")
    Flowable<CategoryBottomInfoBean> getCategoryBottomInfo(@Query("id")String id);

    //竖着导航栏
    @GET("api/catalog/index")
    Flowable<CatalogBean> getCatlog();

    //竖着导航数据
    @GET("api/catalog/current")
    Flowable<CruuentBean> getCurrent(@Query("id") String id);

    //竖着导航数据进入详情页
    //https://cdplay.cn/
    @GET("api/goods/list")
    Flowable<CruuentInfoBean> getCruuentInfo(@Query("categoryId") String id,@Query("page") String page,@Query("size") String size);

    //登录
    @POST("api/auth/login")
    @FormUrlEncoded
    Flowable<LoginBean> getLogin(@Field("username") String username, @Field("password") String pw);


    //添加到购物车
    @POST("api/cart/add")
    @FormUrlEncoded
    Flowable<AddCarBean> addCar(@FieldMap HashMap<String,String> map);

   //购物车列表
   @GET("api/cart/index")
   Flowable<CarBean> getCarList();


    //更新购物车的数据
    @POST("api/cart/update")
    @FormUrlEncoded
    Flowable<UpdateCarBean> updateCar(@FieldMap HashMap<String,String> map);

    //删除购物车数据
    @POST("api/cart/delete")
    @FormUrlEncoded
    Flowable<DeleteCarBean> deleteCar(@Field("productIds") String productIds);

}
