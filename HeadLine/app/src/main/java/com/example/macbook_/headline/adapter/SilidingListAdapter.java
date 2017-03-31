package com.example.macbook_.headline.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macbook_.headline.R;
import com.example.macbook_.headline.bean.SilidingList;

import java.util.List;

/**
 * Created by MacBook- on 2017/3/14.
 */
public class SilidingListAdapter extends BaseAdapter {
    private List<SilidingList> silidingLists;
    private Context context;
    public SilidingListAdapter(List<SilidingList> silidingLists, Context context) {
        this.silidingLists = silidingLists;
        this.context = context;
    }

    @Override
    public int getCount() {
        return silidingLists.size();
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
                  convertView=View.inflate(context, R.layout.silidinglist,null);
                  vh.iv= (ImageView) convertView.findViewById(R.id.slidinglist_iv);
                  vh.tv1= (TextView) convertView.findViewById(R.id.slidinglist_tv);
                  convertView.setTag(vh);
        }else {
            vh = (Viewholder) convertView.getTag();
        }
                vh.iv.setImageResource(silidingLists.get(position).getImage());
                vh.tv1.setText(silidingLists.get(position).getName());
        return convertView;
    }
    class Viewholder{
        ImageView iv;
        TextView tv1;

    }
}
