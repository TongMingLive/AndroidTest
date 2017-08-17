package com.example.tong.test1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Tong on 2016/10/25.
 */

public class RegisterPass extends AppCompatActivity {

    private String rand = (int) (Math.random() * (999999999 - 100000000 + 1) + 100000000) + "";
    private String name, psw, phone, emile, id;
    private TextView qq;
    private EditText userName, userPsw, userPhone, userEmile, userId;
    private Button btn, change, show;
    private Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerpass);

        qq = (TextView) findViewById(R.id.regist_number);
        userName = (EditText) findViewById(R.id.regist_userName);
        userPsw = (EditText) findViewById(R.id.regist_userPsw);
        userPhone = (EditText) findViewById(R.id.regist_userPhone);
        userEmile = (EditText) findViewById(R.id.regist_userEmile);
        userId = (EditText) findViewById(R.id.regist_userId);
        btn = (Button) findViewById(R.id.login_back);
        change = (Button) findViewById(R.id.regist_change);
        show = (Button) findViewById(R.id.regist_userPswShow);

        show.setVisibility(show.GONE);

        intent = getIntent();
        name = intent.getStringExtra("userName");
        psw = intent.getStringExtra("userPassword");
        phone = intent.getStringExtra("userPhone");
        emile = intent.getStringExtra("userEmile");
        id = intent.getStringExtra("userId");

        qq.setText(rand);
        userName.setText(name);
        userPsw.setText(psw);
        userPhone.setText(phone);
        userEmile.setText(emile);
        userId.setText(id);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (show.getText().equals("显示")) {
                    userPsw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    show.setText("隐藏");
                    userPsw.setSelection(userPsw.getText().length());
                } else {
                    userPsw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    show.setText("显示");
                    userPsw.setSelection(userPsw.getText().length());
                }
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (change.getText().equals("修改资料")) {
                    userName.setEnabled(true);
                    userPsw.setEnabled(true);
                    userPhone.setEnabled(true);
                    userEmile.setEnabled(true);
                    userId.setEnabled(true);
                    show.setVisibility(show.VISIBLE);
                    change.setText("保存资料");
                } else {
                    userName.setEnabled(false);
                    userPsw.setEnabled(false);
                    userPhone.setEnabled(false);
                    userEmile.setEnabled(false);
                    userId.setEnabled(false);
                    show.setVisibility(show.GONE);
                    change.setText("修改资料");
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences spf = RegisterPass.this.getSharedPreferences("qqUser",MODE_PRIVATE);
                Editor editor = spf.edit();
                editor.putString("userNumber",rand);
                editor.putString("userPassword",userPsw.getText().toString());
                editor.commit();

                Intent intent = new Intent();
                intent.setClass(RegisterPass.this, QqLogin.class);
                intent.putExtra("userNumber", rand);
                intent.putExtra("userPassword", userPsw.getText().toString());
                startActivity(intent);
            }
        });
    }
}
