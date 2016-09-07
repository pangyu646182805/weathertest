package com.ppyy.weathertest.ui.base;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.ppyy.weathertest.ui.activity.MainActivity;
import com.ppyy.weathertest.ui.activity.WeatherActivity;
import com.ppyy.weathertest.ui.utils.ShowUtils;
import com.ppyy.weathertest.ui.utils.SystemUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/19.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private List<Activity> activities = new LinkedList<>();
    private long mPressedTime = 0;
    private Activity mCurrentActivity;
    public boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flag = SystemUtils.setTranslateStatusBar(this);

        init();
        initView();
        initData();
        initListener();
        activities.add(this);
    }

    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activities.remove(this);
    }

    @Override
    protected void onResume() {
        mCurrentActivity = this;  // 最上层的activity
        super.onResume();
    }

    protected void init() {}

    protected abstract void initView();

    protected void initData() {}

    protected void initListener() {}

    public boolean checkPermission(String permission) {
        return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermission(String permission, int requestCode) {
        ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
    }

    // 完全退出
    public void exit() {
        for(Activity abActivity : activities) {
            abActivity.finish();
        }
    }

    @Override
    public void onBackPressed() {
        if(this instanceof MainActivity || this instanceof WeatherActivity) {
            if(System.currentTimeMillis() - mPressedTime > 800) {
                ShowUtils.showToast(this, "再按一次，退出程序");
                mPressedTime = System.currentTimeMillis();
                return;
            }
        }
        super.onBackPressed();
    }

}
