package com.scwang.smartrefresh.layout.header;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.scwang.smartrefresh.layout.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.internal.ArrowDrawable;
import com.scwang.smartrefresh.layout.internal.InternalClassics;
import com.scwang.smartrefresh.layout.internal.ProgressDrawable;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.yalantis.ucrop.view.CropImageView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ClassicsHeader extends InternalClassics<ClassicsHeader> implements RefreshHeader {
    public static final byte ID_TEXT_UPDATE = 4;
    public static String REFRESH_HEADER_FAILED;
    public static String REFRESH_HEADER_FINISH;
    public static String REFRESH_HEADER_LOADING;
    public static String REFRESH_HEADER_PULLING;
    public static String REFRESH_HEADER_REFRESHING;
    public static String REFRESH_HEADER_RELEASE;
    public static String REFRESH_HEADER_SECONDARY;
    public static String REFRESH_HEADER_UPDATE;
    protected String KEY_LAST_UPDATE_TIME;
    protected boolean mEnableLastTime;
    protected Date mLastTime;
    protected DateFormat mLastUpdateFormat;
    protected TextView mLastUpdateText;
    protected SharedPreferences mShared;
    protected String mTextFailed;
    protected String mTextFinish;
    protected String mTextLoading;
    protected String mTextPulling;
    protected String mTextRefreshing;
    protected String mTextRelease;
    protected String mTextSecondary;
    protected String mTextUpdate;

    public ClassicsHeader(Context context) {
        this(context, (AttributeSet) null);
    }

    public ClassicsHeader(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ClassicsHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        FragmentManager supportFragmentManager;
        List fragments;
        this.KEY_LAST_UPDATE_TIME = "LAST_UPDATE_TIME";
        this.mEnableLastTime = true;
        this.mTextPulling = null;
        this.mTextRefreshing = null;
        this.mTextLoading = null;
        this.mTextRelease = null;
        this.mTextFinish = null;
        this.mTextFailed = null;
        this.mTextUpdate = null;
        this.mTextSecondary = null;
        this.mLastUpdateText = new TextView(context);
        this.mLastUpdateText.setTextColor(-8618884);
        ImageView imageView = this.mArrowView;
        TextView textView = this.mLastUpdateText;
        ImageView imageView2 = this.mProgressView;
        LinearLayout linearLayout = this.mCenterLayout;
        DensityUtil densityUtil = new DensityUtil();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ClassicsHeader);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) imageView2.getLayoutParams();
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.topMargin = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ClassicsHeader_srlTextTimeMarginTop, densityUtil.dip2px(CropImageView.DEFAULT_ASPECT_RATIO));
        layoutParams2.rightMargin = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ClassicsFooter_srlDrawableMarginRight, densityUtil.dip2px(20.0f));
        layoutParams.rightMargin = layoutParams2.rightMargin;
        layoutParams.width = obtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsHeader_srlDrawableArrowSize, layoutParams.width);
        layoutParams.height = obtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsHeader_srlDrawableArrowSize, layoutParams.height);
        layoutParams2.width = obtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsHeader_srlDrawableProgressSize, layoutParams2.width);
        layoutParams2.height = obtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsHeader_srlDrawableProgressSize, layoutParams2.height);
        layoutParams.width = obtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsHeader_srlDrawableSize, layoutParams.width);
        layoutParams.height = obtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsHeader_srlDrawableSize, layoutParams.height);
        layoutParams2.width = obtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsHeader_srlDrawableSize, layoutParams2.width);
        layoutParams2.height = obtainStyledAttributes.getLayoutDimension(R.styleable.ClassicsHeader_srlDrawableSize, layoutParams2.height);
        this.mFinishDuration = obtainStyledAttributes.getInt(R.styleable.ClassicsHeader_srlFinishDuration, this.mFinishDuration);
        this.mEnableLastTime = obtainStyledAttributes.getBoolean(R.styleable.ClassicsHeader_srlEnableLastTime, this.mEnableLastTime);
        this.mSpinnerStyle = SpinnerStyle.values()[obtainStyledAttributes.getInt(R.styleable.ClassicsHeader_srlClassicsSpinnerStyle, this.mSpinnerStyle.ordinal())];
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsHeader_srlDrawableArrow)) {
            this.mArrowView.setImageDrawable(obtainStyledAttributes.getDrawable(R.styleable.ClassicsHeader_srlDrawableArrow));
        } else {
            this.mArrowDrawable = new ArrowDrawable();
            this.mArrowDrawable.setColor(-10066330);
            this.mArrowView.setImageDrawable(this.mArrowDrawable);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsHeader_srlDrawableProgress)) {
            this.mProgressView.setImageDrawable(obtainStyledAttributes.getDrawable(R.styleable.ClassicsHeader_srlDrawableProgress));
        } else {
            this.mProgressDrawable = new ProgressDrawable();
            this.mProgressDrawable.setColor(-10066330);
            this.mProgressView.setImageDrawable(this.mProgressDrawable);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsHeader_srlTextSizeTitle)) {
            this.mTitleText.setTextSize(0, (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.ClassicsHeader_srlTextSizeTitle, DensityUtil.dp2px(16.0f)));
        } else {
            this.mTitleText.setTextSize(16.0f);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsHeader_srlTextSizeTime)) {
            this.mLastUpdateText.setTextSize(0, (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.ClassicsHeader_srlTextSizeTime, DensityUtil.dp2px(12.0f)));
        } else {
            this.mLastUpdateText.setTextSize(12.0f);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsHeader_srlPrimaryColor)) {
            super.setPrimaryColor(obtainStyledAttributes.getColor(R.styleable.ClassicsHeader_srlPrimaryColor, 0));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsHeader_srlAccentColor)) {
            setAccentColor(obtainStyledAttributes.getColor(R.styleable.ClassicsHeader_srlAccentColor, 0));
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsHeader_srlTextPulling)) {
            this.mTextPulling = obtainStyledAttributes.getString(R.styleable.ClassicsHeader_srlTextPulling);
        } else if (REFRESH_HEADER_PULLING != null) {
            this.mTextPulling = REFRESH_HEADER_PULLING;
        } else {
            this.mTextPulling = context.getString(R.string.srl_header_pulling);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsHeader_srlTextLoading)) {
            this.mTextLoading = obtainStyledAttributes.getString(R.styleable.ClassicsHeader_srlTextLoading);
        } else if (REFRESH_HEADER_LOADING != null) {
            this.mTextLoading = REFRESH_HEADER_LOADING;
        } else {
            this.mTextLoading = context.getString(R.string.srl_header_loading);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsHeader_srlTextRelease)) {
            this.mTextRelease = obtainStyledAttributes.getString(R.styleable.ClassicsHeader_srlTextRelease);
        } else if (REFRESH_HEADER_RELEASE != null) {
            this.mTextRelease = REFRESH_HEADER_RELEASE;
        } else {
            this.mTextRelease = context.getString(R.string.srl_header_release);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsHeader_srlTextFinish)) {
            this.mTextFinish = obtainStyledAttributes.getString(R.styleable.ClassicsHeader_srlTextFinish);
        } else if (REFRESH_HEADER_FINISH != null) {
            this.mTextFinish = REFRESH_HEADER_FINISH;
        } else {
            this.mTextFinish = context.getString(R.string.srl_header_finish);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsHeader_srlTextFailed)) {
            this.mTextFailed = obtainStyledAttributes.getString(R.styleable.ClassicsHeader_srlTextFailed);
        } else if (REFRESH_HEADER_FAILED != null) {
            this.mTextFailed = REFRESH_HEADER_FAILED;
        } else {
            this.mTextFailed = context.getString(R.string.srl_header_failed);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsHeader_srlTextSecondary)) {
            this.mTextSecondary = obtainStyledAttributes.getString(R.styleable.ClassicsHeader_srlTextSecondary);
        } else if (REFRESH_HEADER_SECONDARY != null) {
            this.mTextSecondary = REFRESH_HEADER_SECONDARY;
        } else {
            this.mTextSecondary = context.getString(R.string.srl_header_secondary);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsHeader_srlTextRefreshing)) {
            this.mTextRefreshing = obtainStyledAttributes.getString(R.styleable.ClassicsHeader_srlTextRefreshing);
        } else if (REFRESH_HEADER_REFRESHING != null) {
            this.mTextRefreshing = REFRESH_HEADER_REFRESHING;
        } else {
            this.mTextRefreshing = context.getString(R.string.srl_header_refreshing);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.ClassicsHeader_srlTextUpdate)) {
            this.mTextUpdate = obtainStyledAttributes.getString(R.styleable.ClassicsHeader_srlTextUpdate);
        } else if (REFRESH_HEADER_UPDATE != null) {
            this.mTextUpdate = REFRESH_HEADER_UPDATE;
        } else {
            this.mTextUpdate = context.getString(R.string.srl_header_update);
        }
        this.mLastUpdateFormat = new SimpleDateFormat(this.mTextUpdate, Locale.getDefault());
        obtainStyledAttributes.recycle();
        textView.setId(4);
        textView.setVisibility(this.mEnableLastTime ? 0 : 8);
        linearLayout.addView(textView, layoutParams3);
        this.mTitleText.setText(isInEditMode() ? this.mTextRefreshing : this.mTextPulling);
        try {
            if ((context instanceof FragmentActivity) && (supportFragmentManager = ((FragmentActivity) context).getSupportFragmentManager()) != null && (fragments = supportFragmentManager.getFragments()) != null && fragments.size() > 0) {
                setLastUpdateTime(new Date());
                return;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.KEY_LAST_UPDATE_TIME += context.getClass().getName();
        this.mShared = context.getSharedPreferences("ClassicsHeader", 0);
        setLastUpdateTime(new Date(this.mShared.getLong(this.KEY_LAST_UPDATE_TIME, System.currentTimeMillis())));
    }

    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean z) {
        if (z) {
            this.mTitleText.setText(this.mTextFinish);
            if (this.mLastTime != null) {
                setLastUpdateTime(new Date());
            }
        } else {
            this.mTitleText.setText(this.mTextFailed);
        }
        return super.onFinish(refreshLayout, z);
    }

    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState refreshState, @NonNull RefreshState refreshState2) {
        ImageView imageView = this.mArrowView;
        TextView textView = this.mLastUpdateText;
        int i = 8;
        switch (refreshState2) {
            case None:
                if (this.mEnableLastTime) {
                    i = 0;
                }
                textView.setVisibility(i);
                break;
            case PullDownToRefresh:
                break;
            case Refreshing:
            case RefreshReleased:
                this.mTitleText.setText(this.mTextRefreshing);
                imageView.setVisibility(8);
                return;
            case ReleaseToRefresh:
                this.mTitleText.setText(this.mTextRelease);
                imageView.animate().rotation(180.0f);
                return;
            case ReleaseToTwoLevel:
                this.mTitleText.setText(this.mTextSecondary);
                imageView.animate().rotation(CropImageView.DEFAULT_ASPECT_RATIO);
                return;
            case Loading:
                imageView.setVisibility(8);
                if (this.mEnableLastTime) {
                    i = 4;
                }
                textView.setVisibility(i);
                this.mTitleText.setText(this.mTextLoading);
                return;
            default:
                return;
        }
        this.mTitleText.setText(this.mTextPulling);
        imageView.setVisibility(0);
        imageView.animate().rotation(CropImageView.DEFAULT_ASPECT_RATIO);
    }

    public ClassicsHeader setLastUpdateTime(Date date) {
        this.mLastTime = date;
        this.mLastUpdateText.setText(this.mLastUpdateFormat.format(date));
        if (this.mShared != null && !isInEditMode()) {
            this.mShared.edit().putLong(this.KEY_LAST_UPDATE_TIME, date.getTime()).apply();
        }
        return this;
    }

    public ClassicsHeader setTimeFormat(DateFormat dateFormat) {
        this.mLastUpdateFormat = dateFormat;
        if (this.mLastTime != null) {
            this.mLastUpdateText.setText(this.mLastUpdateFormat.format(this.mLastTime));
        }
        return this;
    }

    public ClassicsHeader setLastUpdateText(CharSequence charSequence) {
        this.mLastTime = null;
        this.mLastUpdateText.setText(charSequence);
        return this;
    }

    public ClassicsHeader setAccentColor(@ColorInt int i) {
        this.mLastUpdateText.setTextColor((16777215 & i) | -872415232);
        return (ClassicsHeader) super.setAccentColor(i);
    }

    public ClassicsHeader setEnableLastTime(boolean z) {
        TextView textView = this.mLastUpdateText;
        this.mEnableLastTime = z;
        textView.setVisibility(z ? 0 : 8);
        if (this.mRefreshKernel != null) {
            this.mRefreshKernel.requestRemeasureHeightFor(this);
        }
        return this;
    }

    public ClassicsHeader setTextSizeTime(float f) {
        this.mLastUpdateText.setTextSize(f);
        if (this.mRefreshKernel != null) {
            this.mRefreshKernel.requestRemeasureHeightFor(this);
        }
        return this;
    }

    public ClassicsHeader setTextTimeMarginTop(float f) {
        TextView textView = this.mLastUpdateText;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) textView.getLayoutParams();
        marginLayoutParams.topMargin = DensityUtil.dp2px(f);
        textView.setLayoutParams(marginLayoutParams);
        return this;
    }
}
