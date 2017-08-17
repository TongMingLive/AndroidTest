package com.example.tong.test1.music;

import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by admin on 2017/5/18.
 */

public class MusicTool {
    //音乐播放对象
    private MediaPlayer mediaPlayer;
    public static final int PLAY_NULL = -1;//未播放过
    public static final int PLAY_IN = 1;//正在播放
    public static final int PLAY_NOT = 0;//音乐已暂停

    private static MusicTool musicTool;
    private MusicTool(){};

    /**
     * 返回当前实例
     * @return
     */
    public static MusicTool getInstance(){
        if(musicTool == null){
            musicTool = new MusicTool();
        }
        return musicTool;
    }

    /**
     * synchronized 同步锁
     * 音乐播放
     * @param path
     */
    public synchronized void startMusic(final String path){
        new Thread(){
            @Override
            public void run() {
                super.run();
                if(mediaPlayer == null){
                    mediaPlayer = new MediaPlayer();
                }
                mediaPlayer.reset();//重置
                try {
                    mediaPlayer.setDataSource(path);//设置数据源
                    mediaPlayer.prepare();//准备
                    mediaPlayer.start();//播放
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    //音乐暂停
    public void pause(){
        if (mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    //音乐继续播放
    public void play(){
        if (mediaPlayer != null && !mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    //判断是否在播放
    public int getType(){
        if (mediaPlayer == null) return PLAY_NULL;//未播放任何音乐
        else if (mediaPlayer.isPlaying()) return PLAY_IN;//正在播放
        else return PLAY_NOT;//音乐已暂停
    }
}
