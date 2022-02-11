package com.scwang.smartrefresh.header;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.graphics.ColorUtils;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.yalantis.ucrop.view.CropImageView;
import com.youth.banner.BannerConfig;

public class BezierCircleHeader extends InternalAbstract implements RefreshHeader {
    protected static final int TARGET_DEGREE = 270;
    protected Paint mBackPaint;
    protected float mBollRadius;
    protected float mBollY;
    protected float mFinishRatio;
    protected Paint mFrontPaint;
    protected float mHeadHeight;
    protected boolean mOuterIsStart;
    protected Paint mOuterPaint;
    protected Path mPath;
    protected int mRefreshStart;
    protected int mRefreshStop;
    protected boolean mShowBoll;
    protected boolean mShowBollTail;
    protected boolean mShowOuter;
    protected float mSpringRatio;
    protected float mWaveHeight;
    protected boolean mWavePulling;

    public BezierCircleHeader(Context context) {
        this(context, (AttributeSet) null);
    }

    public BezierCircleHeader(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BezierCircleHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mRefreshStop = 90;
        this.mRefreshStart = 90;
        this.mOuterIsStart = true;
        this.mWavePulling = false;
        this.mSpinnerStyle = SpinnerStyle.Scale;
        setMinimumHeight(DensityUtil.dp2px(100.0f));
        this.mBackPaint = new Paint();
        this.mBackPaint.setColor(-15614977);
        this.mBackPaint.setAntiAlias(true);
        this.mFrontPaint = new Paint();
        this.mFrontPaint.setColor(-1);
        this.mFrontPaint.setAntiAlias(true);
        this.mOuterPaint = new Paint();
        this.mOuterPaint.setAntiAlias(true);
        this.mOuterPaint.setColor(-1);
        this.mOuterPaint.setStyle(Paint.Style.STROKE);
        this.mOuterPaint.setStrokeWidth((float) DensityUtil.dp2px(2.0f));
        this.mPath = new Path();
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        if (isInEditMode()) {
            this.mShowBoll = true;
            this.mShowOuter = true;
            this.mHeadHeight = (float) height;
            this.mRefreshStop = 270;
            this.mBollY = this.mHeadHeight / 2.0f;
            this.mBollRadius = this.mHeadHeight / 6.0f;
        }
        drawWave(canvas, width, height);
        drawSpringUp(canvas, width);
        drawBoll(canvas, width);
        drawOuter(canvas, width);
        drawFinish(canvas, width);
        super.dispatchDraw(canvas);
    }

    private void drawWave(Canvas canvas, int i, int i2) {
        float min = Math.min(this.mHeadHeight, (float) i2);
        if (this.mWaveHeight != CropImageView.DEFAULT_ASPECT_RATIO) {
            this.mPath.reset();
            float f = (float) i;
            this.mPath.lineTo(f, CropImageView.DEFAULT_ASPECT_RATIO);
            this.mPath.lineTo(f, min);
            this.mPath.quadTo((float) (i / 2), (this.mWaveHeight * 2.0f) + min, CropImageView.DEFAULT_ASPECT_RATIO, min);
            this.mPath.close();
            canvas.drawPath(this.mPath, this.mBackPaint);
            return;
        }
        canvas.drawRect(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (float) i, min, this.mBackPaint);
    }

    private void drawSpringUp(Canvas canvas, int i) {
        if (this.mSpringRatio > CropImageView.DEFAULT_ASPECT_RATIO) {
            float f = (float) (i / 2);
            float f2 = (f - (this.mBollRadius * 4.0f)) + (this.mSpringRatio * 3.0f * this.mBollRadius);
            if (((double) this.mSpringRatio) < 0.9d) {
                this.mPath.reset();
                this.mPath.moveTo(f2, this.mBollY);
                this.mPath.quadTo(f, this.mBollY - ((this.mBollRadius * this.mSpringRatio) * 2.0f), ((float) i) - f2, this.mBollY);
                canvas.drawPath(this.mPath, this.mFrontPaint);
                return;
            }
            canvas.drawCircle(f, this.mBollY, this.mBollRadius, this.mFrontPaint);
        }
    }

    private void drawBoll(Canvas canvas, int i) {
        if (this.mShowBoll) {
            canvas.drawCircle((float) (i / 2), this.mBollY, this.mBollRadius, this.mFrontPaint);
            drawBollTail(canvas, i, (this.mHeadHeight + this.mWaveHeight) / this.mHeadHeight);
        }
    }

