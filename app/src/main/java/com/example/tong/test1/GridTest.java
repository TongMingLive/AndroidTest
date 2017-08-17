package com.example.tong.test1;

/**
 * Created by Tong on 2016/11/14.
 */

public class GridTest {
    private int image;
    private String title;

    public GridTest(int image, String title) {
        super();
        this.image = image;
        this.title = title;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
