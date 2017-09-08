package com.xushuai.man.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xushuai.man.R;
import com.xushuai.man.utils.BannerImageLoad;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //查找控件
        initView();
    }

    private void initView() {
        leftImg = (ImageView) findViewById(R.id.leftImageView);
        banner = (Banner) findViewById(R.id.banner);
        price = (TextView) findViewById(R.id.priceTextView);
        name = (TextView) findViewById(R.id.nameTextView);

        leftImg.setOnClickListener(this);

        //接收商品信息界面传过来的值
        Intent intent = getIntent();
        String goodsImage = intent.getStringExtra("goodsImage");
        String goodsName = intent.getStringExtra("goodsName");
        String goodsPrice = intent.getStringExtra("goodsPrice");
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
            case R.id.leftImageView:
                finish();
                break;
        }
    }
}