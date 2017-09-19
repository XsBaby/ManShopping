package com.xushuai.man.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * date:2017/9/11
 * author:徐帅(acer)
 * funcation:保存购物车商品的数据库
 */

public class MyOpenHelper extends SQLiteOpenHelper {
    public MyOpenHelper(Context context) {
        super(context, "shopdb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table scar(_id integer primary key autoincrement,name varchar(50),price varchar(20),imageurl text(100))");
        db.execSQL("create table rlogin(_id integer primary key autoincrement,user varchar(50),password varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}