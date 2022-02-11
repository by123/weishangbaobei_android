package com.wang.avi;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public abstract class Indicator extends Drawable implements Animatable {
    private static final Rect ZERO_BOUNDS_RECT = new Rect();
    private int alpha = 255;
    protected Rect drawBounds = ZERO_BOUNDS_RECT;
    private ArrayList<ValueAnimator> mAnimators;
    private boolean mHasAnimators;
    private Paint mPaint = new Paint();
    private HashMap<ValueAnimator, ValueAnimator.AnimatorUpdateListener> mUpdateListeners = new HashMap<>();

    public Indicator() {
        this.mPaint.setColor(-1);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setAntiAlias(true);
    }

    private void ensureAnimators() {
        if (!this.mHasAnimators) {
            this.mAnimators = onCreateAnimators();
            this.mHasAnimators = true;
        }
    }

    private boolean isStarted() {
        Iterator<ValueAnimator> it = this.mAnimators.iterator();
        if (it.hasNext()) {
            return it.next().isStarted();
        }
        return false;
    }

    private void startAnimators() {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < this.mAnimators.size()) {
                ValueAnimator valueAnimator = this.mAnimators.get(i2);
                ValueAnimator.AnimatorUpdateListener animatorUpdateListener = this.mUpdateListeners.get(valueAnimator);
                if (animatorUpdateListener != null) {
                    valueAnimator.addUpdateListener(animatorUpdateListener);
                }
                valueAnimator.start();
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    private void stopAnimators() {
        if (this.mAnimators != null) {
            Iterator<ValueAnimator> it = this.mAnimators.iterator();
            while (it.hasNext()) {
                ValueAnimator next = it.next();
                if (next != null && next.isStarted()) {
                    next.removeAllUpdateListeners();
                    next.end();
                }
            }
        }
    }

    public void addUpdateListener(ValueAnimator valueAnimator, ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.mUpdateListeners.put(valueAnimator, animatorUpdateListener);
    }

    public int centerX() {
        return this.drawBounds.centerX();
    }

    public int centerY() {
        return this.drawBounds.centerY();
    }

    public void draw(Canvas canvas) {
        draw(canvas, this.mPaint);
    }

    public abstract void draw(Canvas canvas, Paint paint);

    public float exactCenterX() {
        return this.drawBounds.exactCenterX();
    }

    public float exactCenterY() {
        return this.drawBounds.exactCenterY();
    }

    public int getAlpha() {
        return this.alpha;
    }

    public int getColor() {
        return this.mPaint.getColor();
    }

    public Rect getDrawBounds() {
        return this.drawBounds;
    }

    public int getHeight() {
        return this.drawBounds.height();
    }

    public int getOpacity() {
        return -1;
    }

    public int getWidth() {
        return this.drawBounds.width();
    }

    public boolean isRunning() {
        Iterator<ValueAnimator> it = this.mAnimators.iterator();
        if (it.hasNext()) {
            return it.next().isRunning();
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        setDrawBounds(rect);
    }

    public abstract ArrayList<ValueAnimator> onCreateAnimators();

    public void postInvalidate() {
        invalidateSelf();
    }

    public void setAlpha(int i) {
        this.alpha = i;
    }

    public void setColor(int i) {
        this.mPaint.setColor(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void setDrawBounds(int i, int i2, int i3, int i4) {
        this.drawBounds = new Rect(i, i2, i3, i4);
    }

    public void setDrawBounds(Rect rect) {
        setDrawBounds(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void start() {
        ensureAnimators();
        if (this.mAnimators != null && !isStarted()) {
            startAnimators();
            invalidateSelf();
        }
    }

    public void stop() {
        stopAnimators();
    }
}
