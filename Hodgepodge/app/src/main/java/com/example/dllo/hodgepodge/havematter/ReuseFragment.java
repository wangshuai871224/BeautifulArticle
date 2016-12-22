package com.example.dllo.hodgepodge.havematter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ListView;

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
 * Created by TaiF on 16/12/20.
 */

public class ReuseFragment extends BaseFragment {
    private ListView lvHaveMatter;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private OkHttpClient mOkHttpClient;
    private ReuseAdapter mAdapter;
    private BeanCategorises mBean;

    @Override
    protected int setLayout() {
        return R.layout.fragment_reuse;
    }

    @Override
    protected void initView() {
        lvHaveMatter = bindView(R.id.lv_havematter);
    }

    @Override
    protected void initData() {
        Log.d("ReuseFragment", "--" + 122773);
        mAdapter = new ReuseAdapter(getContext());
        mOkHttpClient = new OkHttpClient();
        Bundle bundle = getArguments();
        String msg = bundle.get("key").toString();
        String url = URLValues.JEWELRY_BEFORE + msg + URLValues.JEWELRY_AFTER;
        Log.d("ReuseFragment", url);
        Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.d("ReuseFragment", "--" + 13);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                String result = response.body().string();
                mBean = gson.fromJson(result,BeanCategorises.class);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("ReuseFragment", "--" + 1223);
                        mAdapter.setBean(mBean);
                        lvHaveMatter.setAdapter(mAdapter);
                    }
                });
            }
        });
    }

    public static ReuseFragment newInstance(int pos) {
        Bundle bundle = new Bundle();
        String message = HaveMatterAdapter.getMessage(pos);
        bundle.putString("key", message);
        ReuseFragment fragment = new ReuseFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
