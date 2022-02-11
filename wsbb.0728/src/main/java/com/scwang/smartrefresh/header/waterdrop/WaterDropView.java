package com.scwang.smartrefresh.header.waterdrop;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.ColorInt;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.yalantis.ucrop.view.CropImageView;

public class WaterDropView extends View {
    protected static final int BACK_ANIM_DURATION = 180;
    protected static int STROKE_WIDTH = 2;
    protected Circle bottomCircle = new Circle();
    protected int mMaxCircleRadius;
    protected int mMinCircleRadius;
    protected Paint mPaint = new Paint();
    protected Path mPath = new Path();
    protected Circle topCircle = new Circle();

    public WaterDropView(Context context) {
        super(context);
        this.mPaint.setColor(-7829368);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        Paint paint = this.mPaint;
        int dp2px = DensityUtil.dp2px(1.0f);
        STROKE_WIDTH = dp2px;
        paint.setStrokeWidth((float) dp2px);
        this.mPaint.setShadowLayer((float) STROKE_WIDTH, (float) (STROKE_WIDTH / 2), (float) STROKE_WIDTH, SystemBarTintManager.DEFAULT_TINT_COLOR);
        setLayerType(1, (Paint) null);
        int i = STROKE_WIDTH * 4;
        setPadding(i, i, i, i);
        this.mPaint.setColor(-7829368);
        this.mMaxCircleRadius = DensityUtil.dp2px(20.0f);
        this.mMinCircleRadius = this.mMaxCircleRadius / 5;
        this.topCircle.radius = (float) this.mMaxCircleRadius;
        this.bottomCircle.radius = (float) this.mMaxCircleRadius;
        this.topCircle.x = (float) (STROKE_WIDTH + this.mMaxCircleRadius);
        this.topCircle.y = (float) (STROKE_WIDTH + this.mMaxCircleRadius);
        this.bottomCircle.x = (float) (STROKE_WIDTH + this.mMaxCircleRadius);
        this.bottomCircle.y = (float) (STROKE_WIDTH + this.mMaxCircleRadius);
    }

    private double getAngle() {
        if (this.bottomCircle.radius > this.topCircle.radius) {
            return 0.0d;
        }
        return Math.asin((double) ((this.topCircle.radius - this.bottomCircle.radius) / (this.bottomCircle.y - this.topCircle.y)));
    }

    private void makeBezierPath() {
        this.mPath.reset();
        this.mPath.addCircle(this.topCircle.x, this.topCircle.y, this.topCircle.radius, Path.Direction.CCW);
        if (this.bottomCircle.y > this.topCircle.y + ((float) DensityUtil.dp2px(1.0f))) {
            this.mPath.addCircle(this.bottomCircle.x, this.bottomCircle.y, this.bottomCircle.radius, Path.Direction.CCW);
            double angle = getAngle();
            double d = (double) this.topCircle.x;
            double d2 = (double) this.topCircle.radius;
            double cos = Math.cos(angle);
            Double.isNaN(d2);
            Double.isNaN(d);
            double d3 = (double) this.topCircle.y;
            double d4 = (double) this.topCircle.radius;
            double sin = Math.sin(angle);
            Double.isNaN(d4);
            Double.isNaN(d3);
            float f = (float) (d3 + (d4 * sin));
            double d5 = (double) this.topCircle.x;
            double d6 = (double) this.topCircle.radius;
            double cos2 = Math.cos(angle);
            Double.isNaN(d6);
            Double.isNaN(d5);
            double d7 = (double) this.bottomCircle.x;
            double d8 = (double) this.bottomCircle.radius;
            double cos3 = Math.cos(angle);
            Double.isNaN(d8);
            Double.isNaN(d7);
            float f2 = (float) (d7 - (d8 * cos3));
            double d9 = (double) this.bottomCircle.y;
            double d10 = (double) this.bottomCircle.radius;
            double sin2 = Math.sin(angle);
            Double.isNaN(d10);
            Double.isNaN(d9);
            float f3 = (float) (d9 + (d10 * sin2));
            double d11 = (double) this.bottomCircle.x;
            double d12 = (double) this.bottomCircle.radius;
            double cos4 = Math.cos(angle);
            Double.isNaN(d12);
            Double.isNaN(d11);
            this.mPath.moveTo(this.topCircle.x, this.topCircle.y);
            this.mPath.lineTo((float) (d - (d2 * cos)), f);
            this.mPath.quadTo(this.bottomCircle.x - this.bottomCircle.radius, (this.bottomCircle.y + this.topCircle.y) / 2.0f, f2, f3);
            this.mPath.lineTo((float) ((cos4 * d12) + d11), f3);
            this.mPath.quadTo(this.bottomCircle.x + this.bottomCircle.radius, (this.bottomCircle.y + f) / 2.0f, (float) (d5 + (d6 * cos2)), f);
        }
        this.mPath.close();
    }

