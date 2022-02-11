package com.scwang.smartrefresh.layout.internal;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshInternal;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.impl.RefreshFooterWrapper;
import com.scwang.smartrefresh.layout.impl.RefreshHeaderWrapper;

public abstract class InternalAbstract extends RelativeLayout implements RefreshInternal {
    protected SpinnerStyle mSpinnerStyle;
    protected RefreshInternal mWrappedInternal;
    protected View mWrappedView;

    protected InternalAbstract(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    protected InternalAbstract(@NonNull View view) {
        this(view, view instanceof RefreshInternal ? (RefreshInternal) view : null);
    }

    protected InternalAbstract(@NonNull View view, @Nullable RefreshInternal refreshInternal) {
        super(view.getContext(), (AttributeSet) null, 0);
        this.mWrappedView = view;
        this.mWrappedInternal = refreshInternal;
        if ((this instanceof RefreshFooterWrapper) && (this.mWrappedInternal instanceof RefreshHeader) && this.mWrappedInternal.getSpinnerStyle() == SpinnerStyle.MatchLayout) {
            refreshInternal.getView().setScaleY(-1.0f);
        } else if ((this instanceof RefreshHeaderWrapper) && (this.mWrappedInternal instanceof RefreshFooter) && this.mWrappedInternal.getSpinnerStyle() == SpinnerStyle.MatchLayout) {
            refreshInternal.getView().setScaleY(-1.0f);
        }
    }

    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return true;
        }
        if (obj instanceof RefreshInternal) {
            return getView() == ((RefreshInternal) obj).getView();
        }
        return false;
    }

    @NonNull
    public SpinnerStyle getSpinnerStyle() {
        if (this.mSpinnerStyle != null) {
            return this.mSpinnerStyle;
        }
        if (this.mWrappedInternal != null && this.mWrappedInternal != this) {
            return this.mWrappedInternal.getSpinnerStyle();
        }
        if (this.mWrappedView != null) {
            ViewGroup.LayoutParams layoutParams = this.mWrappedView.getLayoutParams();
            if (layoutParams instanceof SmartRefreshLayout.LayoutParams) {
                this.mSpinnerStyle = ((SmartRefreshLayout.LayoutParams) layoutParams).spinnerStyle;
                if (this.mSpinnerStyle != null) {
                    return this.mSpinnerStyle;
                }
            }
            if (layoutParams != null && (layoutParams.height == 0 || layoutParams.height == -1)) {
                SpinnerStyle spinnerStyle = SpinnerStyle.Scale;
                this.mSpinnerStyle = spinnerStyle;
                return spinnerStyle;
            }
        }
        SpinnerStyle spinnerStyle2 = SpinnerStyle.Translate;
        this.mSpinnerStyle = spinnerStyle2;
        return spinnerStyle2;
    }

    @NonNull
    public View getView() {
        return this.mWrappedView == null ? this : this.mWrappedView;
    }

    public boolean isSupportHorizontalDrag() {
        return (this.mWrappedInternal == null || this.mWrappedInternal == this || !this.mWrappedInternal.isSupportHorizontalDrag()) ? false : true;
    }

    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean z) {
        if (this.mWrappedInternal == null || this.mWrappedInternal == this) {
            return 0;
        }
        return this.mWrappedInternal.onFinish(refreshLayout, z);
    }

    public void onHorizontalDrag(float f, int i, int i2) {
        if (this.mWrappedInternal != null && this.mWrappedInternal != this) {
            this.mWrappedInternal.onHorizontalDrag(f, i, i2);
        }
    }

    public void onInitialized(@NonNull RefreshKernel refreshKernel, int i, int i2) {
        if (this.mWrappedInternal != null && this.mWrappedInternal != this) {
            this.mWrappedInternal.onInitialized(refreshKernel, i, i2);
        } else if (this.mWrappedView != null) {
            ViewGroup.LayoutParams layoutParams = this.mWrappedView.getLayoutParams();
            if (layoutParams instanceof SmartRefreshLayout.LayoutParams) {
                refreshKernel.requestDrawBackgroundFor(this, ((SmartRefreshLayout.LayoutParams) layoutParams).backgroundColor);
            }
        }
    }

    public void onMoving(boolean z, float f, int i, int i2, int i3) {
        if (this.mWrappedInternal != null && this.mWrappedInternal != this) {
            this.mWrappedInternal.onMoving(z, f, i, i2, i3);
        }
    }

    public void onReleased(@NonNull RefreshLayout refreshLayout, int i, int i2) {
        if (this.mWrappedInternal != null && this.mWrappedInternal != this) {
            this.mWrappedInternal.onReleased(refreshLayout, i, i2);
        }
    }

    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int i, int i2) {
        if (this.mWrappedInternal != null && this.mWrappedInternal != this) {
            this.mWrappedInternal.onStartAnimator(refreshLayout, i, i2);
        }
    }

    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState refreshState, @NonNull RefreshState refreshState2) {
        if (this.mWrappedInternal != null && this.mWrappedInternal != this) {
            if ((this instanceof RefreshFooterWrapper) && (this.mWrappedInternal instanceof RefreshHeader)) {
                if (refreshState.isFooter) {
                    refreshState = refreshState.toHeader();
                }
                if (refreshState2.isFooter) {
                    refreshState2 = refreshState2.toHeader();
                }
            } else if ((this instanceof RefreshHeaderWrapper) && (this.mWrappedInternal instanceof RefreshFooter)) {
                if (refreshState.isHeader) {
                    refreshState = refreshState.toFooter();
                }
                if (refreshState2.isHeader) {
                    refreshState2 = refreshState2.toFooter();
                }
            }
            RefreshInternal refreshInternal = this.mWrappedInternal;
            if (refreshInternal != null) {
                refreshInternal.onStateChanged(refreshLayout, refreshState, refreshState2);
            }
        }
    }

    public void setPrimaryColors(@ColorInt int... iArr) {
        if (this.mWrappedInternal != null && this.mWrappedInternal != this) {
            this.mWrappedInternal.setPrimaryColors(iArr);
        }
    }
}
