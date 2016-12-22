package com.example.dllo.hodgepodge.mine.mypictorial;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseActivity;

/**
 * Created by shuaiwang on 16/12/22.
 */
public class PictorialActivity extends BaseActivity{

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected int setLayout() {
        return R.layout.activity_pictorial;
    }

    @Override
    protected void initView() {
        mTabLayout = bindView(R.id.pictorial_tab);
        mViewPager = bindView(R.id.pictorial_vp);

    }

    @Override
    protected void initData() {

    }
}
