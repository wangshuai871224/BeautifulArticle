package com.example.dllo.hodgepodge.video.series.seriesdetail;

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
    private List<SeriesDetailBean.DataBean.PostsBean> mList;

    public void setSeriesDetailBean(SeriesDetailBean seriesDetailBean) {
        mSeriesDetailBean = seriesDetailBean;
        mList = mSeriesDetailBean.getData().getPosts();
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
        CommonVH commonVH = CommonVH.listViewHolder(view, viewGroup, R.layout.item_series_detail_lv);
        for (int j = 0; j < mList.get(0).getList().size(); j++) {
            commonVH.setGlideImage(R.id.item_series_detail_iv_thumbnail, mList.get(0).getList().get(j).getThumbnail());
            commonVH.setText(R.id.item_series_detail_tv_addtime, mList.get(0).getList().get(j).getAddtime());
            commonVH.setText(R.id.item_series_detail_tv_title, mList.get(0).getList().get(j).getTitle());
            commonVH.setText(R.id.item_series_detail_tv_duration, mList.get(0).getList().get(j).getDuration());
        }
        if (0 == i){
            commonVH.getView(R.id.item_series_detail_rl_play).setVisibility(View.VISIBLE);
        } else {
            commonVH.getView(R.id.item_series_detail_rl_play).setVisibility(View.GONE);
        }
        return commonVH.getItemView();
    }
}
