package com.example.tong.test1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by tong- on 2017/4/13.
 */

public class DateBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("DateBroadcast", "change");
        Toast.makeText(context, "日期改变了", Toast.LENGTH_SHORT).show();
    }
}
