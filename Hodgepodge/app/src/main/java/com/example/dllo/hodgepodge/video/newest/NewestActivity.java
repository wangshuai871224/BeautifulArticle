package com.example.dllo.hodgepodge.video.newest;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseActivity;
import com.example.dllo.hodgepodge.listener.NetCallBack;
import com.example.dllo.hodgepodge.tools.OkHttpManager;

import java.util.HashMap;

public class NewestActivity extends BaseActivity {


    private WebView mWebView;
    private String mRequestUrl;
    private String mPostId;
    private VideoView mVideoView;
    private String mVideoUrl;

    @Override
    protected int setLayout() {
        return R.layout.activity_newest;
    }

    @Override
    protected void initView() {
        mVideoView = bindView(R.id.activity_newest_video_view);
        mWebView = bindView(R.id.activity_newest_webview);
    }

    @Override
    protected void initData() {
        /**
         * 获取上一界面传过来的数据
         */
        initGetIntentData();
        /**
         * webView 获取二级界面传过来的数据 显示数据
         */
        initGetWebViewData();
        /**
         * 播放视频
         */
        initVideoPlay();
    }

    /**
     * 播放视频
     */
    private void initVideoPlay() {

        HashMap map = new HashMap();

        map.put("postid", mPostId);
        OkHttpManager.getInstance().post("http://app.vmoiver.com/apiv3/post/view", NewestVideoBean.class, new NetCallBack<NewestVideoBean>() {

            @Override
            public void onResponse(NewestVideoBean bean) {
                mVideoUrl = bean.getData().getContent().getVideo().get(0).getQiniu_url();
                // 必须写这里
                Uri uri = Uri.parse(mVideoUrl);
                mVideoView.setVideoURI(uri);
            }

            @Override
            public void onError(Exception e) {

            }
        }, map);

        mVideoView.setMediaController(new MediaController(this));
        /**
         * 准备播放监听
         */
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            }
        });
        /**
         * 播放结束的监听
         */
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mVideoView.setVideoPath(mVideoUrl);
                mVideoView.start();
            }
        });

    }

    /**
     * 获取上一界面传过来的数据
     */
    private void initGetIntentData() {
        Intent intent = getIntent();
        mRequestUrl = intent.getStringExtra("requestUrl");
        mPostId = intent.getStringExtra("postId");
    }

    /**
     * 获取获取网址及显示数据
     */
    private void initGetWebViewData() {

        mWebView.loadUrl(mRequestUrl);
        /**
         * 设置用webView加载网页 对网页中超链接按钮的响应, 当按下某个连接时WebViewClient会调用这个方法
         */
        mWebView.setWebViewClient(new WebViewClient() {
            // shouldOverrideUrlLoading控制新的连接在当前WebView中打开
            // return true时，可以自己来处理这个url，webView则不再处理这个url；
            // return false时，webView来处理这个url
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                // 在当前的webView中跳转到新的url
                // 加载网页
                view.loadUrl(mRequestUrl);
                return true;
            }
        });
        WebSettings webSettings = mWebView.getSettings();
        // setJavaScriptEnabled 设置是否支持Javascript
        webSettings.setJavaScriptEnabled(true);
        // setCacheMode 设置缓冲的模式
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

    }
}