    private void drawBollTail(Canvas canvas, int i, float f) {
        if (this.mShowBollTail) {
            float f2 = this.mHeadHeight + this.mWaveHeight;
            float f3 = this.mBollY + ((this.mBollRadius * f) / 2.0f);
            float f4 = (float) (i / 2);
            float sqrt = ((float) Math.sqrt((double) (this.mBollRadius * this.mBollRadius * (1.0f - ((f * f) / 4.0f))))) + f4;
            float f5 = f4 + (((this.mBollRadius * 3.0f) / 4.0f) * (1.0f - f));
            float f6 = this.mBollRadius + f5;
            this.mPath.reset();
            this.mPath.moveTo(sqrt, f3);
            this.mPath.quadTo(f5, f2, f6, f2);
            float f7 = (float) i;
            this.mPath.lineTo(f7 - f6, f2);
            this.mPath.quadTo(f7 - f5, f2, f7 - sqrt, f3);
            canvas.drawPath(this.mPath, this.mFrontPaint);
        }
    }

    private void drawOuter(Canvas canvas, int i) {
        if (this.mShowOuter) {
            float strokeWidth = this.mBollRadius + (this.mOuterPaint.getStrokeWidth() * 2.0f);
            int i2 = 3;
            this.mRefreshStart += this.mOuterIsStart ? 3 : 10;
            int i3 = this.mRefreshStop;
            if (this.mOuterIsStart) {
                i2 = 10;
            }
            this.mRefreshStop = i3 + i2;
            this.mRefreshStart %= 360;
            this.mRefreshStop %= 360;
            int i4 = this.mRefreshStop - this.mRefreshStart;
            if (i4 < 0) {
                i4 += 360;
            }
            float f = (float) (i / 2);
            canvas.drawArc(new RectF(f - strokeWidth, this.mBollY - strokeWidth, f + strokeWidth, this.mBollY + strokeWidth), (float) this.mRefreshStart, (float) i4, false, this.mOuterPaint);
            if (i4 >= 270) {
                this.mOuterIsStart = false;
            } else if (i4 <= 10) {
                this.mOuterIsStart = true;
            }
            invalidate();
        }
    }

    private void drawFinish(Canvas canvas, int i) {
        Canvas canvas2 = canvas;
        int i2 = i;
        if (this.mFinishRatio > CropImageView.DEFAULT_ASPECT_RATIO) {
            int color = this.mOuterPaint.getColor();
            if (((double) this.mFinishRatio) < 0.3d) {
                int i3 = i2 / 2;
                canvas2.drawCircle((float) i3, this.mBollY, this.mBollRadius, this.mFrontPaint);
                int strokeWidth = (int) (this.mBollRadius + (this.mOuterPaint.getStrokeWidth() * 2.0f * ((this.mFinishRatio / 0.3f) + 1.0f)));
                this.mOuterPaint.setColor(ColorUtils.setAlphaComponent(color, (int) ((1.0f - (this.mFinishRatio / 0.3f)) * 255.0f)));
                float f = (float) strokeWidth;
                canvas.drawArc(new RectF((float) (i3 - strokeWidth), this.mBollY - f, (float) (i3 + strokeWidth), this.mBollY + f), CropImageView.DEFAULT_ASPECT_RATIO, 360.0f, false, this.mOuterPaint);
            }
            this.mOuterPaint.setColor(color);
            if (((double) this.mFinishRatio) >= 0.3d && ((double) this.mFinishRatio) < 0.7d) {
                float f2 = (this.mFinishRatio - 0.3f) / 0.4f;
                this.mBollY = (float) ((int) ((this.mHeadHeight / 2.0f) + ((this.mHeadHeight - (this.mHeadHeight / 2.0f)) * f2)));
                canvas2.drawCircle((float) (i2 / 2), this.mBollY, this.mBollRadius, this.mFrontPaint);
                if (this.mBollY >= this.mHeadHeight - (this.mBollRadius * 2.0f)) {
                    this.mShowBollTail = true;
                    drawBollTail(canvas2, i2, f2);
                }
                this.mShowBollTail = false;
            }
            if (((double) this.mFinishRatio) >= 0.7d && this.mFinishRatio <= 1.0f) {
                float f3 = (this.mFinishRatio - 0.7f) / 0.3f;
                float f4 = (float) (i2 / 2);
                int i4 = (int) ((f4 - this.mBollRadius) - ((this.mBollRadius * 2.0f) * f3));
                this.mPath.reset();
                this.mPath.moveTo((float) i4, this.mHeadHeight);
                this.mPath.quadTo(f4, this.mHeadHeight - (this.mBollRadius * (1.0f - f3)), (float) (i2 - i4), this.mHeadHeight);
                canvas2.drawPath(this.mPath, this.mFrontPaint);
            }
        }
    }

