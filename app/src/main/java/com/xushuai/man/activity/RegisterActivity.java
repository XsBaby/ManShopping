package com.xushuai.man.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.xushuai.man.R;

/**
 * date:2017/9/5
 * author:徐帅(acer)
 * funcation:注册界面
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView leftImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //查找控件
        initView();
    }

    private void initView() {
        leftImage = (ImageView) findViewById(R.id.leftImageView);

        //返回键的监听事件
        leftImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftImageView:
                //销毁掉当前Activity
                finish();
                break;
        }
    }
}