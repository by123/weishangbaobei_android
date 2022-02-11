package com.scwang.smartrefresh.layout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import android.widget.TextView;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshInitializer;
import com.scwang.smartrefresh.layout.api.RefreshContent;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshInternal;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.api.ScrollBoundaryDecider;
import com.scwang.smartrefresh.layout.constant.DimensionStatus;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.impl.RefreshContentWrapper;
import com.scwang.smartrefresh.layout.impl.RefreshFooterWrapper;
import com.scwang.smartrefresh.layout.impl.RefreshHeaderWrapper;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.scwang.smartrefresh.layout.util.DelayedRunnable;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.scwang.smartrefresh.layout.util.SmartUtil;
import com.scwang.smartrefresh.layout.util.ViscousFluidInterpolator;
import com.umeng.socialize.common.SocializeConstants;
import com.yalantis.ucrop.view.CropImageView;
import com.youth.banner.BannerConfig;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

@SuppressLint({"RestrictedApi"})
public class SmartRefreshLayout extends ViewGroup implements RefreshLayout, NestedScrollingParent {
    protected static DefaultRefreshFooterCreator sFooterCreator;
    protected static DefaultRefreshHeaderCreator sHeaderCreator;
    protected static DefaultRefreshInitializer sRefreshInitializer;
    protected Runnable animationRunnable;
    protected int mCurrentVelocity;
    protected boolean mDisableContentWhenLoading;
    protected boolean mDisableContentWhenRefresh;
    protected char mDragDirection;
    protected float mDragRate;
    protected boolean mEnableAutoLoadMore;
    protected boolean mEnableClipFooterWhenFixedBehind;
    protected boolean mEnableClipHeaderWhenFixedBehind;
    protected boolean mEnableFooterFollowWhenNoMoreData;
    protected boolean mEnableFooterTranslationContent;
    protected boolean mEnableHeaderTranslationContent;
    protected boolean mEnableLoadMore;
    protected boolean mEnableLoadMoreWhenContentNotFull;
    protected boolean mEnableOverScrollBounce;
    protected boolean mEnableOverScrollDrag;
    protected boolean mEnablePreviewInEditMode;
    protected boolean mEnablePureScrollMode;
    protected boolean mEnableRefresh;
    protected boolean mEnableScrollContentWhenLoaded;
    protected boolean mEnableScrollContentWhenRefreshed;
    protected MotionEvent mFalsifyEvent;
    protected int mFixedFooterViewId;
    protected int mFixedHeaderViewId;
    protected int mFloorDuration;
    protected int mFooterBackgroundColor;
    protected int mFooterHeight;
    protected DimensionStatus mFooterHeightStatus;
    protected int mFooterInsetStart;
    protected boolean mFooterLocked;
    protected float mFooterMaxDragRate;
    protected boolean mFooterNeedTouchEventWhenLoading;
    protected boolean mFooterNoMoreData;
    protected int mFooterTranslationViewId;
    protected float mFooterTriggerRate;
    protected Handler mHandler;
    protected int mHeaderBackgroundColor;
    protected int mHeaderHeight;
    protected DimensionStatus mHeaderHeightStatus;
    protected int mHeaderInsetStart;
    protected float mHeaderMaxDragRate;
    protected boolean mHeaderNeedTouchEventWhenRefreshing;
    protected int mHeaderTranslationViewId;
    protected float mHeaderTriggerRate;
    protected boolean mIsBeingDragged;
    protected RefreshKernel mKernel;
    protected long mLastOpenTime;
    protected int mLastSpinner;
    protected float mLastTouchX;
    protected float mLastTouchY;
    protected List<DelayedRunnable> mListDelayedRunnable;
    protected OnLoadMoreListener mLoadMoreListener;
    protected boolean mManualFooterTranslationContent;
    protected boolean mManualHeaderTranslationContent;
    protected boolean mManualLoadMore;
    protected int mMaximumVelocity;
    protected int mMinimumVelocity;
    protected NestedScrollingChildHelper mNestedChild;
    protected boolean mNestedInProgress;
    protected NestedScrollingParentHelper mNestedParent;
    protected OnMultiPurposeListener mOnMultiPurposeListener;
    protected Paint mPaint;
    protected int[] mParentOffsetInWindow;
    protected int[] mPrimaryColors;
    protected int mReboundDuration;
    protected Interpolator mReboundInterpolator;
    protected RefreshContent mRefreshContent;
    protected RefreshInternal mRefreshFooter;
    protected RefreshInternal mRefreshHeader;
    protected OnRefreshListener mRefreshListener;
    protected int mScreenHeightPixels;
    protected ScrollBoundaryDecider mScrollBoundaryDecider;
    protected Scroller mScroller;
    protected int mSpinner;
    protected RefreshState mState;
    protected boolean mSuperDispatchTouchEvent;
    protected int mTotalUnconsumed;
    protected int mTouchSlop;
    protected int mTouchSpinner;
    protected float mTouchX;
    protected float mTouchY;
    protected VelocityTracker mVelocityTracker;
    protected boolean mVerticalPermit;
    protected RefreshState mViceState;
    protected ValueAnimator reboundAnimator;

    protected class BounceRunnable implements Runnable {
        int mFrame = 0;
        int mFrameDelay = 10;
        long mLastTime;
        float mOffset = CropImageView.DEFAULT_ASPECT_RATIO;
        int mSmoothDistance;
        float mVelocity;

        BounceRunnable(float f, int i) {
            this.mVelocity = f;
            this.mSmoothDistance = i;
            this.mLastTime = AnimationUtils.currentAnimationTimeMillis();
            SmartRefreshLayout.this.postDelayed(this, (long) this.mFrameDelay);
        }

        public void run() {
            if (SmartRefreshLayout.this.animationRunnable == this && !SmartRefreshLayout.this.mState.isFinishing) {
                if (Math.abs(SmartRefreshLayout.this.mSpinner) < Math.abs(this.mSmoothDistance)) {
                    double d = (double) this.mVelocity;
                    int i = this.mFrame + 1;
                    this.mFrame = i;
                    double pow = Math.pow(0.949999988079071d, (double) (i * 2));
                    Double.isNaN(d);
                    this.mVelocity = (float) (d * pow);
                } else if (this.mSmoothDistance != 0) {
                    double d2 = (double) this.mVelocity;
                    int i2 = this.mFrame + 1;
                    this.mFrame = i2;
                    double pow2 = Math.pow(0.44999998807907104d, (double) (i2 * 2));
                    Double.isNaN(d2);
                    this.mVelocity = (float) (d2 * pow2);
                } else {
                    double d3 = (double) this.mVelocity;
                    int i3 = this.mFrame + 1;
                    this.mFrame = i3;
                    double pow3 = Math.pow(0.8500000238418579d, (double) (i3 * 2));
                    Double.isNaN(d3);
                    this.mVelocity = (float) (d3 * pow3);
                }
                long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                float f = ((((float) (currentAnimationTimeMillis - this.mLastTime)) * 1.0f) / 1000.0f) * this.mVelocity;
                if (Math.abs(f) >= 1.0f) {
                    this.mLastTime = currentAnimationTimeMillis;
                    this.mOffset += f;
                    SmartRefreshLayout.this.moveSpinnerInfinitely(this.mOffset);
                    SmartRefreshLayout.this.postDelayed(this, (long) this.mFrameDelay);
                    return;
                }
                SmartRefreshLayout.this.animationRunnable = null;
                if (Math.abs(SmartRefreshLayout.this.mSpinner) >= Math.abs(this.mSmoothDistance)) {
                    SmartRefreshLayout.this.animSpinner(this.mSmoothDistance, 0, SmartRefreshLayout.this.mReboundInterpolator, Math.min(Math.max((int) DensityUtil.px2dp(Math.abs(SmartRefreshLayout.this.mSpinner - this.mSmoothDistance)), 30), 100) * 10);
                }
            }
        }
    }

    protected class FlingRunnable implements Runnable {
        float mDamping = 0.98f;
        int mFrame = 0;
        int mFrameDelay = 10;
        long mLastTime = AnimationUtils.currentAnimationTimeMillis();
        int mOffset;
        long mStartTime = 0;
        float mVelocity;

        FlingRunnable(float f) {
            this.mVelocity = f;
            this.mOffset = SmartRefreshLayout.this.mSpinner;
        }

        public void run() {
            if (SmartRefreshLayout.this.animationRunnable == this && !SmartRefreshLayout.this.mState.isFinishing) {
                long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                long j = this.mLastTime;
                double d = (double) this.mVelocity;
                double pow = Math.pow((double) this.mDamping, (double) ((currentAnimationTimeMillis - this.mStartTime) / ((long) (SocializeConstants.CANCLE_RESULTCODE / this.mFrameDelay))));
                Double.isNaN(d);
                this.mVelocity = (float) (d * pow);
                float f = ((((float) (currentAnimationTimeMillis - j)) * 1.0f) / 1000.0f) * this.mVelocity;
                if (Math.abs(f) > 1.0f) {
                    this.mLastTime = currentAnimationTimeMillis;
                    this.mOffset = (int) (((float) this.mOffset) + f);
                    if (SmartRefreshLayout.this.mSpinner * this.mOffset > 0) {
                        SmartRefreshLayout.this.mKernel.moveSpinner(this.mOffset, true);
                        SmartRefreshLayout.this.postDelayed(this, (long) this.mFrameDelay);
                        return;
                    }
                    SmartRefreshLayout.this.animationRunnable = null;
                    SmartRefreshLayout.this.mKernel.moveSpinner(0, true);
                    SmartUtil.fling(SmartRefreshLayout.this.mRefreshContent.getScrollableView(), (int) (-this.mVelocity));
                    if (SmartRefreshLayout.this.mFooterLocked && f > CropImageView.DEFAULT_ASPECT_RATIO) {
                        SmartRefreshLayout.this.mFooterLocked = false;
                        return;
                    }
                    return;
                }
                SmartRefreshLayout.this.animationRunnable = null;
            }
        }

