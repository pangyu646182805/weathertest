package com.ppyy.weathertest.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ppyy.weathertest.R;
import com.ppyy.weathertest.ui.adapter.WeatherAdapter;
import com.ppyy.weathertest.ui.base.BaseFragment;
import com.ppyy.weathertest.ui.bean.HeFenWeatherBean;
import com.ppyy.weathertest.ui.conf.Constants;
import com.ppyy.weathertest.ui.contract.WeatherContract;
import com.ppyy.weathertest.ui.presenter.WeatherPresenter;
import com.ppyy.weathertest.ui.utils.CommonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/5.
 */

public class WeatherFragment extends BaseFragment implements WeatherContract.View, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.swipe_layout_weather)
    SwipeRefreshLayout mSwipeLayoutWeather;
    @BindView(R.id.recycler_view_weather)
    RecyclerView mRecyclerViewWeather;

    private Context mContext;
    private WeatherContract.Presenter mWeatherPresenter;
    private WeatherAdapter mWeatherAdapter;
    private LinearLayoutManager mLayoutManager;
    private String mCityId;

    public WeatherFragment(Context context, String cityId) {
        mContext = context;
        this.mCityId = cityId;
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.fragment_weather, null);
        new WeatherPresenter(this);
        ButterKnife.bind(this, view);
        CommonUtils.lazyLoad(getActivity(), new Handler(), new Runnable() {
            @Override
            public void run() {
                showLoading();
                onRefresh();
            }
        });
        return view;
    }

    @Override
    protected void initData() {
        mLayoutManager = new LinearLayoutManager(mContext);
        mWeatherAdapter = new WeatherAdapter(mContext);
        mRecyclerViewWeather.setLayoutManager(mLayoutManager);
        mRecyclerViewWeather.setAdapter(mWeatherAdapter);
    }

    @Override
    protected void initListener() {
        mSwipeLayoutWeather.setOnRefreshListener(this);
    }

    @Override
    protected void processClick(View v) {

    }

    @Override
    public void setPresenter(WeatherContract.Presenter presenter) {
        this.mWeatherPresenter = presenter;
    }

    @Override
    public void showLoading() {
        mSwipeLayoutWeather.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mSwipeLayoutWeather.setRefreshing(false);
    }

    @Override
    public void showTip(String msg) {

    }

    @Override
    public void showWeather(List<HeFenWeatherBean.HeWeatherDataService> dataServiceList) {
        mWeatherAdapter.setDataList(dataServiceList);
        hideLoading();
    }

    @Override
    public void onRefresh() {
        mWeatherPresenter.subscribeWeather(mCityId, Constants.BASE_HEFEN_APP_KEY);
    }
}
