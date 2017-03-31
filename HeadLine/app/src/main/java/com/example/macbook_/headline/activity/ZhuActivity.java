package com.example.macbook_.headline.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macbook_.headline.R;
import com.example.macbook_.headline.adapter.SilidingListAdapter;
import com.example.macbook_.headline.bean.SilidingList;
import com.example.macbook_.headline.fragment.AttentionFragment;
import com.example.macbook_.headline.fragment.HomePageFragment;
import com.example.macbook_.headline.fragment.SunFragment;
import com.example.macbook_.headline.utils.NetWorkUtils;
import com.example.macbook_.headline.utils.SharedPrefrenceUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class ZhuActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup rg;
    private RadioButton rb_home;
    private ImageView iv_shouji;
    private ImageView iv_qq;
    private int theme = R.style.AppTheme;
    private Button lixian;
    private Button riye;
    public static SlidingMenu slidingMenu;
    public ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();
    private SHARE_MEDIA[] list = {SHARE_MEDIA.QQ};
    private ImageView touxiang;
    private TextView qq_zhanghao;
    private LinearLayout ll1;
    private LinearLayout ll2;
    private boolean isauth;
    private ListView sliding_list;
    private List<SilidingList> silidingLists=new ArrayList<>();
    private String iconurl;
    private String name;
    private boolean qq;
    private TextView tui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
           theme=savedInstanceState.getInt("theme");
            setTheme(theme);
        }
        setContentView(R.layout.activity_zhu);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        initView();
        gatSliding();
        initPlatforms();
        qq = SharedPrefrenceUtils.Shoucang(this).getSp("QQ");
        if (qq){
            String iconurl = SharedPrefrenceUtils.getShare(this).getString("iconurl","");
            String name = SharedPrefrenceUtils.getShare(this).getString("name", "");
            ImageOptions options=new ImageOptions.Builder().setCircular(true).setCrop(true).build();
            x.image().bind(touxiang, iconurl,options);
            x.image().bind(new HomePageFragment().getImageView(),iconurl,options);
            qq_zhanghao.setText(name);
            ll1.setVisibility(View.INVISIBLE);
            ll2.setVisibility(View.VISIBLE);
        }
        isauth = UMShareAPI.get(this).isAuthorize(this, platforms.get(0).mPlatform);
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
        touxiang = (ImageView) findViewById(R.id.touxiang);
        qq_zhanghao = (TextView) findViewById(R.id.qq_zhanghao);
        tui = (TextView) findViewById(R.id.siliding_tui);

        ll1 = (LinearLayout) findViewById(R.id.ll1);
        ll2 = (LinearLayout) findViewById(R.id.ll2);
        sliding_list = (ListView) findViewById(R.id.siliding_list);
        slidingListData();
        tui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefrenceUtils.Shoucang(ZhuActivity.this).add("QQ",false);
               recreate();
            }
        });
        iv_shouji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SMSSDK.initSDK(ZhuActivity.this, "1c5605b37e35c", "b117bc400ec7817927c43fa22073b1c7");
                //打开注册页面
                RegisterPage registerPage = new RegisterPage();
                registerPage.setRegisterCallback(new EventHandler() {
                    public void afterEvent(int event, int result, Object data) {
                        // 解析注册结果
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                            String country = (String) phoneMap.get("country");
                            String phone = (String) phoneMap.get("phone");
                        }
                    }
                });
                registerPage.show(ZhuActivity.this);
            }
        });


        iv_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isauth) {
                    Toast.makeText(ZhuActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
                    UMShareAPI.get(ZhuActivity.this).deleteOauth(ZhuActivity.this, platforms.get(0).mPlatform, authListener);

                } else {
                    Toast.makeText(ZhuActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                    UMShareAPI.get(ZhuActivity.this).doOauthVerify(ZhuActivity.this, platforms.get(0).mPlatform, authListener);
                }
                UMShareAPI.get(ZhuActivity.this).getPlatformInfo(ZhuActivity.this, platforms.get(0).mPlatform, authListener);
            }
        });
        lixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isAvailable = NetWorkUtils.isAvailable(ZhuActivity.this);
                if (!isAvailable){
                    Toast.makeText(ZhuActivity.this, "网络未连接，请及时连接", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                    startActivity(intent);
                }else{
                    //连接成功
                    Toast.makeText(ZhuActivity.this, "网络连接成功", Toast.LENGTH_SHORT).show();
                    downLoad();
                }
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

    private void downLoad() {
        String [] items = {"wifi","手机流量"};
        new AlertDialog.Builder(this).setTitle("网络选择").setIcon(R.mipmap.ic_launcher)
                .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                downloadApk();
                                break;
                            case 1:
                                boolean mobile = NetWorkUtils.isMobile(ZhuActivity.this);
                                if (mobile){
                                    Toast.makeText(ZhuActivity.this, "现在未使用wifi，将使用流量下载", Toast.LENGTH_SHORT).show();
                                    Intent wifiSettingsIntent  = new Intent("android.settings.WIFI_SETTINGS");
                                    startActivity(wifiSettingsIntent);
                                }
                                break;
                        }
                        dialog.dismiss();
                    }
                }).show();
    }

    private void downloadApk() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ZhuActivity.this);
        builder.setTitle("版本更新");
        builder.setMessage("现在检测到新版本，是否更新?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                updateApk();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create();
        builder.show();
    }

    private void updateApk() {
        String url = "http://imtt.dd.qq.com/16891/3B9164274F34F47DF2BEFF1FF4E3F064.apk?fsname=com.tencent.mobileqq_6.7.0_496.apk&csr=97c2";
        RequestParams params = new RequestParams(url);
        //保存到sd卡
        params.setSaveFilePath(Environment.getExternalStorageDirectory()+"/update/");
        //自动文件命令
        params.setAutoRename(true);
        //下载
        x.http().post(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onSuccess(File result) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(result),"application/vnd.android.package-archive");
                startActivity(intent);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {

            }
        });
    }

    private void slidingListData() {
       String title[]={"好友动态", "我的话题", "收藏", "活动", "商城", "反馈", "设置"};
        int photo[]={R.mipmap.dongtai,R.mipmap.huati,R.mipmap.shoucang,R.mipmap.huodong,
                R.mipmap.shangcheng,R.mipmap.fankui,R.mipmap.shezhi};
        for (int i = 0; i <title.length ; i++) {
            SilidingList list=new SilidingList(title[i],photo[i]);
            silidingLists.add(list);
        }

        sliding_list.setAdapter(new SilidingListAdapter(silidingLists,ZhuActivity.this));

    }

    private void initView() {
        rg = (RadioGroup) findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(this);
        rb_home = (RadioButton) findViewById(R.id.rb_home);
        rb_home.setChecked(true);
    }

    private void getFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.zhu_fl, fragment).commit();

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
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
       slidingMenu.showMenu();

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
            switch (action) {
                case ACTION_AUTHORIZE:
                    UMShareAPI mShareAPI = UMShareAPI.get(ZhuActivity.this);
                    mShareAPI.getPlatformInfo(ZhuActivity.this, SHARE_MEDIA.QQ, authListener);
                    break;
                case ACTION_GET_PROFILE:
                    iconurl =  data.get("iconurl");
                    name =  data.get("name");
                    ImageOptions options=new ImageOptions.Builder().setCircular(true).setCrop(true).build();
                    x.image().bind(touxiang,iconurl,options);
                    ImageView imageView = new HomePageFragment().getImageView();
                    x.image().bind(new HomePageFragment().getImageView(),iconurl,options);
                    qq_zhanghao.setText(name);
                    ll1.setVisibility(View.INVISIBLE);
                    ll2.setVisibility(View.VISIBLE);
                    SharedPrefrenceUtils.Shoucang(ZhuActivity.this).add("QQ",true);
                    SharedPrefrenceUtils.Shoucang(ZhuActivity.this).getEditor().putString("iconurl",iconurl).commit();
                    SharedPrefrenceUtils.Shoucang(ZhuActivity.this).getEditor().putString("name",name).commit();

                    break;


            }

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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }

}
