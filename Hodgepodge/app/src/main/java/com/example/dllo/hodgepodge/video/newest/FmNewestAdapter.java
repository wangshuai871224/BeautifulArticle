package com.example.dllo.hodgepodge.video.newest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.tools.CommonVH;

/**
 * 庭
 * Created by Ting on 16/12/20.
 */

public class FmNewestAdapter extends BaseAdapter {
    private Context mContext;
    private NewestBean mNewestBean;

    @Override
    public int getCount() {
        return mNewestBean != null && mNewestBean.getData().size() > 0 ? mNewestBean.getData().size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return mNewestBean != null && mNewestBean.getData().size() > 0 ? mNewestBean.getData().get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CommonVH commonVH = CommonVH.listViewHolder(view, viewGroup, R.layout.item_newest_lv);
        commonVH.setText(R.id.item_newest, "woshisehn");
        return commonVH.getItemView();
    }
}
