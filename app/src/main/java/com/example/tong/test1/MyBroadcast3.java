package com.example.tong.test1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by tong- on 2017/4/11.
 */

public class MyBroadcast3 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MyBroadcast", "setAction 广播3 无序广播");
    }
}
