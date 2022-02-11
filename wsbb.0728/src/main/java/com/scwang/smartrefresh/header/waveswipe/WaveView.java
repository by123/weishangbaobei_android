package com.scwang.smartrefresh.header.waveswipe;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import com.yalantis.ucrop.view.CropImageView;

public class WaveView extends View implements ViewTreeObserver.OnPreDrawListener {
    protected static final float[][] APPEAR_PHASE_POINTS = {new float[]{0.1655f, CropImageView.DEFAULT_ASPECT_RATIO}, new float[]{0.5237f, 0.0553f}, new float[]{0.4557f, 0.0936f}, new float[]{0.3908f, 0.1302f}, new float[]{0.4303f, 0.2173f}, new float[]{0.5f, 0.2173f}};
    protected static final float[][] BEGIN_PHASE_POINTS = {new float[]{0.1655f, CropImageView.DEFAULT_ASPECT_RATIO}, new float[]{0.4188f, -0.0109f}, new float[]{0.4606f, -0.0049f}, new float[]{0.4893f, CropImageView.DEFAULT_ASPECT_RATIO}, new float[]{0.4893f, CropImageView.DEFAULT_ASPECT_RATIO}, new float[]{0.5f, CropImageView.DEFAULT_ASPECT_RATIO}};
    protected static final long DROP_BOUNCE_ANIMATOR_DURATION = 500;
    protected static final long DROP_CIRCLE_ANIMATOR_DURATION = 500;
    protected static final int DROP_REMOVE_ANIMATOR_DURATION = 200;
    protected static final long DROP_VERTEX_ANIMATION_DURATION = 500;
    protected static final float[][] EXPAND_PHASE_POINTS = {new float[]{0.1655f, CropImageView.DEFAULT_ASPECT_RATIO}, new float[]{0.5909f, CropImageView.DEFAULT_ASPECT_RATIO}, new float[]{0.4557f, 0.1642f}, new float[]{0.3941f, 0.2061f}, new float[]{0.4303f, 0.2889f}, new float[]{0.5f, 0.2889f}};
    protected static final float MAX_WAVE_HEIGHT = 0.2f;
    protected static final int SHADOW_COLOR = -1728053248;
    protected static final int WAVE_ANIMATOR_DURATION = 1000;
    protected ValueAnimator.AnimatorUpdateListener mAnimatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            WaveView.this.postInvalidate();
        }
    };
    protected float mCurrentCircleCenterY;
    protected ValueAnimator mDisappearCircleAnimator;
    protected ValueAnimator mDropBounceHorizontalAnimator;
    protected ValueAnimator mDropBounceVerticalAnimator;
    protected ValueAnimator mDropCircleAnimator;
    protected Path mDropCirclePath;
    protected float mDropCircleRadius = 100.0f;
    protected boolean mDropHeightUpdated = false;
    protected RectF mDropRect;
    protected Path mDropTangentPath;
    protected ValueAnimator mDropVertexAnimator;
    protected boolean mIsManualRefreshing = false;
    protected int mMaxDropHeight;
    protected Paint mPaint;
    protected Path mShadowPath;
    protected int mUpdateMaxDropHeight;
    protected Path mWavePath;
    protected ValueAnimator mWaveReverseAnimator;
    protected int mWidth;

    public WaveView(Context context) {
        super(context);
        float f = getResources().getDisplayMetrics().density;
        this.mPaint = new Paint();
        this.mPaint.setColor(-14575885);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setShadowLayer((float) ((int) ((f * 2.0f) + 0.5f)), CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, -1728053248);
        this.mWavePath = new Path();
        this.mDropTangentPath = new Path();
        this.mDropCirclePath = new Path();
        this.mShadowPath = new Path();
        resetAnimator();
        this.mDropRect = new RectF();
        setLayerType(1, (Paint) null);
        getViewTreeObserver().addOnPreDrawListener(this);
    }

    public void animationDropCircle() {
        if (!this.mDisappearCircleAnimator.isRunning()) {
            startDropAnimation();
            startWaveAnimation(0.1f);
        }
    }

    public void appearPhase(float f, float f2) {
        onPreDragWave();
        this.mWavePath.moveTo(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
        this.mWavePath.cubicTo(((float) this.mWidth) * APPEAR_PHASE_POINTS[0][0], ((float) this.mWidth) * APPEAR_PHASE_POINTS[0][1], ((float) this.mWidth) * Math.min(BEGIN_PHASE_POINTS[1][0] + f2, APPEAR_PHASE_POINTS[1][0]), ((float) this.mWidth) * Math.max((BEGIN_PHASE_POINTS[1][1] + f) - f2, APPEAR_PHASE_POINTS[1][1]), ((float) this.mWidth) * Math.max(BEGIN_PHASE_POINTS[2][0] - f2, APPEAR_PHASE_POINTS[2][0]), ((float) this.mWidth) * Math.max((BEGIN_PHASE_POINTS[2][1] + f) - f2, APPEAR_PHASE_POINTS[2][1]));
        this.mWavePath.cubicTo(((float) this.mWidth) * Math.max(BEGIN_PHASE_POINTS[3][0] - f2, APPEAR_PHASE_POINTS[3][0]), ((float) this.mWidth) * Math.min(BEGIN_PHASE_POINTS[3][1] + f + f2, APPEAR_PHASE_POINTS[3][1]), ((float) this.mWidth) * Math.max(BEGIN_PHASE_POINTS[4][0] - f2, APPEAR_PHASE_POINTS[4][0]), ((float) this.mWidth) * Math.min(BEGIN_PHASE_POINTS[4][1] + f + f2, APPEAR_PHASE_POINTS[4][1]), ((float) this.mWidth) * APPEAR_PHASE_POINTS[5][0], ((float) this.mWidth) * Math.min(BEGIN_PHASE_POINTS[0][1] + f + f2, APPEAR_PHASE_POINTS[5][1]));
        this.mWavePath.cubicTo(((float) this.mWidth) - (((float) this.mWidth) * Math.max(BEGIN_PHASE_POINTS[4][0] - f2, APPEAR_PHASE_POINTS[4][0])), ((float) this.mWidth) * Math.min(BEGIN_PHASE_POINTS[4][1] + f + f2, APPEAR_PHASE_POINTS[4][1]), ((float) this.mWidth) - (((float) this.mWidth) * Math.max(BEGIN_PHASE_POINTS[3][0] - f2, APPEAR_PHASE_POINTS[3][0])), ((float) this.mWidth) * Math.min(BEGIN_PHASE_POINTS[3][1] + f + f2, APPEAR_PHASE_POINTS[3][1]), ((float) this.mWidth) - (((float) this.mWidth) * Math.max(BEGIN_PHASE_POINTS[2][0] - f2, APPEAR_PHASE_POINTS[2][0])), ((float) this.mWidth) * Math.max((BEGIN_PHASE_POINTS[2][1] + f) - f2, APPEAR_PHASE_POINTS[2][1]));
        this.mWavePath.cubicTo(((float) this.mWidth) - (((float) this.mWidth) * Math.min(BEGIN_PHASE_POINTS[1][0] + f2, APPEAR_PHASE_POINTS[1][0])), ((float) this.mWidth) * Math.max((BEGIN_PHASE_POINTS[1][1] + f) - f2, APPEAR_PHASE_POINTS[1][1]), ((float) this.mWidth) - (((float) this.mWidth) * APPEAR_PHASE_POINTS[0][0]), ((float) this.mWidth) * APPEAR_PHASE_POINTS[0][1], (float) this.mWidth, CropImageView.DEFAULT_ASPECT_RATIO);
        this.mCurrentCircleCenterY = (((float) this.mWidth) * Math.min(BEGIN_PHASE_POINTS[3][1] + f + f2, APPEAR_PHASE_POINTS[3][1])) + this.mDropCircleRadius;
        if (Build.VERSION.SDK_INT >= 16) {
            postInvalidateOnAnimation();
        } else {
            invalidate();
        }
    }

    public void beginPhase(float f) {
        onPreDragWave();
        this.mWavePath.moveTo(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
        Path path = this.mWavePath;
        float f2 = BEGIN_PHASE_POINTS[0][0];
        float f3 = BEGIN_PHASE_POINTS[0][1];
        float f4 = BEGIN_PHASE_POINTS[1][0];
        float f5 = BEGIN_PHASE_POINTS[1][1];
        float f6 = BEGIN_PHASE_POINTS[2][0];
        path.cubicTo(((float) this.mWidth) * f2, f3, f4 * ((float) this.mWidth), (f5 + f) * ((float) this.mWidth), f6 * ((float) this.mWidth), (BEGIN_PHASE_POINTS[2][1] + f) * ((float) this.mWidth));
        this.mWavePath.cubicTo(((float) this.mWidth) * BEGIN_PHASE_POINTS[3][0], ((float) this.mWidth) * (BEGIN_PHASE_POINTS[3][1] + f), ((float) this.mWidth) * BEGIN_PHASE_POINTS[4][0], ((float) this.mWidth) * (BEGIN_PHASE_POINTS[4][1] + f), ((float) this.mWidth) * BEGIN_PHASE_POINTS[5][0], ((float) this.mWidth) * (BEGIN_PHASE_POINTS[5][1] + f));
        this.mWavePath.cubicTo(((float) this.mWidth) - (((float) this.mWidth) * BEGIN_PHASE_POINTS[4][0]), ((float) this.mWidth) * (BEGIN_PHASE_POINTS[4][1] + f), ((float) this.mWidth) - (((float) this.mWidth) * BEGIN_PHASE_POINTS[3][0]), ((float) this.mWidth) * (BEGIN_PHASE_POINTS[3][1] + f), ((float) this.mWidth) - (((float) this.mWidth) * BEGIN_PHASE_POINTS[2][0]), ((float) this.mWidth) * (BEGIN_PHASE_POINTS[2][1] + f));
        this.mWavePath.cubicTo(((float) this.mWidth) - (((float) this.mWidth) * BEGIN_PHASE_POINTS[1][0]), (BEGIN_PHASE_POINTS[1][1] + f) * ((float) this.mWidth), ((float) this.mWidth) - (((float) this.mWidth) * BEGIN_PHASE_POINTS[0][0]), BEGIN_PHASE_POINTS[0][1], (float) this.mWidth, CropImageView.DEFAULT_ASPECT_RATIO);
        if (Build.VERSION.SDK_INT >= 16) {
            postInvalidateOnAnimation();
        } else {
            invalidate();
        }
    }

    public void expandPhase(float f, float f2, float f3) {
        onPreDragWave();
        this.mWavePath.moveTo(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
        this.mWavePath.cubicTo(((float) this.mWidth) * EXPAND_PHASE_POINTS[0][0], ((float) this.mWidth) * EXPAND_PHASE_POINTS[0][1], ((float) this.mWidth) * Math.min(Math.min(BEGIN_PHASE_POINTS[1][0] + f2, APPEAR_PHASE_POINTS[1][0]) + f3, EXPAND_PHASE_POINTS[1][0]), ((float) this.mWidth) * Math.max(Math.max((BEGIN_PHASE_POINTS[1][1] + f) - f2, APPEAR_PHASE_POINTS[1][1]) - f3, EXPAND_PHASE_POINTS[1][1]), ((float) this.mWidth) * Math.max(BEGIN_PHASE_POINTS[2][0] - f2, EXPAND_PHASE_POINTS[2][0]), ((float) this.mWidth) * Math.min(Math.max((BEGIN_PHASE_POINTS[2][1] + f) - f2, APPEAR_PHASE_POINTS[2][1]) + f3, EXPAND_PHASE_POINTS[2][1]));
        this.mWavePath.cubicTo(((float) this.mWidth) * Math.min(Math.max(BEGIN_PHASE_POINTS[3][0] - f2, APPEAR_PHASE_POINTS[3][0]) + f3, EXPAND_PHASE_POINTS[3][0]), ((float) this.mWidth) * Math.min(Math.min(BEGIN_PHASE_POINTS[3][1] + f + f2, APPEAR_PHASE_POINTS[3][1]) + f3, EXPAND_PHASE_POINTS[3][1]), ((float) this.mWidth) * Math.max(BEGIN_PHASE_POINTS[4][0] - f2, EXPAND_PHASE_POINTS[4][0]), ((float) this.mWidth) * Math.min(Math.min(BEGIN_PHASE_POINTS[4][1] + f + f2, APPEAR_PHASE_POINTS[4][1]) + f3, EXPAND_PHASE_POINTS[4][1]), ((float) this.mWidth) * EXPAND_PHASE_POINTS[5][0], ((float) this.mWidth) * Math.min(Math.min(BEGIN_PHASE_POINTS[0][1] + f + f2, APPEAR_PHASE_POINTS[5][1]) + f3, EXPAND_PHASE_POINTS[5][1]));
        this.mWavePath.cubicTo(((float) this.mWidth) - (((float) this.mWidth) * Math.max(BEGIN_PHASE_POINTS[4][0] - f2, EXPAND_PHASE_POINTS[4][0])), ((float) this.mWidth) * Math.min(Math.min(BEGIN_PHASE_POINTS[4][1] + f + f2, APPEAR_PHASE_POINTS[4][1]) + f3, EXPAND_PHASE_POINTS[4][1]), ((float) this.mWidth) - (((float) this.mWidth) * Math.min(Math.max(BEGIN_PHASE_POINTS[3][0] - f2, APPEAR_PHASE_POINTS[3][0]) + f3, EXPAND_PHASE_POINTS[3][0])), ((float) this.mWidth) * Math.min(Math.min(BEGIN_PHASE_POINTS[3][1] + f + f2, APPEAR_PHASE_POINTS[3][1]) + f3, EXPAND_PHASE_POINTS[3][1]), ((float) this.mWidth) - (((float) this.mWidth) * Math.max(BEGIN_PHASE_POINTS[2][0] - f2, EXPAND_PHASE_POINTS[2][0])), ((float) this.mWidth) * Math.min(Math.max((BEGIN_PHASE_POINTS[2][1] + f) - f2, APPEAR_PHASE_POINTS[2][1]) + f3, EXPAND_PHASE_POINTS[2][1]));
        this.mWavePath.cubicTo(((float) this.mWidth) - (((float) this.mWidth) * Math.min(Math.min(BEGIN_PHASE_POINTS[1][0] + f2, APPEAR_PHASE_POINTS[1][0]) + f3, EXPAND_PHASE_POINTS[1][0])), ((float) this.mWidth) * Math.max(Math.max((BEGIN_PHASE_POINTS[1][1] + f) - f2, APPEAR_PHASE_POINTS[1][1]) - f3, EXPAND_PHASE_POINTS[1][1]), ((float) this.mWidth) - (((float) this.mWidth) * EXPAND_PHASE_POINTS[0][0]), ((float) this.mWidth) * EXPAND_PHASE_POINTS[0][1], (float) this.mWidth, CropImageView.DEFAULT_ASPECT_RATIO);
        this.mCurrentCircleCenterY = (((float) this.mWidth) * Math.min(Math.min(BEGIN_PHASE_POINTS[3][1] + f + f2, APPEAR_PHASE_POINTS[3][1]) + f3, EXPAND_PHASE_POINTS[3][1])) + this.mDropCircleRadius;
        if (Build.VERSION.SDK_INT >= 16) {
            postInvalidateOnAnimation();
        } else {
            invalidate();
        }
    }

    public float getCurrentCircleCenterY() {
        return this.mCurrentCircleCenterY;
    }

    public void manualRefresh() {
        if (!this.mIsManualRefreshing) {
            this.mIsManualRefreshing = true;
            this.mDropCircleAnimator = ValueAnimator.ofFloat(new float[]{(float) this.mMaxDropHeight, (float) this.mMaxDropHeight});
            this.mDropCircleAnimator.start();
            this.mDropVertexAnimator = ValueAnimator.ofFloat(new float[]{((float) this.mMaxDropHeight) - this.mDropCircleRadius, ((float) this.mMaxDropHeight) - this.mDropCircleRadius});
            this.mDropVertexAnimator.start();
            this.mCurrentCircleCenterY = (float) this.mMaxDropHeight;
            postInvalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        if (this.mDisappearCircleAnimator != null) {
            this.mDisappearCircleAnimator.end();
            this.mDisappearCircleAnimator.removeAllUpdateListeners();
        }
        if (this.mDropCircleAnimator != null) {
            this.mDropCircleAnimator.end();
            this.mDropCircleAnimator.removeAllUpdateListeners();
        }
        if (this.mDropVertexAnimator != null) {
            this.mDropVertexAnimator.end();
            this.mDropVertexAnimator.removeAllUpdateListeners();
        }
        if (this.mWaveReverseAnimator != null) {
            this.mWaveReverseAnimator.end();
            this.mWaveReverseAnimator.removeAllUpdateListeners();
        }
        if (this.mDropBounceHorizontalAnimator != null) {
            this.mDropBounceHorizontalAnimator.end();
            this.mDropBounceHorizontalAnimator.removeAllUpdateListeners();
        }
        if (this.mDropBounceVerticalAnimator != null) {
            this.mDropBounceVerticalAnimator.end();
            this.mDropBounceVerticalAnimator.removeAllUpdateListeners();
        }
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(this.mWavePath, this.mPaint);
        if (!isInEditMode()) {
            this.mWavePath.rewind();
            this.mDropTangentPath.rewind();
            this.mDropCirclePath.rewind();
        }
        float floatValue = ((Float) this.mDropCircleAnimator.getAnimatedValue()).floatValue();
        float f = ((float) this.mWidth) / 2.0f;
        float floatValue2 = ((Float) this.mDisappearCircleAnimator.getAnimatedValue()).floatValue();
        float floatValue3 = ((Float) this.mDropBounceVerticalAnimator.getAnimatedValue()).floatValue();
        float floatValue4 = ((Float) this.mDropBounceHorizontalAnimator.getAnimatedValue()).floatValue();
        float f2 = floatValue3 + 1.0f;
        float f3 = 1.0f + floatValue4;
        this.mDropRect.set((f - ((this.mDropCircleRadius * f2) * floatValue2)) + ((this.mDropCircleRadius * floatValue4) / 2.0f), (((this.mDropCircleRadius * f3) * floatValue2) + floatValue) - ((this.mDropCircleRadius * floatValue3) / 2.0f), (((f2 * this.mDropCircleRadius) * floatValue2) + f) - ((floatValue4 * this.mDropCircleRadius) / 2.0f), (floatValue - (floatValue2 * (this.mDropCircleRadius * f3))) + ((floatValue3 * this.mDropCircleRadius) / 2.0f));
        float floatValue5 = ((Float) this.mDropVertexAnimator.getAnimatedValue()).floatValue();
        this.mDropTangentPath.moveTo(f, floatValue5);
        double pow = Math.pow((double) this.mDropCircleRadius, 2.0d);
        double d = (double) (floatValue * floatValue5);
        Double.isNaN(d);
        double d2 = (double) floatValue;
        double pow2 = Math.pow(d2, 2.0d);
        double d3 = (double) (floatValue5 - floatValue);
        Double.isNaN(d3);
        double d4 = ((pow + d) - pow2) / d3;
        double d5 = (double) this.mWidth;
        Double.isNaN(d5);
        double d6 = (d5 * -2.0d) / 2.0d;
        Double.isNaN(d2);
        double pow3 = Math.pow(d4 - d2, 2.0d);
        double d7 = -d6;
        double pow4 = (d6 * d6) - (((Math.pow((double) f, 2.0d) + pow3) - Math.pow((double) this.mDropCircleRadius, 2.0d)) * 4.0d);
        float f4 = (float) d4;
        this.mDropTangentPath.lineTo((float) ((Math.sqrt(pow4) + d7) / 2.0d), f4);
        this.mDropTangentPath.lineTo((float) ((d7 - Math.sqrt(pow4)) / 2.0d), f4);
        this.mDropTangentPath.close();
        this.mShadowPath.set(this.mDropTangentPath);
        this.mShadowPath.addOval(this.mDropRect, Path.Direction.CCW);
        this.mDropCirclePath.addOval(this.mDropRect, Path.Direction.CCW);
        canvas.drawPath(this.mDropTangentPath, this.mPaint);
        canvas.drawPath(this.mDropCirclePath, this.mPaint);
    }

    /* access modifiers changed from: protected */
    public void onPreDragWave() {
        if (this.mWaveReverseAnimator != null && this.mWaveReverseAnimator.isRunning()) {
            this.mWaveReverseAnimator.cancel();
        }
    }

    public boolean onPreDraw() {
        getViewTreeObserver().removeOnPreDrawListener(this);
        if (!this.mDropHeightUpdated) {
            return false;
        }
        updateMaxDropHeight(this.mUpdateMaxDropHeight);
        return false;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        this.mWidth = i;
        this.mDropCircleRadius = ((float) i) / 14.4f;
        updateMaxDropHeight((int) Math.min((float) Math.min(i, i2), ((float) getHeight()) - this.mDropCircleRadius));
        super.onSizeChanged(i, i2, i3, i4);
    }

    /* access modifiers changed from: protected */
    public void resetAnimator() {
        this.mDropVertexAnimator = ValueAnimator.ofFloat(new float[]{CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO});
        this.mDropBounceVerticalAnimator = ValueAnimator.ofFloat(new float[]{CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO});
        this.mDropBounceHorizontalAnimator = ValueAnimator.ofFloat(new float[]{CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO});
        this.mDropCircleAnimator = ValueAnimator.ofFloat(new float[]{-1000.0f, -1000.0f});
        this.mDropCircleAnimator.start();
        this.mDisappearCircleAnimator = ValueAnimator.ofFloat(new float[]{1.0f, 1.0f});
        this.mDisappearCircleAnimator.setDuration(1);
        this.mDisappearCircleAnimator.start();
    }

    public void setShadow(int i, int i2) {
        this.mPaint.setShadowLayer((float) i, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, i2);
    }

    public void setWaveColor(@ColorInt int i) {
        this.mPaint.setColor(i);
        invalidate();
    }

    public void startDisappearCircleAnimation() {
        this.mDisappearCircleAnimator = ValueAnimator.ofFloat(new float[]{1.0f, CropImageView.DEFAULT_ASPECT_RATIO});
        this.mDisappearCircleAnimator.addUpdateListener(this.mAnimatorUpdateListener);
        this.mDisappearCircleAnimator.setDuration(200);
        this.mDisappearCircleAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                WaveView.this.resetAnimator();
                WaveView.this.mIsManualRefreshing = false;
            }
        });
        this.mDisappearCircleAnimator.start();
    }

    public void startDropAnimation() {
        this.mDisappearCircleAnimator = ValueAnimator.ofFloat(new float[]{1.0f, 1.0f});
        this.mDisappearCircleAnimator.setDuration(1);
        this.mDisappearCircleAnimator.start();
        this.mDropCircleAnimator = ValueAnimator.ofFloat(new float[]{(((float) this.mWidth) / 1440.0f) * 500.0f, (float) this.mMaxDropHeight});
        this.mDropCircleAnimator.setDuration(500);
        this.mDropCircleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                WaveView.this.mCurrentCircleCenterY = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                WaveView waveView = WaveView.this;
                if (Build.VERSION.SDK_INT >= 16) {
                    waveView.postInvalidateOnAnimation();
                } else {
                    waveView.invalidate();
                }
            }
        });
        this.mDropCircleAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        this.mDropCircleAnimator.start();
        this.mDropVertexAnimator = ValueAnimator.ofFloat(new float[]{0.0f, ((float) this.mMaxDropHeight) - this.mDropCircleRadius});
        this.mDropVertexAnimator.setDuration(500);
        this.mDropVertexAnimator.addUpdateListener(this.mAnimatorUpdateListener);
        this.mDropVertexAnimator.start();
        this.mDropBounceVerticalAnimator = ValueAnimator.ofFloat(new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 1.0f});
        this.mDropBounceVerticalAnimator.setDuration(500);
        this.mDropBounceVerticalAnimator.addUpdateListener(this.mAnimatorUpdateListener);
        this.mDropBounceVerticalAnimator.setInterpolator(new DropBounceInterpolator());
        this.mDropBounceVerticalAnimator.setStartDelay(500);
        this.mDropBounceVerticalAnimator.start();
        this.mDropBounceHorizontalAnimator = ValueAnimator.ofFloat(new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 1.0f});
        this.mDropBounceHorizontalAnimator.setDuration(500);
        this.mDropBounceHorizontalAnimator.addUpdateListener(this.mAnimatorUpdateListener);
        this.mDropBounceHorizontalAnimator.setInterpolator(new DropBounceInterpolator());
        this.mDropBounceHorizontalAnimator.setStartDelay(625);
        this.mDropBounceHorizontalAnimator.start();
    }

    public void startWaveAnimation(float f) {
        this.mWaveReverseAnimator = ValueAnimator.ofFloat(new float[]{Math.min(f, MAX_WAVE_HEIGHT) * ((float) this.mWidth), 0.0f});
        this.mWaveReverseAnimator.setDuration(1000);
        this.mWaveReverseAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                WaveView.this.mWavePath.moveTo(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
                float f = floatValue * 0.5f;
                WaveView.this.mWavePath.quadTo(((float) WaveView.this.mWidth) * 0.25f, CropImageView.DEFAULT_ASPECT_RATIO, ((float) WaveView.this.mWidth) * 0.333f, f);
                WaveView.this.mWavePath.quadTo(((float) WaveView.this.mWidth) * 0.5f, floatValue * 1.4f, ((float) WaveView.this.mWidth) * 0.666f, f);
                WaveView.this.mWavePath.quadTo(((float) WaveView.this.mWidth) * 0.75f, CropImageView.DEFAULT_ASPECT_RATIO, (float) WaveView.this.mWidth, CropImageView.DEFAULT_ASPECT_RATIO);
                WaveView.this.postInvalidate();
            }
        });
        this.mWaveReverseAnimator.setInterpolator(new BounceInterpolator());
        this.mWaveReverseAnimator.start();
    }

    /* access modifiers changed from: protected */
    public void updateMaxDropHeight(int i) {
        float f = (float) i;
        if ((((float) this.mWidth) / 1440.0f) * 500.0f <= f) {
            this.mMaxDropHeight = (int) Math.min(f, ((float) getHeight()) - this.mDropCircleRadius);
            if (this.mIsManualRefreshing) {
                this.mIsManualRefreshing = false;
                manualRefresh();
            }
        }
    }
}
