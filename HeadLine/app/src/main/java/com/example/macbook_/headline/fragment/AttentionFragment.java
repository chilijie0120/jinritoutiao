package com.example.macbook_.headline.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.macbook_.headline.R;
import com.example.macbook_.headline.activity.Home_Detail_CheckActivity;
import com.example.macbook_.headline.adapter.AttentionAdapter;
import com.example.macbook_.headline.bean.Home_Dtail;
import com.example.macbook_.headline.dao.MyDatabase;

import java.util.List;

/**
 * Created by MacBook- on 2017/3/11.
 */
public class AttentionFragment extends Fragment {
    private ImageView imageView;
    private ListView attention_lv;
    private MyDatabase db;
    private List<Home_Dtail> home_dtails;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.attentionfragment,null);
        attention_lv = (ListView) view.findViewById(R.id.attention_lv);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        db=new MyDatabase(getActivity());
        home_dtails = db.queryGuanzhu();
        attention_lv.setAdapter(new AttentionAdapter(home_dtails,getActivity()));
        attention_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), Home_Detail_CheckActivity.class);
                intent.putExtra("url",home_dtails.get(position).getUrl());
                Bundle bundle=new Bundle();
                bundle.putSerializable("object",home_dtails.get(position));
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });
    }
}
