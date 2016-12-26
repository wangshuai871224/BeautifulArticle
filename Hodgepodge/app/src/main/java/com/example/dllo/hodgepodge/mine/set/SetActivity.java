package com.example.dllo.hodgepodge.mine.set;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseActivity;
import com.example.dllo.hodgepodge.widget.DataCleanManager;
import com.example.dllo.hodgepodge.tools.MyApp;
import com.example.dllo.hodgepodge.widget.SwipeBackLayout;


/**
 * Created by shuaiwang on 16/12/22.
 * 从左侧滑,退出当前页面
 */
public class SetActivity extends BaseActivity implements View.OnClickListener{

    private TextView cacheSize;
    private RelativeLayout clean;
    private SwipeBackLayout mSwipeBackLayout;
    @Override
    protected int setLayout() {
        return R.layout.activity_set;
    }

    @Override
    protected void initView() {
        mSwipeBackLayout = new SwipeBackLayout(this);
        cacheSize = bindView(R.id.cache_size);
        clean = bindView(R.id.clean_cache);
        setClick(this, clean);
    }

    @Override
    protected void initData() {
        // 获取缓存
        try {
            String cache = DataCleanManager.getTotalCacheSize(this);
            cacheSize.setText(cache);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 退出当前activity
        mSwipeBackLayout.bind();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clean_cache:
                showAlertDialog();
                break;
        }
    }

    private void showAlertDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        View view = LayoutInflater.from(this).inflate(R.layout.dialog, null);
        dialog.setView(view);

        dialog.setNegativeButton("消失", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        // 清除缓存
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DataCleanManager.clearAllCache(MyApp.getContext());
                try {
                    cacheSize.setText(DataCleanManager.getTotalCacheSize(MyApp.getContext()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        dialog.show();
    }
}
