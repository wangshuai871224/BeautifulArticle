package com.example.dllo.hodgepodge.mine.mypictorial;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseActivity;
import com.example.dllo.hodgepodge.pictorial.PictorialItemBean;
import com.example.dllo.hodgepodge.tools.LiteOrmTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuaiwang on 16/12/22.
 */
public class PictorialActivity extends BaseActivity implements View.OnClickListener {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageView pictorialBack;
    private PictorialActivityAdapter mAdapter;
    private List<Fragment> mFragments;

    @Override
    protected int setLayout() {
        return R.layout.activity_pictorial;
    }

    @Override
    protected void initView() {
        mTabLayout = bindView(R.id.pictorial_tab);
        mViewPager = bindView(R.id.pictorial_vp);
        pictorialBack = bindView(R.id.pictorial_back);
        pictorialBack.setOnClickListener(this);
        mAdapter = new PictorialActivityAdapter(getSupportFragmentManager());
        mFragments = new ArrayList<>();


    }

    @Override
    protected void initData() {
        mFragments.add(new CollectFragment());
        mFragments.add(new PraiseFragment());
        mAdapter.setFragments(mFragments);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
