package com.samon.wechatimageslicer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class LiquidCircleView extends View {
    private static final int COLOR_BACKGROUND = -1;
    private static final int COLOR_FORGROUND = -14501073;
    private static final int DURATION = 1000;
    private float mHeight;
    private Paint mPaint;
    private RectF mRectF;
    private float mSize;
    private float mWidth;
    private long startTime;
    private float strokeWidth;

    public LiquidCircleView(Context context) {
        super(context);
        this.startTime = 0;
        this.mRectF = null;
        init(context);
    }

    public LiquidCircleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.startTime = 0;
        this.mRectF = null;
        init(context);
    }

    public LiquidCircleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.startTime = 0;
        this.mRectF = null;
        this.mPaint = new Paint();
        init(context);
    }

    private void init(Context context) {
        this.mPaint = new Paint();
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setAntiAlias(true);
        this.mRectF = new RectF();
        this.startTime = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mWidth = (float) getMeasuredWidth();
        this.mWidth = (float) getMeasuredWidth();
        this.mHeight = (float) getMeasuredHeight();
        this.mSize = (this.mWidth < this.mHeight ? this.mWidth : this.mHeight) * 0.8f;
        this.mRectF.left = (this.mWidth - this.mSize) / 2.0f;
        this.mRectF.right = (this.mWidth + this.mSize) / 2.0f;
        this.mRectF.top = (this.mHeight - this.mSize) / 2.0f;
        this.mRectF.bottom = (this.mHeight + this.mSize) / 2.0f;
        this.strokeWidth = this.mSize / 10.0f;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Canvas canvas2 = canvas;
        long currentTimeMillis = System.currentTimeMillis();
        this.mPaint.setColor(-1);
        this.mPaint.setAlpha(240);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(this.strokeWidth);
        canvas2.drawCircle(this.mWidth / 2.0f, this.mHeight / 2.0f, this.mSize / 2.0f, this.mPaint);
        this.mPaint.setAlpha(255);
        this.mPaint.setColor(COLOR_FORGROUND);
        float f = ((((float) ((currentTimeMillis - this.startTime) % 1000)) * 1.0f) / 1000.0f) * 360.0f;
        float f2 = f - 90.0f;
        float f3 = f >= 180.0f ? 360.0f - f : f;
        float f4 = f3 / 2.0f;
        float f5 = f2 - f4;
        canvas.drawArc(this.mRectF, f5, f3, false, this.mPaint);
        double d = (double) (this.mSize / 2.0f);
        double d2 = (double) (f5 + 90.0f);
        Double.isNaN(d2);
        double d3 = (d2 * 3.141592653589793d) / 180.0d;
        double sin = Math.sin(d3);
        Double.isNaN(d);
        double d4 = (double) (this.mWidth / 2.0f);
        Double.isNaN(d4);
        double d5 = (double) (this.mHeight / 2.0f);
        double cos = Math.cos(d3);
        Double.isNaN(d);
        Double.isNaN(d5);
        double d6 = (double) (f2 + f4 + 90.0f);
        Double.isNaN(d6);
        double d7 = (d6 * 3.141592653589793d) / 180.0d;
        double sin2 = Math.sin(d7);
        Double.isNaN(d);
        double d8 = (double) (this.mWidth / 2.0f);
        Double.isNaN(d8);
        double d9 = (double) (this.mHeight / 2.0f);
        double cos2 = Math.cos(d7);
        Double.isNaN(d);
        Double.isNaN(d9);
        this.mPaint.setStyle(Paint.Style.FILL);
        canvas2.drawCircle((float) ((sin * d) + d4), (float) (d5 - (cos * d)), this.strokeWidth / 2.0f, this.mPaint);
        canvas2.drawCircle((float) ((sin2 * d) + d8), (float) (d9 - (d * cos2)), this.strokeWidth / 2.0f, this.mPaint);
        if (isShown()) {
            invalidate();
        }
    }
}
