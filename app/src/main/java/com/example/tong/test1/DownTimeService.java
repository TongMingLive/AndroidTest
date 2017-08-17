package com.example.tong.test1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class DownTimeService extends Service {
    private myThread thread;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int count = intent.getIntExtra("count",0);
        if (thread == null){
            thread = new myThread(count);
            thread.start();
        }else if (!thread.isAlive()){
            thread.start();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private class myThread extends Thread{
        int count;
        myThread(int count){
            this.count = count;
        }
        @Override
        public void run() {
            super.run();
            while (count>0){
                try {
                    sleep(1000);
                    count--;
                    Intent intent = new Intent();
                    intent.setAction("com.time.down.action");
                    intent.putExtra("count",count);
                    sendBroadcast(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            stopSelf();//停止服务
        }
    }


}
