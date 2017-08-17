package com.example.tong.test1.db;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tong.test1.PushUtil.ExampleApplication;
import com.example.tong.test1.R;
import com.example.tong.test1.db.util.Constant;
import com.example.tong.test1.db.util.User;

/**
 * Created by tong- on 2017/5/2.
 */

public class SqliteActivity extends AppCompatActivity implements View.OnClickListener {
    private android.widget.TextView sqliteuserId;
    private android.widget.EditText sqliteuserName;
    private android.widget.EditText sqliteuserPassword;
    private android.widget.Button sqlitechangeBtn;
    private android.widget.Button sqlitedeleteBtn;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_activity);
        this.sqlitedeleteBtn = (Button) findViewById(R.id.sqlite_deleteBtn);
        this.sqlitechangeBtn = (Button) findViewById(R.id.sqlite_changeBtn);
        this.sqliteuserPassword = (EditText) findViewById(R.id.sqlite_userPassword);
        this.sqliteuserName = (EditText) findViewById(R.id.sqlite_userName);
        this.sqliteuserId = (TextView) findViewById(R.id.sqlite_userId);

        user = (User) getIntent().getSerializableExtra("user");

        sqliteuserId.setText(user.getId()+"");
        sqliteuserName.setHint(user.getUsername());
        sqliteuserPassword.setHint(user.getPassword());

        sqlitechangeBtn.setOnClickListener(this);
        sqlitedeleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sqlite_changeBtn:
                String username = sqliteuserName.getText().toString();
                String password = sqliteuserPassword.getText().toString();
                if (TextUtils.isEmpty(username)){
                    username = user.getUsername();
                }
                if (TextUtils.isEmpty(password)){
                    password = user.getPassword();
                }
                String change_sql = "update "+Constant.TB_USER+" set "+Constant.User.USERNAME+" = '"+username+"',"+Constant.User.PASSWORD+" = '"+password+"' where "+Constant.User.ID+" = "+user.getId();
                Log.d("SqliteActivity", change_sql);
                SQLiteDatabase cdb = ExampleApplication.dbHelper.getWritableDatabase();
                cdb.execSQL(change_sql);//执行sql语句
                Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                cdb.close();
                finish();
                break;
            case R.id.sqlite_deleteBtn:
                AlertDialog.Builder builder = new AlertDialog.Builder(SqliteActivity.this);
                builder.setTitle("删除提示");
                builder.setMessage("是否确认删除");
                builder.setNegativeButton("取消",null);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String delete_sql = "delete from "+Constant.TB_USER+" where "+Constant.User.ID+" = "+user.getId();
                        Log.d("SqliteActivity", delete_sql);
                        SQLiteDatabase ddb = ExampleApplication.dbHelper.getWritableDatabase();
                        ddb.execSQL(delete_sql);//执行sql语句
                        Toast.makeText(SqliteActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                        finish();
                        ddb.close();
                    }
                });
                builder.show();
                break;
        }
    }
}
