package com.wx.assistants.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ScrollView;
import android.widget.Scroller;
import java.lang.reflect.Field;

public class CustomScrollView extends ScrollView {
    private static final String TAG = "CustomScrollView";
    private int Scroll_height = 0;
    private GestureDetector mGestureDetector;
    protected Field scrollView_mScroller;
    private int view_height = 0;

    public CustomScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mGestureDetector = new GestureDetector(context, new YScrollDetector());
        setFadingEdgeLength(0);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            stopAnim();
        }
        return super.onInterceptTouchEvent(motionEvent) && this.mGestureDetector.onTouchEvent(motionEvent);
    }

    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
        YScrollDetector() {
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return Math.abs(f2) > Math.abs(f);
        }
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        boolean z = this.Scroll_height - this.view_height == i2;
        if (i2 == 0 || z) {
            try {
                if (this.scrollView_mScroller == null) {
                    this.scrollView_mScroller = getDeclaredField(this, "mScroller");
                }
                Object obj = this.scrollView_mScroller.get(this);
                if (obj == null) {
                    return;
                }
                if (obj instanceof Scroller) {
                    ((Scroller) obj).abortAnimation();
                } else {
                    return;
                }
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
        super.onScrollChanged(i, i2, i3, i4);
    }

    private void stopAnim() {
        try {
            if (this.scrollView_mScroller == null) {
                this.scrollView_mScroller = getDeclaredField(this, "mScroller");
            }
            Object obj = this.scrollView_mScroller.get(this);
            if (obj != null) {
                obj.getClass().getMethod("abortAnimation", new Class[0]).invoke(obj, new Object[0]);
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public int computeVerticalScrollRange() {
        this.Scroll_height = super.computeVerticalScrollRange();
        return this.Scroll_height;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            this.view_height = i4 - i2;
        }
    }

    public void requestChildFocus(View view, View view2) {
        if (view2 == null || !(view2 instanceof WebView)) {
            super.requestChildFocus(view, view2);
        }
    }

    public static Field getDeclaredField(Object obj, String str) {
        Class cls = obj.getClass();
        while (cls != Object.class) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                declaredField.setAccessible(true);
                return declaredField;
            } catch (Exception unused) {
                cls = cls.getSuperclass();
            }
        }
        return null;
    }
}
