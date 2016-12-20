package com.example.dllo.hodgepodge.video;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseFragment;
import com.example.dllo.hodgepodge.video.newest.NewestFragment;
import com.example.dllo.hodgepodge.video.series.SeriesFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/12/19.
 */

public class VideoFragment extends BaseFragment {

    private TabLayout mTab;
    private ViewPager mVp;

    @Override
    protected int setLayout() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView() {
        mTab = bindView(R.id.fragment_video_tab);
        mVp = bindView(R.id.fragment_video_vp);
    }

    @Override
    protected void initData() {
        /**
         * 绑定适配器
         */
        initAdapter();
    }

    /**
     * 绑定适配器
     */
    private void initAdapter() {
        FmVideoAdapter adapter = new FmVideoAdapter(getChildFragmentManager());
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new NewestFragment());
        fragments.add(new SeriesFragment());
        adapter.setFragments(fragments);
        mVp.setAdapter(adapter);
        mTab.setupWithViewPager(mVp);
        mTab.setSelectedTabIndicatorColor(Color.WHITE);
    }


}
