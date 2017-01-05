package com.example.dllo.hodgepodge.mine.msgcenter;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseActivity;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by shuaiwang on 16/12/22.
 */

public class MsgCenterActivity extends BaseActivity{

    private WheelView mWheelView;
    private ImageButton mImageButton;

    @Override
    protected int setLayout() {
        return R.layout.activity_msg_center;
    }

    @Override
    protected void initView() {
        mWheelView = bindView(R.id.activity_msg_center_lucky_wheel);
        mImageButton = bindView(R.id.activity_msg_center_start);
    }

    @Override
    protected void initData() {
        mImageButton.setOnClickListener(new View.OnClickListener() {
            int a, b;
            @Override
            public void onClick(View v) {
                if (mWheelView.isStarting()) {
                    //防止stop方法被执行多次
                    if (!mWheelView.isShouldStop()) {
                        switch (b){
                            case 0:
                                Toast.makeText(MsgCenterActivity.this, R.string.m_camera, Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                Toast.makeText(MsgCenterActivity.this, R.string.m_ipad, Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Toast.makeText(MsgCenterActivity.this, R.string.m_sing, Toast.LENGTH_SHORT).show();
                                break;
                            case 3:
                                Toast.makeText(MsgCenterActivity.this, R.string.m_iphone, Toast.LENGTH_SHORT).show();
                                break;
                            case 4:
                                Toast.makeText(MsgCenterActivity.this, R.string.m_clothing, Toast.LENGTH_SHORT).show();
                                break;
                            case 5:
                                Toast.makeText(MsgCenterActivity.this, R.string.m_meney, Toast.LENGTH_SHORT).show();
                                break;
                        }
                        mWheelView.stop();
                        mImageButton.setBackgroundResource(R.mipmap.start);
                    }
                } else {
                    // 设置随机概率
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    for (int i = 0; i < 10; i++) {
                        list.add(2);
                        list.add(5);
                    }
                    list.add(0);
                    list.add(1);
                    list.add(3);
                    list.add(4);

                    // 通过随机数确定中奖选项
                    Random random = new Random();
                    a = random.nextInt(24);
                    // 根据随机数获取中奖号
                    // 参数是中奖号 0 1 2 3 4 5 对应: 单反相机 iPad 硕哥唱歌 iPhone 服装一套 硕哥发红包
                    b = list.get(a);
                    mWheelView.start(b);
                    mImageButton.setBackgroundResource(R.mipmap.stop);
                }
            }
        });
    }

}
