package com.example.dllo.hodgepodge.video.newest;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.tools.CommonVH;

import java.util.List;

/**
 * 庭
 * Created by Ting on 16/12/20.
 */

public class FmNewestAdapter extends BaseAdapter {

    private NewestBean mNewestBean;
    private List<NewestBean.DataBean> mList;
    private boolean flag = false;

    public void setDown(boolean flag){
        this.flag = flag;
    }
    public void setNewestBean(NewestBean newestBean) {
        if (flag){
            // 加载完成后, 把数据加到集合里
            mNewestBean.getData().addAll(newestBean.getData());
            mList = mNewestBean.getData();
        } else {
            mNewestBean = newestBean;
            mList = mNewestBean.getData();
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList != null && mList.size() > 0 ? mList.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return mList != null && mList.size() > 0 ? mList.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CommonVH commonVH = CommonVH.listViewHolder(view, viewGroup, R.layout.item_newest_lv);
        commonVH.setText(R.id.item_newest_title, mList.get(i).getTitle());
        if (Integer.parseInt(mList.get(i).getDuration()) % 60 > 9) {
            commonVH.setText(R.id.item_newest_catename, mList.get(i).getCates().get(0).getCatename() + " / " +
                    Integer.parseInt(mList.get(i).getDuration()) / 60 + "′" +
                    Integer.parseInt(mList.get(i).getDuration()) % 60 + "″");
        } else {
            commonVH.setText(R.id.item_newest_catename, mList.get(i).getCates().get(0).getCatename() + " / " +
                    Integer.parseInt(mList.get(i).getDuration()) / 60 + "′0" +
                    Integer.parseInt(mList.get(i).getDuration()) % 60 + "″");
        }
        commonVH.setGlideImage(R.id.item_newest_image, mList.get(i).getImage());
        return commonVH.getItemView();
    }
}
