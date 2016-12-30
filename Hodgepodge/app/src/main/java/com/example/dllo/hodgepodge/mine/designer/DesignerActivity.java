package com.example.dllo.hodgepodge.mine.designer;

import android.view.View;
import android.widget.ImageView;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseActivity;

/**
 * Created by shuaiwang on 16/12/22.
 */
public class DesignerActivity extends BaseActivity implements View.OnClickListener{

    private ImageView mImageView;
    @Override
    protected int setLayout() {
        return R.layout.activity_designer;
    }

    @Override
    protected void initView() {
        mImageView = bindView(R.id.designer_back);
        setClick(this, mImageView);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.designer_back:
                finish();
                break;
        }
    }
}
