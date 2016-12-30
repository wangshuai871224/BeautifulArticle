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
import android.widget.Toast;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseFragment;
import com.example.dllo.hodgepodge.tools.MyApp;
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

public class ReuseFragment extends BaseFragment implements View.OnClickListener, PopupClick {
    private ListView lvHaveMatter;
    private static RelativeLayout rlPopup;
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
    private static int c;


    @Override
    protected int setLayout() {
        return R.layout.fragment_reuse;
    }

    @Override
    protected void initView() {
        tvAll = bindView(R.id.tv_all);
        rlPopup = bindView(R.id.rl_popup);
        lvHaveMatter = bindView(R.id.lv_havematter);
        setClick(this,rlPopup);

    }

    @Override
    protected void initData() {
        tvAll.setText("全部");
        mAdapter = new ReuseAdapter(getContext());
        mOkHttpClient = new OkHttpClient();

        initList();

        initPopup();


    }

    private void initList() {
        Bundle bundle = getArguments();
        String msg = bundle.get("key").toString();
        String mUrl = URLValues.JEWELRY_BEFORE + msg + URLValues.JEWELRY_AFTER;
        Log.d("ReuseFragment", mUrl + "-----");

        Request request = new Request.Builder().url(mUrl).build();
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



    private void initPopup() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_popup, null);
        mWindow = new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        mWindow.setFocusable(true);
        mWindow.setBackgroundDrawable(new BitmapDrawable());
        rvPopup = (RecyclerView) view.findViewById(R.id.rv_popup);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        rvPopup.setLayoutManager(manager);
        mAdapter1 = new PopupAdapter(getActivity());
        tvChoose = (TextView) view.findViewById(R.id.tv_choose);

//        if (list != null && list.size() > 0){
//
            mAdapter1.setBean(list);
//            rlPopup.setVisibility(View.VISIBLE);
//        }else {
//            rlPopup.setVisibility(View.GONE);
//        }

        rvPopup.setAdapter(mAdapter1);

        mAdapter1.setPopup(this);
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        mAdapter1 = new PopupAdapter(getActivity());
//        mAdapter1.setBean(list);
//        if (rlPopup != null && list != null && list.size() > 0){
//            Log.d("show", "显示");
//            Log.d("show", list.get(c-1).getName());
//            rlPopup.setVisibility(View.VISIBLE);
//        }else if(rlPopup != null ) {
//            Log.d("show", "不显示");
//            rlPopup.setVisibility(View.GONE);
//        }
        if (c == 5){
            rlPopup.setVisibility(View.GONE);
        }
    }



    public static ReuseFragment newInstance(int pos) {

        c = pos;
        Log.d("ReuseFragment", "---" + c);
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
            case R.id.rl_popup:
                if (mWindow == null || !mWindow.isShowing()) {
                    mWindow.showAtLocation(view, Gravity.TOP,0, 200);
                } else {
                    mWindow.dismiss();
                }
                break;
        }
    }

    @Override
    public void getPosition(int pos) {
        mWindow.dismiss();

        mAdapter1.getPosition(pos);
        mAdapter1.notifyDataSetChanged();
//        if (pos > 0 ) {
//            tvAll.setText(mBean1.getData().getCategories().get(c - 1).getSub_categories().get(pos - 1).getName());
//        }
    }
}
