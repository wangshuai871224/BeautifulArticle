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

public class CryFaceView extends RelativeLayout implements View.OnClickListener {

    private Context mContext;
    private Button mButton;
    private Handler mHandler;
    private int mHeight;
    private int tempHeight;
    private View.OnClickListener mOnClickListener;

    private int mDP2PX_first; // 最初的高度
    private int mDP2PX_final = -1; // 最终的高度
    private int mDP_first = 30;  // 表情的高度, 单位dp.
    private int mDP_default = 150; // 默认高度
    private boolean isChange = false; // 判断表情是否处于变化状态, 默认不是
    private boolean isSelected = false; // 用一个静态变量来判断是否选择了 哭脸
    private int coutomBackgroundDrawable = R.drawable.custom;  // 哭脸的默认背景色为白色

    // 设置 myBackgroundDrawable 的 set方法
    public void setCustomBackgroundDrawable(int myBackgroundDrawable) {
        setBackground(getResources().getDrawable(myBackgroundDrawable));
    }
    public void setDP2PX_final(int DP2PX_final){
        mDP2PX_final = DP2PX_final;
    }

    private void setTempHeight(int tempHeight) {
        this.tempHeight = tempHeight;
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = tempHeight;
        setLayoutParams(params);
    }

    public CryFaceView(Context context) {
        this(context, null);
    }

    public CryFaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setBackground(getResources().getDrawable(coutomBackgroundDrawable));
        //初始化
        mHandler = new Handler();
        mButton = new Button(mContext);
        mHandler = new Handler(Looper.getMainLooper()); // 用来计算帧动画的按时结束
        // DensityTool的方法把DP转换PX
        mDP2PX_first = DensityTool.dip2px(mContext, mDP_first);
        if (mDP2PX_final == -1) {
            // 当未动态设置高度成功, 默认高度为150
            mDP2PX_final = DensityTool.dip2px(mContext, mDP_default);
        }
        LayoutParams params = new LayoutParams(mDP2PX_first, mDP2PX_first);
        addView(mButton, params);
        //为Button设置监听和动画
        mButton.setOnClickListener(this);
        mButton.setBackgroundResource(R.mipmap.dislike_1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = getHeight();
    }
    // 将笑脸变白的方法
    private void setSmallWhite() {
        RelativeLayout parent = (RelativeLayout) getParent();
        for (int i = 0; i < parent.getChildCount(); i++) {
            if (parent.getChildAt(i) instanceof SmileFaceView) {
                SmileFaceView smileFaceView = (SmileFaceView) parent.getChildAt(i);
                smileFaceView.setCustomBackgroundDrawable(R.drawable.custom);
            }
        }
    }

    @Override
    public void onClick(View v) {
        setSmallWhite();
        //获取到组件的高
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        //让组件的高等于临时的高度
        layoutParams.height = tempHeight;

        //设置背景颜色变黄色
        setLayoutParams(layoutParams);
        setBackground(getResources().getDrawable(R.drawable.customcolor));
        // 开始拉伸的属性动画
        startAnim();

        //设置帧动画
        mButton.setBackground(getResources().getDrawable(R.drawable.animcry));
        //帧动画跑起来
        AnimationDrawable drawable = (AnimationDrawable) mButton.getBackground();
        drawable.start();

        // 延时 2000 后进行收缩的属性动画
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 进行收缩的属性动画
                stopAnim();
                mButton.setBackgroundResource(R.mipmap.dislike_1);
            }
        }, 20 * 100);

        // 属性动画, 实现左右摇头
        ObjectAnimator centerToRight = ObjectAnimator.ofFloat(mButton, "translationX", 0, 10);
        ObjectAnimator rightToLeft = ObjectAnimator.ofFloat(mButton, "translationX", 10, -10);
        ObjectAnimator leftToCenter = ObjectAnimator.ofFloat(mButton, "translationX", -10, 0);

        AnimatorSet set = new AnimatorSet();
        set.play(centerToRight).with(rightToLeft);
        set.play(leftToCenter).after(rightToLeft);
        set.setDuration(500);
        set.start();
        //点击判断
        if (mOnClickListener != null) {
            mOnClickListener.onClick(v);
        }
    }

    // 停止
    private void stopAnim() {
        mButton.setEnabled(true);   //可以让Button可以点击
        ObjectAnimator animator = ObjectAnimator.ofInt(this, "TempHeight", mDP2PX_final, mDP2PX_first);
        animator.start();
        // 动画结束, 布尔值为false
        isChange = false;
    }
    // 开始
    private void startAnim() {
        mButton.setEnabled(false);
        ObjectAnimator animator = ObjectAnimator.ofInt(this, "TempHeight", mDP2PX_first, mDP2PX_final);
        animator.start();
        // 开始上升动画时, 布尔值为true
        isChange = true;
    }
}
