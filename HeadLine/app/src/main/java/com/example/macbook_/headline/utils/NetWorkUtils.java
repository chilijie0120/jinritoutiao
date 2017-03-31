package com.example.macbook_.headline.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by MacBook- on 2017/3/24.
 */
public class NetWorkUtils {
    public static boolean isAvailable(Context context){
        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info!=null){
            return true;
        }
        return false;
    }
    public static boolean isWifi(Context context){
        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info!=null&&info.getType()==manager.TYPE_WIFI){
            return true;
        }
        return false;
    }
    public static boolean isMobile(Context context){
        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info!=null&&info.getType()==manager.TYPE_MOBILE){
            return true;
        }
        return false;
    }
}
