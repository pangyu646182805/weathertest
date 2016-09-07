package com.ppyy.weathertest.ui.utils;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.ViewTreeObserver;

/**
 * Created by Administrator on 2016/8/14.
 */

public class CommonUtils {
    /**
     * 延迟加载
     *
     * @param activity
     * @param handler
     * @param runnable
     */
    public static void lazyLoad(Activity activity, final Handler handler, final Runnable runnable) {
        activity.getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        });
    }

    /**
     * 更新ViewPager
     */
    public static void updateViewPager(final int position, final ViewPager pager, final ViewPager.OnPageChangeListener listener) {
        pager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                pager.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                if (listener != null) {
                    listener.onPageSelected(position);
                }
            }
        });
    }
}
