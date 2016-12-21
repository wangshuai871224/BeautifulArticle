package com.example.dllo.hodgepodge.pictorial;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.tools.CommonVH;

import java.util.List;


/**
 * Created by shuaiwang on 16/12/21.
 */

public class PictorialAdapter extends RecyclerView.Adapter{

    private PictorialBean mBean;
    private List<PictorialBean.DataBean.ArticlesBean> mList;


    public void setBean(PictorialBean bean) {
        mBean = bean;
        mList = mBean.getData().getArticles();
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonVH commonVH = CommonVH.recyclerViewHolder(parent, R.layout.recents_dummy);
        return commonVH;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CommonVH commonVH = (CommonVH) holder;
        commonVH.setText(R.id.user_name, mList.get(position).getAuthor().getUsername());
        commonVH.setText(R.id.pictorial_title, mList.get(position).getTitle());
        commonVH.setText(R.id.pictorial_subtitle, mList.get(position).getSub_title());

    }

    @Override
    public int getItemCount() {
        return mBean.getData().getArticles().size();
    }
}
