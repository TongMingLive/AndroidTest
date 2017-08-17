package com.example.tong.test1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tong.test1.Download.HtmlSocket;
import com.example.tong.test1.db.SqliteStudy;
import com.example.tong.test1.music.MusicActivity;
import com.example.tong.test1.mysocket.SocketActivity;
import com.example.tong.test1.thread.AsynctaskStudy;
import com.example.tong.test1.thread.ThreadPoolExecutor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tong on 2016/11/15.
 */

public class ActivityMain extends AppCompatActivity {
    private long exitTime = 0;
    private GridView gv;
    private List<GridTest> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gv = (GridView)findViewById(R.id.main_check_in);

        list.add(new GridTest(R.mipmap.sony,"TextView\n文本显示框"));//0
        list.add(new GridTest(R.mipmap.sony,"EditText\n文本输入框"));//1
        list.add(new GridTest(R.mipmap.sony,"计算器"));//2
        list.add(new GridTest(R.mipmap.sony,"ImageView\n图片显示控件"));//3
        list.add(new GridTest(R.mipmap.sony,"Button按钮"));//4
        list.add(new GridTest(R.mipmap.sony,"QQ登录"));//5
        list.add(new GridTest(R.mipmap.sony,"相册浏览"));//6
        list.add(new GridTest(R.mipmap.sony,"Relative相对布局"));//7
        list.add(new GridTest(R.mipmap.sony,"表单验证"));//8
        list.add(new GridTest(R.mipmap.sony,"评分星星"));//9
        list.add(new GridTest(R.mipmap.sony,"仿淘宝评价"));//10
        list.add(new GridTest(R.mipmap.sony,"ListView"));//11
        list.add(new GridTest(R.mipmap.sony,"自定义Adapter\n仿购物车"));//12
        list.add(new GridTest(R.mipmap.sony,"GridView网格布局"));//13
        list.add(new GridTest(R.mipmap.sony,"轻量级数据存储"));//14
        list.add(new GridTest(R.mipmap.sony,"补间动画"));//15
        list.add(new GridTest(R.mipmap.sony,"帧动画"));//16
        list.add(new GridTest(R.mipmap.sony,"属性动画"));//17
        list.add(new GridTest(R.mipmap.sony,"仿新浪微博Add按钮"));//18
        list.add(new GridTest(R.mipmap.sony,"Activity\n活动周期"));//19
        list.add(new GridTest(R.mipmap.sony,"ViewPage\n滑动视图"));//20
        list.add(new GridTest(R.mipmap.sony,"ViewPage\n广告轮播视图"));//21
        list.add(new GridTest(R.mipmap.sony,"Fragment\n碎片"));//22
        list.add(new GridTest(R.mipmap.sony,"Fragment\n仿微信底部菜单"));//23
        list.add(new GridTest(R.mipmap.sony,"HTML网络通信"));//24
        list.add(new GridTest(R.mipmap.sony,"进度条"));//25
        list.add(new GridTest(R.mipmap.sony,"servlet服务"));//26
        list.add(new GridTest(R.mipmap.sony,"广播服务"));//27
        list.add(new GridTest(R.mipmap.sony,"全局倒计时service"));//28
        list.add(new GridTest(R.mipmap.sony,"SQLite小型数据库"));//29
        list.add(new GridTest(R.mipmap.sony,"接收内容提供者的数据"));//30
        list.add(new GridTest(R.mipmap.sony,"侧滑菜单"));//31
        list.add(new GridTest(R.mipmap.sony,"音乐播放器"));//32
        list.add(new GridTest(R.mipmap.sony,"线程池"));//33
        list.add(new GridTest(R.mipmap.sony,"异步加载"));//34
        list.add(new GridTest(R.mipmap.sony,"socket通信"));//35

