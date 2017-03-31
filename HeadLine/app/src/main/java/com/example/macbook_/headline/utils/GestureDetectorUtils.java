package com.example.macbook_.headline.utils;

import android.app.Activity;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by MacBook- on 2017/3/28.
 */
public class GestureDetectorUtils {
    public static GestureDetector getGestureDetector(final Activity activity){
        GestureDetector gestureDetector=new GestureDetector(activity,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                if (Math.abs(velocityY) < 100) {
                    // System.out.println("手指移动的太慢了");
                    return true;
                }

                // 手势向下 down
                if ((e2.getRawY() - e1.getRawY()) > 200) {
                    activity.finish();//在此处控制关闭
                    return true;
                }
                // 手势向上 up
                if ((e1.getRawY() - e2.getRawY()) > 200) {
                    return true;
                }


                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
        return gestureDetector;
    }
}
