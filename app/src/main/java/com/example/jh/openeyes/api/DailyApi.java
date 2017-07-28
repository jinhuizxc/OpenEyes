package com.example.jh.openeyes.api;

import com.example.jh.openeyes.model.Daily;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 作者：jinhui on 2017/7/27
 * 邮箱：1004260403@qq.com
 */

public interface DailyApi {

    @GET("v2/feed?num=2")
    Flowable<Daily> getDaily(@Query("date") long date);

    @GET("v2/feed?num=2")
    Flowable<Daily> getDaily();

}