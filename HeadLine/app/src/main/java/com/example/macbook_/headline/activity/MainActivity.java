package com.example.macbook_.headline.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.macbook_.headline.R;
import com.example.macbook_.headline.adapter.LeadViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager vp;
    private LinearLayout layout;
    private Button in;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private Intent intent;
    private List<ImageView> imageViews;
    private List<Integer> integers=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        addImage();
        addPoint();
        sp=getSharedPreferences("AA",MODE_PRIVATE);
        intent=new Intent(this,WelcomeActivity.class);
        boolean flag=sp.getBoolean("FLAG",false);
        if (flag){
            startActivity(intent);
            finish();
        }
        vp.setAdapter(new LeadViewPagerAdapter(integers,MainActivity.this));

    }

    private void addImage() {
        integers.add(R.mipmap.xinwen1);
        integers.add(R.mipmap.xinwen2);
        integers.add(R.mipmap.xinwen3);
    }

    private void addPoint() {
        imageViews=new ArrayList<>();
        for (int i = 0; i <integers.size() ; i++) {
            ImageView imageView=new ImageView(this);
            imageView.setImageResource(i==0?R.drawable.point_black:R.drawable.point_bai);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.rightMargin=10;
            imageViews.add(imageView);
            layout.addView(imageView,params);
        }
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        layout = (LinearLayout) findViewById(R.id.ll_point);
        in = (Button) findViewById(R.id.in);
        in.setOnClickListener(this);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                in.setVisibility(position==imageViews.size()-1? View.VISIBLE:View.GONE);
                for (int i = 0; i <imageViews.size() ; i++) {
                    imageViews.get(i).setImageResource(i==position?R.drawable.point_black:R.drawable.point_bai);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        editor=sp.edit();
        editor.putBoolean("FLAG",true);
        editor.commit();
        startActivity(intent);
        finish();
    }
}
