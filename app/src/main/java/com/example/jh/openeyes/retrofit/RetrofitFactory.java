package com.example.jh.openeyes.retrofit;

/**
 * 作者：jinhui on 2017/7/27
 * 邮箱：1004260403@qq.com
 */

public class RetrofitFactory {

    private static final Object object = new Object();
    private volatile static WorkerRetrofit retrofit;

    public static WorkerRetrofit getRetrofit() {
        synchronized (object) {
            if (retrofit == null) {
                retrofit = new WorkerRetrofit();
            }
            return retrofit;
        }
    }
}

