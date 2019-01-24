package com.example.my_pc.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ProBarActivity extends Activity {

    private ProgressBar proBar1;
    private ProgressBar proBar2;
    private ProgressBar proBar3;
    private ProgressBar proBar4;
    private ProgressBar proBar5;
    private ProgressBar proBar6;
    private int mProStatus = 0;
    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_probar);
        init();
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x111) {
                    proBar1.setProgress(mProStatus);//更新进度
                    proBar6.setProgress(mProStatus);
                } else {
                    Toast.makeText(ProBarActivity.this, "耗时操作已完成", Toast.LENGTH_LONG).show();
                    proBar1.setVisibility(View.GONE);//设置进度条不显示，并且不占用空间
                    proBar2.setVisibility(View.GONE);
                }
            }
        };
        //开启一个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    mProStatus = doWork();
                    Message m = new Message();
                    if (mProStatus < 100) {
                        m.what = 0x111;
                        mHandler.sendMessage(m);//发送信息
                    } else {
                        m.what = 0x110;
                        mHandler.sendMessage(m);
                        break;
                    }
                }
            }

            // 模拟一个耗时操作
            private int doWork() {
                mProStatus += Math.random() * 10;//改变完成进度
                try {
                    Thread.sleep(200);//休眠200毫秒
                } catch (InterruptedException e) {

                }
                return mProStatus;//返回新的进度
            }
        }).start();
    }

    private void init() {
        proBar1 = (ProgressBar) findViewById(R.id.probar1);
        proBar2 = (ProgressBar) findViewById(R.id.probar2);
        proBar3 = (ProgressBar) findViewById(R.id.probar3);
        proBar4 = (ProgressBar) findViewById(R.id.probar4);
        proBar5 = (ProgressBar) findViewById(R.id.probar5);
        proBar6 = (ProgressBar) findViewById(R.id.probar6);
    }


}
