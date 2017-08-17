package com.example.tong.test1;

import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Tong on 2016/11/23.
 */

public class WeiboAddButton extends AppCompatActivity {
    private Button add, exit, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6;
    private LinearLayout opacity_activity, activity_btn;
    private RelativeLayout activity;

    private void openAnim(int height) {
        ObjectAnimator animatorTran = ObjectAnimator.ofFloat(opacity_activity, "translationY", 0f, height);
        animatorTran.setDuration(0).start();
        ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(opacity_activity, "alpha", 1f, 0f);
        animatorAlpha.setDuration(0).start();
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(activity_btn, "translationY", 0f, height);
        animatorY.setDuration(0).start();
        ObjectAnimator animatorYAlpha = ObjectAnimator.ofFloat(activity_btn, "alpha", 1f, 0f);
        animatorYAlpha.setDuration(0).start();
        ObjectAnimator animatorActivity = ObjectAnimator.ofFloat(activity, "translationY", 0f, height);
        animatorActivity.setDuration(0).start();
        ObjectAnimator animatorBtn1 = ObjectAnimator.ofFloat(btn_1, "translationY", 0f, height);
        animatorBtn1.setDuration(0).start();
        ObjectAnimator animatorBtn2 = ObjectAnimator.ofFloat(btn_2, "translationY", 0f, height);
        animatorBtn2.setDuration(0).start();
        ObjectAnimator animatorBtn3 = ObjectAnimator.ofFloat(btn_3, "translationY", 0f, height);
        animatorBtn3.setDuration(0).start();
        ObjectAnimator animatorBtn4 = ObjectAnimator.ofFloat(btn_4, "translationY", 0f, height);
        animatorBtn4.setDuration(0).start();
        ObjectAnimator animatorBtn5 = ObjectAnimator.ofFloat(btn_5, "translationY", 0f, height);
        animatorBtn5.setDuration(0).start();
        ObjectAnimator animatorBtn6 = ObjectAnimator.ofFloat(btn_6, "translationY", 0f, height);
        animatorBtn6.setDuration(0).start();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weibo_add_button);
        add = (Button) findViewById(R.id.weibo_add);
        activity = (RelativeLayout) findViewById(R.id.weibo_add_activity);
        activity_btn = (LinearLayout) findViewById(R.id.weibo_exit_activity);
        opacity_activity = (LinearLayout) findViewById(R.id.weibo_opacity_activity);
        exit = (Button) findViewById(R.id.weibo_exit_btn);
        btn_1 = (Button) findViewById(R.id.weibo_btn_1);
        btn_2 = (Button) findViewById(R.id.weibo_btn_2);
        btn_3 = (Button) findViewById(R.id.weibo_btn_3);
        btn_4 = (Button) findViewById(R.id.weibo_btn_4);
        btn_5 = (Button) findViewById(R.id.weibo_btn_5);
        btn_6 = (Button) findViewById(R.id.weibo_btn_6);

        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        final int height = frame.height();

        openAnim(height);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animatorTranAdd = ObjectAnimator.ofFloat(opacity_activity, "translationY", height, 0f);
                animatorTranAdd.setDuration(0).start();
                ObjectAnimator animatorAlphaAdd = ObjectAnimator.ofFloat(opacity_activity, "alpha", 0f, 0.97f);
                animatorAlphaAdd.setDuration(500).start();
                ObjectAnimator animatorExit = ObjectAnimator.ofFloat(activity_btn, "translationY", height, 0f);
                animatorExit.setDuration(0).start();
                ObjectAnimator animatorAlphaBtn = ObjectAnimator.ofFloat(activity_btn, "alpha", 0f, 1f);
                animatorAlphaBtn.setDuration(500).start();
                ObjectAnimator animatorActivityExit = ObjectAnimator.ofFloat(activity, "translationY", height, 0f);
                animatorActivityExit.setDuration(0).start();

                ObjectAnimator animatorBtn1Add = ObjectAnimator.ofFloat(btn_1, "translationY", height, 0f);
                animatorBtn1Add.setInterpolator(new AnticipateOvershootInterpolator(1f));
                animatorBtn1Add.setDuration(400).start();
                ObjectAnimator animatorBtn2Add = ObjectAnimator.ofFloat(btn_2, "translationY", height, 0f);
                animatorBtn2Add.setInterpolator(new AnticipateOvershootInterpolator(1f));
                animatorBtn2Add.setDuration(500).start();
                ObjectAnimator animatorBtn3Add = ObjectAnimator.ofFloat(btn_3, "translationY", height, 0f);
                animatorBtn3Add.setInterpolator(new AnticipateOvershootInterpolator(1f));
                animatorBtn3Add.setDuration(600).start();
                ObjectAnimator animatorBtn4Add = ObjectAnimator.ofFloat(btn_4, "translationY", height, 0f);
                animatorBtn4Add.setInterpolator(new AnticipateOvershootInterpolator(1f));
                animatorBtn4Add.setDuration(700).start();
                ObjectAnimator animatorBtn5Add = ObjectAnimator.ofFloat(btn_5, "translationY", height, 0f);
                animatorBtn5Add.setInterpolator(new AnticipateOvershootInterpolator(1f));
                animatorBtn5Add.setDuration(800).start();
                ObjectAnimator animatorBtn6Add = ObjectAnimator.ofFloat(btn_6, "translationY", height, 0f);
                animatorBtn6Add.setInterpolator(new AnticipateOvershootInterpolator(1f));
                animatorBtn6Add.setDuration(900).start();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animatorAlphaExit = ObjectAnimator.ofFloat(opacity_activity, "alpha", 0.97f, 0f);
                animatorAlphaExit.setDuration(500).start();
                ObjectAnimator animatorExit = ObjectAnimator.ofFloat(activity_btn, "translationY", 0f, height);
                animatorExit.setInterpolator(new AnticipateOvershootInterpolator(1f));
                animatorExit.setDuration(700).start();
                ObjectAnimator animatorExitBtn = ObjectAnimator.ofFloat(activity_btn, "alpha", 1f, 0f);
                animatorExitBtn.setDuration(500).start();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ObjectAnimator animatorTranExit = ObjectAnimator.ofFloat(opacity_activity, "translationY", 0f, height);
                        animatorTranExit.setDuration(0).start();
                        ObjectAnimator animatorActivityExit = ObjectAnimator.ofFloat(activity, "translationY", 0f, height);
                        animatorActivityExit.setDuration(0).start();
                    }
                }, 500);
            }
        });
    }
}
