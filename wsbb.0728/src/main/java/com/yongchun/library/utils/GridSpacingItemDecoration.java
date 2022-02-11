package com.yongchun.library.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
    private boolean includeEdge;
    private int spacing;
    private int spanCount;

    public GridSpacingItemDecoration(int i, int i2, boolean z) {
        this.spanCount = i;
        this.spacing = i2;
        this.includeEdge = z;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int i = childAdapterPosition % this.spanCount;
        if (this.includeEdge) {
            rect.left = this.spacing - ((this.spacing * i) / this.spanCount);
            rect.right = ((i + 1) * this.spacing) / this.spanCount;
            if (childAdapterPosition < this.spanCount) {
                rect.top = this.spacing;
            }
            rect.bottom = this.spacing;
            return;
        }
        rect.left = (this.spacing * i) / this.spanCount;
        rect.right = this.spacing - (((i + 1) * this.spacing) / this.spanCount);
        if (childAdapterPosition < this.spanCount) {
            rect.top = this.spacing;
        }
        rect.bottom = this.spacing;
    }
}
