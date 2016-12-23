package com.example.dllo.hodgepodge.pictorial;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.tools.CommonVH;
import com.wirelesspienetwork.overview.model.OverviewAdapter;
import com.wirelesspienetwork.overview.model.ViewHolder;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;


/**
 * Created by shuaiwang on 16/12/21.
 */

public class PictorialAdapter extends OverviewAdapter<PictorialAdapter.ViewHoler,Integer>{

    private PictorialBean mBean;
    private Context mContext;

    public PictorialAdapter(Context context) {
        mContext = context;
    }

    public PictorialAdapter(List<Integer> integers, Context context) {
        super(integers);
        mContext = context;
    }

    public void setBean(PictorialBean bean) {
        mBean = bean;
    }

    @Override
    public ViewHoler onCreateViewHolder(Context context, ViewGroup parent) {
        View view = View.inflate(context, R.layout.recents_dummy, null);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(ViewHoler viewHoler) {
        viewHoler.title.setText(mBean.getData().getArticles().get(mBean.getData().getArticles().size()-viewHoler.getPosition()-1).getTitle());
        viewHoler.subtitle.setText(mBean.getData().getArticles().get(mBean.getData().getArticles().size()-viewHoler.getPosition()-1).getSub_title());
        viewHoler.userName.setText(mBean.getData().getArticles().get(mBean.getData().getArticles().size()-viewHoler.getPosition()-1).getAuthor().getUsername());

        Glide.with(mContext).load(mBean.getData().getArticles()
                .get(mBean.getData().getArticles().size()-viewHoler.getPosition()-1).getImage_url())
                .into(viewHoler.pictorialImage);
        Glide.with(mContext).load(mBean.getData().getArticles()
                .get(mBean.getData().getArticles().size()-viewHoler.getPosition()-1).getAuthor().getAvatar_url())
                .bitmapTransform(new CropCircleTransformation(mContext))
                .into(viewHoler.avatarImage);
    }

    class  ViewHoler extends ViewHolder{

        private TextView userName, subtitle, title;
        private ImageView pictorialImage, avatarImage;

        public ViewHoler(View view) {
            super(view);

            userName = (TextView) view.findViewById(R.id.user_name);
            subtitle = (TextView) view.findViewById(R.id.pictorial_subtitle);
            title = (TextView) view.findViewById(R.id.pictorial_title);
            pictorialImage = (ImageView) view.findViewById(R.id.pictorial_image);
            avatarImage = (ImageView) view.findViewById(R.id.avatar_image);
        }
    }

}
