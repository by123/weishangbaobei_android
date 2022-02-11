package com.wx.assistants.dialog.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import com.example.wx.assistant.R;
import com.yalantis.ucrop.view.CropImageView;

public class MNHudCircularProgressBar extends View {
    private int backgroundColor = -7829368;
    private Paint backgroundPaint;
    private float backgroundStrokeWidth = 10.0f;
    private int color = -16777216;
    private Paint foregroundPaint;
    private float lastProgress = CropImageView.DEFAULT_ASPECT_RATIO;
    private long mDuration = 300;
    /* access modifiers changed from: private */
    public float progress = CropImageView.DEFAULT_ASPECT_RATIO;
    private RectF rectF;
    private int startAngle = -90;
    private float strokeWidth = 10.0f;

    public MNHudCircularProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    /* JADX INFO: finally extract failed */
    private void init(Context context, AttributeSet attributeSet) {
        this.rectF = new RectF();
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.MNHudCircularProgressBar, 0, 0);
        try {
            this.progress = obtainStyledAttributes.getFloat(2, this.progress);
            this.strokeWidth = obtainStyledAttributes.getDimension(4, this.strokeWidth);
            this.backgroundStrokeWidth = obtainStyledAttributes.getDimension(1, this.backgroundStrokeWidth);
            this.color = obtainStyledAttributes.getInt(3, this.color);
            this.backgroundColor = obtainStyledAttributes.getInt(0, this.backgroundColor);
            obtainStyledAttributes.recycle();
            this.backgroundPaint = new Paint(1);
            this.backgroundPaint.setColor(this.backgroundColor);
            this.backgroundPaint.setStyle(Paint.Style.STROKE);
            this.backgroundPaint.setStrokeWidth(this.backgroundStrokeWidth);
            this.foregroundPaint = new Paint(1);
            this.foregroundPaint.setColor(this.color);
            this.foregroundPaint.setStyle(Paint.Style.STROKE);
            this.foregroundPaint.setStrokeWidth(this.strokeWidth);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawOval(this.rectF, this.backgroundPaint);
        Canvas canvas2 = canvas;
        canvas2.drawArc(this.rectF, (float) this.startAngle, (this.progress * 360.0f) / 100.0f, false, this.foregroundPaint);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int min = Math.min(getDefaultSize(getSuggestedMinimumWidth(), i), getDefaultSize(getSuggestedMinimumHeight(), i2));
        setMeasuredDimension(min, min);
        float f = this.strokeWidth > this.backgroundStrokeWidth ? this.strokeWidth : this.backgroundStrokeWidth;
        RectF rectF2 = this.rectF;
        float f2 = f / 2.0f;
        float f3 = CropImageView.DEFAULT_ASPECT_RATIO + f2;
        float f4 = ((float) min) - f2;
        rectF2.set(f3, f3, f4, f4);
    }

    public float getProgress() {
        return this.progress;
    }

    public void setProgress(float f) {
        setProgress(f, true);
    }

    public void setProgress(float f, boolean z) {
        float f2 = 100.0f;
        if (f <= 100.0f) {
            f2 = f;
        }
        this.progress = f2;
        if (z) {
            startAnim();
            this.lastProgress = f;
            return;
        }
        invalidate();
    }

    public void startAnim() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{this.lastProgress, this.progress});
        ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        ofFloat.setDuration(this.mDuration);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float unused = MNHudCircularProgressBar.this.progress = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                MNHudCircularProgressBar.this.postInvalidate();
            }
        });
        ofFloat.start();
    }

    public float getProgressBarWidth() {
        return this.strokeWidth;
    }

    public void setProgressBarWidth(float f) {
        this.strokeWidth = f;
        this.foregroundPaint.setStrokeWidth(f);
        requestLayout();
        invalidate();
    }

    public float getBackgroundProgressBarWidth() {
        return this.backgroundStrokeWidth;
    }

    public void setBackgroundProgressBarWidth(float f) {
        this.backgroundStrokeWidth = f;
        this.backgroundPaint.setStrokeWidth(f);
        requestLayout();
        invalidate();
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int i) {
        this.color = i;
        this.foregroundPaint.setColor(i);
        invalidate();
        requestLayout();
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(int i) {
        this.backgroundColor = i;
        this.backgroundPaint.setColor(i);
        invalidate();
        requestLayout();
    }

    public void setProgressWithAnimation(float f) {
        setProgressWithAnimation(f, 1500);
    }

    public void setProgressWithAnimation(float f, int i) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "progress", new float[]{f});
        ofFloat.setDuration((long) i);
        ofFloat.setInterpolator(new DecelerateInterpolator());
        ofFloat.start();
    }
}