    public void onMoving(boolean z, float f, int i, int i2, int i3) {
        if (z || this.mWavePulling) {
            this.mWavePulling = true;
            this.mHeadHeight = (float) i2;
            this.mWaveHeight = ((float) Math.max(i - i2, 0)) * 0.8f;
        }
    }

    public void onReleased(@NonNull RefreshLayout refreshLayout, int i, int i2) {
        this.mWavePulling = false;
        this.mHeadHeight = (float) i;
        this.mBollRadius = (float) (i / 6);
        DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator();
        final float min = Math.min(this.mWaveHeight * 0.8f, this.mHeadHeight / 2.0f);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{this.mWaveHeight, 0.0f, -(1.0f * min), 0.0f, -(0.4f * min), 0.0f});
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            float speed = CropImageView.DEFAULT_ASPECT_RATIO;
            float springBollY;
            float springRatio = CropImageView.DEFAULT_ASPECT_RATIO;
            int status = 0;

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                if (this.status == 0 && floatValue <= CropImageView.DEFAULT_ASPECT_RATIO) {
                    this.status = 1;
                    this.speed = Math.abs(floatValue - BezierCircleHeader.this.mWaveHeight);
                }
                if (this.status == 1) {
                    this.springRatio = (-floatValue) / min;
                    if (this.springRatio >= BezierCircleHeader.this.mSpringRatio) {
                        BezierCircleHeader.this.mSpringRatio = this.springRatio;
                        BezierCircleHeader.this.mBollY = BezierCircleHeader.this.mHeadHeight + floatValue;
                        this.speed = Math.abs(floatValue - BezierCircleHeader.this.mWaveHeight);
                    } else {
                        this.status = 2;
                        BezierCircleHeader.this.mSpringRatio = CropImageView.DEFAULT_ASPECT_RATIO;
                        BezierCircleHeader.this.mShowBoll = true;
                        BezierCircleHeader.this.mShowBollTail = true;
                        this.springBollY = BezierCircleHeader.this.mBollY;
                    }
                }
                if (this.status == 2 && BezierCircleHeader.this.mBollY > BezierCircleHeader.this.mHeadHeight / 2.0f) {
                    BezierCircleHeader.this.mBollY = Math.max(BezierCircleHeader.this.mHeadHeight / 2.0f, BezierCircleHeader.this.mBollY - this.speed);
                    float animatedFraction = (valueAnimator.getAnimatedFraction() * ((BezierCircleHeader.this.mHeadHeight / 2.0f) - this.springBollY)) + this.springBollY;
                    if (BezierCircleHeader.this.mBollY > animatedFraction) {
                        BezierCircleHeader.this.mBollY = animatedFraction;
                    }
                }
                if (BezierCircleHeader.this.mShowBollTail && floatValue < BezierCircleHeader.this.mWaveHeight) {
                    BezierCircleHeader.this.mShowOuter = true;
                    BezierCircleHeader.this.mShowBollTail = false;
                    BezierCircleHeader.this.mOuterIsStart = true;
                    BezierCircleHeader.this.mRefreshStart = 90;
                    BezierCircleHeader.this.mRefreshStop = 90;
                }
                if (!BezierCircleHeader.this.mWavePulling) {
                    BezierCircleHeader.this.mWaveHeight = floatValue;
                    BezierCircleHeader.this.invalidate();
                }
            }
        });
        ofFloat.setInterpolator(decelerateInterpolator);
        ofFloat.setDuration(1000);
        ofFloat.start();
    }

    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean z) {
        this.mShowBoll = false;
        this.mShowOuter = false;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 1.0f});
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                BezierCircleHeader bezierCircleHeader = BezierCircleHeader.this;
                BezierCircleHeader.this.mFinishRatio = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                bezierCircleHeader.invalidate();
            }
        });
        ofFloat.setInterpolator(new AccelerateInterpolator());
        ofFloat.setDuration(800);
        ofFloat.start();
        return BannerConfig.DURATION;
    }

    @Deprecated
    public void setPrimaryColors(@ColorInt int... iArr) {
        if (iArr.length > 0) {
            this.mBackPaint.setColor(iArr[0]);
            if (iArr.length > 1) {
                this.mFrontPaint.setColor(iArr[1]);
                this.mOuterPaint.setColor(iArr[1]);
            }
        }
    }
}
