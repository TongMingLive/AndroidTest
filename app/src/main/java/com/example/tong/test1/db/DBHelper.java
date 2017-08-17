package com.example.tong.test1.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tong.test1.db.util.Constant;

/**
 * Created by tong- on 2017/4/27.
 */

public class DBHelper extends SQLiteOpenHelper {
    private Context context;

    public DBHelper(Context context) {
        super(context, Constant.DB_NAME, null, Constant.VERSION);//创建数据库
        this.context = context;
    }

    //首次创建数据库时的回调方法
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table "+Constant.TB_USER+"(" +
                Constant.User.ID+" integer primary key autoincrement," +
                Constant.User.USERNAME+" varchar(200)," +
                Constant.User.PASSWORD+" varchar(200)" +
                ")";
        db.execSQL(sql);//执行sql语句
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
