package com.example01.myapplicationtest01.util;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example01.myapplicationtest01.R;

public class ImageUtil {
    public static void show(Activity activity, ImageView view,String uri){
        RequestOptions options = getCommonRequestOptions();
        Glide.with(activity).load(uri).apply(options).into(view);
    }

    public static RequestOptions getCommonRequestOptions(){
        RequestOptions options = new RequestOptions();

        //加载前占位图
        options.placeholder(R.mipmap.image);

        //加载错误图
        options.error(R.mipmap.image);
        options.centerCrop();

        //测试，禁用所用缓存
        //options.diskCacheStrategy(DiskCacheStrategy.NONE);
        return options;
    }
}
