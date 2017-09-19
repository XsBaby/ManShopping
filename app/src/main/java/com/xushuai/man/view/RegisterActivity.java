package com.xushuai.man.view;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xushuai.man.R;
import com.xushuai.man.bean.ReLoginBean;
import com.xushuai.man.db.MyOpenHelper;
import com.xushuai.man.utils.FirstEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * date:2017/9/5
 * author:徐帅(acer)
 * funcation:注册界面
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView leftImage;
    private EditText userName;
    private EditText userPd;
    private EditText userPrd;
    private EditText userEmail;
    private TextView regTextView;

    private List<ReLoginBean> list;
    private SQLiteDatabase sd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //查找控件
        initView();

        //实例化创建数据库的
        MyOpenHelper openHelper = new MyOpenHelper(this);
        sd = openHelper.getWritableDatabase();
    }

    private void initView() {
        leftImage = (ImageView) findViewById(R.id.leftImageView);
        regTextView = (TextView) findViewById(R.id.regTextView);
        userName = (EditText) findViewById(R.id.usernameEditText);
        userPd = (EditText) findViewById(R.id.passwordEditText);
        userPrd = (EditText) findViewById(R.id.passwordRepeatEditText);
        userEmail = (EditText) findViewById(R.id.emailEditText);

        //返回键的监听事件
        leftImage.setOnClickListener(this);
        regTextView.setOnClickListener(this);
    }

    private void getData() {
        String username = userName.getText().toString().trim();
        String userpd = userPd.getText().toString().trim();
        String userprd = userPrd.getText().toString().trim();
        String useremail = userEmail.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(userpd)) {
            Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(userprd)) {
            Toast.makeText(RegisterActivity.this, "确认密码不能为空", Toast.LENGTH_SHORT).show();
        } else if (!userprd.equals(userpd)) {
            Toast.makeText(RegisterActivity.this, "两次密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(useremail)) {
            Toast.makeText(RegisterActivity.this, "邮箱地址不能为空", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            ContentValues values = new ContentValues();
            values.put("user", username);
            values.put("password", userpd);
            long insert = sd.insert("rlogin", null, values);
            System.out.println(insert + "行受影响-----------" + "username:" + username + ",userpd:" + userpd);
            finish();
            EventBus.getDefault().post(new FirstEvent(username));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftImageView:
                //销毁掉当前Activity
                finish();
                break;
            case R.id.regTextView:
                getData();
                break;
        }
    }
}