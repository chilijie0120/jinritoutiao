package com.example.macbook_.headline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.macbook_.headline.R;

public class WelcomeActivity extends AppCompatActivity {
private Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (msg.what==0){
            startActivity(new Intent(WelcomeActivity.this,ZhuActivity.class));
        }
    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ImageView imageView= (ImageView) findViewById(R.id.welcome_iv);
        imageView.setImageResource(R.mipmap.jrtt);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        handler.sendEmptyMessageDelayed(0,2000);


    }
}
