package com.example.tong.test1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Tong on 2016/10/24.
 */

public class RatingStudy extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_study);
    }

    public void back(View v){
        finish();
    }

}
