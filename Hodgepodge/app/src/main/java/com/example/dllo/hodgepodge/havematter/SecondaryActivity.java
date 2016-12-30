package com.example.dllo.hodgepodge.havematter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.transcode.BitmapBytesTranscoder;
import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseActivity;
import com.example.dllo.hodgepodge.listener.NetCallBack;
import com.example.dllo.hodgepodge.tools.MyApp;
import com.example.dllo.hodgepodge.tools.OkHttpManager;
import com.example.dllo.hodgepodge.tools.URLValues;
import com.example.dllo.hodgepodge.video.newest.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by TaiF on 16/12/24.
 */
public class SecondaryActivity extends BaseActivity implements View.OnClickListener {
    private Banner mBanner;
    private ImageView ivBack,ivStyle,ivHighlight,ivAdvice,ivPic,ivPicOne,ivPicTwo,ivHandPic;
    private TextView tvStyle,tvHighlight,tvAdvice,tvDesc,tvName,tvFocusName,tvFocusLabel,tvDescription,tvWorks;
    private List<String> mImage;
    private String mUrl;
    @Override
    protected int setLayout() {
        return R.layout.activity_secondary;
    }

    @Override
    protected void initView() {
        mBanner = bindView(R.id.ban_banner);
        ivBack = bindView(R.id.iv_back);
        ivStyle = bindView(R.id.iv_style_pic);
        ivHighlight = bindView(R.id.iv_highlight_pic);
        ivAdvice = bindView(R.id.iv_advice_pic);
        tvStyle = bindView(R.id.tv_style);
        tvHighlight = bindView(R.id.tv_highlight);
        tvAdvice = bindView(R.id.tv_advice);
        tvDesc = bindView(R.id.tv_secondary_desc);
        tvName = bindView(R.id.tv_secondary_name);
        ivPic = bindView(R.id.iv_secondary_pic);
        ivPicOne = bindView(R.id.iv_secondary_pic_one);
        ivPicTwo = bindView(R.id.iv_secondary_pic_two);
        ivHandPic = bindView(R.id.iv_secondary_focus_hand_pic);
        tvFocusName = bindView(R.id.tv_secondary_focus_name);
        tvFocusLabel = bindView(R.id.tv_secondary_focus_label);
        tvDescription = bindView(R.id.tv_focus_description);
        tvWorks = bindView(R.id.tv_works);
        ivBack.setOnClickListener(this);
        tvWorks.setText("设计师&作品");
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        Log.d("aaaaa", id + "0000");

         mUrl = URLValues.REUSE_SECONDARY_BEFORE + id + URLValues.REUSE_SECONDARY_AFTER;
        Log.d("ssss", mUrl + "0000");
        initBanner();
        OkHttpManager.getInstance().get(mUrl, BeanHaveSecond.class, new NetCallBack<BeanHaveSecond>() {
            @Override
            public void onResponse(BeanHaveSecond bean) {
                for (int i = 0; i < bean.getData().getDesc_types().size(); i++) {

                    Glide.with(MyApp.getContext()).load(bean.getData().getDesc_types().get(0).getImage_url()).into(ivStyle);
                    tvStyle.setText(bean.getData().getDesc_types().get(0).getDesc());

                    Glide.with(MyApp.getContext()).load(bean.getData().getDesc_types().get(1).getImage_url()).into(ivHighlight);
                    tvHighlight.setText(bean.getData().getDesc_types().get(1).getDesc());

                    Glide.with(MyApp.getContext()).load(bean.getData().getDesc_types().get(2).getImage_url()).into(ivAdvice);
                    tvAdvice.setText(bean.getData().getDesc_types().get(2).getDesc());

                }
                tvName.setText(bean.getData().getName());
                tvDesc.setText(bean.getData().getDesc());

                switch (bean.getData().getImages().size()) {
                    case  1:
                        Glide.with(MyApp.getContext()).load(bean.getData().getImages().get(0)).into(ivPic);
                        ivPicOne.setVisibility(View.GONE);
                        ivPicTwo.setVisibility(View.GONE);
                        break;

                    case  2:
                        ivPicTwo.setVisibility(View.GONE);
                        Glide.with(MyApp.getContext()).load(bean.getData().getImages().get(0)).into(ivPic);
                        Glide.with(MyApp.getContext()).load(bean.getData().getImages().get(1)).into(ivPicOne);
                        break;
                    case  3:
                        Glide.with(MyApp.getContext()).load(bean.getData().getImages().get(0)).into(ivPic);
                        Glide.with(MyApp.getContext()).load(bean.getData().getImages().get(1)).into(ivPicOne);
                        Glide.with(MyApp.getContext()).load(bean.getData().getImages().get(2)).into(ivPicTwo);
                        break;
                }
                Glide.with(MyApp.getContext()).load(bean.getData().getDesigner().getAvatar_url())
                        .bitmapTransform(new CropCircleTransformation(MyApp.getContext())).into(ivHandPic);
                tvFocusName.setText(bean.getData().getDesigner().getName());
                tvFocusLabel.setText(bean.getData().getDesigner().getLabel());
                tvDescription.setText(bean.getData().getDesigner().getDescription());

            }

            @Override
            public void onError(Exception e) {

            }
        });

    }

    private void initBanner() {

        OkHttpManager.getInstance().get(mUrl, BeanHaveSecond.class, new NetCallBack<BeanHaveSecond>() {
            @Override
            public void onResponse(BeanHaveSecond bean) {
                mImage = new ArrayList<String>();
                for (int i = 0; i < bean.getData().getCover_images().size(); i++) {
                    mImage.add(bean.getData().getCover_images().get(i));
                }
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
            public void onError(Exception e) {

            }
        });
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
