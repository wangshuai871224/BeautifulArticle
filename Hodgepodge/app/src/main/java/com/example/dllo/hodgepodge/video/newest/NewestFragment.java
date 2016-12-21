package com.example.dllo.hodgepodge.video.newest;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseFragment;
import com.example.dllo.hodgepodge.listener.NetCallBack;
import com.example.dllo.hodgepodge.tools.OkHttpManager;
import com.example.dllo.hodgepodge.tools.URLValues;

import java.util.HashMap;

/**
 * 庭
 * Created by Ting on 16/12/20.
 */

public class NewestFragment extends BaseFragment {

    private ListView mListView;

    @Override
    protected int setLayout() {
        return R.layout.fragment_newest;
    }

    @Override
    protected void initView() {
        mListView = bindView(R.id.fragment_newest_lv);
//        View view =
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
        HashMap<String, String> map = new HashMap<>();
        map.put("p", "1");
        map.put("size", "10");

        OkHttpManager.getInstance().post(URLValues.NEWEST_URL, NewestBean.class, new NetCallBack<NewestBean>() {
            @Override
            public void onResponse(NewestBean bean) {
                FmNewestAdapter adapter = new FmNewestAdapter();
                adapter.setNewestBean(bean);
                mListView.setAdapter(adapter);

            }

            @Override
            public void onError(Exception e) {
            }
        }, map);

    }
}
