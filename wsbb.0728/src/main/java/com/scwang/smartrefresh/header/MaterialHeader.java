package com.scwang.smartrefresh.header;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import com.scwang.smartrefresh.header.internal.MaterialProgressDrawable;
import com.scwang.smartrefresh.header.material.CircleImageView;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.scwang.smartrefresh.layout.util.SmartUtil;
import com.yalantis.ucrop.view.CropImageView;

public class MaterialHeader extends InternalAbstract implements RefreshHeader {
    protected static final int CIRCLE_BG_LIGHT = -328966;
    @VisibleForTesting
    protected static final int CIRCLE_DIAMETER = 40;
    @VisibleForTesting
    protected static final int CIRCLE_DIAMETER_LARGE = 56;
    protected static final float MAX_PROGRESS_ANGLE = 0.8f;
    public static final int SIZE_DEFAULT = 1;
    public static final int SIZE_LARGE = 0;
    protected Paint mBezierPaint;
    protected Path mBezierPath;
    protected int mCircleDiameter;
    protected ImageView mCircleView;
    protected boolean mFinished;
    protected int mHeadHeight;
    protected MaterialProgressDrawable mProgress;
    protected boolean mShowBezierWave;
    protected RefreshState mState;
    protected int mWaveHeight;

    public MaterialHeader(Context context) {
        this(context, (AttributeSet) null);
    }

