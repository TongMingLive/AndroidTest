package com.example.tong.test1.music;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tong- on 2017/5/25.
 */

public class MusicManager {
    private List<Musics> list = new ArrayList<>();
    private static MusicManager musicManager = null;
    private MusicManager(){}
    public static MusicManager getInstance(){
        if (musicManager == null){
            musicManager = new MusicManager();
        }
        return musicManager;
    }

    //获取手机sd卡的音乐信息
    private void getMusicData(Context context){
        list.clear();
        //获取内容接收者对象
        ContentResolver resolver = context.getContentResolver();
        //查询
        Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,null);
        if (cursor!=null){
            while (cursor.moveToNext()){
                Musics musics = new Musics();
                musics.setTitle(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
                musics.setSize(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE)));
                musics.setPath(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
                musics.setAlbum(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)));
                musics.setDuration(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)));
                musics.setArtist(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
                musics.setId(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
                list.add(musics);
            }
            cursor.close();
        }
    }

    //返回音乐信息列表
    public List<Musics> getAllMusicInfo(Context context){
        if (list.size() == 0){
            getMusicData(context);
        }
        return list;
    }

    //刷新音乐信息
    public void refleshMusicInfo(Context context){
        getMusicData(context);
    }
}
