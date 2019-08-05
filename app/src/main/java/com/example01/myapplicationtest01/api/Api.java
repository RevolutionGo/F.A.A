package com.example01.myapplicationtest01.api;

import com.example01.myapplicationtest01.domain.Image;
import com.example01.myapplicationtest01.domain.response.ListResponse;
import com.example01.myapplicationtest01.util.Constants;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static final String SONG_ID = "song_id";
    private static Api instance;
    private static Service service;

    Api() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(Constants.ENDPOINT)  //配置API地址
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(Service.class);
    }

    public static Api getInstance() {
        if (instance == null) {
            instance = new Api();
        }
        return instance;
    }

    public Observable<ListResponse<Image>> images() {
        //调用接口service里的方法
        //调用接口之所以还能返回数据，是retrofit框架内容代理的该方法的调用
        return service.images();
    }
}
