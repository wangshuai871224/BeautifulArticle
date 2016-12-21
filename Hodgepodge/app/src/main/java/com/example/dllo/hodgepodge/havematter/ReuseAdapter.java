package com.example.dllo.hodgepodge.havematter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.hodgepodge.R;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by TaiF on 16/12/21.
 */
public class ReuseAdapter extends BaseAdapter{
    private BeanCategorises bean;
    private Context mContext;

    public void setBean(BeanCategorises bean) {
        this.bean = bean;
    }

    public ReuseAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return bean == null ? 0 : bean.getData().getProducts().size();
    }

    @Override
    public Object getItem(int i) {
        return bean.getData().getProducts().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ReuseViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_reuse,viewGroup,false);
            holder = new ReuseViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ReuseViewHolder) view.getTag();
        }
        holder.tvBrief.setText(bean.getData().getProducts().get(i).getBrief());
        holder.tvName.setText(bean.getData().getProducts().get(i).getDesigner().getName());
        holder.tvLabel.setText(bean.getData().getProducts().get(i).getDesigner().getLabel());
        Glide.with(mContext).load(bean.getData().getProducts().get(i).getCover_images().get(0)).into(holder.ivCover);
        Glide.with(mContext).load(bean.getData().getProducts().get(i).getDesigner().getAvatar_url())
                .bitmapTransform(new CropCircleTransformation(mContext)).into(holder.ivAvatar);
        return view;
    }

    private class ReuseViewHolder {
        private ImageView ivCover,ivAvatar;
        private TextView tvBrief,tvName,tvLabel;
        public ReuseViewHolder(View view) {
            ivCover = (ImageView) view.findViewById(R.id.iv_list_cover);
            ivAvatar = (ImageView) view.findViewById(R.id.iv_avatar_url);
            tvBrief = (TextView) view.findViewById(R.id.tv_brief);
            tvName = (TextView) view.findViewById(R.id.tv_designer_name);
            tvLabel = (TextView) view.findViewById(R.id.tv_designer_label);
        }
    }
}
