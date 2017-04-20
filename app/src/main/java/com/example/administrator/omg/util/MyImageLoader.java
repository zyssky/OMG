package com.example.administrator.omg.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2017/4/20.
 */

public class MyImageLoader {
    private Context context;
    private static MyImageLoader mInstance;
    protected MyImageLoader(Context context){
        this.context = context;
    }

    public static MyImageLoader getInstance(){
        return mInstance;
    }

    public static void init(Context context){
        mInstance = new MyImageLoader(context);
    }

    public void loadImage(ImageView imageView,Uri uri){
        Glide.with(context).load(uri).into(imageView);
    }

    public void loadImage(ImageView imageView,Integer resId){
        Glide.with(context).load(resId).into(imageView);
    }

    public void loadImage(ImageView imageView,Bitmap bitmap){
        Glide.with(context).load(bitmap).into(imageView);
    }
}
