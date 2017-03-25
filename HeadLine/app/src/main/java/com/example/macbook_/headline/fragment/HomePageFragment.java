package com.example.macbook_.headline.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macbook_.headline.R;
import com.example.macbook_.headline.activity.ZhuActivity;
import com.example.macbook_.headline.adapter.HomeFragmentPagrAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MacBook- on 2017/3/11.
 */
public class HomePageFragment extends Fragment {

    public static ImageView imageView;
    private TextView tv_home;
    private TabLayout tab_home;
    private ViewPager vp_home;
    private List<String> list=new ArrayList<>();
    String [] name=new String[]{"推荐","本地","社会","娱乐","科技","汽车","体育","财经","军事","国际","健康"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.homepagefragment,null);
        initView(view);

        return view;
    }
    private void initView(View view) {
        imageView = (ImageView) view.findViewById(R.id.zhu_head_iv);
        tv_home = (TextView) view.findViewById(R.id.home_but_jia);
        tab_home = (TabLayout) view.findViewById(R.id.tab_home);
        vp_home = (ViewPager) view.findViewById(R.id.home_vp);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tab_home.setTabMode(TabLayout.MODE_SCROLLABLE);

        tab_home.setupWithViewPager(vp_home);

        addData();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZhuActivity.slidingMenu.toggle();
            }
        });
        HomeFragmentPagrAdapter adapter=new HomeFragmentPagrAdapter(getChildFragmentManager(),list);
        vp_home.setAdapter(adapter);
    }

    private void addData() {
        for (int i = 0; i <name.length ; i++) {
          list.add(name[i]);
        }
    }
}
