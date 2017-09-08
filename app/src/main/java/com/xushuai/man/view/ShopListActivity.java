package com.xushuai.man.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.xushuai.man.R;
import com.xushuai.man.adapter.ShopListAdapter;
import com.xushuai.man.bean.ShopListBean;
import com.xushuai.man.net.OkHttp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * date:2017/9/7
 * author:徐帅(acer)
 * funcation:商品信息界面
 */
public class ShopListActivity extends AppCompatActivity {

    private RecyclerView sl_recycler;
    private List<ShopListBean.DatasBean.GoodsListBean> list;
    private String url = "http://169.254.30.70/mobile/index.php?act=goods&op=goods_list&page=100";
    private ShopListAdapter adapter;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String str = (String) msg.obj;
            Gson gson = new Gson();
            ShopListBean bean = gson.fromJson(str, ShopListBean.class);
            list = bean.getDatas().getGoods_list();
            //添加适配器
            adapter = new ShopListAdapter(ShopListActivity.this, list);
            sl_recycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            //条目点击事件
            adapter.setOnItemClickListener(new ShopListAdapter.OnItemClickListener() {
                @Override
                public void onItemClickListener(int position, View view) {
                    //跳转到商品详情页界面
                    Intent intent = new Intent(ShopListActivity.this, DetailActivity.class);
                    intent.putExtra("goodsImage",list.get(position).getGoods_image_url());
                    intent.putExtra("goodsName",list.get(position).getGoods_name());
                    intent.putExtra("goodsPrice",list.get(position).getGoods_price());
                    startActivity(intent);
                }
            });
        }
    };
    private ImageView leftImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        //查找控件
        initView();

        //添加数据
        initData();
    }

    private void initView() {
        sl_recycler = (RecyclerView) findViewById(R.id.sl_recycler);

        //返回键按钮
        leftImage = (ImageView) findViewById(R.id.leftImageView);

        //设置布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(this);
        sl_recycler.setLayoutManager(manager);

        //返回键的监听事件
        leftImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //销毁当前Activity
                finish();
            }
        });
    }

    private void initData() {
        list = new ArrayList<>();
        OkHttp.getAsync(url, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Message me = new Message();
                me.obj = result;
                handler.sendMessage(me);
            }
        });
    }
}