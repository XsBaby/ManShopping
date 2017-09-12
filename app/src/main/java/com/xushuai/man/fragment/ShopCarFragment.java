package com.xushuai.man.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    }

    private void initData() {
        list = new ArrayList<>();
        //实例化封装的数据库工具类
        SQLiteUtils sqLiteUtils = new SQLiteUtils(getActivity());
        //调用查询的方法
        list = sqLiteUtils.query();
        System.out.println("query = " + list);
        //添加适配器
        ShopCarAdapter adapter = new ShopCarAdapter(getActivity(), list);
        sc_listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}