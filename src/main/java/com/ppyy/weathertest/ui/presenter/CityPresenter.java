package com.ppyy.weathertest.ui.presenter;

import com.ppyy.weathertest.ui.bean.HeFenCityBean;
import com.ppyy.weathertest.ui.conf.Constants;
import com.ppyy.weathertest.ui.contract.CityContract;
import com.ppyy.weathertest.ui.module.ICityModule;
import com.ppyy.weathertest.ui.module.impl.CityModuleImpl;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/8/30.
 */

public class CityPresenter implements CityContract.Presenter {
    private CompositeSubscription mSubscriptions;
    private ICityModule mCityModule;
    private CityContract.View mCityView;

    public CityPresenter(CityContract.View view) {
        mCityView = view;
        mSubscriptions = new CompositeSubscription();
        mCityModule = new CityModuleImpl();
        mCityView.setPresenter(this);
    }

    @Override
    public void subscribeCitys(String search, String appKey) {
        Subscription subscription = mCityModule.getCitys(search, appKey)
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<HeFenCityBean, Observable<List<HeFenCityBean.CityInfoBean>>>() {
                    @Override
                    public Observable<List<HeFenCityBean.CityInfoBean>> call(final HeFenCityBean heFenCityBean) {
                        if (Constants.STATUS_OK.equals(heFenCityBean.getStatus())) {
                            return Observable.create(new Observable.OnSubscribe<List<HeFenCityBean.CityInfoBean>>() {
                                @Override
                                public void call(Subscriber<? super List<HeFenCityBean.CityInfoBean>> subscriber) {
                                    subscriber.onNext(heFenCityBean.getCity_info());
                                    subscriber.onCompleted();
                                }
                            });
                        }
                        return Observable.error(new Exception(heFenCityBean.getStatus()));
                    }
                })
                .flatMap(new Func1<List<HeFenCityBean.CityInfoBean>, Observable<HeFenCityBean.CityInfoBean>>() {
                    @Override
                    public Observable<HeFenCityBean.CityInfoBean> call(List<HeFenCityBean.CityInfoBean> cityInfoBeen) {
                        return Observable.from(cityInfoBeen);
                    }
                })
                .map(new Func1<HeFenCityBean.CityInfoBean, HeFenCityBean.CityInfoBean>() {
                    @Override
                    public HeFenCityBean.CityInfoBean call(HeFenCityBean.CityInfoBean cityInfoBean) {
                        return cityInfoBean;
                    }
                })
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<HeFenCityBean.CityInfoBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mCityView.showTip(e.getMessage());
                    }

                    @Override
                    public void onNext(List<HeFenCityBean.CityInfoBean> cityInfoBeen) {
                        mCityView.showCitys(cityInfoBeen);
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
    }
}
