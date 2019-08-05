package com.example01.myapplicationtest01.api;

import com.example01.myapplicationtest01.domain.Image;
import com.example01.myapplicationtest01.domain.response.ListResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Service {
    @GET("v1/images")
    Observable<ListResponse<Image>> images();
}
