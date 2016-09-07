package com.ppyy.weathertest.ui.network;

import com.ppyy.weathertest.ui.bean.HeFenCityBean;
import com.ppyy.weathertest.ui.bean.HeFenWeatherBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/8/30.
 */

public interface WeatherService {
    @GET("/x3/citylist")
    Observable<HeFenCityBean> getCitys(@Query("search") String search, @Query("key") String appKey);

    @GET("/x3/weather")
    Observable<HeFenWeatherBean> getWeather(@Query("cityid") String cityId, @Query("key") String appKey);
}
