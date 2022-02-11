package com.scwang.smartrefresh.layout.impl;

import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.Space;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import com.scwang.smartrefresh.layout.api.RefreshContent;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.ScrollBoundaryDecider;
import com.scwang.smartrefresh.layout.listener.CoordinatorLayoutListener;
import com.scwang.smartrefresh.layout.util.DesignUtil;
import com.scwang.smartrefresh.layout.util.ScrollBoundaryUtil;
import com.scwang.smartrefresh.layout.util.SmartUtil;
import com.yalantis.ucrop.view.CropImageView;
import java.util.Collections;
import java.util.LinkedList;

public class RefreshContentWrapper implements RefreshContent, CoordinatorLayoutListener, ValueAnimator.AnimatorUpdateListener {
    protected ScrollBoundaryDeciderAdapter mBoundaryAdapter = new ScrollBoundaryDeciderAdapter();
    protected View mContentView;
    protected boolean mEnableLoadMore = true;
    protected boolean mEnableRefresh = true;
    protected View mFixedFooter;
    protected View mFixedHeader;
    protected int mLastSpinner = 0;
    protected View mOriginalContentView;
    protected View mScrollableView;

    public RefreshContentWrapper(@NonNull View view) {
        this.mScrollableView = view;
        this.mOriginalContentView = view;
        this.mContentView = view;
    }

    public boolean canLoadMore() {
        return this.mEnableLoadMore && this.mBoundaryAdapter.canLoadMore(this.mContentView);
    }

    public boolean canRefresh() {
        return this.mEnableRefresh && this.mBoundaryAdapter.canRefresh(this.mContentView);
    }

    /* access modifiers changed from: protected */
    public void findScrollableView(View view, RefreshKernel refreshKernel) {
        boolean isInEditMode = this.mContentView.isInEditMode();
        View view2 = null;
        while (true) {
            if (view2 != null && (!(view2 instanceof NestedScrollingParent) || (view2 instanceof NestedScrollingChild))) {
                break;
            }
            View findScrollableViewInternal = findScrollableViewInternal(view, view2 == null);
            if (findScrollableViewInternal == view2) {
                break;
            }
            if (!isInEditMode) {
                DesignUtil.checkCoordinatorLayout(findScrollableViewInternal, refreshKernel, this);
            }
            view = findScrollableViewInternal;
            view2 = findScrollableViewInternal;
        }
        if (view2 != null) {
            this.mScrollableView = view2;
        }
    }

