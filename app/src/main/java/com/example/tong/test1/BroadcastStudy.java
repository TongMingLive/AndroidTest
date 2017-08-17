package com.example.tong.test1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by tong- on 2017/4/10.
 */

public class BroadcastStudy extends AppCompatActivity {
    private Button btn;
    private int num = 9;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast_study);
        btn = (Button) findViewById(R.id.push_broadcast);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //intent.setClass(BroadcastStudy.this,MyBroadcast.class);
                intent.setAction("com.mybroad");
                intent.putExtra("name","张三");
                intent.putExtra("num",num+"");
                sendBroadcast(intent);
                num++;
            }
        });
    }
}
