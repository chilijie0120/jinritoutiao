package com.example.macbook_.headline.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macbook_.headline.R;
import com.example.macbook_.headline.activity.ImageShowActivity;
import com.example.macbook_.headline.bean.Home_Dtail;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MacBook- on 2017/3/14.
 */
public class HomeDetailAdapter extends BaseAdapter {
    private List<Home_Dtail> homeDtails;
    private Activity context;
    private final static int ONE=0;
    private final static int TWO=1;
    public HomeDetailAdapter(List<Home_Dtail> homeDtails, Activity context) {
        this.homeDtails = homeDtails;
        this.context = context;
    }

    @Override
    public int getCount() {
        return homeDtails.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        Viewholder vh=null;
        Viewholder1 vh1=null;

        if (convertView==null){
          switch (type){
              case ONE:
                  vh=new Viewholder();
                  convertView=View.inflate(context, R.layout.detaillistview,null);
                  vh.tv1= (TextView) convertView.findViewById(R.id.detaillist_tv1);
                  vh.tv2= (TextView) convertView.findViewById(R.id.detaillist_tv2);
                  vh.iv1= (ImageView) convertView.findViewById(R.id.detaillist_iv3);
                  vh.iv1.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          fenXiang(homeDtails.get(position).getUrl());
                      }
                  });
                  convertView.setTag(vh);
                  break;
              case TWO:
                  vh1=new Viewholder1();
                  convertView=View.inflate(context, R.layout.detaillistview1,null);
                  vh1.tv11= (TextView) convertView.findViewById(R.id.detaillist1_tv1);
                  vh1.tv12= (TextView) convertView.findViewById(R.id.detaillist1_tv2);
                  vh1.iv1= (ImageView) convertView.findViewById(R.id.detaillist1_iv1);
                  vh1.iv2= (ImageView) convertView.findViewById(R.id.detaillist1_iv2);
                  vh1.iv3= (ImageView) convertView.findViewById(R.id.detaillist1_iv3);
                  vh1.ll= (LinearLayout) convertView.findViewById(R.id.honedetail_ll);
                  vh1.iv4= (ImageView) convertView.findViewById(R.id.detaillist1_iv4);
                  vh1.iv4.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          fenXiang(homeDtails.get(position).getUrl());
                      }
                  });
                  for (int i = 0; i <vh1.ll.getChildCount() ; i++) {
                      final int finalI = i;
                      vh1.ll.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              Intent intent = new Intent(context, ImageShowActivity.class);
                              intent.putExtra("id", finalI);
                          intent.putExtra("images", (Serializable) homeDtails.get(position).getImage_list());
                          context.startActivity(intent);
                          }
                      });
                  }

                  convertView.setTag(vh1);
                  break;
          }


        }else{
            switch (type){
                case ONE:
                    vh= (Viewholder) convertView.getTag();
                    break;
                case TWO:
                    vh1= (Viewholder1) convertView.getTag();
                    break;
            }

        }
        switch (type){
            case ONE:
                vh.tv1.setText(homeDtails.get(position).getTitle());
                vh.tv2.setText(homeDtails.get(position).getSource());
                break;
            case TWO:
                vh1.tv11.setText(homeDtails.get(position).getTitle());
                vh1.tv12.setText(homeDtails.get(position).getSource());
                ImageLoader.getInstance().displayImage(homeDtails.get(position).getImage_list().get(0).getUrl(),vh1.iv1);
                ImageLoader.getInstance().displayImage(homeDtails.get(position).getImage_list().get(1).getUrl(),vh1.iv2);
                ImageLoader.getInstance().displayImage(homeDtails.get(position).getImage_list().get(2).getUrl(),vh1.iv3);
                break;
        }

        return convertView;
    }

    private void fenXiang(String url) {

        //UMImage umImage = new UMImage(context, url);
        UMWeb umWeb=new UMWeb(url);
        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.QQ)
                .withText("hello")
                .withMedia(umWeb)
                .setCallback(umShareListener)
                .share();
    }
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);

            Toast.makeText(context, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(context, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(context, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {

        if (homeDtails.get(position).getImage_list()!=null&&homeDtails.get(position).getImage_list().size()>0)
            return TWO;
        else
        return ONE;
    }

    class Viewholder{
        TextView tv1,tv2;
        ImageView iv1;
    }
    class Viewholder1{
        TextView tv11,tv12;
        ImageView iv1,iv2,iv3,iv4;
        LinearLayout ll;
    }

}