    /* access modifiers changed from: protected */
    public View findScrollableViewByPoint(View view, PointF pointF, View view2) {
        if ((view instanceof ViewGroup) && pointF != null) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            PointF pointF2 = new PointF();
            int i = childCount;
            while (i > 0) {
                View childAt = viewGroup.getChildAt(i - 1);
                if (!ScrollBoundaryUtil.isTransformedTouchPointInView(viewGroup, childAt, pointF.x, pointF.y, pointF2)) {
                    i--;
                } else if (!(childAt instanceof ViewPager) && SmartUtil.isContentView(childAt)) {
                    return childAt;
                } else {
                    pointF.offset(pointF2.x, pointF2.y);
                    View findScrollableViewByPoint = findScrollableViewByPoint(childAt, pointF, view2);
                    pointF.offset(-pointF2.x, -pointF2.y);
                    return findScrollableViewByPoint;
                }
            }
        }
        return view2;
    }

    /* access modifiers changed from: protected */
    public View findScrollableViewInternal(View view, boolean z) {
        LinkedList linkedList = new LinkedList(Collections.singletonList(view));
        View view2 = null;
        while (!linkedList.isEmpty() && view2 == null) {
            View view3 = (View) linkedList.poll();
            if (view3 != null) {
                if ((z || view3 != view) && SmartUtil.isContentView(view3)) {
                    view2 = view3;
                } else if (view3 instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view3;
                    for (int i = 0; i < viewGroup.getChildCount(); i++) {
                        linkedList.add(viewGroup.getChildAt(i));
                    }
                }
            }
        }
        return view2 == null ? view : view2;
    }

    @NonNull
    public View getScrollableView() {
        return this.mScrollableView;
    }

    @NonNull
    public View getView() {
        return this.mContentView;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0021  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    public void moveSpinner(int i, int i2, int i3) {
        boolean z;
        View findViewById;
        View findViewById2;
        boolean z2 = true;
        if (!(i2 == -1 || (findViewById2 = this.mOriginalContentView.findViewById(i2)) == null)) {
            if (i > 0) {
                findViewById2.setTranslationY((float) i);
                z = true;
                if (!(i3 == -1 || (findViewById = this.mOriginalContentView.findViewById(i3)) == null)) {
                    if (i >= 0) {
                        findViewById.setTranslationY((float) i);
                    } else if (findViewById.getTranslationY() < CropImageView.DEFAULT_ASPECT_RATIO) {
                        findViewById.setTranslationY(CropImageView.DEFAULT_ASPECT_RATIO);
                        z2 = z;
                    }
                    if (!z2) {
                        this.mOriginalContentView.setTranslationY((float) i);
                    } else {
                        this.mOriginalContentView.setTranslationY(CropImageView.DEFAULT_ASPECT_RATIO);
                    }
                    if (this.mFixedHeader != null) {
                        this.mFixedHeader.setTranslationY((float) Math.max(0, i));
                    }
                    if (this.mFixedFooter != null) {
                        this.mFixedFooter.setTranslationY((float) Math.min(0, i));
                        return;
                    }
                    return;
                }
                z2 = z;
                if (!z2) {
                }
                if (this.mFixedHeader != null) {
                }
                if (this.mFixedFooter != null) {
                }
            } else if (findViewById2.getTranslationY() > CropImageView.DEFAULT_ASPECT_RATIO) {
                findViewById2.setTranslationY(CropImageView.DEFAULT_ASPECT_RATIO);
            }
        }
        z = false;
        if (i >= 0) {
        }
        if (!z2) {
        }
        if (this.mFixedHeader != null) {
        }
        if (this.mFixedFooter != null) {
        }
    }

    public void onActionDown(MotionEvent motionEvent) {
        PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
        pointF.offset((float) (-this.mContentView.getLeft()), (float) (-this.mContentView.getTop()));
        if (this.mScrollableView != this.mContentView) {
            this.mScrollableView = findScrollableViewByPoint(this.mContentView, pointF, this.mScrollableView);
        }
        if (this.mScrollableView == this.mContentView) {
            this.mBoundaryAdapter.mActionEvent = null;
        } else {
            this.mBoundaryAdapter.mActionEvent = pointF;
        }
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        try {
            if (this.mScrollableView instanceof AbsListView) {
                SmartUtil.scrollListBy((AbsListView) this.mScrollableView, intValue - this.mLastSpinner);
            } else {
                this.mScrollableView.scrollBy(0, intValue - this.mLastSpinner);
            }
        } catch (Throwable th) {
        }
        this.mLastSpinner = intValue;
    }

    public void onCoordinatorUpdate(boolean z, boolean z2) {
        this.mEnableRefresh = z;
        this.mEnableLoadMore = z2;
    }

    public ValueAnimator.AnimatorUpdateListener scrollContentWhenFinished(int i) {
        if (this.mScrollableView == null || i == 0 || ((i >= 0 || !ScrollBoundaryUtil.canScrollDown(this.mScrollableView)) && (i <= 0 || !ScrollBoundaryUtil.canScrollUp(this.mScrollableView)))) {
            return null;
        }
        this.mLastSpinner = i;
        return this;
    }

    public void setEnableLoadMoreWhenContentNotFull(boolean z) {
        this.mBoundaryAdapter.mEnableLoadMoreWhenContentNotFull = z;
    }

    public void setScrollBoundaryDecider(ScrollBoundaryDecider scrollBoundaryDecider) {
        if (scrollBoundaryDecider instanceof ScrollBoundaryDeciderAdapter) {
            this.mBoundaryAdapter = (ScrollBoundaryDeciderAdapter) scrollBoundaryDecider;
        } else {
            this.mBoundaryAdapter.boundary = scrollBoundaryDecider;
        }
    }

    public void setUpComponent(RefreshKernel refreshKernel, View view, View view2) {
        findScrollableView(this.mContentView, refreshKernel);
        if (view != null || view2 != null) {
            this.mFixedHeader = view;
            this.mFixedFooter = view2;
            FrameLayout frameLayout = new FrameLayout(this.mContentView.getContext());
            refreshKernel.getRefreshLayout().getLayout().removeView(this.mContentView);
            ViewGroup.LayoutParams layoutParams = this.mContentView.getLayoutParams();
            frameLayout.addView(this.mContentView, -1, -1);
            refreshKernel.getRefreshLayout().getLayout().addView(frameLayout, layoutParams);
            this.mContentView = frameLayout;
            if (view != null) {
                view.setClickable(true);
                ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                int indexOfChild = viewGroup.indexOfChild(view);
                viewGroup.removeView(view);
                layoutParams2.height = SmartUtil.measureViewHeight(view);
                viewGroup.addView(new Space(this.mContentView.getContext()), indexOfChild, layoutParams2);
                frameLayout.addView(view);
            }
            if (view2 != null) {
                view2.setClickable(true);
                ViewGroup.LayoutParams layoutParams3 = view2.getLayoutParams();
                ViewGroup viewGroup2 = (ViewGroup) view2.getParent();
                int indexOfChild2 = viewGroup2.indexOfChild(view2);
                viewGroup2.removeView(view2);
                FrameLayout.LayoutParams layoutParams4 = new FrameLayout.LayoutParams(layoutParams3);
                layoutParams3.height = SmartUtil.measureViewHeight(view2);
                viewGroup2.addView(new Space(this.mContentView.getContext()), indexOfChild2, layoutParams3);
                layoutParams4.gravity = 80;
                frameLayout.addView(view2, layoutParams4);
            }
        }
    }
}
