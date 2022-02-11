package com.scwang.smartrefresh.layout.footer;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.scwang.smartrefresh.layout.R;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.internal.ArrowDrawable;
import com.scwang.smartrefresh.layout.internal.InternalClassics;
import com.scwang.smartrefresh.layout.internal.ProgressDrawable;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.yalantis.ucrop.view.CropImageView;

public class ClassicsFooter extends InternalClassics<ClassicsFooter> implements RefreshFooter {
    public static String REFRESH_FOOTER_FAILED;
    public static String REFRESH_FOOTER_FINISH;
    public static String REFRESH_FOOTER_LOADING;
    public static String REFRESH_FOOTER_NOTHING;
    public static String REFRESH_FOOTER_PULLING;
    public static String REFRESH_FOOTER_REFRESHING;
    public static String REFRESH_FOOTER_RELEASE;
    protected boolean mNoMoreData;
    protected String mTextFailed;
    protected String mTextFinish;
    protected String mTextLoading;
    protected String mTextNothing;
    protected String mTextPulling;
    protected String mTextRefreshing;
    protected String mTextRelease;

    public ClassicsFooter(Context context) {
        this(context, (AttributeSet) null);
    }

    public ClassicsFooter(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ClassicsFooter(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTextPulling = null;
        this.mTextRelease = null;
        this.mTextLoading = null;
        this.mTextRefreshing = null;
        this.mTextFinish = null;
        this.mTextFailed = null;
        this.mTextNothing = null;
        this.mNoMoreData = false;
        ImageView imageView = this.mArrowView;
        ImageView imageView2 = this.mProgressView;
        DensityUtil densityUtil = new DensityUtil();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ClassicsFooter);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) imageView2.getLayoutParams();
        layoutParams2.rightMargin = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ClassicsFooter_srlDrawableMarginRight, densityUtil.dip2px(20.0f));
        layoutParams.rightMargin = layoutParams2.rightMargin;
        layoutParams.width = obtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsFooter_srlDrawableArrowSize, layoutParams.width);
        layoutParams.height = obtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsFooter_srlDrawableArrowSize, layoutParams.height);
        layoutParams2.width = obtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsFooter_srlDrawableProgressSize, layoutParams2.width);
        layoutParams2.height = obtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsFooter_srlDrawableProgressSize, layoutParams2.height);
        layoutParams.width = obtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsFooter_srlDrawableSize, layoutParams.width);
        layoutParams.height = obtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsFooter_srlDrawableSize, layoutParams.height);
        layoutParams2.width = obtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsFooter_srlDrawableSize, layoutParams2.width);
        layoutParams2.height = obtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsFooter_srlDrawableSize, layoutParams2.height);
        this.mFinishDuration = obtainStyledAttributes.getInt(R.styleable.ClassicsFooter_srlFinishDuration, this.mFinishDuration);
        this.mSpinnerStyle = SpinnerStyle.values()[obtainStyledAttributes.getInt(R.styleable.ClassicsFooter_srlClassicsSpinnerStyle, this.mSpinnerStyle.ordinal())];
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlDrawableArrow)) {
            this.mArrowView.setImageDrawable(obtainStyledAttributes.getDrawable(R.styleable.ClassicsFooter_srlDrawableArrow));
        } else {
            this.mArrowDrawable = new ArrowDrawable();
            this.mArrowDrawable.setColor(-10066330);
            this.mArrowView.setImageDrawable(this.mArrowDrawable);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlDrawableProgress)) {
            this.mProgressView.setImageDrawable(obtainStyledAttributes.getDrawable(R.styleable.ClassicsFooter_srlDrawableProgress));
        } else {
            this.mProgressDrawable = new ProgressDrawable();
            this.mProgressDrawable.setColor(-10066330);
            this.mProgressView.setImageDrawable(this.mProgressDrawable);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlTextSizeTitle)) {
            this.mTitleText.setTextSize(0, (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.ClassicsFooter_srlTextSizeTitle, DensityUtil.dp2px(16.0f)));
        } else {
            this.mTitleText.setTextSize(16.0f);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlPrimaryColor)) {
            super.setPrimaryColor(obtainStyledAttributes.getColor(R.styleable.ClassicsFooter_srlPrimaryColor, 0));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlAccentColor)) {
            super.setAccentColor(obtainStyledAttributes.getColor(R.styleable.ClassicsFooter_srlAccentColor, 0));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlTextPulling)) {
            this.mTextPulling = obtainStyledAttributes.getString(R.styleable.ClassicsFooter_srlTextPulling);
        } else if (REFRESH_FOOTER_PULLING != null) {
            this.mTextPulling = REFRESH_FOOTER_PULLING;
        } else {
            this.mTextPulling = context.getString(R.string.srl_footer_pulling);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlTextRelease)) {
            this.mTextRelease = obtainStyledAttributes.getString(R.styleable.ClassicsFooter_srlTextRelease);
        } else if (REFRESH_FOOTER_RELEASE != null) {
            this.mTextRelease = REFRESH_FOOTER_RELEASE;
        } else {
            this.mTextRelease = context.getString(R.string.srl_footer_release);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlTextLoading)) {
            this.mTextLoading = obtainStyledAttributes.getString(R.styleable.ClassicsFooter_srlTextLoading);
        } else if (REFRESH_FOOTER_LOADING != null) {
            this.mTextLoading = REFRESH_FOOTER_LOADING;
        } else {
            this.mTextLoading = context.getString(R.string.srl_footer_loading);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlTextRefreshing)) {
            this.mTextRefreshing = obtainStyledAttributes.getString(R.styleable.ClassicsFooter_srlTextRefreshing);
        } else if (REFRESH_FOOTER_REFRESHING != null) {
            this.mTextRefreshing = REFRESH_FOOTER_REFRESHING;
        } else {
            this.mTextRefreshing = context.getString(R.string.srl_footer_refreshing);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlTextFinish)) {
            this.mTextFinish = obtainStyledAttributes.getString(R.styleable.ClassicsFooter_srlTextFinish);
        } else if (REFRESH_FOOTER_FINISH != null) {
            this.mTextFinish = REFRESH_FOOTER_FINISH;
        } else {
            this.mTextFinish = context.getString(R.string.srl_footer_finish);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlTextFailed)) {
            this.mTextFailed = obtainStyledAttributes.getString(R.styleable.ClassicsFooter_srlTextFailed);
        } else if (REFRESH_FOOTER_FAILED != null) {
            this.mTextFailed = REFRESH_FOOTER_FAILED;
        } else {
            this.mTextFailed = context.getString(R.string.srl_footer_failed);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsFooter_srlTextNothing)) {
            this.mTextNothing = obtainStyledAttributes.getString(R.styleable.ClassicsFooter_srlTextNothing);
        } else if (REFRESH_FOOTER_NOTHING != null) {
            this.mTextNothing = REFRESH_FOOTER_NOTHING;
        } else {
            this.mTextNothing = context.getString(R.string.srl_footer_nothing);
        }
        obtainStyledAttributes.recycle();
        this.mTitleText.setTextColor(-10066330);
        this.mTitleText.setText(isInEditMode() ? this.mTextLoading : this.mTextPulling);
    }

    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int i, int i2) {
        if (!this.mNoMoreData) {
            super.onStartAnimator(refreshLayout, i, i2);
        }
    }

    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean z) {
        if (this.mNoMoreData) {
            return 0;
        }
        this.mTitleText.setText(z ? this.mTextFinish : this.mTextFailed);
        return super.onFinish(refreshLayout, z);
    }

    @Deprecated
    public void setPrimaryColors(@ColorInt int... iArr) {
        if (this.mSpinnerStyle == SpinnerStyle.FixedBehind) {
            super.setPrimaryColors(iArr);
        }
    }

    public boolean setNoMoreData(boolean z) {
        if (this.mNoMoreData == z) {
            return true;
        }
        this.mNoMoreData = z;
        ImageView imageView = this.mArrowView;
        if (z) {
            this.mTitleText.setText(this.mTextNothing);
            imageView.setVisibility(8);
            return true;
        }
        this.mTitleText.setText(this.mTextPulling);
        imageView.setVisibility(0);
        return true;
    }

    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState refreshState, @NonNull RefreshState refreshState2) {
        ImageView imageView = this.mArrowView;
        if (!this.mNoMoreData) {
            switch (refreshState2) {
                case None:
                    imageView.setVisibility(0);
                    break;
                case PullUpToLoad:
                    break;
                case Loading:
                case LoadReleased:
                    imageView.setVisibility(8);
                    this.mTitleText.setText(this.mTextLoading);
                    return;
                case ReleaseToLoad:
                    this.mTitleText.setText(this.mTextRelease);
                    imageView.animate().rotation(CropImageView.DEFAULT_ASPECT_RATIO);
                    return;
                case Refreshing:
                    this.mTitleText.setText(this.mTextRefreshing);
                    imageView.setVisibility(8);
                    return;
                default:
                    return;
            }
            this.mTitleText.setText(this.mTextPulling);
            imageView.animate().rotation(180.0f);
        }
    }
}
