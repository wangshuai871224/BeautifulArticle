package com.example.dllo.hodgepodge.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by dllo on 16/12/19.
 */
public class MainAdapter extends FragmentPagerAdapter {


    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return MainBean.getVpFragment().get(position).getFragment();
    }

    @Override
    public int getCount() {
        return MainBean.getVpFragment().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return MainBean.getVpFragment().get(position).getTitle();
    }
}
