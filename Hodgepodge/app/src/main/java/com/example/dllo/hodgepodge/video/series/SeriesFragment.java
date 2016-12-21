package com.example.dllo.hodgepodge.video.series;

import android.widget.ListView;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseFragment;

/**
 * 庭
 * Created by Ting on 16/12/20.
 */

public class SeriesFragment extends BaseFragment {

    private ListView mListView;

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
    }

    /**
     * 获取listView 的数据
     */
    private void initLvData() {

    }
}
