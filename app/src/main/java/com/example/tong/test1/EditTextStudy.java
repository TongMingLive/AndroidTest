package com.example.tong.test1;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Tong on 2016/10/8.
 */

public class EditTextStudy extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_text);
        Button button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(EditTextStudy.this,"此功能待开发，敬请期待！",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void back(View v){
        finish();
    }
}
