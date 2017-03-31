package com.example.macbook_.headline.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.webkit.WebView;

import com.example.macbook_.headline.R;
import com.example.macbook_.headline.utils.GestureDetectorUtils;

public class Home_Detail_CheckActivity extends AppCompatActivity {
    private GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__detail__check);
        WebView wv= (WebView) findViewById(R.id.home_detail_check_wv);
        String url = getIntent().getStringExtra("url");
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl(url);
        gestureDetector= GestureDetectorUtils.getGestureDetector(this);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
