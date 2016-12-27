package com.example.dllo.hodgepodge.pictorial;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseFragment;
import com.example.dllo.hodgepodge.listener.NetCallBack;
import com.example.dllo.hodgepodge.tools.OkHttpManager;
import com.example.dllo.hodgepodge.tools.URLValues;
import com.wirelesspienetwork.overview.misc.Utilities;
import com.wirelesspienetwork.overview.model.OverviewAdapter;
import com.wirelesspienetwork.overview.model.ViewHolder;
import com.wirelesspienetwork.overview.views.Overview;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by dllo on 16/12/19.
 */

// 画报
public class PictorialFragment extends BaseFragment implements Overview.RecentsViewCallbacks{

    private boolean mVisible;
    private ArrayList<Integer> models;
    // Top level views
    private Overview mRecentView;
    private PictorialAdapter mPictorialAdapter;

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
        // Register the broadcast receiver to handle messages when the screen is turned off

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(SearchManager.INTENT_GLOBAL_SEARCH_ACTIVITY_CHANGED);
        models = new ArrayList<>();

        // Private API calls to make the shadows look better

        try {
            Utilities.setShadowProperty("setProperty", String.valueOf(1.5f));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

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

        // 通过改变适配器来实现画报, 适配器继承OverViewAdapter
        mPictorialAdapter = new PictorialAdapter(models, getContext());
        OkHttpManager.getInstance().get(URLValues.PICTORIAL_URL, PictorialBean.class, new NetCallBack<PictorialBean>() {
            @Override
            public void onResponse(PictorialBean bean) {

                Collections.reverse(bean.getData().getArticles());
                mRecentView.setTaskStack(mPictorialAdapter);
                models.clear();// 防止每次都添加数据,造成数组越界
                for (int i = 0; i < bean.getData().getArticles().size(); i++) {
                    models.add(bean.getData().getArticles().size());
                }
                mPictorialAdapter.setBean(bean);
            }

            @Override
            public void onError(Exception e) {

            }
        });


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
