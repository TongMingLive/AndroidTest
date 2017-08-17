package com.example.tong.test1;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

/**
 * Created by tong- on 2017/4/10.
 */

public class MyBroadcast extends BroadcastReceiver {
    private NotificationManager notificationManager;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MyBroadcast", "setAction 广播1 无序广播");
        Log.d("广播","我收到了广播"+intent.getStringExtra("name"));
        Log.d("广播","铃声"+intent.getStringExtra("num"));

        //系统notificationManager管理者对象
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context);

        //设置状态文本
        builder.setTicker("您有一条新消息");
        //设置消息内容标题
        builder.setContentTitle("设置消息内容标题");
        //设置消息内容
        builder.setContentText("今天天气 15度");
        //设置图标
        builder.setSmallIcon(R.mipmap.ic_launcher);
        //设置消息提示音乐
        builder.setSound(Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI,intent.getStringExtra("num")));

        //设置点击跳转到相应activity
        Intent ten = new Intent();
        ten.setClass(context,ActivityMain.class);
        builder.setContentIntent(PendingIntent.getActivity(context,0x123,ten,PendingIntent.FLAG_CANCEL_CURRENT));

        Notification notification = builder.build();
        notificationManager.notify(0x123,notification);
    }
}
