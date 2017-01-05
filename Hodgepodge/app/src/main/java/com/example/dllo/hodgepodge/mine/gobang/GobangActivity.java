package com.example.dllo.hodgepodge.mine.gobang;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;

import com.example.dllo.hodgepodge.R;
import com.example.dllo.hodgepodge.base.BaseActivity;

public class GobangActivity extends BaseActivity {

    private GobangPanel mGobangPanel;
    private MediaPlayer mMediaPlayer;

    @Override
    protected int setLayout() {
        return R.layout.activity_gobang;
    }

    @Override
    protected void initView() {
        mGobangPanel = bindView(R.id.activity_gabang_panel);
    }

    @Override
    protected void initData() {
        /**
         * 判断胜负及是否重新开始
         */
        initVictoryOrDefeat();
        /**
         * 播放音乐
         */
        playerMusic();
    }

    /**
     * 播放音乐
     */
    private void playerMusic() {
        mMediaPlayer = MediaPlayer.create(this, R.raw.music);
        mMediaPlayer.start();
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });
    }

    /**
     * 判断胜负及是否重新开始
     */
    private void initVictoryOrDefeat() {
        // 实现接口
        mGobangPanel.setOnGameOVerListener(new OnGameOverListener() {
            @Override
            public void gameOver(boolean isWhiteWinner) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GobangActivity.this);
                builder.setTitle("结果");
                String msg = isWhiteWinner ? "白棋获胜" : "黑棋获胜";
                builder.setMessage(msg + "是否再来一局?");
                builder.setPositiveButton("决战到天亮", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mGobangPanel.restart();
                    }
                });
                builder.setNegativeButton("回家吃饭, 下次再玩", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GobangActivity.this.onBackPressed();
                        mMediaPlayer.stop();
                    }
                });
                builder.setCancelable(false);
                builder.show();
            }
        });
    }
}
