package com.example.tong.test1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyAidlService extends Service {

    private IMyName.Stub stub = new IMyName.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void testShow() throws RemoteException {
            Log.d("MyAidlService", "我是AIDL里面的方法");
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }
}
