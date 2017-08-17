package com.example.tong.test1.music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.example.tong.test1.PushUtil.ExampleApplication;

import java.io.IOException;
import java.util.List;

public class MusicService extends Service {
    private MediaPlayer mediaPlayer = null;
    private int currentPosition = -1;//当前播放歌曲的下标
    private List<Musics> musicses = null;
    private MusicPlayInterface playInterface;
    private Handler handler = new Handler();

    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ExampleApplication.musicService = this;
        //获取音乐信息
        musicses = MusicManager.getInstance().getAllMusicInfo(this);
        //实例化mediaPlay对象
        mediaPlayer = new MediaPlayer();
        //音乐播放完成
        mediaPlayer.setOnCompletionListener(listener);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //回调播放进度值
            if (playInterface!=null){
                playInterface.pulishProgress(mediaPlayer.getCurrentPosition());
            }
            Log.e("MusicService","thisTime:"+mediaPlayer.getCurrentPosition()/100+"sumTime:"+musicses.get(currentPosition).getDuration()/100);
            handler.post(runnable);
        }
    };

    //开始播放
    private void start(){
        handler.removeCallbacks(runnable);
        mediaPlayer.start();
        handler.post(runnable);
        //回调播放时长
        if (playInterface!=null) {
            playInterface.getMusicDuration(musicses.get(currentPosition));
        }
    }

    public void setMusicPlayListener(MusicPlayInterface playInterface){
        this.playInterface = playInterface;
    }

    //音乐播放完成
    private MediaPlayer.OnCompletionListener listener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            downPlay();
        }
    };

    //判断当前mediaPlay播放的状态
    public boolean checkPlay(){
        if (mediaPlayer.isPlaying()) return true;
        else return false;
    }

    //返回currentPosition
    public int getCurrent(){
        return currentPosition;
    }

    //返回musics
    public List<Musics> getMusicses(){
        return musicses;
    }

    //播放音乐
    public void playMusic(int currentPosition) {
        this.currentPosition = currentPosition;
            try {
                if (mediaPlayer != null && mediaPlayer != null) {
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(musicses.get(currentPosition).getPath());
                    mediaPlayer.prepare();
                    start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    //播放音乐
    public void playMusic(Musics musics) {
            try {
                if (musics != null && mediaPlayer != null) {
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(musics.getPath());
                    mediaPlayer.prepare();
                    start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    //上一首
    public void upPlay(){
        if (--currentPosition < 0){
            currentPosition = musicses.size()-1;
        }
        playMusic(musicses.get(currentPosition));
    }

    //下一首
    public void downPlay(){
        if (++currentPosition > musicses.size()-1){
            currentPosition = 0;
        }
        playMusic(musicses.get(currentPosition));
    }

    //暂停与播放
    public void playPause(){
        if (mediaPlayer == null){
            return;
        }else if (currentPosition<0){
            currentPosition = 0;
            playMusic(musicses.get(currentPosition));
        }else if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            handler.removeCallbacks(runnable);
        }else {
            start();
        }
    }

    //返回当前播放的进度
    public int getPlayPosition(){
        if (mediaPlayer!=null){
            return mediaPlayer.getCurrentPosition();
        }else {
            return 0;
        }
    }

    //设置音乐播放的位置
    public void seekTo(int position){
        mediaPlayer.seekTo(position);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        /*Musics musics = (Musics) intent.getSerializableExtra("musics");
        playMusic(musics);*/
        Intent tent = new Intent("com.example.tong.test1");
        if (currentPosition>=0){
            tent.putExtra("music",musicses.get(currentPosition));
        }
        sendBroadcast(tent);//发送广播
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null){
            handler.removeCallbacks(runnable);//移除线程
            mediaPlayer.reset();
            mediaPlayer = null;
        }
    }
}
