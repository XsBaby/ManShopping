package com.xushuai.man.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.xushuai.man.R;
import com.xushuai.man.view.ShopListActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private List<String> group = new ArrayList<>();

    public String[][] gridViewChild;
    String[][] child = {{""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}, {""}};
    LayoutInflater mInflater;
    Context context;

    public ExpandableListViewAdapter(Context context, List<String> group, String[][] gridViewChild) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.group = group;
        this.gridViewChild = gridViewChild;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child[groupPosition][childPosition];
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            mViewChild = new ViewChild();
            convertView = mInflater.inflate(R.layout.channel_expandablelistview_item, null);
            mViewChild.gridView = (GridView) convertView.findViewById(R.id.channel_item_child_gridView);
            convertView.setTag(mViewChild);
        } else {
            mViewChild = (ViewChild) convertView.getTag();
        }

        SimpleAdapter mSimpleAdapter = new SimpleAdapter(context, setGridViewData(gridViewChild[groupPosition]),
                R.layout.channel_gridview_item, new String[]{"channel_gridview_item"}, new int[]{R.id.channel_gridview_item});
        mViewChild.gridView.setAdapter(mSimpleAdapter);
        setGridViewListener(mViewChild.gridView);
        mViewChild.gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        return convertView;
    }

    /**
     * 设置gridview点击事件监听
     *
     * @param gridView
     */
    private void setGridViewListener(final GridView gridView) {
        gridView.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view instanceof TextView) {
                    //如果想要获取到哪一行，则自定义gridview的adapter，item设置2个textview一个隐藏设置id，显示哪一行
                    TextView tv = (TextView) view;
                    Toast.makeText(context, tv.getText(), Toast.LENGTH_SHORT).show();

                    if (position == 0 && tv.getText().equals("瑞士品牌")) {
                        Intent intent = new Intent(context, ShopListActivity.class);
                        context.startActivity(intent);
                    }
                    Log.e("hefeng", "gridView listaner position=" + position + "||text=" + tv.getText());
                }
            }
        });
    }

    /**
     * 设置gridview数据
     *
     * @param data
     * @return
     */
    private ArrayList<HashMap<String, Object>> setGridViewData(String[] data) {
        ArrayList<HashMap<String, Object>> gridItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < data.length; i++) {
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("channel_gridview_item", data[i]);
            gridItem.add(hashMap);
        }
        return gridItem;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            mViewChild = new ViewChild();
            convertView = mInflater.inflate(R.layout.f_classify_expandablelistview, null);
            mViewChild.textView = (TextView) convertView.findViewById(R.id.channel_group_name);
            convertView.setTag(mViewChild);
        } else {
            mViewChild = (ViewChild) convertView.getTag();
        }
        mViewChild.textView.setText(getGroup(groupPosition).toString());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    ViewChild mViewChild;

    static class ViewChild {
        TextView textView;
        GridView gridView;
    }
}