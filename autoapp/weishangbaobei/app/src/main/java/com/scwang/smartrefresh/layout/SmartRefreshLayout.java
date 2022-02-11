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

    @NonNull
    public SmartRefreshLayout getLayout() {
        return this;
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

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0052  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onFinishInflate() {
        /*
            r11 = this;
            super.onFinishInflate()
            int r0 = super.getChildCount()
            r1 = 3
            if (r0 > r1) goto L_0x009e
            r2 = -1
            r3 = 0
            r4 = 0
            r5 = -1
            r6 = 0
        L_0x000f:
            r7 = 2
            r8 = 1
            if (r4 >= r0) goto L_0x0033
            android.view.View r9 = super.getChildAt(r4)
            boolean r10 = com.scwang.smartrefresh.layout.util.SmartUtil.isContentView(r9)
            if (r10 == 0) goto L_0x0024
            if (r6 < r7) goto L_0x0021
            if (r4 != r8) goto L_0x0024
        L_0x0021:
            r5 = r4
            r6 = 2
            goto L_0x0030
        L_0x0024:
            boolean r7 = r9 instanceof com.scwang.smartrefresh.layout.api.RefreshInternal
            if (r7 != 0) goto L_0x0030
            if (r6 >= r8) goto L_0x0030
            if (r4 <= 0) goto L_0x002d
            goto L_0x002e
        L_0x002d:
            r8 = 0
        L_0x002e:
            r5 = r4
            r6 = r8
        L_0x0030:
            int r4 = r4 + 1
            goto L_0x000f
        L_0x0033:
            if (r5 < 0) goto L_0x004d
            com.scwang.smartrefresh.layout.impl.RefreshContentWrapper r4 = new com.scwang.smartrefresh.layout.impl.RefreshContentWrapper
            android.view.View r6 = super.getChildAt(r5)
            r4.<init>(r6)
            r11.mRefreshContent = r4
            if (r5 != r8) goto L_0x0048
            if (r0 != r1) goto L_0x0046
            r1 = 0
            goto L_0x004f
        L_0x0046:
            r1 = 0
            goto L_0x004e
        L_0x0048:
            if (r0 != r7) goto L_0x004d
            r1 = -1
            r7 = 1
            goto L_0x004f
        L_0x004d:
            r1 = -1
        L_0x004e:
            r7 = -1
        L_0x004f:
            r4 = 0
        L_0x0050:
            if (r4 >= r0) goto L_0x009d
            android.view.View r5 = super.getChildAt(r4)
            if (r4 == r1) goto L_0x008b
            if (r4 == r7) goto L_0x0065
            if (r1 != r2) goto L_0x0065
            com.scwang.smartrefresh.layout.api.RefreshInternal r6 = r11.mRefreshHeader
            if (r6 != 0) goto L_0x0065
            boolean r6 = r5 instanceof com.scwang.smartrefresh.layout.api.RefreshHeader
            if (r6 == 0) goto L_0x0065
            goto L_0x008b
        L_0x0065:
            if (r4 == r7) goto L_0x006d
            if (r7 != r2) goto L_0x009a
            boolean r6 = r5 instanceof com.scwang.smartrefresh.layout.api.RefreshFooter
            if (r6 == 0) goto L_0x009a
        L_0x006d:
            boolean r6 = r11.mEnableLoadMore
            if (r6 != 0) goto L_0x0078
            boolean r6 = r11.mManualLoadMore
            if (r6 != 0) goto L_0x0076
            goto L_0x0078
        L_0x0076:
            r6 = 0
            goto L_0x0079
        L_0x0078:
            r6 = 1
        L_0x0079:
            r11.mEnableLoadMore = r6
            boolean r6 = r5 instanceof com.scwang.smartrefresh.layout.api.RefreshFooter
            if (r6 == 0) goto L_0x0082
            com.scwang.smartrefresh.layout.api.RefreshFooter r5 = (com.scwang.smartrefresh.layout.api.RefreshFooter) r5
            goto L_0x0088
        L_0x0082:
            com.scwang.smartrefresh.layout.impl.RefreshFooterWrapper r6 = new com.scwang.smartrefresh.layout.impl.RefreshFooterWrapper
            r6.<init>(r5)
            r5 = r6
        L_0x0088:
            r11.mRefreshFooter = r5
            goto L_0x009a
        L_0x008b:
            boolean r6 = r5 instanceof com.scwang.smartrefresh.layout.api.RefreshHeader
            if (r6 == 0) goto L_0x0092
            com.scwang.smartrefresh.layout.api.RefreshHeader r5 = (com.scwang.smartrefresh.layout.api.RefreshHeader) r5
            goto L_0x0098
        L_0x0092:
            com.scwang.smartrefresh.layout.impl.RefreshHeaderWrapper r6 = new com.scwang.smartrefresh.layout.impl.RefreshHeaderWrapper
            r6.<init>(r5)
            r5 = r6
        L_0x0098:
            r11.mRefreshHeader = r5
        L_0x009a:
            int r4 = r4 + 1
            goto L_0x0050
        L_0x009d:
            return
        L_0x009e:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "最多只支持3个子View，Most only support three sub view"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scwang.smartrefresh.layout.SmartRefreshLayout.onFinishInflate():void");
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (this.mHandler == null) {
                this.mHandler = new Handler();
            }
            View view = null;
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
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0120  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r18, int r19) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            boolean r3 = r17.isInEditMode()
            r5 = 0
            if (r3 == 0) goto L_0x0013
            boolean r3 = r0.mEnablePreviewInEditMode
            if (r3 == 0) goto L_0x0013
            r3 = 1
            goto L_0x0014
        L_0x0013:
            r3 = 0
        L_0x0014:
            int r6 = super.getChildCount()
            r7 = 0
            r8 = 0
        L_0x001a:
            if (r7 >= r6) goto L_0x02ef
            android.view.View r9 = super.getChildAt(r7)
            int r10 = r9.getVisibility()
            r11 = 8
            if (r10 == r11) goto L_0x02eb
            int r10 = com.scwang.smartrefresh.layout.R.string.srl_component_falsify
            java.lang.Object r10 = r9.getTag(r10)
            if (r10 != r9) goto L_0x0032
            goto L_0x02eb
        L_0x0032:
            com.scwang.smartrefresh.layout.api.RefreshInternal r10 = r0.mRefreshHeader
            r13 = -2
            if (r10 == 0) goto L_0x0148
            com.scwang.smartrefresh.layout.api.RefreshInternal r10 = r0.mRefreshHeader
            android.view.View r10 = r10.getView()
            if (r10 != r9) goto L_0x0148
            com.scwang.smartrefresh.layout.api.RefreshInternal r10 = r0.mRefreshHeader
            android.view.View r10 = r10.getView()
            android.view.ViewGroup$LayoutParams r15 = r10.getLayoutParams()
            com.scwang.smartrefresh.layout.SmartRefreshLayout$LayoutParams r15 = (com.scwang.smartrefresh.layout.SmartRefreshLayout.LayoutParams) r15
            int r4 = r15.leftMargin
            int r11 = r15.rightMargin
            int r4 = r4 + r11
            int r11 = r15.width
            int r4 = android.view.ViewGroup.getChildMeasureSpec(r1, r4, r11)
            int r11 = r0.mHeaderHeight
            com.scwang.smartrefresh.layout.constant.DimensionStatus r14 = r0.mHeaderHeightStatus
            int r14 = r14.ordinal()
            com.scwang.smartrefresh.layout.constant.DimensionStatus r16 = com.scwang.smartrefresh.layout.constant.DimensionStatus.XmlLayoutUnNotify
            int r12 = r16.ordinal()
            if (r14 >= r12) goto L_0x00d7
            int r12 = r15.height
            if (r12 <= 0) goto L_0x008c
            int r11 = r15.height
            int r12 = r15.bottomMargin
            int r11 = r11 + r12
            int r12 = r15.topMargin
            int r14 = r11 + r12
            com.scwang.smartrefresh.layout.constant.DimensionStatus r11 = r0.mHeaderHeightStatus
            com.scwang.smartrefresh.layout.constant.DimensionStatus r12 = com.scwang.smartrefresh.layout.constant.DimensionStatus.XmlExactUnNotify
            boolean r11 = r11.canReplaceWith(r12)
            if (r11 == 0) goto L_0x00d8
            int r11 = r15.height
            int r12 = r15.bottomMargin
            int r11 = r11 + r12
            int r12 = r15.topMargin
            int r11 = r11 + r12
            r0.mHeaderHeight = r11
            com.scwang.smartrefresh.layout.constant.DimensionStatus r11 = com.scwang.smartrefresh.layout.constant.DimensionStatus.XmlExactUnNotify
            r0.mHeaderHeightStatus = r11
            goto L_0x00d8
        L_0x008c:
            int r12 = r15.height
            if (r12 != r13) goto L_0x00d7
            com.scwang.smartrefresh.layout.api.RefreshInternal r12 = r0.mRefreshHeader
            com.scwang.smartrefresh.layout.constant.SpinnerStyle r12 = r12.getSpinnerStyle()
            com.scwang.smartrefresh.layout.constant.SpinnerStyle r14 = com.scwang.smartrefresh.layout.constant.SpinnerStyle.MatchLayout
            if (r12 != r14) goto L_0x00a0
            com.scwang.smartrefresh.layout.constant.DimensionStatus r12 = r0.mHeaderHeightStatus
            boolean r12 = r12.notified
            if (r12 != 0) goto L_0x00d7
        L_0x00a0:
            int r12 = android.view.View.MeasureSpec.getSize(r19)
            int r14 = r15.bottomMargin
            int r12 = r12 - r14
            int r14 = r15.topMargin
            int r12 = r12 - r14
            int r12 = java.lang.Math.max(r12, r5)
            r14 = -2147483648(0xffffffff80000000, float:-0.0)
            int r13 = android.view.View.MeasureSpec.makeMeasureSpec(r12, r14)
            r10.measure(r4, r13)
            int r13 = r10.getMeasuredHeight()
            if (r13 <= 0) goto L_0x00d7
            if (r13 == r12) goto L_0x00d5
            com.scwang.smartrefresh.layout.constant.DimensionStatus r11 = r0.mHeaderHeightStatus
            com.scwang.smartrefresh.layout.constant.DimensionStatus r12 = com.scwang.smartrefresh.layout.constant.DimensionStatus.XmlWrapUnNotify
            boolean r11 = r11.canReplaceWith(r12)
            if (r11 == 0) goto L_0x00d5
            int r11 = r15.bottomMargin
            int r13 = r13 + r11
            int r11 = r15.topMargin
            int r13 = r13 + r11
            r0.mHeaderHeight = r13
            com.scwang.smartrefresh.layout.constant.DimensionStatus r11 = com.scwang.smartrefresh.layout.constant.DimensionStatus.XmlWrapUnNotify
            r0.mHeaderHeightStatus = r11
        L_0x00d5:
            r14 = -1
            goto L_0x00d8
        L_0x00d7:
            r14 = r11
        L_0x00d8:
            com.scwang.smartrefresh.layout.api.RefreshInternal r11 = r0.mRefreshHeader
            com.scwang.smartrefresh.layout.constant.SpinnerStyle r11 = r11.getSpinnerStyle()
            com.scwang.smartrefresh.layout.constant.SpinnerStyle r12 = com.scwang.smartrefresh.layout.constant.SpinnerStyle.MatchLayout
            if (r11 != r12) goto L_0x00e8
            int r14 = android.view.View.MeasureSpec.getSize(r19)
        L_0x00e6:
            r11 = -1
            goto L_0x0105
        L_0x00e8:
            com.scwang.smartrefresh.layout.api.RefreshInternal r11 = r0.mRefreshHeader
            com.scwang.smartrefresh.layout.constant.SpinnerStyle r11 = r11.getSpinnerStyle()
            com.scwang.smartrefresh.layout.constant.SpinnerStyle r12 = com.scwang.smartrefresh.layout.constant.SpinnerStyle.Scale
            if (r11 != r12) goto L_0x00e6
            if (r3 != 0) goto L_0x00e6
            boolean r11 = r0.mEnableRefresh
            boolean r11 = r0.isEnableRefreshOrLoadMore(r11)
            if (r11 == 0) goto L_0x00ff
            int r11 = r0.mSpinner
            goto L_0x0100
        L_0x00ff:
            r11 = 0
        L_0x0100:
            int r14 = java.lang.Math.max(r5, r11)
            goto L_0x00e6
        L_0x0105:
            if (r14 == r11) goto L_0x011a
            int r11 = r15.bottomMargin
            int r14 = r14 - r11
            int r11 = r15.topMargin
            int r14 = r14 - r11
            int r11 = java.lang.Math.max(r14, r5)
            r12 = 1073741824(0x40000000, float:2.0)
            int r11 = android.view.View.MeasureSpec.makeMeasureSpec(r11, r12)
            r10.measure(r4, r11)
        L_0x011a:
            com.scwang.smartrefresh.layout.constant.DimensionStatus r4 = r0.mHeaderHeightStatus
            boolean r4 = r4.notified
            if (r4 != 0) goto L_0x0139
            com.scwang.smartrefresh.layout.constant.DimensionStatus r4 = r0.mHeaderHeightStatus
            com.scwang.smartrefresh.layout.constant.DimensionStatus r4 = r4.notified()
            r0.mHeaderHeightStatus = r4
            com.scwang.smartrefresh.layout.api.RefreshInternal r4 = r0.mRefreshHeader
            com.scwang.smartrefresh.layout.api.RefreshKernel r11 = r0.mKernel
            int r12 = r0.mHeaderHeight
            float r13 = r0.mHeaderMaxDragRate
            int r14 = r0.mHeaderHeight
            float r14 = (float) r14
            float r13 = r13 * r14
            int r13 = (int) r13
            r4.onInitialized(r11, r12, r13)
        L_0x0139:
            if (r3 == 0) goto L_0x0148
            boolean r4 = r0.mEnableRefresh
            boolean r4 = r0.isEnableRefreshOrLoadMore(r4)
            if (r4 == 0) goto L_0x0148
            int r4 = r10.getMeasuredHeight()
            int r8 = r8 + r4
        L_0x0148:
            com.scwang.smartrefresh.layout.api.RefreshInternal r4 = r0.mRefreshFooter
            if (r4 == 0) goto L_0x025d
            com.scwang.smartrefresh.layout.api.RefreshInternal r4 = r0.mRefreshFooter
            android.view.View r4 = r4.getView()
            if (r4 != r9) goto L_0x025d
            com.scwang.smartrefresh.layout.api.RefreshInternal r4 = r0.mRefreshFooter
            android.view.View r4 = r4.getView()
            android.view.ViewGroup$LayoutParams r10 = r4.getLayoutParams()
            com.scwang.smartrefresh.layout.SmartRefreshLayout$LayoutParams r10 = (com.scwang.smartrefresh.layout.SmartRefreshLayout.LayoutParams) r10
            int r11 = r10.leftMargin
            int r12 = r10.rightMargin
            int r11 = r11 + r12
            int r12 = r10.width
            int r11 = android.view.ViewGroup.getChildMeasureSpec(r1, r11, r12)
            int r14 = r0.mFooterHeight
            com.scwang.smartrefresh.layout.constant.DimensionStatus r12 = r0.mFooterHeightStatus
            int r12 = r12.ordinal()
            com.scwang.smartrefresh.layout.constant.DimensionStatus r13 = com.scwang.smartrefresh.layout.constant.DimensionStatus.XmlLayoutUnNotify
            int r13 = r13.ordinal()
            if (r12 >= r13) goto L_0x01ec
            int r12 = r10.height
            if (r12 <= 0) goto L_0x01a1
            int r12 = r10.height
            int r13 = r10.topMargin
            int r12 = r12 + r13
            int r13 = r10.bottomMargin
            int r14 = r12 + r13
            com.scwang.smartrefresh.layout.constant.DimensionStatus r12 = r0.mFooterHeightStatus
            com.scwang.smartrefresh.layout.constant.DimensionStatus r13 = com.scwang.smartrefresh.layout.constant.DimensionStatus.XmlExactUnNotify
            boolean r12 = r12.canReplaceWith(r13)
            if (r12 == 0) goto L_0x01ec
            int r12 = r10.height
            int r13 = r10.topMargin
            int r12 = r12 + r13
            int r13 = r10.bottomMargin
            int r12 = r12 + r13
            r0.mFooterHeight = r12
            com.scwang.smartrefresh.layout.constant.DimensionStatus r12 = com.scwang.smartrefresh.layout.constant.DimensionStatus.XmlExactUnNotify
            r0.mFooterHeightStatus = r12
            goto L_0x01ec
        L_0x01a1:
            int r12 = r10.height
            r13 = -2
            if (r12 != r13) goto L_0x01ec
            com.scwang.smartrefresh.layout.api.RefreshInternal r12 = r0.mRefreshFooter
            com.scwang.smartrefresh.layout.constant.SpinnerStyle r12 = r12.getSpinnerStyle()
            com.scwang.smartrefresh.layout.constant.SpinnerStyle r13 = com.scwang.smartrefresh.layout.constant.SpinnerStyle.MatchLayout
            if (r12 != r13) goto L_0x01b6
            com.scwang.smartrefresh.layout.constant.DimensionStatus r12 = r0.mFooterHeightStatus
            boolean r12 = r12.notified
            if (r12 != 0) goto L_0x01ec
        L_0x01b6:
            int r12 = android.view.View.MeasureSpec.getSize(r19)
            int r13 = r10.bottomMargin
            int r12 = r12 - r13
            int r13 = r10.topMargin
            int r12 = r12 - r13
            int r12 = java.lang.Math.max(r12, r5)
            r13 = -2147483648(0xffffffff80000000, float:-0.0)
            int r13 = android.view.View.MeasureSpec.makeMeasureSpec(r12, r13)
            r4.measure(r11, r13)
            int r13 = r4.getMeasuredHeight()
            if (r13 <= 0) goto L_0x01ec
            if (r13 == r12) goto L_0x01eb
            com.scwang.smartrefresh.layout.constant.DimensionStatus r12 = r0.mFooterHeightStatus
            com.scwang.smartrefresh.layout.constant.DimensionStatus r14 = com.scwang.smartrefresh.layout.constant.DimensionStatus.XmlWrapUnNotify
            boolean r12 = r12.canReplaceWith(r14)
            if (r12 == 0) goto L_0x01eb
            int r12 = r10.topMargin
            int r13 = r13 + r12
            int r12 = r10.bottomMargin
            int r13 = r13 + r12
            r0.mFooterHeight = r13
            com.scwang.smartrefresh.layout.constant.DimensionStatus r12 = com.scwang.smartrefresh.layout.constant.DimensionStatus.XmlWrapUnNotify
            r0.mFooterHeightStatus = r12
        L_0x01eb:
            r14 = -1
        L_0x01ec:
            com.scwang.smartrefresh.layout.api.RefreshInternal r12 = r0.mRefreshFooter
            com.scwang.smartrefresh.layout.constant.SpinnerStyle r12 = r12.getSpinnerStyle()
            com.scwang.smartrefresh.layout.constant.SpinnerStyle r13 = com.scwang.smartrefresh.layout.constant.SpinnerStyle.MatchLayout
            if (r12 != r13) goto L_0x01fc
            int r14 = android.view.View.MeasureSpec.getSize(r19)
        L_0x01fa:
            r12 = -1
            goto L_0x021a
        L_0x01fc:
            com.scwang.smartrefresh.layout.api.RefreshInternal r12 = r0.mRefreshFooter
            com.scwang.smartrefresh.layout.constant.SpinnerStyle r12 = r12.getSpinnerStyle()
            com.scwang.smartrefresh.layout.constant.SpinnerStyle r13 = com.scwang.smartrefresh.layout.constant.SpinnerStyle.Scale
            if (r12 != r13) goto L_0x01fa
            if (r3 != 0) goto L_0x01fa
            boolean r12 = r0.mEnableLoadMore
            boolean r12 = r0.isEnableRefreshOrLoadMore(r12)
            if (r12 == 0) goto L_0x0214
            int r12 = r0.mSpinner
            int r12 = -r12
            goto L_0x0215
        L_0x0214:
            r12 = 0
        L_0x0215:
            int r14 = java.lang.Math.max(r5, r12)
            goto L_0x01fa
        L_0x021a:
            if (r14 == r12) goto L_0x022f
            int r12 = r10.bottomMargin
            int r14 = r14 - r12
            int r10 = r10.topMargin
            int r14 = r14 - r10
            int r10 = java.lang.Math.max(r14, r5)
            r12 = 1073741824(0x40000000, float:2.0)
            int r10 = android.view.View.MeasureSpec.makeMeasureSpec(r10, r12)
            r4.measure(r11, r10)
        L_0x022f:
            com.scwang.smartrefresh.layout.constant.DimensionStatus r10 = r0.mFooterHeightStatus
            boolean r10 = r10.notified
            if (r10 != 0) goto L_0x024e
            com.scwang.smartrefresh.layout.constant.DimensionStatus r10 = r0.mFooterHeightStatus
            com.scwang.smartrefresh.layout.constant.DimensionStatus r10 = r10.notified()
            r0.mFooterHeightStatus = r10
            com.scwang.smartrefresh.layout.api.RefreshInternal r10 = r0.mRefreshFooter
            com.scwang.smartrefresh.layout.api.RefreshKernel r11 = r0.mKernel
            int r12 = r0.mFooterHeight
            float r13 = r0.mFooterMaxDragRate
            int r14 = r0.mFooterHeight
            float r14 = (float) r14
            float r13 = r13 * r14
            int r13 = (int) r13
            r10.onInitialized(r11, r12, r13)
        L_0x024e:
            if (r3 == 0) goto L_0x025d
            boolean r10 = r0.mEnableLoadMore
            boolean r10 = r0.isEnableRefreshOrLoadMore(r10)
            if (r10 == 0) goto L_0x025d
            int r4 = r4.getMeasuredHeight()
            int r8 = r8 + r4
        L_0x025d:
            com.scwang.smartrefresh.layout.api.RefreshContent r4 = r0.mRefreshContent
            if (r4 == 0) goto L_0x02eb
            com.scwang.smartrefresh.layout.api.RefreshContent r4 = r0.mRefreshContent
            android.view.View r4 = r4.getView()
            if (r4 != r9) goto L_0x02eb
            com.scwang.smartrefresh.layout.api.RefreshContent r4 = r0.mRefreshContent
            android.view.View r4 = r4.getView()
            android.view.ViewGroup$LayoutParams r9 = r4.getLayoutParams()
            com.scwang.smartrefresh.layout.SmartRefreshLayout$LayoutParams r9 = (com.scwang.smartrefresh.layout.SmartRefreshLayout.LayoutParams) r9
            com.scwang.smartrefresh.layout.api.RefreshInternal r10 = r0.mRefreshHeader
            if (r10 == 0) goto L_0x028d
            boolean r10 = r0.mEnableRefresh
            boolean r10 = r0.isEnableRefreshOrLoadMore(r10)
            if (r10 == 0) goto L_0x028d
            boolean r10 = r0.mEnableHeaderTranslationContent
            com.scwang.smartrefresh.layout.api.RefreshInternal r11 = r0.mRefreshHeader
            boolean r10 = r0.isEnableTranslationContent(r10, r11)
            if (r10 == 0) goto L_0x028d
            r10 = 1
            goto L_0x028e
        L_0x028d:
            r10 = 0
        L_0x028e:
            com.scwang.smartrefresh.layout.api.RefreshInternal r11 = r0.mRefreshFooter
            if (r11 == 0) goto L_0x02a6
            boolean r11 = r0.mEnableLoadMore
            boolean r11 = r0.isEnableRefreshOrLoadMore(r11)
            if (r11 == 0) goto L_0x02a6
            boolean r11 = r0.mEnableFooterTranslationContent
            com.scwang.smartrefresh.layout.api.RefreshInternal r12 = r0.mRefreshFooter
            boolean r11 = r0.isEnableTranslationContent(r11, r12)
            if (r11 == 0) goto L_0x02a6
            r11 = 1
            goto L_0x02a7
        L_0x02a6:
            r11 = 0
        L_0x02a7:
            int r12 = r17.getPaddingLeft()
            int r13 = r17.getPaddingRight()
            int r12 = r12 + r13
            int r13 = r9.leftMargin
            int r12 = r12 + r13
            int r13 = r9.rightMargin
            int r12 = r12 + r13
            int r13 = r9.width
            int r12 = android.view.ViewGroup.getChildMeasureSpec(r1, r12, r13)
            int r13 = r17.getPaddingTop()
            int r14 = r17.getPaddingBottom()
            int r13 = r13 + r14
            int r14 = r9.topMargin
            int r13 = r13 + r14
            int r14 = r9.bottomMargin
            int r13 = r13 + r14
            if (r3 == 0) goto L_0x02d2
            if (r10 == 0) goto L_0x02d2
            int r10 = r0.mHeaderHeight
            goto L_0x02d3
        L_0x02d2:
            r10 = 0
        L_0x02d3:
            int r13 = r13 + r10
            if (r3 == 0) goto L_0x02db
            if (r11 == 0) goto L_0x02db
            int r10 = r0.mFooterHeight
            goto L_0x02dc
        L_0x02db:
            r10 = 0
        L_0x02dc:
            int r13 = r13 + r10
            int r9 = r9.height
            int r9 = android.view.ViewGroup.getChildMeasureSpec(r2, r13, r9)
            r4.measure(r12, r9)
            int r4 = r4.getMeasuredHeight()
            int r8 = r8 + r4
        L_0x02eb:
            int r7 = r7 + 1
            goto L_0x001a
        L_0x02ef:
            int r3 = super.getSuggestedMinimumWidth()
            int r1 = android.view.View.resolveSize(r3, r1)
            int r2 = android.view.View.resolveSize(r8, r2)
            super.setMeasuredDimension(r1, r2)
            int r1 = r17.getMeasuredWidth()
            int r1 = r1 / 2
            float r1 = (float) r1
            r0.mLastTouchX = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scwang.smartrefresh.layout.SmartRefreshLayout.onMeasure(int, int):void");
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        getPaddingBottom();
        int childCount = super.getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = super.getChildAt(i5);
            if (!(childAt.getVisibility() == 8 || childAt.getTag(R.string.srl_component_falsify) == childAt)) {
                boolean z2 = true;
                if (this.mRefreshContent != null && this.mRefreshContent.getView() == childAt) {
                    boolean z3 = isInEditMode() && this.mEnablePreviewInEditMode && isEnableRefreshOrLoadMore(this.mEnableRefresh) && this.mRefreshHeader != null;
                    View view = this.mRefreshContent.getView();
                    LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                    int i6 = layoutParams.leftMargin + paddingLeft;
                    int i7 = layoutParams.topMargin + paddingTop;
                    int measuredWidth = view.getMeasuredWidth() + i6;
                    int measuredHeight = view.getMeasuredHeight() + i7;
                    if (z3 && isEnableTranslationContent(this.mEnableHeaderTranslationContent, this.mRefreshHeader)) {
                        i7 += this.mHeaderHeight;
                        measuredHeight += this.mHeaderHeight;
                    }
                    view.layout(i6, i7, measuredWidth, measuredHeight);
                }
                if (this.mRefreshHeader != null && this.mRefreshHeader.getView() == childAt) {
                    boolean z4 = isInEditMode() && this.mEnablePreviewInEditMode && isEnableRefreshOrLoadMore(this.mEnableRefresh);
                    View view2 = this.mRefreshHeader.getView();
                    LayoutParams layoutParams2 = (LayoutParams) view2.getLayoutParams();
                    int i8 = layoutParams2.leftMargin;
                    int i9 = layoutParams2.topMargin + this.mHeaderInsetStart;
                    int measuredWidth2 = view2.getMeasuredWidth() + i8;
                    int measuredHeight2 = view2.getMeasuredHeight() + i9;
                    if (!z4 && this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.Translate) {
                        i9 -= this.mHeaderHeight;
                        measuredHeight2 -= this.mHeaderHeight;
                    }
                    view2.layout(i8, i9, measuredWidth2, measuredHeight2);
                }
                if (this.mRefreshFooter != null && this.mRefreshFooter.getView() == childAt) {
                    if (!isInEditMode() || !this.mEnablePreviewInEditMode || !isEnableRefreshOrLoadMore(this.mEnableLoadMore)) {
                        z2 = false;
                    }
                    View view3 = this.mRefreshFooter.getView();
                    LayoutParams layoutParams3 = (LayoutParams) view3.getLayoutParams();
                    SpinnerStyle spinnerStyle = this.mRefreshFooter.getSpinnerStyle();
                    int i10 = layoutParams3.leftMargin;
                    int measuredHeight3 = (layoutParams3.topMargin + getMeasuredHeight()) - this.mFooterInsetStart;
                    if (spinnerStyle == SpinnerStyle.MatchLayout) {
                        measuredHeight3 = layoutParams3.topMargin - this.mFooterInsetStart;
                    } else if (z2 || spinnerStyle == SpinnerStyle.FixedFront || spinnerStyle == SpinnerStyle.FixedBehind) {
                        measuredHeight3 -= this.mFooterHeight;
                    } else if (spinnerStyle == SpinnerStyle.Scale && this.mSpinner < 0) {
                        measuredHeight3 -= Math.max(isEnableRefreshOrLoadMore(this.mEnableLoadMore) ? -this.mSpinner : 0, 0);
                    }
                    view3.layout(i10, measuredHeight3, view3.getMeasuredWidth() + i10, view3.getMeasuredHeight() + measuredHeight3);
                }
            }
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

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View view, long j) {
        View view2 = this.mRefreshContent != null ? this.mRefreshContent.getView() : null;
        if (this.mRefreshHeader != null && this.mRefreshHeader.getView() == view) {
            if (!isEnableRefreshOrLoadMore(this.mEnableRefresh) || (!this.mEnablePreviewInEditMode && isInEditMode())) {
                return true;
            }
            if (view2 != null) {
                int max = Math.max(view2.getTop() + view2.getPaddingTop() + this.mSpinner, view.getTop());
                if (!(this.mHeaderBackgroundColor == 0 || this.mPaint == null)) {
                    this.mPaint.setColor(this.mHeaderBackgroundColor);
                    if (this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.Scale) {
                        max = view.getBottom();
                    } else if (this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.Translate) {
                        max = view.getBottom() + this.mSpinner;
                    }
                    canvas.drawRect((float) view.getLeft(), (float) view.getTop(), (float) view.getRight(), (float) max, this.mPaint);
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
            if (!isEnableRefreshOrLoadMore(this.mEnableLoadMore) || (!this.mEnablePreviewInEditMode && isInEditMode())) {
                return true;
            }
            if (view2 != null) {
                int min = Math.min((view2.getBottom() - view2.getPaddingBottom()) + this.mSpinner, view.getBottom());
                if (!(this.mFooterBackgroundColor == 0 || this.mPaint == null)) {
                    this.mPaint.setColor(this.mFooterBackgroundColor);
                    if (this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.Scale) {
                        min = view.getTop();
                    } else if (this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.Translate) {
                        min = view.getTop() + this.mSpinner;
                    }
                    canvas.drawRect((float) view.getLeft(), (float) min, (float) view.getRight(), (float) view.getBottom(), this.mPaint);
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

    public void computeScroll() {
        float f;
        this.mScroller.getCurrY();
        if (this.mScroller.computeScrollOffset()) {
            int finalY = this.mScroller.getFinalY();
            if ((finalY >= 0 || !this.mEnableRefresh || !this.mRefreshContent.canRefresh()) && (finalY <= 0 || !this.mEnableLoadMore || !this.mRefreshContent.canLoadMore())) {
                this.mVerticalPermit = true;
                invalidate();
                return;
            }
            if (this.mVerticalPermit) {
                if (Build.VERSION.SDK_INT >= 14) {
                    f = finalY > 0 ? -this.mScroller.getCurrVelocity() : this.mScroller.getCurrVelocity();
                } else {
                    f = (((float) (this.mScroller.getCurrY() - finalY)) * 1.0f) / ((float) Math.max(this.mScroller.getDuration() - this.mScroller.timePassed(), 1));
                }
                animSpinnerBounce(f);
            }
            this.mScroller.forceFinished(true);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:188:0x02a3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean dispatchTouchEvent(android.view.MotionEvent r23) {
        /*
            r22 = this;
            r0 = r22
            r1 = r23
            int r6 = r23.getActionMasked()
            r2 = 6
            r10 = 0
            r11 = 1
            if (r6 != r2) goto L_0x000f
            r3 = 1
            goto L_0x0010
        L_0x000f:
            r3 = 0
        L_0x0010:
            if (r3 == 0) goto L_0x0017
            int r4 = r23.getActionIndex()
            goto L_0x0018
        L_0x0017:
            r4 = -1
        L_0x0018:
            int r5 = r23.getPointerCount()
            r7 = 0
            r8 = 0
            r9 = 0
            r12 = 0
        L_0x0020:
            if (r8 >= r5) goto L_0x0032
            if (r4 != r8) goto L_0x0025
            goto L_0x002f
        L_0x0025:
            float r13 = r1.getX(r8)
            float r9 = r9 + r13
            float r13 = r1.getY(r8)
            float r12 = r12 + r13
        L_0x002f:
            int r8 = r8 + 1
            goto L_0x0020
        L_0x0032:
            if (r3 == 0) goto L_0x0036
            int r5 = r5 + -1
        L_0x0036:
            float r3 = (float) r5
            float r9 = r9 / r3
            float r8 = r12 / r3
            r3 = 5
            if (r6 == r2) goto L_0x003f
            if (r6 != r3) goto L_0x004c
        L_0x003f:
            boolean r4 = r0.mIsBeingDragged
            if (r4 == 0) goto L_0x004c
            float r4 = r0.mTouchY
            float r5 = r0.mLastTouchY
            float r5 = r8 - r5
            float r4 = r4 + r5
            r0.mTouchY = r4
        L_0x004c:
            r0.mLastTouchX = r9
            r0.mLastTouchY = r8
            boolean r4 = r0.mNestedInProgress
            if (r4 == 0) goto L_0x00ac
            int r2 = r0.mTotalUnconsumed
            boolean r1 = super.dispatchTouchEvent(r23)
            r3 = 2
            if (r6 != r3) goto L_0x00ab
            int r3 = r0.mTotalUnconsumed
            if (r2 != r3) goto L_0x00ab
            float r2 = r0.mLastTouchX
            int r2 = (int) r2
            int r3 = r22.getWidth()
            float r4 = r0.mLastTouchX
            if (r3 != 0) goto L_0x006d
            goto L_0x006e
        L_0x006d:
            r11 = r3
        L_0x006e:
            float r5 = (float) r11
            float r4 = r4 / r5
            boolean r5 = r0.mEnableRefresh
            boolean r5 = r0.isEnableRefreshOrLoadMore(r5)
            if (r5 == 0) goto L_0x008e
            int r5 = r0.mSpinner
            if (r5 <= 0) goto L_0x008e
            com.scwang.smartrefresh.layout.api.RefreshInternal r5 = r0.mRefreshHeader
            if (r5 == 0) goto L_0x008e
            com.scwang.smartrefresh.layout.api.RefreshInternal r5 = r0.mRefreshHeader
            boolean r5 = r5.isSupportHorizontalDrag()
            if (r5 == 0) goto L_0x008e
            com.scwang.smartrefresh.layout.api.RefreshInternal r5 = r0.mRefreshHeader
            r5.onHorizontalDrag(r4, r2, r3)
            goto L_0x00ab
        L_0x008e:
            boolean r5 = r0.mEnableLoadMore
            boolean r5 = r0.isEnableRefreshOrLoadMore(r5)
            if (r5 == 0) goto L_0x00ab
            int r5 = r0.mSpinner
            if (r5 >= 0) goto L_0x00ab
            com.scwang.smartrefresh.layout.api.RefreshInternal r5 = r0.mRefreshFooter
            if (r5 == 0) goto L_0x00ab
            com.scwang.smartrefresh.layout.api.RefreshInternal r5 = r0.mRefreshFooter
            boolean r5 = r5.isSupportHorizontalDrag()
            if (r5 == 0) goto L_0x00ab
            com.scwang.smartrefresh.layout.api.RefreshInternal r5 = r0.mRefreshFooter
            r5.onHorizontalDrag(r4, r2, r3)
        L_0x00ab:
            return r1
        L_0x00ac:
            boolean r4 = r22.isEnabled()
            if (r4 == 0) goto L_0x0358
            boolean r4 = r0.mEnableRefresh
            if (r4 != 0) goto L_0x00ba
            boolean r4 = r0.mEnableLoadMore
            if (r4 == 0) goto L_0x0358
        L_0x00ba:
            boolean r4 = r0.mHeaderNeedTouchEventWhenRefreshing
            if (r4 == 0) goto L_0x00d0
            com.scwang.smartrefresh.layout.constant.RefreshState r4 = r0.mState
            boolean r4 = r4.isOpening
            if (r4 != 0) goto L_0x00ca
            com.scwang.smartrefresh.layout.constant.RefreshState r4 = r0.mState
            boolean r4 = r4.isFinishing
            if (r4 == 0) goto L_0x00d0
        L_0x00ca:
            com.scwang.smartrefresh.layout.constant.RefreshState r4 = r0.mState
            boolean r4 = r4.isHeader
            if (r4 != 0) goto L_0x0358
        L_0x00d0:
            boolean r4 = r0.mFooterNeedTouchEventWhenLoading
            if (r4 == 0) goto L_0x00e8
            com.scwang.smartrefresh.layout.constant.RefreshState r4 = r0.mState
            boolean r4 = r4.isOpening
            if (r4 != 0) goto L_0x00e0
            com.scwang.smartrefresh.layout.constant.RefreshState r4 = r0.mState
            boolean r4 = r4.isFinishing
            if (r4 == 0) goto L_0x00e8
        L_0x00e0:
            com.scwang.smartrefresh.layout.constant.RefreshState r4 = r0.mState
            boolean r4 = r4.isFooter
            if (r4 == 0) goto L_0x00e8
            goto L_0x0358
        L_0x00e8:
            boolean r4 = r0.interceptAnimatorByAction(r6)
            if (r4 != 0) goto L_0x0357
            com.scwang.smartrefresh.layout.constant.RefreshState r4 = r0.mState
            boolean r4 = r4.isFinishing
            if (r4 != 0) goto L_0x0357
            com.scwang.smartrefresh.layout.constant.RefreshState r4 = r0.mState
            com.scwang.smartrefresh.layout.constant.RefreshState r5 = com.scwang.smartrefresh.layout.constant.RefreshState.Loading
            if (r4 != r5) goto L_0x00fe
            boolean r4 = r0.mDisableContentWhenLoading
            if (r4 != 0) goto L_0x0357
        L_0x00fe:
            com.scwang.smartrefresh.layout.constant.RefreshState r4 = r0.mState
            com.scwang.smartrefresh.layout.constant.RefreshState r5 = com.scwang.smartrefresh.layout.constant.RefreshState.Refreshing
            if (r4 != r5) goto L_0x010a
            boolean r4 = r0.mDisableContentWhenRefresh
            if (r4 == 0) goto L_0x010a
            goto L_0x0357
        L_0x010a:
            r4 = 104(0x68, float:1.46E-43)
            r5 = 0
            switch(r6) {
                case 0: goto L_0x0311;
                case 1: goto L_0x02c6;
                case 2: goto L_0x0112;
                case 3: goto L_0x02e1;
                default: goto L_0x0110;
            }
        L_0x0110:
            goto L_0x0352
        L_0x0112:
            float r2 = r0.mTouchX
            float r9 = r9 - r2
            float r2 = r0.mTouchY
            float r2 = r8 - r2
            android.view.VelocityTracker r3 = r0.mVelocityTracker
            r3.addMovement(r1)
            boolean r3 = r0.mIsBeingDragged
            r6 = 3
            if (r3 != 0) goto L_0x01e0
            char r3 = r0.mDragDirection
            if (r3 == r4) goto L_0x01e0
            com.scwang.smartrefresh.layout.api.RefreshContent r3 = r0.mRefreshContent
            if (r3 == 0) goto L_0x01e0
            char r3 = r0.mDragDirection
            r12 = 118(0x76, float:1.65E-43)
            if (r3 == r12) goto L_0x0168
            float r3 = java.lang.Math.abs(r2)
            int r13 = r0.mTouchSlop
            float r13 = (float) r13
            int r3 = (r3 > r13 ? 1 : (r3 == r13 ? 0 : -1))
            if (r3 < 0) goto L_0x0149
            float r3 = java.lang.Math.abs(r9)
            float r13 = java.lang.Math.abs(r2)
            int r3 = (r3 > r13 ? 1 : (r3 == r13 ? 0 : -1))
            if (r3 >= 0) goto L_0x0149
            goto L_0x0168
        L_0x0149:
            float r3 = java.lang.Math.abs(r9)
            int r13 = r0.mTouchSlop
            float r13 = (float) r13
            int r3 = (r3 > r13 ? 1 : (r3 == r13 ? 0 : -1))
            if (r3 < 0) goto L_0x01e0
            float r3 = java.lang.Math.abs(r9)
            float r13 = java.lang.Math.abs(r2)
            int r3 = (r3 > r13 ? 1 : (r3 == r13 ? 0 : -1))
            if (r3 <= 0) goto L_0x01e0
            char r3 = r0.mDragDirection
            if (r3 == r12) goto L_0x01e0
            r0.mDragDirection = r4
            goto L_0x01e0
        L_0x0168:
            r0.mDragDirection = r12
            int r3 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r3 <= 0) goto L_0x0188
            int r3 = r0.mSpinner
            if (r3 < 0) goto L_0x017e
            boolean r3 = r0.mEnableRefresh
            if (r3 == 0) goto L_0x0188
            com.scwang.smartrefresh.layout.api.RefreshContent r3 = r0.mRefreshContent
            boolean r3 = r3.canRefresh()
            if (r3 == 0) goto L_0x0188
        L_0x017e:
            r0.mIsBeingDragged = r11
            int r3 = r0.mTouchSlop
            float r3 = (float) r3
            float r3 = r8 - r3
            r0.mTouchY = r3
            goto L_0x01ae
        L_0x0188:
            int r3 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r3 >= 0) goto L_0x01ae
            int r3 = r0.mSpinner
            if (r3 > 0) goto L_0x01a6
            boolean r3 = r0.mEnableLoadMore
            if (r3 == 0) goto L_0x01ae
            com.scwang.smartrefresh.layout.constant.RefreshState r3 = r0.mState
            com.scwang.smartrefresh.layout.constant.RefreshState r4 = com.scwang.smartrefresh.layout.constant.RefreshState.Loading
            if (r3 != r4) goto L_0x019e
            boolean r3 = r0.mFooterLocked
            if (r3 != 0) goto L_0x01a6
        L_0x019e:
            com.scwang.smartrefresh.layout.api.RefreshContent r3 = r0.mRefreshContent
            boolean r3 = r3.canLoadMore()
            if (r3 == 0) goto L_0x01ae
        L_0x01a6:
            r0.mIsBeingDragged = r11
            int r3 = r0.mTouchSlop
            float r3 = (float) r3
            float r3 = r3 + r8
            r0.mTouchY = r3
        L_0x01ae:
            boolean r3 = r0.mIsBeingDragged
            if (r3 == 0) goto L_0x01e0
            float r2 = r0.mTouchY
            float r2 = r8 - r2
            boolean r3 = r0.mSuperDispatchTouchEvent
            if (r3 == 0) goto L_0x01c0
            r1.setAction(r6)
            super.dispatchTouchEvent(r23)
        L_0x01c0:
            com.scwang.smartrefresh.layout.api.RefreshKernel r3 = r0.mKernel
            int r4 = r0.mSpinner
            if (r4 > 0) goto L_0x01d2
            int r4 = r0.mSpinner
            if (r4 != 0) goto L_0x01cf
            int r4 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r4 <= 0) goto L_0x01cf
            goto L_0x01d2
        L_0x01cf:
            com.scwang.smartrefresh.layout.constant.RefreshState r4 = com.scwang.smartrefresh.layout.constant.RefreshState.PullUpToLoad
            goto L_0x01d4
        L_0x01d2:
            com.scwang.smartrefresh.layout.constant.RefreshState r4 = com.scwang.smartrefresh.layout.constant.RefreshState.PullDownToRefresh
        L_0x01d4:
            r3.setState(r4)
            android.view.ViewParent r3 = r22.getParent()
            if (r3 == 0) goto L_0x01e0
            r3.requestDisallowInterceptTouchEvent(r11)
        L_0x01e0:
            boolean r3 = r0.mIsBeingDragged
            if (r3 == 0) goto L_0x02b3
            int r3 = (int) r2
            int r4 = r0.mTouchSpinner
            int r3 = r3 + r4
            com.scwang.smartrefresh.layout.constant.RefreshState r4 = r0.mViceState
            boolean r4 = r4.isHeader
            if (r4 == 0) goto L_0x01f4
            if (r3 < 0) goto L_0x0200
            int r4 = r0.mLastSpinner
            if (r4 < 0) goto L_0x0200
        L_0x01f4:
            com.scwang.smartrefresh.layout.constant.RefreshState r4 = r0.mViceState
            boolean r4 = r4.isFooter
            if (r4 == 0) goto L_0x02ae
            if (r3 > 0) goto L_0x0200
            int r4 = r0.mLastSpinner
            if (r4 <= 0) goto L_0x02ae
        L_0x0200:
            r0.mLastSpinner = r3
            long r20 = r23.getEventTime()
            android.view.MotionEvent r1 = r0.mFalsifyEvent
            if (r1 != 0) goto L_0x0225
            r16 = 0
            float r1 = r0.mTouchX
            float r17 = r1 + r9
            float r1 = r0.mTouchY
            r19 = 0
            r12 = r20
            r14 = r20
            r18 = r1
            android.view.MotionEvent r1 = android.view.MotionEvent.obtain(r12, r14, r16, r17, r18, r19)
            r0.mFalsifyEvent = r1
            android.view.MotionEvent r1 = r0.mFalsifyEvent
            super.dispatchTouchEvent(r1)
        L_0x0225:
            r16 = 2
            float r1 = r0.mTouchX
            float r17 = r1 + r9
            float r1 = r0.mTouchY
            float r4 = (float) r3
            float r18 = r1 + r4
            r19 = 0
            r12 = r20
            r14 = r20
            android.view.MotionEvent r1 = android.view.MotionEvent.obtain(r12, r14, r16, r17, r18, r19)
            super.dispatchTouchEvent(r1)
            boolean r4 = r0.mFooterLocked
            if (r4 == 0) goto L_0x024e
            int r4 = r0.mTouchSlop
            float r4 = (float) r4
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 <= 0) goto L_0x024e
            int r2 = r0.mSpinner
            if (r2 >= 0) goto L_0x024e
            r0.mFooterLocked = r10
        L_0x024e:
            if (r3 <= 0) goto L_0x026b
            boolean r2 = r0.mEnableRefresh
            if (r2 == 0) goto L_0x026b
            com.scwang.smartrefresh.layout.api.RefreshContent r2 = r0.mRefreshContent
            boolean r2 = r2.canRefresh()
            if (r2 == 0) goto L_0x026b
            r0.mLastTouchY = r8
            r0.mTouchY = r8
            r0.mTouchSpinner = r10
            com.scwang.smartrefresh.layout.api.RefreshKernel r2 = r0.mKernel
            com.scwang.smartrefresh.layout.constant.RefreshState r3 = com.scwang.smartrefresh.layout.constant.RefreshState.PullDownToRefresh
            r2.setState(r3)
        L_0x0269:
            r3 = 0
            goto L_0x0287
        L_0x026b:
            if (r3 >= 0) goto L_0x0287
            boolean r2 = r0.mEnableLoadMore
            if (r2 == 0) goto L_0x0287
            com.scwang.smartrefresh.layout.api.RefreshContent r2 = r0.mRefreshContent
            boolean r2 = r2.canLoadMore()
            if (r2 == 0) goto L_0x0287
            r0.mLastTouchY = r8
            r0.mTouchY = r8
            r0.mTouchSpinner = r10
            com.scwang.smartrefresh.layout.api.RefreshKernel r2 = r0.mKernel
            com.scwang.smartrefresh.layout.constant.RefreshState r3 = com.scwang.smartrefresh.layout.constant.RefreshState.PullUpToLoad
            r2.setState(r3)
            goto L_0x0269
        L_0x0287:
            com.scwang.smartrefresh.layout.constant.RefreshState r2 = r0.mViceState
            boolean r2 = r2.isHeader
            if (r2 == 0) goto L_0x028f
            if (r3 < 0) goto L_0x0297
        L_0x028f:
            com.scwang.smartrefresh.layout.constant.RefreshState r2 = r0.mViceState
            boolean r2 = r2.isFooter
            if (r2 == 0) goto L_0x029f
            if (r3 <= 0) goto L_0x029f
        L_0x0297:
            int r1 = r0.mSpinner
            if (r1 == 0) goto L_0x029e
            r0.moveSpinnerInfinitely(r7)
        L_0x029e:
            return r11
        L_0x029f:
            android.view.MotionEvent r2 = r0.mFalsifyEvent
            if (r2 == 0) goto L_0x02ab
            r0.mFalsifyEvent = r5
            r1.setAction(r6)
            super.dispatchTouchEvent(r1)
        L_0x02ab:
            r1.recycle()
        L_0x02ae:
            float r1 = (float) r3
            r0.moveSpinnerInfinitely(r1)
            return r11
        L_0x02b3:
            boolean r3 = r0.mFooterLocked
            if (r3 == 0) goto L_0x0352
            int r3 = r0.mTouchSlop
            float r3 = (float) r3
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 <= 0) goto L_0x0352
            int r2 = r0.mSpinner
            if (r2 >= 0) goto L_0x0352
            r0.mFooterLocked = r10
            goto L_0x0352
        L_0x02c6:
            android.view.VelocityTracker r2 = r0.mVelocityTracker
            r2.addMovement(r1)
            android.view.VelocityTracker r2 = r0.mVelocityTracker
            r3 = 1000(0x3e8, float:1.401E-42)
            int r4 = r0.mMaximumVelocity
            float r4 = (float) r4
            r2.computeCurrentVelocity(r3, r4)
            android.view.VelocityTracker r2 = r0.mVelocityTracker
            float r2 = r2.getYVelocity()
            int r2 = (int) r2
            r0.mCurrentVelocity = r2
            r0.startFlingIfNeed(r5)
        L_0x02e1:
            android.view.VelocityTracker r2 = r0.mVelocityTracker
            r2.clear()
            r2 = 110(0x6e, float:1.54E-43)
            r0.mDragDirection = r2
            android.view.MotionEvent r2 = r0.mFalsifyEvent
            if (r2 == 0) goto L_0x0307
            android.view.MotionEvent r2 = r0.mFalsifyEvent
            r2.recycle()
            r0.mFalsifyEvent = r5
            long r4 = r23.getEventTime()
            float r7 = r0.mTouchX
            r9 = 0
            r2 = r4
            android.view.MotionEvent r2 = android.view.MotionEvent.obtain(r2, r4, r6, r7, r8, r9)
            super.dispatchTouchEvent(r2)
            r2.recycle()
        L_0x0307:
            r22.overSpinner()
            boolean r2 = r0.mIsBeingDragged
            if (r2 == 0) goto L_0x0352
            r0.mIsBeingDragged = r10
            return r11
        L_0x0311:
            r0.mCurrentVelocity = r10
            android.view.VelocityTracker r5 = r0.mVelocityTracker
            r5.addMovement(r1)
            android.widget.Scroller r5 = r0.mScroller
            r5.forceFinished(r11)
            r0.mTouchX = r9
            r0.mTouchY = r8
            r0.mLastSpinner = r10
            int r5 = r0.mSpinner
            r0.mTouchSpinner = r5
            r0.mIsBeingDragged = r10
            boolean r5 = super.dispatchTouchEvent(r23)
            r0.mSuperDispatchTouchEvent = r5
            com.scwang.smartrefresh.layout.constant.RefreshState r5 = r0.mState
            com.scwang.smartrefresh.layout.constant.RefreshState r6 = com.scwang.smartrefresh.layout.constant.RefreshState.TwoLevel
            if (r5 != r6) goto L_0x0348
            float r5 = r0.mTouchY
            int r6 = r22.getMeasuredHeight()
            int r6 = r6 * 5
            int r6 = r6 / r2
            float r2 = (float) r6
            int r2 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r2 >= 0) goto L_0x0348
            r0.mDragDirection = r4
            boolean r1 = r0.mSuperDispatchTouchEvent
            return r1
        L_0x0348:
            com.scwang.smartrefresh.layout.api.RefreshContent r2 = r0.mRefreshContent
            if (r2 == 0) goto L_0x0351
            com.scwang.smartrefresh.layout.api.RefreshContent r2 = r0.mRefreshContent
            r2.onActionDown(r1)
        L_0x0351:
            return r11
        L_0x0352:
            boolean r1 = super.dispatchTouchEvent(r23)
            return r1
        L_0x0357:
            return r10
        L_0x0358:
            boolean r1 = super.dispatchTouchEvent(r23)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scwang.smartrefresh.layout.SmartRefreshLayout.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    /* access modifiers changed from: protected */
    public boolean startFlingIfNeed(Float f) {
        float floatValue = f == null ? (float) this.mCurrentVelocity : f.floatValue();
        if (Math.abs(floatValue) > ((float) this.mMinimumVelocity)) {
            if (((float) this.mSpinner) * floatValue < CropImageView.DEFAULT_ASPECT_RATIO) {
                if (this.mState == RefreshState.Refreshing || this.mState == RefreshState.Loading || (this.mSpinner < 0 && this.mFooterNoMoreData)) {
                    this.animationRunnable = new FlingRunnable(floatValue).start();
                    return true;
                } else if (this.mState.isReleaseToOpening) {
                    return true;
                }
            }
            if ((floatValue < CropImageView.DEFAULT_ASPECT_RATIO && ((this.mEnableOverScrollBounce && this.mEnableLoadMore) || ((this.mState == RefreshState.Loading && this.mSpinner >= 0) || (this.mEnableAutoLoadMore && isEnableRefreshOrLoadMore(this.mEnableLoadMore))))) || (floatValue > CropImageView.DEFAULT_ASPECT_RATIO && ((this.mEnableOverScrollBounce && this.mEnableRefresh) || (this.mState == RefreshState.Refreshing && this.mSpinner <= 0)))) {
                this.mVerticalPermit = false;
                this.mScroller.fling(0, 0, 0, (int) (-floatValue), 0, 0, -2147483647, Integer.MAX_VALUE);
                this.mScroller.computeScrollOffset();
                invalidate();
            }
        }
        return false;
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
        if (this.reboundAnimator != null) {
            return true;
        }
        return false;
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
        AnonymousClass1 r0 = new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                SmartRefreshLayout.this.setStateDirectLoading(true);
            }
        };
        notifyStateChanged(RefreshState.LoadReleased);
        ValueAnimator animSpinner = this.mKernel.animSpinner(-this.mFooterHeight);
        if (animSpinner != null) {
            animSpinner.addListener(r0);
        }
        if (this.mRefreshFooter != null) {
            this.mRefreshFooter.onReleased(this, this.mFooterHeight, (int) (this.mFooterMaxDragRate * ((float) this.mFooterHeight)));
        }
        if (this.mOnMultiPurposeListener != null && (this.mRefreshFooter instanceof RefreshFooter)) {
            this.mOnMultiPurposeListener.onFooterReleased((RefreshFooter) this.mRefreshFooter, this.mFooterHeight, (int) (this.mFooterMaxDragRate * ((float) this.mFooterHeight)));
        }
        if (animSpinner == null) {
            r0.onAnimationEnd((Animator) null);
        }
    }

    /* access modifiers changed from: protected */
    public void setStateRefreshing() {
        AnonymousClass2 r0 = new AnimatorListenerAdapter() {
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
            animSpinner.addListener(r0);
        }
        if (this.mRefreshHeader != null) {
            this.mRefreshHeader.onReleased(this, this.mHeaderHeight, (int) (this.mHeaderMaxDragRate * ((float) this.mHeaderHeight)));
        }
        if (this.mOnMultiPurposeListener != null && (this.mRefreshHeader instanceof RefreshHeader)) {
            this.mOnMultiPurposeListener.onHeaderReleased((RefreshHeader) this.mRefreshHeader, this.mHeaderHeight, (int) (this.mHeaderMaxDragRate * ((float) this.mHeaderHeight)));
        }
        if (animSpinner == null) {
            r0.onAnimationEnd((Animator) null);
        }
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
    public boolean isEnableTranslationContent(boolean z, RefreshInternal refreshInternal) {
        return z || this.mEnablePureScrollMode || refreshInternal == null || refreshInternal.getSpinnerStyle() == SpinnerStyle.FixedBehind;
    }

    /* access modifiers changed from: protected */
    public boolean isEnableRefreshOrLoadMore(boolean z) {
        return z && !this.mEnablePureScrollMode;
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

        public void run() {
            if (SmartRefreshLayout.this.animationRunnable == this && !SmartRefreshLayout.this.mState.isFinishing) {
                long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                double d = (double) this.mVelocity;
                double pow = Math.pow((double) this.mDamping, (double) ((currentAnimationTimeMillis - this.mStartTime) / ((long) (SocializeConstants.CANCLE_RESULTCODE / this.mFrameDelay))));
                Double.isNaN(d);
                this.mVelocity = (float) (d * pow);
                float f = this.mVelocity * ((((float) (currentAnimationTimeMillis - this.mLastTime)) * 1.0f) / 1000.0f);
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
    }

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
                float f = this.mVelocity * ((((float) (currentAnimationTimeMillis - this.mLastTime)) * 1.0f) / 1000.0f);
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

    /* access modifiers changed from: protected */
    public void moveSpinnerInfinitely(float f) {
        float f2 = (!this.mNestedInProgress || this.mEnableLoadMoreWhenContentNotFull || f >= CropImageView.DEFAULT_ASPECT_RATIO || this.mRefreshContent.canLoadMore()) ? f : CropImageView.DEFAULT_ASPECT_RATIO;
        if (this.mState == RefreshState.TwoLevel && f2 > CropImageView.DEFAULT_ASPECT_RATIO) {
            this.mKernel.moveSpinner(Math.min((int) f2, getMeasuredHeight()), true);
        } else if (this.mState != RefreshState.Refreshing || f2 < CropImageView.DEFAULT_ASPECT_RATIO) {
            if (f2 >= CropImageView.DEFAULT_ASPECT_RATIO || (this.mState != RefreshState.Loading && ((!this.mEnableFooterFollowWhenNoMoreData || !this.mFooterNoMoreData || !isEnableRefreshOrLoadMore(this.mEnableLoadMore)) && (!this.mEnableAutoLoadMore || this.mFooterNoMoreData || !isEnableRefreshOrLoadMore(this.mEnableLoadMore))))) {
                if (f2 >= CropImageView.DEFAULT_ASPECT_RATIO) {
                    double d = (double) (this.mHeaderMaxDragRate * ((float) this.mHeaderHeight));
                    double max = (double) Math.max(this.mScreenHeightPixels / 2, getHeight());
                    double max2 = (double) Math.max(CropImageView.DEFAULT_ASPECT_RATIO, this.mDragRate * f2);
                    Double.isNaN(max2);
                    double d2 = -max2;
                    if (max == 0.0d) {
                        max = 1.0d;
                    }
                    Double.isNaN(d);
                    this.mKernel.moveSpinner((int) Math.min(d * (1.0d - Math.pow(100.0d, d2 / max)), max2), true);
                } else {
                    double d3 = (double) (this.mFooterMaxDragRate * ((float) this.mFooterHeight));
                    double max3 = (double) Math.max(this.mScreenHeightPixels / 2, getHeight());
                    double d4 = (double) (-Math.min(CropImageView.DEFAULT_ASPECT_RATIO, this.mDragRate * f2));
                    Double.isNaN(d4);
                    double d5 = -d4;
                    if (max3 == 0.0d) {
                        max3 = 1.0d;
                    }
                    Double.isNaN(d3);
                    this.mKernel.moveSpinner((int) (-Math.min(d3 * (1.0d - Math.pow(100.0d, d5 / max3)), d4)), true);
                }
            } else if (f2 > ((float) (-this.mFooterHeight))) {
                this.mKernel.moveSpinner((int) f2, true);
            } else {
                double d6 = (double) ((this.mFooterMaxDragRate - 1.0f) * ((float) this.mFooterHeight));
                double max4 = (double) (Math.max((this.mScreenHeightPixels * 4) / 3, getHeight()) - this.mFooterHeight);
                double d7 = (double) (-Math.min(CropImageView.DEFAULT_ASPECT_RATIO, (((float) this.mFooterHeight) + f2) * this.mDragRate));
                Double.isNaN(d7);
                double d8 = -d7;
                if (max4 == 0.0d) {
                    max4 = 1.0d;
                }
                Double.isNaN(d6);
                this.mKernel.moveSpinner(((int) (-Math.min(d6 * (1.0d - Math.pow(100.0d, d8 / max4)), d7))) - this.mFooterHeight, true);
            }
        } else if (f2 < ((float) this.mHeaderHeight)) {
            this.mKernel.moveSpinner((int) f2, true);
        } else {
            double max5 = (double) (Math.max((this.mScreenHeightPixels * 4) / 3, getHeight()) - this.mHeaderHeight);
            double max6 = (double) Math.max(CropImageView.DEFAULT_ASPECT_RATIO, (f2 - ((float) this.mHeaderHeight)) * this.mDragRate);
            Double.isNaN(max6);
            double d9 = (double) ((this.mHeaderMaxDragRate - 1.0f) * ((float) this.mHeaderHeight));
            double d10 = -max6;
            if (max5 == 0.0d) {
                max5 = 1.0d;
            }
            Double.isNaN(d9);
            this.mKernel.moveSpinner(((int) Math.min(d9 * (1.0d - Math.pow(100.0d, d10 / max5)), max6)) + this.mHeaderHeight, true);
        }
        if (this.mEnableAutoLoadMore && !this.mFooterNoMoreData && isEnableRefreshOrLoadMore(this.mEnableLoadMore) && f2 < CropImageView.DEFAULT_ASPECT_RATIO && this.mState != RefreshState.Refreshing && this.mState != RefreshState.Loading && this.mState != RefreshState.LoadFinish) {
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
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int backgroundColor = 0;
        public SpinnerStyle spinnerStyle = null;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SmartRefreshLayout_Layout);
            this.backgroundColor = obtainStyledAttributes.getColor(R.styleable.SmartRefreshLayout_Layout_layout_srlBackgroundColor, this.backgroundColor);
            if (obtainStyledAttributes.hasValue(R.styleable.SmartRefreshLayout_Layout_layout_srlSpinnerStyle)) {
                this.spinnerStyle = SpinnerStyle.values()[obtainStyledAttributes.getInt(R.styleable.SmartRefreshLayout_Layout_layout_srlSpinnerStyle, SpinnerStyle.Translate.ordinal())];
            }
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public int getNestedScrollAxes() {
        return this.mNestedParent.getNestedScrollAxes();
    }

    public boolean onStartNestedScroll(@NonNull View view, @NonNull View view2, int i) {
        if (!(isEnabled() && isNestedScrollingEnabled() && (i & 2) != 0) || (!this.mEnableRefresh && !this.mEnableLoadMore)) {
            return false;
        }
        return true;
    }

    public void onNestedScrollAccepted(@NonNull View view, @NonNull View view2, int i) {
        this.mNestedParent.onNestedScrollAccepted(view, view2, i);
        this.mNestedChild.startNestedScroll(i & 2);
        this.mTotalUnconsumed = this.mSpinner;
        this.mNestedInProgress = true;
        interceptAnimatorByAction(0);
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
        iArr[1] = iArr[1] + i3;
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

    public boolean onNestedPreFling(@NonNull View view, float f, float f2) {
        return (this.mFooterLocked && f2 > CropImageView.DEFAULT_ASPECT_RATIO) || startFlingIfNeed(Float.valueOf(-f2)) || this.mNestedChild.dispatchNestedPreFling(f, f2);
    }

    public boolean onNestedFling(@NonNull View view, float f, float f2, boolean z) {
        return this.mNestedChild.dispatchNestedFling(f, f2, z);
    }

    public void onStopNestedScroll(@NonNull View view) {
        this.mNestedParent.onStopNestedScroll(view);
        this.mNestedInProgress = false;
        this.mTotalUnconsumed = 0;
        overSpinner();
        this.mNestedChild.stopNestedScroll();
    }

    public void setNestedScrollingEnabled(boolean z) {
        this.mNestedChild.setNestedScrollingEnabled(z);
    }

    public boolean isNestedScrollingEnabled() {
        return this.mNestedChild.isNestedScrollingEnabled();
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

    public SmartRefreshLayout setFooterInsetStart(float f) {
        this.mFooterInsetStart = DensityUtil.dp2px(f);
        return this;
    }

    public SmartRefreshLayout setDragRate(float f) {
        this.mDragRate = f;
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

    public SmartRefreshLayout setFooterMaxDragRate(float f) {
        this.mFooterMaxDragRate = f;
        if (this.mRefreshFooter == null || this.mHandler == null) {
            this.mFooterHeightStatus = this.mFooterHeightStatus.unNotify();
        } else {
            this.mRefreshFooter.onInitialized(this.mKernel, this.mFooterHeight, (int) (((float) this.mFooterHeight) * this.mFooterMaxDragRate));
        }
        return this;
    }

    public SmartRefreshLayout setHeaderTriggerRate(float f) {
        this.mHeaderTriggerRate = f;
        return this;
    }

    public SmartRefreshLayout setFooterTriggerRate(float f) {
        this.mFooterTriggerRate = f;
        return this;
    }

    public SmartRefreshLayout setReboundInterpolator(@NonNull Interpolator interpolator) {
        this.mReboundInterpolator = interpolator;
        return this;
    }

    public SmartRefreshLayout setReboundDuration(int i) {
        this.mReboundDuration = i;
        return this;
    }

    public SmartRefreshLayout setEnableLoadMore(boolean z) {
        this.mManualLoadMore = true;
        this.mEnableLoadMore = z;
        return this;
    }

    public SmartRefreshLayout setEnableRefresh(boolean z) {
        this.mEnableRefresh = z;
        return this;
    }

    public SmartRefreshLayout setEnableHeaderTranslationContent(boolean z) {
        this.mEnableHeaderTranslationContent = z;
        this.mManualHeaderTranslationContent = true;
        return this;
    }

    public SmartRefreshLayout setEnableFooterTranslationContent(boolean z) {
        this.mEnableFooterTranslationContent = z;
        this.mManualFooterTranslationContent = true;
        return this;
    }

    public SmartRefreshLayout setEnableAutoLoadMore(boolean z) {
        this.mEnableAutoLoadMore = z;
        return this;
    }

    public SmartRefreshLayout setEnableOverScrollBounce(boolean z) {
        this.mEnableOverScrollBounce = z;
        return this;
    }

    public SmartRefreshLayout setEnablePureScrollMode(boolean z) {
        this.mEnablePureScrollMode = z;
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

    public SmartRefreshLayout setEnableLoadMoreWhenContentNotFull(boolean z) {
        this.mEnableLoadMoreWhenContentNotFull = z;
        if (this.mRefreshContent != null) {
            this.mRefreshContent.setEnableLoadMoreWhenContentNotFull(z);
        }
        return this;
    }

    public SmartRefreshLayout setEnableOverScrollDrag(boolean z) {
        this.mEnableOverScrollDrag = z;
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

    public SmartRefreshLayout setEnableClipHeaderWhenFixedBehind(boolean z) {
        this.mEnableClipHeaderWhenFixedBehind = z;
        return this;
    }

    public SmartRefreshLayout setEnableClipFooterWhenFixedBehind(boolean z) {
        this.mEnableClipFooterWhenFixedBehind = z;
        return this;
    }

    public RefreshLayout setEnableNestedScroll(boolean z) {
        setNestedScrollingEnabled(z);
        return this;
    }

    public SmartRefreshLayout setDisableContentWhenRefresh(boolean z) {
        this.mDisableContentWhenRefresh = z;
        return this;
    }

    public SmartRefreshLayout setDisableContentWhenLoading(boolean z) {
        this.mDisableContentWhenLoading = z;
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

    public SmartRefreshLayout setRefreshContent(@NonNull View view) {
        return setRefreshContent(view, -1, -1);
    }

    public SmartRefreshLayout setRefreshContent(@NonNull View view, int i, int i2) {
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
            View view2 = null;
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

    public SmartRefreshLayout setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.mRefreshListener = onRefreshListener;
        return this;
    }

    public SmartRefreshLayout setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.mLoadMoreListener = onLoadMoreListener;
        this.mEnableLoadMore = this.mEnableLoadMore || (!this.mManualLoadMore && onLoadMoreListener != null);
        return this;
    }

    public SmartRefreshLayout setOnRefreshLoadMoreListener(OnRefreshLoadMoreListener onRefreshLoadMoreListener) {
        this.mRefreshListener = onRefreshLoadMoreListener;
        this.mLoadMoreListener = onRefreshLoadMoreListener;
        this.mEnableLoadMore = this.mEnableLoadMore || (!this.mManualLoadMore && onRefreshLoadMoreListener != null);
        return this;
    }

    public SmartRefreshLayout setOnMultiPurposeListener(OnMultiPurposeListener onMultiPurposeListener) {
        this.mOnMultiPurposeListener = onMultiPurposeListener;
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

    public SmartRefreshLayout setScrollBoundaryDecider(ScrollBoundaryDecider scrollBoundaryDecider) {
        this.mScrollBoundaryDecider = scrollBoundaryDecider;
        if (this.mRefreshContent != null) {
            this.mRefreshContent.setScrollBoundaryDecider(scrollBoundaryDecider);
        }
        return this;
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

    public RefreshLayout resetNoMoreData() {
        this.mFooterNoMoreData = false;
        if ((this.mRefreshFooter instanceof RefreshFooter) && !((RefreshFooter) this.mRefreshFooter).setNoMoreData(false)) {
            PrintStream printStream = System.out;
            printStream.println("Footer:" + this.mRefreshFooter + " NoMoreData is not supported.(不支持NoMoreData，请使用ClassicsFooter或者自定义)");
        }
        return this;
    }

    public SmartRefreshLayout finishRefresh() {
        return finishRefresh(Math.min(Math.max(0, 300 - ((int) (System.currentTimeMillis() - this.mLastOpenTime))), 300));
    }

    public SmartRefreshLayout finishLoadMore() {
        return finishLoadMore(Math.min(Math.max(0, 300 - ((int) (System.currentTimeMillis() - this.mLastOpenTime))), 300));
    }

    public SmartRefreshLayout finishRefresh(int i) {
        return finishRefresh(i, true);
    }

    public SmartRefreshLayout finishRefresh(boolean z) {
        long currentTimeMillis = System.currentTimeMillis() - this.mLastOpenTime;
        int i = 0;
        if (z) {
            i = Math.min(Math.max(0, 300 - ((int) currentTimeMillis)), 300);
        }
        return finishRefresh(i, z);
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
                            long j = currentTimeMillis;
                            boolean unused = SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(currentTimeMillis, j, 0, SmartRefreshLayout.this.mLastTouchX, (SmartRefreshLayout.this.mLastTouchY + ((float) SmartRefreshLayout.this.mSpinner)) - ((float) (SmartRefreshLayout.this.mTouchSlop * 2)), 0));
                            boolean unused2 = SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(currentTimeMillis, j, 2, SmartRefreshLayout.this.mLastTouchX, SmartRefreshLayout.this.mLastTouchY + ((float) SmartRefreshLayout.this.mSpinner), 0));
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

    public SmartRefreshLayout finishLoadMore(int i) {
        return finishLoadMore(i, true, false);
    }

    public SmartRefreshLayout finishLoadMore(boolean z) {
        return finishLoadMore(z ? Math.min(Math.max(0, 300 - ((int) (System.currentTimeMillis() - this.mLastOpenTime))), 300) : 0, z, false);
    }

    public SmartRefreshLayout finishLoadMore(int i, final boolean z, final boolean z2) {
        postDelayed(new Runnable() {
            public void run() {
                boolean z = true;
                if (SmartRefreshLayout.this.mState == RefreshState.Loading && SmartRefreshLayout.this.mRefreshFooter != null && SmartRefreshLayout.this.mRefreshContent != null) {
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.LoadFinish);
                    int onFinish = SmartRefreshLayout.this.mRefreshFooter.onFinish(SmartRefreshLayout.this, z);
                    if (SmartRefreshLayout.this.mOnMultiPurposeListener != null && (SmartRefreshLayout.this.mRefreshFooter instanceof RefreshFooter)) {
                        SmartRefreshLayout.this.mOnMultiPurposeListener.onFooterFinish((RefreshFooter) SmartRefreshLayout.this.mRefreshFooter, z);
                    }
                    if (onFinish < Integer.MAX_VALUE) {
                        if (!z2 || !SmartRefreshLayout.this.mEnableFooterFollowWhenNoMoreData || SmartRefreshLayout.this.mSpinner >= 0 || !SmartRefreshLayout.this.mRefreshContent.canLoadMore()) {
                            z = false;
                        }
                        final int max = SmartRefreshLayout.this.mSpinner - (z ? Math.max(SmartRefreshLayout.this.mSpinner, -SmartRefreshLayout.this.mFooterHeight) : 0);
                        if (SmartRefreshLayout.this.mIsBeingDragged || SmartRefreshLayout.this.mNestedInProgress) {
                            if (SmartRefreshLayout.this.mIsBeingDragged) {
                                SmartRefreshLayout.this.mTouchY = SmartRefreshLayout.this.mLastTouchY;
                                SmartRefreshLayout.this.mIsBeingDragged = false;
                                SmartRefreshLayout.this.mTouchSpinner = SmartRefreshLayout.this.mSpinner - max;
                            }
                            long currentTimeMillis = System.currentTimeMillis();
                            float f = (float) max;
                            long j = currentTimeMillis;
                            long j2 = currentTimeMillis;
                            boolean unused = SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(j, j2, 0, SmartRefreshLayout.this.mLastTouchX, SmartRefreshLayout.this.mLastTouchY + f + ((float) (SmartRefreshLayout.this.mTouchSlop * 2)), 0));
                            boolean unused2 = SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(j, j2, 2, SmartRefreshLayout.this.mLastTouchX, SmartRefreshLayout.this.mLastTouchY + f, 0));
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

    public SmartRefreshLayout finishLoadMoreWithNoMoreData() {
        return finishLoadMore(Math.min(Math.max(0, 300 - ((int) (System.currentTimeMillis() - this.mLastOpenTime))), 300), true, true);
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

    public boolean autoRefresh() {
        return autoRefresh(this.mHandler == null ? 400 : 0, this.mReboundDuration, ((((this.mHeaderMaxDragRate / 2.0f) + 0.5f) * ((float) this.mHeaderHeight)) * 1.0f) / ((float) (this.mHeaderHeight == 0 ? 1 : this.mHeaderHeight)), false);
    }

    @Deprecated
    public boolean autoRefresh(int i) {
        return autoRefresh(i, this.mReboundDuration, ((((this.mHeaderMaxDragRate / 2.0f) + 0.5f) * ((float) this.mHeaderHeight)) * 1.0f) / ((float) (this.mHeaderHeight == 0 ? 1 : this.mHeaderHeight)), false);
    }

    public boolean autoRefreshAnimationOnly() {
        return autoRefresh(this.mHandler == null ? 400 : 0, this.mReboundDuration, ((((this.mHeaderMaxDragRate / 2.0f) + 0.5f) * ((float) this.mHeaderHeight)) * 1.0f) / ((float) (this.mHeaderHeight == 0 ? 1 : this.mHeaderHeight)), true);
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
                    public void onAnimationStart(Animator animator) {
                        SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                        SmartRefreshLayout.this.mLastTouchX = (float) (smartRefreshLayout.getMeasuredWidth() / 2);
                        SmartRefreshLayout.this.mKernel.setState(RefreshState.PullDownToRefresh);
                    }

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
                });
                SmartRefreshLayout.this.reboundAnimator.start();
            }
        };
        if (i > 0) {
            postDelayed(r0, (long) i);
            return true;
        }
        r0.run();
        return true;
    }

    public boolean autoLoadMore() {
        return autoLoadMore(0, this.mReboundDuration, ((((float) this.mFooterHeight) * ((this.mFooterMaxDragRate / 2.0f) + 0.5f)) * 1.0f) / ((float) (this.mFooterHeight == 0 ? 1 : this.mFooterHeight)), false);
    }

    @Deprecated
    public boolean autoLoadMore(int i) {
        return autoLoadMore(i, this.mReboundDuration, ((((float) this.mFooterHeight) * ((this.mFooterMaxDragRate / 2.0f) + 0.5f)) * 1.0f) / ((float) (this.mFooterHeight == 0 ? 1 : this.mFooterHeight)), false);
    }

    public boolean autoLoadMoreAnimationOnly() {
        return autoLoadMore(0, this.mReboundDuration, ((((float) this.mFooterHeight) * ((this.mFooterMaxDragRate / 2.0f) + 0.5f)) * 1.0f) / ((float) (this.mFooterHeight == 0 ? 1 : this.mFooterHeight)), true);
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
                    public void onAnimationStart(Animator animator) {
                        SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                        SmartRefreshLayout.this.mLastTouchX = (float) (smartRefreshLayout.getMeasuredWidth() / 2);
                        SmartRefreshLayout.this.mKernel.setState(RefreshState.PullUpToLoad);
                    }

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
                });
                SmartRefreshLayout.this.reboundAnimator.start();
            }
        };
        if (i > 0) {
            postDelayed(r0, (long) i);
            return true;
        }
        r0.run();
        return true;
    }

    public static void setDefaultRefreshHeaderCreator(@NonNull DefaultRefreshHeaderCreator defaultRefreshHeaderCreator) {
        sHeaderCreator = defaultRefreshHeaderCreator;
    }

    public static void setDefaultRefreshFooterCreator(@NonNull DefaultRefreshFooterCreator defaultRefreshFooterCreator) {
        sFooterCreator = defaultRefreshFooterCreator;
    }

    public static void setDefaultRefreshInitializer(@NonNull DefaultRefreshInitializer defaultRefreshInitializer) {
        sRefreshInitializer = defaultRefreshInitializer;
    }

    public class RefreshKernelImpl implements RefreshKernel {
        public RefreshKernelImpl() {
        }

        @NonNull
        public RefreshLayout getRefreshLayout() {
            return SmartRefreshLayout.this;
        }

        @NonNull
        public RefreshContent getRefreshContent() {
            return SmartRefreshLayout.this.mRefreshContent;
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
                AnonymousClass1 r4 = new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        SmartRefreshLayout.this.mKernel.setState(RefreshState.TwoLevel);
                    }
                };
                ValueAnimator animSpinner = animSpinner(SmartRefreshLayout.this.getMeasuredHeight());
                if (animSpinner == null || animSpinner != SmartRefreshLayout.this.reboundAnimator) {
                    r4.onAnimationEnd((Animator) null);
                } else {
                    animSpinner.setDuration((long) SmartRefreshLayout.this.mFloorDuration);
                    animSpinner.addListener(r4);
                }
            } else if (animSpinner(0) == null) {
                SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
            }
            return this;
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

        public RefreshKernel moveSpinner(int i, boolean z) {
            int i2;
            int i3 = i;
            if (SmartRefreshLayout.this.mSpinner == i3 && ((SmartRefreshLayout.this.mRefreshHeader == null || !SmartRefreshLayout.this.mRefreshHeader.isSupportHorizontalDrag()) && (SmartRefreshLayout.this.mRefreshFooter == null || !SmartRefreshLayout.this.mRefreshFooter.isSupportHorizontalDrag()))) {
                return this;
            }
            SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
            int i4 = SmartRefreshLayout.this.mSpinner;
            SmartRefreshLayout.this.mSpinner = i3;
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
            int i5 = 1;
            if (SmartRefreshLayout.this.mRefreshContent != null) {
                Integer num = null;
                if (i3 >= 0 && SmartRefreshLayout.this.mRefreshHeader != null) {
                    if (SmartRefreshLayout.this.isEnableTranslationContent(SmartRefreshLayout.this.mEnableHeaderTranslationContent, SmartRefreshLayout.this.mRefreshHeader)) {
                        num = Integer.valueOf(i);
                    } else if (i4 < 0) {
                        num = 0;
                    }
                }
                if (i3 <= 0 && SmartRefreshLayout.this.mRefreshFooter != null) {
                    if (SmartRefreshLayout.this.isEnableTranslationContent(SmartRefreshLayout.this.mEnableFooterTranslationContent, SmartRefreshLayout.this.mRefreshFooter)) {
                        num = Integer.valueOf(i);
                    } else if (i4 > 0) {
                        num = 0;
                    }
                }
                if (num != null) {
                    SmartRefreshLayout.this.mRefreshContent.moveSpinner(num.intValue(), SmartRefreshLayout.this.mHeaderTranslationViewId, SmartRefreshLayout.this.mFooterTranslationViewId);
                    boolean z2 = (SmartRefreshLayout.this.mEnableClipHeaderWhenFixedBehind && SmartRefreshLayout.this.mRefreshHeader != null && SmartRefreshLayout.this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.FixedBehind) || SmartRefreshLayout.this.mHeaderBackgroundColor != 0;
                    boolean z3 = (SmartRefreshLayout.this.mEnableClipFooterWhenFixedBehind && SmartRefreshLayout.this.mRefreshFooter != null && SmartRefreshLayout.this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.FixedBehind) || SmartRefreshLayout.this.mFooterBackgroundColor != 0;
                    if ((z2 && (num.intValue() >= 0 || i4 > 0)) || (z3 && (num.intValue() <= 0 || i4 < 0))) {
                        smartRefreshLayout.invalidate();
                    }
                }
            }
            if ((i3 >= 0 || i4 > 0) && SmartRefreshLayout.this.mRefreshHeader != null) {
                int max = Math.max(i3, 0);
                int i6 = SmartRefreshLayout.this.mHeaderHeight;
                int i7 = (int) (((float) SmartRefreshLayout.this.mHeaderHeight) * SmartRefreshLayout.this.mHeaderMaxDragRate);
                float f = (((float) max) * 1.0f) / ((float) (SmartRefreshLayout.this.mHeaderHeight == 0 ? 1 : SmartRefreshLayout.this.mHeaderHeight));
                if (SmartRefreshLayout.this.isEnableRefreshOrLoadMore(SmartRefreshLayout.this.mEnableRefresh) || (SmartRefreshLayout.this.mState == RefreshState.RefreshFinish && !z)) {
                    if (i4 != SmartRefreshLayout.this.mSpinner) {
                        if (SmartRefreshLayout.this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.Translate) {
                            SmartRefreshLayout.this.mRefreshHeader.getView().setTranslationY((float) SmartRefreshLayout.this.mSpinner);
                            if (!(SmartRefreshLayout.this.mHeaderBackgroundColor == 0 || SmartRefreshLayout.this.mPaint == null || SmartRefreshLayout.this.isEnableTranslationContent(SmartRefreshLayout.this.mEnableHeaderTranslationContent, SmartRefreshLayout.this.mRefreshHeader))) {
                                smartRefreshLayout.invalidate();
                            }
                        } else if (SmartRefreshLayout.this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.Scale) {
                            SmartRefreshLayout.this.mRefreshHeader.getView().requestLayout();
                        }
                        i2 = i7;
                        SmartRefreshLayout.this.mRefreshHeader.onMoving(z, f, max, i6, i7);
                    } else {
                        i2 = i7;
                    }
                    if (z && SmartRefreshLayout.this.mRefreshHeader.isSupportHorizontalDrag()) {
                        int i8 = (int) SmartRefreshLayout.this.mLastTouchX;
                        int width = smartRefreshLayout.getWidth();
                        SmartRefreshLayout.this.mRefreshHeader.onHorizontalDrag(SmartRefreshLayout.this.mLastTouchX / ((float) (width == 0 ? 1 : width)), i8, width);
                    }
                } else {
                    i2 = i7;
                }
                if (!(i4 == SmartRefreshLayout.this.mSpinner || SmartRefreshLayout.this.mOnMultiPurposeListener == null || !(SmartRefreshLayout.this.mRefreshHeader instanceof RefreshHeader))) {
                    SmartRefreshLayout.this.mOnMultiPurposeListener.onHeaderMoving((RefreshHeader) SmartRefreshLayout.this.mRefreshHeader, z, f, max, i6, i2);
                }
            }
            if ((i3 <= 0 || i4 < 0) && SmartRefreshLayout.this.mRefreshFooter != null) {
                int i9 = -Math.min(i3, 0);
                int i10 = SmartRefreshLayout.this.mFooterHeight;
                int i11 = (int) (((float) SmartRefreshLayout.this.mFooterHeight) * SmartRefreshLayout.this.mFooterMaxDragRate);
                float f2 = (((float) i9) * 1.0f) / ((float) (SmartRefreshLayout.this.mFooterHeight == 0 ? 1 : SmartRefreshLayout.this.mFooterHeight));
                if (SmartRefreshLayout.this.isEnableRefreshOrLoadMore(SmartRefreshLayout.this.mEnableLoadMore) || (SmartRefreshLayout.this.mState == RefreshState.LoadFinish && !z)) {
                    if (i4 != SmartRefreshLayout.this.mSpinner) {
                        if (SmartRefreshLayout.this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.Translate) {
                            SmartRefreshLayout.this.mRefreshFooter.getView().setTranslationY((float) SmartRefreshLayout.this.mSpinner);
                            if (!(SmartRefreshLayout.this.mFooterBackgroundColor == 0 || SmartRefreshLayout.this.mPaint == null || SmartRefreshLayout.this.isEnableTranslationContent(SmartRefreshLayout.this.mEnableFooterTranslationContent, SmartRefreshLayout.this.mRefreshFooter))) {
                                smartRefreshLayout.invalidate();
                            }
                        } else if (SmartRefreshLayout.this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.Scale) {
                            SmartRefreshLayout.this.mRefreshFooter.getView().requestLayout();
                        }
                        SmartRefreshLayout.this.mRefreshFooter.onMoving(z, f2, i9, i10, i11);
                    }
                    if (z && SmartRefreshLayout.this.mRefreshFooter.isSupportHorizontalDrag()) {
                        int i12 = (int) SmartRefreshLayout.this.mLastTouchX;
                        int width2 = smartRefreshLayout.getWidth();
                        float f3 = SmartRefreshLayout.this.mLastTouchX;
                        if (width2 != 0) {
                            i5 = width2;
                        }
                        SmartRefreshLayout.this.mRefreshFooter.onHorizontalDrag(f3 / ((float) i5), i12, width2);
                    }
                }
                if (!(i4 == SmartRefreshLayout.this.mSpinner || SmartRefreshLayout.this.mOnMultiPurposeListener == null || !(SmartRefreshLayout.this.mRefreshFooter instanceof RefreshFooter))) {
                    SmartRefreshLayout.this.mOnMultiPurposeListener.onFooterMoving((RefreshFooter) SmartRefreshLayout.this.mRefreshFooter, z, f2, i9, i10, i11);
                }
            }
            return this;
        }

        public ValueAnimator animSpinner(int i) {
            return SmartRefreshLayout.this.animSpinner(i, 0, SmartRefreshLayout.this.mReboundInterpolator, SmartRefreshLayout.this.mReboundDuration);
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

        public RefreshKernel requestNeedTouchEventFor(@NonNull RefreshInternal refreshInternal, boolean z) {
            if (refreshInternal.equals(SmartRefreshLayout.this.mRefreshHeader)) {
                SmartRefreshLayout.this.mHeaderNeedTouchEventWhenRefreshing = z;
            } else if (refreshInternal.equals(SmartRefreshLayout.this.mRefreshFooter)) {
                SmartRefreshLayout.this.mFooterNeedTouchEventWhenLoading = z;
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

        public RefreshKernel requestFloorDuration(int i) {
            SmartRefreshLayout.this.mFloorDuration = i;
            return this;
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
}
