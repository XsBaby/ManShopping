package com.xushuai.man.bean;

/**
 * date:2017/9/15
 * author:徐帅(acer)
 * funcation:保存注册登录信息的实体类
 */

public class ReLoginBean {
    private String username;
    private String userpwd;

    public ReLoginBean(String username, String userpwd) {
        this.username = username;
        this.userpwd = userpwd;
    }

    public ReLoginBean() {
    }
}