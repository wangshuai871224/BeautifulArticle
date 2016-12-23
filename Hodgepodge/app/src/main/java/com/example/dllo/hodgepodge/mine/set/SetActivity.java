package com.example.dllo.hodgepodge.mine.set;

import android.widget.TextView;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseActivity;
import com.example.dllo.hodgepodge.widget.SwipeBackLayout;


/**
 * Created by shuaiwang on 16/12/22.
 * 从左侧滑,退出当前页面
 */
public class SetActivity extends BaseActivity{

    private SwipeBackLayout mSwipeBackLayout;
    @Override
    protected int setLayout() {
        return R.layout.activity_set;
    }

    @Override
    protected void initView() {
        mSwipeBackLayout = new SwipeBackLayout(this);
    }

    @Override
    protected void initData() {
        mSwipeBackLayout.bind();
    }
}
