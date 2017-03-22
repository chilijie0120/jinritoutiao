package com.example.macbook_.headline.application;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.xutils.x;

/**
 * Created by MacBook- on 2017/3/14.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Config.DEBUG=true;
        UMShareAPI.get(this);
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(getApplicationContext()).build();
        ImageLoader.getInstance().init(configuration);
        x.Ext.init(this);
        x.Ext.setDebug(false);
    }

    {
        PlatformConfig.setQQZone("1106036236", "mjFCi0oxXZKZEWJs");
    }
}
