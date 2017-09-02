package com.xushuai.man.utils;

import com.xushuai.man.R;
import com.xushuai.man.fragment.HomePageFragment;
import com.xushuai.man.fragment.MineFragment;
import com.xushuai.man.fragment.ShopCarFragment;
import com.xushuai.man.fragment.TypeFragment;

/**
 * date:2017/8/31
 * author:徐帅(acer)
 * funcation: 工具类，设置底部导航字体和图片
 */

public class TabDb {
    /***
     * 获得底部所有项
     */
    public static String[] getTabsTxt() {
        String[] tabs = {"首页", "分类", "购物车", "个人"};
        return tabs;
    }

    /***
     * 获得所有Fragment
     */
    public static Class[] getFramgent() {
        Class[] cls = {HomePageFragment.class, TypeFragment.class, ShopCarFragment.class, MineFragment.class};
        return cls;
    }

    /***
     * 获得所有点击前的图片
     */
    public static int[] getTabsImg() {
        int[] img = {R.drawable.ic_nav_home, R.drawable.ic_nav_class, R.drawable.ic_nav_cart, R.drawable.ic_nav_user};
        return img;
    }

    /***
     * 获得所有点击后的图片
     */
    public static int[] getTabsImgLight() {
        int[] img = {R.drawable.ic_nav_home_press, R.drawable.ic_nav_class_press, R.drawable.ic_nav_cart_press, R.drawable.ic_nav_user_press};
        return img;
    }
}