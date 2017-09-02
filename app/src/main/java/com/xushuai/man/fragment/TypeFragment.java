package com.xushuai.man.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xushuai.man.R;
import com.xushuai.man.adapter.TypeListOneAdapter;
import com.xushuai.man.bean.TypeOneBean;
import com.xushuai.man.net.OkHttp;
import com.xushuai.man.utils.MyDecoration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * date:2017/8/31
 * author:徐帅(acer)
 * funcation:分类的Fragment
 */

public class TypeFragment extends Fragment {

    private View view;
    private RecyclerView listone_type;
    private List<TypeOneBean.DatasBean.ClassListBean> list;
    private String url = "http://169.254.30.70/mobile/index.php?act=goods_class";
    private TypeListOneAdapter adapter;
    private ExpandableListView exblv_type;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.type_fragment, null);

        //查找控件
        initView();

        //添加数据
        initData();
        return view;
    }

    private void initView() {
        //左边的RecycleView
        listone_type = (RecyclerView) view.findViewById(R.id.listone_type);
        //右边的ExpandableListView
        exblv_type = (ExpandableListView) view.findViewById(R.id.exblv_type);
    }

    private void initData() {
        list = new ArrayList<>();
        //添加布局适配器
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        listone_type.setLayoutManager(manager);
        //添加适配器
        adapter = new TypeListOneAdapter(getActivity(), list);
        listone_type.setAdapter(adapter);

        listone_type.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));
        //网络请求的方法
        getData();

        adapter.setOnItemClickListener(new TypeListOneAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
                Toast.makeText(getActivity(), "当前单击了" + list.get(position).getGc_name(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getData() {
        OkHttp.getAsync(url, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson = new Gson();
                TypeOneBean bean = gson.fromJson(result, TypeOneBean.class);
                List<TypeOneBean.DatasBean.ClassListBean> class_list = bean.getDatas().getClass_list();
                list.addAll(class_list);
                adapter.notifyDataSetChanged();
                System.out.println("ListOne result = " + result);
            }
        });
    }
}