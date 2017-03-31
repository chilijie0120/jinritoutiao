package com.example.macbook_.headline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.macbook_.headline.R;
import com.example.macbook_.headline.adapter.AttentionSelectAdapter;
import com.example.macbook_.headline.bean.AttentionList;
import com.example.macbook_.headline.utils.GestureDetectorUtils;

import java.util.ArrayList;
import java.util.List;

public class AttentionSelectActivity extends AppCompatActivity {
    String [] name=new String[]{"推荐","本地","社会","娱乐","科技","汽车","体育","财经","军事","国际","健康"};
    private int count=20;
    private GestureDetector gestureDetector;

    private String[] jsonu = new String[]{
            "http://ic.snssdk.com/2/article/v25/stream/?count="+count+"&min_behot_time=1455521444&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455521401&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000",
            "http://ic.snssdk.com/2/article/v25/stream/?category=news_local&count="+count+"&min_behot_time=1455521226&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455521401&loc_mode=5&user_city=%E5%8C%97%E4%BA%AC&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&ap_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000",
            "http://ic.snssdk.com/2/article/v25/stream/?category=news_society&count="+count+"&min_behot_time=1455521720&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455522107&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000",
            "http://ic.snssdk.com/2/article/v25/stream/?category=news_entertainment&count="+count+"&min_behot_time=1455522338&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455522784&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000",
            "http://ic.snssdk.com/2/article/v25/stream/?category=news_tech&count="+count+"&min_behot_time=1455522427&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455522784&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000",
            "http://ic.snssdk.com/2/article/v25/stream/?category=news_car&count="+count+"&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455522784&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000",
            "http://ic.snssdk.com/2/article/v25/stream/?category=news_sports&count="+count+"&min_behot_time=1455522629&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455522784&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000",
            "http://ic.snssdk.com/2/article/v25/stream/?category=news_finance&count="+count+"&min_behot_time=1455522899&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455523440&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000",
            "http://ic.snssdk.com/2/article/v25/stream/?category=news_military&count="+count+"&min_behot_time=1455522991&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455523440&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000",
            "http://ic.snssdk.com/2/article/v25/stream/?category=news_world&count="+count+"&min_behot_time=1455523059&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455523440&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000",
            "http://ic.snssdk.com/2/article/v25/stream/?category=news_health&count="+count+"&bd_city=%E5%8C%97%E4%BA%AC%E5%B8%82&bd_latitude=40.049317&bd_longitude=116.296499&bd_loc_time=1455524092&loc_mode=5&lac=4527&cid=28883&iid=3642583580&device_id=11131669133&ac=wifi&channel=baidu&aid=13&app_name=news_article&version_code=460&device_platform=android&device_type=SCH-I919U&os_api=19&os_version=4.4.2&uuid=285592931621751&openudid=AC9E172CE2490000",
    };
    private List<AttentionList> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention_select);
        ListView select_lv= (ListView) findViewById(R.id.attention_select_lv);
        ImageView iv= (ImageView) findViewById(R.id.attention_fan);
        gestureDetector= GestureDetectorUtils.getGestureDetector(this);

        for (int i = 0; i <name.length ; i++) {
            AttentionList attentionList=new AttentionList(name[i],false,jsonu[i]);
            list.add(attentionList);
        }
       select_lv.setAdapter(new AttentionSelectAdapter(list,this));
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                setResult(1,intent);
               finish();
            }
        });
        select_lv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return false;
            }
        });
    }


}