        myAdapter adapter = new myAdapter();
        gv.setAdapter(adapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position){
                    case 0:
                        intent.setClass(ActivityMain.this, TextViewStudy.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.setClass(ActivityMain.this, EditTextStudy.class);startActivity(intent);
                        break;
                    case 2:
                        intent.setClass(ActivityMain.this, Counter.class);startActivity(intent);
                        break;
                    case 3:
                        intent.setClass(ActivityMain.this, ImageViewStudy.class);startActivity(intent);
                        break;
                    case 4:
                        intent.setClass(ActivityMain.this, ButtonStudy.class);startActivity(intent);
                        break;
                    case 5:
                        intent.setClass(ActivityMain.this, QqLogin.class);startActivity(intent);
                        break;
                    case 6:
                        intent.setClass(ActivityMain.this, PhotoBrowse.class);startActivity(intent);
                        break;
                    case 7:
                        intent.setClass(ActivityMain.this, RelativeStudy.class);startActivity(intent);
                        break;
                    case 8:
                        intent.setClass(ActivityMain.this, Register.class);startActivity(intent);
                        break;
                    case 9:
                        intent.setClass(ActivityMain.this, RatingStudy.class);startActivity(intent);
                        break;
                    case 10:
                        intent.setClass(ActivityMain.this, TaobaoPinjia.class);startActivity(intent);
                        break;
                    case 11:
                        intent.setClass(ActivityMain.this, ListViewStudy.class);startActivity(intent);
                        break;
                    case 12:
                        intent.setClass(ActivityMain.this, BaseAdapterStudy.class);startActivity(intent);
                        break;
                    case 13:
                        intent.setClass(ActivityMain.this, GridViewStudy.class);startActivity(intent);
                        break;
                    case 14:
                        intent.setClass(ActivityMain.this, SharedPreferencesStudy.class);startActivity(intent);
                        break;
                    case 15:
                        intent.setClass(ActivityMain.this, AnimStudy.class);startActivity(intent);
                        break;
                    case 16:
                        intent.setClass(ActivityMain.this, FrameStudy.class);startActivity(intent);
                        break;
                    case 17:
                        intent.setClass(ActivityMain.this, PropertyStudy.class);startActivity(intent);
                        break;
                    case 18:
                        intent.setClass(ActivityMain.this, WeiboAddButton.class);startActivity(intent);
                        break;
                    case 19:
                        intent.setClass(ActivityMain.this, MyActivityA.class);startActivity(intent);
                        break;
                    case 20:
                        intent.setClass(ActivityMain.this, ViewPagerStudy.class);startActivity(intent);
                        break;
                    case 21:
                        intent.setClass(ActivityMain.this, CarouselViewStudy.class);startActivity(intent);
                        break;
                    case 22:
                        intent.setClass(ActivityMain.this, FragmentMain.class);startActivity(intent);
                        break;
                    case 23:
                        intent.setClass(ActivityMain.this, Wechat.class);startActivity(intent);
                        break;
                    case 24:
                        intent.setClass(ActivityMain.this, HtmlSocket.class);startActivity(intent);
                        break;
                    case 25:
                        intent.setClass(ActivityMain.this, ProgressbarStudy.class);startActivity(intent);
                        break;
                    case 26:
                        intent.setClass(ActivityMain.this, ServletSudy.class);startActivity(intent);
                        break;
                    case 27:
                        intent.setClass(ActivityMain.this, BroadcastStudy.class);startActivity(intent);
                        break;
                    case 28:
                        intent.setClass(ActivityMain.this, DownTimeActivity.class);startActivity(intent);
                        break;
                    case 29:
                        intent.setClass(ActivityMain.this, SqliteStudy.class);startActivity(intent);
                        break;
                    case 30:
                        intent.setClass(ActivityMain.this, MyContentProvider.class);startActivity(intent);
                        break;
                    case 31:
                        intent.setClass(ActivityMain.this, DrawStudy.class);startActivity(intent);
                        break;
                    case 32:
                        intent.setClass(ActivityMain.this, MusicActivity.class);startActivity(intent);
                        break;
                    case 33:
                        intent.setClass(ActivityMain.this, ThreadPoolExecutor.class);startActivity(intent);
                        break;
                    case 34:
                        intent.setClass(ActivityMain.this, AsynctaskStudy.class);startActivity(intent);
                        break;
                    case 35:
                        intent.setClass(ActivityMain.this, SocketActivity.class);startActivity(intent);
                        break;
                    default:
                        Toast.makeText(ActivityMain.this,"事件未绑定！",Toast.LENGTH_SHORT).show();
                        break;
                }
                overridePendingTransition(R.anim.activity_tran_star,R.anim.activity_tran_exit);
            }
        });
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
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(ActivityMain.this).inflate(R.layout.activity_main_item, null);
                holder.iv = (ImageView) convertView.findViewById(R.id.main_item_iv);
                holder.tv = (TextView) convertView.findViewById(R.id.main_item_tv);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.iv.setImageResource(list.get(position).getImage());
            holder.tv.setText(list.get(position).getTitle());

            return convertView;
        }
        class ViewHolder{
            ImageView iv;
            TextView tv;
        }
    }

    //再次返回键退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Snackbar.make(gv, "再按一次退出程序", Snackbar.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
