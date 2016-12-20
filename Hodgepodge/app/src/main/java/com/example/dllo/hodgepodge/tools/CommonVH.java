package com.example.dllo.hodgepodge.tools;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dllo on 16/12/19.
 */

public class CommonVH extends RecyclerView.ViewHolder{

    private View mItemView;
    private SparseArray<View> mViews;// key值固定为int类型的hashmap

    public CommonVH(View itemView) {
        super(itemView);
        mItemView = itemView;
        mViews = new SparseArray<>();
    }

    public <T extends View> T getView(int layoutId) {
        View view = mViews.get(layoutId);
        if (view == null) {
            view = mItemView.findViewById(layoutId);
        }
        mViews.put(layoutId, view);
        return (T) view;
    }

    public View getItemView() {
        return mItemView;
    }

    // listView的viewHolder
    public static CommonVH listViewHolder(View view, ViewGroup viewGroup, int layoutId) {
        CommonVH commonVH;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false);
            commonVH = new CommonVH(view);
            view.setTag(commonVH);
        }else {
            commonVH = (CommonVH) view.getTag();
        }
        return commonVH;
    }

    // recyclerView的viewHolder
    public static CommonVH recyclerViewHolder(ViewGroup viewGroup, int id) {
        return listViewHolder(null, viewGroup , id);
    }

    public CommonVH setText(int id, String s) {

        TextView textView = getView(id);
        textView.setText(s);
        return this;
    }

    public CommonVH setImage(int id, int imgId) {

        ImageView imageView = getView(id);
        imageView.setImageResource(imgId);
        return this;
    }

    public CommonVH setClick(int id, View.OnClickListener listener) {
        getView(id).setOnClickListener(listener);
        return this;
    }



}
