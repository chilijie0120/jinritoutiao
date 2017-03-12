package com.example.macbook_.headline.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.macbook_.headline.R;
import com.example.macbook_.headline.fragment.AttentionFragment;
import com.example.macbook_.headline.fragment.HomePageFragment;
import com.example.macbook_.headline.fragment.SunFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class ZhuActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private ImageView head_iv;
    public static SlidingMenu slidingMenu;
    private FragmentManager fragmentManager;
    private RadioGroup rg;
    private RadioButton rb_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu);
        initView();
        gatSliding();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.zhu_fl,new HomePageFragment());
        transaction.commit();
        rb_home.setChecked(true);
    }
    private void gatSliding() {
        slidingMenu=new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setBehindOffset(200);
        slidingMenu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.slidingmenu);
    }
    private void initView() {
        rg = (RadioGroup) findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(this);
        rb_home = (RadioButton) findViewById(R.id.rb_home);

    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.zhu_fl,fragment);
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_home:
                getFragment(new HomePageFragment());
                break;
            case R.id.rb_sun:
                getFragment(new SunFragment());
                break;
            case R.id.rb_attention:
                getFragment(new AttentionFragment());
                break;
        }
    }
}
