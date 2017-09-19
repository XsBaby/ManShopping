package com.xushuai.man.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xushuai.man.R;
import com.xushuai.man.bean.SQLiteBean;
import com.xushuai.man.fragment.ShopCarFragment;
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
    private ShopCarFragment.AllCheckListener allCheckListener;

    public ShopCarAdapter(Context context, List<SQLiteBean> list, ShopCarFragment.AllCheckListener allCheckListener) {
        this.context = context;
        this.list = list;
        this.allCheckListener = allCheckListener;
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
        ViewHolder vh;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.shopcar_item, null);
            vh = new ViewHolder();
            vh.sc_image = (ImageView) convertView.findViewById(R.id.sc_image);
            vh.sc_name = (TextView) convertView.findViewById(R.id.sc_name);
            vh.sc_price = (TextView) convertView.findViewById(R.id.sc_price);
            vh.selfview = (AmountView) convertView.findViewById(R.id.selfview);
            vh.scitem_cb = (CheckBox) convertView.findViewById(R.id.scitem_cb);
            convertView.setTag(vh);
        }
        SQLiteBean mode = list.get(position);
        vh = (ViewHolder) convertView.getTag();
        final ViewHolder hdFinal = vh;
        vh.scitem_cb.setChecked(mode.isCheck());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = hdFinal.scitem_cb;
                if (checkBox.isChecked()) {
                    checkBox.setChecked(true);
                    list.get(position).setCheck(false);
                } else {
                    checkBox.setChecked(false);
                    list.get(position).setCheck(true);
                }
                //监听每个item，若所有checkbox都为选中状态则更改main的全选checkbox状态
                for (SQLiteBean bean : list) {
                    if (bean.isCheck()) {
                        allCheckListener.onCheckedChanged(false);
                        return;
                    }
                }
                allCheckListener.onCheckedChanged(true);
            }
        });

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
        return convertView;
    }

    class ViewHolder {
        ImageView sc_image;
        TextView sc_name, sc_price;
        AmountView selfview;
        CheckBox scitem_cb;
        Button btnDecrease, btnIncrease;
    }
}