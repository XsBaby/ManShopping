package com.xushuai.man.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xushuai.man.R;
import com.xushuai.man.bean.ShopListBean;

import java.util.List;

/**
 * date:2017/9/7
 * author:徐帅(acer)
 * funcation: 商品信息列表的适配器类
 */

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.SlViewHolder> {

    private Context context;
    private List<ShopListBean.DatasBean.GoodsListBean> list;

    public ShopListAdapter(Context context, List<ShopListBean.DatasBean.GoodsListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public SlViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载视图
        View view = View.inflate(context, R.layout.shoplist_item, null);
        SlViewHolder viewHolder = new SlViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SlViewHolder holder, final int position) {
        //赋值
        Glide.with(context).load(list.get(position).getGoods_image_url()).into(holder.sl_img);
        holder.goodsName.setText(list.get(position).getGoods_name());
        holder.goodsPrice.setText("￥" + list.get(position).getGoods_price());

        holder.sl_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClickListener(position, v);
            }
        });
        holder.goodsName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClickListener(position, v);
            }
        });

        holder.goodsPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClickListener(position, v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class SlViewHolder extends RecyclerView.ViewHolder {

        private ImageView sl_img;
        private TextView goodsName;
        private TextView goodsPrice;

        public SlViewHolder(View itemView) {
            super(itemView);
            //查找控件
            sl_img = (ImageView) itemView.findViewById(R.id.sl_img);
            goodsName = (TextView) itemView.findViewById(R.id.sl_goods_name);
            goodsPrice = (TextView) itemView.findViewById(R.id.sl_goods_price);
        }
    }

    //自定义item点击监听事件
    public interface OnItemClickListener {
        void onItemClickListener(int position, View view);
    }

    public ShopListAdapter.OnItemClickListener listener;

    public void setOnItemClickListener(ShopListAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}