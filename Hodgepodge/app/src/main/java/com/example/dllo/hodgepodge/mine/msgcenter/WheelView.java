package com.example.dllo.hodgepodge.mine.msgcenter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.dllo.hodgepodge.R;

/**
 * 庭
 * Created by Ting on 17/1/3.
 */

/**
 * 自定义的转盘View
 */
public class WheelView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    // 用于绘制的线程
    private Thread mThread;
    // 控制线程的开关
    private Boolean mIsRunning = false;
    // 盘块的奖项文字

    private String[] mTexts = new String[]{getContext().getString(R.string.m_camera), getContext().getString(R.string.m_ipad),
            getContext().getString(R.string.m_sing), getContext().getString(R.string.m_iphone),
            getContext().getString(R.string.m_clothing), getContext().getString(R.string.m_meney)};
    // 盘块的奖项图片
    private int[] mImageIds = new int[]{R.mipmap.camera, R.mipmap.ipad, R.mipmap.shuo_ge, R.mipmap.iphone, R.mipmap.dress, R.mipmap.shuo_ge};
    // 与图片对应的Bitmap数组
    private Bitmap[] mImageBitmaps;

    private Bitmap mBackgroundBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.background);
    // 盘块的颜色
    private int[] mColors = new int[]{0xFFFFC300, 0xFFF17E01, 0xFFFFC300, 0xFFF17E01, 0xFFFFC300, 0xFFF17E01};
    // 盘块的数量
    private int mItemCount = 6;
    // 绘制盘块的画笔
    private Paint mArcPaint;
    // 绘制文本的画笔
    private Paint mTextPaint;

    private float mTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, getResources().getDisplayMetrics());
    // 整个盘块的范围
    private RectF mRangeRectF;
    // 整个盘块的直径
    private int mDiameter;
    // 转盘的中心位置
    private int mCenter;
    // 这里的padding以paddingLeft为准
    private int mPadding;
    // 盘块滚动的速度
    private double mSpeed = 0;
    // 保证线程间变量的可见性
    private volatile float mStartAngle = 0;
    // 判断是会否点击了停止按钮
    private boolean mShouldStop;

    public WheelView(Context context) {
        this(context, null);
    }

    public WheelView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mHolder = getHolder();
        mHolder.addCallback(this);

        //获得焦点
        setFocusable(true);
        setFocusableInTouchMode(true);
        //设置常亮
        setKeepScreenOn(true);
    }

    /**
     * @param widthMeasureSpec  父布局也就是SurfaceView的宽度测量规格
     * @param heightMeasureSpec 父布局也就是SurfaceView的高度测量规格
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = Math.min(getMeasuredWidth(), getMeasuredHeight());

        mPadding = getPaddingLeft();
        //直径
        mDiameter = width - mPadding * 2;
        //中心点
        mCenter = width / 2;

        setMeasuredDimension(width, width);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //初始化绘制盘块的画笔
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setDither(true);

        //初始化绘制文本的画笔
        mTextPaint = new Paint();
        mTextPaint.setColor(0xFFFFFFFF);
        mTextPaint.setTextSize(mTextSize);

        //初始化盘块绘制范围
        mRangeRectF = new RectF(mPadding, mPadding, mPadding + mDiameter, mPadding + mDiameter);

        //初始化图片
        mImageBitmaps = new Bitmap[mItemCount];
        for (int i = 0; i < mItemCount; i++) {
            mImageBitmaps[i] = BitmapFactory.decodeResource(getResources(), mImageIds[i]);
        }

        mIsRunning = true;

        mThread = new Thread(this);
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsRunning = false;
    }

    @Override
    public void run() {
        while (mIsRunning) {
            //不断进行绘制
            long start = System.currentTimeMillis();
            draw();
            long end = System.currentTimeMillis();
            if (end - start < 50) {
                SystemClock.sleep(50 - (end - start));
            }
        }
    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            if (mCanvas != null) {
                // draw something
                // 绘制背景
                drawBackground();
                // 绘制盘块
                float startAngle = mStartAngle;
                float sweepAngle = 360 / mItemCount;

                for (int i = 0; i < mItemCount; i++) {
                    mArcPaint.setColor(mColors[i]);
                    mCanvas.drawArc(mRangeRectF, startAngle, sweepAngle, true, mArcPaint);
                    //绘制每个盘块的文本
                    drawText(startAngle, sweepAngle, mTexts[i] + "");
                    //绘制每个盘块上的图片
                    if (2 == i || 5 == i){
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), mImageIds[i]);
                        // 把图片画成圆形
                        BitmapToRound circleDrawable = new BitmapToRound();
                        Bitmap bitmapCircle = circleDrawable.toRoundBitmap(bitmap);
                        drawIcon(startAngle, bitmapCircle);
                    } else {
                        drawIcon(startAngle, mImageBitmaps[i]);
                    }
                    startAngle += sweepAngle;
                }
                mStartAngle += mSpeed;
                //如果点击了停止按钮
                if (mShouldStop) {
                    mSpeed--;
                }
                if (mSpeed <= 0) {
                    mSpeed = 0;
                    mShouldStop = false;
                }
            }
        } finally {
            if (mCanvas != null) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    /**
     * 启动转盘旋转
     */
    public void start(int index) {
        //计算每一项的角度
        float angle = 360 / mItemCount;
        /**
         * 计算每一项的中奖范围(当前index)
         * index = 0  单反相机  旋转的角度范围是 270 ~ 210
         * index = 1  IPAD     旋转的角度范围是 210 ~ 150
         * index = 2  恭喜发财  旋转的角度范围是 150 ~ 90
         * ......
         * 公式为：旋转的起始角度  = 270 - (index + 1) * angle
         *        旋转的终止角度  = 起始角度 + angle
         */

        float from = 270 - (index + 1) * angle;
        float end = from + angle;

        //设置停下来需要旋转的距离
        float targetFrom = 2 * 360 + from;
        float targetEnd = 2 * 360 + end;

        /**
         * v1 - > 0  每次减1， 等差数列
         * 等差数列计算公式：(首项 v1 + 尾项 0) * 项数(v1 + 1) / 2
         * (v1 + 0) * (v1 + 1) / 2 = targetFrom
         * (v2 + 0) * (v2 + 1) / 2 = targetEnd
         */

        float v1 = (float) ((-1 + Math.sqrt(1 + 8 * targetFrom)) / 2);
        float v2 = (float) ((-1 + Math.sqrt(1 + 8 * targetEnd)) / 2);

        mSpeed = v1 + Math.random() * (v2 - v1);  //随机产生v1~v2之间的随机数
        mShouldStop = false;
    }

    /**
     * 停止转盘旋转
     */
    public void stop() {
        mStartAngle = 0;
        mShouldStop = true;
    }

    /**
     * 判断是会否点击了停止按钮
     *
     * @return 是否点击了停止按钮
     */
    public boolean isShouldStop() {
        return mShouldStop;
    }

    /**
     * 转盘是否正在旋转
     *
     * @return true表示正在旋转
     */
    public boolean isStarting() {
        return mSpeed != 0;
    }

    /**
     * 绘制每个盘块上的图片
     *
     * @param startAngle 起始角度
     * @param bitmap     位图
     */
    private void drawIcon(float startAngle, Bitmap bitmap) {
        //设置图片的高度和宽度为直径的1/8
        int iconWidth = mDiameter / 8;
        //转化成弧度
        float iconAngle = (float) ((startAngle + 360 / mItemCount / 2) * Math.PI / 180);
        //每个图片中心点的坐标
        int x = (int) (mCenter + mDiameter / 4 * Math.cos(iconAngle));
        int y = (int) (mCenter + mDiameter / 4 * Math.sin(iconAngle));

        //确定每个图片的位置
        Rect iconRect = new Rect(x - iconWidth / 2, y - iconWidth / 2, x + iconWidth / 2, y + iconWidth / 2);
        mCanvas.drawBitmap(bitmap, null, iconRect, null);
    }

    /**
     * 绘制每个盘块的文本
     *
     * @param startAngle 初始角度
     * @param sweepAngle 偏移角度
     * @param text       文本
     */
    private void drawText(float startAngle, float sweepAngle, String text) {
        Path path = new Path();
        path.addArc(mRangeRectF, startAngle, sweepAngle);
        //利用水平偏移量让文字居中
        float textWidth = mTextPaint.measureText(text);
        //弧长 = 弧度(角度：60° -> 弧度：Math.PI / 3) * 半径
        int hOffset = (int) (Math.PI / 3 * mDiameter / 2 / 2 - textWidth / 2);
        //垂直偏移量
        int vOffset = mDiameter / 2 / 6;
        mCanvas.drawTextOnPath(text, path, hOffset, vOffset, mTextPaint);
    }

    private void drawBackground() {
        mCanvas.drawColor(0xFFFFFFFF);
        /**
         * 对图片剪接和限定显示区域drawBitmap(Bitmap bitmap, Rect src, Rect dst, Paint paint)；
         * Rect src: 是对图片进行裁截，若是空null则显示整个图片
         * Rect dst：是图片在Canvas画布中显示的区域，
         * 大于src则把src的裁截区放大，小于src则把src的裁截区缩小
         */
        mCanvas.drawBitmap(mBackgroundBitmap, null, new Rect(mPadding / 2, mPadding / 2,
                getMeasuredWidth() - mPadding / 2, getMeasuredHeight() - mPadding / 2), null);
    }
}
