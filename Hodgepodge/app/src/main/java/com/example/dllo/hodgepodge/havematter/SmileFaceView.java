package com.example.dllo.hodgepodge.havematter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.dllo.hodgepodge.R;

/**
 * Created by TaiF on 16/12/27.
 */

public class SmileFaceView extends RelativeLayout implements View.OnClickListener {
    private Context mContext;
    private int mMDP2PX_FIRST;
    private int mEND_HIGHLY = -1; //最终高度赋值-1是用于下面的判断
    private int customBackgroundDrawable = R.drawable.custom;
    private View.OnClickListener mClickListener;
    private int tempHeight;
    private int mDP2PX_60;
    private Button mButton;
    private Handler mHandler;
    private int mHeight;

    // 点击笑脸 哭脸变白
    public void setCustomBackgroundDrawable(int customBackgroundDrawable) {
        this.customBackgroundDrawable = customBackgroundDrawable;
        setBackground(getResources().getDrawable(customBackgroundDrawable));
    }

    //在ReuseAdapter里调用该方法设置最终高度
    public void setEndHighly(int END_HIGHLY) {
        mEND_HIGHLY = END_HIGHLY;
    }

    public void setTempHeight(int tempHeight) {
        this.tempHeight = tempHeight;
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = tempHeight;
        setLayoutParams(params);
    }

    public SmileFaceView(Context context) {
        super(context, null);
    }

    public SmileFaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        init();
    }

    private void init() {
        setBackground(getResources().getDrawable(customBackgroundDrawable));
        mButton = new Button(mContext);
        mHandler = new Handler(Looper.getMainLooper());
        //一个工具类,dp转px
        mMDP2PX_FIRST = DensityTool.dip2px(mContext, 30);    //圆的直径
        if (mEND_HIGHLY == -1) {
            // 当未动态设置高度成功, 默认高度为150
            mEND_HIGHLY = DensityTool.dip2px(mContext, 150);
        }
        LayoutParams layoutParams = new LayoutParams(mMDP2PX_FIRST, mMDP2PX_FIRST);
        addView(mButton, layoutParams);
        mButton.setOnClickListener(this);
        mButton.setBackgroundResource(R.mipmap.like_1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = getHeight();
    }

    @Override
    public void onClick(View view) {
        setCryWhite();


        //动画 --> 得到布局的高度,然后修改高度,并且给布局加上颜色
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = tempHeight;
        setLayoutParams(params);

        setBackgroundResource(R.drawable.customcolor);
        startAnim();

        //帧动画
        mButton.setBackgroundResource(R.drawable.anim);
        AnimationDrawable animationDrawable = (AnimationDrawable) mButton.getBackground();
        animationDrawable.start();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stopAnim();
                mButton.setBackgroundResource(R.mipmap.like_1);
            }
        }, 33 * 100);

        //属性动画
        ObjectAnimator centerToRight = ObjectAnimator.ofFloat(mButton, "translationY", 0, 10);
        ObjectAnimator rightToLeft = ObjectAnimator.ofFloat(mButton, "translationY", 10, -10);
        ObjectAnimator leftToCenter = ObjectAnimator.ofFloat(mButton, "translationY", -10, 0);

        AnimatorSet set = new AnimatorSet();

        set.play(centerToRight).with(rightToLeft);
        set.play(leftToCenter).after(rightToLeft);
        set.setDuration(500);
        set.start();
        if (mClickListener != null) {
            mClickListener.onClick(view);
        }
    }

    private void stopAnim() {
        mButton.setEnabled(true);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofInt(this, "tempHeight", mEND_HIGHLY, mMDP2PX_FIRST);
        objectAnimator1.start();
    }

    private void startAnim() {
        mButton.setEnabled(false);
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(this, "tempHeight", mDP2PX_60, mEND_HIGHLY);
        objectAnimator.start();
    }

    private void setCryWhite() {
        // 设置 哭脸的背景为白色
        RelativeLayout parent = (RelativeLayout) getParent();
        for (int i = 0; i < parent.getChildCount(); i++) {
            if (parent.getChildAt(i) instanceof CryFaceView) {
                CryFaceView cryFaceView = (CryFaceView) parent.getChildAt(i);
                cryFaceView.setCustomBackgroundDrawable(R.drawable.custom);
            }
        }
    }
}
