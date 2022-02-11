package com.scwang.smartrefresh.layout.internal;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.support.annotation.NonNull;
import android.view.animation.LinearInterpolator;

public class ProgressDrawable extends PaintDrawable implements Animatable, ValueAnimator.AnimatorUpdateListener {
    protected int mHeight = 0;
    protected Path mPath = new Path();
    protected int mProgressDegree = 0;
    protected ValueAnimator mValueAnimator = ValueAnimator.ofInt(new int[]{30, 3600});
    protected int mWidth = 0;

    public ProgressDrawable() {
        this.mValueAnimator.setDuration(10000);
        this.mValueAnimator.setInterpolator(new LinearInterpolator());
        this.mValueAnimator.setRepeatCount(-1);
        this.mValueAnimator.setRepeatMode(1);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.mProgressDegree = (((Integer) valueAnimator.getAnimatedValue()).intValue() / 30) * 30;
        invalidateSelf();
    }

    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds();
        int width = bounds.width();
        int height = bounds.height();
        int max = Math.max(1, width / 20);
        if (!(this.mWidth == width && this.mHeight == height)) {
            this.mPath.reset();
            float f = (float) (width - max);
            int i = height / 2;
            float f2 = (float) i;
            float f3 = (float) max;
            this.mPath.addCircle(f, f2, f3, Path.Direction.CW);
            float f4 = (float) (width - (max * 5));
            this.mPath.addRect(f4, (float) (i - max), f, (float) (i + max), Path.Direction.CW);
            this.mPath.addCircle(f4, f2, f3, Path.Direction.CW);
            this.mWidth = width;
            this.mHeight = height;
        }
        canvas.save();
        float f5 = (float) (width / 2);
        float f6 = (float) (height / 2);
        canvas.rotate((float) this.mProgressDegree, f5, f6);
        for (int i2 = 0; i2 < 12; i2++) {
            this.mPaint.setAlpha((i2 + 5) * 17);
            canvas.rotate(30.0f, f5, f6);
            canvas.drawPath(this.mPath, this.mPaint);
        }
        canvas.restore();
    }

    public void start() {
        if (!this.mValueAnimator.isRunning()) {
            this.mValueAnimator.addUpdateListener(this);
            this.mValueAnimator.start();
        }
    }

    public void stop() {
        if (this.mValueAnimator.isRunning()) {
            this.mValueAnimator.removeAllListeners();
            this.mValueAnimator.removeAllUpdateListeners();
            this.mValueAnimator.cancel();
        }
    }

    public boolean isRunning() {
        return this.mValueAnimator.isRunning();
    }
}
