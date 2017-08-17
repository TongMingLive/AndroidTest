package com.example.tong.test1.music;

/**
 * Impl
 * Created by tong on 17-6-1.
 */

public interface MusicPlayInterface {

    //更新进度条-当前进度
    void pulishProgress(int currentProgress);
    //歌曲时长
    void getMusicDuration(Musics musics);

}