        public Runnable start() {
            if (SmartRefreshLayout.this.mState.isFinishing) {
                return null;
            }
            if (SmartRefreshLayout.this.mSpinner != 0 && ((!SmartRefreshLayout.this.mState.isOpening && (!SmartRefreshLayout.this.mFooterNoMoreData || !SmartRefreshLayout.this.mEnableFooterFollowWhenNoMoreData || !SmartRefreshLayout.this.isEnableRefreshOrLoadMore(SmartRefreshLayout.this.mEnableLoadMore))) || (((SmartRefreshLayout.this.mState == RefreshState.Loading || (SmartRefreshLayout.this.mFooterNoMoreData && SmartRefreshLayout.this.mEnableFooterFollowWhenNoMoreData && SmartRefreshLayout.this.isEnableRefreshOrLoadMore(SmartRefreshLayout.this.mEnableLoadMore))) && SmartRefreshLayout.this.mSpinner < (-SmartRefreshLayout.this.mFooterHeight)) || (SmartRefreshLayout.this.mState == RefreshState.Refreshing && SmartRefreshLayout.this.mSpinner > SmartRefreshLayout.this.mHeaderHeight)))) {
                int i = 0;
                int i2 = SmartRefreshLayout.this.mSpinner;
                int i3 = SmartRefreshLayout.this.mSpinner;
                float f = this.mVelocity;
                while (true) {
                    if (i3 * i2 <= 0) {
                        break;
                    }
                    double d = (double) f;
                    i++;
                    double pow = Math.pow((double) this.mDamping, (double) ((this.mFrameDelay * i) / 10));
                    Double.isNaN(d);
                    f = (float) (d * pow);
                    float f2 = ((((float) this.mFrameDelay) * 1.0f) / 1000.0f) * f;
                    if (Math.abs(f2) >= 1.0f) {
                        i2 = (int) (((float) i2) + f2);
                    } else if (!SmartRefreshLayout.this.mState.isOpening || ((SmartRefreshLayout.this.mState == RefreshState.Refreshing && i2 > SmartRefreshLayout.this.mHeaderHeight) || (SmartRefreshLayout.this.mState != RefreshState.Refreshing && i2 < (-SmartRefreshLayout.this.mFooterHeight)))) {
                        return null;
                    }
                }
            }
            this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
            SmartRefreshLayout.this.postDelayed(this, (long) this.mFrameDelay);
            return this;
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int backgroundColor = 0;
        public SpinnerStyle spinnerStyle = null;

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SmartRefreshLayout_Layout);
            this.backgroundColor = obtainStyledAttributes.getColor(R.styleable.SmartRefreshLayout_Layout_layout_srlBackgroundColor, this.backgroundColor);
            if (obtainStyledAttributes.hasValue(R.styleable.SmartRefreshLayout_Layout_layout_srlSpinnerStyle)) {
                this.spinnerStyle = SpinnerStyle.values()[obtainStyledAttributes.getInt(R.styleable.SmartRefreshLayout_Layout_layout_srlSpinnerStyle, SpinnerStyle.Translate.ordinal())];
            }
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }
    }

    public class RefreshKernelImpl implements RefreshKernel {
        public RefreshKernelImpl() {
        }

        public ValueAnimator animSpinner(int i) {
            return SmartRefreshLayout.this.animSpinner(i, 0, SmartRefreshLayout.this.mReboundInterpolator, SmartRefreshLayout.this.mReboundDuration);
        }

        public RefreshKernel finishTwoLevel() {
            if (SmartRefreshLayout.this.mState == RefreshState.TwoLevel) {
                SmartRefreshLayout.this.mKernel.setState(RefreshState.TwoLevelFinish);
                if (SmartRefreshLayout.this.mSpinner == 0) {
                    moveSpinner(0, false);
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
                } else {
                    animSpinner(0).setDuration((long) SmartRefreshLayout.this.mFloorDuration);
                }
            }
            return this;
        }

        @NonNull
        public RefreshContent getRefreshContent() {
            return SmartRefreshLayout.this.mRefreshContent;
        }

        @NonNull
        public RefreshLayout getRefreshLayout() {
            return SmartRefreshLayout.this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:38:0x00e0  */
        public RefreshKernel moveSpinner(int i, boolean z) {
            Integer num;
            if (SmartRefreshLayout.this.mSpinner != i || ((SmartRefreshLayout.this.mRefreshHeader != null && SmartRefreshLayout.this.mRefreshHeader.isSupportHorizontalDrag()) || (SmartRefreshLayout.this.mRefreshFooter != null && SmartRefreshLayout.this.mRefreshFooter.isSupportHorizontalDrag()))) {
                SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                int i2 = SmartRefreshLayout.this.mSpinner;
                SmartRefreshLayout.this.mSpinner = i;
                if (z && (SmartRefreshLayout.this.mViceState.isDragging || SmartRefreshLayout.this.mViceState.isOpening)) {
                    if (((float) SmartRefreshLayout.this.mSpinner) > ((float) SmartRefreshLayout.this.mHeaderHeight) * SmartRefreshLayout.this.mHeaderTriggerRate) {
                        if (SmartRefreshLayout.this.mState != RefreshState.ReleaseToTwoLevel) {
                            SmartRefreshLayout.this.mKernel.setState(RefreshState.ReleaseToRefresh);
                        }
                    } else if (((float) (-SmartRefreshLayout.this.mSpinner)) > ((float) SmartRefreshLayout.this.mFooterHeight) * SmartRefreshLayout.this.mFooterTriggerRate && !SmartRefreshLayout.this.mFooterNoMoreData) {
                        SmartRefreshLayout.this.mKernel.setState(RefreshState.ReleaseToLoad);
                    } else if (SmartRefreshLayout.this.mSpinner < 0 && !SmartRefreshLayout.this.mFooterNoMoreData) {
                        SmartRefreshLayout.this.mKernel.setState(RefreshState.PullUpToLoad);
                    } else if (SmartRefreshLayout.this.mSpinner > 0) {
                        SmartRefreshLayout.this.mKernel.setState(RefreshState.PullDownToRefresh);
                    }
                }
                if (SmartRefreshLayout.this.mRefreshContent != null) {
                    int i3 = null;
                    if (i >= 0 && SmartRefreshLayout.this.mRefreshHeader != null) {
                        if (SmartRefreshLayout.this.isEnableTranslationContent(SmartRefreshLayout.this.mEnableHeaderTranslationContent, SmartRefreshLayout.this.mRefreshHeader)) {
                            i3 = Integer.valueOf(i);
                        } else if (i2 < 0) {
                            i3 = 0;
                        }
                    }
                    if (i <= 0 && SmartRefreshLayout.this.mRefreshFooter != null) {
                        if (SmartRefreshLayout.this.isEnableTranslationContent(SmartRefreshLayout.this.mEnableFooterTranslationContent, SmartRefreshLayout.this.mRefreshFooter)) {
                            num = Integer.valueOf(i);
                        } else if (i2 > 0) {
                            num = 0;
                        }
                        if (num != null) {
                            SmartRefreshLayout.this.mRefreshContent.moveSpinner(num.intValue(), SmartRefreshLayout.this.mHeaderTranslationViewId, SmartRefreshLayout.this.mFooterTranslationViewId);
                            boolean z2 = (SmartRefreshLayout.this.mEnableClipHeaderWhenFixedBehind && SmartRefreshLayout.this.mRefreshHeader != null && SmartRefreshLayout.this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.FixedBehind) || SmartRefreshLayout.this.mHeaderBackgroundColor != 0;
                            boolean z3 = (SmartRefreshLayout.this.mEnableClipFooterWhenFixedBehind && SmartRefreshLayout.this.mRefreshFooter != null && SmartRefreshLayout.this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.FixedBehind) || SmartRefreshLayout.this.mFooterBackgroundColor != 0;
                            if ((z2 && (num.intValue() >= 0 || i2 > 0)) || (z3 && (num.intValue() <= 0 || i2 < 0))) {
                                smartRefreshLayout.invalidate();
                            }
                        }
                    }
                    num = i3;
                    if (num != null) {
                    }
                }
                if ((i >= 0 || i2 > 0) && SmartRefreshLayout.this.mRefreshHeader != null) {
                    int max = Math.max(i, 0);
                    int i4 = SmartRefreshLayout.this.mHeaderHeight;
                    int i5 = (int) (((float) SmartRefreshLayout.this.mHeaderHeight) * SmartRefreshLayout.this.mHeaderMaxDragRate);
                    float f = (((float) max) * 1.0f) / ((float) (SmartRefreshLayout.this.mHeaderHeight == 0 ? 1 : SmartRefreshLayout.this.mHeaderHeight));
                    if (SmartRefreshLayout.this.isEnableRefreshOrLoadMore(SmartRefreshLayout.this.mEnableRefresh) || (SmartRefreshLayout.this.mState == RefreshState.RefreshFinish && !z)) {
                        if (i2 != SmartRefreshLayout.this.mSpinner) {
                            if (SmartRefreshLayout.this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.Translate) {
                                SmartRefreshLayout.this.mRefreshHeader.getView().setTranslationY((float) SmartRefreshLayout.this.mSpinner);
                                if (!(SmartRefreshLayout.this.mHeaderBackgroundColor == 0 || SmartRefreshLayout.this.mPaint == null || SmartRefreshLayout.this.isEnableTranslationContent(SmartRefreshLayout.this.mEnableHeaderTranslationContent, SmartRefreshLayout.this.mRefreshHeader))) {
                                    smartRefreshLayout.invalidate();
                                }
                            } else if (SmartRefreshLayout.this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.Scale) {
                                SmartRefreshLayout.this.mRefreshHeader.getView().requestLayout();
                            }
                            SmartRefreshLayout.this.mRefreshHeader.onMoving(z, f, max, i4, i5);
                        }
                        if (z && SmartRefreshLayout.this.mRefreshHeader.isSupportHorizontalDrag()) {
                            int i6 = (int) SmartRefreshLayout.this.mLastTouchX;
                            int width = smartRefreshLayout.getWidth();
                            SmartRefreshLayout.this.mRefreshHeader.onHorizontalDrag(SmartRefreshLayout.this.mLastTouchX / ((float) (width == 0 ? 1 : width)), i6, width);
                        }
                    }
                    if (!(i2 == SmartRefreshLayout.this.mSpinner || SmartRefreshLayout.this.mOnMultiPurposeListener == null || !(SmartRefreshLayout.this.mRefreshHeader instanceof RefreshHeader))) {
                        SmartRefreshLayout.this.mOnMultiPurposeListener.onHeaderMoving((RefreshHeader) SmartRefreshLayout.this.mRefreshHeader, z, f, max, i4, i5);
                    }
                }
                if ((i <= 0 || i2 < 0) && SmartRefreshLayout.this.mRefreshFooter != null) {
                    int i7 = -Math.min(i, 0);
                    int i8 = SmartRefreshLayout.this.mFooterHeight;
                    int i9 = (int) (((float) SmartRefreshLayout.this.mFooterHeight) * SmartRefreshLayout.this.mFooterMaxDragRate);
                    float f2 = (((float) i7) * 1.0f) / ((float) (SmartRefreshLayout.this.mFooterHeight == 0 ? 1 : SmartRefreshLayout.this.mFooterHeight));
                    if (SmartRefreshLayout.this.isEnableRefreshOrLoadMore(SmartRefreshLayout.this.mEnableLoadMore) || (SmartRefreshLayout.this.mState == RefreshState.LoadFinish && !z)) {
                        if (i2 != SmartRefreshLayout.this.mSpinner) {
                            if (SmartRefreshLayout.this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.Translate) {
                                SmartRefreshLayout.this.mRefreshFooter.getView().setTranslationY((float) SmartRefreshLayout.this.mSpinner);
                                if (!(SmartRefreshLayout.this.mFooterBackgroundColor == 0 || SmartRefreshLayout.this.mPaint == null || SmartRefreshLayout.this.isEnableTranslationContent(SmartRefreshLayout.this.mEnableFooterTranslationContent, SmartRefreshLayout.this.mRefreshFooter))) {
                                    smartRefreshLayout.invalidate();
                                }
                            } else if (SmartRefreshLayout.this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.Scale) {
                                SmartRefreshLayout.this.mRefreshFooter.getView().requestLayout();
                            }
                            SmartRefreshLayout.this.mRefreshFooter.onMoving(z, f2, i7, i8, i9);
                        }
                        if (z && SmartRefreshLayout.this.mRefreshFooter.isSupportHorizontalDrag()) {
                            int i10 = (int) SmartRefreshLayout.this.mLastTouchX;
                            int width2 = smartRefreshLayout.getWidth();
                            SmartRefreshLayout.this.mRefreshFooter.onHorizontalDrag(SmartRefreshLayout.this.mLastTouchX / ((float) (width2 == 0 ? 1 : width2)), i10, width2);
                        }
                    }
                    if (!(i2 == SmartRefreshLayout.this.mSpinner || SmartRefreshLayout.this.mOnMultiPurposeListener == null || !(SmartRefreshLayout.this.mRefreshFooter instanceof RefreshFooter))) {
                        SmartRefreshLayout.this.mOnMultiPurposeListener.onFooterMoving((RefreshFooter) SmartRefreshLayout.this.mRefreshFooter, z, f2, i7, i8, i9);
                    }
                }
            }
            return this;
        }

        public RefreshKernel requestDefaultTranslationContentFor(@NonNull RefreshInternal refreshInternal, boolean z) {
            if (refreshInternal.equals(SmartRefreshLayout.this.mRefreshHeader)) {
                if (!SmartRefreshLayout.this.mManualHeaderTranslationContent) {
                    SmartRefreshLayout.this.mManualHeaderTranslationContent = true;
                    SmartRefreshLayout.this.mEnableHeaderTranslationContent = z;
                }
            } else if (refreshInternal.equals(SmartRefreshLayout.this.mRefreshFooter) && !SmartRefreshLayout.this.mManualFooterTranslationContent) {
                SmartRefreshLayout.this.mManualFooterTranslationContent = true;
                SmartRefreshLayout.this.mEnableFooterTranslationContent = z;
            }
            return this;
        }

        public RefreshKernel requestDrawBackgroundFor(@NonNull RefreshInternal refreshInternal, int i) {
            if (SmartRefreshLayout.this.mPaint == null && i != 0) {
                SmartRefreshLayout.this.mPaint = new Paint();
            }
            if (refreshInternal.equals(SmartRefreshLayout.this.mRefreshHeader)) {
                SmartRefreshLayout.this.mHeaderBackgroundColor = i;
            } else if (refreshInternal.equals(SmartRefreshLayout.this.mRefreshFooter)) {
                SmartRefreshLayout.this.mFooterBackgroundColor = i;
            }
            return this;
        }

        public RefreshKernel requestFloorDuration(int i) {
            SmartRefreshLayout.this.mFloorDuration = i;
            return this;
        }

        public RefreshKernel requestNeedTouchEventFor(@NonNull RefreshInternal refreshInternal, boolean z) {
            if (refreshInternal.equals(SmartRefreshLayout.this.mRefreshHeader)) {
                SmartRefreshLayout.this.mHeaderNeedTouchEventWhenRefreshing = z;
            } else if (refreshInternal.equals(SmartRefreshLayout.this.mRefreshFooter)) {
                SmartRefreshLayout.this.mFooterNeedTouchEventWhenLoading = z;
            }
            return this;
        }

        public RefreshKernel requestRemeasureHeightFor(@NonNull RefreshInternal refreshInternal) {
            if (refreshInternal.equals(SmartRefreshLayout.this.mRefreshHeader)) {
                if (SmartRefreshLayout.this.mHeaderHeightStatus.notified) {
                    SmartRefreshLayout.this.mHeaderHeightStatus = SmartRefreshLayout.this.mHeaderHeightStatus.unNotify();
                }
            } else if (refreshInternal.equals(SmartRefreshLayout.this.mRefreshFooter) && SmartRefreshLayout.this.mFooterHeightStatus.notified) {
                SmartRefreshLayout.this.mFooterHeightStatus = SmartRefreshLayout.this.mFooterHeightStatus.unNotify();
            }
            return this;
        }

        public RefreshKernel setState(@NonNull RefreshState refreshState) {
            switch (refreshState) {
                case None:
                    SmartRefreshLayout.this.resetStatus();
                    return null;
                case PullDownToRefresh:
                    if (SmartRefreshLayout.this.mState.isOpening || !SmartRefreshLayout.this.isEnableRefreshOrLoadMore(SmartRefreshLayout.this.mEnableRefresh)) {
                        SmartRefreshLayout.this.setViceState(RefreshState.PullDownToRefresh);
                        return null;
                    }
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.PullDownToRefresh);
                    return null;
                case PullUpToLoad:
                    if (!SmartRefreshLayout.this.isEnableRefreshOrLoadMore(SmartRefreshLayout.this.mEnableLoadMore) || SmartRefreshLayout.this.mState.isOpening || SmartRefreshLayout.this.mState.isFinishing || (SmartRefreshLayout.this.mFooterNoMoreData && SmartRefreshLayout.this.mEnableFooterFollowWhenNoMoreData)) {
                        SmartRefreshLayout.this.setViceState(RefreshState.PullUpToLoad);
                        return null;
                    }
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.PullUpToLoad);
                    return null;
                case PullDownCanceled:
                    if (SmartRefreshLayout.this.mState.isOpening || !SmartRefreshLayout.this.isEnableRefreshOrLoadMore(SmartRefreshLayout.this.mEnableRefresh)) {
                        SmartRefreshLayout.this.setViceState(RefreshState.PullDownCanceled);
                        return null;
                    }
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.PullDownCanceled);
                    SmartRefreshLayout.this.resetStatus();
                    return null;
                case PullUpCanceled:
                    if (!SmartRefreshLayout.this.isEnableRefreshOrLoadMore(SmartRefreshLayout.this.mEnableLoadMore) || SmartRefreshLayout.this.mState.isOpening || (SmartRefreshLayout.this.mFooterNoMoreData && SmartRefreshLayout.this.mEnableFooterFollowWhenNoMoreData)) {
                        SmartRefreshLayout.this.setViceState(RefreshState.PullUpCanceled);
                        return null;
                    }
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.PullUpCanceled);
                    SmartRefreshLayout.this.resetStatus();
                    return null;
                case ReleaseToRefresh:
                    if (SmartRefreshLayout.this.mState.isOpening || !SmartRefreshLayout.this.isEnableRefreshOrLoadMore(SmartRefreshLayout.this.mEnableRefresh)) {
                        SmartRefreshLayout.this.setViceState(RefreshState.ReleaseToRefresh);
                        return null;
                    }
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.ReleaseToRefresh);
                    return null;
                case ReleaseToLoad:
                    if (!SmartRefreshLayout.this.isEnableRefreshOrLoadMore(SmartRefreshLayout.this.mEnableLoadMore) || SmartRefreshLayout.this.mState.isOpening || SmartRefreshLayout.this.mState.isFinishing || (SmartRefreshLayout.this.mFooterNoMoreData && SmartRefreshLayout.this.mEnableFooterFollowWhenNoMoreData)) {
                        SmartRefreshLayout.this.setViceState(RefreshState.ReleaseToLoad);
                        return null;
                    }
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.ReleaseToLoad);
                    return null;
                case ReleaseToTwoLevel:
                    if (SmartRefreshLayout.this.mState.isOpening || !SmartRefreshLayout.this.isEnableRefreshOrLoadMore(SmartRefreshLayout.this.mEnableRefresh)) {
                        SmartRefreshLayout.this.setViceState(RefreshState.ReleaseToTwoLevel);
                        return null;
                    }
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.ReleaseToTwoLevel);
                    return null;
                case RefreshReleased:
                    if (SmartRefreshLayout.this.mState.isOpening || !SmartRefreshLayout.this.isEnableRefreshOrLoadMore(SmartRefreshLayout.this.mEnableRefresh)) {
                        SmartRefreshLayout.this.setViceState(RefreshState.RefreshReleased);
                        return null;
                    }
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.RefreshReleased);
                    return null;
                case LoadReleased:
                    if (SmartRefreshLayout.this.mState.isOpening || !SmartRefreshLayout.this.isEnableRefreshOrLoadMore(SmartRefreshLayout.this.mEnableLoadMore)) {
                        SmartRefreshLayout.this.setViceState(RefreshState.LoadReleased);
                        return null;
                    }
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.LoadReleased);
                    return null;
                case Refreshing:
                    SmartRefreshLayout.this.setStateRefreshing();
                    return null;
                case Loading:
                    SmartRefreshLayout.this.setStateLoading();
                    return null;
                case RefreshFinish:
                    if (SmartRefreshLayout.this.mState != RefreshState.Refreshing) {
                        return null;
                    }
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.RefreshFinish);
                    return null;
                case LoadFinish:
                    if (SmartRefreshLayout.this.mState != RefreshState.Loading) {
                        return null;
                    }
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.LoadFinish);
                    return null;
                case TwoLevelReleased:
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.TwoLevelReleased);
                    return null;
                case TwoLevelFinish:
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.TwoLevelFinish);
                    return null;
                case TwoLevel:
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.TwoLevel);
                    return null;
                default:
                    return null;
            }
        }

        public RefreshKernel startTwoLevel(boolean z) {
            if (z) {
                AnonymousClass1 r0 = new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        SmartRefreshLayout.this.mKernel.setState(RefreshState.TwoLevel);
                    }
                };
                ValueAnimator animSpinner = animSpinner(SmartRefreshLayout.this.getMeasuredHeight());
                if (animSpinner == null || animSpinner != SmartRefreshLayout.this.reboundAnimator) {
                    r0.onAnimationEnd((Animator) null);
                } else {
                    animSpinner.setDuration((long) SmartRefreshLayout.this.mFloorDuration);
                    animSpinner.addListener(r0);
                }
            } else if (animSpinner(0) == null) {
                SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
            }
            return this;
        }
    }

    public SmartRefreshLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public SmartRefreshLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SmartRefreshLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mFloorDuration = 250;
        this.mReboundDuration = 250;
        this.mDragRate = 0.5f;
        this.mDragDirection = 'n';
        this.mFixedHeaderViewId = -1;
        this.mFixedFooterViewId = -1;
        this.mHeaderTranslationViewId = -1;
        this.mFooterTranslationViewId = -1;
        this.mEnableRefresh = true;
        this.mEnableLoadMore = false;
        this.mEnableClipHeaderWhenFixedBehind = true;
        this.mEnableClipFooterWhenFixedBehind = true;
        this.mEnableHeaderTranslationContent = true;
        this.mEnableFooterTranslationContent = true;
        this.mEnableFooterFollowWhenNoMoreData = false;
        this.mEnablePreviewInEditMode = true;
        this.mEnableOverScrollBounce = true;
        this.mEnableOverScrollDrag = false;
        this.mEnableAutoLoadMore = true;
        this.mEnablePureScrollMode = false;
        this.mEnableScrollContentWhenLoaded = true;
        this.mEnableScrollContentWhenRefreshed = true;
        this.mEnableLoadMoreWhenContentNotFull = true;
        this.mDisableContentWhenRefresh = false;
        this.mDisableContentWhenLoading = false;
        this.mFooterNoMoreData = false;
        this.mManualLoadMore = false;
        this.mManualHeaderTranslationContent = false;
        this.mManualFooterTranslationContent = false;
        this.mParentOffsetInWindow = new int[2];
        this.mNestedChild = new NestedScrollingChildHelper(this);
        this.mNestedParent = new NestedScrollingParentHelper(this);
        this.mHeaderHeightStatus = DimensionStatus.DefaultUnNotify;
        this.mFooterHeightStatus = DimensionStatus.DefaultUnNotify;
        this.mHeaderMaxDragRate = 2.5f;
        this.mFooterMaxDragRate = 2.5f;
        this.mHeaderTriggerRate = 1.0f;
        this.mFooterTriggerRate = 1.0f;
        this.mKernel = new RefreshKernelImpl();
        this.mState = RefreshState.None;
        this.mViceState = RefreshState.None;
        this.mLastOpenTime = 0;
        this.mHeaderBackgroundColor = 0;
        this.mFooterBackgroundColor = 0;
        this.mFooterLocked = false;
        this.mVerticalPermit = false;
        this.mFalsifyEvent = null;
        super.setClipToPadding(false);
        DensityUtil densityUtil = new DensityUtil();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mScroller = new Scroller(context);
        this.mVelocityTracker = VelocityTracker.obtain();
        this.mScreenHeightPixels = context.getResources().getDisplayMetrics().heightPixels;
        this.mReboundInterpolator = new ViscousFluidInterpolator();
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMinimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mFooterHeight = densityUtil.dip2px(60.0f);
        this.mHeaderHeight = densityUtil.dip2px(100.0f);
        this.mNestedChild.setNestedScrollingEnabled(true);
        if (sRefreshInitializer != null) {
            sRefreshInitializer.initialize(context, this);
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SmartRefreshLayout);
        this.mNestedChild.setNestedScrollingEnabled(obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableNestedScrolling, this.mNestedChild.isNestedScrollingEnabled()));
        this.mDragRate = obtainStyledAttributes.getFloat(R.styleable.SmartRefreshLayout_srlDragRate, this.mDragRate);
        this.mHeaderMaxDragRate = obtainStyledAttributes.getFloat(R.styleable.SmartRefreshLayout_srlHeaderMaxDragRate, this.mHeaderMaxDragRate);
        this.mFooterMaxDragRate = obtainStyledAttributes.getFloat(R.styleable.SmartRefreshLayout_srlFooterMaxDragRate, this.mFooterMaxDragRate);
        this.mHeaderTriggerRate = obtainStyledAttributes.getFloat(R.styleable.SmartRefreshLayout_srlHeaderTriggerRate, this.mHeaderTriggerRate);
        this.mFooterTriggerRate = obtainStyledAttributes.getFloat(R.styleable.SmartRefreshLayout_srlFooterTriggerRate, this.mFooterTriggerRate);
        this.mEnableRefresh = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableRefresh, this.mEnableRefresh);
        this.mReboundDuration = obtainStyledAttributes.getInt(R.styleable.SmartRefreshLayout_srlReboundDuration, this.mReboundDuration);
        this.mEnableLoadMore = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableLoadMore, this.mEnableLoadMore);
        this.mHeaderHeight = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.SmartRefreshLayout_srlHeaderHeight, this.mHeaderHeight);
        this.mFooterHeight = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.SmartRefreshLayout_srlFooterHeight, this.mFooterHeight);
        this.mHeaderInsetStart = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.SmartRefreshLayout_srlHeaderInsetStart, this.mHeaderInsetStart);
        this.mFooterInsetStart = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.SmartRefreshLayout_srlFooterInsetStart, this.mFooterInsetStart);
        this.mDisableContentWhenRefresh = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlDisableContentWhenRefresh, this.mDisableContentWhenRefresh);
        this.mDisableContentWhenLoading = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlDisableContentWhenLoading, this.mDisableContentWhenLoading);
        this.mEnableHeaderTranslationContent = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableHeaderTranslationContent, this.mEnableHeaderTranslationContent);
        this.mEnableFooterTranslationContent = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableFooterTranslationContent, this.mEnableFooterTranslationContent);
        this.mEnablePreviewInEditMode = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnablePreviewInEditMode, this.mEnablePreviewInEditMode);
        this.mEnableAutoLoadMore = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableAutoLoadMore, this.mEnableAutoLoadMore);
        this.mEnableOverScrollBounce = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableOverScrollBounce, this.mEnableOverScrollBounce);
        this.mEnablePureScrollMode = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnablePureScrollMode, this.mEnablePureScrollMode);
        this.mEnableScrollContentWhenLoaded = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableScrollContentWhenLoaded, this.mEnableScrollContentWhenLoaded);
        this.mEnableScrollContentWhenRefreshed = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableScrollContentWhenRefreshed, this.mEnableScrollContentWhenRefreshed);
        this.mEnableLoadMoreWhenContentNotFull = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableLoadMoreWhenContentNotFull, this.mEnableLoadMoreWhenContentNotFull);
        this.mEnableFooterFollowWhenNoMoreData = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableFooterFollowWhenLoadFinished, this.mEnableFooterFollowWhenNoMoreData);
        this.mEnableFooterFollowWhenNoMoreData = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableFooterFollowWhenNoMoreData, this.mEnableFooterFollowWhenNoMoreData);
        this.mEnableClipHeaderWhenFixedBehind = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableClipHeaderWhenFixedBehind, this.mEnableClipHeaderWhenFixedBehind);
        this.mEnableClipFooterWhenFixedBehind = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableClipFooterWhenFixedBehind, this.mEnableClipFooterWhenFixedBehind);
        this.mEnableOverScrollDrag = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableOverScrollDrag, this.mEnableOverScrollDrag);
        this.mFixedHeaderViewId = obtainStyledAttributes.getResourceId(R.styleable.SmartRefreshLayout_srlFixedHeaderViewId, this.mFixedHeaderViewId);
        this.mFixedFooterViewId = obtainStyledAttributes.getResourceId(R.styleable.SmartRefreshLayout_srlFixedFooterViewId, this.mFixedFooterViewId);
        this.mHeaderTranslationViewId = obtainStyledAttributes.getResourceId(R.styleable.SmartRefreshLayout_srlHeaderTranslationViewId, this.mHeaderTranslationViewId);
        this.mFooterTranslationViewId = obtainStyledAttributes.getResourceId(R.styleable.SmartRefreshLayout_srlFooterTranslationViewId, this.mFooterTranslationViewId);
        this.mManualLoadMore = this.mManualLoadMore || obtainStyledAttributes.hasValue(R.styleable.SmartRefreshLayout_srlEnableLoadMore);
        this.mManualHeaderTranslationContent = this.mManualHeaderTranslationContent || obtainStyledAttributes.hasValue(R.styleable.SmartRefreshLayout_srlEnableHeaderTranslationContent);
        this.mManualFooterTranslationContent = this.mManualFooterTranslationContent || obtainStyledAttributes.hasValue(R.styleable.SmartRefreshLayout_srlEnableFooterTranslationContent);
        this.mHeaderHeightStatus = obtainStyledAttributes.hasValue(R.styleable.SmartRefreshLayout_srlHeaderHeight) ? DimensionStatus.XmlLayoutUnNotify : this.mHeaderHeightStatus;
        this.mFooterHeightStatus = obtainStyledAttributes.hasValue(R.styleable.SmartRefreshLayout_srlFooterHeight) ? DimensionStatus.XmlLayoutUnNotify : this.mFooterHeightStatus;
        int color = obtainStyledAttributes.getColor(R.styleable.SmartRefreshLayout_srlAccentColor, 0);
        int color2 = obtainStyledAttributes.getColor(R.styleable.SmartRefreshLayout_srlPrimaryColor, 0);
        if (color2 != 0) {
            if (color != 0) {
                this.mPrimaryColors = new int[]{color2, color};
            } else {
                this.mPrimaryColors = new int[]{color2};
            }
        } else if (color != 0) {
            this.mPrimaryColors = new int[]{0, color};
        }
        if (this.mEnablePureScrollMode && !obtainStyledAttributes.hasValue(R.styleable.SmartRefreshLayout_srlEnableOverScrollDrag)) {
            this.mEnableOverScrollDrag = true;
        }
        if (this.mEnableOverScrollDrag && !this.mManualLoadMore && !this.mEnableLoadMore) {
            this.mEnableLoadMore = true;
        }
        obtainStyledAttributes.recycle();
    }

    public static void setDefaultRefreshFooterCreator(@NonNull DefaultRefreshFooterCreator defaultRefreshFooterCreator) {
        sFooterCreator = defaultRefreshFooterCreator;
    }

    public static void setDefaultRefreshHeaderCreator(@NonNull DefaultRefreshHeaderCreator defaultRefreshHeaderCreator) {
        sHeaderCreator = defaultRefreshHeaderCreator;
    }

    public static void setDefaultRefreshInitializer(@NonNull DefaultRefreshInitializer defaultRefreshInitializer) {
        sRefreshInitializer = defaultRefreshInitializer;
    }

    /* access modifiers changed from: protected */
    public ValueAnimator animSpinner(int i, int i2, Interpolator interpolator, int i3) {
        if (this.mSpinner == i) {
            return null;
        }
        if (this.reboundAnimator != null) {
            this.reboundAnimator.cancel();
        }
        this.animationRunnable = null;
        this.reboundAnimator = ValueAnimator.ofInt(new int[]{this.mSpinner, i});
        this.reboundAnimator.setDuration((long) i3);
        this.reboundAnimator.setInterpolator(interpolator);
        this.reboundAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                SmartRefreshLayout.this.reboundAnimator = null;
                if (SmartRefreshLayout.this.mSpinner == 0 && SmartRefreshLayout.this.mState != RefreshState.None && !SmartRefreshLayout.this.mState.isOpening) {
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
                } else if (SmartRefreshLayout.this.mState != SmartRefreshLayout.this.mViceState) {
                    SmartRefreshLayout.this.setViceState(SmartRefreshLayout.this.mState);
                }
            }
        });
        this.reboundAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                SmartRefreshLayout.this.mKernel.moveSpinner(((Integer) valueAnimator.getAnimatedValue()).intValue(), false);
            }
        });
        this.reboundAnimator.setStartDelay((long) i2);
        this.reboundAnimator.start();
        return this.reboundAnimator;
    }

    /* access modifiers changed from: protected */
    public void animSpinnerBounce(float f) {
        if (this.reboundAnimator != null) {
            return;
        }
        if (f > CropImageView.DEFAULT_ASPECT_RATIO && (this.mState == RefreshState.Refreshing || this.mState == RefreshState.TwoLevel)) {
            this.animationRunnable = new BounceRunnable(f, this.mHeaderHeight);
        } else if (f < CropImageView.DEFAULT_ASPECT_RATIO && (this.mState == RefreshState.Loading || ((this.mEnableFooterFollowWhenNoMoreData && this.mFooterNoMoreData && isEnableRefreshOrLoadMore(this.mEnableLoadMore)) || (this.mEnableAutoLoadMore && !this.mFooterNoMoreData && isEnableRefreshOrLoadMore(this.mEnableLoadMore) && this.mState != RefreshState.Refreshing)))) {
            this.animationRunnable = new BounceRunnable(f, -this.mFooterHeight);
        } else if (this.mSpinner == 0 && this.mEnableOverScrollBounce) {
            this.animationRunnable = new BounceRunnable(f, 0);
        }
    }

    public boolean autoLoadMore() {
        return autoLoadMore(0, this.mReboundDuration, ((((float) this.mFooterHeight) * ((this.mFooterMaxDragRate / 2.0f) + 0.5f)) * 1.0f) / ((float) (this.mFooterHeight == 0 ? 1 : this.mFooterHeight)), false);
    }

    @Deprecated
    public boolean autoLoadMore(int i) {
        return autoLoadMore(i, this.mReboundDuration, ((((float) this.mFooterHeight) * ((this.mFooterMaxDragRate / 2.0f) + 0.5f)) * 1.0f) / ((float) (this.mFooterHeight == 0 ? 1 : this.mFooterHeight)), false);
    }

    public boolean autoLoadMore(int i, final int i2, final float f, final boolean z) {
        if (this.mState != RefreshState.None || !isEnableRefreshOrLoadMore(this.mEnableLoadMore) || this.mFooterNoMoreData) {
            return false;
        }
        AnonymousClass9 r0 = new Runnable() {
            public void run() {
                if (SmartRefreshLayout.this.reboundAnimator != null) {
                    SmartRefreshLayout.this.reboundAnimator.cancel();
                }
                SmartRefreshLayout.this.reboundAnimator = ValueAnimator.ofInt(new int[]{SmartRefreshLayout.this.mSpinner, -((int) (((float) SmartRefreshLayout.this.mFooterHeight) * f))});
                SmartRefreshLayout.this.reboundAnimator.setDuration((long) i2);
                SmartRefreshLayout.this.reboundAnimator.setInterpolator(new DecelerateInterpolator());
                SmartRefreshLayout.this.reboundAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        SmartRefreshLayout.this.mKernel.moveSpinner(((Integer) valueAnimator.getAnimatedValue()).intValue(), true);
                    }
                });
                SmartRefreshLayout.this.reboundAnimator.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        SmartRefreshLayout.this.reboundAnimator = null;
                        if (z) {
                            if (SmartRefreshLayout.this.mState == RefreshState.ReleaseToLoad) {
                                SmartRefreshLayout.this.mKernel.setState(RefreshState.PullUpToLoad);
                            }
                        } else if (SmartRefreshLayout.this.mState != RefreshState.ReleaseToLoad) {
                            SmartRefreshLayout.this.mKernel.setState(RefreshState.ReleaseToLoad);
                        }
                        if (SmartRefreshLayout.this.mEnableAutoLoadMore) {
                            SmartRefreshLayout.this.mEnableAutoLoadMore = false;
                            SmartRefreshLayout.this.overSpinner();
                            SmartRefreshLayout.this.mEnableAutoLoadMore = true;
                            return;
                        }
                        SmartRefreshLayout.this.overSpinner();
                    }

                    public void onAnimationStart(Animator animator) {
                        SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                        SmartRefreshLayout.this.mLastTouchX = (float) (smartRefreshLayout.getMeasuredWidth() / 2);
                        SmartRefreshLayout.this.mKernel.setState(RefreshState.PullUpToLoad);
                    }
                });
                SmartRefreshLayout.this.reboundAnimator.start();
            }
        };
        if (i > 0) {
            postDelayed(r0, (long) i);
        } else {
            r0.run();
        }
        return true;
    }

    public boolean autoLoadMoreAnimationOnly() {
        return autoLoadMore(0, this.mReboundDuration, ((((float) this.mFooterHeight) * ((this.mFooterMaxDragRate / 2.0f) + 0.5f)) * 1.0f) / ((float) (this.mFooterHeight == 0 ? 1 : this.mFooterHeight)), true);
    }

    public boolean autoRefresh() {
        return autoRefresh(this.mHandler == null ? 400 : 0, this.mReboundDuration, ((((this.mHeaderMaxDragRate / 2.0f) + 0.5f) * ((float) this.mHeaderHeight)) * 1.0f) / ((float) (this.mHeaderHeight == 0 ? 1 : this.mHeaderHeight)), false);
    }

    @Deprecated
    public boolean autoRefresh(int i) {
        return autoRefresh(i, this.mReboundDuration, ((((this.mHeaderMaxDragRate / 2.0f) + 0.5f) * ((float) this.mHeaderHeight)) * 1.0f) / ((float) (this.mHeaderHeight == 0 ? 1 : this.mHeaderHeight)), false);
    }

    public boolean autoRefresh(int i, final int i2, final float f, final boolean z) {
        if (this.mState != RefreshState.None || !isEnableRefreshOrLoadMore(this.mEnableRefresh)) {
            return false;
        }
        AnonymousClass8 r0 = new Runnable() {
            public void run() {
                if (SmartRefreshLayout.this.reboundAnimator != null) {
                    SmartRefreshLayout.this.reboundAnimator.cancel();
                }
                SmartRefreshLayout.this.reboundAnimator = ValueAnimator.ofInt(new int[]{SmartRefreshLayout.this.mSpinner, (int) (((float) SmartRefreshLayout.this.mHeaderHeight) * f)});
                SmartRefreshLayout.this.reboundAnimator.setDuration((long) i2);
                SmartRefreshLayout.this.reboundAnimator.setInterpolator(new DecelerateInterpolator());
                SmartRefreshLayout.this.reboundAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        SmartRefreshLayout.this.mKernel.moveSpinner(((Integer) valueAnimator.getAnimatedValue()).intValue(), true);
                    }
                });
                SmartRefreshLayout.this.reboundAnimator.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        SmartRefreshLayout.this.reboundAnimator = null;
                        if (z) {
                            if (SmartRefreshLayout.this.mState == RefreshState.ReleaseToRefresh) {
                                SmartRefreshLayout.this.mKernel.setState(RefreshState.PullDownToRefresh);
                            }
                        } else if (SmartRefreshLayout.this.mState != RefreshState.ReleaseToRefresh) {
                            SmartRefreshLayout.this.mKernel.setState(RefreshState.ReleaseToRefresh);
                        }
                        SmartRefreshLayout.this.overSpinner();
                    }

                    public void onAnimationStart(Animator animator) {
                        SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                        SmartRefreshLayout.this.mLastTouchX = (float) (smartRefreshLayout.getMeasuredWidth() / 2);
                        SmartRefreshLayout.this.mKernel.setState(RefreshState.PullDownToRefresh);
                    }
                });
                SmartRefreshLayout.this.reboundAnimator.start();
            }
        };
        if (i > 0) {
            postDelayed(r0, (long) i);
        } else {
            r0.run();
        }
        return true;
    }

    public boolean autoRefreshAnimationOnly() {
        return autoRefresh(this.mHandler == null ? 400 : 0, this.mReboundDuration, ((((this.mHeaderMaxDragRate / 2.0f) + 0.5f) * ((float) this.mHeaderHeight)) * 1.0f) / ((float) (this.mHeaderHeight == 0 ? 1 : this.mHeaderHeight)), true);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public RefreshLayout closeHeaderOrFooter() {
        if (this.mState == RefreshState.Refreshing) {
            finishRefresh();
        } else if (this.mState == RefreshState.Loading) {
            finishLoadMore();
        } else if (this.mSpinner != 0) {
            animSpinner(0, 0, this.mReboundInterpolator, this.mReboundDuration);
        }
        return this;
    }

    public void computeScroll() {
        this.mScroller.getCurrY();
        if (this.mScroller.computeScrollOffset()) {
            int finalY = this.mScroller.getFinalY();
            if ((finalY >= 0 || !this.mEnableRefresh || !this.mRefreshContent.canRefresh()) && (finalY <= 0 || !this.mEnableLoadMore || !this.mRefreshContent.canLoadMore())) {
                this.mVerticalPermit = true;
                invalidate();
                return;
            }
            if (this.mVerticalPermit) {
                animSpinnerBounce(Build.VERSION.SDK_INT >= 14 ? finalY > 0 ? -this.mScroller.getCurrVelocity() : this.mScroller.getCurrVelocity() : (((float) (this.mScroller.getCurrY() - finalY)) * 1.0f) / ((float) Math.max(this.mScroller.getDuration() - this.mScroller.timePassed(), 1)));
            }
            this.mScroller.forceFinished(true);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:126:0x01a9  */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x02b5  */
    /* JADX WARNING: Removed duplicated region for block: B:195:0x02ca  */
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        float f;
        int i;
        int actionMasked = motionEvent.getActionMasked();
        boolean z = actionMasked == 6;
        int actionIndex = z ? motionEvent.getActionIndex() : -1;
        int pointerCount = motionEvent.getPointerCount();
        float f2 = CropImageView.DEFAULT_ASPECT_RATIO;
        float f3 = CropImageView.DEFAULT_ASPECT_RATIO;
        for (int i2 = 0; i2 < pointerCount; i2++) {
            if (actionIndex != i2) {
                f2 += motionEvent.getX(i2);
                f3 += motionEvent.getY(i2);
            }
        }
        float f4 = (float) (z ? pointerCount - 1 : pointerCount);
        float f5 = f2 / f4;
        float f6 = f3 / f4;
        if ((actionMasked == 6 || actionMasked == 5) && this.mIsBeingDragged) {
            this.mTouchY += f6 - this.mLastTouchY;
        }
        this.mLastTouchX = f5;
        this.mLastTouchY = f6;
        if (this.mNestedInProgress) {
            int i3 = this.mTotalUnconsumed;
            boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
            if (actionMasked == 2 && i3 == this.mTotalUnconsumed) {
                int i4 = (int) this.mLastTouchX;
                int width = getWidth();
                float f7 = this.mLastTouchX / ((float) (width == 0 ? 1 : width));
                if (isEnableRefreshOrLoadMore(this.mEnableRefresh) && this.mSpinner > 0 && this.mRefreshHeader != null && this.mRefreshHeader.isSupportHorizontalDrag()) {
                    this.mRefreshHeader.onHorizontalDrag(f7, i4, width);
                    return dispatchTouchEvent;
                } else if (isEnableRefreshOrLoadMore(this.mEnableLoadMore) && this.mSpinner < 0 && this.mRefreshFooter != null && this.mRefreshFooter.isSupportHorizontalDrag()) {
                    this.mRefreshFooter.onHorizontalDrag(f7, i4, width);
                }
            }
            return dispatchTouchEvent;
        } else if (!isEnabled() || ((!this.mEnableRefresh && !this.mEnableLoadMore) || ((this.mHeaderNeedTouchEventWhenRefreshing && ((this.mState.isOpening || this.mState.isFinishing) && this.mState.isHeader)) || (this.mFooterNeedTouchEventWhenLoading && ((this.mState.isOpening || this.mState.isFinishing) && this.mState.isFooter))))) {
            return super.dispatchTouchEvent(motionEvent);
        } else {
            if (interceptAnimatorByAction(actionMasked) || this.mState.isFinishing || (this.mState == RefreshState.Loading && this.mDisableContentWhenLoading)) {
                return false;
            }
            if (this.mState == RefreshState.Refreshing && this.mDisableContentWhenRefresh) {
                return false;
            }
            switch (actionMasked) {
                case 0:
                    this.mCurrentVelocity = 0;
                    this.mVelocityTracker.addMovement(motionEvent);
                    this.mScroller.forceFinished(true);
                    this.mTouchX = f5;
                    this.mTouchY = f6;
                    this.mLastSpinner = 0;
                    this.mTouchSpinner = this.mSpinner;
                    this.mIsBeingDragged = false;
                    this.mSuperDispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
                    if (this.mState != RefreshState.TwoLevel || this.mTouchY >= ((float) ((getMeasuredHeight() * 5) / 6))) {
                        if (this.mRefreshContent != null) {
                            this.mRefreshContent.onActionDown(motionEvent);
                        }
                        return true;
                    }
                    this.mDragDirection = 'h';
                    return this.mSuperDispatchTouchEvent;
                case 1:
                    this.mVelocityTracker.addMovement(motionEvent);
                    this.mVelocityTracker.computeCurrentVelocity(SocializeConstants.CANCLE_RESULTCODE, (float) this.mMaximumVelocity);
                    this.mCurrentVelocity = (int) this.mVelocityTracker.getYVelocity();
                    startFlingIfNeed((Float) null);
                    break;
                case 2:
                    float f8 = f5 - this.mTouchX;
                    float f9 = f6 - this.mTouchY;
                    this.mVelocityTracker.addMovement(motionEvent);
                    if (!(this.mIsBeingDragged || this.mDragDirection == 'h' || this.mRefreshContent == null)) {
                        if (this.mDragDirection == 'v' || (Math.abs(f9) >= ((float) this.mTouchSlop) && Math.abs(f8) < Math.abs(f9))) {
                            this.mDragDirection = 'v';
                            if (f9 > CropImageView.DEFAULT_ASPECT_RATIO && (this.mSpinner < 0 || (this.mEnableRefresh && this.mRefreshContent.canRefresh()))) {
                                this.mIsBeingDragged = true;
                                this.mTouchY = f6 - ((float) this.mTouchSlop);
                            } else if (f9 < CropImageView.DEFAULT_ASPECT_RATIO && (this.mSpinner > 0 || (this.mEnableLoadMore && ((this.mState == RefreshState.Loading && this.mFooterLocked) || this.mRefreshContent.canLoadMore())))) {
                                this.mIsBeingDragged = true;
                                this.mTouchY = ((float) this.mTouchSlop) + f6;
                            }
                            if (this.mIsBeingDragged) {
                                float f10 = f6 - this.mTouchY;
                                if (this.mSuperDispatchTouchEvent) {
                                    motionEvent.setAction(3);
                                    super.dispatchTouchEvent(motionEvent);
                                }
                                this.mKernel.setState((this.mSpinner > 0 || (this.mSpinner == 0 && f10 > CropImageView.DEFAULT_ASPECT_RATIO)) ? RefreshState.PullDownToRefresh : RefreshState.PullUpToLoad);
                                ViewParent parent = getParent();
                                if (parent != null) {
                                    parent.requestDisallowInterceptTouchEvent(true);
                                    f = f10;
                                } else {
                                    f = f10;
                                }
                                if (!this.mIsBeingDragged) {
                                    if (this.mFooterLocked && f > ((float) this.mTouchSlop) && this.mSpinner < 0) {
                                        this.mFooterLocked = false;
                                        break;
                                    }
                                } else {
                                    int i5 = ((int) f) + this.mTouchSpinner;
                                    if ((this.mViceState.isHeader && (i5 < 0 || this.mLastSpinner < 0)) || (this.mViceState.isFooter && (i5 > 0 || this.mLastSpinner > 0))) {
                                        this.mLastSpinner = i5;
                                        long eventTime = motionEvent.getEventTime();
                                        if (this.mFalsifyEvent == null) {
                                            this.mFalsifyEvent = MotionEvent.obtain(eventTime, eventTime, 0, this.mTouchX + f8, this.mTouchY, 0);
                                            super.dispatchTouchEvent(this.mFalsifyEvent);
                                        }
                                        MotionEvent obtain = MotionEvent.obtain(eventTime, eventTime, 2, this.mTouchX + f8, this.mTouchY + ((float) i5), 0);
                                        super.dispatchTouchEvent(obtain);
                                        if (this.mFooterLocked && f > ((float) this.mTouchSlop) && this.mSpinner < 0) {
                                            this.mFooterLocked = false;
                                        }
                                        if (i5 > 0 && this.mEnableRefresh && this.mRefreshContent.canRefresh()) {
                                            this.mLastTouchY = f6;
                                            this.mTouchY = f6;
                                            this.mTouchSpinner = 0;
                                            this.mKernel.setState(RefreshState.PullDownToRefresh);
                                        } else if (i5 >= 0 || !this.mEnableLoadMore || !this.mRefreshContent.canLoadMore()) {
                                            i = i5;
                                            if ((this.mViceState.isHeader || i >= 0) && (!this.mViceState.isFooter || i <= 0)) {
                                                if (this.mFalsifyEvent != null) {
                                                    this.mFalsifyEvent = null;
                                                    obtain.setAction(3);
                                                    super.dispatchTouchEvent(obtain);
                                                }
                                                obtain.recycle();
                                                i5 = i;
                                            } else {
                                                if (this.mSpinner != 0) {
                                                    moveSpinnerInfinitely(CropImageView.DEFAULT_ASPECT_RATIO);
                                                }
                                                return true;
                                            }
                                        } else {
                                            this.mLastTouchY = f6;
                                            this.mTouchY = f6;
                                            this.mTouchSpinner = 0;
                                            this.mKernel.setState(RefreshState.PullUpToLoad);
                                        }
                                        i = 0;
                                        if (this.mViceState.isHeader) {
                                        }
                                        if (this.mFalsifyEvent != null) {
                                        }
                                        obtain.recycle();
                                        i5 = i;
                                    }
                                    moveSpinnerInfinitely((float) i5);
                                    return true;
                                }
                            }
                        } else if (Math.abs(f8) >= ((float) this.mTouchSlop) && Math.abs(f8) > Math.abs(f9) && this.mDragDirection != 'v') {
                            this.mDragDirection = 'h';
                            f = f9;
                            if (!this.mIsBeingDragged) {
                            }
                        }
                    }
                    f = f9;
                    if (!this.mIsBeingDragged) {
                    }
                    break;
                case 3:
                    break;
            }
            this.mVelocityTracker.clear();
            this.mDragDirection = 'n';
            if (this.mFalsifyEvent != null) {
                this.mFalsifyEvent.recycle();
                this.mFalsifyEvent = null;
                long eventTime2 = motionEvent.getEventTime();
                MotionEvent obtain2 = MotionEvent.obtain(eventTime2, eventTime2, actionMasked, this.mTouchX, f6, 0);
                super.dispatchTouchEvent(obtain2);
                obtain2.recycle();
            }
            overSpinner();
            if (this.mIsBeingDragged) {
                this.mIsBeingDragged = false;
                return true;
            }
            return super.dispatchTouchEvent(motionEvent);
        }
    }

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View view, long j) {
        View view2 = this.mRefreshContent != null ? this.mRefreshContent.getView() : null;
        if (this.mRefreshHeader != null && this.mRefreshHeader.getView() == view) {
            if (!isEnableRefreshOrLoadMore(this.mEnableRefresh)) {
                return true;
            }
            if (!this.mEnablePreviewInEditMode && isInEditMode()) {
                return true;
            }
            if (view2 != null) {
                int max = Math.max(view2.getTop() + view2.getPaddingTop() + this.mSpinner, view.getTop());
                if (!(this.mHeaderBackgroundColor == 0 || this.mPaint == null)) {
                    this.mPaint.setColor(this.mHeaderBackgroundColor);
                    int bottom = this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.Scale ? view.getBottom() : this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.Translate ? view.getBottom() + this.mSpinner : max;
                    canvas.drawRect((float) view.getLeft(), (float) view.getTop(), (float) view.getRight(), (float) bottom, this.mPaint);
                    max = bottom;
                }
                if (this.mEnableClipHeaderWhenFixedBehind && this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.FixedBehind) {
                    canvas.save();
                    canvas.clipRect(view.getLeft(), view.getTop(), view.getRight(), max);
                    boolean drawChild = super.drawChild(canvas, view, j);
                    canvas.restore();
                    return drawChild;
                }
            }
        }
        if (this.mRefreshFooter != null && this.mRefreshFooter.getView() == view) {
            if (!isEnableRefreshOrLoadMore(this.mEnableLoadMore)) {
                return true;
            }
            if (!this.mEnablePreviewInEditMode && isInEditMode()) {
                return true;
            }
            if (view2 != null) {
                int min = Math.min((view2.getBottom() - view2.getPaddingBottom()) + this.mSpinner, view.getBottom());
                if (!(this.mFooterBackgroundColor == 0 || this.mPaint == null)) {
                    this.mPaint.setColor(this.mFooterBackgroundColor);
                    int top2 = this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.Scale ? view.getTop() : this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.Translate ? view.getTop() + this.mSpinner : min;
                    canvas.drawRect((float) view.getLeft(), (float) top2, (float) view.getRight(), (float) view.getBottom(), this.mPaint);
                    min = top2;
                }
                if (this.mEnableClipFooterWhenFixedBehind && this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.FixedBehind) {
                    canvas.save();
                    canvas.clipRect(view.getLeft(), min, view.getRight(), view.getBottom());
                    boolean drawChild2 = super.drawChild(canvas, view, j);
                    canvas.restore();
                    return drawChild2;
                }
            }
        }
        return super.drawChild(canvas, view, j);
    }

    public SmartRefreshLayout finishLoadMore() {
        return finishLoadMore(Math.min(Math.max(0, 300 - ((int) (System.currentTimeMillis() - this.mLastOpenTime))), 300));
    }

    public SmartRefreshLayout finishLoadMore(int i) {
        return finishLoadMore(i, true, false);
    }

    public SmartRefreshLayout finishLoadMore(int i, final boolean z, final boolean z2) {
        postDelayed(new Runnable() {
            public void run() {
                if (SmartRefreshLayout.this.mState == RefreshState.Loading && SmartRefreshLayout.this.mRefreshFooter != null && SmartRefreshLayout.this.mRefreshContent != null) {
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.LoadFinish);
                    int onFinish = SmartRefreshLayout.this.mRefreshFooter.onFinish(SmartRefreshLayout.this, z);
                    if (SmartRefreshLayout.this.mOnMultiPurposeListener != null && (SmartRefreshLayout.this.mRefreshFooter instanceof RefreshFooter)) {
                        SmartRefreshLayout.this.mOnMultiPurposeListener.onFooterFinish((RefreshFooter) SmartRefreshLayout.this.mRefreshFooter, z);
                    }
                    if (onFinish < Integer.MAX_VALUE) {
                        final int max = SmartRefreshLayout.this.mSpinner - (z2 && SmartRefreshLayout.this.mEnableFooterFollowWhenNoMoreData && SmartRefreshLayout.this.mSpinner < 0 && SmartRefreshLayout.this.mRefreshContent.canLoadMore() ? Math.max(SmartRefreshLayout.this.mSpinner, -SmartRefreshLayout.this.mFooterHeight) : 0);
                        if (SmartRefreshLayout.this.mIsBeingDragged || SmartRefreshLayout.this.mNestedInProgress) {
                            if (SmartRefreshLayout.this.mIsBeingDragged) {
                                SmartRefreshLayout.this.mTouchY = SmartRefreshLayout.this.mLastTouchY;
                                SmartRefreshLayout.this.mIsBeingDragged = false;
                                SmartRefreshLayout.this.mTouchSpinner = SmartRefreshLayout.this.mSpinner - max;
                            }
                            long currentTimeMillis = System.currentTimeMillis();
                            float f = (float) max;
                            boolean unused = SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(currentTimeMillis, currentTimeMillis, 0, SmartRefreshLayout.this.mLastTouchX, SmartRefreshLayout.this.mLastTouchY + f + ((float) (SmartRefreshLayout.this.mTouchSlop * 2)), 0));
                            boolean unused2 = SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(currentTimeMillis, currentTimeMillis, 2, SmartRefreshLayout.this.mLastTouchX, SmartRefreshLayout.this.mLastTouchY + f, 0));
                            if (SmartRefreshLayout.this.mNestedInProgress) {
                                SmartRefreshLayout.this.mTotalUnconsumed = 0;
                            }
                        }
                        SmartRefreshLayout.this.postDelayed(new Runnable() {
                            public void run() {
                                ValueAnimator valueAnimator;
                                ValueAnimator.AnimatorUpdateListener scrollContentWhenFinished = (!SmartRefreshLayout.this.mEnableScrollContentWhenLoaded || max >= 0) ? null : SmartRefreshLayout.this.mRefreshContent.scrollContentWhenFinished(SmartRefreshLayout.this.mSpinner);
                                if (scrollContentWhenFinished != null) {
                                    scrollContentWhenFinished.onAnimationUpdate(ValueAnimator.ofInt(new int[]{0, 0}));
                                }
                                AnonymousClass1 r2 = new AnimatorListenerAdapter() {
                                    public void onAnimationEnd(Animator animator) {
                                        SmartRefreshLayout.this.mFooterLocked = false;
                                        if (z2) {
                                            SmartRefreshLayout.this.setNoMoreData(true);
                                        }
                                        if (SmartRefreshLayout.this.mState == RefreshState.LoadFinish) {
                                            SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
                                        }
                                    }
                                };
                                if (SmartRefreshLayout.this.mSpinner > 0) {
                                    valueAnimator = SmartRefreshLayout.this.mKernel.animSpinner(0);
                                } else {
                                    if (scrollContentWhenFinished != null || SmartRefreshLayout.this.mSpinner == 0) {
                                        if (SmartRefreshLayout.this.reboundAnimator != null) {
                                            SmartRefreshLayout.this.reboundAnimator.cancel();
                                            SmartRefreshLayout.this.reboundAnimator = null;
                                        }
                                        SmartRefreshLayout.this.mKernel.moveSpinner(0, false);
                                        SmartRefreshLayout.this.resetStatus();
                                    } else if (!z2 || !SmartRefreshLayout.this.mEnableFooterFollowWhenNoMoreData) {
                                        valueAnimator = SmartRefreshLayout.this.mKernel.animSpinner(0);
                                    } else if (SmartRefreshLayout.this.mSpinner >= (-SmartRefreshLayout.this.mFooterHeight)) {
                                        SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
                                    } else {
                                        valueAnimator = SmartRefreshLayout.this.mKernel.animSpinner(-SmartRefreshLayout.this.mFooterHeight);
                                    }
                                    valueAnimator = null;
                                }
                                if (valueAnimator != null) {
                                    valueAnimator.addListener(r2);
                                } else {
                                    r2.onAnimationEnd((Animator) null);
                                }
                            }
                        }, SmartRefreshLayout.this.mSpinner < 0 ? (long) onFinish : 0);
                    }
                } else if (z2) {
                    SmartRefreshLayout.this.setNoMoreData(true);
                }
            }
        }, i <= 0 ? 1 : (long) i);
        return this;
    }

    public SmartRefreshLayout finishLoadMore(boolean z) {
        return finishLoadMore(z ? Math.min(Math.max(0, 300 - ((int) (System.currentTimeMillis() - this.mLastOpenTime))), 300) : 0, z, false);
    }

    public SmartRefreshLayout finishLoadMoreWithNoMoreData() {
        return finishLoadMore(Math.min(Math.max(0, 300 - ((int) (System.currentTimeMillis() - this.mLastOpenTime))), 300), true, true);
    }

    public SmartRefreshLayout finishRefresh() {
        return finishRefresh(Math.min(Math.max(0, 300 - ((int) (System.currentTimeMillis() - this.mLastOpenTime))), 300));
    }

    public SmartRefreshLayout finishRefresh(int i) {
        return finishRefresh(i, true);
    }

    public SmartRefreshLayout finishRefresh(int i, final boolean z) {
        if (this.mState == RefreshState.Refreshing && z) {
            resetNoMoreData();
        }
        postDelayed(new Runnable() {
            public void run() {
                if (SmartRefreshLayout.this.mState == RefreshState.Refreshing && SmartRefreshLayout.this.mRefreshHeader != null && SmartRefreshLayout.this.mRefreshContent != null) {
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.RefreshFinish);
                    int onFinish = SmartRefreshLayout.this.mRefreshHeader.onFinish(SmartRefreshLayout.this, z);
                    if (SmartRefreshLayout.this.mOnMultiPurposeListener != null && (SmartRefreshLayout.this.mRefreshHeader instanceof RefreshHeader)) {
                        SmartRefreshLayout.this.mOnMultiPurposeListener.onHeaderFinish((RefreshHeader) SmartRefreshLayout.this.mRefreshHeader, z);
                    }
                    if (onFinish < Integer.MAX_VALUE) {
                        if (SmartRefreshLayout.this.mIsBeingDragged || SmartRefreshLayout.this.mNestedInProgress) {
                            if (SmartRefreshLayout.this.mIsBeingDragged) {
                                SmartRefreshLayout.this.mTouchY = SmartRefreshLayout.this.mLastTouchY;
                                SmartRefreshLayout.this.mTouchSpinner = 0;
                                SmartRefreshLayout.this.mIsBeingDragged = false;
                            }
                            long currentTimeMillis = System.currentTimeMillis();
                            boolean unused = SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(currentTimeMillis, currentTimeMillis, 0, SmartRefreshLayout.this.mLastTouchX, (SmartRefreshLayout.this.mLastTouchY + ((float) SmartRefreshLayout.this.mSpinner)) - ((float) (SmartRefreshLayout.this.mTouchSlop * 2)), 0));
                            boolean unused2 = SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(currentTimeMillis, currentTimeMillis, 2, SmartRefreshLayout.this.mLastTouchX, SmartRefreshLayout.this.mLastTouchY + ((float) SmartRefreshLayout.this.mSpinner), 0));
                            if (SmartRefreshLayout.this.mNestedInProgress) {
                                SmartRefreshLayout.this.mTotalUnconsumed = 0;
                            }
                        }
                        if (SmartRefreshLayout.this.mSpinner > 0) {
                            ValueAnimator.AnimatorUpdateListener animatorUpdateListener = null;
                            ValueAnimator animSpinner = SmartRefreshLayout.this.animSpinner(0, onFinish, SmartRefreshLayout.this.mReboundInterpolator, SmartRefreshLayout.this.mReboundDuration);
                            if (SmartRefreshLayout.this.mEnableScrollContentWhenRefreshed) {
                                animatorUpdateListener = SmartRefreshLayout.this.mRefreshContent.scrollContentWhenFinished(SmartRefreshLayout.this.mSpinner);
                            }
                            if (animSpinner != null && animatorUpdateListener != null) {
                                animSpinner.addUpdateListener(animatorUpdateListener);
                            }
                        } else if (SmartRefreshLayout.this.mSpinner < 0) {
                            SmartRefreshLayout.this.animSpinner(0, onFinish, SmartRefreshLayout.this.mReboundInterpolator, SmartRefreshLayout.this.mReboundDuration);
                        } else {
                            SmartRefreshLayout.this.mKernel.moveSpinner(0, false);
                            SmartRefreshLayout.this.resetStatus();
                        }
                    }
                }
            }
        }, i <= 0 ? 1 : (long) i);
        return this;
    }

    public SmartRefreshLayout finishRefresh(boolean z) {
        int i = 0;
        long currentTimeMillis = System.currentTimeMillis();
        long j = this.mLastOpenTime;
        if (z) {
            i = Math.min(Math.max(0, 300 - ((int) (currentTimeMillis - j))), 300);
        }
        return finishRefresh(i, z);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    @NonNull
    public SmartRefreshLayout getLayout() {
        return this;
    }

    public int getNestedScrollAxes() {
        return this.mNestedParent.getNestedScrollAxes();
    }

    @Nullable
    public RefreshFooter getRefreshFooter() {
        if (this.mRefreshFooter instanceof RefreshFooter) {
            return (RefreshFooter) this.mRefreshFooter;
        }
        return null;
    }

    @Nullable
    public RefreshHeader getRefreshHeader() {
        if (this.mRefreshHeader instanceof RefreshHeader) {
            return (RefreshHeader) this.mRefreshHeader;
        }
        return null;
    }

    @NonNull
    public RefreshState getState() {
        return this.mState;
    }

    /* access modifiers changed from: protected */
    public boolean interceptAnimatorByAction(int i) {
        if (i == 0) {
            if (this.reboundAnimator != null) {
                if (this.mState.isFinishing || this.mState == RefreshState.TwoLevelReleased) {
                    return true;
                }
                if (this.mState == RefreshState.PullDownCanceled) {
                    this.mKernel.setState(RefreshState.PullDownToRefresh);
                } else if (this.mState == RefreshState.PullUpCanceled) {
                    this.mKernel.setState(RefreshState.PullUpToLoad);
                }
                this.reboundAnimator.cancel();
                this.reboundAnimator = null;
            }
            this.animationRunnable = null;
        }
        return this.reboundAnimator != null;
    }

    /* access modifiers changed from: protected */
    public boolean isEnableRefreshOrLoadMore(boolean z) {
        return z && !this.mEnablePureScrollMode;
    }

    /* access modifiers changed from: protected */
    public boolean isEnableTranslationContent(boolean z, RefreshInternal refreshInternal) {
        return z || this.mEnablePureScrollMode || refreshInternal == null || refreshInternal.getSpinnerStyle() == SpinnerStyle.FixedBehind;
    }

    public boolean isNestedScrollingEnabled() {
        return this.mNestedChild.isNestedScrollingEnabled();
    }

    /* access modifiers changed from: protected */
    public void moveSpinnerInfinitely(float f) {
        if (this.mNestedInProgress && !this.mEnableLoadMoreWhenContentNotFull && f < CropImageView.DEFAULT_ASPECT_RATIO && !this.mRefreshContent.canLoadMore()) {
            f = CropImageView.DEFAULT_ASPECT_RATIO;
        }
        if (this.mState == RefreshState.TwoLevel && f > CropImageView.DEFAULT_ASPECT_RATIO) {
            this.mKernel.moveSpinner(Math.min((int) f, getMeasuredHeight()), true);
        } else if (this.mState != RefreshState.Refreshing || f < CropImageView.DEFAULT_ASPECT_RATIO) {
            if (f >= CropImageView.DEFAULT_ASPECT_RATIO || (this.mState != RefreshState.Loading && ((!this.mEnableFooterFollowWhenNoMoreData || !this.mFooterNoMoreData || !isEnableRefreshOrLoadMore(this.mEnableLoadMore)) && (!this.mEnableAutoLoadMore || this.mFooterNoMoreData || !isEnableRefreshOrLoadMore(this.mEnableLoadMore))))) {
                if (f >= CropImageView.DEFAULT_ASPECT_RATIO) {
                    double d = (double) (this.mHeaderMaxDragRate * ((float) this.mHeaderHeight));
                    double max = (double) Math.max(this.mScreenHeightPixels / 2, getHeight());
                    double max2 = (double) Math.max(CropImageView.DEFAULT_ASPECT_RATIO, this.mDragRate * f);
                    Double.isNaN(max2);
                    double d2 = -max2;
                    if (max == 0.0d) {
                        max = 1.0d;
                    }
                    double pow = Math.pow(100.0d, d2 / max);
                    Double.isNaN(d);
                    this.mKernel.moveSpinner((int) Math.min((1.0d - pow) * d, max2), true);
                } else {
                    double d3 = (double) (this.mFooterMaxDragRate * ((float) this.mFooterHeight));
                    double max3 = (double) Math.max(this.mScreenHeightPixels / 2, getHeight());
                    double d4 = (double) (-Math.min(CropImageView.DEFAULT_ASPECT_RATIO, this.mDragRate * f));
                    Double.isNaN(d4);
                    double d5 = -d4;
                    if (max3 == 0.0d) {
                        max3 = 1.0d;
                    }
                    double pow2 = Math.pow(100.0d, d5 / max3);
                    Double.isNaN(d3);
                    this.mKernel.moveSpinner((int) (-Math.min((1.0d - pow2) * d3, d4)), true);
                }
            } else if (f > ((float) (-this.mFooterHeight))) {
                this.mKernel.moveSpinner((int) f, true);
            } else {
                double d6 = (double) ((this.mFooterMaxDragRate - 1.0f) * ((float) this.mFooterHeight));
                double max4 = (double) (Math.max((this.mScreenHeightPixels * 4) / 3, getHeight()) - this.mFooterHeight);
                double d7 = (double) (-Math.min(CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mFooterHeight) + f) * this.mDragRate));
                Double.isNaN(d7);
                double d8 = -d7;
                if (max4 == 0.0d) {
                    max4 = 1.0d;
                }
                double pow3 = Math.pow(100.0d, d8 / max4);
                Double.isNaN(d6);
                this.mKernel.moveSpinner(((int) (-Math.min((1.0d - pow3) * d6, d7))) - this.mFooterHeight, true);
            }
        } else if (f < ((float) this.mHeaderHeight)) {
            this.mKernel.moveSpinner((int) f, true);
        } else {
            double d9 = (double) ((this.mHeaderMaxDragRate - 1.0f) * ((float) this.mHeaderHeight));
            double max5 = (double) (Math.max((this.mScreenHeightPixels * 4) / 3, getHeight()) - this.mHeaderHeight);
            double max6 = (double) Math.max(CropImageView.DEFAULT_ASPECT_RATIO, (f - ((float) this.mHeaderHeight)) * this.mDragRate);
            Double.isNaN(max6);
            double d10 = -max6;
            if (max5 == 0.0d) {
                max5 = 1.0d;
            }
            double pow4 = Math.pow(100.0d, d10 / max5);
            Double.isNaN(d9);
            this.mKernel.moveSpinner(((int) Math.min((1.0d - pow4) * d9, max6)) + this.mHeaderHeight, true);
        }
        if (this.mEnableAutoLoadMore && !this.mFooterNoMoreData && isEnableRefreshOrLoadMore(this.mEnableLoadMore) && f < CropImageView.DEFAULT_ASPECT_RATIO && this.mState != RefreshState.Refreshing && this.mState != RefreshState.Loading && this.mState != RefreshState.LoadFinish) {
            if (this.mDisableContentWhenLoading) {
                this.animationRunnable = null;
                this.mKernel.animSpinner(-this.mFooterHeight);
            }
            setStateDirectLoading(false);
            postDelayed(new Runnable() {
                public void run() {
                    if (SmartRefreshLayout.this.mLoadMoreListener != null) {
                        SmartRefreshLayout.this.mLoadMoreListener.onLoadMore(SmartRefreshLayout.this);
                    } else if (SmartRefreshLayout.this.mOnMultiPurposeListener == null) {
                        SmartRefreshLayout.this.finishLoadMore((int) BannerConfig.TIME);
                    }
                    OnMultiPurposeListener onMultiPurposeListener = SmartRefreshLayout.this.mOnMultiPurposeListener;
                    if (onMultiPurposeListener != null) {
                        onMultiPurposeListener.onLoadMore(SmartRefreshLayout.this);
                    }
                }
            }, (long) this.mReboundDuration);
        }
    }

    /* access modifiers changed from: protected */
    public void notifyStateChanged(RefreshState refreshState) {
        RefreshState refreshState2 = this.mState;
        if (refreshState2 != refreshState) {
            this.mState = refreshState;
            this.mViceState = refreshState;
            RefreshInternal refreshInternal = this.mRefreshHeader;
            RefreshInternal refreshInternal2 = this.mRefreshFooter;
            OnMultiPurposeListener onMultiPurposeListener = this.mOnMultiPurposeListener;
            if (refreshInternal != null) {
                refreshInternal.onStateChanged(this, refreshState2, refreshState);
            }
            if (refreshInternal2 != null) {
                refreshInternal2.onStateChanged(this, refreshState2, refreshState);
            }
            if (onMultiPurposeListener != null) {
                onMultiPurposeListener.onStateChanged(this, refreshState2, refreshState);
            }
        } else if (this.mViceState != this.mState) {
            this.mViceState = this.mState;
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        View view = null;
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (this.mHandler == null) {
                this.mHandler = new Handler();
            }
            if (this.mListDelayedRunnable != null) {
                for (DelayedRunnable next : this.mListDelayedRunnable) {
                    this.mHandler.postDelayed(next, next.delayMillis);
                }
                this.mListDelayedRunnable.clear();
                this.mListDelayedRunnable = null;
            }
            if (this.mRefreshHeader == null) {
                if (sHeaderCreator != null) {
                    setRefreshHeader(sHeaderCreator.createRefreshHeader(getContext(), this));
                } else {
                    setRefreshHeader((RefreshHeader) new BezierRadarHeader(getContext()));
                }
            }
            if (this.mRefreshFooter != null) {
                this.mEnableLoadMore = this.mEnableLoadMore || !this.mManualLoadMore;
            } else if (sFooterCreator != null) {
                setRefreshFooter(sFooterCreator.createRefreshFooter(getContext(), this));
            } else {
                boolean z = this.mEnableLoadMore;
                setRefreshFooter((RefreshFooter) new BallPulseFooter(getContext()));
                this.mEnableLoadMore = z;
            }
            if (this.mRefreshContent == null) {
                int childCount = getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = getChildAt(i);
                    if ((this.mRefreshHeader == null || childAt != this.mRefreshHeader.getView()) && (this.mRefreshFooter == null || childAt != this.mRefreshFooter.getView())) {
                        this.mRefreshContent = new RefreshContentWrapper(childAt);
                    }
                }
            }
            if (this.mRefreshContent == null) {
                int dp2px = DensityUtil.dp2px(20.0f);
                TextView textView = new TextView(getContext());
                textView.setTextColor(-39424);
                textView.setGravity(17);
                textView.setTextSize(20.0f);
                textView.setText(R.string.srl_content_empty);
                super.addView(textView, -1, -1);
                this.mRefreshContent = new RefreshContentWrapper(textView);
                this.mRefreshContent.getView().setPadding(dp2px, dp2px, dp2px, dp2px);
            }
            View findViewById = this.mFixedHeaderViewId > 0 ? findViewById(this.mFixedHeaderViewId) : null;
            if (this.mFixedFooterViewId > 0) {
                view = findViewById(this.mFixedFooterViewId);
            }
            this.mRefreshContent.setScrollBoundaryDecider(this.mScrollBoundaryDecider);
            this.mRefreshContent.setEnableLoadMoreWhenContentNotFull(this.mEnableLoadMoreWhenContentNotFull);
            this.mRefreshContent.setUpComponent(this.mKernel, findViewById, view);
            if (this.mSpinner != 0) {
                notifyStateChanged(RefreshState.None);
                RefreshContent refreshContent = this.mRefreshContent;
                this.mSpinner = 0;
                refreshContent.moveSpinner(0, this.mHeaderTranslationViewId, this.mFooterTranslationViewId);
            }
        }
        if (this.mPrimaryColors != null) {
            if (this.mRefreshHeader != null) {
                this.mRefreshHeader.setPrimaryColors(this.mPrimaryColors);
            }
            if (this.mRefreshFooter != null) {
                this.mRefreshFooter.setPrimaryColors(this.mPrimaryColors);
            }
        }
        if (this.mRefreshContent != null) {
            super.bringChildToFront(this.mRefreshContent.getView());
        }
        if (!(this.mRefreshHeader == null || this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.FixedBehind)) {
            super.bringChildToFront(this.mRefreshHeader.getView());
        }
        if (this.mRefreshFooter != null && this.mRefreshFooter.getSpinnerStyle() != SpinnerStyle.FixedBehind) {
            super.bringChildToFront(this.mRefreshFooter.getView());
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mKernel.moveSpinner(0, true);
        notifyStateChanged(RefreshState.None);
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages((Object) null);
            this.mHandler = null;
        }
        if (this.mListDelayedRunnable != null) {
            this.mListDelayedRunnable.clear();
            this.mListDelayedRunnable = null;
        }
        this.mManualLoadMore = true;
        this.animationRunnable = null;
        if (this.reboundAnimator != null) {
            this.reboundAnimator.removeAllListeners();
            this.reboundAnimator.removeAllUpdateListeners();
            this.reboundAnimator.cancel();
            this.reboundAnimator = null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0048  */
    public void onFinishInflate() {
        int i;
        int i2;
        int i3;
        int i4 = 2;
        super.onFinishInflate();
        int childCount = super.getChildCount();
        if (childCount <= 3) {
            int i5 = -1;
            char c = 0;
            int i6 = 0;
            while (i6 < childCount) {
                View childAt = super.getChildAt(i6);
                if (SmartUtil.isContentView(childAt) && (c < 2 || i6 == 1)) {
                    c = 2;
                    i5 = i6;
                } else if (!(childAt instanceof RefreshInternal) && c < 1) {
                    c = i6 > 0 ? (char) 1 : 0;
                    i5 = i6;
                }
                i6++;
            }
            if (i5 >= 0) {
                this.mRefreshContent = new RefreshContentWrapper(super.getChildAt(i5));
                if (i5 == 1) {
                    if (childCount == 3) {
                        i2 = 0;
                    } else {
                        i = 0;
                        i4 = -1;
                        i2 = i;
                    }
                } else if (childCount == 2) {
                    i4 = 1;
                    i2 = -1;
                }
                for (i3 = 0; i3 < childCount; i3++) {
                    View childAt2 = super.getChildAt(i3);
                    if (i3 == i2 || (i3 != i4 && i2 == -1 && this.mRefreshHeader == null && (childAt2 instanceof RefreshHeader))) {
                        this.mRefreshHeader = childAt2 instanceof RefreshHeader ? (RefreshHeader) childAt2 : new RefreshHeaderWrapper(childAt2);
                    } else if (i3 == i4 || (i4 == -1 && (childAt2 instanceof RefreshFooter))) {
                        this.mEnableLoadMore = this.mEnableLoadMore || !this.mManualLoadMore;
                        this.mRefreshFooter = childAt2 instanceof RefreshFooter ? (RefreshFooter) childAt2 : new RefreshFooterWrapper(childAt2);
                    }
                }
                return;
            }
            i = -1;
            i4 = -1;
            i2 = i;
            while (i3 < childCount) {
            }
            return;
        }
        throw new RuntimeException("最多只支持3个子View，Most only support three sub view");
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        getPaddingBottom();
        int childCount = super.getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = super.getChildAt(i8);
            if (!(childAt.getVisibility() == 8 || childAt.getTag(R.string.srl_component_falsify) == childAt)) {
                if (this.mRefreshContent != null && this.mRefreshContent.getView() == childAt) {
                    boolean z2 = isInEditMode() && this.mEnablePreviewInEditMode && isEnableRefreshOrLoadMore(this.mEnableRefresh) && this.mRefreshHeader != null;
                    View view = this.mRefreshContent.getView();
                    LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                    int i9 = layoutParams.leftMargin + paddingLeft;
                    int i10 = layoutParams.topMargin + paddingTop;
                    int measuredWidth = view.getMeasuredWidth();
                    int measuredHeight = view.getMeasuredHeight() + i10;
                    if (!z2 || !isEnableTranslationContent(this.mEnableHeaderTranslationContent, this.mRefreshHeader)) {
                        i7 = measuredHeight;
                    } else {
                        i10 += this.mHeaderHeight;
                        i7 = this.mHeaderHeight + measuredHeight;
                    }
                    view.layout(i9, i10, measuredWidth + i9, i7);
                }
                if (this.mRefreshHeader != null && this.mRefreshHeader.getView() == childAt) {
                    boolean z3 = isInEditMode() && this.mEnablePreviewInEditMode && isEnableRefreshOrLoadMore(this.mEnableRefresh);
                    View view2 = this.mRefreshHeader.getView();
                    LayoutParams layoutParams2 = (LayoutParams) view2.getLayoutParams();
                    int i11 = layoutParams2.leftMargin;
                    int i12 = layoutParams2.topMargin + this.mHeaderInsetStart;
                    int measuredWidth2 = view2.getMeasuredWidth();
                    int measuredHeight2 = view2.getMeasuredHeight() + i12;
                    if (z3 || this.mRefreshHeader.getSpinnerStyle() != SpinnerStyle.Translate) {
                        i6 = measuredHeight2;
                    } else {
                        i12 -= this.mHeaderHeight;
                        i6 = measuredHeight2 - this.mHeaderHeight;
                    }
                    view2.layout(i11, i12, measuredWidth2 + i11, i6);
                }
                if (this.mRefreshFooter != null && this.mRefreshFooter.getView() == childAt) {
                    boolean z4 = isInEditMode() && this.mEnablePreviewInEditMode && isEnableRefreshOrLoadMore(this.mEnableLoadMore);
                    View view3 = this.mRefreshFooter.getView();
                    LayoutParams layoutParams3 = (LayoutParams) view3.getLayoutParams();
                    SpinnerStyle spinnerStyle = this.mRefreshFooter.getSpinnerStyle();
                    int i13 = layoutParams3.leftMargin;
                    int measuredHeight3 = (layoutParams3.topMargin + getMeasuredHeight()) - this.mFooterInsetStart;
                    if (spinnerStyle == SpinnerStyle.MatchLayout) {
                        i5 = layoutParams3.topMargin - this.mFooterInsetStart;
                    } else if (z4 || spinnerStyle == SpinnerStyle.FixedFront || spinnerStyle == SpinnerStyle.FixedBehind) {
                        i5 = measuredHeight3 - this.mFooterHeight;
                    } else if (spinnerStyle != SpinnerStyle.Scale || this.mSpinner >= 0) {
                        i5 = measuredHeight3;
                    } else {
                        i5 = measuredHeight3 - Math.max(isEnableRefreshOrLoadMore(this.mEnableLoadMore) ? -this.mSpinner : 0, 0);
                    }
                    view3.layout(i13, i5, view3.getMeasuredWidth() + i13, view3.getMeasuredHeight() + i5);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x02d3  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x031d  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0112  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0140  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0147  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0162  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0195  */
    public void onMeasure(int i, int i2) {
        int i3;
        View view;
        int i4;
        boolean z = isInEditMode() && this.mEnablePreviewInEditMode;
        int childCount = super.getChildCount();
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = super.getChildAt(i6);
            if (!(childAt.getVisibility() == 8 || childAt.getTag(R.string.srl_component_falsify) == childAt)) {
                if (this.mRefreshHeader != null && this.mRefreshHeader.getView() == childAt) {
                    View view2 = this.mRefreshHeader.getView();
                    LayoutParams layoutParams = (LayoutParams) view2.getLayoutParams();
                    int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width);
                    int i7 = this.mHeaderHeight;
                    if (this.mHeaderHeightStatus.ordinal() < DimensionStatus.XmlLayoutUnNotify.ordinal()) {
                        if (layoutParams.height > 0) {
                            i7 = layoutParams.height + layoutParams.bottomMargin + layoutParams.topMargin;
                            if (this.mHeaderHeightStatus.canReplaceWith(DimensionStatus.XmlExactUnNotify)) {
                                this.mHeaderHeight = layoutParams.height + layoutParams.bottomMargin + layoutParams.topMargin;
                                this.mHeaderHeightStatus = DimensionStatus.XmlExactUnNotify;
                            }
                        } else if (layoutParams.height == -2 && (this.mRefreshHeader.getSpinnerStyle() != SpinnerStyle.MatchLayout || !this.mHeaderHeightStatus.notified)) {
                            int max = Math.max((View.MeasureSpec.getSize(i2) - layoutParams.bottomMargin) - layoutParams.topMargin, 0);
                            view2.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(max, Integer.MIN_VALUE));
                            int measuredHeight = view2.getMeasuredHeight();
                            if (measuredHeight > 0) {
                                if (measuredHeight != max && this.mHeaderHeightStatus.canReplaceWith(DimensionStatus.XmlWrapUnNotify)) {
                                    this.mHeaderHeight = layoutParams.bottomMargin + measuredHeight + layoutParams.topMargin;
                                    this.mHeaderHeightStatus = DimensionStatus.XmlWrapUnNotify;
                                }
                                i7 = -1;
                            }
                        }
                    }
                    if (this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.MatchLayout) {
                        i7 = View.MeasureSpec.getSize(i2);
                    } else if (this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.Scale && !z) {
                        i7 = Math.max(0, isEnableRefreshOrLoadMore(this.mEnableRefresh) ? this.mSpinner : 0);
                    }
                    if (i7 != -1) {
                        view2.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(Math.max((i7 - layoutParams.bottomMargin) - layoutParams.topMargin, 0), 1073741824));
                    }
                    if (!this.mHeaderHeightStatus.notified) {
                        this.mHeaderHeightStatus = this.mHeaderHeightStatus.notified();
                        this.mRefreshHeader.onInitialized(this.mKernel, this.mHeaderHeight, (int) (this.mHeaderMaxDragRate * ((float) this.mHeaderHeight)));
                    }
                    if (z && isEnableRefreshOrLoadMore(this.mEnableRefresh)) {
                        i3 = view2.getMeasuredHeight() + i5;
                        if (this.mRefreshFooter != null && this.mRefreshFooter.getView() == childAt) {
                            view = this.mRefreshFooter.getView();
                            LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
                            int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i, layoutParams2.leftMargin + layoutParams2.rightMargin, layoutParams2.width);
                            i4 = this.mFooterHeight;
                            if (this.mFooterHeightStatus.ordinal() < DimensionStatus.XmlLayoutUnNotify.ordinal()) {
                                if (layoutParams2.height > 0) {
                                    i4 = layoutParams2.height + layoutParams2.topMargin + layoutParams2.bottomMargin;
                                    if (this.mFooterHeightStatus.canReplaceWith(DimensionStatus.XmlExactUnNotify)) {
                                        this.mFooterHeight = layoutParams2.height + layoutParams2.topMargin + layoutParams2.bottomMargin;
                                        this.mFooterHeightStatus = DimensionStatus.XmlExactUnNotify;
                                    }
                                } else if (layoutParams2.height == -2 && (this.mRefreshFooter.getSpinnerStyle() != SpinnerStyle.MatchLayout || !this.mFooterHeightStatus.notified)) {
                                    int max2 = Math.max((View.MeasureSpec.getSize(i2) - layoutParams2.bottomMargin) - layoutParams2.topMargin, 0);
                                    view.measure(childMeasureSpec2, View.MeasureSpec.makeMeasureSpec(max2, Integer.MIN_VALUE));
                                    int measuredHeight2 = view.getMeasuredHeight();
                                    if (measuredHeight2 > 0) {
                                        if (measuredHeight2 != max2 && this.mFooterHeightStatus.canReplaceWith(DimensionStatus.XmlWrapUnNotify)) {
                                            this.mFooterHeight = layoutParams2.topMargin + measuredHeight2 + layoutParams2.bottomMargin;
                                            this.mFooterHeightStatus = DimensionStatus.XmlWrapUnNotify;
                                        }
                                        i4 = -1;
                                    }
                                }
                            }
                            if (this.mRefreshFooter.getSpinnerStyle() != SpinnerStyle.MatchLayout) {
                                i4 = View.MeasureSpec.getSize(i2);
                            } else if (this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.Scale && !z) {
                                i4 = Math.max(0, isEnableRefreshOrLoadMore(this.mEnableLoadMore) ? -this.mSpinner : 0);
                            }
                            if (i4 != -1) {
                                view.measure(childMeasureSpec2, View.MeasureSpec.makeMeasureSpec(Math.max((i4 - layoutParams2.bottomMargin) - layoutParams2.topMargin, 0), 1073741824));
                            }
                            if (!this.mFooterHeightStatus.notified) {
                                this.mFooterHeightStatus = this.mFooterHeightStatus.notified();
                                this.mRefreshFooter.onInitialized(this.mKernel, this.mFooterHeight, (int) (this.mFooterMaxDragRate * ((float) this.mFooterHeight)));
                            }
                            if (z && isEnableRefreshOrLoadMore(this.mEnableLoadMore)) {
                                i3 += view.getMeasuredHeight();
                            }
                        }
                        if (this.mRefreshContent == null || this.mRefreshContent.getView() != childAt) {
                            i5 = i3;
                        } else {
                            View view3 = this.mRefreshContent.getView();
                            LayoutParams layoutParams3 = (LayoutParams) view3.getLayoutParams();
                            boolean z2 = this.mRefreshHeader != null && isEnableRefreshOrLoadMore(this.mEnableRefresh) && isEnableTranslationContent(this.mEnableHeaderTranslationContent, this.mRefreshHeader);
                            boolean z3 = this.mRefreshFooter != null && isEnableRefreshOrLoadMore(this.mEnableLoadMore) && isEnableTranslationContent(this.mEnableFooterTranslationContent, this.mRefreshFooter);
                            int childMeasureSpec3 = ViewGroup.getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + layoutParams3.leftMargin + layoutParams3.rightMargin, layoutParams3.width);
                            int paddingTop = getPaddingTop();
                            int paddingBottom = getPaddingBottom();
                            int i8 = layoutParams3.topMargin;
                            view3.measure(childMeasureSpec3, ViewGroup.getChildMeasureSpec(i2, ((!z || !z3) ? 0 : this.mFooterHeight) + paddingTop + paddingBottom + i8 + layoutParams3.bottomMargin + ((!z || !z2) ? 0 : this.mHeaderHeight), layoutParams3.height));
                            i5 = i3 + view3.getMeasuredHeight();
                        }
                    }
                }
                i3 = i5;
                view = this.mRefreshFooter.getView();
                LayoutParams layoutParams22 = (LayoutParams) view.getLayoutParams();
                int childMeasureSpec22 = ViewGroup.getChildMeasureSpec(i, layoutParams22.leftMargin + layoutParams22.rightMargin, layoutParams22.width);
                i4 = this.mFooterHeight;
                if (this.mFooterHeightStatus.ordinal() < DimensionStatus.XmlLayoutUnNotify.ordinal()) {
                }
                if (this.mRefreshFooter.getSpinnerStyle() != SpinnerStyle.MatchLayout) {
                }
                if (i4 != -1) {
                }
                if (!this.mFooterHeightStatus.notified) {
                }
                i3 += view.getMeasuredHeight();
                if (this.mRefreshContent == null || this.mRefreshContent.getView() != childAt) {
                }
            }
        }
        super.setMeasuredDimension(View.resolveSize(super.getSuggestedMinimumWidth(), i), View.resolveSize(i5, i2));
        this.mLastTouchX = (float) (getMeasuredWidth() / 2);
    }

    public boolean onNestedFling(@NonNull View view, float f, float f2, boolean z) {
        return this.mNestedChild.dispatchNestedFling(f, f2, z);
    }

    public boolean onNestedPreFling(@NonNull View view, float f, float f2) {
        return (this.mFooterLocked && f2 > CropImageView.DEFAULT_ASPECT_RATIO) || startFlingIfNeed(Float.valueOf(-f2)) || this.mNestedChild.dispatchNestedPreFling(f, f2);
    }

    public void onNestedPreScroll(@NonNull View view, int i, int i2, @NonNull int[] iArr) {
        int i3;
        if (this.mTotalUnconsumed * i2 > 0) {
            if (Math.abs(i2) > Math.abs(this.mTotalUnconsumed)) {
                i3 = this.mTotalUnconsumed;
                this.mTotalUnconsumed = 0;
            } else {
                this.mTotalUnconsumed -= i2;
                i3 = i2;
            }
            moveSpinnerInfinitely((float) this.mTotalUnconsumed);
        } else if (i2 <= 0 || !this.mFooterLocked) {
            i3 = 0;
        } else {
            this.mTotalUnconsumed -= i2;
            moveSpinnerInfinitely((float) this.mTotalUnconsumed);
            i3 = i2;
        }
        this.mNestedChild.dispatchNestedPreScroll(i, i2 - i3, iArr, (int[]) null);
        iArr[1] = i3 + iArr[1];
    }

    public void onNestedScroll(@NonNull View view, int i, int i2, int i3, int i4) {
        this.mNestedChild.dispatchNestedScroll(i, i2, i3, i4, this.mParentOffsetInWindow);
        int i5 = i4 + this.mParentOffsetInWindow[1];
        if (i5 != 0 && ((i5 < 0 && this.mEnableRefresh) || (i5 > 0 && this.mEnableLoadMore))) {
            if (this.mViceState == RefreshState.None || this.mViceState.isOpening) {
                this.mKernel.setState(i5 > 0 ? RefreshState.PullUpToLoad : RefreshState.PullDownToRefresh);
            }
            int i6 = this.mTotalUnconsumed - i5;
            this.mTotalUnconsumed = i6;
            moveSpinnerInfinitely((float) i6);
        }
        if (this.mFooterLocked && i2 < 0) {
            this.mFooterLocked = false;
        }
    }

    public void onNestedScrollAccepted(@NonNull View view, @NonNull View view2, int i) {
        this.mNestedParent.onNestedScrollAccepted(view, view2, i);
        this.mNestedChild.startNestedScroll(i & 2);
        this.mTotalUnconsumed = this.mSpinner;
        this.mNestedInProgress = true;
        interceptAnimatorByAction(0);
    }

    public boolean onStartNestedScroll(@NonNull View view, @NonNull View view2, int i) {
        return (isEnabled() && isNestedScrollingEnabled() && (i & 2) != 0) && (this.mEnableRefresh || this.mEnableLoadMore);
    }

    public void onStopNestedScroll(@NonNull View view) {
        this.mNestedParent.onStopNestedScroll(view);
        this.mNestedInProgress = false;
        this.mTotalUnconsumed = 0;
        overSpinner();
        this.mNestedChild.stopNestedScroll();
    }

    /* access modifiers changed from: protected */
    public void overSpinner() {
        if (this.mState == RefreshState.TwoLevel) {
            if (this.mCurrentVelocity > -1000 && this.mSpinner > getMeasuredHeight() / 2) {
                ValueAnimator animSpinner = this.mKernel.animSpinner(getMeasuredHeight());
                if (animSpinner != null) {
                    animSpinner.setDuration((long) this.mFloorDuration);
                }
            } else if (this.mIsBeingDragged) {
                this.mKernel.finishTwoLevel();
            }
        } else if (this.mState == RefreshState.Loading || (this.mEnableFooterFollowWhenNoMoreData && this.mFooterNoMoreData && this.mSpinner < 0 && isEnableRefreshOrLoadMore(this.mEnableLoadMore))) {
            if (this.mSpinner < (-this.mFooterHeight)) {
                this.mKernel.animSpinner(-this.mFooterHeight);
            } else if (this.mSpinner > 0) {
                this.mKernel.animSpinner(0);
            }
        } else if (this.mState == RefreshState.Refreshing) {
            if (this.mSpinner > this.mHeaderHeight) {
                this.mKernel.animSpinner(this.mHeaderHeight);
            } else if (this.mSpinner < 0) {
                this.mKernel.animSpinner(0);
            }
        } else if (this.mState == RefreshState.PullDownToRefresh) {
            this.mKernel.setState(RefreshState.PullDownCanceled);
        } else if (this.mState == RefreshState.PullUpToLoad) {
            this.mKernel.setState(RefreshState.PullUpCanceled);
        } else if (this.mState == RefreshState.ReleaseToRefresh) {
            this.mKernel.setState(RefreshState.Refreshing);
        } else if (this.mState == RefreshState.ReleaseToLoad) {
            this.mKernel.setState(RefreshState.Loading);
        } else if (this.mState == RefreshState.ReleaseToTwoLevel) {
            this.mKernel.setState(RefreshState.TwoLevelReleased);
        } else if (this.mState == RefreshState.RefreshReleased) {
            if (this.reboundAnimator == null) {
                this.mKernel.animSpinner(this.mHeaderHeight);
            }
        } else if (this.mState == RefreshState.LoadReleased) {
            if (this.reboundAnimator == null) {
                this.mKernel.animSpinner(-this.mFooterHeight);
            }
        } else if (this.mSpinner != 0) {
            this.mKernel.animSpinner(0);
        }
    }

    public boolean post(@NonNull Runnable runnable) {
        if (this.mHandler != null) {
            return this.mHandler.post(new DelayedRunnable(runnable, 0));
        }
        this.mListDelayedRunnable = this.mListDelayedRunnable == null ? new ArrayList<>() : this.mListDelayedRunnable;
        this.mListDelayedRunnable.add(new DelayedRunnable(runnable, 0));
        return false;
    }

    public boolean postDelayed(@NonNull Runnable runnable, long j) {
        if (j == 0) {
            new DelayedRunnable(runnable, 0).run();
            return true;
        } else if (this.mHandler != null) {
            return this.mHandler.postDelayed(new DelayedRunnable(runnable, 0), j);
        } else {
            this.mListDelayedRunnable = this.mListDelayedRunnable == null ? new ArrayList<>() : this.mListDelayedRunnable;
            this.mListDelayedRunnable.add(new DelayedRunnable(runnable, j));
            return false;
        }
    }

    public RefreshLayout resetNoMoreData() {
        this.mFooterNoMoreData = false;
        if ((this.mRefreshFooter instanceof RefreshFooter) && !((RefreshFooter) this.mRefreshFooter).setNoMoreData(false)) {
            PrintStream printStream = System.out;
            printStream.println("Footer:" + this.mRefreshFooter + " NoMoreData is not supported.(不支持NoMoreData，请使用ClassicsFooter或者自定义)");
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void resetStatus() {
        if (this.mState != RefreshState.None && this.mSpinner == 0) {
            notifyStateChanged(RefreshState.None);
        }
        if (this.mSpinner != 0) {
            this.mKernel.animSpinner(0);
        }
    }

    public SmartRefreshLayout setDisableContentWhenLoading(boolean z) {
        this.mDisableContentWhenLoading = z;
        return this;
    }

    public SmartRefreshLayout setDisableContentWhenRefresh(boolean z) {
        this.mDisableContentWhenRefresh = z;
        return this;
    }

    public SmartRefreshLayout setDragRate(float f) {
        this.mDragRate = f;
        return this;
    }

    public SmartRefreshLayout setEnableAutoLoadMore(boolean z) {
        this.mEnableAutoLoadMore = z;
        return this;
    }

    public SmartRefreshLayout setEnableClipFooterWhenFixedBehind(boolean z) {
        this.mEnableClipFooterWhenFixedBehind = z;
        return this;
    }

    public SmartRefreshLayout setEnableClipHeaderWhenFixedBehind(boolean z) {
        this.mEnableClipHeaderWhenFixedBehind = z;
        return this;
    }

    @Deprecated
    public SmartRefreshLayout setEnableFooterFollowWhenLoadFinished(boolean z) {
        this.mEnableFooterFollowWhenNoMoreData = z;
        return this;
    }

    public RefreshLayout setEnableFooterFollowWhenNoMoreData(boolean z) {
        this.mEnableFooterFollowWhenNoMoreData = z;
        return this;
    }

    public SmartRefreshLayout setEnableFooterTranslationContent(boolean z) {
        this.mEnableFooterTranslationContent = z;
        this.mManualFooterTranslationContent = true;
        return this;
    }

    public SmartRefreshLayout setEnableHeaderTranslationContent(boolean z) {
        this.mEnableHeaderTranslationContent = z;
        this.mManualHeaderTranslationContent = true;
        return this;
    }

    public SmartRefreshLayout setEnableLoadMore(boolean z) {
        this.mManualLoadMore = true;
        this.mEnableLoadMore = z;
        return this;
    }

    public SmartRefreshLayout setEnableLoadMoreWhenContentNotFull(boolean z) {
        this.mEnableLoadMoreWhenContentNotFull = z;
        if (this.mRefreshContent != null) {
            this.mRefreshContent.setEnableLoadMoreWhenContentNotFull(z);
        }
        return this;
    }

    public RefreshLayout setEnableNestedScroll(boolean z) {
        setNestedScrollingEnabled(z);
        return this;
    }

    public SmartRefreshLayout setEnableOverScrollBounce(boolean z) {
        this.mEnableOverScrollBounce = z;
        return this;
    }

    public SmartRefreshLayout setEnableOverScrollDrag(boolean z) {
        this.mEnableOverScrollDrag = z;
        return this;
    }

    public SmartRefreshLayout setEnablePureScrollMode(boolean z) {
        this.mEnablePureScrollMode = z;
        return this;
    }

    public SmartRefreshLayout setEnableRefresh(boolean z) {
        this.mEnableRefresh = z;
        return this;
    }

    public SmartRefreshLayout setEnableScrollContentWhenLoaded(boolean z) {
        this.mEnableScrollContentWhenLoaded = z;
        return this;
    }

    public SmartRefreshLayout setEnableScrollContentWhenRefreshed(boolean z) {
        this.mEnableScrollContentWhenRefreshed = z;
        return this;
    }

    public SmartRefreshLayout setFooterHeight(float f) {
        if (this.mFooterHeightStatus.canReplaceWith(DimensionStatus.CodeExact)) {
            this.mFooterHeight = DensityUtil.dp2px(f);
            this.mFooterHeightStatus = DimensionStatus.CodeExactUnNotify;
            if (this.mRefreshFooter != null) {
                this.mRefreshFooter.getView().requestLayout();
            }
        }
        return this;
    }

    public SmartRefreshLayout setFooterInsetStart(float f) {
        this.mFooterInsetStart = DensityUtil.dp2px(f);
        return this;
    }

    public SmartRefreshLayout setFooterMaxDragRate(float f) {
        this.mFooterMaxDragRate = f;
        if (this.mRefreshFooter == null || this.mHandler == null) {
            this.mFooterHeightStatus = this.mFooterHeightStatus.unNotify();
        } else {
            this.mRefreshFooter.onInitialized(this.mKernel, this.mFooterHeight, (int) (((float) this.mFooterHeight) * this.mFooterMaxDragRate));
        }
        return this;
    }

    public SmartRefreshLayout setFooterTriggerRate(float f) {
        this.mFooterTriggerRate = f;
        return this;
    }

    public SmartRefreshLayout setHeaderHeight(float f) {
        if (this.mHeaderHeightStatus.canReplaceWith(DimensionStatus.CodeExact)) {
            this.mHeaderHeight = DensityUtil.dp2px(f);
            this.mHeaderHeightStatus = DimensionStatus.CodeExactUnNotify;
            if (this.mRefreshHeader != null) {
                this.mRefreshHeader.getView().requestLayout();
            }
        }
        return this;
    }

    public SmartRefreshLayout setHeaderInsetStart(float f) {
        this.mHeaderInsetStart = DensityUtil.dp2px(f);
        return this;
    }

    public SmartRefreshLayout setHeaderMaxDragRate(float f) {
        this.mHeaderMaxDragRate = f;
        if (this.mRefreshHeader == null || this.mHandler == null) {
            this.mHeaderHeightStatus = this.mHeaderHeightStatus.unNotify();
        } else {
            this.mRefreshHeader.onInitialized(this.mKernel, this.mHeaderHeight, (int) (this.mHeaderMaxDragRate * ((float) this.mHeaderHeight)));
        }
        return this;
    }

    public SmartRefreshLayout setHeaderTriggerRate(float f) {
        this.mHeaderTriggerRate = f;
        return this;
    }

    public void setNestedScrollingEnabled(boolean z) {
        this.mNestedChild.setNestedScrollingEnabled(z);
    }

    @Deprecated
    public SmartRefreshLayout setNoMoreData(boolean z) {
        if (this.mState == RefreshState.Loading && z) {
            finishLoadMore();
        }
        this.mFooterNoMoreData = z;
        if ((this.mRefreshFooter instanceof RefreshFooter) && !((RefreshFooter) this.mRefreshFooter).setNoMoreData(z)) {
            PrintStream printStream = System.out;
            printStream.println("Footer:" + this.mRefreshFooter + " NoMoreData is not supported.(不支持NoMoreData，请使用ClassicsFooter或者自定义)");
        }
        return this;
    }

    public SmartRefreshLayout setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.mLoadMoreListener = onLoadMoreListener;
        this.mEnableLoadMore = this.mEnableLoadMore || (!this.mManualLoadMore && onLoadMoreListener != null);
        return this;
    }

    public SmartRefreshLayout setOnMultiPurposeListener(OnMultiPurposeListener onMultiPurposeListener) {
        this.mOnMultiPurposeListener = onMultiPurposeListener;
        return this;
    }

    public SmartRefreshLayout setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.mRefreshListener = onRefreshListener;
        return this;
    }

    public SmartRefreshLayout setOnRefreshLoadMoreListener(OnRefreshLoadMoreListener onRefreshLoadMoreListener) {
        this.mRefreshListener = onRefreshLoadMoreListener;
        this.mLoadMoreListener = onRefreshLoadMoreListener;
        this.mEnableLoadMore = this.mEnableLoadMore || (!this.mManualLoadMore && onRefreshLoadMoreListener != null);
        return this;
    }

    public SmartRefreshLayout setPrimaryColors(@ColorInt int... iArr) {
        if (this.mRefreshHeader != null) {
            this.mRefreshHeader.setPrimaryColors(iArr);
        }
        if (this.mRefreshFooter != null) {
            this.mRefreshFooter.setPrimaryColors(iArr);
        }
        this.mPrimaryColors = iArr;
        return this;
    }

    public SmartRefreshLayout setPrimaryColorsId(@ColorRes int... iArr) {
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[i] = SmartUtil.getColor(getContext(), iArr[i]);
        }
        setPrimaryColors(iArr2);
        return this;
    }

    public SmartRefreshLayout setReboundDuration(int i) {
        this.mReboundDuration = i;
        return this;
    }

    public SmartRefreshLayout setReboundInterpolator(@NonNull Interpolator interpolator) {
        this.mReboundInterpolator = interpolator;
        return this;
    }

    public SmartRefreshLayout setRefreshContent(@NonNull View view) {
        return setRefreshContent(view, -1, -1);
    }

    public SmartRefreshLayout setRefreshContent(@NonNull View view, int i, int i2) {
        View view2 = null;
        if (this.mRefreshContent != null) {
            super.removeView(this.mRefreshContent.getView());
        }
        super.addView(view, 0, new LayoutParams(i, i2));
        if (this.mRefreshHeader != null && this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.FixedBehind) {
            super.bringChildToFront(view);
            if (!(this.mRefreshFooter == null || this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.FixedBehind)) {
                super.bringChildToFront(this.mRefreshFooter.getView());
            }
        } else if (this.mRefreshFooter != null && this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.FixedBehind) {
            super.bringChildToFront(view);
            if (this.mRefreshHeader != null && this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.FixedBehind) {
                super.bringChildToFront(this.mRefreshHeader.getView());
            }
        }
        this.mRefreshContent = new RefreshContentWrapper(view);
        if (this.mHandler != null) {
            View findViewById = this.mFixedHeaderViewId > 0 ? findViewById(this.mFixedHeaderViewId) : null;
            if (this.mFixedFooterViewId > 0) {
                view2 = findViewById(this.mFixedFooterViewId);
            }
            this.mRefreshContent.setScrollBoundaryDecider(this.mScrollBoundaryDecider);
            this.mRefreshContent.setEnableLoadMoreWhenContentNotFull(this.mEnableLoadMoreWhenContentNotFull);
            this.mRefreshContent.setUpComponent(this.mKernel, findViewById, view2);
        }
        return this;
    }

    public SmartRefreshLayout setRefreshFooter(@NonNull RefreshFooter refreshFooter) {
        return setRefreshFooter(refreshFooter, -1, -2);
    }

    public SmartRefreshLayout setRefreshFooter(@NonNull RefreshFooter refreshFooter, int i, int i2) {
        if (this.mRefreshFooter != null) {
            super.removeView(this.mRefreshFooter.getView());
        }
        this.mRefreshFooter = refreshFooter;
        this.mFooterBackgroundColor = 0;
        this.mFooterNeedTouchEventWhenLoading = false;
        this.mFooterHeightStatus = this.mFooterHeightStatus.unNotify();
        this.mEnableLoadMore = !this.mManualLoadMore || this.mEnableLoadMore;
        if (this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.FixedBehind) {
            super.addView(this.mRefreshFooter.getView(), 0, new LayoutParams(i, i2));
        } else {
            super.addView(this.mRefreshFooter.getView(), i, i2);
        }
        return this;
    }

    public SmartRefreshLayout setRefreshHeader(@NonNull RefreshHeader refreshHeader) {
        return setRefreshHeader(refreshHeader, -1, -2);
    }

    public SmartRefreshLayout setRefreshHeader(@NonNull RefreshHeader refreshHeader, int i, int i2) {
        if (this.mRefreshHeader != null) {
            super.removeView(this.mRefreshHeader.getView());
        }
        this.mRefreshHeader = refreshHeader;
        this.mHeaderBackgroundColor = 0;
        this.mHeaderNeedTouchEventWhenRefreshing = false;
        this.mHeaderHeightStatus = this.mHeaderHeightStatus.unNotify();
        if (this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.FixedBehind) {
            super.addView(this.mRefreshHeader.getView(), 0, new LayoutParams(i, i2));
        } else {
            super.addView(this.mRefreshHeader.getView(), i, i2);
        }
        return this;
    }

    public SmartRefreshLayout setScrollBoundaryDecider(ScrollBoundaryDecider scrollBoundaryDecider) {
        this.mScrollBoundaryDecider = scrollBoundaryDecider;
        if (this.mRefreshContent != null) {
            this.mRefreshContent.setScrollBoundaryDecider(scrollBoundaryDecider);
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void setStateDirectLoading(boolean z) {
        if (this.mState != RefreshState.Loading) {
            this.mLastOpenTime = System.currentTimeMillis();
            this.mFooterLocked = true;
            notifyStateChanged(RefreshState.Loading);
            if (this.mLoadMoreListener != null) {
                if (z) {
                    this.mLoadMoreListener.onLoadMore(this);
                }
            } else if (this.mOnMultiPurposeListener == null) {
                finishLoadMore((int) BannerConfig.TIME);
            }
            if (this.mRefreshFooter != null) {
                this.mRefreshFooter.onStartAnimator(this, this.mFooterHeight, (int) (this.mFooterMaxDragRate * ((float) this.mFooterHeight)));
            }
            if (this.mOnMultiPurposeListener != null && (this.mRefreshFooter instanceof RefreshFooter)) {
                OnMultiPurposeListener onMultiPurposeListener = this.mOnMultiPurposeListener;
                if (onMultiPurposeListener != null && z) {
                    onMultiPurposeListener.onLoadMore(this);
                }
                this.mOnMultiPurposeListener.onFooterStartAnimator((RefreshFooter) this.mRefreshFooter, this.mFooterHeight, (int) (this.mFooterMaxDragRate * ((float) this.mFooterHeight)));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setStateLoading() {
        AnonymousClass1 r1 = new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                SmartRefreshLayout.this.setStateDirectLoading(true);
            }
        };
        notifyStateChanged(RefreshState.LoadReleased);
        ValueAnimator animSpinner = this.mKernel.animSpinner(-this.mFooterHeight);
        if (animSpinner != null) {
            animSpinner.addListener(r1);
        }
        if (this.mRefreshFooter != null) {
            this.mRefreshFooter.onReleased(this, this.mFooterHeight, (int) (this.mFooterMaxDragRate * ((float) this.mFooterHeight)));
        }
        if (this.mOnMultiPurposeListener != null && (this.mRefreshFooter instanceof RefreshFooter)) {
            this.mOnMultiPurposeListener.onFooterReleased((RefreshFooter) this.mRefreshFooter, this.mFooterHeight, (int) (this.mFooterMaxDragRate * ((float) this.mFooterHeight)));
        }
        if (animSpinner == null) {
            r1.onAnimationEnd((Animator) null);
        }
    }

    /* access modifiers changed from: protected */
    public void setStateRefreshing() {
        AnonymousClass2 r1 = new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                SmartRefreshLayout.this.mLastOpenTime = System.currentTimeMillis();
                SmartRefreshLayout.this.notifyStateChanged(RefreshState.Refreshing);
                if (SmartRefreshLayout.this.mRefreshListener != null) {
                    SmartRefreshLayout.this.mRefreshListener.onRefresh(SmartRefreshLayout.this);
                } else if (SmartRefreshLayout.this.mOnMultiPurposeListener == null) {
                    SmartRefreshLayout.this.finishRefresh(3000);
                }
                if (SmartRefreshLayout.this.mRefreshHeader != null) {
                    SmartRefreshLayout.this.mRefreshHeader.onStartAnimator(SmartRefreshLayout.this, SmartRefreshLayout.this.mHeaderHeight, (int) (SmartRefreshLayout.this.mHeaderMaxDragRate * ((float) SmartRefreshLayout.this.mHeaderHeight)));
                }
                if (SmartRefreshLayout.this.mOnMultiPurposeListener != null && (SmartRefreshLayout.this.mRefreshHeader instanceof RefreshHeader)) {
                    SmartRefreshLayout.this.mOnMultiPurposeListener.onRefresh(SmartRefreshLayout.this);
                    SmartRefreshLayout.this.mOnMultiPurposeListener.onHeaderStartAnimator((RefreshHeader) SmartRefreshLayout.this.mRefreshHeader, SmartRefreshLayout.this.mHeaderHeight, (int) (SmartRefreshLayout.this.mHeaderMaxDragRate * ((float) SmartRefreshLayout.this.mHeaderHeight)));
                }
            }
        };
        notifyStateChanged(RefreshState.RefreshReleased);
        ValueAnimator animSpinner = this.mKernel.animSpinner(this.mHeaderHeight);
        if (animSpinner != null) {
            animSpinner.addListener(r1);
        }
        if (this.mRefreshHeader != null) {
            this.mRefreshHeader.onReleased(this, this.mHeaderHeight, (int) (this.mHeaderMaxDragRate * ((float) this.mHeaderHeight)));
        }
        if (this.mOnMultiPurposeListener != null && (this.mRefreshHeader instanceof RefreshHeader)) {
            this.mOnMultiPurposeListener.onHeaderReleased((RefreshHeader) this.mRefreshHeader, this.mHeaderHeight, (int) (this.mHeaderMaxDragRate * ((float) this.mHeaderHeight)));
        }
        if (animSpinner == null) {
            r1.onAnimationEnd((Animator) null);
        }
    }

    /* access modifiers changed from: protected */
    public void setViceState(RefreshState refreshState) {
        if (this.mState.isDragging && this.mState.isHeader != refreshState.isHeader) {
            notifyStateChanged(RefreshState.None);
        }
        if (this.mViceState != refreshState) {
            this.mViceState = refreshState;
        }
    }

    /* access modifiers changed from: protected */
    public boolean startFlingIfNeed(Float f) {
        float floatValue = f == null ? (float) this.mCurrentVelocity : f.floatValue();
        if (Math.abs(floatValue) <= ((float) this.mMinimumVelocity)) {
            return false;
        }
        if (((float) this.mSpinner) * floatValue < CropImageView.DEFAULT_ASPECT_RATIO) {
            if (this.mState == RefreshState.Refreshing || this.mState == RefreshState.Loading || (this.mSpinner < 0 && this.mFooterNoMoreData)) {
                this.animationRunnable = new FlingRunnable(floatValue).start();
                return true;
            } else if (this.mState.isReleaseToOpening) {
                return true;
            }
        }
        if (floatValue >= CropImageView.DEFAULT_ASPECT_RATIO || ((!this.mEnableOverScrollBounce || !this.mEnableLoadMore) && ((this.mState != RefreshState.Loading || this.mSpinner < 0) && (!this.mEnableAutoLoadMore || !isEnableRefreshOrLoadMore(this.mEnableLoadMore))))) {
            if (floatValue <= CropImageView.DEFAULT_ASPECT_RATIO) {
                return false;
            }
            if ((!this.mEnableOverScrollBounce || !this.mEnableRefresh) && (this.mState != RefreshState.Refreshing || this.mSpinner > 0)) {
                return false;
            }
        }
        this.mVerticalPermit = false;
        this.mScroller.fling(0, 0, 0, (int) (-floatValue), 0, 0, -2147483647, Integer.MAX_VALUE);
        this.mScroller.computeScrollOffset();
        invalidate();
        return false;
    }
}
