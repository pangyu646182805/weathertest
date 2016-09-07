package com.ppyy.weathertest.ui.contract;

import com.ppyy.weathertest.ui.base.BasePresenter;
import com.ppyy.weathertest.ui.base.BaseView;
import com.ppyy.weathertest.ui.bean.HeFenWeatherBean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/5.
 */

public class WeatherContract {
    public interface Presenter extends BasePresenter {
        void subscribeWeather(String cityId, String appKey);
    }

    public interface View extends BaseView<WeatherContract.Presenter> {
        void showWeather(List<HeFenWeatherBean.HeWeatherDataService> dataServiceList);
    }
}
