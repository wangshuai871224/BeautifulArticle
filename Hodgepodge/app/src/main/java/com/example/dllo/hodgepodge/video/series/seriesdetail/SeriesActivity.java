package com.example.dllo.hodgepodge.video.series.seriesdetail;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseActivity;
import com.example.dllo.hodgepodge.listener.NetCallBack;
import com.example.dllo.hodgepodge.tools.OkHttpManager;
import com.example.dllo.hodgepodge.tools.URLValues;

import java.util.HashMap;

public class SeriesActivity extends BaseActivity {


    private VideoView mVideoView;
    private ListView mListView;
    private SeriesDetailBean mSeriesDetailBean;
    private String mSeriesFirstVideoUrl;
    private String mDetailTitle;
    private String mNumber;
    private int a = 0;// 正在播放视频的横向position
    private int b = 0;// 正在播放视频的纵向position
    private String mSeriesPostId;
    private RadioButton mRadioButton;
    private boolean radioButton = true;// 默认第一个radioButton 为选中状态
    private SeriesDetailAdapter mAdapter;
    private TextView mTvDetailTitle;
    private ImageView mIvBack;

    @Override
    protected int setLayout() {
        return R.layout.activity_series;
    }

    @Override
    protected void initView() {
        mVideoView = bindView(R.id.activity_series_videoview);
        mListView = bindView(R.id.activity_series_lv);
        mIvBack = bindView(R.id.activity_series_iv_back);

    }

    /**
     * 头布局操作
     */
    private void initHeadView() {
        View headView = LayoutInflater.from(this).inflate(R.layout.headview_series_activity, null);
        TextView tvTitle = (TextView) headView.findViewById(R.id.headview_series_activity_tv_title);
        TextView tvCountFollow = (TextView) headView.findViewById(R.id.headview_series_activity_tv_count_follow);
        TextView tvWeekly = (TextView) headView.findViewById(R.id.headview_series_activity_tv_detail_weekly);
        TextView tvUpdate = (TextView) headView.findViewById(R.id.headview_series_activity_tv_detail_update_to);
        TextView tvTagName = (TextView) headView.findViewById(R.id.headview_series_activity_tv_detail_tag_name);
        mTvDetailTitle = (TextView) headView.findViewById(R.id.headview_series_activity_tv_detail_title);
        final TextView tvContent = (TextView) headView.findViewById(R.id.headview_series_activity_tv_detail_content);
        final TextView tvContentTwo = (TextView) headView.findViewById(R.id.headview_series_activity_tv_detail_content_two);
        final RelativeLayout rlMore = (RelativeLayout) headView.findViewById(R.id.headview_series_activity_rl_look_more);
        final RelativeLayout rlIntroduction = (RelativeLayout) headView.findViewById(R.id.headview_series_activity_rl_ntroduction_of_fold);
        RadioGroup radioGroup = (RadioGroup) headView.findViewById(R.id.headview_series_activity_radio_group_horizontal_scorllview);
        // 添加头布局
        mListView.addHeaderView(headView);
        tvTitle.setText(mSeriesDetailBean.getData().getTitle());
        tvCountFollow.setText(String.valueOf(mSeriesDetailBean.getData().getCount_follow()) + "人订阅");
        if (!mSeriesDetailBean.getData().getTag_name().isEmpty()) {
            tvTagName.setText("类型 : " + mSeriesDetailBean.getData().getTag_name());
        } else {
            tvTagName.setText("类型 :");
        }
        tvWeekly.setText(mSeriesDetailBean.getData().getWeekly());
        tvUpdate.setText("更新 : " + mSeriesDetailBean.getData().getUpdate_to());
        mTvDetailTitle.setText("第" + mSeriesDetailBean.getData().getPosts().get(0).getList().get(0).getNumber() +
                "集 " + mSeriesDetailBean.getData().getPosts().get(0).getList().get(0).getTitle());
        tvContent.setText(mSeriesDetailBean.getData().getContent());
        tvContentTwo.setText(mSeriesDetailBean.getData().getContent());
        // 可滑动标题
        for (int i = 0; i < mSeriesDetailBean.getData().getPosts().size(); i++) {
            mRadioButton = new RadioButton(this);
            mRadioButton.setId(i);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(5, 15, 5, 15);//4个参数按顺序分别是左上右下
            mRadioButton.setLayoutParams(layoutParams);
            mRadioButton.setButtonDrawable(null);
            // 显示竖线
            if (i == mSeriesDetailBean.getData().getPosts().size() - 1) {
                mRadioButton.setText("      " + mSeriesDetailBean.getData().getPosts().get(i).getFrom_to() + "       ");
            } else {
                mRadioButton.setText("      " + mSeriesDetailBean.getData().getPosts().get(i).getFrom_to() + "      |");
            }

            mRadioButton.setBackgroundResource(R.drawable.selector_one);
            mRadioButton.setTextColor(getResources().getColor(R.color.radio_button_text));
            radioGroup.addView(mRadioButton);


            mRadioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    a = view.getId();
                    mAdapter.setSeriesDetailBean(mSeriesDetailBean, view.getId());
                    mListView.setAdapter(mAdapter);
                }
            });
            mRadioButton.setTextColor(Color.GRAY);
        }


        rlMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rlMore.setVisibility(View.GONE);
                rlIntroduction.setVisibility(View.VISIBLE);
                tvContentTwo.setVisibility(View.GONE);
                tvContent.setVisibility(View.VISIBLE);
            }
        });
        rlIntroduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rlIntroduction.setVisibility(View.GONE);
                rlMore.setVisibility(View.VISIBLE);
                tvContent.setVisibility(View.GONE);
                tvContentTwo.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    protected void initData() {
        /**
         * 获取二级界面 post请求数据
         */
        initPostSeriesDetailData();
        /**
         * listView Item的点击事件
         */
        listViewItemClick();
        /**
         * 返回点击事件
         */
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    /**
     * listView Item的点击事件
     */
    private void listViewItemClick() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mAdapter.showPlay(false, i -1, a);
                b = i - 1;
                // 播放视频
                initVideoPlay();
                mTvDetailTitle.setText("第" + mSeriesDetailBean.getData().getPosts().get(a).getList().get(i - 1).getNumber() + "集 "
                + mSeriesDetailBean.getData().getPosts().get(a).getList().get(i - 1).getTitle());
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 播放视频
     */
    private void initVideoPlay() {

        mSeriesPostId = String.valueOf(mSeriesDetailBean.getData().getPosts().get(a).getList().get(b).getSeries_postid());
        HashMap map = new HashMap();
        map.put("series_postid", mSeriesPostId);
        OkHttpManager.getInstance().post(URLValues.SERIES_VIDEO_URL, SeriesVideoBean.class, new NetCallBack<SeriesVideoBean>() {

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
        OkHttpManager.getInstance().post(URLValues.SERIES_DETAIL_URL, SeriesDetailBean.class, new NetCallBack<SeriesDetailBean>() {
            @Override
            public void onResponse(SeriesDetailBean bean) {
                mSeriesDetailBean = bean;
                /**
                 * 头布局操作
                 */
                initHeadView();
                /**
                 * 这里是垃圾
                 */
                mAdapter = new SeriesDetailAdapter();
                mAdapter.setSeriesDetailBean(bean, 0);
                mListView.setAdapter(mAdapter);
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
