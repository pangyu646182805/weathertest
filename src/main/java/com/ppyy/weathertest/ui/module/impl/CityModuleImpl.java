package com.ppyy.weathertest.ui.module.impl;

import com.ppyy.weathertest.ui.bean.HeFenCityBean;
import com.ppyy.weathertest.ui.conf.Constants;
import com.ppyy.weathertest.ui.module.ICityModule;
import com.ppyy.weathertest.ui.network.RetrofitUtil;
import com.ppyy.weathertest.ui.network.WeatherService;

import rx.Observable;

/**
 * Created by Administrator on 2016/8/30.
 */

public class CityModuleImpl implements ICityModule {
    private WeatherService mWeatherService;

    public CityModuleImpl() {
        mWeatherService = RetrofitUtil.getInstance(Constants.BASE_HEFEN_URL).create(WeatherService.class);
    }

    @Override
    public Observable<HeFenCityBean> getCitys(String search, String appKey) {
        return mWeatherService.getCitys(search, appKey);
    }
}
