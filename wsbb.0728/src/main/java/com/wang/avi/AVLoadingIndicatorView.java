package com.wang.avi;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.wang.avi.indicators.BallPulseIndicator;

public class AVLoadingIndicatorView extends View {
    private static final BallPulseIndicator DEFAULT_INDICATOR = new BallPulseIndicator();
    private static final int MIN_DELAY = 500;
    private static final int MIN_SHOW_TIME = 500;
    private static final String TAG = "AVLoadingIndicatorView";
    private final Runnable mDelayedHide = new Runnable() {
        public void run() {
            boolean unused = AVLoadingIndicatorView.this.mPostedHide = false;
            long unused2 = AVLoadingIndicatorView.this.mStartTime = -1;
            AVLoadingIndicatorView.this.setVisibility(8);
        }
    };
    private final Runnable mDelayedShow = new Runnable() {
        public void run() {
            boolean unused = AVLoadingIndicatorView.this.mPostedShow = false;
            if (!AVLoadingIndicatorView.this.mDismissed) {
                long unused2 = AVLoadingIndicatorView.this.mStartTime = System.currentTimeMillis();
                AVLoadingIndicatorView.this.setVisibility(0);
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean mDismissed = false;
    private Indicator mIndicator;
    private int mIndicatorColor;
    int mMaxHeight;
    int mMaxWidth;
    int mMinHeight;
    int mMinWidth;
    /* access modifiers changed from: private */
    public boolean mPostedHide = false;
    /* access modifiers changed from: private */
    public boolean mPostedShow = false;
    private boolean mShouldStartAnimationDrawable;
    /* access modifiers changed from: private */
    public long mStartTime = -1;

    public AVLoadingIndicatorView(Context context) {
        super(context);
        init(context, (AttributeSet) null, 0, 0);
    }

    public AVLoadingIndicatorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0, R.style.AVLoadingIndicatorView);
    }

    public AVLoadingIndicatorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i, R.style.AVLoadingIndicatorView);
    }

    @TargetApi(21)
    public AVLoadingIndicatorView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet, i, R.style.AVLoadingIndicatorView);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        this.mMinWidth = 24;
        this.mMaxWidth = 48;
        this.mMinHeight = 24;
        this.mMaxHeight = 48;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AVLoadingIndicatorView, i, i2);
        this.mMinWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AVLoadingIndicatorView_minWidth, this.mMinWidth);
        this.mMaxWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AVLoadingIndicatorView_maxWidth, this.mMaxWidth);
        this.mMinHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AVLoadingIndicatorView_minHeight, this.mMinHeight);
        this.mMaxHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AVLoadingIndicatorView_maxHeight, this.mMaxHeight);
        String string = obtainStyledAttributes.getString(R.styleable.AVLoadingIndicatorView_indicatorName);
        this.mIndicatorColor = obtainStyledAttributes.getColor(R.styleable.AVLoadingIndicatorView_indicatorColor, -1);
        setIndicator(string);
        if (this.mIndicator == null) {
            setIndicator((Indicator) DEFAULT_INDICATOR);
        }
        obtainStyledAttributes.recycle();
    }

    private void removeCallbacks() {
        removeCallbacks(this.mDelayedHide);
        removeCallbacks(this.mDelayedShow);
    }

    private void updateDrawableBounds(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int paddingRight = i - (getPaddingRight() + getPaddingLeft());
        int paddingTop = i2 - (getPaddingTop() + getPaddingBottom());
        if (this.mIndicator != null) {
            float intrinsicWidth = ((float) this.mIndicator.getIntrinsicWidth()) / ((float) this.mIndicator.getIntrinsicHeight());
            float f = (float) paddingRight;
            float f2 = (float) paddingTop;
            float f3 = f / f2;
            if (intrinsicWidth == f3) {
                i3 = 0;
            } else if (f3 > intrinsicWidth) {
                int i6 = (int) (intrinsicWidth * f2);
                i3 = (paddingRight - i6) / 2;
                paddingRight = i6 + i3;
            } else {
                int i7 = (int) ((1.0f / intrinsicWidth) * f);
                int i8 = (paddingTop - i7) / 2;
                paddingTop = i7 + i8;
                i5 = i8;
                i4 = 0;
                this.mIndicator.setBounds(i4, i5, paddingRight, paddingTop);
            }
            i5 = 0;
            i4 = i3;
            this.mIndicator.setBounds(i4, i5, paddingRight, paddingTop);
        }
    }

    private void updateDrawableState() {
        int[] drawableState = getDrawableState();
        if (this.mIndicator != null && this.mIndicator.isStateful()) {
            this.mIndicator.setState(drawableState);
        }
    }

    /* access modifiers changed from: package-private */
    public void drawTrack(Canvas canvas) {
        Indicator indicator = this.mIndicator;
        if (indicator != null) {
            int save = canvas.save();
            canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
            indicator.draw(canvas);
            canvas.restoreToCount(save);
            if (this.mShouldStartAnimationDrawable && (indicator instanceof Animatable)) {
                indicator.start();
                this.mShouldStartAnimationDrawable = false;
            }
        }
    }

    @TargetApi(21)
    public void drawableHotspotChanged(float f, float f2) {
        super.drawableHotspotChanged(f, f2);
        if (this.mIndicator != null) {
            this.mIndicator.setHotspot(f, f2);
        }
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        updateDrawableState();
    }

    public Indicator getIndicator() {
        return this.mIndicator;
    }

    public void hide() {
        this.mDismissed = true;
        removeCallbacks(this.mDelayedShow);
        long currentTimeMillis = System.currentTimeMillis() - this.mStartTime;
        if (currentTimeMillis >= 500 || this.mStartTime == -1) {
            setVisibility(8);
        } else if (!this.mPostedHide) {
            postDelayed(this.mDelayedHide, 500 - currentTimeMillis);
            this.mPostedHide = true;
        }
    }

    public void invalidateDrawable(Drawable drawable) {
        if (verifyDrawable(drawable)) {
            Rect bounds = drawable.getBounds();
            int scrollX = getScrollX() + getPaddingLeft();
            int scrollY = getScrollY() + getPaddingTop();
            invalidate(bounds.left + scrollX, bounds.top + scrollY, scrollX + bounds.right, bounds.bottom + scrollY);
            return;
        }
        super.invalidateDrawable(drawable);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnimation();
        removeCallbacks();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        stopAnimation();
        super.onDetachedFromWindow();
        removeCallbacks();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        synchronized (this) {
            super.onDraw(canvas);
            drawTrack(canvas);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        synchronized (this) {
            Indicator indicator = this.mIndicator;
            if (indicator != null) {
                i3 = Math.max(this.mMinWidth, Math.min(this.mMaxWidth, indicator.getIntrinsicWidth()));
                i4 = Math.max(this.mMinHeight, Math.min(this.mMaxHeight, indicator.getIntrinsicHeight()));
            } else {
                i3 = 0;
                i4 = 0;
            }
            updateDrawableState();
            setMeasuredDimension(resolveSizeAndState(i3 + getPaddingLeft() + getPaddingRight(), i, 0), resolveSizeAndState(getPaddingTop() + getPaddingBottom() + i4, i2, 0));
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        updateDrawableBounds(i, i2);
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i == 8 || i == 4) {
            stopAnimation();
        } else {
            startAnimation();
        }
    }

    public void setIndicator(Indicator indicator) {
        if (this.mIndicator != indicator) {
            if (this.mIndicator != null) {
                this.mIndicator.setCallback((Drawable.Callback) null);
                unscheduleDrawable(this.mIndicator);
            }
            this.mIndicator = indicator;
            setIndicatorColor(this.mIndicatorColor);
            if (indicator != null) {
                indicator.setCallback(this);
            }
            postInvalidate();
        }
    }

    public void setIndicator(String str) {
        if (!TextUtils.isEmpty(str)) {
            StringBuilder sb = new StringBuilder();
            if (!str.contains(".")) {
                sb.append(getClass().getPackage().getName());
                sb.append(".indicators");
                sb.append(".");
            }
            sb.append(str);
            try {
                setIndicator((Indicator) Class.forName(sb.toString()).newInstance());
            } catch (ClassNotFoundException e) {
                Log.e(TAG, "Didn't find your class , check the name again !");
            } catch (InstantiationException e2) {
                e2.printStackTrace();
            } catch (IllegalAccessException e3) {
                e3.printStackTrace();
            }
        }
    }

    public void setIndicatorColor(int i) {
        this.mIndicatorColor = i;
        this.mIndicator.setColor(i);
    }

    public void setVisibility(int i) {
        if (getVisibility() != i) {
            super.setVisibility(i);
            if (i == 8 || i == 4) {
                stopAnimation();
            } else {
                startAnimation();
            }
        }
    }

    public void show() {
        this.mStartTime = -1;
        this.mDismissed = false;
        removeCallbacks(this.mDelayedHide);
        if (!this.mPostedShow) {
            postDelayed(this.mDelayedShow, 500);
            this.mPostedShow = true;
        }
    }

    public void smoothToHide() {
        startAnimation(AnimationUtils.loadAnimation(getContext(), 17432577));
        setVisibility(8);
    }

    public void smoothToShow() {
        startAnimation(AnimationUtils.loadAnimation(getContext(), 17432576));
        setVisibility(0);
    }

    /* access modifiers changed from: package-private */
    public void startAnimation() {
        if (getVisibility() == 0) {
            if (this.mIndicator instanceof Animatable) {
                this.mShouldStartAnimationDrawable = true;
            }
            postInvalidate();
        }
    }

    /* access modifiers changed from: package-private */
    public void stopAnimation() {
        if (this.mIndicator instanceof Animatable) {
            this.mIndicator.stop();
            this.mShouldStartAnimationDrawable = false;
        }
        postInvalidate();
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        return drawable == this.mIndicator || super.verifyDrawable(drawable);
    }
}
