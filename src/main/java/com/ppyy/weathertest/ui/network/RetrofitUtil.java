package com.ppyy.weathertest.ui.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.ppyy.weathertest.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/8/30.
 */
public class RetrofitUtil {
    public static volatile Retrofit retrofit;

    private RetrofitUtil() {
    }

    /**
     * 确保该方法在Application类中调用一次
     *
     * @param
     * @return
     */
    public static Retrofit getInstance(String url) {
        retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getClient())
                .build();
        return retrofit;
    }

    private static OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder.networkInterceptors().add(new StethoInterceptor());
        }
        return builder.build();
    }
}
