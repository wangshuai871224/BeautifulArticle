package com.example.dllo.hodgepodge.havematter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.hodgepodge.R;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by TaiF on 16/12/21.
 * 获得喜欢 和 不喜欢的百分比 和 高度
 * 在画脸时用
 */
public class ReuseAdapter extends BaseAdapter {
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ReuseViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_reuse, viewGroup, false);
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
        holder.ivCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SecondaryActivity.class);
                intent.putExtra("id",bean.getData().getProducts().get(i).getId());
                Log.d("ReuseAdapter", "intent" + bean.getData().getProducts().get(i).getId());
                mContext.startActivity(intent);

            }
        });

        // 喜欢和不喜欢的值
        int likeCount, disLikeCount;
        likeCount = bean.getData().getProducts().get(i).getLike_user_num();
        disLikeCount = bean.getData().getProducts().get(i).getUnlike_user_num();

        // 喜欢和不喜欢的高度
        double likeHeight = GetPercent.getLikeHigh(likeCount, disLikeCount);
        double dislikeHeight = GetPercent.getDislikeHigh(likeCount, disLikeCount);
        // 喜欢和不喜欢的百分比
        int likePercent = (int) GetPercent.getLikePercent(likeCount, disLikeCount);
        int dislikePercent = 100 - likePercent; // 100% 的值 为100
        // 找到两个表情, 设置高度
        CryFaceView cryFaceView = holder.cryPic;
        SmileFaceView smileFaceView = holder.smilePic;
        cryFaceView.setDP2PX_final((int) dislikeHeight);
        smileFaceView.setEndHighly((int) likeHeight);
        return view;
    }

    private class ReuseViewHolder {
        private ImageView ivCover, ivAvatar;
        private TextView tvBrief, tvName, tvLabel;
        private CryFaceView cryPic;
        private SmileFaceView smilePic;
        public ReuseViewHolder(View view) {
            ivCover = (ImageView) view.findViewById(R.id.iv_list_cover);
            ivAvatar = (ImageView) view.findViewById(R.id.iv_avatar_url);
            tvBrief = (TextView) view.findViewById(R.id.tv_brief);
            tvName = (TextView) view.findViewById(R.id.tv_designer_name);
            tvLabel = (TextView) view.findViewById(R.id.tv_designer_label);
            cryPic = (CryFaceView) view.findViewById(R.id.cfv_cry);
            smilePic = (SmileFaceView) view.findViewById(R.id.sfv_smile);
        }
    }
}
