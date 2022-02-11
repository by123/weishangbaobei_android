package com.scwang.smartrefresh.header;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import com.scwang.smartrefresh.header.internal.MaterialProgressDrawable;
import com.scwang.smartrefresh.header.waveswipe.WaveView;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.yalantis.ucrop.view.CropImageView;

public class WaveSwipeHeader extends InternalAbstract implements RefreshHeader {
    protected static final float MAX_PROGRESS_ROTATION_RATE = 0.8f;
    protected ProgressAnimationImageView mCircleView;
    protected float mLastFirstBounds;
    protected MaterialProgressDrawable mProgress;
    protected RefreshState mState;
    protected WaveView mWaveView;

    protected class ProgressAnimationImageView extends ImageView {
        protected Animation.AnimationListener mListener;

        public ProgressAnimationImageView(Context context) {
            super(context);
            WaveSwipeHeader.this.mProgress = new MaterialProgressDrawable(WaveSwipeHeader.this);
            WaveSwipeHeader.this.mProgress.setBackgroundColor(0);
            if (WaveSwipeHeader.isOver600dp()) {
                WaveSwipeHeader.this.mProgress.updateSizes(0);
            }
            super.setImageDrawable(WaveSwipeHeader.this.mProgress);
        }

        public void onAnimationEnd() {
            super.onAnimationEnd();
            if (this.mListener != null) {
                this.mListener.onAnimationEnd(getAnimation());
            }
        }

        public void onAnimationStart() {
            super.onAnimationStart();
            if (this.mListener != null) {
                this.mListener.onAnimationStart(getAnimation());
            }
        }

        public void setAnimationListener(Animation.AnimationListener animationListener) {
            this.mListener = animationListener;
        }
    }

    protected enum VERTICAL_DRAG_THRESHOLD {
        FIRST(0.1f),
        SECOND(FIRST.val + 0.16f),
        THIRD(FIRST.val + 0.5f);
        
        final float val;

        private VERTICAL_DRAG_THRESHOLD(float f) {
            this.val = f;
        }
    }

    public WaveSwipeHeader(Context context) {
        this(context, (AttributeSet) null);
    }

