package com.example.macbook_.headline.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.macbook_.headline.R;
import com.example.macbook_.headline.activity.AttentionDetailActivity;
import com.example.macbook_.headline.activity.AttentionSelectActivity;
import com.example.macbook_.headline.adapter.AttentionAdapter;
import com.example.macbook_.headline.bean.AttentionList;
import com.example.macbook_.headline.dao.MyDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MacBook- on 2017/3/11.
 */
public class AttentionFragment extends Fragment {
    private ListView attention_lv;
    private MyDatabase db;
    private List<AttentionList> home_dtails=new ArrayList<>();
    private TextView attention_jia;
    private RelativeLayout moren_rl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.attentionfragment,null);
        attention_lv = (ListView) view.findViewById(R.id.attention_lv);
        attention_jia = (TextView) view.findViewById(R.id.attention_jia);
        moren_rl = (RelativeLayout) view.findViewById(R.id.moren_rl);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        db=new MyDatabase(getActivity());

        attention_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent=new Intent(getActivity(), AttentionDetailActivity.class);
                intent.putExtra("title",home_dtails.get(position).getTitle());
                intent.putExtra("url",home_dtails.get(position).getUrl());
                startActivity(intent);
            }
        });
        attention_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent=new Intent(getActivity(), AttentionSelectActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        home_dtails.clear();

        home_dtails = db.queryAttention();
        Log.i("ssss",home_dtails.size()+"");
        if (home_dtails.size()==0){
            moren_rl.setVisibility(View.VISIBLE);
            attention_lv.setVisibility(View.GONE);
        }else{
            moren_rl.setVisibility(View.GONE);
            attention_lv.setVisibility(View.VISIBLE);
            attention_lv.setAdapter(new AttentionAdapter(home_dtails,getActivity(),attention_lv,moren_rl));
        }

    }
}
