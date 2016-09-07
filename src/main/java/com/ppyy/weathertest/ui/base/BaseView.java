package com.ppyy.weathertest.ui.base;

/**
 * Created by Administrator on 2016/8/30.
 */

public interface BaseView<T> {
    void setPresenter(T presenter);
    void showLoading();
    void hideLoading();
    void showTip(String msg);
}
