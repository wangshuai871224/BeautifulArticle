package com.example.dllo.hodgepodge.mine.wish;

import android.view.View;
import android.widget.ImageView;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseActivity;

/**
 * Created by shuaiwang on 16/12/22.
 */
public class WishActivity extends BaseActivity implements View.OnClickListener{

    private ImageView wishBack;
    @Override
    protected int setLayout() {
        return R.layout.activity_wish;
    }

    @Override
    protected void initView() {
        wishBack = bindView(R.id.wish_back);
        setClick(this, wishBack);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.wish_back:
                finish();
                break;
        }
    }
}
