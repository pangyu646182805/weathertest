package com.ppyy.weathertest.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ppyy.weathertest.R;
import com.ppyy.weathertest.ui.base.BaseActivity;
import com.ppyy.weathertest.ui.base.BaseFragment;
import com.ppyy.weathertest.ui.conf.Constants;
import com.ppyy.weathertest.ui.fragment.WeatherFragment;
import com.ppyy.weathertest.ui.utils.DensityUtils;
import com.ppyy.weathertest.ui.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/5.
 */

public class WeatherActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private String mCityName;
    private String mCityId;
    private List<String> mCityIdList;
    private List<String> mCityNameList;
    private int mPointWidth;
    private List<BaseFragment> mFragments;

    @BindView(R.id.ll_root)
    LinearLayout mLlRoot;
    @BindView(R.id.tv_city)
    TextView mTvCity;
    @BindView(R.id.vp_weather)
    ViewPager mVpWeather;
    @BindView(R.id.ll_point_group)
    LinearLayout mLlPointGroup;
    @BindView(R.id.view_white_point)
    View mWhitePoint;
    @BindView(R.id.rl_point_group)
    RelativeLayout mRlPointGroup;
    private WeatherListAdapter mAdapter;
    private int mPosition;

    @Override
    protected void init() {
        Intent intent = getIntent();
        mCityName = intent.getStringExtra(Constants.EXTRA_CITY_NAME);
        mCityId = intent.getStringExtra(Constants.EXTRA_CITY_ID);
        mCityIdList = (List<String>) intent.getSerializableExtra(Constants.EXTRA_CITY_ID_List);
        mCityNameList = (List<String>) intent.getSerializableExtra(Constants.EXTRA_CITY_NAME_List);
        mPosition = intent.getIntExtra("position", 0);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        if (mCityIdList != null) {
            mFragments = new ArrayList<>();
            WeatherFragment weatherFragment;
            if (mCityIdList.size() > 1) {
                for (int i = 0; i < mCityIdList.size(); i++) {
                    View point = new View(this);
                    point.setBackgroundResource(R.drawable.shape_point_gray);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtils.dp2px(this, 5),
                            DensityUtils.dp2px(this, 5));
                    point.setLayoutParams(params);
                    if (i > 0) {
                        params.leftMargin = DensityUtils.dp2px(this, 5);
                    }
                    mLlPointGroup.addView(point);
                    weatherFragment = new WeatherFragment(this, mCityIdList.get(i));
                    mFragments.add(weatherFragment);
                }
                mLlPointGroup.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override  // 当layout结束后回调此方法
                    public void onGlobalLayout() {
                        mLlPointGroup.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        int left1 = mLlPointGroup.getChildAt(1).getLeft();
                        int left0 = mLlPointGroup.getChildAt(0).getLeft();
                        mPointWidth = left1 - left0;
                        mVpWeather.setCurrentItem(mPosition);
                    }
                });
            } else {
                mFragments.add(new WeatherFragment(this, mCityId));
                mRlPointGroup.setVisibility(View.GONE);
            }
        }
        if (TimeUtils.judgeDayOrNight()) {
            mLlRoot.setBackgroundResource(R.mipmap.img_weather);
        } else {
            mLlRoot.setBackgroundResource(R.mipmap.img_night);
        }
    }

    @Override
    protected void initData() {
        mTvCity.setText(mCityNameList.get(mPosition));
        mAdapter = new WeatherListAdapter(getSupportFragmentManager(), mFragments);
        mVpWeather.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        mVpWeather.addOnPageChangeListener(this);
        mTvCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WeatherActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mPointWidth != 0) {
            int len = (int) (mPointWidth * positionOffset) + position * mPointWidth;
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mWhitePoint.getLayoutParams();
            params.leftMargin = len;
            mWhitePoint.setLayoutParams(params);
        }
    }

    @Override
    public void onPageSelected(int position) {
        mTvCity.setText(mCityNameList.get(position));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class WeatherListAdapter extends FragmentPagerAdapter {
        private List<BaseFragment> fragments;

        public WeatherListAdapter(FragmentManager fm, List<BaseFragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
