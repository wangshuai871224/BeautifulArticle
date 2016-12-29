package com.example.dllo.hodgepodge.pictorial;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseActivity;
import com.example.dllo.hodgepodge.listener.NetCallBack;
import com.example.dllo.hodgepodge.tools.LiteOrmTool;
import com.example.dllo.hodgepodge.tools.OkHttpManager;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.litesuits.orm.LiteOrm;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

/**
 * Created by shuaiwang on 16/12/24.
 */
public class PictorialItemActivity extends BaseActivity implements View.OnClickListener, SwipeBackActivityBase, View.OnScrollChangeListener {

    private TextView designCity, designerName, commentNum, likeUserNum;
    private ImageView avatarUrl, returnBack, pictorialCollect;
    private WebView mWebView;
    private Intent mIntent;
    private String url, itemTitle, itemSubTitle, itemImageUrl, itemWebUrl;
    private PictorialItemBean mPictorialItemBean;
    private boolean isSave = false;
    private CollectBean mBean;
    private RelativeLayout mTopLayout;
    private SwipeBackActivityHelper mSwipeBackActivityHelper;// 侧滑退出(需要添加依赖)
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient mClient;
    //    private ScrollView mScrollView;
//    private ArticleGestureDetectorListener mGestureDetectorListener;
//    private GestureDetector mGestureDetector;


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
        mWebView.setOnScrollChangeListener(this);

//        mScrollView = bindView(R.id.item_scroll);
        pictorialCollect = bindView(R.id.pictorial_collect);
        mTopLayout = bindView(R.id.pictorial_top_layout);
        setClick(this, returnBack, pictorialCollect);
//        mScrollView.setOnTouchListener(this);


        mIntent = getIntent();

    }

    @Override
    protected void initData() {
        // 侧滑退出 需要绑定Activity
        mSwipeBackActivityHelper = new SwipeBackActivityHelper(this);
        mSwipeBackActivityHelper.onActivityCreate();

        // 初始化 自定义的 GestureDetectorListener 监听接口的对象, 实现 top栏 的隐藏和显示
//        mGestureDetectorListener = new ArticleGestureDetectorListener(mTopLayout);
//        mGestureDetector = new GestureDetector(PictorialItemActivity.this, mGestureDetectorListener);

        url = mIntent.getStringExtra("itemUrl");

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
                isSave = !isSave;
                if (isSave) {
                    pictorialCollect.setImageResource(R.mipmap.heart_icon_h);
                    LiteOrmTool.getmLiteOrm().insertCollectBean(mBean);
                } else {
                    pictorialCollect.setImageResource(R.mipmap.heart_icon);
                    LiteOrmTool.getmLiteOrm().deleteByTitle(mBean);
                }
                break;

        }
    }

    /**
     * 复写 SwipeBackActivityBase接口的三个抽象方法:
     * getSwipeBackLayout, setSwipeBackEnable, scrollToFinishActivity.
     * 此外还需要手动复写另外两个方法: onPostCreate, findViewById
     */
    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        // 修改了返回值
        return mSwipeBackActivityHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        // 添加一行代码
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mSwipeBackActivityHelper.onPostCreate();
    }

    @Override
    public View findViewById(@IdRes int id) {
        View v = super.findViewById(id);
        if (v == null && mSwipeBackActivityHelper != null) {
            return mSwipeBackActivityHelper.findViewById(id);
        }
        return v;
    }

//    @Override
//    public boolean onTouch(View view, MotionEvent motionEvent) {
//        // 修改了 返回值
//        return mGestureDetector.onTouchEvent(motionEvent);
//    }


    @Override//                                  移动的距离            原始距离
    public void onScrollChange(View view, int x, int y, int oldX, int oldY) {

        // 代表向上滑动(偏移量大于0)
        if ((y - oldY) > 70) {
            Log.d("PictorialItemActivity", "y:" + y);
            Log.d("PictorialItemActivity", "oldY:" + oldY);
            mTopLayout.setVisibility(View.INVISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale);
            mTopLayout.setAnimation(animation);
        }
        // 代表向下滑动(偏移量小于0)
        if ((y - oldY) < -40) {
            Log.d("PictorialItemActivity", "y:" + y);
            Log.d("PictorialItemActivity", "oldY:" + oldY);
            mTopLayout.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.scalebig);
            mTopLayout.setAnimation(animation);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("PictorialItem Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient.connect();
        AppIndex.AppIndexApi.start(mClient, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(mClient, getIndexApiAction());
        mClient.disconnect();
    }
}
