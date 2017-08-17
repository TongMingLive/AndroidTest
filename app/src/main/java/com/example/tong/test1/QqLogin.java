package com.example.tong.test1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Tong on 2016/10/11.
 */

public class QqLogin extends AppCompatActivity {

    private EditText numInput, pswInput;
    private Button loginBtn, register;
    private String userNumber, userPassword;
    private SharedPreferences spf;

    //自定义toast显示时间方法
    public static void showToast(final Activity activity, final String word, final long time) {
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qq_login);
        numInput = (EditText) findViewById(R.id.qqlogin_qqnum_input_text);
        pswInput = (EditText) findViewById(R.id.qqlogin_qqnum_input_psw);
        loginBtn = (Button) findViewById(R.id.qqlogin_qqnum_input_btn);
        register = (Button) findViewById(R.id.regist_newUser);

        spf = this.getSharedPreferences("qqUser",MODE_PRIVATE);

        register.setBackgroundColor(Color.TRANSPARENT);

        if(spf.getString("userNumber","userNull") != "userNull"){
            numInput.setText(spf.getString("userNumber","userNull"));
            pswInput.setText(spf.getString("userPassword","userNull"));
        }

        Intent intent = getIntent();
        userNumber = intent.getStringExtra("userNumber");
        if (userNumber != null) {
            numInput.setText(userNumber);
            pswInput.requestFocus();
            userPassword = intent.getStringExtra("userPassword");
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = numInput.getText().toString();
                String psw = pswInput.getText().toString();
                if (TextUtils.isEmpty(num)) {
                    showToast(QqLogin.this, "请输入QQ号", 800);
                } else if (!num.matches("^\\d{5,10}$")) {
                    showToast(QqLogin.this, "请输入正确账号", 800);
                } else if (TextUtils.isEmpty(psw)) {
                    showToast(QqLogin.this, "请输入QQ密码", 800);
                } else if (psw.length() < 6) {
                    showToast(QqLogin.this, "密码输入错误", 800);
                } else if (!num.equals(spf.getString("userNumber","userNull")) || num.equals("userNull") ) {
                    showToast(QqLogin.this, "账号不存在，请注册或检查后登录", 1200);
                } else if (!num.equals(spf.getString("userPassword","userNull")) || num.equals("userNull")) {
                    showToast(QqLogin.this, "密码错误，请检查后登录", 1200);
                } else {
                    showToast(QqLogin.this, "登录成功！", 800);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(QqLogin.this, Register.class);
                startActivity(intent);
            }
        });
    }
}
