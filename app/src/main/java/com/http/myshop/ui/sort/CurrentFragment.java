package com.http.myshop.ui.sort;

import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.http.myshop.R;
import com.http.myshop.adapter.home.sort.CruuentAdapter;
import com.http.myshop.base.BaseAdapter;
import com.http.myshop.base.BaseFragment;
import com.http.myshop.interfaces.sort.ISort;
import com.http.myshop.model.sort.bean.CatalogBean;
import com.http.myshop.model.sort.bean.CruuentBean;
import com.http.myshop.model.sort.bean.CruuentInfoBean;
import com.http.myshop.presenter.sort.SortPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CurrentFragment extends BaseFragment<ISort.Presenter> implements ISort.View {

    @BindView(R.id.head_img)
    ImageView headImg;
    @BindView(R.id.head_text)
    TextView headText;
    @BindView(R.id.head)
    ConstraintLayout head;
    @BindView(R.id.classify)
    TextView classify;
    @BindView(R.id.rlv_classify)
    RecyclerView mRlvClassify;
    private String id;
    private ArrayList<CruuentBean.DataBean.CurrentCategoryBean> mCurrent;
    private CruuentAdapter cruuentAdapter;
    private ArrayList<CruuentBean.DataBean.CurrentCategoryBean.SubCategoryListBean> mSubdata;

    @Override
    protected int getLayout() {
        return R.layout.layout_fragment_current;
    }

    @Override
    protected ISort.Presenter createPrenter() {
        return new SortPresenter(this);
    }

    @Override
    protected void initView() {
        mCurrent = new ArrayList<>();
        mSubdata = new ArrayList<>();
        //创建适配器
        mRlvClassify.setLayoutManager(new GridLayoutManager(mContext,3));
        cruuentAdapter = new CruuentAdapter(mContext, mSubdata);
        //设置适配器
        mRlvClassify.setAdapter(cruuentAdapter);
    }

    @Override
    protected void initData() {
        presenter.getCurrent(id);
    }

    public void getId(String id) {
        this.id = id;
    }

    @Override
    public void getCatalogReturn(CatalogBean catalogBean) { }

    @Override
    public void getCurrentReturn(CruuentBean catlogCurrentBean) {
        CruuentBean.DataBean.CurrentCategoryBean data = catlogCurrentBean.getData().getCurrentCategory();
        mCurrent.clear();
        mSubdata.clear();
        mCurrent.add(data);
        if (data!=null){
            mSubdata.addAll(data.getSubCategoryList());

            //赋值
            Glide.with(mContext).load(data.getWap_banner_url()).into(headImg);
            headText.setText(data.getFront_name());
            classify.setText("---"+data.getName()+"分类---");
        }

        cruuentAdapter.notifyDataSetChanged();

        cruuentAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Log.e("tagcurrent", "itemClick: "+mSubdata.get(pos).getName() );
                Log.e("tagcurrent", "itemClick: "+mSubdata.get(pos).getId() );
                Intent intent = new Intent(mContext, CurrentInfoActivity.class);
                intent.putExtra("currentId",id);
                intent.putExtra("infoid",mSubdata.get(pos).getId());
                intent.putExtra("name",mSubdata.get(pos).getName());
                startActivity(intent);
            }
        });
    }

    @Override
    public void getCurrentInfoReturn(CruuentInfoBean cruuentInfoBean) {

    }
}
