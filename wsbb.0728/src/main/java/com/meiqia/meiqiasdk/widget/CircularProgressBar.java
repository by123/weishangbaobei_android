package com.meiqia.meiqiasdk.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.meiqia.meiqiasdk.R;
import com.yalantis.ucrop.view.CropImageView;

public class CircularProgressBar extends View {
    private int backgroundColor = -7829368;
    private Paint backgroundPaint;
    private Paint backgroundRectPaint;
    private float backgroundStrokeWidth = 8.0f;
    private int color = -16777216;
    private Paint foregroundPaint;
    private float progress = CropImageView.DEFAULT_ASPECT_RATIO;
    private RectF rectF;
    private RectF rectFRect;
    private int startAngle = -90;
    private float strokeWidth = 8.0f;

    public CircularProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        this.backgroundColor = getResources().getColor(R.color.mq_circle_progress_bg);
        this.color = getResources().getColor(R.color.mq_circle_progress_color);
        this.rectF = new RectF();
        this.rectFRect = new RectF();
        this.backgroundPaint = new Paint(1);
        this.backgroundPaint.setColor(this.backgroundColor);
        this.backgroundPaint.setStyle(Paint.Style.STROKE);
        this.backgroundPaint.setStrokeWidth(this.backgroundStrokeWidth);
        this.backgroundRectPaint = new Paint(1);
        this.backgroundRectPaint.setColor(this.backgroundColor);
        this.backgroundRectPaint.setStyle(Paint.Style.STROKE);
        this.backgroundRectPaint.setStrokeWidth(this.strokeWidth);
        this.backgroundRectPaint.setStyle(Paint.Style.FILL);
        this.foregroundPaint = new Paint(1);
        this.foregroundPaint.setColor(this.color);
        this.foregroundPaint.setStyle(Paint.Style.STROKE);
        this.foregroundPaint.setStrokeWidth(this.strokeWidth);
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    public float getBackgroundProgressBarWidth() {
        return this.backgroundStrokeWidth;
    }

    public int getColor() {
        return this.color;
    }

    public float getProgress() {
        return this.progress;
    }

    public float getProgressBarWidth() {
        return this.strokeWidth;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawOval(this.rectF, this.backgroundPaint);
        Canvas canvas2 = canvas;
        canvas2.drawArc(this.rectF, (float) this.startAngle, (this.progress * 360.0f) / 100.0f, false, this.foregroundPaint);
        canvas.drawRect(this.rectFRect, this.backgroundRectPaint);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int defaultSize = getDefaultSize(getSuggestedMinimumHeight(), i2);
        int defaultSize2 = getDefaultSize(getSuggestedMinimumWidth(), i);
        int min = Math.min(defaultSize2, defaultSize);
        setMeasuredDimension(min, min);
        float f = this.strokeWidth > this.backgroundStrokeWidth ? this.strokeWidth : this.backgroundStrokeWidth;
        RectF rectF2 = this.rectF;
        float f2 = f / 2.0f;
        float f3 = CropImageView.DEFAULT_ASPECT_RATIO + f2;
        float f4 = ((float) min) - f2;
        rectF2.set(f3, f3, f4, f4);
        float f5 = (float) defaultSize;
        float f6 = (float) defaultSize2;
        this.rectFRect.set(f5 * 0.4f, 0.4f * f6, f5 * 0.6f, f6 * 0.6f);
    }

    public void setBackgroundColor(int i) {
        this.backgroundColor = i;
        this.backgroundPaint.setColor(i);
        invalidate();
        requestLayout();
    }

    public void setBackgroundProgressBarWidth(float f) {
        this.backgroundStrokeWidth = f;
        this.backgroundPaint.setStrokeWidth(f);
        requestLayout();
        invalidate();
    }

    public void setColor(int i) {
        this.color = i;
        this.foregroundPaint.setColor(i);
        invalidate();
        requestLayout();
    }

    public void setProgress(float f) {
        this.progress = f <= 100.0f ? f : 100.0f;
        invalidate();
        if (f >= 100.0f) {
            this.progress = CropImageView.DEFAULT_ASPECT_RATIO;
        }
    }

    public void setProgressBarWidth(float f) {
        this.strokeWidth = f;
        this.foregroundPaint.setStrokeWidth(f);
        requestLayout();
        invalidate();
    }
}
