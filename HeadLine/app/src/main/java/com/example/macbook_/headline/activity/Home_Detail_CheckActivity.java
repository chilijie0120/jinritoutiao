package com.example.macbook_.headline.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.example.macbook_.headline.R;
import com.example.macbook_.headline.bean.Home_Dtail;
import com.example.macbook_.headline.dao.MyDatabase;

import java.util.List;

public class Home_Detail_CheckActivity extends AppCompatActivity {

    private Button iv;
    private Home_Dtail home_dtail;
    private MyDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__detail__check);
         db=new MyDatabase(this);
        WebView wv= (WebView) findViewById(R.id.home_detail_check_wv);
        iv = (Button) findViewById(R.id.home_detail_guanzhu);
        String url = getIntent().getStringExtra("url");
        Bundle bundle=getIntent().getBundleExtra("bundle");
        home_dtail = (Home_Dtail) bundle.get("object");
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl(url);

        List<Home_Dtail> dtails = db.queryGuanzhu();
        for (Home_Dtail home:dtails) {
        if (home_dtail.getTitle().equals(home.getTitle())){
            iv.setBackgroundColor(getResources().getColor(R.color.GuanzhuFu));
            iv.setText("已关注");
        }}
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv.setBackgroundColor(getResources().getColor(R.color.GuanzhuFu));
                iv.setText("已关注");
            db.addGuanzhu(home_dtail.getUser_info().getUrl(),home_dtail.getTitle(),home_dtail.getSource(),home_dtail.getUrl());


            }
        });
    }
}
