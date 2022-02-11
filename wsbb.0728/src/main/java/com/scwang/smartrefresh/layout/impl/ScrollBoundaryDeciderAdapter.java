package com.scwang.smartrefresh.layout.impl;

import android.graphics.PointF;
import android.view.View;
import com.scwang.smartrefresh.layout.api.ScrollBoundaryDecider;
import com.scwang.smartrefresh.layout.util.ScrollBoundaryUtil;

public class ScrollBoundaryDeciderAdapter implements ScrollBoundaryDecider {
    public ScrollBoundaryDecider boundary;
    public PointF mActionEvent;
    public boolean mEnableLoadMoreWhenContentNotFull = true;

    public boolean canLoadMore(View view) {
        return this.boundary != null ? this.boundary.canLoadMore(view) : ScrollBoundaryUtil.canLoadMore(view, this.mActionEvent, this.mEnableLoadMoreWhenContentNotFull);
    }

    public boolean canRefresh(View view) {
        return this.boundary != null ? this.boundary.canRefresh(view) : ScrollBoundaryUtil.canRefresh(view, this.mActionEvent);
    }
}
