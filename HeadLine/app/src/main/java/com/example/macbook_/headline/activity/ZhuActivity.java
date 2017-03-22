package com.example.macbook_.headline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.macbook_.headline.R;
import com.example.macbook_.headline.fragment.AttentionFragment;
import com.example.macbook_.headline.fragment.HomePageFragment;
import com.example.macbook_.headline.fragment.SunFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class ZhuActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private RadioGroup rg;
    private RadioButton rb_home;
    private ImageView iv_shouji;
    private ImageView iv_qq;
    private int theme=R.style.AppTheme;
    private Button lixian;
    private Button riye;
    public static SlidingMenu slidingMenu;
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
    private SHARE_MEDIA[] list = {SHARE_MEDIA.QQ};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState!=null){
            theme = savedInstanceState.getInt("theme");
            setTheme(theme);
        }
        setContentView(R.layout.activity_zhu);

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        initView();
        gatSliding();
        initPlatforms();
    }
    private void gatSliding() {
        slidingMenu = new SlidingMenu(this);
        //设置侧滑的方向
        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置不让整个屏幕滑出
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        //设置阴影图片
        slidingMenu.setFadeDegree(0.4f);
        ////SlidingMenu划出时主页面显示的剩余宽度
        slidingMenu.setBehindOffset(200);
        //设置SlidingMenu菜单的宽度
       // slidingMenu.setBehindWidth(500);
        //设置滑动时拖拽效果
        slidingMenu.setBehindScrollScale(1);
        //让侧滑依附在activity上
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.slidingfragment);

        iv_shouji = (ImageView) findViewById(R.id.shouji);
        iv_qq = (ImageView) findViewById(R.id.qq);
        lixian = (Button) findViewById(R.id.lixian);
        riye = (Button) findViewById(R.id.riye);

        iv_shouji.setOnClickListener(this);
        iv_qq.setOnClickListener(this);
        lixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        riye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theme = (theme == R.style.AppTheme) ? R.style.NightAppTheme : R.style.AppTheme;
                recreate();
            }
        });
    }
    private void initView() {
        rg = (RadioGroup) findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(this);
        rb_home = (RadioButton) findViewById(R.id.rb_home);
        rb_home.setChecked(true);
    }

    private void getFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.zhu_fl,fragment).commit();

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

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.shouji:
            SMSSDK.initSDK(this,"1c5605b37e35c","b117bc400ec7817927c43fa22073b1c7");
            //打开注册页面
            RegisterPage registerPage = new RegisterPage();
            registerPage.setRegisterCallback(new EventHandler() {
                public void afterEvent(int event, int result, Object data) {
                    // 解析注册结果
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                        String country = (String) phoneMap.get("country");
                        String phone = (String) phoneMap.get("phone");
                    }
                }
            });
            registerPage.show(this);
            break;
        case R.id.qq:
            boolean isauth = UMShareAPI.get(this).isAuthorize(this, platforms.get(0).mPlatform);
            if (isauth) {
                Toast.makeText(ZhuActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
                UMShareAPI.get(ZhuActivity.this).deleteOauth(ZhuActivity.this, platforms.get(0).mPlatform, authListener);

            } else {
                Toast.makeText(ZhuActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ZhuActivity.this, ZhuActivity.class));
                finish();
                UMShareAPI.get(ZhuActivity.this).doOauthVerify(ZhuActivity.this, platforms.get(0).mPlatform, authListener);
            }
            break;
    }
    }
    private void initPlatforms() {
        platforms.clear();
        for (SHARE_MEDIA e : list) {
            if (!e.toString().equals(SHARE_MEDIA.GENERIC.toString())) {
                platforms.add(e.toSnsPlatform());
            }
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("theme", theme);
    }

    //恢复数据
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null){
            savedInstanceState.getInt("theme");
        }
    }

    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            // SocializeUtils.safeShowDialog(dialog);
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            //  SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(ZhuActivity.this, "成功了", Toast.LENGTH_LONG).show();
            //  notifyDataSetChanged();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            //  SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(ZhuActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            //   SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(ZhuActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };
}
