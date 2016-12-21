package com.example.dllo.hodgepodge.havematter;

import android.os.Handler;
import android.os.Looper;
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
public class DailyFragment extends BaseFragment {
    private ListView lvDaily;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private OkHttpClient mOkHttpClient;
    private DailyAdapter mAdapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_daily;
    }

    @Override
    protected void initView() {
        lvDaily = bindView(R.id.lv_daily);
    }

    @Override
    protected void initData() {
        mOkHttpClient = new OkHttpClient();
        mAdapter = new DailyAdapter(getContext());
        final Request request = new Request.Builder().url(URLValues.DAILY_URL).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                String result = response.body().string();
                final BeanDaily bean = gson.fromJson(result, BeanDaily.class);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                    mAdapter.setBean(bean);
                        lvDaily.setAdapter(mAdapter);
                    }
                });
            }

        });
    }
}
