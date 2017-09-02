package com.xushuai.man.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xushuai.man.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * date:2017/8/31
 * author:徐帅(acer)
 * funcation:用于3秒计时跳转到主界面
 */
public class LoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        //实例化一个Timer
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //开始跳转
                Intent intent = new Intent(LoadActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 1000);
    }
}