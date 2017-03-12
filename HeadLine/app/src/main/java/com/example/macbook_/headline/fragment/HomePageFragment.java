package com.example.macbook_.headline.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.macbook_.headline.R;
import com.example.macbook_.headline.activity.ZhuActivity;

/**
 * Created by MacBook- on 2017/3/11.
 */
public class HomePageFragment extends Fragment {

    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.homepagefragment,null);
        imageView = (ImageView) view.findViewById(R.id.zhu_head_iv);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZhuActivity.slidingMenu.toggle();
            }
        });
    }
}
