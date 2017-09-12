package com.xushuai.man.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xushuai.man.R;
import com.xushuai.man.bean.SQLiteBean;
import com.xushuai.man.view.AmountView;

import java.util.List;

/**
 * date:2017/9/12
 * author:徐帅(acer)
 * funcation:购物车ListView的适配器
 */

public class ShopCarAdapter extends BaseAdapter {

    private Context context;
    private List<SQLiteBean> list;

    public ShopCarAdapter(Context context, List<SQLiteBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder v;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.shopcar_item, null);
            v = new ViewHolder();
            v.sc_image = (ImageView) convertView.findViewById(R.id.sc_image);
            v.sc_name = (TextView) convertView.findViewById(R.id.sc_name);
            v.sc_price = (TextView) convertView.findViewById(R.id.sc_price);
            v.selfview = (AmountView) convertView.findViewById(R.id.selfview);
            convertView.setTag(v);
        }
        v = (ViewHolder) convertView.getTag();

        Glide.with(context).load(list.get(position).getImageurl()).into(v.sc_image);
        v.sc_name.setText(list.get(position).getName());
        v.sc_price.setText(list.get(position).getPrice());
        v.selfview.setGoods_storage(50);
        //购物车加减器监听事件
//        v.selfview.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
//            @Override
//            public void onAmountChange(View view, int amount) {
//                Toast.makeText(context, "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
//            }
//        });
        return convertView;
    }

    class ViewHolder {
        ImageView sc_image;
        TextView sc_name, sc_price;
        AmountView selfview;
    }
}