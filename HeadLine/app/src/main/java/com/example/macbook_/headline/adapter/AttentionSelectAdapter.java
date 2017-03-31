package com.example.macbook_.headline.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.macbook_.headline.R;
import com.example.macbook_.headline.bean.AttentionList;
import com.example.macbook_.headline.dao.MyDatabase;
import com.example.macbook_.headline.utils.SharedPrefrenceUtils;

import java.util.List;

/**
 * Created by MacBook- on 2017/3/14.
 */
public class AttentionSelectAdapter extends BaseAdapter {
    private List<AttentionList> attentionLists;
    private Context context;
    private MyDatabase db;
    public AttentionSelectAdapter(List<AttentionList> attentionLists, Context context) {
        this.attentionLists = attentionLists;
        this.context = context;
    }

    @Override
    public int getCount() {
        return attentionLists.size();
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
        Viewholder vh=null;
        db=new MyDatabase(context);
        if (convertView==null){
                  vh=new Viewholder();
                  convertView=View.inflate(context, R.layout.attention_select_listview,null);
                  vh.cb= (CheckBox) convertView.findViewById(R.id.attention_select_list_cb);
                  vh.tv1= (TextView) convertView.findViewById(R.id.attention_select_list_tv);
            vh.cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPrefrenceUtils.Shoucang(context).add(attentionLists.get(position).getTitle(),// add添加健
                            !SharedPrefrenceUtils.getSp(attentionLists.get(position).getTitle()));
                    if(SharedPrefrenceUtils.getSp(attentionLists.get(position).getTitle())){
                        Log.i("ssss","111111111");
                        db.addAttention(attentionLists.get(position).getTitle(),attentionLists.get(position).getUrl());

                    }else{
                        Log.i("ssss","2222222222222");
                        db.deleteAttention(attentionLists.get(position).getTitle());

                    }
                }
            });
                  convertView.setTag(vh);
        }else {
            vh = (Viewholder) convertView.getTag();
        }
                vh.tv1.setText(attentionLists.get(position).getTitle());
        if(SharedPrefrenceUtils.Shoucang(context).getSp(attentionLists.get(position).getTitle())){
            vh.cb.setChecked(true);
        }else{
            vh.cb.setChecked(false);
        }
        return convertView;
    }
    class Viewholder{
        CheckBox cb;
        TextView tv1;

    }
}
