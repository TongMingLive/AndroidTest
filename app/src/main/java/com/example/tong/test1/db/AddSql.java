package com.example.tong.test1.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tong.test1.PushUtil.ExampleApplication;
import com.example.tong.test1.R;
import com.example.tong.test1.db.util.Constant;

/**
 * Created by tong- on 2017/4/27.
 */

public class AddSql extends AppCompatActivity implements View.OnClickListener {
    private android.widget.EditText addusername;
    private android.widget.EditText addpassword;
    private android.widget.Button addbtn;
    private android.widget.Button addgetlen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_sql);
        this.addgetlen = (Button) findViewById(R.id.add_get_len);
        this.addbtn = (Button) findViewById(R.id.add_btn);
        this.addpassword = (EditText) findViewById(R.id.add_password);
        this.addusername = (EditText) findViewById(R.id.add_username);

        addbtn.setOnClickListener(this);
        addgetlen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_btn:
                String username = addusername.getText().toString();
                String password = addpassword.getText().toString();
                if (TextUtils.isEmpty(username)){
                    addusername.setError("用户名不能为空");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    addpassword.setError("密码不能为空");
                }
                String isnert_sql = "insert into "+ Constant.TB_USER+"("+
                            Constant.User.USERNAME+","+Constant.User.PASSWORD+
                        ") values('"+username+"','"+password+"')";
                SQLiteDatabase idb = ExampleApplication.dbHelper.getWritableDatabase();
                idb.execSQL(isnert_sql);//执行sql语句
                Snackbar.make(addbtn, "注册成功", Snackbar.LENGTH_SHORT).show();
                idb.close();
                break;
            case R.id.add_get_len:
                String select_len_sql = "select * from "+Constant.TB_USER;
                SQLiteDatabase sdb = ExampleApplication.dbHelper.getReadableDatabase();
                Cursor cursor = sdb.rawQuery(select_len_sql,null);
                if (cursor != null){
                    Snackbar.make(addbtn, "共有"+cursor.getCount()+"行数据", Snackbar.LENGTH_SHORT).show();
                    cursor.close();
                }
                sdb.close();
                break;
        }
    }
}
