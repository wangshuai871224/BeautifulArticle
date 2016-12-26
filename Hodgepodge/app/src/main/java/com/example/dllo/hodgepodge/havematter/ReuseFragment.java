package com.example.dllo.hodgepodge.havematter;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
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
import java.util.List;

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
    private TextView tvAll,tvChoose;
    private RecyclerView rvPopup;
    private PopupAdapter mAdapter1;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private BeanMatterPop mBean1;
    private static List<BeanTab.DataBean.CategoriesBean.SubCategoriesBean> list;

    @Override
    protected int setLayout() {
        return R.layout.fragment_reuse;
    }

    @Override
    protected void initView() {
        tvAll = bindView(R.id.tv_all);
        llPopup = bindView(R.id.ll_popup);
        lvHaveMatter = bindView(R.id.lv_havematter);
        setClick(this,llPopup);

    }

    @Override
    protected void initData() {
        tvAll.setText("全部");
        mAdapter = new ReuseAdapter(getContext());
        mOkHttpClient = new OkHttpClient();
        getPOP();

        initList();

        initPopup();

    }

    private void initList() {
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
    }

    private void getPOP() {
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
                mBean1 = gson.fromJson(result, BeanMatterPop.class);
            }
        });
    }

    private void initPopup() {
        mWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_popup, null);
        mWindow.setContentView(view);
        rvPopup = (RecyclerView) view.findViewById(R.id.rv_popup);
        tvChoose = (TextView) view.findViewById(R.id.tv_choose);

        mAdapter1 = new PopupAdapter(getActivity());
        mAdapter1.setBean(list);
        rvPopup.setAdapter(mAdapter1);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        rvPopup.setLayoutManager(manager);
        mWindow.setBackgroundDrawable(new BitmapDrawable());
        mWindow.setOutsideTouchable(true);
    }

    public static ReuseFragment newInstance(int pos) {
        Bundle bundle = new Bundle();
        String message = HaveMatterAdapter.getMessage(pos);
        list = HaveMatterAdapter.getTabPOP(pos - 1);
        bundle.putString("key", message);
        ReuseFragment fragment = new ReuseFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_popup:
                if (mWindow == null || !mWindow.isShowing()) {
                    mWindow.showAtLocation(view, Gravity.TOP,0, 210);
                } else {
                    mWindow.dismiss();
                }
                break;
        }
    }
}
