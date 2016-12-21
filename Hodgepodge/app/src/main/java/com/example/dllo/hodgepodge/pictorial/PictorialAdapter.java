package com.example.dllo.hodgepodge.pictorial;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.tools.CommonVH;
import com.wirelesspienetwork.overview.model.OverviewAdapter;
import com.wirelesspienetwork.overview.model.ViewHolder;

import java.util.List;


/**
 * Created by shuaiwang on 16/12/21.
 */

public class PictorialAdapter extends OverviewAdapter<PictorialAdapter.ViewHoler,PictorialBean>{

    private PictorialBean mBean;

    public void setBean(PictorialBean bean) {
        mBean = bean;
    }

    @Override
    public ViewHoler onCreateViewHolder(Context context, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.recents_dummy, parent, false);
        ViewHoler viewHoler = new ViewHoler(view);
        return viewHoler;
    }

    @Override
    public void onBindViewHolder(ViewHoler viewHoler) {

        viewHoler.title.setText(mBean.getData().getArticles().get(0).getTitle());
        viewHoler.subtitle.setText(mBean.getData().getArticles().get(0).getSub_title());
        viewHoler.userName.setText(mBean.getData().getArticles().get(0).getAuthor().getUsername());

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
