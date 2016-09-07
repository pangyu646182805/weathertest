package com.ppyy.weathertest.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ppyy.weathertest.R;
import com.ppyy.weathertest.ui.view.ChartView;

/**
 * Created by Administrator on 2016/9/6.
 */

public class Test extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        ChartView tv1 = (ChartView) findViewById(R.id.tv1);
        final ChartView tv2 = (ChartView) findViewById(R.id.tv2);
        final ChartView tv3 = (ChartView) findViewById(R.id.tv3);
        final ChartView tv4 = (ChartView) findViewById(R.id.tv4);
        final ChartView tv5 = (ChartView) findViewById(R.id.tv5);
        final ChartView tv6 = (ChartView) findViewById(R.id.tv6);
        final ChartView tv7 = (ChartView) findViewById(R.id.tv7);
        final ChartView tv8 = (ChartView) findViewById(R.id.tv8);
        final ChartView tv9 = (ChartView) findViewById(R.id.tv9);

        tv1.setOnClickListener(new ChartView.OnClickListener() {
            @Override
            public void onClick(String msg) {
                System.out.println(msg);
            }
        });
        tv2.setOnClickListener(new ChartView.OnClickListener() {
            @Override
            public void onClick(String msg) {
                System.out.println(msg);
            }
        });

        tv1.setDayTemp(36);
        tv1.setNightTemp(27);
        tv1.setCenterTemp(27);

        tv2.setDayTemp(35);
        tv2.setNightTemp(26);
        tv2.setCenterTemp(27);
        tv1.setNextDayTemp(35);
        tv1.setNextNightTemp(26);

        tv1.setOnNextTempFinishedListener(new ChartView.OnNextTempFinishedListener() {
            @Override
            public void onNextTempFinished(float nextDayTempY, float nextNightTempY) {
                tv2.mPreDayTempY = nextDayTempY;
                tv2.mPreNightTempY = nextNightTempY;
            }
        });

        tv3.setDayTemp(35);
        tv3.setNightTemp(26);
        tv3.setCenterTemp(27);
        tv2.setNextDayTemp(35);
        tv2.setNextNightTemp(26);

        tv2.setOnNextTempFinishedListener(new ChartView.OnNextTempFinishedListener() {
            @Override
            public void onNextTempFinished(float nextDayTempY, float nextNightTempY) {
                tv3.mPreDayTempY = nextDayTempY;
                tv3.mPreNightTempY = nextNightTempY;
            }
        });

        tv4.setDayTemp(32);
        tv4.setNightTemp(24);
        tv4.setCenterTemp(27);
        tv3.setNextDayTemp(32);
        tv3.setNextNightTemp(24);

        tv3.setOnNextTempFinishedListener(new ChartView.OnNextTempFinishedListener() {
            @Override
            public void onNextTempFinished(float nextDayTempY, float nextNightTempY) {
                tv4.mPreDayTempY = nextDayTempY;
                tv4.mPreNightTempY = nextNightTempY;
            }
        });

        tv5.setDayTemp(31);
        tv5.setNightTemp(24);
        tv5.setCenterTemp(27);
        tv4.setNextDayTemp(31);
        tv4.setNextNightTemp(24);

        tv4.setOnNextTempFinishedListener(new ChartView.OnNextTempFinishedListener() {
            @Override
            public void onNextTempFinished(float nextDayTempY, float nextNightTempY) {
                tv5.mPreDayTempY = nextDayTempY;
                tv5.mPreNightTempY = nextNightTempY;
            }
        });

        tv6.setDayTemp(31);
        tv6.setNightTemp(23);
        tv6.setCenterTemp(27);
        tv5.setNextDayTemp(31);
        tv5.setNextNightTemp(23);

        tv5.setOnNextTempFinishedListener(new ChartView.OnNextTempFinishedListener() {
            @Override
            public void onNextTempFinished(float nextDayTempY, float nextNightTempY) {
                tv6.mPreDayTempY = nextDayTempY;
                tv6.mPreNightTempY = nextNightTempY;
            }
        });

        tv7.setDayTemp(31);
        tv7.setNightTemp(23);
        tv7.setCenterTemp(27);
        tv6.setNextDayTemp(31);
        tv6.setNextNightTemp(23);

        tv6.setOnNextTempFinishedListener(new ChartView.OnNextTempFinishedListener() {
            @Override
            public void onNextTempFinished(float nextDayTempY, float nextNightTempY) {
                tv7.mPreDayTempY = nextDayTempY;
                tv7.mPreNightTempY = nextNightTempY;
            }
        });

        tv8.setDayTemp(35);
        tv8.setNightTemp(20);
        tv8.setCenterTemp(27);
        tv7.setNextDayTemp(35);
        tv7.setNextNightTemp(20);

        tv7.setOnNextTempFinishedListener(new ChartView.OnNextTempFinishedListener() {
            @Override
            public void onNextTempFinished(float nextDayTempY, float nextNightTempY) {
                tv8.mPreDayTempY = nextDayTempY;
                tv8.mPreNightTempY = nextNightTempY;
            }
        });

        tv9.setDayTemp(28);
        tv9.setNightTemp(19);
        tv9.setCenterTemp(27);
        tv8.setNextDayTemp(28);
        tv8.setNextNightTemp(19);

        tv8.setOnNextTempFinishedListener(new ChartView.OnNextTempFinishedListener() {
            @Override
            public void onNextTempFinished(float nextDayTempY, float nextNightTempY) {
                tv9.mPreDayTempY = nextDayTempY;
                tv9.mPreNightTempY = nextNightTempY;
            }
        });
    }
}
