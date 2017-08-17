package com.example.tong.test1.PushUtil;

import android.app.Application;
import android.util.Log;

import com.example.tong.test1.db.DBHelper;
import com.example.tong.test1.music.MusicService;

import cn.jpush.android.api.JPushInterface;

/**
 * For developer startup JPush SDK
 * 
 * 一般建议在自定义 Application 类里初始化。也可以在主 Activity 里。
 */
public class ExampleApplication extends Application {
    public static DBHelper dbHelper = null;
    public static MusicService musicService = null;

    private static final String TAG = "JPush";

    @Override
    public void onCreate() {    	     
        Log.d(TAG, "[ExampleApplication] onCreate");
        super.onCreate();

        JPushInterface.setDebugMode(false); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush

        dbHelper = new DBHelper(this);
    }
}
