package com.ppyy.weathertest.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ppyy.weathertest.R;
import com.ppyy.weathertest.ui.bean.HeFenWeatherBean;
import com.ppyy.weathertest.ui.conf.Constants;
import com.ppyy.weathertest.ui.utils.DensityUtils;
import com.ppyy.weathertest.ui.utils.TimeUtils;
import com.ppyy.weathertest.ui.utils.WeatherUtils;
import com.ppyy.weathertest.ui.view.ChartHorizontalScrollView;
import com.ppyy.weathertest.ui.view.ChartView;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/6.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.Holder> {
    private static final int ITEM_HEADER = 0;
    private static final int ITEM_LINE_CHART = 1;  // 折线图
    private static final int ITEM_AIR_QUALITY = 2;  // 空气质量
    private static final int ITEM_SUGGESTION = 3;  // 生活指数
    private static final int ITEM_SUN = 4;  // 日出日落
    private static final int ITEM_COUNT = 5;

    private Context mContext;
    private List<HeFenWeatherBean.HeWeatherDataService> mDataList;
    private List<HeFenWeatherBean.DailyForecastBean> mDailyForecast;

    public void setDataList(List<HeFenWeatherBean.HeWeatherDataService> dataList) {
        mDataList = dataList;
        mDailyForecast = mDataList.get(0).getDaily_forecast();
        notifyDataSetChanged();
    }

    public WeatherAdapter(Context context) {
        mContext = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        Holder holder = null;
        View view;
        switch (getItemViewType(viewType)) {
            case ITEM_HEADER:
                view = View.inflate(mContext, R.layout.item_weather_header, null);
                holder = new Holder(view, getItemViewType(viewType));
                break;
            case ITEM_LINE_CHART:
                view = View.inflate(mContext, R.layout.item_line_chart, null);
                holder = new Holder(view, getItemViewType(viewType));
                break;
            case ITEM_AIR_QUALITY:
                view = View.inflate(mContext, R.layout.item_air_quality, null);
                holder = new Holder(view, getItemViewType(viewType));
                break;
            case ITEM_SUGGESTION:
                view = View.inflate(mContext, R.layout.item_suggestion, null);
                holder = new Holder(view, getItemViewType(viewType));
                break;
            case ITEM_SUN:
                view = View.inflate(mContext, R.layout.item_sun, null);
                holder = new Holder(view, getItemViewType(viewType));
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        switch (getItemViewType(position)) {
            case ITEM_HEADER:
                holder.setHeaderData();
                break;
            case ITEM_LINE_CHART:
                holder.setLineChartData();
                break;
            case ITEM_AIR_QUALITY:
                holder.setAirQuality();
                break;
            case ITEM_SUGGESTION:
                break;
            case ITEM_SUN:
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (mDataList == null) {
            return 0;
        }
        return ITEM_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case ITEM_HEADER:
                return ITEM_HEADER;
            case ITEM_LINE_CHART:
                return ITEM_LINE_CHART;
            case ITEM_AIR_QUALITY:
                return ITEM_AIR_QUALITY;
            case ITEM_SUGGESTION:
                return ITEM_SUGGESTION;
            case ITEM_SUN:
                return ITEM_SUN;
        }
        return super.getItemViewType(position);
    }

    public class Holder extends RecyclerView.ViewHolder {
        private int mViewType;
        private HeFenWeatherBean.HeWeatherDataService mDataService;
        /**
         * header
         */
        TextView mTvUpdateTime;
        TextView mTvTmp;
        TextView mTvMax;
        TextView mTvMin;
        TextView mTvWeather;
        ImageView mIvWeather;
        TextView mTvWet;
        TextView mTvWindDesc;
        TextView mTvWind;
        TextView mTvTemp;

        /**
         * 折线图
         */
        LinearLayout mLlLineChart;
        LinearLayout mLlRoot;
        ChartHorizontalScrollView mScrollView;

        /**
         * 空气质量
         */
        TextView mTvPm25;
        TextView mTvPm10;
        TextView mTvSo2;
        TextView mTvCo;
        TextView mTvNo2;
        TextView mTvO3;

        public Holder(View itemView, int viewType) {
            super(itemView);
            if (mDataList != null) {
                mDataService = mDataList.get(0);
            }
            this.mViewType = viewType;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
            itemView.setLayoutParams(params);
            switch (mViewType) {
                case ITEM_HEADER:
                    mTvUpdateTime = ButterKnife.findById(itemView, R.id.tv_update_time);
                    mTvTmp = ButterKnife.findById(itemView, R.id.tv_tmp);
                    mTvMax = ButterKnife.findById(itemView, R.id.tv_tmp_max);
                    mTvMin = ButterKnife.findById(itemView, R.id.tv_tmp_min);
                    mTvWeather = ButterKnife.findById(itemView, R.id.tv_weather);
                    mIvWeather = ButterKnife.findById(itemView, R.id.iv_weather);
                    mTvWet = ButterKnife.findById(itemView, R.id.tv_wet);
                    mTvWindDesc = ButterKnife.findById(itemView, R.id.tv_wind_desc);
                    mTvWind = ButterKnife.findById(itemView, R.id.tv_wind);
                    mTvTemp = ButterKnife.findById(itemView, R.id.tv_temp);
                    break;
                case ITEM_LINE_CHART:
                    params = new LinearLayout.LayoutParams(-1, -1);
                    itemView.setLayoutParams(params);
                    mLlLineChart = ButterKnife.findById(itemView, R.id.ll_line_chart);
                    mLlRoot = ButterKnife.findById(itemView, R.id.ll_root);
                    mScrollView = ButterKnife.findById(itemView, R.id.chart_scroll_view);
                    mScrollView.setParentWhidth(((Activity) mContext).getWindowManager().getDefaultDisplay().getWidth());
                    break;
                case ITEM_AIR_QUALITY:
                    mTvPm25 = ButterKnife.findById(itemView, R.id.tv_pm25);
                    mTvPm10 = ButterKnife.findById(itemView, R.id.tv_pm10);
                    mTvSo2 = ButterKnife.findById(itemView, R.id.tv_so2);
                    mTvCo = ButterKnife.findById(itemView, R.id.tv_co);
                    mTvNo2 = ButterKnife.findById(itemView, R.id.tv_no2);
                    mTvO3 = ButterKnife.findById(itemView, R.id.tv_o3);
                    break;
                case ITEM_SUGGESTION:

                    break;
                case ITEM_SUN:

                    break;
            }
        }

        private void setHeaderData() {
            HeFenWeatherBean.NowBean now = mDataService.getNow();
            HeFenWeatherBean.BasicBean basic = mDataService.getBasic();
            List<HeFenWeatherBean.DailyForecastBean> dailyForecast = mDataService.getDaily_forecast();
            if (now != null) {
                mTvTmp.setText(now.getTmp() + Constants.TEMP);
                mTvWeather.setText(now.getCond().getTxt());
                mTvWet.setText(now.getHum() + "%");
                mTvWindDesc.setText(now.getWind().getDir());
                mTvWind.setText(now.getWind().getSc() + "级");
                mTvTemp.setText(now.getFl() + Constants.TEMP);
                WeatherUtils.setWeatherIconById(mContext, Integer.parseInt(now.getCond().getCode()), mIvWeather);
            }
            if (now != null) {
                mTvUpdateTime.setText(basic.getUpdate().getLoc() + "更新");
            }
            if (dailyForecast != null) {
                HeFenWeatherBean.DailyForecastBean bean = dailyForecast.get(0);
                mTvMax.setText(bean.getTmp().getMax() + Constants.MAX);
                mTvMin.setText(bean.getTmp().getMin() + Constants.MIN);
                if (now != null) {
                    if (Integer.parseInt(now.getTmp()) < Integer.parseInt(bean.getTmp().getMin())) {
                        mTvTmp.setText(bean.getTmp().getMin() + Constants.TEMP);
                    }
                    if (Integer.parseInt(now.getTmp()) > Integer.parseInt(bean.getTmp().getMax())) {
                        mTvTmp.setText(bean.getTmp().getMax() + Constants.TEMP);
                    }
                }
            }
        }

        private void setLineChartData() {
            ChartView chartView = null;
            ChartView preChartView = null;
            int center = 20;
            if (mDailyForecast != null) {
                mLlLineChart.removeAllViews();
                for (int i = 0; i < mDailyForecast.size(); i++) {
                    chartView = new ChartView(mContext);
                    chartView.setId(i);
                    mLlLineChart.addView(chartView);
                }
                for (int i = 0; i < mLlLineChart.getChildCount(); i++) {
                    HeFenWeatherBean.DailyForecastBean bean = mDailyForecast.get(i);
                    chartView = (ChartView) mLlLineChart.findViewById(i);
                    int max = Integer.parseInt(bean.getTmp().getMax());
                    int min = Integer.parseInt(bean.getTmp().getMin());
                    try {
                        chartView.setWeekText(TimeUtils.getWeekText(bean.getDate()));
                        chartView.setDateText(TimeUtils.getDateText(bean.getDate()));
                        chartView.setDayWeatherIcon(Integer.parseInt(bean.getCond().getCode_d()));
                        chartView.setDayWeatherText(bean.getCond().getTxt_d());
                        chartView.setNightWeatherIcon(Integer.parseInt(bean.getCond().getCode_n()));
                        chartView.setNightWeatherText(bean.getCond().getTxt_n());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (i == 0) {
                        chartView.setDayTemp(max);
                        chartView.setNightTemp(min);
                        chartView.setCenterTemp(min);
                        center = min;
                    } else {
                        chartView.setDayTemp(max);
                        chartView.setNightTemp(min);
                        chartView.setCenterTemp(center);
                        preChartView = (ChartView) mLlLineChart.findViewById(i - 1);
                        preChartView.setNextDayTemp(max);
                        preChartView.setNextNightTemp(min);
                        final ChartView tempChartView = chartView;
                        preChartView.setOnNextTempFinishedListener(new ChartView.OnNextTempFinishedListener() {
                            @Override
                            public void onNextTempFinished(float nextDayTempY, float nextNightTempY) {
                                tempChartView.mPreDayTempY = nextDayTempY;
                                tempChartView.mPreNightTempY = nextNightTempY;
                            }
                        });
                    }
                }
                mLlLineChart.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        mLlLineChart.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        mLlRoot.getLayoutParams().height = mLlLineChart.getHeight() + DensityUtils.dp2px(mContext, 20.0f);
                        mLlRoot.setPadding(0, DensityUtils.dp2px(mContext, 20.0f), 0, 0);
                        mLlRoot.requestLayout();
                    }
                });
            }
        }

        private void setAirQuality() {
            HeFenWeatherBean.AqiBean aqi = mDataService.getAqi();
            if (aqi != null) {
                mTvPm25.setText(aqi.getCity().getPm25());
                mTvPm10.setText(aqi.getCity().getPm10());
                mTvSo2.setText(aqi.getCity().getSo2());
                mTvCo.setText(aqi.getCity().getCo());
                mTvNo2.setText(aqi.getCity().getNo2());
                mTvO3.setText(aqi.getCity().getO3());
            }
        }
    }
}
