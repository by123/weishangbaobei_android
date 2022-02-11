package com.scwang.smartrefresh.layout.header;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import com.luck.picture.lib.widget.longimage.SubsamplingScaleImageView;
import com.scwang.smartrefresh.layout.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.scwang.smartrefresh.layout.util.SmartUtil;
import com.umeng.socialize.common.SocializeConstants;
import com.yalantis.ucrop.view.CropImageView;

public class BezierRadarHeader extends InternalAbstract implements RefreshHeader {
    protected static final byte PROPERTY_DOT_ALPHA = 2;
    protected static final byte PROPERTY_RADAR_ANGLE = 4;
    protected static final byte PROPERTY_RADAR_SCALE = 0;
    protected static final byte PROPERTY_RIPPLE_RADIUS = 3;
    protected static final byte PROPERTY_WAVE_HEIGHT = 1;
    protected int mAccentColor;
    protected Animator mAnimatorSet;
    protected float mDotAlpha;
    protected float mDotFraction;
    protected float mDotRadius;
    protected boolean mEnableHorizontalDrag;
    protected boolean mManualAccentColor;
    protected boolean mManualPrimaryColor;
    protected Paint mPaint;
    protected Path mPath;
    protected int mPrimaryColor;
    protected int mRadarAngle;
    protected float mRadarCircle;
    protected float mRadarRadius;
    protected RectF mRadarRect;
    protected float mRadarScale;
    protected float mRippleRadius;
    protected int mWaveHeight;
    protected int mWaveOffsetX;
    protected boolean mWavePulling;
    protected int mWaveTop;

    protected class AnimatorUpdater implements ValueAnimator.AnimatorUpdateListener {
        byte propertyName;

        AnimatorUpdater(byte b) {
            this.propertyName = b;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (this.propertyName == 0) {
                BezierRadarHeader.this.mRadarScale = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            } else if (1 == this.propertyName) {
                if (BezierRadarHeader.this.mWavePulling) {
                    valueAnimator.cancel();
                    return;
                } else {
                    BezierRadarHeader.this.mWaveHeight = ((Integer) valueAnimator.getAnimatedValue()).intValue() / 2;
                }
            } else if (2 == this.propertyName) {
                BezierRadarHeader.this.mDotAlpha = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            } else if (3 == this.propertyName) {
                BezierRadarHeader.this.mRippleRadius = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            } else if (4 == this.propertyName) {
                BezierRadarHeader.this.mRadarAngle = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            }
            BezierRadarHeader.this.invalidate();
        }
    }

    public BezierRadarHeader(Context context) {
        this(context, (AttributeSet) null);
    }

