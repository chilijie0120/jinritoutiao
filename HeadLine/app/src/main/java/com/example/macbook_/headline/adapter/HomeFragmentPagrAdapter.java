package com.example.macbook_.headline.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.macbook_.headline.fragment.DetailFragment;

import java.util.List;

/**
 * Created by MacBook- on 2017/3/13.
 */
public class HomeFragmentPagrAdapter extends FragmentPagerAdapter {
    private FragmentManager fm;
    private List<String> list;

    public HomeFragmentPagrAdapter(FragmentManager fm, List<String> list) {
        super(fm);
        fm = fm;
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        fragment=new DetailFragment().getFragment(position);
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
