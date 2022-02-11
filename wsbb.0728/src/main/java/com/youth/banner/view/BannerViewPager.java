package com.youth.banner.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class BannerViewPager extends ViewPager {
    private boolean scrollable = true;

    public BannerViewPager(Context context) {
        super(context);
    }

    public BannerViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.scrollable) {
            return false;
        }
        if (getCurrentItem() == 0 && getChildCount() == 0) {
            return false;
        }
        return BannerViewPager.super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.scrollable) {
            return false;
        }
        if (getCurrentItem() == 0 && getChildCount() == 0) {
            return false;
        }
        return BannerViewPager.super.onTouchEvent(motionEvent);
    }

    public void setScrollable(boolean z) {
        this.scrollable = z;
    }
}
