package com.ppyy.weathertest.ui.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/2/19.
 */
public class ShowUtils {
    public static Toast mToast;

    public static void showToast(Context mContext, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }
}
