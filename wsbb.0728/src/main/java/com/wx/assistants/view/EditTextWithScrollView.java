package com.wx.assistants.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

public class EditTextWithScrollView extends EditText {
    private boolean mBottomFlag = false;
    private boolean mCanVerticalScroll;
    private int mOffsetHeight;

    public EditTextWithScrollView(Context context) {
        super(context);
        init();
    }

    public EditTextWithScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public EditTextWithScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private boolean canVerticalScroll() {
        int scrollY = getScrollY();
        this.mOffsetHeight = getLayout().getHeight() - ((getHeight() - getCompoundPaddingTop()) - getCompoundPaddingBottom());
        if (this.mOffsetHeight == 0) {
            return false;
        }
        if (scrollY <= 0) {
            return scrollY < this.mOffsetHeight + -1;
        }
        return true;
    }

    private void init() {
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.mBottomFlag = false;
        }
        if (this.mBottomFlag) {
            motionEvent.setAction(3);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mCanVerticalScroll = canVerticalScroll();
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (i2 == this.mOffsetHeight || i2 == 0) {
            getParent().requestDisallowInterceptTouchEvent(false);
            this.mBottomFlag = true;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        if (!this.mCanVerticalScroll) {
            getParent().requestDisallowInterceptTouchEvent(false);
        } else if (!this.mBottomFlag) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return onTouchEvent;
    }
}
