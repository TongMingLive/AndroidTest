package com.example.tong.test1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tong.test1.music.MusicActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tong- on 2017/5/25.
 */

public class DrawStudy extends AppCompatActivity implements View.OnClickListener {
    private android.widget.Button drawbtnstart;
    private android.support.v4.widget.DrawerLayout layoutdrawmain;
    private Button drawbtntimeout;
    private Button drawbtneasilytime;
    private Timer timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_activity);
        this.layoutdrawmain = (DrawerLayout) findViewById(R.id.layout_draw_main);
        this.drawbtnstart = (Button) findViewById(R.id.draw_btn_start);
        this.drawbtntimeout = (Button) findViewById(R.id.draw_btn_time_out);
        this.drawbtneasilytime = (Button) findViewById(R.id.draw_btn_easily_time);

        drawbtnstart.setOnClickListener(this);
        drawbtntimeout.setOnClickListener(this);
        drawbtneasilytime.setOnClickListener(this);

        layoutdrawmain.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //侧滑过程中回调
                setTitle(""+slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                //全部打开回调
                setTitle("打开");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                //完全关闭回调
                setTitle("关闭");
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                //滑动状态的改变

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.draw_btn_start:
                if (layoutdrawmain.isDrawerOpen(Gravity.START)){
                    //抽屉已打开--将其关闭
                    layoutdrawmain.closeDrawer(Gravity.START);
                }else {
                    layoutdrawmain.openDrawer(Gravity.START);
                }
                break;
            case R.id.draw_btn_time_out:
                //定时退出
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("定时退出");
                final EditText editText = new EditText(this);
                editText.setHint("请输入定时退出的时间（秒）");
                builder.setView(editText);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (TextUtils.isEmpty(editText.getText().toString())){
                            Toast.makeText(DrawStudy.this, "定时时间不能为空", Toast.LENGTH_SHORT).show();
                        }else if (!editText.getText().toString().matches("[0-9]*")){
                            Toast.makeText(DrawStudy.this, "时间只能为数字", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(DrawStudy.this, "该程序将于" + editText.getText().toString().trim() + "秒后关闭", Toast.LENGTH_SHORT).show();
                            int time = Integer.parseInt(editText.getText().toString().trim())*1000;
                            timer = new Timer();
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    //子线程
                                    Intent MyIntent = new Intent(Intent.ACTION_MAIN);
                                    MyIntent.addCategory(Intent.CATEGORY_HOME);
                                    startActivity(MyIntent);
                                    finish();
                                    //退回到桌面
                                    if (timer!=null){
                                        timer.cancel();
                                        timer = null;
                                    }
                                }
                            },time,5000);
                        }
                    }
                });
                builder.setNegativeButton("取消",null);
                builder.show();
                break;
            case R.id.draw_btn_easily_time:
                //轻松一刻
                startActivity(new Intent(this,MusicActivity.class));
                break;
        }
    }
}
