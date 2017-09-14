package com.xushuai.man.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xushuai.man.R;
import com.xushuai.man.bean.SQLiteBean;
import com.xushuai.man.view.AmountView;

import java.util.HashMap;
import java.util.List;

/**
 * date:2017/9/12
 * author:徐帅(acer)
 * funcation:购物车ListView的适配器
 */

public class ShopCarAdapter extends BaseAdapter {

    private Context context;
    private List<SQLiteBean> list;
    private HashMap<Integer, Boolean> isCheckedHasMap;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.shopcar_item, null);
            vh = new ViewHolder();
            vh.sc_image = (ImageView) convertView.findViewById(R.id.sc_image);
            vh.sc_name = (TextView) convertView.findViewById(R.id.sc_name);
            vh.sc_price = (TextView) convertView.findViewById(R.id.sc_price);
            vh.selfview = (AmountView) convertView.findViewById(R.id.selfview);
            vh.scitem_cb = (CheckBox) convertView.findViewById(R.id.scitem_cb);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }

        Glide.with(context).load(list.get(position).getImageurl()).into(vh.sc_image);
        vh.sc_name.setText(list.get(position).getName());
        vh.sc_price.setText(list.get(position).getPrice());
        vh.selfview.setGoods_storage(50);
        //购物车加减器监听事件
//        v.selfview.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
//            @Override
//            public void onAmountChange(View view, int amount) {
//                Toast.makeText(context, "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
//            }
//        });
        vh.scitem_cb.setChecked(list.get(position).isCheck());

        vh.scitem_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).isCheck()) {
                    list.get(position).setCheck(false);
                    vh.scitem_cb.setChecked(false);
                }else{
                    list.get(position).setCheck(true);
                    vh.scitem_cb.setChecked(true);
                }
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    class ViewHolder {
        ImageView sc_image;
        TextView sc_name, sc_price;
        AmountView selfview;
        CheckBox scitem_cb;
    }
}