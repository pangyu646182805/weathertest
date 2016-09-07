package com.ppyy.weathertest.ui.base;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.ppyy.weathertest.BuildConfig;

/**
 * Created by Administrator on 2016/8/30.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initStetho();
    }

    private void initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initialize(Stetho.newInitializerBuilder(this)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                    .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                    .build());
        }
    }
}
