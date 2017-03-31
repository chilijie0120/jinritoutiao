package com.example.macbook_.headline.bean;

/**
 * Created by MacBook- on 2017/3/25.
 */
public class AttentionList {
    private String title;
    private Boolean flag;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public AttentionList(String title, Boolean flag, String url) {
        this.title = title;
        this.flag = flag;
        this.url = url;
    }

    public AttentionList(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
