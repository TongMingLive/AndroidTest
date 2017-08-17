package com.example.tong.test1;

import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Tong on 2016/11/22.
 */

public class PropertyStudy extends AppCompatActivity {
    private ImageView img;
    private Button alpha,tran;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_activity);
        img = (ImageView)findViewById(R.id.property_img);
        alpha = (Button)findViewById(R.id.property_alpha);
        tran = (Button)findViewById(R.id.property_tran);

        alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(img,"alpha",1f,0f,0.5f,0.1f,0.2f,0.8f,1f);
                animator.setDuration(10000).start();
            }
        });
        tran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rect frame = new Rect();
                getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
                int height = frame.height();
                int width = frame.width();

                Log.i("displayMsg","width:"+width+",height:"+height);

                ObjectAnimator animatorX = ObjectAnimator.ofFloat(img,"translationX",0f,(width-img.getWidth())/2);
                animatorX.setDuration(4000).start();
                ObjectAnimator animatorY = ObjectAnimator.ofFloat(img,"translationY",0f,height-img.getHeight());
                animatorY.setDuration(4000).start();
            }
        });
    }
}
