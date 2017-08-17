package com.example.tong.test1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Tong on 2016/11/17.
 */

public class AnimStudy extends AppCompatActivity {
    private ImageView img;
    private Button alpha_btn,tran_btn,rotate_btn,scale_btn,alpha_java,tran_java,rotate_java,scale_java;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_activity);
        img = (ImageView)findViewById(R.id.anim_img);
        alpha_btn = (Button)findViewById(R.id.anim_alpha_btn);
        tran_btn = (Button)findViewById(R.id.anim_tran_btn);
        rotate_btn = (Button)findViewById(R.id.anim_rotate_btn);
        scale_btn = (Button)findViewById(R.id.anim_scale_btn);
        alpha_java = (Button)findViewById(R.id.java_alpha_btn);
        tran_java = (Button)findViewById(R.id.java_tran_btn);
        rotate_java = (Button)findViewById(R.id.java_rotate_btn);
        scale_java = (Button)findViewById(R.id.java_scale_btn);


        alpha_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(AnimStudy.this,R.anim.anim_alpha);
                img.startAnimation(animation);
            }
        });

        tran_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(AnimStudy.this,R.anim.anim_tran);
                img.startAnimation(animation);
            }
        });

        rotate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(AnimStudy.this,R.anim.anim_rotate);
                img.startAnimation(animation);
            }
        });

        scale_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(AnimStudy.this,R.anim.anim_scale);
                img.startAnimation(animation);
            }
        });

        alpha_java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.0f);
                alphaAnimation.setDuration(3000);
                alphaAnimation.setFillAfter(true);
                img.startAnimation(alphaAnimation);
            }
        });

        tran_java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TranslateAnimation translateAnimation = new TranslateAnimation(
                        TranslateAnimation.RELATIVE_TO_SELF,0.0f,TranslateAnimation.RELATIVE_TO_PARENT,1.0f,
                        TranslateAnimation.RELATIVE_TO_SELF,0.0f,TranslateAnimation.RELATIVE_TO_PARENT,0.0f);
                translateAnimation.setDuration(3000);
                img.startAnimation(translateAnimation);
            }
        });

        rotate_java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RotateAnimation rotateAnimation = new RotateAnimation(0f,360f,
                        RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
                rotateAnimation.setDuration(3000);
                img.startAnimation(rotateAnimation);
            }
        });

        scale_java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScaleAnimation scaleAnimation = new ScaleAnimation(0f,2f,0f,2f,
                        ScaleAnimation.RELATIVE_TO_SELF,0.5f,ScaleAnimation.RELATIVE_TO_SELF,0.5f);
                scaleAnimation.setDuration(3000);
                img.setAnimation(scaleAnimation);
            }
        });
    }

    //返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            startActivity(new Intent(AnimStudy.this, ActivityMain.class));
            overridePendingTransition(R.anim.back_tran_star,R.anim.back_tran_exit);
            AnimStudy.this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
