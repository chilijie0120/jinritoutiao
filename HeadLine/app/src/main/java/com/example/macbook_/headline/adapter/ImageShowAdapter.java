package com.example.macbook_.headline.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.macbook_.headline.bean.Home_Dtail;

import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by MacBook- on 2017/3/24.
 */
public class ImageShowAdapter extends PagerAdapter {
    private List<Home_Dtail.Home_Image> images;
    private Activity context;

    public ImageShowAdapter(List<Home_Dtail.Home_Image> images, Activity context) {
        this.images = images;
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PhotoView photoView=new PhotoView(context);
        Glide.with(context).load(images.get(position).getUrl()).into(photoView);

        photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float v, float v1) {
               context.finish();
            }
        });
        container.addView(photoView);
        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
