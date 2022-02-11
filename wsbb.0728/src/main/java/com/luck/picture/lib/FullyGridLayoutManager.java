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

    private void measureScrapChild(RecyclerView.Recycler recycler, int i, int i2, int i3, int[] iArr) {
        if (i < this.mState.getItemCount()) {
            try {
                View viewForPosition = recycler.getViewForPosition(0);
                if (viewForPosition != null) {
                    RecyclerView.LayoutParams layoutParams = viewForPosition.getLayoutParams();
                    viewForPosition.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight(), layoutParams.width), ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom(), layoutParams.height));
                    iArr[0] = viewForPosition.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
                    iArr[1] = layoutParams.topMargin + viewForPosition.getMeasuredHeight() + layoutParams.bottomMargin;
                    recycler.recycleView(viewForPosition);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2) {
        int i3;
        int i4;
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int itemCount = getItemCount();
        int spanCount = getSpanCount();
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i5 < itemCount) {
            measureScrapChild(recycler, i5, View.MeasureSpec.makeMeasureSpec(i5, 0), View.MeasureSpec.makeMeasureSpec(i5, 0), this.mMeasuredDimension);
            if (getOrientation() == 0) {
                i4 = i5 % spanCount == 0 ? this.mMeasuredDimension[0] + i6 : i6;
                i3 = i5 == 0 ? this.mMeasuredDimension[1] : i7;
            } else {
                i3 = i5 % spanCount == 0 ? i7 + this.mMeasuredDimension[1] : i7;
                i4 = i5 == 0 ? this.mMeasuredDimension[0] : i6;
            }
            i5++;
            i6 = i4;
            i7 = i3;
        }
        if (mode == 1073741824) {
            i6 = size;
        }
        if (mode2 == 1073741824) {
            i7 = size2;
        }
        setMeasuredDimension(i6, i7);
    }
}
