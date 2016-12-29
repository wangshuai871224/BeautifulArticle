package com.example.dllo.hodgepodge.mine.mypictorial;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseFragment;
import com.example.dllo.hodgepodge.pictorial.CollectBean;
import com.example.dllo.hodgepodge.pictorial.PictorialItemActivity;

import com.example.dllo.hodgepodge.tools.LiteOrmTool;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

/**
 * Created by shuaiwang on 16/12/26.
 */
public class CollectFragment extends BaseFragment {

    private List<CollectBean> mList;
    private PullToRefreshListView mListView;
    private CollectAdapter mAdapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_collect;
    }

    @Override
    protected void initView() {
        mListView = bindView(R.id.collect_list);
        mAdapter = new CollectAdapter();
        mList = LiteOrmTool.getmLiteOrm().queryAll(CollectBean.class);
    }

    @Override
    protected void initData() {
        mAdapter.setList(mList);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), PictorialItemActivity.class);
                intent.putExtra("itemUrl", mList.get(i - 1).getItemUrl());
                getActivity().startActivity(intent);
            }
        });

        refresh();

    }

    private void refresh() {
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                // 刷新
                mList = LiteOrmTool.getmLiteOrm().queryAll(CollectBean.class);
                mAdapter.setList(mList);
                mListView.setAdapter(mAdapter);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mListView.onRefreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                // 加载
                Toast.makeText(getActivity(), "数据库不用加载", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
