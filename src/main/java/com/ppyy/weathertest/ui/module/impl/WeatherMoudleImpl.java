package com.ppyy.weathertest.ui.module.impl;

import com.ppyy.weathertest.ui.bean.HeFenWeatherBean;
import com.ppyy.weathertest.ui.conf.Constants;
import com.ppyy.weathertest.ui.module.IWeatherMoudle;
import com.ppyy.weathertest.ui.network.RetrofitUtil;
import com.ppyy.weathertest.ui.network.WeatherService;

import rx.Observable;

/**
 * Created by Administrator on 2016/9/5.
 */

public class WeatherMoudleImpl implements IWeatherMoudle {
    private WeatherService mWeatherService;

    public WeatherMoudleImpl() {
        mWeatherService = RetrofitUtil.getInstance(Constants.BASE_HEFEN_URL).create(WeatherService.class);
    }

    @Override
    public Observable<HeFenWeatherBean> getWeather(String cityId, String appKey) {
        return mWeatherService.getWeather(cityId, appKey);
    }
}
