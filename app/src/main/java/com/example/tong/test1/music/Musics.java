package com.example.tong.test1.music;

import java.io.Serializable;

/**
 * Created by tong- on 2017/5/25.
 */

public class Musics implements Serializable{
    private int id;//歌曲id
    private String path;//歌曲路径
    private long duration;//音乐时长
    private String album;//专辑名
    private String artist;//歌手名
    private String title;//歌曲名
    private int size;//文件大小

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