    public WaveSwipeHeader(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WaveSwipeHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mSpinnerStyle = SpinnerStyle.MatchLayout;
        WaveView waveView = new WaveView(context);
        this.mWaveView = waveView;
        addView(waveView);
        ProgressAnimationImageView progressAnimationImageView = new ProgressAnimationImageView(context);
        this.mCircleView = progressAnimationImageView;
        addView(progressAnimationImageView);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.WaveSwipeHeader);
        int color = obtainStyledAttributes.getColor(R.styleable.WaveSwipeHeader_wshPrimaryColor, 0);
        int color2 = obtainStyledAttributes.getColor(R.styleable.WaveSwipeHeader_wshAccentColor, 0);
        if (color != 0) {
            this.mWaveView.setWaveColor(color);
        }
        if (color2 != 0) {
            this.mProgress.setColorSchemeColors(color2);
        } else {
            this.mProgress.setColorSchemeColors(-1);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.WaveSwipeHeader_wshShadowRadius)) {
            this.mWaveView.setShadow(obtainStyledAttributes.getDimensionPixelOffset(R.styleable.WaveSwipeHeader_wshShadowRadius, 0), obtainStyledAttributes.getColor(R.styleable.WaveSwipeHeader_wshShadowColor, -16777216));
        }
        obtainStyledAttributes.recycle();
    }

    public static boolean isOver600dp() {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        return ((float) displayMetrics.widthPixels) / displayMetrics.density >= 600.0f;
    }

    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean z) {
        final ProgressAnimationImageView progressAnimationImageView = this.mCircleView;
        AnonymousClass2 r1 = new Animation() {
            public void applyTransformation(float f, Transformation transformation) {
                float f2 = 1.0f - f;
                progressAnimationImageView.setScaleX(f2);
                progressAnimationImageView.setScaleY(f2);
            }
        };
        r1.setDuration(200);
        this.mCircleView.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                WaveSwipeHeader.this.mProgress.stop();
                WaveSwipeHeader.this.mProgress.setAlpha(255);
                WaveSwipeHeader.this.mWaveView.startDisappearCircleAnimation();
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        progressAnimationImageView.clearAnimation();
        progressAnimationImageView.startAnimation(r1);
        return 0;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        WaveView waveView = this.mWaveView;
        ProgressAnimationImageView progressAnimationImageView = this.mCircleView;
        waveView.layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
        int measuredWidth = getMeasuredWidth();
        int measuredWidth2 = progressAnimationImageView.getMeasuredWidth();
        progressAnimationImageView.layout((measuredWidth - measuredWidth2) / 2, -progressAnimationImageView.getMeasuredHeight(), (measuredWidth + measuredWidth2) / 2, 0);
        if (isInEditMode()) {
            onMoving(true, 0.99f, DensityUtil.dp2px(99.0f), DensityUtil.dp2px(100.0f), DensityUtil.dp2px(100.0f));
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
        WaveView waveView = this.mWaveView;
        ProgressAnimationImageView progressAnimationImageView = this.mCircleView;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.mProgress.getIntrinsicWidth(), 1073741824);
        progressAnimationImageView.measure(makeMeasureSpec, makeMeasureSpec);
        waveView.measure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i), 1073741824), View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i2), 1073741824));
    }

    public void onMoving(boolean z, float f, int i, int i2, int i3) {
        if (z) {
            if (this.mState != RefreshState.Refreshing) {
                ProgressAnimationImageView progressAnimationImageView = this.mCircleView;
                double min = (double) Math.min(1.0f, f);
                Double.isNaN(min);
                float max = (((float) Math.max(min - 0.4d, 0.0d)) * 5.0f) / 3.0f;
                float f2 = f > 3.0f ? 2.0f : f > 1.0f ? f - 1.0f : CropImageView.DEFAULT_ASPECT_RATIO;
                float f3 = (f2 * (4.0f - f2)) / 8.0f;
                if (f < 1.0f) {
                    this.mProgress.setStartEndTrim(CropImageView.DEFAULT_ASPECT_RATIO, Math.min(MAX_PROGRESS_ROTATION_RATE, MAX_PROGRESS_ROTATION_RATE * max));
                    this.mProgress.setArrowScale(Math.min(1.0f, max));
                }
                this.mProgress.setProgressRotation(((f3 * 2.0f) + ((max * 0.4f) - 0.25f)) * 0.5f);
                progressAnimationImageView.setTranslationY(this.mWaveView.getCurrentCircleCenterY());
                float min2 = (((float) i) * 1.0f) / ((float) Math.min(getMeasuredWidth(), getMeasuredHeight()));
                float f4 = (min2 * (5.0f - (2.0f * min2))) / 3.5f;
                float f5 = f4 - VERTICAL_DRAG_THRESHOLD.FIRST.val;
                float f6 = (f4 - VERTICAL_DRAG_THRESHOLD.SECOND.val) / 5.0f;
                this.mLastFirstBounds = f4;
                if (f4 < VERTICAL_DRAG_THRESHOLD.FIRST.val) {
                    this.mWaveView.beginPhase(f4);
                } else if (f4 < VERTICAL_DRAG_THRESHOLD.SECOND.val) {
                    this.mWaveView.appearPhase(f4, f5);
                } else {
                    this.mWaveView.expandPhase(f4, f5, f6);
                }
            }
        } else if (this.mLastFirstBounds != CropImageView.DEFAULT_ASPECT_RATIO && this.mState == RefreshState.None) {
            this.mProgress.showArrow(false);
            this.mProgress.setProgressRotation(CropImageView.DEFAULT_ASPECT_RATIO);
            this.mProgress.setStartEndTrim(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
            this.mWaveView.startWaveAnimation(this.mLastFirstBounds);
            this.mLastFirstBounds = CropImageView.DEFAULT_ASPECT_RATIO;
        }
    }

    public void onReleased(@NonNull RefreshLayout refreshLayout, int i, int i2) {
        this.mLastFirstBounds = CropImageView.DEFAULT_ASPECT_RATIO;
        this.mWaveView.animationDropCircle();
        this.mProgress.setAlpha(255);
        this.mProgress.start();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO});
        ofFloat.setDuration(500);
        ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ProgressAnimationImageView progressAnimationImageView = WaveSwipeHeader.this.mCircleView;
                progressAnimationImageView.setTranslationY(WaveSwipeHeader.this.mWaveView.getCurrentCircleCenterY() + (((float) progressAnimationImageView.getHeight()) / 2.0f));
            }
        });
        ofFloat.start();
    }

    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState refreshState, @NonNull RefreshState refreshState2) {
        ProgressAnimationImageView progressAnimationImageView = this.mCircleView;
        this.mState = refreshState2;
        switch (refreshState2) {
            case PullDownToRefresh:
                this.mProgress.showArrow(true);
                progressAnimationImageView.setScaleX(1.0f);
                progressAnimationImageView.setScaleY(1.0f);
                this.mProgress.setAlpha(255);
                return;
            case PullDownCanceled:
                this.mProgress.showArrow(false);
                this.mProgress.setProgressRotation(CropImageView.DEFAULT_ASPECT_RATIO);
                this.mProgress.setStartEndTrim(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
                this.mWaveView.startWaveAnimation(this.mLastFirstBounds);
                this.mLastFirstBounds = CropImageView.DEFAULT_ASPECT_RATIO;
                return;
            default:
                return;
        }
    }

    public void setColorSchemeColorIds(@IdRes int... iArr) {
        Resources resources = getResources();
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            if (Build.VERSION.SDK_INT >= 23) {
                iArr2[i] = resources.getColor(iArr[i], getContext().getTheme());
            } else {
                iArr2[i] = resources.getColor(iArr[i]);
            }
        }
        this.mProgress.setColorSchemeColors(iArr2);
    }

    public void setColorSchemeColors(int... iArr) {
        this.mProgress.setColorSchemeColors(iArr);
    }

    @Deprecated
    public void setPrimaryColors(@ColorInt int... iArr) {
        if (iArr.length > 0) {
            this.mWaveView.setWaveColor(iArr[0]);
            if (iArr.length > 1) {
                this.mProgress.setColorSchemeColors(iArr[1]);
            }
        }
    }
}
