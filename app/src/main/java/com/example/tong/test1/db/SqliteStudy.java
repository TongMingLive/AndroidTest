package com.example.tong.test1.db;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.tong.test1.PushUtil.ExampleApplication;
import com.example.tong.test1.R;
import com.example.tong.test1.db.util.Constant;
import com.example.tong.test1.db.util.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tong- on 2017/4/27.
 */

public class SqliteStudy extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private android.widget.ListView sqlitelv;
    private android.widget.Button sqlitebtn;
    private List<User> list = new ArrayList<>();
    private android.widget.SearchView sqliteselect;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_study);
        this.sqliteselect = (SearchView) findViewById(R.id.sqlite_select);
        this.sqlitebtn = (Button) findViewById(R.id.sqlite_btn);
        this.sqlitelv = (ListView) findViewById(R.id.sqlite_lv);

        sqlitelv.setOnItemClickListener(this);

        sqlitebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SqliteStudy.this,AddSql.class));
            }
        });

        sqliteselect.setSubmitButtonEnabled(true);
        sqliteselect.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getSelect(newText);
                return true;
            }
        });
    }

    private void getSelect(String key){
        list.clear();
        String select_len_sql = "select * from "+ Constant.TB_USER+" where "+Constant.User.ID+" like '%"+key+"%' or "+Constant.User.USERNAME+" like '%"+key+"%'";
        SQLiteDatabase sdb = ExampleApplication.dbHelper.getReadableDatabase();
        Cursor cursor = sdb.rawQuery(select_len_sql,null);
        if (cursor != null){
            while (cursor.moveToNext()){
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(Constant.User.ID)));
                user.setUsername(cursor.getString(cursor.getColumnIndex(Constant.User.USERNAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(Constant.User.PASSWORD)));
                list.add(user);
            }
            cursor.close();
        }
        myAdapter myAdapter = new myAdapter();
        sqlitelv.setAdapter(myAdapter);
        sdb.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSelect("");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(SqliteStudy.this,SqliteActivity.class);
        intent.putExtra("user",list.get(position));
        startActivity(intent);
    }

    private class myAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null){
                viewHolder = new ViewHolder();
                convertView = getLayoutInflater().inflate(R.layout.sqlite_item, null);
                viewHolder.sqliteitempassword = (TextView) convertView.findViewById(R.id.sqlite_item_password);
                viewHolder.sqliteitemusername = (TextView) convertView.findViewById(R.id.sqlite_item_username);
                viewHolder.sqliteitemid = (TextView) convertView.findViewById(R.id.sqlite_item_id);

                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder)convertView.getTag();
            }

            viewHolder.sqliteitemid.setText(list.get(position).getId()+"");
            viewHolder.sqliteitemusername.setText(list.get(position).getUsername());
            viewHolder.sqliteitempassword.setText(list.get(position).getPassword());

            return convertView;
        }

        class ViewHolder{
            TextView sqliteitemid,sqliteitemusername,sqliteitempassword;
        }
    }
}
