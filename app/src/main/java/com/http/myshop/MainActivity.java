package com.http.myshop;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.http.myshop.ui.home.CatActivity;
import com.http.myshop.ui.home.HomeFragment;
import com.http.myshop.ui.me.MeFragment;
import com.http.myshop.ui.shop.ShopFragment;
import com.http.myshop.ui.sort.SortFragment;
import com.http.myshop.ui.topic.TopicFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.tab)
    TabLayout mTab;
    private ArrayList<Fragment> mFs;
    private String[] name={"首页","专题","分类","购物车","我的"};
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

/*        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_shop, R.id.navigation_topic,R.id.navigation_sort,R.id.navigation_me)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);*/


        mFs = new ArrayList<>();
        mFs.add(new HomeFragment());
        mFs.add(new TopicFragment());
        mFs.add(new SortFragment());
        mFs.add(new ShopFragment());
        mFs.add(new MeFragment());
        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager());
        mVp.setAdapter(vpAdapter);
        mTab.setupWithViewPager(mVp);
        mTab.getTabAt(0).setIcon(R.drawable.select_home);
        mTab.getTabAt(1).setIcon(R.drawable.select_topic);
        mTab.getTabAt(2).setIcon(R.drawable.select_sort);
        mTab.getTabAt(3).setIcon(R.drawable.select_shop);
        mTab.getTabAt(4).setIcon(R.drawable.select_me);


        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        mVp.setCurrentItem(position);
    }

    //购物车跳转
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //回调打开购物车
        if(resultCode == CatActivity.RECOMMEND_CAR){
            mVp.setCurrentItem(3);
        }
    }

    class VpAdapter extends FragmentStatePagerAdapter {
        public VpAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFs.get(position);
        }

        @Override
        public int getCount() {
            return mFs.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return name[position];
        }
    }
}