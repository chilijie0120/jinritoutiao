package com.example.macbook_.headline.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macbook_.headline.R;
import com.example.macbook_.headline.bean.Home_Dtail;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by MacBook- on 2017/3/14.
 */
public class AttentionAdapter extends BaseAdapter {
    private List<Home_Dtail> homeDtails;
    private Context context;
    public AttentionAdapter(List<Home_Dtail> homeDtails, Context context) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder vh=null;
        if (convertView==null){
                  vh=new Viewholder();
                  convertView=View.inflate(context, R.layout.attentionlistview,null);
                  vh.iv= (ImageView) convertView.findViewById(R.id.attention_list_iv);
                  vh.tv1= (TextView) convertView.findViewById(R.id.attention_list_tv1);
                  vh.tv2= (TextView) convertView.findViewById(R.id.attention_list_tv2);
                  convertView.setTag(vh);
        }else {
            vh = (Viewholder) convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(homeDtails.get(position).getUser_info().getUrl(),vh.iv);
                vh.tv1.setText(homeDtails.get(position).getTitle());
                vh.tv2.setText(homeDtails.get(position).getSource());
        return convertView;
    }
    class Viewholder{
        ImageView iv;
        TextView tv1,tv2;

    }
}
