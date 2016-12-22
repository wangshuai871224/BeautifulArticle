package com.example.dllo.hodgepodge.havematter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by TaiF on 16/12/20.
 */
public class HaveMatterAdapter extends FragmentStatePagerAdapter {
    private static BeanTab bean;
    private String title = "Daily";

    public void setBean(BeanTab bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    public HaveMatterAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new DailyFragment();
        } else {
            return ReuseFragment.newInstance(position);
        }
    }

    @Override
    public int getCount() {
        return bean == null ? 0 : bean.getData().getCategories().size();
    }

    public static String getMessage(int pos) {
        return String.valueOf(bean.getData().getCategories().get(pos).getId());
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return title;
        } else {
            return bean.getData().getCategories().get(position).getName();
        }
    }
}
