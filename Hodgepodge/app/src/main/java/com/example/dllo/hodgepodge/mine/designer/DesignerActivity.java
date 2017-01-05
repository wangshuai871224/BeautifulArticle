package com.example.dllo.hodgepodge.mine.designer;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by shuaiwang on 16/12/22.
 */
public class DesignerActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mImageView;
    private Button clickName;
    private TextView showWinning, winningName;

    private boolean isRunning;
    private boolean isStart = true;
    private Handler mHandler;
    private List<String> names;
    private InnerThread mThread;



    @Override
    protected int setLayout() {
        return R.layout.activity_designer;
    }

    @Override
    protected void initView() {
        mImageView = bindView(R.id.designer_back);
        clickName = bindView(R.id.click_name);
        showWinning = bindView(R.id.show_winning);
        winningName = bindView(R.id.winning_name);
        names = getNames();
        setClick(this, mImageView, clickName);
        mHandler = new Handler(new InnerCallBack());
        mThread = new InnerThread();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.designer_back:
                finish();
                break;
            case R.id.click_name:

                showWinning.setVisibility(View.INVISIBLE);
                if (isRunning) {
                    showWinning.setVisibility(View.INVISIBLE);
                    if (mThread.isAlive() && mThread != null){
                        mThread.interrupt();
                    }
                    isRunning = false;
                    clickName.setText("点名");
                } else {
                    isRunning = true;
                    showWinning.setVisibility(View.VISIBLE);
                    mThread.start();
                    clickName.setText("暂停");
                }
                break;
        }
    }

    private List<String> getNames() {
        List<String> name = new ArrayList<String>();
        name.add("王振涛");
        name.add("卢小玉");
        name.add("马瑞庭");
        name.add("王桥生");
        name.add("顾斌");
        name.add("王帅");
        name.add("陈国强");
        name.add("陈焕洞");
        name.add("李硕");
        name.add("陈小飞");
        name.add("裴亮");
        name.add("小可爱");
        name.add("赵宁");
        name.add("123");
        return name;
    }

    private class InnerCallBack implements Handler.Callback {
        @Override
        public boolean handleMessage(Message message) {
            int index = message.arg1;
            String name = names.get(index);
            winningName.setText(name);
            return false;
        }
    }

    private class InnerThread extends Thread {

        Random mRandom = new Random();
        int nameCount = names.size();
        int ranNumber;

        @Override
        public void run() {
            while (isRunning) {
                ranNumber = mRandom.nextInt(nameCount);
                //             handler  what  obj
                Message.obtain(mHandler, 0, ranNumber, 0).sendToTarget();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
