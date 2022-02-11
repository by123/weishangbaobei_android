package com.scwang.smartrefresh.layout.listener;

import android.support.annotation.NonNull;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;

public class SimpleMultiPurposeListener implements OnMultiPurposeListener {
    public void onFooterFinish(RefreshFooter refreshFooter, boolean z) {
    }

    public void onFooterMoving(RefreshFooter refreshFooter, boolean z, float f, int i, int i2, int i3) {
    }

    public void onFooterReleased(RefreshFooter refreshFooter, int i, int i2) {
    }

    public void onFooterStartAnimator(RefreshFooter refreshFooter, int i, int i2) {
    }

    public void onHeaderFinish(RefreshHeader refreshHeader, boolean z) {
    }

    public void onHeaderMoving(RefreshHeader refreshHeader, boolean z, float f, int i, int i2, int i3) {
    }

    public void onHeaderReleased(RefreshHeader refreshHeader, int i, int i2) {
    }

    public void onHeaderStartAnimator(RefreshHeader refreshHeader, int i, int i2) {
    }

    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
    }

    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
    }

    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState refreshState, @NonNull RefreshState refreshState2) {
    }
}
