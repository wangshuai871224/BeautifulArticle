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
 * Created by TaiF on 16/12/20.
 */
public class DailyAdapter extends BaseAdapter{
    private Context mContext;
    private BeanDaily bean;

    public DailyAdapter(Context context) {
        mContext = context;
    }

    public void setBean(BeanDaily bean) {
        this.bean = bean;
        notifyDataSetChanged();
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
        DailyViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_daily,viewGroup,false);
            holder = new DailyViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (DailyViewHolder) view.getTag();
        }
        holder.tvBrief.setText(bean.getData().getProducts().get(i).getName());
        holder.tvName.setText(bean.getData().getProducts().get(i).getDesigner().getName());
        holder.tvLabel.setText(bean.getData().getProducts().get(i).getDesigner().getLabel());
        Glide.with(mContext).load(bean.getData().getProducts().get(i).getCover_images().get(0)).into(holder.ivCover);
        Glide.with(mContext).load(bean.getData().getProducts().get(i).getDesigner().getAvatar_url())
                .bitmapTransform(new CropCircleTransformation(mContext)).into(holder.ivAvatar);
        return view;
    }

    private class DailyViewHolder {
        private ImageView ivCover,ivAvatar;
        private TextView tvBrief,tvName,tvLabel;
        public DailyViewHolder(View view) {
            ivCover = (ImageView) view.findViewById(R.id.iv_products_cover);
            ivAvatar = (ImageView) view.findViewById(R.id.iv_designer_avatar_url);
            tvBrief = (TextView) view.findViewById(R.id.tv_products_name);
            tvName = (TextView) view.findViewById(R.id.daily_designer_name);
            tvLabel = (TextView) view.findViewById(R.id.daily_designer_label);
        }
    }
}
