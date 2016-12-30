package com.example.dllo.hodgepodge.video.series.seriesdetail;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.tools.CommonVH;

import java.util.List;

/**
 * 庭
 * Created by Ting on 16/12/23.
 */

public class SeriesDetailAdapter extends BaseAdapter {
    private SeriesDetailBean mSeriesDetailBean;
    private List<SeriesDetailBean.DataBean.PostsBean.ListBean> mList;
    private boolean b = true;// 用来判断视频播放状态
    private int position;
    private int a;
    private int c;// 判断视频播放状态

    public void setSeriesDetailBean(SeriesDetailBean seriesDetailBean, int a) {
        this.a = a;
        mSeriesDetailBean = seriesDetailBean;
        mList = mSeriesDetailBean.getData().getPosts().get(a).getList();
        notifyDataSetChanged();
    }
    // 通过改变b 的boolean 类型, 在对应位置显示正在播放
    public void showPlay(boolean b, int position, int c){
        this.c = c;
        this.b = b;
        this.position = position;
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
        CommonVH commonVH = CommonVH.listViewHolder(view, viewGroup, R.layout.item_series_detail_lv);

        commonVH.setGlideImage(R.id.item_series_detail_iv_thumbnail, mList.get(i).getThumbnail());
        commonVH.setText(R.id.item_series_detail_tv_addtime, mList.get(i).getAddtime());
        commonVH.setText(R.id.item_series_detail_tv_title, "第" + String.valueOf(mList.get(i).getNumber()) + "集 : " + mList.get(i).getTitle());
        commonVH.setText(R.id.item_series_detail_tv_duration, mList.get(i).getDuration());
        if (Integer.parseInt(mList.get(i).getDuration()) % 60 > 9) {
            if (Integer.parseInt(mList.get(i).getDuration()) / 60 > 9) {
                commonVH.setText(R.id.item_series_detail_tv_duration, Integer.parseInt(mList.get(i).getDuration()) / 60 + ":" +
                        Integer.parseInt(mList.get(i).getDuration()) % 60);
            } else {
                commonVH.setText(R.id.item_series_detail_tv_duration, "0" + Integer.parseInt(mList.get(i).getDuration()) / 60 + ":" +
                        Integer.parseInt(mList.get(i).getDuration()) % 60);
            }
        } else {
            if (Integer.parseInt(mList.get(i).getDuration()) / 60 > 9) {
                commonVH.setText(R.id.item_series_detail_tv_duration, Integer.parseInt(mList.get(i).getDuration()) / 60 + ":0" +
                        Integer.parseInt(mList.get(i).getDuration()) % 60);
            } else {
                commonVH.setText(R.id.item_series_detail_tv_duration, "0" + Integer.parseInt(mList.get(i).getDuration()) / 60 + ":0" +
                        Integer.parseInt(mList.get(i).getDuration()) % 60);
            }
        }
        /**
         * 判断视频播放状态 默认开始播放的是最新一集, 先判断播放的视频是否是默认播放状态, 如果是, b为true, 不是, b则为false
         * b 为false的时候, 根据横向position 和纵向position判断
         */
        if (b) {
            if (0 == a &&0 == i) {
                commonVH.getView(R.id.item_series_detail_rl_play).setVisibility(View.VISIBLE);
            } else {
                commonVH.getView(R.id.item_series_detail_rl_play).setVisibility(View.GONE);
            }
        } else {
            if (a == c &&i == position){
                commonVH.getView(R.id.item_series_detail_rl_play).setVisibility(View.VISIBLE);
            } else {
                commonVH.getView(R.id.item_series_detail_rl_play).setVisibility(View.GONE);
            }
        }
        return commonVH.getItemView();
    }

}
