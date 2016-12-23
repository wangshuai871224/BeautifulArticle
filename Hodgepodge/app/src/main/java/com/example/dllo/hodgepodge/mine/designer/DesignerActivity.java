package com.example.dllo.hodgepodge.mine.designer;

import android.widget.ImageView;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseActivity;

/**
 * Created by shuaiwang on 16/12/22.
 */
public class DesignerActivity extends BaseActivity{

    private ImageView mImageView;
    @Override
    protected int setLayout() {
        return R.layout.activity_designer;
    }

    @Override
    protected void initView() {
         mImageView = bindView(R.id.designer_back);
    }

    @Override
    protected void initData() {

    }
}
