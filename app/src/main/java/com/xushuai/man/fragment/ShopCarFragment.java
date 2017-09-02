package com.xushuai.man.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xushuai.man.R;

/**
 * date:2017/8/31
 * author:徐帅(acer)
 * funcation:购物车的Fragment
 */

public class ShopCarFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.shopcar_fragment, null);
        return view;
    }
}