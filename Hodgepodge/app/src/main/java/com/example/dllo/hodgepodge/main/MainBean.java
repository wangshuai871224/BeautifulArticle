package com.example.dllo.hodgepodge.main;

import android.support.v4.app.Fragment;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.havematter.HaveMatterFragment;
import com.example.dllo.hodgepodge.mine.MineFragment;
import com.example.dllo.hodgepodge.pictorial.PictorialFragment;
import com.example.dllo.hodgepodge.video.VideoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/12/19.
 */

public class MainBean {

    private Fragment mFragment;
    private String title;
    private int imageId;

    public MainBean(int imageId, Fragment fragment, String title) {
        this.imageId = imageId;
        mFragment = fragment;
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public void setFragment(Fragment fragment) {
        mFragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static List<MainBean> getVpFragment() {
        List<MainBean> beanList = new ArrayList<>();
        beanList.add(new MainBean(R.mipmap.ic_launcher, new PictorialFragment(), "画报"));
        beanList.add(new MainBean(R.mipmap.ic_launcher, new HaveMatterFragment(), "有物"));
        beanList.add(new MainBean(R.mipmap.ic_launcher, new VideoFragment(), "视频"));
        beanList.add(new MainBean(R.mipmap.ic_launcher, new MineFragment(), "我的"));
        return beanList;
    }
}
