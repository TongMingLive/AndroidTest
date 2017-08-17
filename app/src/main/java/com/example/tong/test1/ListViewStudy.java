package com.example.tong.test1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tong on 2016/11/2.
 */

public class ListViewStudy extends AppCompatActivity {
    private ListView lv;
    private List<Map<String,String>> list = new ArrayList<Map<String,String>>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);
        lv = (ListView)findViewById(R.id.test_line);

        Map<String,String> map1 = new HashMap<String, String>();
        map1.put("week","one");
        map1.put("date","1");
        Map<String,String> map2 = new HashMap<String, String>();
        map2.put("week","two");
        map2.put("date","2");
        Map<String,String> map3 = new HashMap<String, String>();
        map3.put("week","three");
        map3.put("date","3");
        Map<String,String> map4 = new HashMap<String, String>();
        map4.put("week","four");
        map4.put("date","4");
        Map<String,String> map5 = new HashMap<String, String>();
        map5.put("week","five");
        map5.put("date","5");
        Map<String,String> map6 = new HashMap<String, String>();
        map6.put("week","six");
        map6.put("date","6");
        Map<String,String> map7 = new HashMap<String, String>();
        map7.put("week","seven");
        map7.put("date","7");

        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        list.add(map6);
        list.add(map7);

        //ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.my_item,list);
        SimpleAdapter adapter = new SimpleAdapter(this,list,R.layout.my_item,
                new String[]{"week","date"},
                new int[]{R.id.text_item1,R.id.text_item2});
        lv.setAdapter(adapter);
    }
}
