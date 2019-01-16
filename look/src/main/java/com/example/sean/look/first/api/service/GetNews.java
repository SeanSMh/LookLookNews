package com.example.sean.look.first.api.service;

import com.example.sean.look.first.mvp.model.entity.NewsInfo;
import com.example.sean.look.first.mvp.model.entity.NewsInfos;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Sean
 * @data 2019/1/16
 */
public interface GetNews {

    @GET("http://v.juhe.cn/toutiao/index")
    Observable<NewsInfos> getNews(@Query("type") String top, @Query("key") String key);
}
