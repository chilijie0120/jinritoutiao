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
public class HomeDetailAdapter extends BaseAdapter {
    private List<Home_Dtail> homeDtails;
    private Context context;
    private final static int ONE=0;
    private final static int TWO=1;
    public HomeDetailAdapter(List<Home_Dtail> homeDtails, Context context) {
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
//        if(homeDtails.size()==0) {
//            convertView = View.inflate(context, R.layout.detaillistview, null);
//            return convertView;
//        }
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

    }
    class Viewholder1{
        TextView tv11,tv12;
        ImageView iv1,iv2,iv3;
    }

}
