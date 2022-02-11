package com.scwang.smartrefresh.layout.impl;

import android.graphics.PointF;
import android.view.View;
import com.scwang.smartrefresh.layout.api.ScrollBoundaryDecider;
import com.scwang.smartrefresh.layout.util.ScrollBoundaryUtil;

public class ScrollBoundaryDeciderAdapter implements ScrollBoundaryDecider {
    public ScrollBoundaryDecider boundary;
    public PointF mActionEvent;
    public boolean mEnableLoadMoreWhenContentNotFull = true;

    public boolean canRefresh(View view) {
        if (this.boundary != null) {
            return this.boundary.canRefresh(view);
        }
        return ScrollBoundaryUtil.canRefresh(view, this.mActionEvent);
    }

    public boolean canLoadMore(View view) {
        if (this.boundary != null) {
            return this.boundary.canLoadMore(view);
        }
        return ScrollBoundaryUtil.canLoadMore(view, this.mActionEvent, this.mEnableLoadMoreWhenContentNotFull);
    }
}
