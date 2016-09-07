package com.ppyy.weathertest.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * Created by Administrator on 2016/9/7.
 */

public class ChartHorizontalScrollView extends HorizontalScrollView {
    private boolean mCanScroll = true;
    private float mDownX;
    private int mParentWhidth;

    public void setParentWhidth(int parentWhidth) {
        mParentWhidth = parentWhidth;
    }

    public ChartHorizontalScrollView(Context context) {
        super(context);
    }

    public ChartHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChartHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        int maxScrollX = getChildAt(0).getMeasuredWidth() - getMeasuredWidth();
        // 滑到最左
        if (getScrollX() == 0) {
        } else if (getScrollX() == maxScrollX) {  // 滑到最右
        } else {  // 滑到中间
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            mDownX = ev.getX();
        }

        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            int scrollx = getScrollX();
            if ((scrollx == 0 && mDownX - ev.getX() <= -10)
                    || (getChildAt(0).getMeasuredWidth() <= (scrollx + mParentWhidth) && mDownX
                    - ev.getX() >= 10)) {
                mCanScroll = false;
            }
        }

        if (ev.getAction() == MotionEvent.ACTION_UP
                || ev.getAction() == MotionEvent.ACTION_CANCEL) {
            mCanScroll = true;
        }

        if (this.mCanScroll) {
            //此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
            getParent().requestDisallowInterceptTouchEvent(true);
            return super.onTouchEvent(ev);
        } else {
            getParent().requestDisallowInterceptTouchEvent(false);
            return false;
        }
    }
}
