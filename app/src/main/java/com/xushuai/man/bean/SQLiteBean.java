package com.xushuai.man.bean;

/**
 * date:2017/9/11
 * author:徐帅(acer)
 * funcation:数据库实体类
*/

public class SQLiteBean {
    private String name;
    private String price;
    private String imageurl;

    public SQLiteBean(String name, String price, String imageurl) {
        this.name = name;
        this.price = price;
        this.imageurl = imageurl;
    }

    public SQLiteBean() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}