package com.example.dllo.hodgepodge.havematter;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseFragment;

/**
 * Created by TaiF on 16/12/19.
 */

public class HaveMatterFragment extends BaseFragment {
    private TabLayout tbHaveMatter;
    private ViewPager vpHaveMatter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_havematter;
    }

    @Override
    protected void initView() {
        tbHaveMatter = bindView(R.id.tb_havematter);
        vpHaveMatter = bindView(R.id.vp_havematter);
    }

    @Override
    protected void initData() {
        HaveMatterAdapter mAdapter = new HaveMatterAdapter(getChildFragmentManager());
    }


}
