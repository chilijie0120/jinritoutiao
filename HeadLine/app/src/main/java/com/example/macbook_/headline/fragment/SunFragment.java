package com.example.macbook_.headline.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.macbook_.headline.R;
import com.example.macbook_.headline.adapter.SunFragmentPagrAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MacBook- on 2017/3/11.
 */
public class SunFragment extends Fragment {
    private TabLayout tab_sun;
    private ViewPager vp_sun;
    private List<String> list=new ArrayList<>();
    String [] name=new String[]{"热点","娱乐","搞笑","精品"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.sunfragment,null);
        initView(view);

        return view;
    }
    private void initView(View view) {
        tab_sun = (TabLayout) view.findViewById(R.id.tab_sun);
        vp_sun = (ViewPager) view.findViewById(R.id.sun_vp);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tab_sun.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab_sun.setupWithViewPager(vp_sun);
        addData();
        SunFragmentPagrAdapter adapter=new SunFragmentPagrAdapter(getChildFragmentManager(),list);
        vp_sun.setAdapter(adapter);
    }

    private void addData() {
        for (int i = 0; i <name.length ; i++) {
            list.add(name[i]);
        }
    }
}
