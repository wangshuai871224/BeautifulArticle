package com.example.dllo.hodgepodge.mine.mypictorial;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseFragment;
import com.example.dllo.hodgepodge.pictorial.CollectBean;
import com.example.dllo.hodgepodge.pictorial.PictorialItemActivity;
import com.example.dllo.hodgepodge.pictorial.PictorialItemBean;
import com.example.dllo.hodgepodge.tools.LiteOrmTool;

import java.util.List;

/**
 * Created by shuaiwang on 16/12/26.
 */
public class CollectFragment extends BaseFragment {

    private List<CollectBean> mList;
    private ListView mListView;
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
                intent.putExtra("itemUrl", mList.get(i).getItemUrl());
                getActivity().startActivity(intent);
            }
        });
    }
}
