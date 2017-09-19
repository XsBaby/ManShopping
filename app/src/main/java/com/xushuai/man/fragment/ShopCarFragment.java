package com.xushuai.man.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.xushuai.man.R;
import com.xushuai.man.adapter.ShopCarAdapter;
import com.xushuai.man.bean.SQLiteBean;
import com.xushuai.man.utils.SQLiteUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2017/8/31
 * author:徐帅(acer)
 * funcation:购物车的Fragment
 */

public class ShopCarFragment extends Fragment {

    private View view;

    private ListView sc_listView;
    private List<SQLiteBean> list;
    private CheckBox sc_cb;
    private ShopCarAdapter adapter;

    //监听来源
    public boolean mIsFromItem = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.shopcar_fragment, null);
        //查找控件
        initView();

        //添加数据
        initData();
        return view;
    }

    private void initView() {
        sc_listView = (ListView) view.findViewById(R.id.sc_listView);
        sc_cb = (CheckBox) view.findViewById(R.id.sc_cb);
    }

    private void initData() {
        list = new ArrayList<>();
        //实例化封装的数据库工具类
        SQLiteUtils sqLiteUtils = new SQLiteUtils(getActivity());
        //调用查询的方法
        list = sqLiteUtils.query();
        System.out.println("query = " + list);
        //添加适配器
        adapter = new ShopCarAdapter(getActivity(), list, new AllCheckListener() {
            @Override
            public void onCheckedChanged(boolean b) {
                //根据不同的情况对maincheckbox做处理
                if (!b && !sc_cb.isChecked()) {
                    return;
                } else if (!b && sc_cb.isChecked()) {
                    mIsFromItem = true;
                    sc_cb.setChecked(false);
                } else if (b) {
                    mIsFromItem = true;
                    sc_cb.setChecked(true);
                }
            }
        });
        sc_listView.setAdapter(adapter);

        //CheckBox改变监听事件
        sc_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //当监听来源为点击item改变maincbk状态时不在监听改变，防止死循环
                if (mIsFromItem) {
                    mIsFromItem = false;
                    Log.e("mainCheckBox", "此时我不可以触发");
                    return;
                }

                //改变数据
                for (SQLiteBean model : list) {
                    model.setCheck(isChecked);
                }
                //刷新listview
                adapter.notifyDataSetChanged();
            }
        });
    }

    //对item导致maincheckbox改变做监听
    public interface AllCheckListener {
        void onCheckedChanged(boolean b);
    }
}