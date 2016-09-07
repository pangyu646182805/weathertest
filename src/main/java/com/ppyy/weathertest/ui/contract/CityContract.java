package com.ppyy.weathertest.ui.contract;

import com.ppyy.weathertest.ui.base.BasePresenter;
import com.ppyy.weathertest.ui.base.BaseView;
import com.ppyy.weathertest.ui.bean.HeFenCityBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/30.
 */

public class CityContract {
    public interface Presenter extends BasePresenter {
        void subscribeCitys(String search, String appKey);
    }

    public interface View extends BaseView<Presenter> {
        void showCitys(List<HeFenCityBean.CityInfoBean> resultBean);
    }
}
