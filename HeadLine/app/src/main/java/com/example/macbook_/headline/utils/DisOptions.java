package com.example.macbook_.headline.utils;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

/**
 * Created by MacBook- on 2017/3/14.
 */
public class DisOptions {
    public static DisplayImageOptions getOptions(){
        DisplayImageOptions options=new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();
        return options;
    }
}
