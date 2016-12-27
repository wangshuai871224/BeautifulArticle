package com.example.dllo.hodgepodge.mine.mypictorial;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.pictorial.CollectBean;
import com.example.dllo.hodgepodge.pictorial.PictorialItemBean;
import com.example.dllo.hodgepodge.tools.CommonVH;

import java.util.List;

/**
 * Created by shuaiwang on 16/12/26.
 */
public class CollectAdapter extends BaseAdapter{

    private List<CollectBean> mList;

    public void setList(List<CollectBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CommonVH commonVH = CommonVH.listViewHolder(view, viewGroup, R.layout.item_collect);
        commonVH.setText(R.id.item_collect_title, mList.get(i).getTitle());
        commonVH.setText(R.id.item_collect_sub_title, mList.get(i).getSubTitle());
        commonVH.setGlideImage(R.id.item_collect_image, mList.get(i).getImageUrl());
        return commonVH.getItemView();
    }
}
