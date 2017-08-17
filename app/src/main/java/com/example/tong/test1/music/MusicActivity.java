package com.example.tong.test1.music;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tong.test1.PushUtil.ExampleApplication;
import com.example.tong.test1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tong- on 2017/5/25.
 */

public class MusicActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private boolean permission;
    private android.widget.ListView musiclv;
    private TextView musicsize;
    private android.widget.ImageView musicpic;
    private SeekBar musicseek;
    private TextView musictitle;
    private android.widget.ImageButton musicprevio;
    private android.widget.ImageButton musicstop;
    private android.widget.ImageButton musicnext;
    private List<Musics> list = new ArrayList<>();
    private MusicAdapter musicAdapter;
    boolean boo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_activity);
        this.musicnext = (ImageButton) findViewById(R.id.music_next);
        this.musicstop = (ImageButton) findViewById(R.id.music_stop);
        this.musicprevio = (ImageButton) findViewById(R.id.music_previo);
        this.musictitle = (TextView) findViewById(R.id.music_title);
        this.musicpic = (ImageView) findViewById(R.id.music_pic);
        this.musicsize = (TextView) findViewById(R.id.music_size);
        this.musiclv = (ListView) findViewById(R.id.music_lv);
        this.musicseek = (SeekBar) findViewById(R.id.music_seek);

        if (Build.VERSION.SDK_INT >= 23) {
            //sdk版本6.0及以后
            permission = PermissionTool.requestPermission(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0X123);
        }
        getMusic();
    }

    private void getMusic() {
        if (permission) {
            list = MusicManager.getInstance().getAllMusicInfo(this);
            musicsize.setText("(" + list.size() + "首)");
            MusicAdapter musicAdapter = new MusicAdapter(this);
            musicAdapter.setData(list);
            musiclv.setAdapter(musicAdapter);

            musiclv.setOnItemClickListener(this);

            musicprevio.setOnClickListener(this);
            musicstop.setOnClickListener(this);
            musicnext.setOnClickListener(this);

            Intent service = new Intent(this, MusicService.class);
            startService(service);

            //动态注册广播
            IntentFilter filter = new IntentFilter();
            filter.addAction("com.example.tong.test1");
            registerReceiver(br,filter);

            musicseek.setOnSeekBarChangeListener(seekBarChangeListener);

        } else {
            Toast.makeText(this, "需使用该功能请打开sd卡读取权限", Toast.LENGTH_SHORT).show();
        }
    }

    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Musics music = (Musics)intent.getSerializableExtra("music");
            if (music!=null){
                musicseek.setMax((int)(music.getDuration()/100));
                musictitle.setText(music.getTitle());
                musicseek.setProgress(ExampleApplication.musicService.getPlayPosition());
                musicAdapter.setSelected(list.indexOf(music));
            }
            //设置音乐播放的监听事件
            ExampleApplication.musicService.setMusicPlayListener(playInterface);
        }
    };

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (ExampleApplication.musicService != null){
                ExampleApplication.musicService.seekTo(seekBar.getProgress());
            }
        }
    } ;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.music_previo:
                ExampleApplication.musicService.upPlay();
                musicstop.setImageResource(R.mipmap.ic_launcher_stop);
                musictitle.setText(list.get(ExampleApplication.musicService.getCurrent()).getTitle());
                break;
            case R.id.music_stop:
                ExampleApplication.musicService.playPause();
                boo = ExampleApplication.musicService.checkPlay();
                musictitle.setText(list.get(ExampleApplication.musicService.getCurrent()).getTitle());
                musicstop.setImageResource(boo ? R.mipmap.ic_launcher_stop: R.mipmap.ic_launcher_play);
                break;
            case R.id.music_next:
                ExampleApplication.musicService.downPlay();
                musicstop.setImageResource(R.mipmap.ic_launcher_stop);
                musictitle.setText(list.get(ExampleApplication.musicService.getCurrent()).getTitle());
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        musictitle.setText(list.get(position).getTitle());
        musicstop.setImageResource(R.mipmap.ic_launcher_stop);
        if (ExampleApplication.musicService == null) return;
        ExampleApplication.musicService.playMusic(position);
    }

    private class MusicAdapter extends BaseAdapter {
        private Context context;
        private List<Musics> list = new ArrayList<>();
        private int selected = -1;

        public MusicAdapter(Context context) {
            this.context = context;
        }

        public void setData(List<Musics> list) {
            this.list = list;
        }

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

        //设置正在播放的歌曲
        public void setSelected(int selected){
            this.selected = selected;
            notifyDataSetChanged();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder hoder;
            if (convertView == null) {
                hoder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.music_item, null);
                hoder.title = (TextView) convertView.findViewById(R.id.music_item_title);
                hoder.artist = (TextView) convertView.findViewById(R.id.music_item_artist);
                convertView.setTag(hoder);
            } else {
                hoder = (ViewHolder) convertView.getTag();
            }

            hoder.title.setText(list.get(position).getTitle());
            String size = String.valueOf(Double.valueOf(list.get(position).getSize())/1048576).toString();
            size = size.substring(0,size.indexOf(".")+2);
            hoder.artist.setText(size+"M" + "\t" + list.get(position).getArtist());

            if (position == selected){
                convertView.setBackgroundColor(Color.parseColor("#000000"));
                hoder.title.setTextColor(Color.parseColor("#FFFFFF"));
                hoder.artist.setTextColor(Color.parseColor("#CFCFCF"));
            }else {
                convertView.setBackgroundColor(Color.TRANSPARENT);
                hoder.title.setTextColor(Color.parseColor("#000000"));
                hoder.artist.setTextColor(Color.parseColor("#CFCFCF"));
            }

            return convertView;
        }

        class ViewHolder {
            TextView title, artist;
        }

    }

    private MusicPlayInterface playInterface = new MusicPlayInterface() {
        @Override
        public void pulishProgress(int currentProgress) {
            musicseek.setProgress(currentProgress);
        }

        @Override
        public void getMusicDuration(Musics musics) {
            musicseek.setMax((int) musics.getDuration());
            musictitle.setText(musics.getTitle());
        }
    };

    //权限回调事件
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0x123) {
            for (int result : grantResults) {
                if (PackageManager.PERMISSION_DENIED == result) {
                    //用户拒绝授权
                    PermissionTool.showDialog(this, "拒绝后你将无法使用部分功能，是否前往修改");
                    break;
                } else {
                    getMusic();
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ExampleApplication.musicService == null){
            return;
        }else {
            boo = ExampleApplication.musicService.checkPlay();
            musicstop.setImageResource(boo ? R.mipmap.ic_launcher_stop: R.mipmap.ic_launcher_play);
            list = ExampleApplication.musicService.getMusicses();
            Log.e("MusicActivity", "list.get(ExampleApplication.musicService.getCurrent()).getDuration()/100:" + list.get(ExampleApplication.musicService.getCurrent()).getDuration()/100);
            musicseek.setMax((int) (list.get(ExampleApplication.musicService.getCurrent()).getDuration()/100));
            Log.e("MusicActivity", "ExampleApplication.musicService.getPlayPosition():" + ExampleApplication.musicService.getPlayPosition()/100);
            musicseek.setProgress(ExampleApplication.musicService.getPlayPosition()/100);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(br);
    }
}
