package com.example.dllo.hodgepodge.video.newest;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseFragment;
import com.example.dllo.hodgepodge.listener.NetCallBack;
import com.example.dllo.hodgepodge.tools.OkHttpManager;
import com.example.dllo.hodgepodge.tools.URLValues;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 庭
 * Created by Ting on 16/12/20.
 */

public class NewestFragment extends BaseFragment {

    private PullToRefreshListView mRefreshListView;
    private ListView mListView;
    private Banner mBanner;
    private List<String> mImage;
    private int a = 1;
    private FmNewestAdapter mAdapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_newest;
    }

    @Override
    protected void initView() {
        mRefreshListView = bindView(R.id.fragment_newest_lv);
        // 由于PullToRefreshListView没有直接添加头布局的方法，所以我们需要用refreshListView.getRefreshableView()
        // 把refreshListView 转化成正常的listView，转化后的listView才可以添加头布局
        mListView = mRefreshListView.getRefreshableView();
        View headView = LayoutInflater.from(getContext()).inflate(R.layout.newest_headview, null);
        mBanner = (Banner) headView.findViewById(R.id.newest_headview_banner);
        mListView.addHeaderView(headView);
    }

    @Override
    protected void initData() {
        /**
         * 获取listView 的数据
         */
        initLvData();
        /**
         * 获取轮播图数据
         */
        initRunData();
        /**
         * 显示刷新动画
         */
        initRefreshAnimation();
        /**
         * 上拉加载, 下拉刷新
         */
        initRefreshData();
    }

    /**
     * 上拉加载, 下拉刷新
     */
    private void initRefreshData() {
        mRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            // 下拉刷新
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                initPullDownToRefreshData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 加载完成, 刷新UI
                        mRefreshListView.onRefreshComplete();
                    }
                }, 1000);
            }
            // 上拉加载
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                a += 1;
                HashMap map = new HashMap();
                map.put("p", a + "");
                map.put("size", "10");
                OkHttpManager.getInstance().post(URLValues.NEWEST_URL, NewestBean.class, new NetCallBack<NewestBean>() {
                    @Override
                    public void onResponse(NewestBean bean) {
                        mAdapter.setDown(true);
                        mAdapter.setNewestBean(bean);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                }, map);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 加载完成, 刷新UI
                        mRefreshListView.onRefreshComplete();
                    }
                }, 1000);

            }

        });
    }

    /**
     * 显示刷新动画
     */
    private void initRefreshAnimation() {
        mRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mRefreshListView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载...");
        mRefreshListView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在拼命加载...");
        mRefreshListView.getLoadingLayoutProxy(false, true).setReleaseLabel("松开加载更多...");

        mRefreshListView.getLoadingLayoutProxy(true, false).setPullLabel("下拉刷新...");
        mRefreshListView.getLoadingLayoutProxy(true, false).setRefreshingLabel("正在拼命加载...");
        mRefreshListView.getLoadingLayoutProxy(true, false).setReleaseLabel("松开加载更多...");
    }

    /**
     * 获取轮播图数据
     */
    private void initRunData() {

        OkHttpManager.getInstance().get(URLValues.NEWEST_BANNER_URL,
                NewestRunBean.class, new NetCallBack<NewestRunBean>() {
            @Override
            public void onResponse(NewestRunBean bean) {
                mImage = new ArrayList<String>();
                for (int i = 0; i < bean.getData().size(); i++) {
                    mImage.add(bean.getData().get(i).getImage());
                }
            setBannerData();
            }
            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void setBannerData() {
        // 设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        // 设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        // 设置图片集合
        mBanner.setImages(mImage);
        // 设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        // 设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        // 设置轮播时间
        mBanner.setDelayTime(2000);
        // 设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        // banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    /**
     * 获取listView 刷新的数据
     */
    private void initPullDownToRefreshData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("p", "1");
        map.put("size", "10");

        OkHttpManager.getInstance().post(URLValues.NEWEST_URL, NewestBean.class, new NetCallBack<NewestBean>() {
            @Override
            public void onResponse(NewestBean bean) {
                mAdapter = new FmNewestAdapter();
                mAdapter.setNewestBean(bean);
            }

            @Override
            public void onError(Exception e) {
            }
        }, map);
    }
    /**
     * 获取listView 的数据
     */
    private void initLvData() {
        // a = 1 防止离开这个界面之后, 再次加载数据时a 的值不是1
        a = 1;
        HashMap<String, String> map = new HashMap<>();
        map.put("p", "1");
        map.put("size", "10");

        OkHttpManager.getInstance().post(URLValues.NEWEST_URL, NewestBean.class, new NetCallBack<NewestBean>() {
            @Override
            public void onResponse(NewestBean bean) {
                mAdapter = new FmNewestAdapter();
                mAdapter.setNewestBean(bean);
                mRefreshListView.setAdapter(mAdapter);

            }

            @Override
            public void onError(Exception e) {
            }
        }, map);
    }
}
