package com.ppyy.weathertest.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ppyy.weathertest.R;
import com.ppyy.weathertest.ui.base.BaseActivity;
import com.ppyy.weathertest.ui.bean.HeFenCityBean;
import com.ppyy.weathertest.ui.bean.HeFenWeatherBean;
import com.ppyy.weathertest.ui.conf.Constants;
import com.ppyy.weathertest.ui.contract.WeatherContract;
import com.ppyy.weathertest.ui.db.dao.CityListDao;
import com.ppyy.weathertest.ui.presenter.WeatherPresenter;
import com.ppyy.weathertest.ui.utils.PrefUtils;
import com.ppyy.weathertest.ui.utils.ShowUtils;
import com.ppyy.weathertest.ui.utils.TimeUtils;
import com.ppyy.weathertest.ui.utils.WeatherUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements WeatherContract.View {
    private static final int PERSION_WRITE_EXTERNAL_STORAGE = 1;
    @BindView(R.id.main_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.gv_city)
    GridView mGvCity;
    @BindView(R.id.ll_root)
    LinearLayout mLlRoot;

    private List<HeFenCityBean.CityInfoBean> mCityList = new ArrayList<>();
    private List<String> mCityIdList = new ArrayList<>();
    private List<String> mCityNameList = new ArrayList<>();
    private CityAdapter mCityAdapter;
    private boolean isEditState;

    private WeatherContract.Presenter mWeatherPresenter;
    private CityListDao mDao;

    @Override
    protected void init() {
        new WeatherPresenter(this);
        mDao = new CityListDao(this);
        boolean grant = checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (grant) {
            readData();
            if (mCityList.size() == 0) {
                startActivityForResult(new Intent(this, AddCityActivity.class), 0);
            }
        } else {
            requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, PERSION_WRITE_EXTERNAL_STORAGE);
        }
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mToolbar.setTitle(R.string.city_manage);
        setSupportActionBar(mToolbar);
        if (TimeUtils.judgeDayOrNight()) {
            mLlRoot.setBackgroundResource(R.mipmap.img_weather);
        } else {
            mLlRoot.setBackgroundResource(R.mipmap.img_night);
        }
    }

    @Override
    protected void initData() {
        mCityAdapter = new CityAdapter();
        mGvCity.setAdapter(mCityAdapter);
    }

    @Override
    protected void initListener() {
        mGvCity.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (!isEditState && position != mCityAdapter.getCount() - 1) {
                    isEditState = true;
                    mCityAdapter.notifyDataSetChanged();
                    invalidateOptionsMenu();
                }
                return false;
            }
        });
        mGvCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position != mCityAdapter.getCount() - 1 && !isEditState) {
                    Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                    intent.putExtra(Constants.EXTRA_CITY_ID, mCityList.get(position).getId());
                    intent.putExtra(Constants.EXTRA_CITY_NAME, mCityList.get(position).getCity());
                    intent.putExtra("position", position);
                    for (HeFenCityBean.CityInfoBean bean : mCityList) {
                        mCityIdList.add(bean.getId());
                        mCityNameList.add(bean.getCity());
                    }
                    intent.putExtra(Constants.EXTRA_CITY_ID_List, (Serializable) mCityIdList);
                    intent.putExtra(Constants.EXTRA_CITY_NAME_List, (Serializable) mCityNameList);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERSION_WRITE_EXTERNAL_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readData();
                if (mCityList.size() == 0) {
                    startActivityForResult(new Intent(this, AddCityActivity.class), 0);
                }
            } else {
                ShowUtils.showToast(this, "没有权限");
            }
        }
    }

    @Override
    public void setPresenter(WeatherContract.Presenter presenter) {
        this.mWeatherPresenter = presenter;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showTip(String msg) {
        ShowUtils.showToast(this, msg);
    }

    @Override
    public void showWeather(List<HeFenWeatherBean.HeWeatherDataService> dataServiceList) {
        saveData(dataServiceList.get(0));
    }

    private void saveData(HeFenWeatherBean.HeWeatherDataService heWeatherDataService) {
        HeFenWeatherBean.BasicBean basic = heWeatherDataService.getBasic();
        HeFenWeatherBean.NowBean now = heWeatherDataService.getNow();
        List<HeFenWeatherBean.DailyForecastBean> dailyList = heWeatherDataService.getDaily_forecast();
        String id = basic.getId();
        boolean have = mDao.find(id);
        if (!have) {
            mDao.addCity(id, basic.getCity(), now.getCond().getCode(),
                    dailyList.get(0).getTmp().getMax(),
                    dailyList.get(0).getTmp().getMin(),
                    now.getCond().getTxt());
            readData();
        } else {
            ShowUtils.showToast(this, "抱歉，你不能添加一个城市两次");
        }
    }

    private void readData() {
        mCityList = mDao.getAll();
        if (mCityAdapter != null) {
            mCityAdapter.notifyDataSetChanged();
        }
    }

    class CityAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mCityList.size() + 1;
        }

        @Override
        public Object getItem(int position) {
            return mCityList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
            if (convertView == null) {
                holder = new Holder();
                convertView = View.inflate(MainActivity.this, R.layout.item_city, null);
                holder.mRlAdd = (RelativeLayout) convertView.findViewById(R.id.rl_add);
                holder.mLlCity = (LinearLayout) convertView.findViewById(R.id.ll_city);
                holder.mTvCity = (TextView) convertView.findViewById(R.id.tv_city);
                holder.mIvDel = (ImageView) convertView.findViewById(R.id.iv_del);
                holder.mFlCity = (FrameLayout) convertView.findViewById(R.id.fl_city);
                holder.mIvWeather = (ImageView) convertView.findViewById(R.id.iv_weather);
                holder.mTvMax = (TextView) convertView.findViewById(R.id.tv_weather_x);
                holder.mTvMin = (TextView) convertView.findViewById(R.id.tv_weather_y);
                holder.mTvWeather = (TextView) convertView.findViewById(R.id.tv_weather);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            holder.mRlAdd.setVisibility(position == getCount() - 1 ? View.VISIBLE : View.GONE);
            holder.mLlCity.setVisibility(position != getCount() - 1 ? View.VISIBLE : View.GONE);
            if (position == getCount() - 1) {
                holder.mIvDel.setVisibility(View.GONE);
                holder.mFlCity.setVisibility(isEditState ? View.INVISIBLE : View.VISIBLE);
            } else {
                holder.mTvCity.setText(mCityList.get(position).getCity());
                holder.mIvDel.setVisibility(isEditState ? View.VISIBLE : View.GONE);
                WeatherUtils.setWeatherIconById(MainActivity.this, Integer.parseInt(mCityList.get(position).getCode()), holder.mIvWeather);
                holder.mTvMax.setText(mCityList.get(position).getMax() + "°");
                holder.mTvMin.setText(mCityList.get(position).getMin() + "°");
                holder.mTvWeather.setText(mCityList.get(position).getTxt());
                addCityItemListener(holder, position);
            }
            addListener(holder);
            return convertView;
        }

        private void addCityItemListener(Holder holder, final int position) {
            if (holder.mIvDel != null) {
                holder.mIvDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mCityList.size() == 1) {
                            ShowUtils.showToast(MainActivity.this, "当前只有一个城市，不可删除");
                            return;
                        }
                        if (mDao.find(mCityList.get(position).getId())) {
                            mDao.deleteById(mCityList.get(position).getId());
                        }
                        mCityList.remove(position);
                        mGvCity.setAdapter(mCityAdapter);
                    }
                });
            }
        }

        private void addListener(Holder holder) {
            if (holder.mRlAdd != null) {
                holder.mRlAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivityForResult(new Intent(MainActivity.this, AddCityActivity.class), 0);
                    }
                });
            }
        }

        class Holder {
            TextView mTvCity, mTvMax, mTvMin, mTvWeather;
            ImageView mIvDel;
            ImageView mIvWeather;
            RelativeLayout mRlAdd;
            LinearLayout mLlCity;
            FrameLayout mFlCity;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_edit).setVisible(!isEditState);
        menu.findItem(R.id.menu_ok).setVisible(isEditState);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWeatherPresenter.unSubscribe();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:

                break;
            case R.id.menu_edit:
                isEditState = true;
                invalidateOptionsMenu();
                mCityAdapter.notifyDataSetChanged();
                break;
            case R.id.menu_ok:
                isEditState = false;
                invalidateOptionsMenu();
                mCityAdapter.notifyDataSetChanged();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (isEditState) {
            isEditState = false;
            invalidateOptionsMenu();
            mCityAdapter.notifyDataSetChanged();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (resultCode) {
                case 0:
                    String cityName = data.getStringExtra(Constants.EXTRA_CITY_NAME);
                    String cityId = data.getStringExtra(Constants.EXTRA_CITY_ID);
                    if (mWeatherPresenter != null) {
                        mWeatherPresenter.subscribeWeather(cityId, Constants.BASE_HEFEN_APP_KEY);
                    }
                    break;
            }
        }
    }
}
