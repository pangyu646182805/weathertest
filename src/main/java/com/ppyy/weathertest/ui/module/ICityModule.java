package com.ppyy.weathertest.ui.module;

import com.ppyy.weathertest.ui.bean.HeFenCityBean;

import rx.Observable;

/**
 * Created by Administrator on 2016/8/30.
 */

public interface ICityModule {
    Observable<HeFenCityBean> getCitys(String search, String appKey);
}
