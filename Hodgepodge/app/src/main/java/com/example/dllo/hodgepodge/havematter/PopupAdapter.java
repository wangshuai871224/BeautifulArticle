package com.example.dllo.hodgepodge.havematter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dllo.hodgepodge.R;

import java.util.List;

/**
 * Created by TaiF on 16/12/22.
 */
public class PopupAdapter extends RecyclerView.Adapter<PopupAdapter.PopupViewHolder> {
    private Context mContext;
    private PopupClick mPopup;

    private int a;

    public void setPopup(PopupClick popup) {
        mPopup = popup;
        notifyDataSetChanged();
    }

    private List<BeanTab.DataBean.CategoriesBean.SubCategoriesBean> bean;

    public void setBean(List<BeanTab.DataBean.CategoriesBean.SubCategoriesBean> bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    public PopupAdapter(Context context) {
        mContext = context;
    }

    @Override
    public PopupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rvpopu, parent, false);
        PopupViewHolder holder = new PopupViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final PopupViewHolder holder, final int position) {

        if ( position == 0 ) {
            holder.tvData.setText("全部");
        }
        else{
            holder.tvData.setText(bean.get(position - 1).getName());

        }
        holder.tvData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopup.getPosition(position);
            }
        });
        if (position == a){
            holder.tvData.setTextColor(Color.BLACK);
            holder.tvData.setBackgroundResource(R.drawable.popup_bcakground);
        } else {
            holder.tvData.setTextColor(Color.WHITE);
            holder.tvData.setBackgroundResource(R.color.t_black);
        }
    }
    @Override
    public int getItemCount() {
        return bean == null ? 0 : bean.size();
    }

    public class PopupViewHolder extends RecyclerView.ViewHolder {
        private TextView tvData;
        public PopupViewHolder(View itemView) {
            super(itemView);
            tvData = (TextView) itemView.findViewById(R.id.tv_choose);

        }
    }

    public void getPosition(int a){
        this.a = a;
    }
}
