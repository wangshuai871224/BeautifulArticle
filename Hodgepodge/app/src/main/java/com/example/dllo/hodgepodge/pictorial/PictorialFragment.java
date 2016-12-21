package com.example.dllo.hodgepodge.pictorial;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseFragment;
import com.example.dllo.hodgepodge.listener.NetCallBack;
import com.example.dllo.hodgepodge.tools.CommonVH;
import com.example.dllo.hodgepodge.tools.OkHttpManager;
import com.example.dllo.hodgepodge.tools.URLValues;
import com.google.gson.Gson;
import com.wirelesspienetwork.overview.misc.Utilities;
import com.wirelesspienetwork.overview.model.OverviewAdapter;
import com.wirelesspienetwork.overview.model.ViewHolder;
import com.wirelesspienetwork.overview.views.Overview;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dllo on 16/12/19.
 */

// 画报
public class PictorialFragment extends BaseFragment implements Overview.RecentsViewCallbacks{

    private boolean mVisible;
    // Top level views
    private Overview mRecentView;

    @Override
    protected int setLayout() {
        return R.layout.fragment_pictorial;
    }

    @Override
    protected void initView() {
        mRecentView = bindView(R.id.recent_view);

    }

    @Override
    protected void initData() {
        mRecentView.setCallbacks(this);
        // 如果启用, 超出屏幕
//        mRecentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
//                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
//                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        try {
            Utilities.setShadowProperty("setProperty", String.valueOf(1.5f));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        OkHttpManager.getInstance().get(URLValues.PICTORIAL_URL, PictorialBean.class, new NetCallBack<PictorialBean>() {
            @Override
            public void onResponse(PictorialBean bean) {

            }

            @Override
            public void onError(Exception e) {

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

        // Mark Recents as visible
        mVisible = true;

        ArrayList<Integer> models = new ArrayList<>();
        for(int i = 0; i < 10; ++i)
        {
            Random random = new Random();
            random.setSeed(i);
            models.add(0xffffffff);
        }
        


        final OverviewAdapter stack = new OverviewAdapter<ViewHolder<View, Integer>, Integer>(models) {
            @Override
            public ViewHolder onCreateViewHolder(Context context, ViewGroup parent) {
                View v = View.inflate(context, R.layout.recents_dummy, null);
                return new ViewHolder<View, Integer>(v);
            }

            @Override
            public void onBindViewHolder(ViewHolder<View, Integer> viewHolder) {
                viewHolder.itemView.setBackgroundColor(viewHolder.model);

            }
        };

        mRecentView.setTaskStack(stack);

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCardDismissed(int position) {

    }

    @Override
    public void onAllCardsDismissed() {

    }
}