    public ValueAnimator createAnimator() {
        ValueAnimator duration = ValueAnimator.ofFloat(new float[]{1.0f, 0.001f}).setDuration(180);
        duration.setInterpolator(new DecelerateInterpolator());
        duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                WaterDropView.this.updateCompleteState(((Float) valueAnimator.getAnimatedValue()).floatValue());
                WaterDropView.this.postInvalidate();
            }
        });
        return duration;
    }

    public Circle getBottomCircle() {
        return this.bottomCircle;
    }

    public int getIndicatorColor() {
        return this.mPaint.getColor();
    }

    public int getMaxCircleRadius() {
        return this.mMaxCircleRadius;
    }

    public Circle getTopCircle() {
        return this.topCircle;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingTop = getPaddingTop();
        int paddingLeft = getPaddingLeft();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        canvas.save();
        float f = (float) height;
        float f2 = (float) paddingTop;
        float f3 = (float) paddingBottom;
        if (f <= (this.topCircle.radius * 2.0f) + f2 + f3) {
            canvas.translate((float) paddingLeft, (f - (this.topCircle.radius * 2.0f)) - f3);
            canvas.drawCircle(this.topCircle.x, this.topCircle.y, this.topCircle.radius, this.mPaint);
        } else {
            canvas.translate((float) paddingLeft, f2);
            makeBezierPath();
            canvas.drawPath(this.mPath, this.mPaint);
        }
        canvas.restore();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        updateCompleteState(getHeight());
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.setMeasuredDimension(((this.mMaxCircleRadius + STROKE_WIDTH) * 2) + getPaddingLeft() + getPaddingRight(), View.resolveSize(getPaddingTop() + ((int) Math.ceil((double) (this.bottomCircle.y + this.bottomCircle.radius + ((float) (STROKE_WIDTH * 2))))) + getPaddingBottom(), i2));
    }

    public void setIndicatorColor(@ColorInt int i) {
        this.mPaint.setColor(i);
    }

    public void updateCompleteState(float f) {
        double d = (double) this.mMaxCircleRadius;
        double d2 = (double) f;
        Double.isNaN(d2);
        double d3 = (double) this.mMaxCircleRadius;
        Double.isNaN(d3);
        Double.isNaN(d);
        this.topCircle.radius = (float) (d - ((d2 * 0.25d) * d3));
        this.bottomCircle.radius = (((float) (this.mMinCircleRadius - this.mMaxCircleRadius)) * f) + ((float) this.mMaxCircleRadius);
        this.bottomCircle.y = this.topCircle.y + (4.0f * f * ((float) this.mMaxCircleRadius));
    }

    public void updateCompleteState(int i) {
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        float f = (float) ((this.mMaxCircleRadius * 2) + paddingTop + paddingBottom);
        float f2 = (float) i;
        if (f2 < f) {
            this.topCircle.radius = (float) this.mMaxCircleRadius;
            this.bottomCircle.radius = (float) this.mMaxCircleRadius;
            this.bottomCircle.y = this.topCircle.y;
            return;
        }
        double d = (double) ((float) (this.mMaxCircleRadius - this.mMinCircleRadius));
        double pow = Math.pow(100.0d, (double) ((-Math.max(CropImageView.DEFAULT_ASPECT_RATIO, f2 - f)) / ((float) DensityUtil.dp2px(200.0f))));
        Double.isNaN(d);
        float f3 = (float) ((1.0d - pow) * d);
        this.topCircle.radius = ((float) this.mMaxCircleRadius) - (f3 / 4.0f);
        this.bottomCircle.radius = ((float) this.mMaxCircleRadius) - f3;
        this.bottomCircle.y = ((float) ((i - paddingTop) - paddingBottom)) - this.bottomCircle.radius;
    }

    public void updateCompleteState(int i, int i2) {
    }
}
