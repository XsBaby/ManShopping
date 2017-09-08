package com.xushuai.man.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xushuai.man.R;
import com.xushuai.man.view.LoginActivity;

/**
 * date:2017/8/31
 * author:徐帅(acer)
 * funcation:个人的Fragment
 */

public class MineFragment extends Fragment implements View.OnClickListener {

    private View view;
    private ImageView headImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.mine_fragment, null);
        //查找控件
        initView();
        return view;
    }

    private void initView() {
        headImage = (ImageView) view.findViewById(R.id.headImageView);

        headImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.headImageView:
                //跳转到登录界面
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}