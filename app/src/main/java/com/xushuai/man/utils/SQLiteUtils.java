package com.xushuai.man.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xushuai.man.bean.SQLiteBean;
import com.xushuai.man.db.MyOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2017/9/11
 * author:徐帅(acer)
 * funcation:封装的数据库的帮助类
 */

public class SQLiteUtils {

    private SQLiteDatabase sd;

    public SQLiteUtils(Context context) {
        MyOpenHelper openHelper = new MyOpenHelper(context);
        sd = openHelper.getWritableDatabase();
    }

    //数据库添加数据的方法
    public void insert(String name, String price, String imageurl) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("price", price);
        values.put("imageurl", imageurl);
        long insert = sd.insert("scar", null, values);
        System.out.println(insert + "行受影响-----------");
    }

    //数据库删除数据的方法
    public void delete(String name, String price, String imageurl) {
        int delete = sd.delete("scar", "_id = 1", null);
        System.out.println("被影响的行数-----" + delete);
    }

    //数据库添加数据的方法
    public List<SQLiteBean> query() {
        List<SQLiteBean> list = new ArrayList<>();
        Cursor cursor = sd.query("scar", null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String sdname = cursor.getString(cursor.getColumnIndex("name"));
            String sdprice = cursor.getString(cursor.getColumnIndex("price"));
            String sdimage = cursor.getString(cursor.getColumnIndex("imageurl"));
            SQLiteBean sqLiteBean = new SQLiteBean(sdname, "￥"+sdprice, sdimage);
            list.add(sqLiteBean);

            System.out.println(id + "-----" + sdname + "-----" + sdprice + "-----" + sdimage);
        }
        return list;
    }
}