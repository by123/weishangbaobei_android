package com.luck.picture.lib;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class FullyGridLayoutManager extends GridLayoutManager {
    private int[] mMeasuredDimension = new int[2];
    final RecyclerView.State mState = new RecyclerView.State();

    public FullyGridLayoutManager(Context context, int i) {
        super(context, i);
    }

    public FullyGridLayoutManager(Context context, int i, int i2, boolean z) {
        super(context, i, i2, z);
    }

    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int itemCount = getItemCount();
        int spanCount = getSpanCount();
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < itemCount; i5++) {
            measureScrapChild(recycler, i5, View.MeasureSpec.makeMeasureSpec(i5, 0), View.MeasureSpec.makeMeasureSpec(i5, 0), this.mMeasuredDimension);
            if (getOrientation() == 0) {
                if (i5 % spanCount == 0) {
                    i3 += this.mMeasuredDimension[0];
                }
                if (i5 == 0) {
                    i4 = this.mMeasuredDimension[1];
                }
            } else {
                if (i5 % spanCount == 0) {
                    i4 += this.mMeasuredDimension[1];
                }
                if (i5 == 0) {
                    i3 = this.mMeasuredDimension[0];
                }
            }
        }
        if (mode == 1073741824) {
            i3 = size;
        }
        if (mode2 != 1073741824) {
            size2 = i4;
        }
        setMeasuredDimension(i3, size2);
    }

    private void measureScrapChild(RecyclerView.Recycler recycler, int i, int i2, int i3, int[] iArr) {
        if (i < this.mState.getItemCount()) {
            try {
                View viewForPosition = recycler.getViewForPosition(0);
                if (viewForPosition != null) {
                    RecyclerView.LayoutParams layoutParams = viewForPosition.getLayoutParams();
                    viewForPosition.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight(), layoutParams.width), ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom(), layoutParams.height));
                    iArr[0] = viewForPosition.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
                    iArr[1] = viewForPosition.getMeasuredHeight() + layoutParams.bottomMargin + layoutParams.topMargin;
                    recycler.recycleView(viewForPosition);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
