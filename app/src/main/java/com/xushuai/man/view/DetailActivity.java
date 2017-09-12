package com.xushuai.man.view;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xushuai.man.R;
import com.xushuai.man.db.MyOpenHelper;
import com.xushuai.man.utils.BannerImageLoad;
import com.xushuai.man.utils.SQLiteUtils;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2017/9/8
 * author:徐帅(acer)
 * funcation:商品详情页
 */
public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView leftImg;
    private TextView price;
    private TextView name;
    private Banner banner;
    private List<String> image = new ArrayList<>();
    private TextView joinCar;
    private SQLiteDatabase sd;
    private String goodsImage;
    private String goodsName;
    private String goodsPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //查找控件
        initView();

        //实例化创建数据库的类
        MyOpenHelper openHelper = new MyOpenHelper(this);
        sd = openHelper.getWritableDatabase();
    }

    private void initView() {
        leftImg = (ImageView) findViewById(R.id.leftImageView);
        banner = (Banner) findViewById(R.id.banner);
        price = (TextView) findViewById(R.id.priceTextView);
        name = (TextView) findViewById(R.id.nameTextView);

        joinCar = (TextView) findViewById(R.id.joinCartTextView);

        leftImg.setOnClickListener(this);
        joinCar.setOnClickListener(this);

        //接收商品信息界面传过来的值
        Intent intent = getIntent();
        goodsImage = intent.getStringExtra("goodsImage");
        goodsName = intent.getStringExtra("goodsName");
        goodsPrice = intent.getStringExtra("goodsPrice");
        name.setText(goodsName);
        price.setText(goodsPrice);

        image.add(goodsImage);

        //设置图片加载器
        banner.setImageLoader(new BannerImageLoad());
        //设置图片集合
        banner.setImages(image);
        banner.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftImageView:    //返回键
                finish();
                break;
            case R.id.joinCartTextView: //点击加入购物车
                //实例化封装的数据库工具类
                SQLiteUtils sqLiteUtils = new SQLiteUtils(this);
                //调用里面的添加数据的方法
                sqLiteUtils.insert(goodsName, goodsPrice, goodsImage);
                Toast.makeText(DetailActivity.this, "加入购物车成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}