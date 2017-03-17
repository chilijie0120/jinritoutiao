package com.example.macbook_.headline.bean;

import java.util.List;

/**
 * Created by MacBook- on 2017/3/13.
 */
public class Sun {
    private String title;
    private String source;
    private List<Sun_Image> large_image_list;

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

    public List<Sun_Image> getLarge_image_list() {
        return large_image_list;
    }

    public void setLarge_image_list(List<Sun_Image> large_image_list) {
        this.large_image_list = large_image_list;
    }

    public class Sun_Image{
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
