package com.example.tong.test1;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by tong on 17-4-7.
 */

public class ServletSudy extends AppCompatActivity implements View.OnClickListener {
    private Button start,get;
    private IMyName stub;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Toast.makeText(ServletSudy.this, "服务器启动成功", Toast.LENGTH_SHORT).show();
            stub = IMyName.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servlet_sudy);
        start = (Button) findViewById(R.id.servlet_start_btn);
        get = (Button) findViewById(R.id.servlet_get_btn);

        start.setOnClickListener(this);
        get.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.servlet_start_btn:
                Intent intent = new Intent();
                intent.setClass(ServletSudy.this,MyAidlService.class);
                bindService(intent,connection,BIND_AUTO_CREATE);
                break;
            case R.id.servlet_get_btn:
                if (stub == null){
                    Toast.makeText(this, "未启动服务", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        stub.testShow();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
