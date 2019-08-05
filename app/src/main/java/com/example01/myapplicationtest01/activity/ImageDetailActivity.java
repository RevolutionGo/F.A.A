package com.example01.myapplicationtest01.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example01.myapplicationtest01.R;
import com.example01.myapplicationtest01.util.Constants;
import com.example01.myapplicationtest01.util.ImageUtil;
import com.github.chrisbanes.photoview.PhotoView;

public class ImageDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        PhotoView pv = findViewById(R.id.pv);

        //获取传递过来的参数
        String url = getIntent().getStringExtra(Constants.ID);

        //显示图片
        ImageUtil.show(this,pv,url);
    }
}
