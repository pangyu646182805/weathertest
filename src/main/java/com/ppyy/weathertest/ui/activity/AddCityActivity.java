package com.ppyy.weathertest.ui.activity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ppyy.weathertest.R;
import com.ppyy.weathertest.ui.adapter.AddCityAdapter;
import com.ppyy.weathertest.ui.base.BaseActivity;
import com.ppyy.weathertest.ui.bean.HeFenCityBean;
import com.ppyy.weathertest.ui.conf.Constants;
import com.ppyy.weathertest.ui.contract.CityContract;
import com.ppyy.weathertest.ui.presenter.CityPresenter;
import com.ppyy.weathertest.ui.utils.CommonUtils;
import com.ppyy.weathertest.ui.utils.PrefUtils;
import com.ppyy.weathertest.ui.utils.ShowUtils;
import com.ppyy.weathertest.ui.utils.SystemUtils;
import com.ppyy.weathertest.ui.view.PinnedHeaderListView;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/3.
 */

public class AddCityActivity extends BaseActivity implements TencentLocationListener, CityContract.View,
        SwipeRefreshLayout.OnRefreshListener {
    private static final int PERSION_ACCESS_COARSE_LOCATION = 0;
    @BindView(R.id.ll_top)
    LinearLayout mLlTop;
    @BindView(R.id.lv_city)
    PinnedHeaderListView mLvCity;
    @BindView(R.id.tv_location)
    TextView mTvLocation;
    @BindView(R.id.refresh_city)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.rv_prov)
    RecyclerView mRvProv;
    private TencentLocationManager mManager;

    private CityContract.Presenter mCityPresenter;
    private List<HeFenCityBean.CityInfoBean> mResultBean = null;
    private AddCityAdapter mAddCityAdapter;
    private LinearLayoutManager mLayoutManager;
    private ProvAdapter mProvAdapter;
    private AddCityBroadcastReceiver mReceiver;

    @Override
    protected void init() {
        mLayoutManager = new LinearLayoutManager(this);
        mReceiver = new AddCityBroadcastReceiver();
        IntentFilter filter = new IntentFilter(Constants.ACTION_ADD_CITY_RESULT);
        registerReceiver(mReceiver, filter);
        new CityPresenter(this);
        CommonUtils.lazyLoad(this, new Handler(), new Runnable() {
            @Override
            public void run() {
                showLoading();
                onRefresh();
            }
        });
        boolean grant = checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
        if (grant) {
            requestLocation();
        } else {
            requestPermission(Manifest.permission.ACCESS_COARSE_LOCATION, PERSION_ACCESS_COARSE_LOCATION);
        }
    }

    private void requestLocation() {
        TencentLocationRequest request = TencentLocationRequest.create();
        request.setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_POI);
        if (mManager == null) {
            mManager = TencentLocationManager.getInstance(this);
        }
        mManager.requestLocationUpdates(request, this);
    }

    private void removeUpdate() {
        if (mManager != null) {
            mManager.removeUpdates(this);
            mManager = null;
        }
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_add_city);
        ButterKnife.bind(this);
        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mRefreshLayout.setOnRefreshListener(this);
        if (flag) {
            mLlTop.setPadding(0, SystemUtils.getStatusHeight(this), 0, 0);
        }
    }

    @Override
    protected void initData() {
        mAddCityAdapter = new AddCityAdapter(this, mRvProv);
        mLvCity.setAdapter(mAddCityAdapter);
        mLvCity.setOnScrollListener(mAddCityAdapter);
        View header = this.getLayoutInflater().inflate(R.layout.item_header, mLvCity, false);
        mLvCity.setPinnedHeaderView(header);

        mProvAdapter = new ProvAdapter();
        mRvProv.setLayoutManager(mLayoutManager);
        mRvProv.setAdapter(mProvAdapter);
    }

    @Override
    protected void initListener() {
        mProvAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                mAddCityAdapter.setClick(true);
                ProvAdapter.Holder holder = (ProvAdapter.Holder) mRvProv.getChildViewHolder(v);
                holder.check(pos);
                mProvAdapter.notifyDataSetChanged();
                mLvCity.setSelection(pos);
            }
        });
    }

    @Override
    public void onLocationChanged(TencentLocation tencentLocation, int error, String s) {
        if (TencentLocation.ERROR_OK == error) {
            // 定位成功
            mTvLocation.setText(tencentLocation.getCity() + "," + tencentLocation.getProvince() + "," + tencentLocation.getNation());
        } else {
            // 定位失败
            mTvLocation.setText("定位失败");
        }
        removeUpdate();
    }

    @Override
    public void onStatusUpdate(String s, int i, String s1) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERSION_ACCESS_COARSE_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestLocation();
            } else {
                ShowUtils.showToast(this, "没有权限");
            }
        }
    }

    @Override
    public void showCitys(List<HeFenCityBean.CityInfoBean> resultBean) {
        List<String> list = getProv(resultBean);
        mAddCityAdapter.setCityInfoBeanList(resultBean, list);
        mProvAdapter.setProvList(list);
        hideLoading();
    }

    @Override
    public void setPresenter(CityContract.Presenter presenter) {
        this.mCityPresenter = presenter;
    }

    @Override
    public void showLoading() {
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showTip(String msg) {
    }

    @Override
    public void onRefresh() {
        mCityPresenter.subscribeCitys(Constants.SEARCH_ALLCHINA, Constants.BASE_HEFEN_APP_KEY);
    }

    private List<String> getProv(List<HeFenCityBean.CityInfoBean> cityInfoBeanList) {
        List<String> provList = new ArrayList<>();
        if (cityInfoBeanList.size() > 1) {
            provList.add(cityInfoBeanList.get(0).getProv());
            for (int i = 1; i < cityInfoBeanList.size(); i++) {
                String temp = cityInfoBeanList.get(i).getProv();
                if (!provList.contains(temp)) {
                    provList.add(temp);
                }
            }
        }
        return provList;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCityPresenter.unSubscribe();
        unregisterReceiver(mReceiver);
    }

    class ProvAdapter extends RecyclerView.Adapter<ProvAdapter.Holder> {
        private OnItemClickListener mOnItemClickListener;
        private List<String> provList;

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            mOnItemClickListener = onItemClickListener;
        }

        public void setProvList(List<String> provList) {
            this.provList = provList;
            notifyDataSetChanged();
        }

        @Override
        public ProvAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(AddCityActivity.this, R.layout.item_prov, null);
            return new Holder(view, mOnItemClickListener);
        }

        @Override
        public void onBindViewHolder(ProvAdapter.Holder holder, int position) {
            holder.setDataAndRefreshUI(provList.get(position));
        }

        @Override
        public int getItemCount() {
            if (provList == null) {
                return 0;
            }
            return provList.size();
        }

        public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private OnItemClickListener mOnItemClickListener;
            private LinearLayout llContent;
            private TextView tvProv;
            private View viewCheck;

            public Holder(View itemView, OnItemClickListener onItemClickListener) {
                super(itemView);
                tvProv = (TextView) itemView.findViewById(R.id.tv_prov);
                viewCheck = itemView.findViewById(R.id.view_check);
                llContent = (LinearLayout) itemView.findViewById(R.id.ll_content);
                this.mOnItemClickListener = onItemClickListener;
                llContent.setOnClickListener(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
                llContent.setLayoutParams(params);
            }

            public void setDataAndRefreshUI(String prov) {
                tvProv.setText(prov);
                int checkedPosition = PrefUtils.getInt(AddCityActivity.this, Constants.CHECKED_POSITION, 0);
                if (getLayoutPosition() == checkedPosition) {
                    llContent.setBackgroundColor(getColor(R.color.white));
                    viewCheck.setVisibility(View.VISIBLE);
                } else {
                    llContent.setBackgroundColor(getColor(R.color.windowBackground));
                    viewCheck.setVisibility(View.INVISIBLE);
                }
            }

            public void check(int pos) {
                PrefUtils.setInt(AddCityActivity.this, Constants.CHECKED_POSITION, pos);
            }

            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, getLayoutPosition());
                }
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    class AddCityBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Constants.ACTION_ADD_CITY_RESULT)) {
                AddCityActivity.this.setResult(0, intent);
                finish();
            }
        }
    }
}
