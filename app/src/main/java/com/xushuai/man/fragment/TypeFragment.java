package com.xushuai.man.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xushuai.man.R;
import com.xushuai.man.adapter.ExpandableListViewAdapter;
import com.xushuai.man.adapter.TypeListOneAdapter;
import com.xushuai.man.bean.TypeOneBean;
import com.xushuai.man.bean.f_classify_right01;
import com.xushuai.man.bean.f_classify_right02;
import com.xushuai.man.net.OkHttp;
import com.xushuai.man.net.xutils;
import com.xushuai.man.utils.MyDecoration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

import static com.xushuai.man.R.id.expandableListView;

/**
 * date:2017/8/31
 * author:徐帅(acer)
 * funcation:分类的Fragment
 */

public class TypeFragment extends Fragment {

    private View view;
    private RecyclerView listone_type;
    private List<TypeOneBean.DatasBean.ClassListBean> tdclist = new ArrayList<TypeOneBean.DatasBean.ClassListBean>();
    private String url = "http://169.254.30.70/mobile/index.php?act=goods_class";
    private String url2 = "http://169.254.30.70/mobile/index.php?act=goods_class&gc_id=1";
    private TypeListOneAdapter adapter;
    private ExpandableListView mExpandableListView;
    private ExpandableListViewAdapter mExpandableListViewAdapter;
    private static final String TAG = "Main";
    private List<String> list = new ArrayList<>();
    private List<f_classify_right01.DatasBean.ClassListBean> list2 = new ArrayList<>();

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
        mExpandableListView = (ExpandableListView) view.findViewById(expandableListView);
    }

    private void initData() {
        list = new ArrayList<>();
        //添加布局适配器
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        listone_type.setLayoutManager(manager);
        //添加适配器
        adapter = new TypeListOneAdapter(getActivity(), tdclist);
        listone_type.setAdapter(adapter);

        listone_type.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));
        //请求RecycleView数据的方法
        getData();

        //请求二级列表数据的方法
        getDataTwo();

        adapter.setOnItemClickListener(new TypeListOneAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
                Toast.makeText(getActivity(), "当前单击了" + tdclist.get(position).getGc_name(), Toast.LENGTH_SHORT).show();
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
                tdclist.addAll(class_list);
                //刷新适配器
                adapter.notifyDataSetChanged();
                System.out.println("ListOne result = " + result);
            }
        });
    }

    public void getDataTwo() {
        OkHttp.getAsync(url2, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                f_classify_right01 data = new Gson().fromJson(result, f_classify_right01.class);
                list2.addAll(data.getDatas().getClass_list());
                for (int i = 0; i < list2.size(); i++) {
                    list.add(list2.get(i).getGc_name());
                }
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        final String[][] arr = new String[list2.size()][];

                        for (int i = 0; i < list2.size(); i++) {
                            String itemurl = "http://169.254.30.70/mobile/index.php?act=goods_class&gc_id=" + list2.get(i).getGc_id();
                            String aa = xutils.getUrlConnect(itemurl);
                            f_classify_right02 data2 = new Gson().fromJson(aa, f_classify_right02.class);
                            List<f_classify_right02.DatasBean.ClassListBean> list3 = new ArrayList<f_classify_right02.DatasBean.ClassListBean>();
                            list3.addAll(data2.getDatas().getClass_list());
                            String[] arr2 = new String[list3.size()];
                            for (int a = 0; a < list3.size(); a++) {
                                arr2[a] = list3.get(a).getGc_name();
                            }
                            arr[i] = arr2;
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mExpandableListViewAdapter = new ExpandableListViewAdapter(getActivity(), list, arr);
                                mExpandableListView.setAdapter(mExpandableListViewAdapter);   //设置它的adapter
                                for (int i = 0; i < list.size(); i++) {
                                    mExpandableListView.expandGroup(i);
                                }
                            }
                        });
                    }
                }.start();
            }
        });
        //设置父item的点击事件
        mExpandableListView
                .setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent,
                                                View v, int groupPosition, long id) {
                        return false;
                    }
                });

        //设置子item的点击事件
        mExpandableListView
                .setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent,
                                                View v, int groupPosition, int childPosition,
                                                long id) {
                        Log.e(TAG, "groupPosition=" + groupPosition
                                + ",childPosition=" + childPosition);
                        return false;
                    }
                });
    }
}