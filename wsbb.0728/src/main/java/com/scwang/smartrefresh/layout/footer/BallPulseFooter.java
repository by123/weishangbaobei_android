package com.scwang.smartrefresh.layout.footer;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
import android.util.AttributeSet;
import com.scwang.smartrefresh.layout.R;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BallPulseFooter extends InternalAbstract implements RefreshFooter {
    public static final int DEFAULT_SIZE = 50;
    protected int mAnimatingColor;
    protected ArrayList<ValueAnimator> mAnimators;
    protected float mCircleSpacing;
    protected boolean mIsStarted;
    protected boolean mManualAnimationColor;
    protected boolean mManualNormalColor;
    protected int mNormalColor;
    protected Paint mPaint;
    protected float[] mScaleFloats;
    protected Map<ValueAnimator, ValueAnimator.AnimatorUpdateListener> mUpdateListeners;

    public BallPulseFooter(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    public BallPulseFooter(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BallPulseFooter(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        super(context, attributeSet, i);
        this.mNormalColor = -1118482;
        this.mAnimatingColor = -1615546;
        this.mScaleFloats = new float[]{1.0f, 1.0f, 1.0f};
        this.mIsStarted = false;
        this.mUpdateListeners = new HashMap();
        setMinimumHeight(DensityUtil.dp2px(60.0f));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BallPulseFooter);
        this.mPaint = new Paint();
        this.mPaint.setColor(-1);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setAntiAlias(true);
        this.mSpinnerStyle = SpinnerStyle.Translate;
        this.mSpinnerStyle = SpinnerStyle.values()[obtainStyledAttributes.getInt(R.styleable.BallPulseFooter_srlClassicsSpinnerStyle, this.mSpinnerStyle.ordinal())];
        if (obtainStyledAttributes.hasValue(R.styleable.BallPulseFooter_srlNormalColor)) {
            setNormalColor(obtainStyledAttributes.getColor(R.styleable.BallPulseFooter_srlNormalColor, 0));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.BallPulseFooter_srlAnimatingColor)) {
            setAnimatingColor(obtainStyledAttributes.getColor(R.styleable.BallPulseFooter_srlAnimatingColor, 0));
        }
        obtainStyledAttributes.recycle();
        this.mCircleSpacing = (float) DensityUtil.dp2px(4.0f);
        this.mAnimators = new ArrayList<>();
        for (final int i2 = 0; i2 < 3; i2++) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{1.0f, 0.3f, 1.0f});
            ofFloat.setDuration(750);
            ofFloat.setRepeatCount(-1);
            ofFloat.setTarget(Integer.valueOf(i2));
            ofFloat.setStartDelay((long) new int[]{120, 240, 360}[i2]);
            this.mUpdateListeners.put(ofFloat, new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    BallPulseFooter.this.mScaleFloats[i2] = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    this.postInvalidate();
                }
            });
            this.mAnimators.add(ofFloat);
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        float min = (((float) Math.min(width, height)) - (this.mCircleSpacing * 2.0f)) / 6.0f;
        float f = (float) (width / 2);
        float f2 = 2.0f * min;
        float f3 = this.mCircleSpacing;
        float f4 = (float) (height / 2);
        for (int i = 0; i < 3; i++) {
            canvas.save();
            float f5 = (float) i;
            canvas.translate((f5 * this.mCircleSpacing) + (f2 * f5) + (f - (f3 + f2)), f4);
            canvas.scale(this.mScaleFloats[i], this.mScaleFloats[i]);
            canvas.drawCircle(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, min, this.mPaint);
            canvas.restore();
        }
        super.dispatchDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mAnimators != null) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < this.mAnimators.size()) {
                    this.mAnimators.get(i2).cancel();
                    this.mAnimators.get(i2).removeAllListeners();
                    this.mAnimators.get(i2).removeAllUpdateListeners();
                    i = i2 + 1;
                } else {
                    return;
                }
            }
        }
    }

    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean z) {
        if (this.mAnimators != null && this.mIsStarted) {
            this.mIsStarted = false;
            this.mScaleFloats = new float[]{1.0f, 1.0f, 1.0f};
            Iterator<ValueAnimator> it = this.mAnimators.iterator();
            while (it.hasNext()) {
                ValueAnimator next = it.next();
                if (next != null) {
                    next.removeAllUpdateListeners();
                    next.end();
                }
            }
        }
        this.mPaint.setColor(this.mNormalColor);
        return 0;
    }

    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int i, int i2) {
        if (!this.mIsStarted) {
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 < this.mAnimators.size()) {
                    ValueAnimator valueAnimator = this.mAnimators.get(i4);
                    ValueAnimator.AnimatorUpdateListener animatorUpdateListener = this.mUpdateListeners.get(valueAnimator);
                    if (animatorUpdateListener != null) {
                        valueAnimator.addUpdateListener(animatorUpdateListener);
                    }
                    valueAnimator.start();
                    i3 = i4 + 1;
                } else {
                    this.mIsStarted = true;
                    this.mPaint.setColor(this.mAnimatingColor);
                    return;
                }
            }
        }
    }

    public BallPulseFooter setAnimatingColor(@ColorInt int i) {
        this.mAnimatingColor = i;
        this.mManualAnimationColor = true;
        if (this.mIsStarted) {
            this.mPaint.setColor(i);
        }
        return this;
    }

    public boolean setNoMoreData(boolean z) {
        return false;
    }

    public BallPulseFooter setNormalColor(@ColorInt int i) {
        this.mNormalColor = i;
        this.mManualNormalColor = true;
        if (!this.mIsStarted) {
            this.mPaint.setColor(i);
        }
        return this;
    }

    @Deprecated
    public void setPrimaryColors(@ColorInt int... iArr) {
        if (!this.mManualAnimationColor && iArr.length > 1) {
            setAnimatingColor(iArr[0]);
            this.mManualAnimationColor = false;
        }
        if (!this.mManualNormalColor) {
            if (iArr.length > 1) {
                setNormalColor(iArr[1]);
            } else if (iArr.length > 0) {
                setNormalColor(ColorUtils.compositeColors(-1711276033, iArr[0]));
            }
            this.mManualNormalColor = false;
        }
    }

    public BallPulseFooter setSpinnerStyle(SpinnerStyle spinnerStyle) {
        this.mSpinnerStyle = spinnerStyle;
        return this;
    }
}
