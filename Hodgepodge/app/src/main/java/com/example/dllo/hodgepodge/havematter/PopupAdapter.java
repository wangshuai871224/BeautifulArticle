package com.example.dllo.hodgepodge.havematter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dllo.hodgepodge.R;

/**
 * Created by TaiF on 16/12/22.
 */
public class PopupAdapter extends RecyclerView.Adapter<PopupAdapter.PopupViewHolder> {
    private Context mContext;
    private BeanMatterPop bean;

    public void setBean(BeanMatterPop bean) {
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
    public void onBindViewHolder(PopupViewHolder holder, int position) {
        holder.tvData.setText(bean.getData().getCategories().get(position).getName());
    }

    @Override
    public int getItemCount() {
        return bean == null ? 0 : bean.getData().getCategories().size();
    }

    public class PopupViewHolder extends RecyclerView.ViewHolder {
        private TextView tvData;
        public PopupViewHolder(View itemView) {
            super(itemView);
            tvData = (TextView) itemView.findViewById(R.id.tv_popup);

        }
    }
}
