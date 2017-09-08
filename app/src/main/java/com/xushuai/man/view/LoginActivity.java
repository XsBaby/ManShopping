package com.xushuai.man.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xushuai.man.R;

/**
 * date:2017/9/5
 * author:徐帅(acer)
 * funcation:登录界面
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView leftImage;
    private TextView regTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //查找控件
        initView();
    }

    private void initView() {
        //返回键的图片
        leftImage = (ImageView) findViewById(R.id.leftImageView);
        //注册的TextView
        regTextView = (TextView) findViewById(R.id.regTextView);

        //返回键的监听事件
        leftImage.setOnClickListener(this);
        regTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftImageView:
                //销毁掉当前Activity
                finish();
                break;
            case R.id.regTextView:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}