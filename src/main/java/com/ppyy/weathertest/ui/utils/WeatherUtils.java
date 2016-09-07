package com.ppyy.weathertest.ui.utils;

import android.content.Context;
import android.widget.ImageView;

import com.ppyy.weathertest.R;

import static android.R.attr.id;

/**
 * Created by Administrator on 2016/9/5.
 */

public class WeatherUtils {
    public static final String MIPMAP = "mipmap";
    public static final String PRE = "id_";

    public static final int ID100 = 100;
    public static final int ID101 = 101;
    public static final int ID102 = 102;
    public static final int ID103 = 103;
    public static final int ID104 = 104;

    public static final int ID200 = 200;
    public static final int ID201 = 201;
    public static final int ID202 = 202;
    public static final int ID203 = 203;
    public static final int ID204 = 204;
    public static final int ID205 = 205;
    public static final int ID206 = 206;
    public static final int ID207 = 207;
    public static final int ID208 = 208;
    public static final int ID209 = 209;
    public static final int ID210 = 210;
    public static final int ID211 = 211;
    public static final int ID212 = 212;
    public static final int ID213 = 213;

    public static final int ID300 = 300;
    public static final int ID301 = 301;
    public static final int ID302 = 302;
    public static final int ID303 = 303;
    public static final int ID304 = 304;
    public static final int ID305 = 305;
    public static final int ID306 = 306;
    public static final int ID307 = 307;
    public static final int ID308 = 308;
    public static final int ID309 = 309;
    public static final int ID310 = 310;
    public static final int ID311 = 311;
    public static final int ID312 = 312;
    public static final int ID313 = 313;

    public static final int ID400 = 400;
    public static final int ID401 = 401;
    public static final int ID402 = 402;
    public static final int ID403 = 403;
    public static final int ID404 = 404;
    public static final int ID405 = 405;
    public static final int ID406 = 406;
    public static final int ID407 = 407;

    public static final int ID500 = 500;
    public static final int ID501 = 501;
    public static final int ID502 = 502;
    public static final int ID503 = 503;
    public static final int ID504 = 504;
    public static final int ID505 = 505;
    public static final int ID506 = 506;
    public static final int ID507 = 507;
    public static final int ID508 = 508;

    public static final int ID900 = 900;
    public static final int ID901 = 901;
    public static final int ID999 = 999;

