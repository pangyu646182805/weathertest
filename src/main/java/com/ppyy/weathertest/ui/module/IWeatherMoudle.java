package com.ppyy.weathertest.ui.module;

import com.ppyy.weathertest.ui.bean.HeFenWeatherBean;

import rx.Observable;

/**
 * Created by Administrator on 2016/9/5.
 */

public interface IWeatherMoudle {
    Observable<HeFenWeatherBean> getWeather(String cityId, String appKey);
}
