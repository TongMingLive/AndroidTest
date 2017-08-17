package com.example.tong.test1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Tong on 2016/11/24.
 */

public class MyActivityA extends AppCompatActivity {
    private Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity_a);
        btn = (Button)findViewById(R.id.my_activity_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyActivityA.this,MyActivityB.class));
            }
        });
        Log.i("ActivityA","onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("ActivityA","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ActivityA","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("ActivityA","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ActivityA","onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("ActivityA","onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ActivityA","onDestroy");
    }
}
