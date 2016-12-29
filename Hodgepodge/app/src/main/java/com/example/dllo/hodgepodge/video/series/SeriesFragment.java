package com.example.dllo.hodgepodge.video.series;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseFragment;
import com.example.dllo.hodgepodge.listener.NetCallBack;
import com.example.dllo.hodgepodge.tools.OkHttpManager;
import com.example.dllo.hodgepodge.tools.URLValues;
import com.example.dllo.hodgepodge.video.series.seriesdetail.SeriesActivity;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.HashMap;

/**
 * 庭
 * Created by Ting on 16/12/20.
 */

public class SeriesFragment extends BaseFragment {

    private PullToRefreshListView mListView;
    private int a = 1;
    private FmSeriesAdapter mAdapter;
    private SeriesBean mSeriesBean;

    @Override
    protected int setLayout() {
        return R.layout.fragment_series;
    }

    @Override
    protected void initView() {
        mListView = bindView(R.id.fragment_series_lv);
    }

    @Override
    protected void initData() {
        /**
         * 获取listView 的数据
         */
        initLvData();
        /**
         * 显示刷新动画
         */
        initRefreshAnimation();
        /**
         * 上拉加载, 下拉刷新
         */
        initRefreshData();
        /**
         * listView item 点击事件
         */
        listViewItemClick();
    }

    /**
     * listView item 点击事件
     */
    private void listViewItemClick() {
       mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Intent intent = new Intent(getContext(), SeriesActivity.class);
               intent.putExtra("seriesId", String.valueOf(mSeriesBean.getData().get(i -1).getSeriesid()));
               startActivity(intent);
           }
       });
    }

    /**
     * 获取listView 的数据
     */
    private void initLvData() {
        a = 1;
        HashMap map = new HashMap();
        map.put("p", a + "" );
        OkHttpManager.getInstance().post(URLValues.SERIES_URL, SeriesBean.class, new NetCallBack<SeriesBean>() {
            @Override
            public void onResponse(SeriesBean bean) {
                mSeriesBean = bean;
                mAdapter = new FmSeriesAdapter();
                mAdapter.setSeriesBean(bean);
                mListView.setAdapter(mAdapter);
            }

            @Override
            public void onError(Exception e) {

            }
        }, map);
    }
    /**
     * 上拉加载, 下拉刷新
     */
    private void initRefreshData() {
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            // 下拉刷新
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 加载完成, 刷新UI
                        mListView.onRefreshComplete();
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
                OkHttpManager.getInstance().post(URLValues.SERIES_URL, SeriesBean.class, new NetCallBack<SeriesBean>() {
                    @Override
                    public void onResponse(SeriesBean bean) {
                        mAdapter.setDown(true);
                        mAdapter.setSeriesBean(bean);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                }, map);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 加载完成, 刷新UI
                        mListView.onRefreshComplete();
                    }
                }, 1000);

            }

        });
    }

    /**
     * 显示刷新动画
     */
    private void initRefreshAnimation() {
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载...");
        mListView.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在拼命加载...");
        mListView.getLoadingLayoutProxy(false, true).setReleaseLabel("松开加载更多...");

        mListView.getLoadingLayoutProxy(true, false).setPullLabel("下拉刷新...");
        mListView.getLoadingLayoutProxy(true, false).setRefreshingLabel("正在拼命刷新数据...");
        mListView.getLoadingLayoutProxy(true, false).setReleaseLabel("松开加载更多...");
    }
    /**
     * 获取listView 刷新的数据
     */
    private void initPullDownToRefreshData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("p", "1");
        map.put("size", "10");

        OkHttpManager.getInstance().post(URLValues.SERIES_URL, SeriesBean.class, new NetCallBack<SeriesBean>() {
            @Override
            public void onResponse(SeriesBean bean) {
                mAdapter.setSeriesBean(bean);
            }

            @Override
            public void onError(Exception e) {

            }
        }, map);
    }
}