    public static void setWeatherIconById(Context context, int id, ImageView iv) {
        switch (id) {
            case ID100:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_100, iv);
                break;
            case ID101:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_101, iv);
                break;
            case ID102:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_102, iv);
                break;
            case ID103:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_103, iv);
                break;
            case ID104:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_104, iv);
                break;

            case ID200:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_200, iv);
                break;
            case ID201:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_201, iv);
                break;
            case ID202:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_202, iv);
                break;
            case ID203:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_203, iv);
                break;
            case ID204:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_204, iv);
                break;
            case ID205:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_205, iv);
                break;
            case ID206:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_206, iv);
                break;
            case ID207:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_207, iv);
                break;
            case ID208:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_208, iv);
                break;
            case ID209:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_209, iv);
                break;
            case ID210:
            case ID211:
            case ID212:
            case ID213:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_210, iv);
                break;

            case ID300:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_300, iv);
                break;
            case ID301:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_301, iv);
                break;
            case ID302:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_302, iv);
                break;
            case ID303:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_303, iv);
                break;
            case ID304:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_304, iv);
                break;
            case ID305:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_305, iv);
                break;
            case ID306:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_306, iv);
                break;
            case ID307:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_307, iv);
                break;
            case ID308:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_308, iv);
                break;
            case ID309:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_309, iv);
                break;
            case ID310:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_310, iv);
                break;
            case ID311:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_311, iv);
                break;
            case ID312:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_312, iv);
                break;
            case ID313:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_313, iv);
                break;

            case ID400:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_400, iv);
                break;
            case ID401:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_401, iv);
                break;
            case ID402:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_402, iv);
                break;
            case ID403:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_403, iv);
                break;
            case ID404:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_404, iv);
                break;
            case ID405:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_405, iv);
                break;
            case ID406:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_406, iv);
                break;
            case ID407:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_407, iv);
                break;

            case ID500:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_500, iv);
                break;
            case ID501:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_501, iv);
                break;
            case ID502:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_502, iv);
                break;
            case ID503:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_503, iv);
                break;
            case ID504:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_504, iv);
                break;
            case ID505:
            case ID506:
            case ID507:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_507, iv);
                break;
            case ID508:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_508, iv);
                break;

            case ID900:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_900, iv);
                break;
            case ID901:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_901, iv);
                break;
            case ID999:
                ImageLoader.getInstance().displayImage(context, R.mipmap.id_999, iv);
                break;
        }
    }

    public static int getWeatherIconResId(int weatherCode) {
        int resId = R.mipmap.id_100;
        switch (weatherCode) {
            case ID100:
                resId = R.mipmap.id_100;
                break;
            case ID101:
                resId = R.mipmap.id_101;
                break;
            case ID102:
                resId = R.mipmap.id_102;
                break;
            case ID103:
                resId = R.mipmap.id_103;
                break;
            case ID104:
                resId = R.mipmap.id_104;
                break;

            case ID200:
                resId = R.mipmap.id_200;
                break;
            case ID201:
                resId = R.mipmap.id_201;
                break;
            case ID202:
                resId = R.mipmap.id_202;
                break;
            case ID203:
                resId = R.mipmap.id_203;
                break;
            case ID204:
                resId = R.mipmap.id_204;
                break;
            case ID205:
                resId = R.mipmap.id_205;
                break;
            case ID206:
                resId = R.mipmap.id_206;
                break;
            case ID207:
                resId = R.mipmap.id_207;
                break;
            case ID208:
                resId = R.mipmap.id_208;
                break;
            case ID209:
                resId = R.mipmap.id_209;
                break;
            case ID210:
            case ID211:
            case ID212:
            case ID213:
                resId = R.mipmap.id_210;
                break;

            case ID300:
                resId = R.mipmap.id_300;
                break;
            case ID301:
                resId = R.mipmap.id_301;
                break;
            case ID302:
                resId = R.mipmap.id_302;
                break;
            case ID303:
                resId = R.mipmap.id_303;
                break;
            case ID304:
                resId = R.mipmap.id_304;
                break;
            case ID305:
                resId = R.mipmap.id_305;
                break;
            case ID306:
                resId = R.mipmap.id_306;
                break;
            case ID307:
                resId = R.mipmap.id_307;
                break;
            case ID308:
                resId = R.mipmap.id_308;
                break;
            case ID309:
                resId = R.mipmap.id_309;
                break;
            case ID310:
                resId = R.mipmap.id_310;
                break;
            case ID311:
                resId = R.mipmap.id_311;
                break;
            case ID312:
                resId = R.mipmap.id_312;
                break;
            case ID313:
                resId = R.mipmap.id_313;
                break;

            case ID400:
                resId = R.mipmap.id_400;
                break;
            case ID401:
                resId = R.mipmap.id_401;
                break;
            case ID402:
                resId = R.mipmap.id_402;
                break;
            case ID403:
                resId = R.mipmap.id_403;
                break;
            case ID404:
                resId = R.mipmap.id_404;
                break;
            case ID405:
                resId = R.mipmap.id_405;
                break;
            case ID406:
                resId = R.mipmap.id_406;
                break;
            case ID407:
                resId = R.mipmap.id_407;
                break;

            case ID500:
                resId = R.mipmap.id_500;
                break;
            case ID501:
                resId = R.mipmap.id_501;
                break;
            case ID502:
                resId = R.mipmap.id_502;
                break;
            case ID503:
                resId = R.mipmap.id_503;
                break;
            case ID504:
                resId = R.mipmap.id_504;
                break;
            case ID505:
            case ID506:
            case ID507:
                resId = R.mipmap.id_507;
                break;
            case ID508:
                resId = R.mipmap.id_508;
                break;

            case ID900:
                resId = R.mipmap.id_900;
                break;
            case ID901:
                resId = R.mipmap.id_901;
                break;
            case ID999:
                resId = R.mipmap.id_999;
                break;
        }
        return resId;
    }
}
