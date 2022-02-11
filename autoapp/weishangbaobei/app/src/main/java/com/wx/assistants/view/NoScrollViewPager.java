package com.wx.assistants.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoScrollViewPager extends ViewPager {
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public NoScrollViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NoScrollViewPager(Context context) {
        super(context);
    }
}
