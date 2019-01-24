package com.example.my_pc.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private TextView tv_title;
    private Button btn1;
    // 保存全部图片id的数组
    int[] imgs = new int[]{R.drawable.enn_ok,R.drawable.enn_sorry,R.drawable.enn_sorry};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        reset();
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRight(v,0);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRight(v,1);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRight(v,2);
            }
        });
        //再玩一次
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
                tv_title.setText(R.string.title);
                img1.setAlpha(255);
                img2.setAlpha(255);
                img3.setAlpha(255);
                img1.setImageDrawable(getResources().getDrawable(R.drawable.enn_default));
                img2.setImageDrawable(getResources().getDrawable(R.drawable.enn_default));
                img3.setImageDrawable(getResources().getDrawable(R.drawable.enn_default));
            }
        });
    }

    // 随机指定正确图片的位置
    private void reset(){
        for (int i=0;i<3;i++){
            int temp = imgs[i];
            int index = (int)(Math.random()*2);
            imgs[i] = imgs[index];
            imgs[index] = temp;
        }
    }

    // 获取组件
    private void init(){
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        tv_title = (TextView) findViewById(R.id.tv_title);
        btn1 = (Button) findViewById(R.id.btn1);
    }

    // 判断结果
    private void isRight(View v,int index){
        img1.setImageDrawable(getResources().getDrawable(imgs[0]));
        img2.setImageDrawable(getResources().getDrawable(imgs[1]));
        img3.setImageDrawable(getResources().getDrawable(imgs[2]));
        //半透明显示
        img1.setAlpha(100);
        img2.setAlpha(100);
        img3.setAlpha(100);
        // 获取被单击的图像视图
        ImageView iv1 = (ImageView)v;
        iv1.setAlpha(255);
        if (imgs[index] == R.drawable.enn_ok){
            tv_title.setText("恭喜你，猜对了！");
        }else {
            tv_title.setText("哎呀，手滑了，再来一次？");
        }
    }
}
