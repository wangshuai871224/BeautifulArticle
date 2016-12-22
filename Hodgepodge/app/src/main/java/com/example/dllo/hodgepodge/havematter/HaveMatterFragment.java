package com.example.dllo.hodgepodge.havematter;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TableLayout;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseFragment;
import com.example.dllo.hodgepodge.tools.URLValues;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by TaiF on 16/12/19.
 */

public class HaveMatterFragment extends BaseFragment {
    private TabLayout tbHaveMatter;
    private ViewPager vpHaveMatter;
    private OkHttpClient mOkHttpClient;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private BeanTab mBean;
    private HaveMatterAdapter mMAdapter;
    private Request mRequest;

    @Override
    protected int setLayout() {
        return R.layout.fragment_havematter;
    }

    @Override
    protected void initView() {
        tbHaveMatter = bindView(R.id.tb_havematter);
        vpHaveMatter = bindView(R.id.vp_havematter);
    }

    @Override
    protected void initData() {
        mOkHttpClient = new OkHttpClient();
        mMAdapter = new HaveMatterAdapter(getChildFragmentManager());
        mRequest = new Request.Builder().url(URLValues.TAB_URL).build();
        mOkHttpClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Gson gson = new Gson();
                String result = response.body().string();
                mBean = gson.fromJson(result, BeanTab.class);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        mMAdapter.setBean(mBean);
                        vpHaveMatter.setAdapter(mMAdapter);
                        tbHaveMatter.setupWithViewPager(vpHaveMatter);
                        tbHaveMatter.setTabMode(TabLayout.MODE_SCROLLABLE);
                        tbHaveMatter.setTabTextColors(Color.GRAY,Color.WHITE);
                        tbHaveMatter.setSelectedTabIndicatorColor(Color.WHITE);
                    }
                });

            }
        });

    }


}
