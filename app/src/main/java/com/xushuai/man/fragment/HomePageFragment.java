package com.xushuai.man.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xushuai.man.R;

/**
 * date:2017/8/31
 * author:徐帅(acer)
 * funcation:首页的Fragment
 */

public class HomePageFragment extends Fragment implements View.OnClickListener {

    private View view;
    private ImageView zxing;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.home_fragment, null);
        //查找控件
        initView();
        return view;
    }

    private void initView() {
        zxing = (ImageView) view.findViewById(R.id.zxing);

        //设置监听事件
        zxing.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zxing:

                break;
        }
    }
}