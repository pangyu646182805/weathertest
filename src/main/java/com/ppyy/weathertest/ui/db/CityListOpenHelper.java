package com.ppyy.weathertest.ui.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/9/5.
 */

public class CityListOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "citylist.db";

    public CityListOpenHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table citylist (_id varchar(20) primary key,cityname varchar(10)," +
                "weathercode varchar(10),max varchar(10),min varchar(10), weatherdesc varchar(10))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
