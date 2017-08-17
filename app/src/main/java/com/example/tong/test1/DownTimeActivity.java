package com.example.tong.test1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by tong- on 2017/4/12.
 */

public class DownTimeActivity extends AppCompatActivity {

    private android.widget.TextView dowmtext;
    private android.widget.Button dowmstart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.down_time);
        this.dowmstart = (Button) findViewById(R.id.dowm_start);
        this.dowmtext = (TextView) findViewById(R.id.dowm_text);

        dowmstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(DownTimeActivity.this,DownTimeService.class);
                intent.putExtra("count",60);
                startService(intent);
            }
        });

        //注册广播
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.time.down.action");
        registerReceiver(br,filter);

    }

    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int count = intent.getIntExtra("count",0);
            if (count == 0){
                dowmtext.setText("时间到");
            }else {
                dowmtext.setText(count+"秒");
            }

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(br);
    }
}
