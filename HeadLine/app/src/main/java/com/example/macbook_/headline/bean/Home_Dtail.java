package com.example.macbook_.headline.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MacBook- on 2017/3/13.
 */
public class Home_Dtail implements Serializable{
    private String title;
    private String source;
    private List<Home_Image> image_list;
    private String url;
    private User_info middle_image;

    public Home_Dtail(String title, String source, String url, User_info middle_image) {
        this.title = title;
        this.source = source;
        this.url = url;
        this.middle_image = middle_image;
    }

    public User_info getUser_info() {
        return middle_image;
    }

    public void setUser_info(User_info user_info) {
        this.middle_image = user_info;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<Home_Image> getImage_list() {
        return image_list;
    }

    public void setImage_list(List<Home_Image> image_list) {
        this.image_list = image_list;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public class Home_Image implements Serializable{
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
    public static class User_info implements Serializable{
        private String url;

        @Override
        public String toString() {
            return "User_info{" +
                    "url='" + url + '\'' +
                    '}';
        }

        public User_info(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    @Override
    public String toString() {
        return "Home_Dtail{" +
                "title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", image_list=" + image_list +
                ", url='" + url + '\'' +
                ", user_info=" + middle_image +
                '}';
    }
}
