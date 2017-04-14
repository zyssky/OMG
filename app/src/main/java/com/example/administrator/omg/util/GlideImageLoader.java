package com.example.administrator.omg.util;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Administrator on 2017/4/9.
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Integer integer = (Integer) path;
        Glide.with(context).load(integer).thumbnail( 0.1f ).into(imageView);
    }
}
