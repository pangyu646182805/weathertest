package com.ppyy.weathertest.ui.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtils {
    private static final String PREF_NAME = "config";

    public static boolean getBoolean(Context context, String key, boolean defaulfValue) {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        return sp.getBoolean(key, defaulfValue);
    }

    public static void setBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        return sp.getString(key, defaultValue);
    }

    public static void setString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    public static int getInt(Context context, String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    public static void setInt(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        sp.edit().putInt(key, value).commit();
    }

    public static float getFloat(Context context, String key, float defValue) {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        return sp.getFloat(key, defValue);
    }

    public static void setFloat(Context context, String key, float value) {
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        sp.edit().putFloat(key, value).commit();
    }
}
