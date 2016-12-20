package com.example.dllo.hodgepodge.main;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseActivity;
import com.example.dllo.hodgepodge.tools.HttpUtil;
import com.example.dllo.hodgepodge.tools.OkHttpManager;

public class MainActivity extends BaseActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private MainAdapter mAdapter;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mTabLayout = bindView(R.id.main_tab);
        mViewPager = bindView(R.id.main_vp);
        mAdapter = new MainAdapter(getSupportFragmentManager());

    }

    @Override
    protected void initData() {

        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setSelectedTabIndicatorColor(Color.argb(00,00,00,00));

        int mTab = mTabLayout.getTabCount();
        for (int i = 0; i < mTab; i++) {
            TabLayout.Tab tabTitle = mTabLayout.getTabAt(i);
            tabTitle.setIcon(MainBean.getVpFragment().get(i).getImageId());
        }

    }


}
