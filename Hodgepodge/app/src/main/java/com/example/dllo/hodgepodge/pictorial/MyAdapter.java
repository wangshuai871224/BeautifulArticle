package com.example.dllo.hodgepodge.pictorial;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.hodgepodge.R;
import com.wirelesspienetwork.overview.model.OverviewAdapter;
import com.wirelesspienetwork.overview.model.ViewHolder;

/**
 * Created by shuaiwang on 16/12/23.
 */

public class MyAdapter extends OverviewAdapter<MyAdapter.MyViewHolder,PictorialBean> {
    private PictorialBean.DataBean.ArticlesBean mPictorialBean;
    private Context mContext;

    public MyAdapter(Context context) {
        mContext = context;
    }


    public void setPictorialBean(PictorialBean.DataBean.ArticlesBean pictorialBean) {
        mPictorialBean = pictorialBean;

    }



    @Override
    public MyViewHolder onCreateViewHolder(Context context, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.recents_dummy,parent,false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder) {
        myViewHolder.title.setTextColor(Color.WHITE);
        myViewHolder.title.setText(mPictorialBean.getTitle());

    }

    class MyViewHolder extends ViewHolder<View,PictorialBean>{
        private TextView userName, subtitle, title;
        private ImageView pictorialImage, avatarImage;
        public MyViewHolder(View view) {
            super(view);
            userName = (TextView) view.findViewById(R.id.user_name);
            subtitle = (TextView) view.findViewById(R.id.pictorial_subtitle);
            title = (TextView) view.findViewById(R.id.pictorial_title);
            pictorialImage = (ImageView) view.findViewById(R.id.pictorial_image);
            avatarImage = (ImageView) view.findViewById(R.id.avatar_image);
        }
    }


}
