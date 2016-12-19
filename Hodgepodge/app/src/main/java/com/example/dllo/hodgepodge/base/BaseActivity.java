package com.example.dllo.hodgepodge.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dllo on 16/12/19.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        initView();
        initData();
    }

    // 加载布局
    protected abstract int setLayout();
    // 初始化组件
    protected abstract void initView();
    // 初始化数据
    protected abstract void initData();

    protected <T extends View> T bindView(int id) {
        return (T) findViewById(id);
    }

    protected void setClick(View.OnClickListener listener, View ... views) {
        for (View view : views) {
            view.setOnClickListener(listener);
        }
    }
}
