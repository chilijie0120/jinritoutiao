package com.example.macbook_.headline.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.macbook_.headline.fragment.SunDetailFragment;

import java.util.List;

/**
 * Created by MacBook- on 2017/3/13.
 */
public class SunFragmentPagrAdapter extends FragmentPagerAdapter {
    private FragmentManager fm;
    private List<String> list;

    public SunFragmentPagrAdapter(FragmentManager fm, List<String> list) {
        super(fm);
        fm = fm;
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:
                fragment=new SunDetailFragment().getFragment(position,"V9LG4B3A0");
                break;
            case 1:
                fragment=new SunDetailFragment().getFragment(position,"V9LG4CHOR");
                break;
            case 2:
                fragment=new SunDetailFragment().getFragment(position,"V9LG4E6VR");
                break;
            case 3:
                fragment=new SunDetailFragment().getFragment(position,"00850FRB");
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}
