package com.scwang.smartrefresh.header;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.scwang.smartrefresh.header.internal.MaterialProgressDrawable;
import com.scwang.smartrefresh.header.waterdrop.WaterDropView;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;
import com.scwang.smartrefresh.layout.internal.ProgressDrawable;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.yalantis.ucrop.view.CropImageView;

public class WaterDropHeader extends InternalAbstract implements RefreshHeader {
    protected static final float MAX_PROGRESS_ANGLE = 0.8f;
    protected ImageView mImageView;
    protected MaterialProgressDrawable mProgress;
    protected ProgressDrawable mProgressDrawable;
    protected RefreshState mState;
    protected WaterDropView mWaterDropView;

    public WaterDropHeader(Context context) {
        this(context, (AttributeSet) null);
    }

    public WaterDropHeader(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WaterDropHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        DensityUtil densityUtil = new DensityUtil();
        this.mSpinnerStyle = SpinnerStyle.Scale;
        this.mWaterDropView = new WaterDropView(context);
        this.mWaterDropView.updateCompleteState(0);
        addView(this.mWaterDropView, -1, -1);
        this.mProgressDrawable = new ProgressDrawable();
        ProgressDrawable progressDrawable = this.mProgressDrawable;
        progressDrawable.setCallback(this);
        progressDrawable.setBounds(0, 0, densityUtil.dip2px(20.0f), densityUtil.dip2px(20.0f));
        this.mImageView = new ImageView(context);
        this.mProgress = new MaterialProgressDrawable(this.mImageView);
        this.mProgress.setBackgroundColor(-1);
        this.mProgress.setAlpha(255);
        this.mProgress.setColorSchemeColors(-1, -16737844, -48060, -10053376, -5609780, -30720);
        this.mImageView.setImageDrawable(this.mProgress);
        addView(this.mImageView, densityUtil.dip2px(30.0f), densityUtil.dip2px(30.0f));
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        WaterDropView waterDropView = this.mWaterDropView;
        ProgressDrawable progressDrawable = this.mProgressDrawable;
        if (this.mState == RefreshState.Refreshing) {
            canvas.save();
            canvas.translate((float) ((getWidth() / 2) - (progressDrawable.getBounds().width() / 2)), (float) ((waterDropView.getPaddingTop() + this.mWaterDropView.getMaxCircleRadius()) - (progressDrawable.getBounds().height() / 2)));
            progressDrawable.draw(canvas);
            canvas.restore();
        }
    }

    public void invalidateDrawable(@NonNull Drawable drawable) {
        invalidate();
    }

    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean z) {
        this.mProgressDrawable.stop();
        return 0;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        ImageView imageView = this.mImageView;
        WaterDropView waterDropView = this.mWaterDropView;
        int measuredWidth = getMeasuredWidth();
        int measuredWidth2 = waterDropView.getMeasuredWidth();
        int i5 = measuredWidth / 2;
        int i6 = measuredWidth2 / 2;
        int i7 = i5 - i6;
        waterDropView.layout(i7, 0, i7 + measuredWidth2, waterDropView.getMeasuredHeight() + 0);
        int measuredWidth3 = imageView.getMeasuredWidth();
        int measuredHeight = imageView.getMeasuredHeight();
        int i8 = measuredWidth3 / 2;
        int i9 = i5 - i8;
        int i10 = i6 - i8;
        int i11 = (measuredWidth2 - measuredWidth3) / 2;
        if (i10 + measuredHeight > waterDropView.getBottom() - i11) {
            i10 = (waterDropView.getBottom() - i11) - measuredHeight;
        }
        imageView.layout(i9, i10, measuredWidth3 + i9, measuredHeight + i10);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        ImageView imageView = this.mImageView;
        WaterDropView waterDropView = this.mWaterDropView;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        imageView.measure(View.MeasureSpec.makeMeasureSpec(layoutParams.width, 1073741824), View.MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824));
        waterDropView.measure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i), Integer.MIN_VALUE), i2);
        super.setMeasuredDimension(View.resolveSize(Math.max(imageView.getMeasuredWidth(), waterDropView.getMeasuredWidth()), i), View.resolveSize(Math.max(imageView.getMeasuredHeight(), waterDropView.getMeasuredHeight()), i2));
    }

    public void onMoving(boolean z, float f, int i, int i2, int i3) {
        if (z || !(this.mState == RefreshState.Refreshing || this.mState == RefreshState.RefreshReleased)) {
            WaterDropView waterDropView = this.mWaterDropView;
            this.mWaterDropView.updateCompleteState(Math.max(i, 0), i3 + i2);
            waterDropView.postInvalidate();
        }
        if (z) {
            float f2 = (float) i2;
            double min = (double) Math.min(1.0f, Math.abs((((float) i) * 1.0f) / f2));
            Double.isNaN(min);
            float max = (((float) Math.max(min - 0.4d, 0.0d)) * 5.0f) / 3.0f;
            double max2 = (double) (Math.max(CropImageView.DEFAULT_ASPECT_RATIO, Math.min((float) (Math.abs(i) - i2), f2 * 2.0f) / f2) / 4.0f);
            double pow = Math.pow(max2, 2.0d);
            Double.isNaN(max2);
            this.mProgress.showArrow(true);
            this.mProgress.setStartEndTrim(CropImageView.DEFAULT_ASPECT_RATIO, Math.min(MAX_PROGRESS_ANGLE, max * MAX_PROGRESS_ANGLE));
            this.mProgress.setArrowScale(Math.min(1.0f, max));
            this.mProgress.setProgressRotation((((max * 0.4f) - 0.25f) + (((float) (max2 - pow)) * 2.0f * 2.0f)) * 0.5f);
        }
    }

    public void onReleased(@NonNull RefreshLayout refreshLayout, int i, int i2) {
        ImageView imageView = this.mImageView;
        final WaterDropView waterDropView = this.mWaterDropView;
        this.mProgressDrawable.start();
        imageView.setVisibility(8);
        this.mWaterDropView.createAnimator().start();
        waterDropView.animate().setDuration(150).alpha(CropImageView.DEFAULT_ASPECT_RATIO).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                waterDropView.setVisibility(8);
                waterDropView.setAlpha(1.0f);
            }
        });
    }

    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState refreshState, @NonNull RefreshState refreshState2) {
        WaterDropView waterDropView = this.mWaterDropView;
        ImageView imageView = this.mImageView;
        this.mState = refreshState2;
        switch (refreshState2) {
            case None:
                waterDropView.setVisibility(0);
                imageView.setVisibility(0);
                return;
            case PullDownToRefresh:
                waterDropView.setVisibility(0);
                imageView.setVisibility(0);
                return;
            case ReleaseToRefresh:
                waterDropView.setVisibility(0);
                imageView.setVisibility(0);
                return;
            case RefreshFinish:
                waterDropView.setVisibility(8);
                imageView.setVisibility(8);
                return;
            default:
                return;
        }
    }

    @Deprecated
    public void setPrimaryColors(@ColorInt int... iArr) {
        if (iArr.length > 0) {
            this.mWaterDropView.setIndicatorColor(iArr[0]);
        }
    }
}
