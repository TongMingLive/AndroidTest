package com.example.tong.test1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Tong on 2016/10/9.
 */

public class Counter extends AppCompatActivity {

    private TextView tv_text;
    private TextView tv_push;
    private Button btn;
    private String str;

    private SharedPreferences spf;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counter);
        tv_text = (TextView) findViewById(R.id.tv_text);
        tv_push = (TextView) findViewById(R.id.tv_push);
    }

    public void countClick(View v){
        btn = (Button) v;
        String btnText = btn.getText().toString();

        switch (btnText){
            case "AC":
                str = "";
                tv_push.setText(str+"0");
                tv_push.setTextSize(80);
                break;
            default:
                str += btnText;
                tv_push.setText(str);
                break;
        }

        if(tv_push.getText().length()>8){
            tv_push.setTextSize(60);
        }else if(tv_push.getText().length()>7){
            tv_push.setTextSize(70);
        }
    }

    public void back(View v){
        finish();
    }

}
