package com.example.tong.test1;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Tong on 2016/10/20.
 */

public class BorderInput extends AppCompatActivity {

    Button btn = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.border_input);
        btn = (Button) findViewById(R.id.login_button);
        //注册监听事件
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //实例化intent对象
                Intent intent = new Intent();
                //设置目标activity
                intent.setClass(BorderInput.this,PhotoBrowse.class);
                //启动意图
                startActivity(intent);
                //一步
                //startActivity(new Intent(BorderInput.this,PhotoBrowse.class));
            }
        });
    }
}
