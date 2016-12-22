package com.example.dllo.hodgepodge.havematter;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

public class ReuseFragment extends BaseFragment implements View.OnClickListener {
    private ListView lvHaveMatter;
    private RelativeLayout llPopup;
    private OkHttpClient mOkHttpClient;
    private ReuseAdapter mAdapter;
    private BeanCategorises mBean;
    private PopupWindow mWindow;
    private TextView tvAll;
    private ImageView ivDown, ivUpward;
    private RecyclerView rvPopup;
    private PopupAdapter mAdapter1;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected int setLayout() {
        return R.layout.fragment_reuse;
    }

    @Override
    protected void initView() {
        tvAll = bindView(R.id.tv_all);
        llPopup = bindView(R.id.ll_popup);
        ivDown = bindView(R.id.iv_down);
        ivUpward = bindView(R.id.iv_upward);
        lvHaveMatter = bindView(R.id.lv_havematter);
        setClick(this, llPopup, ivUpward);
    }

    @Override
    protected void initData() {
        mAdapter = new ReuseAdapter(getContext());
        mOkHttpClient = new OkHttpClient();
        Bundle bundle = getArguments();
        String msg = bundle.get("key").toString();
        String url = URLValues.JEWELRY_BEFORE + msg + URLValues.JEWELRY_AFTER;
        Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                String result = response.body().string();
                mBean = gson.fromJson(result, BeanCategorises.class);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setBean(mBean);
                        lvHaveMatter.setAdapter(mAdapter);
                    }
                });
            }
        });

        initPopup();
    }

    private void initPopup() {
        mWindow = new PopupWindow(llPopup, ViewGroup.LayoutParams.MATCH_PARENT, 300, true);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_popup, null);
        mWindow.setContentView(view);
        rvPopup = (RecyclerView) view.findViewById(R.id.rv_popup);
        mAdapter1 = new PopupAdapter(getContext());
        mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(URLValues.MATTERPOP_URL).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                String result = response.body().string();
                BeanMatterPop bean = gson.fromJson(result,BeanMatterPop.class);
                mAdapter1.setBean(bean);
                rvPopup.setAdapter(mAdapter1);
                GridLayoutManager manager = new GridLayoutManager(getContext(),3);
                rvPopup.setLayoutManager(manager);

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_popup:
                tvAll.setVisibility(View.INVISIBLE);
                ivDown.setVisibility(View.INVISIBLE);
                ivUpward.setVisibility(View.VISIBLE);
                if (mWindow == null || !mWindow.isShowing()) {
                    popupClick();
                }
                break;
        }
    }

    private void popupClick() {
        mWindow.setBackgroundDrawable(new BitmapDrawable());
        mWindow.showAsDropDown(llPopup, 0, 0);
    }
}
