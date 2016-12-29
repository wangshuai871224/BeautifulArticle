package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;

import com.handmark.pulltorefresh.library.internal.LoadingLayout;

/**
 * Created by shuaiwang on 16/12/28.
 * 在pullToRefreshBase中1330行作为返回值
 */

public class ListViewRefreshAnimation extends LoadingLayout{

    //自己定义的    其他方法都是自己出来的
    //在倒数第三个方法里面开启
    private AnimationDrawable mAnimationDrawable ;

    public ListViewRefreshAnimation(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.Orientation scrollDirection, TypedArray attrs) {
        super(context, mode, scrollDirection, attrs);
        //前面是Jar包里面自己有的  后面是自己定义的帧动画

        // 设置动画
        mHeaderImage.setImageResource(R.drawable.anim);// 修改图片位置(需要找到image所在布局一顿commend + 点点点)
        mAnimationDrawable = (AnimationDrawable) mHeaderImage.getDrawable();

    }

    // 某些时候出现的图...
    @Override
    protected int getDefaultDrawableResId() {
        return R.drawable.anim;
    }

    @Override
    protected void onLoadingDrawableSet(Drawable imageDrawable) {

    }

    @Override
    protected void onPullImpl(float scaleOfLayout) {

    }

    @Override
    protected void pullToRefreshImpl() {

    }

    //在倒数第三个方法里面开启
    @Override
    protected void refreshingImpl() {

    }

    @Override
    protected void releaseToRefreshImpl() {

    }

    @Override
    protected void resetImpl() {

    }
}
