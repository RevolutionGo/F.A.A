package com.example01.myapplicationtest01;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example01.myapplicationtest01.R;
import com.example01.myapplicationtest01.activity.ImageDetailActivity;
import com.example01.myapplicationtest01.api.Api;
import com.example01.myapplicationtest01.domain.Image;
import com.example01.myapplicationtest01.activity.BaseActivity;
import com.example01.myapplicationtest01.activity.LoginActivity;
import com.example01.myapplicationtest01.adapter.ImageAdapter;
import com.example01.myapplicationtest01.domain.response.ListResponse;
import com.example01.myapplicationtest01.util.Constants;
import com.example01.myapplicationtest01.util.SharedPreferencesUtil;
import com.example01.myapplicationtest01.util.ToastUtil;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    private ImageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        /**
         * 显示两列宽高相同根据图片实际宽高比来缩放的图片
         * 使用GridLayoutManager来实现类似的格子布局
         */
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        rv.setLayoutManager(layoutManager);

        //设置测试数据
        //ArrayList<Image> datas = new Arraylist<>();
        //for (int i = 1; i < 10; i++) {
         //   datas.add(new Image(String.format("http://dev-courses-quick.oss-cn-being.aliyuncs.com/%d.jpg",i)));
        //}

        adapter = new ImageAdapter(this);
        adapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //ToastUtil.shortToast(MainActivity.this,"click:"+position);
                //点击一个图片后调用此处
                Image data = adapter.getData(position);

                //跳转到图片详情页面
                Intent intent = new Intent(MainActivity.this, ImageDetailActivity.class);

                //通过intent将图片地址传递到详情页面
                intent.putExtra(Constants.ID,data.getUri());
                startActivity(intent);
            }
        });
        rv.setAdapter(adapter);

        //adapter.setData(datas);

        fetchData();
    }

    private void fetchData() {
        Api
                .getInstance()
                .images()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListResponse<Image>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListResponse<Image> imageListResponse) {
                        //当数据请求完毕后，会解析成对象，并返回
                        adapter.setData(imageListResponse.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        //TODO将错误提示给用户，同时写到日志中
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void onLogoutClick(View view) {
        sp.setLogin(false);
        //退出后跳转到登录界面
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        finish();
    }
}
