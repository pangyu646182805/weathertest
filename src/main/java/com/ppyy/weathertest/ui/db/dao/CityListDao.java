package com.ppyy.weathertest.ui.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ppyy.weathertest.ui.bean.HeFenCityBean;
import com.ppyy.weathertest.ui.db.CityListOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/5.
 */

public class CityListDao {
    private CityListOpenHelper mHelper;
    private Context context;
    private String tableName = "citylist";

    public CityListDao(Context context) {
        mHelper = new CityListOpenHelper(context);
        this.context = context;
    }

    public void addCity(String cityId, String cityName, String weatherCode,
                        String max, String min, String desc) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_id", cityId);
        values.put("cityname", cityName);
        values.put("weathercode", weatherCode);
        values.put("max", max);
        values.put("min", min);
        values.put("weatherdesc", desc);
        db.insert(tableName, null, values);
        db.close();
    }

    public void deleteById(String cityId) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(tableName, "_id=?", new String[] {cityId});
        db.close();
    }

    public boolean find(String cityId) {
        System.out.println(cityId + "============================================");
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Cursor cursor = db.query(tableName, null, "_id=?", new String[] {cityId},
                null, null, null);
        boolean result = false;
        if (cursor.moveToNext()) {
            result = true;
        }
        cursor.close();
        db.close();
        return result;
    }

    public List<HeFenCityBean.CityInfoBean> getAll() {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Cursor cursor = db.query(tableName, null, null, null,
                null, null, null);
        List<HeFenCityBean.CityInfoBean> infoBeanList = new ArrayList<>();
        HeFenCityBean.CityInfoBean infoBean;
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("_id"));
            String cityName = cursor.getString(cursor.getColumnIndex("cityname"));
            String code = cursor.getString(cursor.getColumnIndex("weathercode"));
            String max = cursor.getString(cursor.getColumnIndex("max"));
            String min = cursor.getString(cursor.getColumnIndex("min"));
            String desc = cursor.getString(cursor.getColumnIndex("weatherdesc"));
            infoBean = new HeFenCityBean.CityInfoBean();
            infoBean.setId(id);
            infoBean.setCity(cityName);
            infoBean.setCode(code);
            infoBean.setMax(max);
            infoBean.setMin(min);
            infoBean.setTxt(desc);
            infoBeanList.add(infoBean);
        }
        return infoBeanList;
    }
}
