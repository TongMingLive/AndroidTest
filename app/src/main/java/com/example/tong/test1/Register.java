package com.example.tong.test1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Tong on 2016/10/25.
 */

public class Register extends AppCompatActivity {

    /*初始化对象*/
    private String rand;//验证码
    private int renovateTime = 9;//验证码刷新间隔时间
    private EditText Edit_name, Edit_psw1, Edit_psw2, Edit_phone, Edit_emile, Edit_id, Edit_check;
    private TextView checkRan;
    private Button view_btn1, view_btn2, check_btn, confirm_btn;

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

    //生成随机验证码
    private void getRan() {
        rand = "";
        String str = "0123456789";
        for (int i = 0; i < 4; i++) {
            rand += str.charAt(new Random().nextInt(10));
        }
    }

    /*判断用户名*/
    private Boolean numChecked(String num) {
        if (TextUtils.isEmpty(num)) {
            showToast(Register.this, "账号不能为空", 800);
            return false;
        } else if (num.trim().length() < 6) {
            showToast(Register.this, "账号不能小于6位", 800);
            return false;
        } else if (!num.trim().matches("^[A-Za-z0-9]+$")) {
            showToast(Register.this, "账号只能为数字和字母", 800);
            return false;
        } else {
            return true;
        }
    }

    /*判断密码1*/
    private Boolean pswChecked1(String psw1) {
        if (TextUtils.isEmpty(psw1)) {
            showToast(Register.this, "密码不能为空", 800);
            return false;
        } else if (psw1.length() != 6) {
            showToast(Register.this, "密码必须是6位", 800);
            return false;
        } else if (psw1.equals("111111") || psw1.equals("222222") || psw1.equals("333333") || psw1.equals("444444") || psw1.equals("555555") || psw1.equals("666666") || psw1.equals("777777") || psw1.equals("888888") || psw1.equals("999999") || psw1.equals("000000") || psw1.equals("123456")) {
            showToast(Register.this, "密码过于简单", 800);
            return false;
        } else {
            return true;
        }
    }

    /*判断密码2*/
    private Boolean pswChecked2(String psw1, String psw2) {
        if (TextUtils.isEmpty(psw2)) {
            showToast(Register.this, "请再次输入密码", 800);
            return false;
        } else if (!psw1.equals(psw2)) {
            showToast(Register.this, "两次密码输入不一致", 800);
            return false;
        } else {
            return true;
        }
    }

    /*判断手机号*/
    private Boolean phoneChecked(String phone) {
        if (TextUtils.isEmpty(phone)) {
            showToast(Register.this, "请输入手机号", 800);
            return false;
        } else if (phone.trim().length() != 11) {
            showToast(Register.this, "手机号必须为11位", 800);
            return false;
        } else if (!phone.trim().startsWith("1")) {
            showToast(Register.this, "手机号第一位数字必须是1", 800);
            return false;
        } else {
            return true;
        }
    }

    /*判断邮箱*/
    private Boolean emileChecked(String emile) {
        if (TextUtils.isEmpty(emile)) {
            showToast(Register.this, "邮箱不能为空", 800);
            return false;
        } else if (!emile.contains("@") || !emile.contains(".")) {
            showToast(Register.this, "邮箱必须包含“@”和“.”", 800);
            return false;
        } else if (emile.indexOf("@") != emile.lastIndexOf("@")) {
            showToast(Register.this, "“@”符号只能出现一次", 800);
            return false;
        } else if (emile.indexOf("@") > emile.lastIndexOf(".")) {
            showToast(Register.this, "“@”后必须跟“xx.xx”", 800);
            return false;
        } else if (!emile.matches("^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$")) {
            showToast(Register.this, "邮箱格式错误，请检查", 800);
            return false;
        } else {
            return true;
        }
    }

    /*判断身份证*/
    private Boolean idChecked(String id) {
        if (TextUtils.isEmpty(id)) {
            showToast(Register.this, "请输入身份证号", 800);
            return false;
        } else if (!id.matches("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}[0-9Xx]$")) {
            showToast(Register.this, "身份证输入错误", 800);
            return false;
        } else {
            return true;
        }
    }

