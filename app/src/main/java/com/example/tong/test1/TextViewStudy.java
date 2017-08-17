package com.example.tong.test1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TextViewStudy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_view);
    }

    public void back(View v) {
        finish();
    }
}
