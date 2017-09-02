package com.xushuai.man.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xushuai.man.R;
import com.xushuai.man.bean.TypeOneBean;

import java.util.List;

/**
 * date:2017/8/31
 * author:徐帅(acer)
 * funcation: 分类界面左边RecycleView的适配器
 */

public class TypeListOneAdapter extends RecyclerView.Adapter<TypeListOneAdapter.MyViewHolder> {

    private Context context;
    private List<TypeOneBean.DatasBean.ClassListBean> list;

    public TypeListOneAdapter(Context context, List<TypeOneBean.DatasBean.ClassListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.recitem_flone, null);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv.setText(list.get(position).getGc_name());
        Glide.with(context).load(list.get(position).getImage()).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
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

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.itemone_image);
            tv = (TextView) itemView.findViewById(R.id.itemone_tv);
        }
    }

    //自定义item点击监听事件
    public interface OnItemClickListener {
        void onItemClickListener(int position, View view);
    }

    public OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}