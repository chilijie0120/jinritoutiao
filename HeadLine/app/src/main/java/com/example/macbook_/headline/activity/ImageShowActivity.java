package com.example.macbook_.headline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.macbook_.headline.R;
import com.example.macbook_.headline.adapter.ImageShowAdapter;
import com.example.macbook_.headline.bean.Home_Dtail;

import java.util.List;

public class ImageShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);
        ViewPager vp = (ViewPager) findViewById(R.id.photo_vp);
        //获取数据
        Intent intent = getIntent();
        int position = intent.getIntExtra("id", 0);
        List<Home_Dtail.Home_Image> images = (List<Home_Dtail.Home_Image>) intent.getSerializableExtra("images");
        ImageShowAdapter adapter = new ImageShowAdapter(images,this);
        vp.setAdapter(adapter);
        vp.setCurrentItem(position);
    }
}
