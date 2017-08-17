package com.example.tong.test1;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Tong on 2016/11/16.
 */

public class SharedPreferencesStudy extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_preferences);
        tv = (TextView)findViewById(R.id.shared_tv);

        SharedPreferences spf = this.getSharedPreferences("msit",MODE_PRIVATE);

        boolean boo = spf.getBoolean("isFirst",true);
        if(boo){
            //第一次进入
            tv.setText("您是首次访问该页面");
            //向SharedPreferences存数据，首先拿到Editor对象
            Editor editor = spf.edit();
            //通过Editor对象存数据
            editor.putBoolean("isFirst",false);
            //提交数据
            editor.commit();
        }else{
            //非第一次进入
            tv.setText("非首次访问该页面");
        }


    }
}