    public BezierRadarHeader(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BezierRadarHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mEnableHorizontalDrag = false;
        this.mWaveOffsetX = -1;
        this.mRadarAngle = 0;
        this.mRadarRadius = CropImageView.DEFAULT_ASPECT_RATIO;
        this.mRadarCircle = CropImageView.DEFAULT_ASPECT_RATIO;
        this.mRadarScale = CropImageView.DEFAULT_ASPECT_RATIO;
        this.mRadarRect = new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
        this.mSpinnerStyle = SpinnerStyle.Scale;
        DensityUtil densityUtil = new DensityUtil();
        this.mPath = new Path();
        this.mPaint = new Paint();
        this.mPaint.setAntiAlias(true);
        this.mDotRadius = (float) densityUtil.dip2px(7.0f);
        this.mRadarRadius = (float) densityUtil.dip2px(20.0f);
        this.mRadarCircle = (float) densityUtil.dip2px(7.0f);
        this.mPaint.setStrokeWidth((float) densityUtil.dip2px(3.0f));
        setMinimumHeight(densityUtil.dip2px(100.0f));
        if (isInEditMode()) {
            this.mWaveTop = SocializeConstants.CANCLE_RESULTCODE;
            this.mRadarScale = 1.0f;
            this.mRadarAngle = SubsamplingScaleImageView.ORIENTATION_270;
        } else {
            this.mRadarScale = CropImageView.DEFAULT_ASPECT_RATIO;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BezierRadarHeader);
        this.mEnableHorizontalDrag = obtainStyledAttributes.getBoolean(R.styleable.BezierRadarHeader_srlEnableHorizontalDrag, this.mEnableHorizontalDrag);
        setAccentColor(obtainStyledAttributes.getColor(R.styleable.BezierRadarHeader_srlAccentColor, -1));
        setPrimaryColor(obtainStyledAttributes.getColor(R.styleable.BezierRadarHeader_srlPrimaryColor, -14540254));
        this.mManualAccentColor = obtainStyledAttributes.hasValue(R.styleable.BezierRadarHeader_srlAccentColor);
        this.mManualPrimaryColor = obtainStyledAttributes.hasValue(R.styleable.BezierRadarHeader_srlPrimaryColor);
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        drawWave(canvas, width);
        drawDot(canvas, width, height);
        drawRadar(canvas, width, height);
        drawRipple(canvas, width, height);
        super.dispatchDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void drawDot(Canvas canvas, int i, int i2) {
        float f = this.mDotAlpha;
        float f2 = CropImageView.DEFAULT_ASPECT_RATIO;
        if (f > CropImageView.DEFAULT_ASPECT_RATIO) {
            this.mPaint.setColor(this.mAccentColor);
            float px2dp = DensityUtil.px2dp(i2);
            float f3 = (float) (i / 7);
            float f4 = this.mDotFraction;
            float f5 = this.mDotFraction > 1.0f ? ((this.mDotFraction - 1.0f) * f3) / this.mDotFraction : CropImageView.DEFAULT_ASPECT_RATIO;
            float f6 = (float) i2;
            if (this.mDotFraction > 1.0f) {
                f2 = (((this.mDotFraction - 1.0f) * f6) / 2.0f) / this.mDotFraction;
            }
            for (int i3 = 0; i3 < 7; i3++) {
                float f7 = (((float) i3) + 1.0f) - 4.0f;
                Paint paint = this.mPaint;
                double abs = (double) ((1.0f - ((Math.abs(f7) / 7.0f) * 2.0f)) * 255.0f * this.mDotAlpha);
                double d = (double) px2dp;
                Double.isNaN(d);
                Double.isNaN(abs);
                paint.setAlpha((int) (abs * (1.0d - (1.0d / Math.pow((d / 800.0d) + 1.0d, 15.0d)))));
                float f8 = this.mDotRadius * (1.0f - (1.0f / ((px2dp / 10.0f) + 1.0f)));
                Canvas canvas2 = canvas;
                canvas2.drawCircle((f7 * ((f4 * f3) - f5)) + (((float) (i / 2)) - (f8 / 2.0f)), (f6 - f2) / 2.0f, f8, this.mPaint);
            }
            this.mPaint.setAlpha(255);
        }
    }

    /* access modifiers changed from: protected */
    public void drawRadar(Canvas canvas, int i, int i2) {
        if (this.mAnimatorSet != null || isInEditMode()) {
            float f = this.mRadarRadius * this.mRadarScale;
            float f2 = this.mRadarCircle;
            float f3 = this.mRadarScale;
            this.mPaint.setColor(this.mAccentColor);
            this.mPaint.setStyle(Paint.Style.FILL);
            float f4 = (float) (i / 2);
            float f5 = (float) (i2 / 2);
            canvas.drawCircle(f4, f5, f, this.mPaint);
            this.mPaint.setStyle(Paint.Style.STROKE);
            float f6 = (f2 * f3) + f;
            canvas.drawCircle(f4, f5, f6, this.mPaint);
            this.mPaint.setColor((this.mPrimaryColor & 16777215) | 1426063360);
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mRadarRect.set(f4 - f, f5 - f, f4 + f, f + f5);
            canvas.drawArc(this.mRadarRect, 270.0f, (float) this.mRadarAngle, true, this.mPaint);
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mRadarRect.set(f4 - f6, f5 - f6, f4 + f6, f5 + f6);
            canvas.drawArc(this.mRadarRect, 270.0f, (float) this.mRadarAngle, false, this.mPaint);
            this.mPaint.setStyle(Paint.Style.FILL);
        }
    }

    /* access modifiers changed from: protected */
    public void drawRipple(Canvas canvas, int i, int i2) {
        if (this.mRippleRadius > CropImageView.DEFAULT_ASPECT_RATIO) {
            this.mPaint.setColor(this.mAccentColor);
            canvas.drawCircle((float) (i / 2), (float) (i2 / 2), this.mRippleRadius, this.mPaint);
        }
    }

    /* access modifiers changed from: protected */
    public void drawWave(Canvas canvas, int i) {
        this.mPath.reset();
        this.mPath.lineTo(CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mWaveTop);
        float f = (float) i;
        this.mPath.quadTo((float) (this.mWaveOffsetX >= 0 ? this.mWaveOffsetX : i / 2), (float) (this.mWaveTop + this.mWaveHeight), f, (float) this.mWaveTop);
        this.mPath.lineTo(f, CropImageView.DEFAULT_ASPECT_RATIO);
        this.mPaint.setColor(this.mPrimaryColor);
        canvas.drawPath(this.mPath, this.mPaint);
    }

    public boolean isSupportHorizontalDrag() {
        return this.mEnableHorizontalDrag;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mAnimatorSet != null) {
            this.mAnimatorSet.removeAllListeners();
            this.mAnimatorSet.end();
            this.mAnimatorSet = null;
        }
    }

    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean z) {
        if (this.mAnimatorSet != null) {
            this.mAnimatorSet.removeAllListeners();
            this.mAnimatorSet.end();
            this.mAnimatorSet = null;
        }
        int width = getWidth();
        int height = getHeight();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, (float) Math.sqrt((double) ((width * width) + (height * height)))});
        ofFloat.setDuration(400);
        ofFloat.addUpdateListener(new AnimatorUpdater((byte) 3));
        ofFloat.start();
        return 400;
    }

    public void onHorizontalDrag(float f, int i, int i2) {
        this.mWaveOffsetX = i;
        if (Build.VERSION.SDK_INT >= 16) {
            postInvalidateOnAnimation();
        } else {
            invalidate();
        }
    }

    public void onMoving(boolean z, float f, int i, int i2, int i3) {
        if (z || this.mWavePulling) {
            this.mWavePulling = true;
            this.mWaveTop = Math.min(i2, i);
            this.mWaveHeight = (int) (((float) Math.max(0, i - i2)) * 1.9f);
            this.mDotFraction = f;
        }
    }

    public void onReleased(@NonNull RefreshLayout refreshLayout, int i, int i2) {
        this.mWaveTop = i;
        this.mWavePulling = false;
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{0, 360});
        ofInt.setDuration(720);
        ofInt.setRepeatCount(-1);
        ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
        ofInt.addUpdateListener(new AnimatorUpdater((byte) 4));
        DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{1.0f, CropImageView.DEFAULT_ASPECT_RATIO});
        ofFloat.setInterpolator(decelerateInterpolator);
        ofFloat.addUpdateListener(new AnimatorUpdater((byte) 2));
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 1.0f});
        ofFloat.setInterpolator(decelerateInterpolator);
        ofFloat2.addUpdateListener(new AnimatorUpdater((byte) 0));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(new Animator[]{ofFloat, ofFloat2, ofInt});
        animatorSet.start();
        ValueAnimator ofInt2 = ValueAnimator.ofInt(new int[]{this.mWaveHeight, 0, -((int) (((float) this.mWaveHeight) * 0.8f)), 0, -((int) (((float) this.mWaveHeight) * 0.4f)), 0});
        ofInt2.addUpdateListener(new AnimatorUpdater((byte) 1));
        ofInt2.setInterpolator(decelerateInterpolator);
        ofInt2.setDuration(800);
        ofInt2.start();
        this.mAnimatorSet = animatorSet;
    }

    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState refreshState, @NonNull RefreshState refreshState2) {
        switch (refreshState2) {
            case None:
            case PullDownToRefresh:
                this.mDotAlpha = 1.0f;
                this.mRadarScale = CropImageView.DEFAULT_ASPECT_RATIO;
                this.mRippleRadius = CropImageView.DEFAULT_ASPECT_RATIO;
                return;
            default:
                return;
        }
    }

    public BezierRadarHeader setAccentColor(@ColorInt int i) {
        this.mAccentColor = i;
        this.mManualAccentColor = true;
        return this;
    }

    public BezierRadarHeader setAccentColorId(@ColorRes int i) {
        setAccentColor(SmartUtil.getColor(getContext(), i));
        return this;
    }

    public BezierRadarHeader setEnableHorizontalDrag(boolean z) {
        this.mEnableHorizontalDrag = z;
        if (!z) {
            this.mWaveOffsetX = -1;
        }
        return this;
    }

    public BezierRadarHeader setPrimaryColor(@ColorInt int i) {
        this.mPrimaryColor = i;
        this.mManualPrimaryColor = true;
        return this;
    }

    public BezierRadarHeader setPrimaryColorId(@ColorRes int i) {
        setPrimaryColor(SmartUtil.getColor(getContext(), i));
        return this;
    }

    @Deprecated
    public void setPrimaryColors(@ColorInt int... iArr) {
        if (iArr.length > 0 && !this.mManualPrimaryColor) {
            setPrimaryColor(iArr[0]);
            this.mManualPrimaryColor = false;
        }
        if (iArr.length > 1 && !this.mManualAccentColor) {
            setAccentColor(iArr[1]);
            this.mManualAccentColor = false;
        }
    }
}
