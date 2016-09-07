package com.ppyy.weathertest.ui.presenter;

import com.ppyy.weathertest.ui.bean.HeFenWeatherBean;
import com.ppyy.weathertest.ui.conf.Constants;
import com.ppyy.weathertest.ui.contract.WeatherContract;
import com.ppyy.weathertest.ui.module.IWeatherMoudle;
import com.ppyy.weathertest.ui.module.impl.WeatherMoudleImpl;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/9/5.
 */

public class WeatherPresenter implements WeatherContract.Presenter {
    private CompositeSubscription mSubscriptions;
    private IWeatherMoudle mWeatherModule;
    private WeatherContract.View mWeatherView;

    public WeatherPresenter(WeatherContract.View view) {
        mWeatherView = view;
        mSubscriptions = new CompositeSubscription();
        mWeatherModule = new WeatherMoudleImpl();
        mWeatherView.setPresenter(this);
    }

    @Override
    public void subscribeWeather(String cityId, String appKey) {
        Subscription subscription = mWeatherModule.getWeather(cityId, appKey)
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<HeFenWeatherBean, Observable<List<HeFenWeatherBean.HeWeatherDataService>>>() {
                    @Override
                    public Observable<List<HeFenWeatherBean.HeWeatherDataService>> call(final HeFenWeatherBean heFenWeatherBean) {
                        if (heFenWeatherBean.getDataService() != null) {
                            if (Constants.STATUS_OK.equals(heFenWeatherBean.getDataService().get(0).getStatus())) {
                                return Observable.create(new Observable.OnSubscribe<List<HeFenWeatherBean.HeWeatherDataService>>() {
                                    @Override
                                    public void call(Subscriber<? super List<HeFenWeatherBean.HeWeatherDataService>> subscriber) {
                                        subscriber.onNext(heFenWeatherBean.getDataService());
                                        subscriber.onCompleted();
                                    }
                                });
                            } else {
                                return Observable.error(new Exception(heFenWeatherBean.getDataService().get(0).getStatus()));
                            }
                        }
                        return Observable.error(new NullPointerException());
                    }
                })
                .flatMap(new Func1<List<HeFenWeatherBean.HeWeatherDataService>, Observable<HeFenWeatherBean.HeWeatherDataService>>() {
                    @Override
                    public Observable<HeFenWeatherBean.HeWeatherDataService> call(List<HeFenWeatherBean.HeWeatherDataService> heWeatherDataServices) {
                        return Observable.from(heWeatherDataServices);
                    }
                })
                .map(new Func1<HeFenWeatherBean.HeWeatherDataService, HeFenWeatherBean.HeWeatherDataService>() {
                    @Override
                    public HeFenWeatherBean.HeWeatherDataService call(HeFenWeatherBean.HeWeatherDataService heWeatherDataService) {
                        return heWeatherDataService;
                    }
                })
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<HeFenWeatherBean.HeWeatherDataService>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mWeatherView.showTip(e.getMessage());
                    }

                    @Override
                    public void onNext(List<HeFenWeatherBean.HeWeatherDataService> heWeatherDataServices) {
                        mWeatherView.showWeather(heWeatherDataServices);
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
    }
}
