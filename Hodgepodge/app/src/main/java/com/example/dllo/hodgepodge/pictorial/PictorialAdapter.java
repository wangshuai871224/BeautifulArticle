package com.example.dllo.hodgepodge.pictorial;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.mine.mypictorial.PictorialActivity;
import com.example.dllo.hodgepodge.tools.CommonVH;
import com.example.dllo.hodgepodge.tools.URLValues;
import com.wirelesspienetwork.overview.model.OverviewAdapter;
import com.wirelesspienetwork.overview.model.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;


/**
 * Created by shuaiwang on 16/12/21.
 */

// 适配器的参数, ViewHolder是自定义的, 单是需要继承OverViewAdapter
public class PictorialAdapter extends OverviewAdapter<PictorialAdapter.CustomViewHolder,Integer>{

    private PictorialBean mBean;
    private Context mContext;
    private List<PictorialBean.DataBean.ArticlesBean> mBeanList;

    public PictorialAdapter(Context context) {
        mContext = context;
    }

    public PictorialAdapter(List<Integer> integers, Context context) {
        super(integers);
        mContext = context;
    }

    public void setBean(PictorialBean bean) {
        mBean = bean;
        mBeanList = mBean.getData().getArticles();

    }

    @Override
    public CustomViewHolder onCreateViewHolder(Context context, ViewGroup parent) {
        View view = View.inflate(context, R.layout.recents_dummy, null);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder viewHolder) {
        viewHolder.title.setText(mBeanList.get(viewHolder.getPosition()).getTitle());
        viewHolder.subtitle.setText(mBeanList.get(viewHolder.getPosition()).getSub_title());
        viewHolder.userName.setText(mBeanList.get(viewHolder.getPosition()).getAuthor().getUsername());

        Glide.with(mContext).load(mBeanList
                .get(viewHolder.getPosition()).getImage_url())
                .into(viewHolder.pictorialImage);
        Glide.with(mContext).load(mBeanList
                .get(viewHolder.getPosition()).getAuthor().getAvatar_url())
                .bitmapTransform(new CropCircleTransformation(mContext))
                .into(viewHolder.avatarImage);
        // item的点击事件
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = mBeanList.get(viewHolder.getPosition()).getId();
                String itemUrl = URLValues.PICTORIAL_ITEM_BEFORE + id + URLValues.PICTORIAL_ITEM_AFTER;
                Intent intent = new Intent(mContext, PictorialItemActivity.class);
                intent.putExtra("itemUrl", itemUrl);
                mContext.startActivity(intent);
            }
        });
    }



    class CustomViewHolder extends ViewHolder{

        private TextView userName, subtitle, title;
        private ImageView pictorialImage, avatarImage;

        public CustomViewHolder(View view) {
            super(view);

            userName = (TextView) view.findViewById(R.id.user_name);
            subtitle = (TextView) view.findViewById(R.id.pictorial_subtitle);
            title = (TextView) view.findViewById(R.id.pictorial_title);
            pictorialImage = (ImageView) view.findViewById(R.id.pictorial_image);
            avatarImage = (ImageView) view.findViewById(R.id.avatar_image);
        }
    }

}
