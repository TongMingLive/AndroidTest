package com.example.tong.test1;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Tong on 2016/10/24.
 */

public class TaobaoPinjia extends AppCompatActivity {

    public void back(View v){
        finish();
    }

    //自定义toast显示时间方法
    public static void showToast(final Activity activity, final String word, final long time){
        activity.runOnUiThread(new Runnable() {
            public void run() {
                final Toast toast = Toast.makeText(activity, word, Toast.LENGTH_LONG);
                toast.show();
                android.os.Handler handler = new android.os.Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        toast.cancel();
                    }
                }, time);
            }
        });
    }

    Button regist_btn;
    EditText body_edit;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taobao_pinjia);
        body_edit = (EditText) findViewById(R.id.taobao_body);
        regist_btn = (Button)findViewById(R.id.regist);
        regist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (body_edit.getText() == null || body_edit.getText().equals("")){
                    showToast(TaobaoPinjia.this,"评价不能为空",500);
                }else{
                    showToast(TaobaoPinjia.this,"感谢您的评价",500);
                }
            }
        });
    }
}