    /*判断验证码*/
    private Boolean checkChecked(String check) {
        if (TextUtils.isEmpty(check)) {
            showToast(Register.this, "请输入验证码", 800);
            return false;
        } else if (!check.equals(rand)) {
            showToast(Register.this, "验证码输入错误", 800);
            return false;
        } else {
            return true;
        }
    }

    /*******************************************************************************************************************/

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Edit_name = (EditText) findViewById(R.id.register_numText);
        Edit_psw1 = (EditText) findViewById(R.id.register_pswText1);
        Edit_psw2 = (EditText) findViewById(R.id.register_pswText2);
        Edit_phone = (EditText) findViewById(R.id.register_phoneText);
        Edit_emile = (EditText) findViewById(R.id.register_emileText);
        Edit_id = (EditText) findViewById(R.id.register_IDText);
        Edit_check = (EditText) findViewById(R.id.register_checkText);
        checkRan = (TextView) findViewById(R.id.register_checkRan);
        view_btn1 = (Button) findViewById(R.id.register_pswView1);
        view_btn2 = (Button) findViewById(R.id.register_pswView2);
        check_btn = (Button) findViewById(R.id.register_checkBtn);
        confirm_btn = (Button) findViewById(R.id.register_confirmBtn);
        getRan();//产生随机验证码；
        checkRan.setText(rand);//随机验证码数字放入验证显示框

        /*密码显示隐藏按钮*/
        view_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (view_btn1.getText().equals("显示")) {
                    Edit_psw1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    view_btn1.setText("隐藏");
                    Edit_psw1.setSelection(Edit_psw1.getText().length());
                } else {
                    Edit_psw1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    view_btn1.setText("显示");
                    Edit_psw1.setSelection(Edit_psw1.getText().length());
                }
            }
        });
        view_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (view_btn2.getText().equals("显示")) {
                    Edit_psw2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    view_btn2.setText("隐藏");
                    Edit_psw2.setSelection(Edit_psw2.getText().length());
                } else {
                    Edit_psw2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    view_btn2.setText("显示");
                    Edit_psw2.setSelection(Edit_psw2.getText().length());
                }
            }
        });

        /*产生随机验证码按钮*/
        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRan();
                checkRan.setText(rand);
                final Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        check_btn.setText("（" + renovateTime + "）");
                        check_btn.setClickable(false);
                        check_btn.setBackgroundColor(0xFFAADDFF);
                        handler.postDelayed(this, 1000);//定时器执行间隔时间
                        if (renovateTime == 0) {
                            check_btn.setText("刷新");
                            check_btn.setClickable(true);
                            check_btn.setBackgroundColor(0xFF00AAFF);
                            handler.removeCallbacks(this);//关闭定时器
                            renovateTime = 9;
                        }
                        renovateTime--;
                    }
                };
                handler.postDelayed(runnable, 0);//打开定时器
            }
        });

        /*注册按钮*/
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Edit_name.getText().toString();
                String psw1 = Edit_psw1.getText().toString();
                String psw2 = Edit_psw2.getText().toString();
                String phone = Edit_phone.getText().toString();
                String emile = Edit_emile.getText().toString();
                String id = Edit_id.getText().toString();
                String check = Edit_check.getText().toString();
                if (    numChecked(name) &&
                        pswChecked1(psw1) &&
                        pswChecked2(psw1, psw2) &&
                        phoneChecked(phone) &&
                        emileChecked(emile) &&
                        idChecked(id) &&
                        checkChecked(check)) {
                    Intent intent = new Intent();
                    intent.setClass(Register.this, RegisterPass.class);
                    intent.putExtra("userName",name);
                    intent.putExtra("userPassword", psw2);
                    intent.putExtra("userPhone",phone);
                    intent.putExtra("userEmile",emile);
                    intent.putExtra("userId",id);
                    startActivity(intent);
                }
            }
        });
    }
}
