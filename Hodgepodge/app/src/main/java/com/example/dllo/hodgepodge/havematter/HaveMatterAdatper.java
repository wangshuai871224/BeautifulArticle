package com.example.dllo.hodgepodge.havematter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by TaiF on 16/12/20.
 */
public class HaveMatterAdatper extends FragmentPagerAdapter{
    private BeanTab bean;
    private String title = "Daily";

    public void setBean(BeanTab bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    public HaveMatterAdatper(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
