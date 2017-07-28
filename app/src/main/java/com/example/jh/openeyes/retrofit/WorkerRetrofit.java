package com.example.jh.openeyes.retrofit;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：jinhui on 2017/7/27
 * 邮箱：1004260403@qq.com
 */

public class WorkerRetrofit {

    private static final String BASE_URL = "http://baobab.kaiyanapp.com/api/";

    private Map<Class, Object> apis = new HashMap<>();

    private Retrofit retrofit;

    public WorkerRetrofit() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public <T>T createApi(Class<T> service) {
        if (!apis.containsKey(service)) {
            T instance = retrofit.create(service);
            apis.put(service, instance);
        }

        return (T)apis.get(service);
    }
}

