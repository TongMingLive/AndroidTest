package com.example.tong.test1;

import java.util.List;

/**
 * Created by Tong on 2016/10/27.
 */

public class QQUser {
    private String qqname;//用户名
    private String qqpwd;//密码

    public QQUser (String qqname,String qqpwd){
        this.qqname = qqname;
        this.qqpwd = qqpwd;
    }

    public void setQqname(String qqname) {
        this.qqname = qqname;
    }

    public String getQqname() {
        return qqname;
    }

    public void setQqpwd(String qqpwd) {
        this.qqpwd = qqpwd;
    }

    public String getQqpwd() {
        return qqpwd;
    }
}
