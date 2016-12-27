package com.example.dllo.hodgepodge.pictorial;

import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseActivity;
import com.example.dllo.hodgepodge.listener.NetCallBack;
import com.example.dllo.hodgepodge.tools.LiteOrmTool;
import com.example.dllo.hodgepodge.tools.OkHttpManager;
import com.litesuits.orm.LiteOrm;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by shuaiwang on 16/12/24.
 */
public class PictorialItemActivity extends BaseActivity implements View.OnClickListener {

    private TextView designCity, designerName, commentNum, likeUserNum;
    private ImageView avatarUrl, returnBack, pictorialCollect;
    private WebView mWebView;
    private Intent mIntent;
    private String url, itemTitle, itemSubTitle, itemImageUrl, itemWebUrl;
    private PictorialItemBean mPictorialItemBean;
    private boolean isSave = false;
    private CollectBean mBean;


    @Override
    protected int setLayout() {
        return R.layout.activity_pictorial_item;
    }

    @Override
    protected void initView() {
        designCity = bindView(R.id.design_city);
        designerName = bindView(R.id.designer_name);
        avatarUrl = bindView(R.id.avatar_url);
        returnBack = bindView(R.id.return_back);
        commentNum = bindView(R.id.comment_num);
        likeUserNum = bindView(R.id.like_user_num);
        mWebView = bindView(R.id.pictorial_web_view);
        pictorialCollect = bindView(R.id.pictorial_collect);
        setClick(this, returnBack, pictorialCollect);

        mIntent = getIntent();

    }

    @Override
    protected void initData() {

        url = mIntent.getStringExtra("itemUrl");
        Log.d("PictorialItemActivity", url);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());

        OkHttpManager.getInstance().get(url, PictorialItemBean.class, new NetCallBack<PictorialItemBean>() {
            @Override
            public void onResponse(PictorialItemBean bean) {
                mPictorialItemBean = bean;
                setDetail(bean);

                mBean = new CollectBean(url, mPictorialItemBean.getData().getTitle()
                        , mPictorialItemBean.getData().getSub_title()
                        , mPictorialItemBean.getData().getImage_url()
                        , mPictorialItemBean.getData().getWeb_url());

            }

            @Override
            public void onError(Exception e) {

            }
        });

    }


    private void setDetail(PictorialItemBean bean) {

        mWebView.loadUrl(bean.getData().getWeb_url());
        if (LiteOrmTool.getmLiteOrm().isSave(mPictorialItemBean.getData().getTitle())) {
            pictorialCollect.setImageResource(R.mipmap.heart_icon_h);
            isSave = true;
        }
        Glide.with(this).load(bean.getData().getDesigners().get(0).getAvatar_url())
                .bitmapTransform(new CropCircleTransformation(this)).into(avatarUrl);
        designerName.setText(bean.getData().getDesigners().get(0).getName());
        designCity.setText(bean.getData().getDesigners().get(0).getCity());
        commentNum.setText(String.valueOf(bean.getData().getComment_num()));
        likeUserNum.setText(String.valueOf(bean.getData().getLike_user_num()));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.return_back:
                finish();
                break;
            case R.id.pictorial_collect:

                if (!isSave) {
                    pictorialCollect.setImageResource(R.mipmap.heart_icon_h);
                    LiteOrmTool.getmLiteOrm().insertCollectBean(mBean);
                } else {
                    pictorialCollect.setImageResource(R.mipmap.heart_icon);
                    LiteOrmTool.getmLiteOrm().deleteByTitle(mBean);
                }

                break;

        }
    }
}
