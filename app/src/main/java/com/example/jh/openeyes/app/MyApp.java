package com.example.jh.openeyes.app;

import android.app.Application;
import android.content.Context;

import com.danikula.videocache.HttpProxyCacheServer;

/**
 * 作者：jinhui on 2017/7/27
 * 邮箱：1004260403@qq.com
 */

public class MyApp extends Application {

    private HttpProxyCacheServer cacheServer;

    public static HttpProxyCacheServer cacheServer(Context context){
        MyApp myApp = (MyApp) context.getApplicationContext();
        return myApp.cacheServer == null?myApp.cacheServer =
                myApp.newCacheServer():myApp.cacheServer;
    }

    private HttpProxyCacheServer newCacheServer() {
        return new HttpProxyCacheServer.Builder(this)
                .maxCacheSize(1024*1024*1024)    // 1 Gb for cache
                .build();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
