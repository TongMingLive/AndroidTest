package com.example.tong.test1;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Tong on 2016/11/22.
 */

public class FrameStudy extends AppCompatActivity {
    private ImageView img;
    private Button star,change,stop;
    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_activity);
        img = (ImageView)findViewById(R.id.fram_iv);
        star = (Button)findViewById(R.id.fram_start);
        change = (Button)findViewById(R.id.fram_change);
        stop = (Button)findViewById(R.id.fram_stop);

        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setBackgroundResource(R.drawable.fram_change);
                animationDrawable = (AnimationDrawable) img.getBackground();
                animationDrawable.start();
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setBackgroundResource(R.drawable.fram_change_two);
                animationDrawable = (AnimationDrawable) img.getBackground();
                animationDrawable.start();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDrawable.stop();
            }
        });
    }
}