    public MaterialHeader(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaterialHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mShowBezierWave = false;
        this.mSpinnerStyle = SpinnerStyle.MatchLayout;
        setMinimumHeight(DensityUtil.dp2px(100.0f));
        this.mProgress = new MaterialProgressDrawable(this);
        this.mProgress.setBackgroundColor(CIRCLE_BG_LIGHT);
        this.mProgress.setAlpha(255);
        this.mProgress.setColorSchemeColors(-16737844, -48060, -10053376, -5609780, -30720);
        this.mCircleView = new CircleImageView(context, CIRCLE_BG_LIGHT);
        this.mCircleView.setImageDrawable(this.mProgress);
        this.mCircleView.setAlpha(CropImageView.DEFAULT_ASPECT_RATIO);
        addView(this.mCircleView);
        this.mCircleDiameter = (int) (getResources().getDisplayMetrics().density * 40.0f);
        this.mBezierPath = new Path();
        this.mBezierPaint = new Paint();
        this.mBezierPaint.setAntiAlias(true);
        this.mBezierPaint.setStyle(Paint.Style.FILL);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MaterialHeader);
        this.mShowBezierWave = obtainStyledAttributes.getBoolean(R.styleable.MaterialHeader_mhShowBezierWave, this.mShowBezierWave);
        this.mBezierPaint.setColor(obtainStyledAttributes.getColor(R.styleable.MaterialHeader_mhPrimaryColor, -15614977));
        if (obtainStyledAttributes.hasValue(R.styleable.MaterialHeader_mhShadowRadius)) {
            int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.MaterialHeader_mhShadowRadius, 0);
            this.mBezierPaint.setShadowLayer((float) dimensionPixelOffset, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, obtainStyledAttributes.getColor(R.styleable.MaterialHeader_mhShadowColor, -16777216));
            setLayerType(1, (Paint) null);
        }
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        if (this.mShowBezierWave) {
            this.mBezierPath.reset();
            this.mBezierPath.lineTo(CropImageView.DEFAULT_ASPECT_RATIO, (float) this.mHeadHeight);
            this.mBezierPath.quadTo((float) (getMeasuredWidth() / 2), ((float) this.mHeadHeight) + (((float) this.mWaveHeight) * 1.9f), (float) getMeasuredWidth(), (float) this.mHeadHeight);
            this.mBezierPath.lineTo((float) getMeasuredWidth(), CropImageView.DEFAULT_ASPECT_RATIO);
            canvas.drawPath(this.mBezierPath, this.mBezierPaint);
        }
        super.dispatchDraw(canvas);
    }

    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean z) {
        ImageView imageView = this.mCircleView;
        this.mProgress.stop();
        imageView.animate().scaleX(CropImageView.DEFAULT_ASPECT_RATIO).scaleY(CropImageView.DEFAULT_ASPECT_RATIO);
        this.mFinished = true;
        return 0;
    }

    public void onInitialized(@NonNull RefreshKernel refreshKernel, int i, int i2) {
        if (!this.mShowBezierWave) {
            refreshKernel.requestDefaultTranslationContentFor(this, false);
        }
        if (isInEditMode()) {
            int i3 = i / 2;
            this.mHeadHeight = i3;
            this.mWaveHeight = i3;
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (getChildCount() != 0) {
            ImageView imageView = this.mCircleView;
            int measuredWidth = getMeasuredWidth();
            int measuredWidth2 = imageView.getMeasuredWidth();
            int measuredHeight = imageView.getMeasuredHeight();
            if (!isInEditMode() || this.mHeadHeight <= 0) {
                int i5 = measuredWidth / 2;
                int i6 = measuredWidth2 / 2;
                imageView.layout(i5 - i6, -measuredHeight, i5 + i6, 0);
                return;
            }
            int i7 = this.mHeadHeight - (measuredHeight / 2);
            int i8 = measuredWidth / 2;
            int i9 = measuredWidth2 / 2;
            imageView.layout(i8 - i9, i7, i8 + i9, measuredHeight + i7);
            this.mProgress.showArrow(true);
            this.mProgress.setStartEndTrim(CropImageView.DEFAULT_ASPECT_RATIO, MAX_PROGRESS_ANGLE);
            this.mProgress.setArrowScale(1.0f);
            imageView.setAlpha(1.0f);
            imageView.setVisibility(0);
        }
    }

    public void onMeasure(int i, int i2) {
        super.setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
        this.mCircleView.measure(View.MeasureSpec.makeMeasureSpec(this.mCircleDiameter, 1073741824), View.MeasureSpec.makeMeasureSpec(this.mCircleDiameter, 1073741824));
    }

    public void onMoving(boolean z, float f, int i, int i2, int i3) {
        if (this.mShowBezierWave) {
            this.mHeadHeight = Math.min(i, i2);
            this.mWaveHeight = Math.max(0, i - i2);
            postInvalidate();
        }
        if (z || (!this.mProgress.isRunning() && !this.mFinished)) {
            if (this.mState != RefreshState.Refreshing) {
                float f2 = (float) i2;
                double min = (double) Math.min(1.0f, Math.abs((((float) i) * 1.0f) / f2));
                Double.isNaN(min);
                float max = (((float) Math.max(min - 0.4d, 0.0d)) * 5.0f) / 3.0f;
                double max2 = (double) (Math.max(CropImageView.DEFAULT_ASPECT_RATIO, Math.min((float) (Math.abs(i) - i2), 2.0f * f2) / f2) / 4.0f);
                double pow = Math.pow(max2, 2.0d);
                Double.isNaN(max2);
                this.mProgress.showArrow(true);
                this.mProgress.setStartEndTrim(CropImageView.DEFAULT_ASPECT_RATIO, Math.min(MAX_PROGRESS_ANGLE, MAX_PROGRESS_ANGLE * max));
                this.mProgress.setArrowScale(Math.min(1.0f, max));
                this.mProgress.setProgressRotation((((max * 0.4f) - 0.25f) + (((float) (max2 - pow)) * 2.0f * 2.0f)) * 0.5f);
            }
            ImageView imageView = this.mCircleView;
            float f3 = (float) i;
            imageView.setTranslationY(Math.min(f3, (float) ((i / 2) + (this.mCircleDiameter / 2))));
            imageView.setAlpha(Math.min(1.0f, (f3 * 4.0f) / ((float) this.mCircleDiameter)));
        }
    }

    public void onReleased(@NonNull RefreshLayout refreshLayout, int i, int i2) {
        this.mProgress.start();
    }

    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState refreshState, @NonNull RefreshState refreshState2) {
        ImageView imageView = this.mCircleView;
        this.mState = refreshState2;
        switch (refreshState2) {
            case PullDownToRefresh:
                this.mFinished = false;
                imageView.setVisibility(0);
                imageView.setTranslationY(CropImageView.DEFAULT_ASPECT_RATIO);
                imageView.setScaleX(1.0f);
                imageView.setScaleY(1.0f);
                return;
            default:
                return;
        }
    }

    public MaterialHeader setColorSchemeColors(@ColorInt int... iArr) {
        this.mProgress.setColorSchemeColors(iArr);
        return this;
    }

    public MaterialHeader setColorSchemeResources(@ColorRes int... iArr) {
        Context context = getContext();
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[i] = SmartUtil.getColor(context, iArr[i]);
        }
        return setColorSchemeColors(iArr2);
    }

    @Deprecated
    public void setPrimaryColors(@ColorInt int... iArr) {
        if (iArr.length > 0) {
            this.mBezierPaint.setColor(iArr[0]);
        }
    }

    public MaterialHeader setShowBezierWave(boolean z) {
        this.mShowBezierWave = z;
        return this;
    }

    public MaterialHeader setSize(int i) {
        if (i == 0 || i == 1) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            if (i == 0) {
                this.mCircleDiameter = (int) (displayMetrics.density * 56.0f);
            } else {
                this.mCircleDiameter = (int) (displayMetrics.density * 40.0f);
            }
            this.mCircleView.setImageDrawable((Drawable) null);
            this.mProgress.updateSizes(i);
            this.mCircleView.setImageDrawable(this.mProgress);
        }
        return this;
    }
}
