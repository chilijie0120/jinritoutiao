package com.example.macbook_.headline.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.macbook_.headline.R;
import com.example.macbook_.headline.bean.Sun;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by MacBook- on 2017/3/14.
 */
public class SunFragmentAdapter extends BaseAdapter {
    private List<Sun> list;
    private Context context;

    public SunFragmentAdapter(List<Sun> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder vh=null;
        if (convertView==null){
                  vh=new Viewholder();
                  convertView=View.inflate(context, R.layout.sundetaillistview,null);
            vh.tv1= (TextView) convertView.findViewById(R.id.sunlist_tv1);
            vh.tv2= (TextView) convertView.findViewById(R.id.sunlist_tv2);
            vh.player = (JCVideoPlayerStandard) convertView.findViewById(R.id.sun_player_video);
                  convertView.setTag(vh);
        }else{
                    vh= (Viewholder) convertView.getTag();
        }
        vh.tv1.setText(list.get(position).getTitle());
        vh.tv2.setText(list.get(position).getSource());
        boolean setUp =vh.player.setUp("http://gslb.miaopai.com/stream/ed5HCfnhovu3tyIQAiv60Q__.mp4", JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
        if (setUp) {
            Glide.with(context).load(list.get(position).getLarge_image_list().get(0).getUrl()).into(vh.player.thumbImageView);
        }
        return convertView;
    }

    class Viewholder{
        JCVideoPlayerStandard player;
        TextView tv1,tv2;
    }
}
