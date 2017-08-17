package com.example.tong.test1;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import android.widget.TextView;

/**
 * Created by Tong on 2016/10/11.
 */

public class ButtonStudy extends AppCompatActivity {

    private TextView tv;

    @Override
    public void onCreate(Bundle savedInstanceStatee) {
        super.onCreate(savedInstanceStatee);
        setContentView(R.layout.button_study);
        tv = (TextView) findViewById(R.id.tv_color);
    }

    //给按钮添加事件
    //吐司
    public void btnclick(View v){
        Toast.makeText(this,"请叫我吐司(Toast)",Toast.LENGTH_LONG).show();
    }
    //改变颜色
    public void redClick(View v){
        tv.setBackgroundColor(0xFFFF0020);
    }
    public void greenClick(View v){
        tv.setBackgroundColor(0xFF00FF20);
    }
    public void blueClick(View v){
        tv.setBackgroundColor(0xFF00CCFF);
    }
    public void sonyClick(View v){
        tv.setBackgroundResource(R.mipmap.sony);
    }


    public void back(View v){
        finish();
    }
}
