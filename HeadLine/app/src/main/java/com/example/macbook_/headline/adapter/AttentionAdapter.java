package com.example.macbook_.headline.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.macbook_.headline.R;
import com.example.macbook_.headline.bean.AttentionList;
import com.example.macbook_.headline.dao.MyDatabase;
import com.example.macbook_.headline.utils.SharedPrefrenceUtils;

import java.util.List;

/**
 * Created by MacBook- on 2017/3/14.
 */
public class AttentionAdapter extends BaseAdapter {
    private List<AttentionList> homeDtails;
    private Context context;
    private MyDatabase db;
    private ListView attention_lv;
    private RelativeLayout moren_rl;
    public AttentionAdapter(List<AttentionList> homeDtails, Context context, ListView attention_lv, RelativeLayout moren_rl) {
        this.homeDtails = homeDtails;
        this.context = context;
        this.attention_lv=attention_lv;
        this.moren_rl=moren_rl;
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        Viewholder vh=null;
        db=new MyDatabase(context);
        if (convertView==null){
                  vh=new Viewholder();
                  convertView=View.inflate(context, R.layout.attentionlistview,null);
                  vh.tv1= (TextView) convertView.findViewById(R.id.attention_list_tv1);
                  vh.tv2= (TextView) convertView.findViewById(R.id.attention_list_tv2);
                 vh.tv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPrefrenceUtils.Shoucang(context).add(homeDtails.get(position).getTitle(),// add添加健
                            !SharedPrefrenceUtils.getSp(homeDtails.get(position).getTitle()));
                    db.deleteAttention(homeDtails.get(position).getTitle());
                    homeDtails.remove(position);
                    notifyDataSetChanged();
                    if (homeDtails.size()==0){
                        moren_rl.setVisibility(View.VISIBLE);
                        attention_lv.setVisibility(View.GONE);
                    }else {
                        moren_rl.setVisibility(View.GONE);
                        attention_lv.setVisibility(View.VISIBLE);
                    }
                }
            });
                  convertView.setTag(vh);
        }else {
            vh = (Viewholder) convertView.getTag();
        }
                vh.tv1.setText(homeDtails.get(position).getTitle());
        return convertView;
    }
    class Viewholder{
        TextView tv1,tv2;

    }
}
