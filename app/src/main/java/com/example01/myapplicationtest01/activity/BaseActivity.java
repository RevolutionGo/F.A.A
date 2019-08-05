package com.example01.myapplicationtest01.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example01.myapplicationtest01.util.SharedPreferencesUtil;

public class BaseActivity extends AppCompatActivity {
    protected SharedPreferencesUtil sp;

    //重写了setContentView方法，在子类调用了setContentView设置布局
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        //配置文件
        sp = SharedPreferencesUtil.getInstance(getApplicationContext());
    }
}
