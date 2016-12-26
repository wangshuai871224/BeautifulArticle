package com.example.dllo.hodgepodge.havematter;

import android.view.View;
import android.widget.ImageView;
import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseActivity;
import com.example.dllo.hodgepodge.listener.NetCallBack;
import com.example.dllo.hodgepodge.tools.OkHttpManager;
import com.example.dllo.hodgepodge.tools.URLValues;
import com.example.dllo.hodgepodge.video.newest.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TaiF on 16/12/24.
 */
public class SecondaryActivity extends BaseActivity implements View.OnClickListener {
    private Banner mBanner;
    private ImageView ivBack;
    private List<String> mImage;
    @Override
    protected int setLayout() {
        return R.layout.activity_secondary;
    }

    @Override
    protected void initView() {
        mBanner = bindView(R.id.ban_banner);
        ivBack = bindView(R.id.iv_back);
        ivBack.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        initBanner();

    }

    private void initBanner() {

        OkHttpManager.getInstance().get(URLValues.HAVEMATTER_SECONDARY, BeanSecondary.class, new NetCallBack<BeanSecondary>() {
            @Override
            public void onResponse(BeanSecondary bean) {
                mImage = new ArrayList<String>();
                for (int i = 0; i < bean.getData().getCover_images().size(); i++) {
                    mImage.add(bean.getData().getCover_images().get(i));
                }
                setBanner();
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void setBanner() {
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);// 设置banner样式
        mBanner.setImageLoader(new GlideImageLoader());//设置图片加载器
        mBanner.setImages(mImage);//设置图片集合
        mBanner.setBannerAnimation(Transformer.DepthPage);//设置Banner动画效果
        mBanner.isAutoPlay(true);//设置自动轮播
        mBanner.setDelayTime(2000);//设置播放间隔时长
        mBanner.setIndicatorGravity(BannerConfig.CENTER);//设置圆点位置
        mBanner.start();//设置完成后别忘记调用
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
