package com.example.dllo.hodgepodge.video.series;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.tools.CommonVH;

import java.util.List;

/**
 * 庭
 * Created by Ting on 16/12/22.
 */

public class FmSeriesAdapter extends BaseAdapter {

    private SeriesBean mSeriesBean;
    private List<SeriesBean.DataBean> mList;
    private boolean flag = false;
    private boolean subscription = false;

    /**
     * 上拉加载, 刷新数据
     * @param b
     */
    public void setDown(boolean b){
        this.flag = b;
    }
    public void setSeriesBean(SeriesBean seriesBean) {
        if (flag) {
            mSeriesBean.getData().addAll(seriesBean.getData());
            mList = mSeriesBean.getData();
            notifyDataSetChanged();
        } else {
            mSeriesBean = seriesBean;
            mList = mSeriesBean.getData();
            notifyDataSetChanged();
        }
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
        CommonVH commonVH = CommonVH.listViewHolder(view, viewGroup, R.layout.item_series_lv);
        commonVH.setText(R.id.item_series_tv_title, mList.get(i).getTitle());
        commonVH.setText(R.id.item_series_tv_update_to, "以更新至" + mList.get(i).getUpdate_to() + "集");
        commonVH.setText(R.id.item_series_tv_follower_num, mList.get(i).getFollower_num() + "人以订阅");
        commonVH.setText(R.id.item_series_tv_content, mList.get(i).getContent());
        commonVH.setGlideImage(R.id.item_series_iv_image, mList.get(i).getImage());
        // 关注点击事件
        commonVH.getView(R.id.item_series_iv_attention).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return commonVH.getItemView();
    }
    /**
     * 关注的点击事件
     */
    public void subscriptionClick(boolean b){
        this.subscription = b;
    }
}
