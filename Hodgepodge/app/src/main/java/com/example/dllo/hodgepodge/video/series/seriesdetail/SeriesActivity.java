package com.example.dllo.hodgepodge.video.series.seriesdetail;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseActivity;
import com.example.dllo.hodgepodge.listener.NetCallBack;
import com.example.dllo.hodgepodge.tools.OkHttpManager;

import java.util.HashMap;

public class SeriesActivity extends BaseActivity {


    private VideoView mVideoView;
    private ListView mListView;
    private SeriesDetailBean mSeriesDetailBean;
    private String mSeriesFirstVideoUrl;
    // 系列二级界面post 请求接口
    public static String SERIES_DETAIL_URL = "http://app.vmoiver.com/apiv3/series/view";
    // 系列二级界面, 视频详情post 请求接口
    public static String SERIES_VIDEO_URL = "http://app.vmoiver.com/apiv3/series/getVideo";
    private String mSeriesPostId;

    @Override
    protected int setLayout() {
        return R.layout.activity_series;
    }

    @Override
    protected void initView() {
        mVideoView = bindView(R.id.activity_series_videoview);
        mListView = bindView(R.id.activity_series_lv);
    }

    @Override
    protected void initData() {
        /**
         * 获取二级界面 post请求数据
         */
        initPostSeriesDetailData();


    }

    /**
     * 播放视频
     */
    private void initVideoPlay() {

        mSeriesPostId = String.valueOf(mSeriesDetailBean.getData().getPosts().get(0).getList().get(0).getSeries_postid());
        HashMap map = new HashMap();
        map.put("series_postid", mSeriesPostId);
        OkHttpManager.getInstance().post(SERIES_VIDEO_URL, SeriesVideoBean.class, new NetCallBack<SeriesVideoBean>() {

            @Override
            public void onResponse(SeriesVideoBean bean) {
                mSeriesFirstVideoUrl = bean.getData().getQiniu_url();
                // 必须写这里
                Uri uri = Uri.parse(mSeriesFirstVideoUrl);
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
                mVideoView.setVideoPath(mSeriesFirstVideoUrl);
                mVideoView.start();
            }
        });
    }

    /**
     * 获取二级界面 post请求数据
     */
    private void initPostSeriesDetailData() {
        Intent intent = getIntent();
        String value = intent.getStringExtra("seriesId");
        HashMap map = new HashMap();
        map.put("seriesid", value);
        OkHttpManager.getInstance().post(SERIES_DETAIL_URL, SeriesDetailBean.class, new NetCallBack<SeriesDetailBean>() {
            @Override
            public void onResponse(SeriesDetailBean bean) {
                mSeriesDetailBean = bean;
                /**
                 * 这里是垃圾
                 */
                SeriesDetailAdapter adapter = new SeriesDetailAdapter();
                adapter.setSeriesDetailBean(bean);
                mListView.setAdapter(adapter);
                /**
                 * 这里是垃圾
                 */
                // 播放视频 必须在这里调用
                initVideoPlay();
            }

            @Override
            public void onError(Exception e) {

            }
        }, map);
    }
}
