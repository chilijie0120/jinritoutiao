package com.example.macbook_.headline.bean;

/**
 * Created by MacBook- on 2017/3/23.
 */
public class SilidingList {
    private String name;
    private int image;

    public SilidingList(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
