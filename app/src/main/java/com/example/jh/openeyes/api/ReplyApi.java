package com.example.jh.openeyes.api;

import com.example.jh.openeyes.model.Replies;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 作者：jinhui on 2017/7/27
 * 邮箱：1004260403@qq.com
 */

public interface ReplyApi {

    @GET("v1/replies/video")
    Flowable<Replies> fetchReplies(@Query("id") int id);

    @GET("v1/replies/video?num=10")
    Flowable<Replies> fetchReplies(@Query("id") int id, @Query("lastId") int lastId);

}

