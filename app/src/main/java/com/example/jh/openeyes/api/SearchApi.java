package com.example.jh.openeyes.api;

import com.example.jh.openeyes.model.SearchResult;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 作者：jinhui on 2017/7/28
 * 邮箱：1004260403@qq.com
 */

public interface SearchApi {

    @GET("v3/queries/hot")
    Flowable<List<String>> getTrendingTag();

    @GET("v1/search?num=10")
    Flowable<SearchResult> query(@Query("query") String key, @Query("start") int start);

}
