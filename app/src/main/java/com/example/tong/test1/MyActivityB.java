package com.example.tong.test1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Tong on 2016/11/24.
 */

public class MyActivityB extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity_b);
        Log.i("ActivityB","onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("ActivityB","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ActivityB","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("ActivityB","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ActivityB","onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("ActivityB","onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ActivityB","onDestroy");
    }
}
