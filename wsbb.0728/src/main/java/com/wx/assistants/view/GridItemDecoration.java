package com.wx.assistants.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.View;

public class GridItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDivider;
    private int mHorizonSpan;
    private boolean mShowLastLine;
    private int mVerticalSpan;

    public static class Builder {
        private int mColor = -1;
        private Context mContext;
        private int mHorizonSpan = 0;
        private Resources mResources;
        private boolean mShowLastLine = true;
        private int mVerticalSpan = 0;

        public Builder(Context context) {
            this.mContext = context;
            this.mResources = context.getResources();
        }

        public GridItemDecoration build() {
            return new GridItemDecoration(this.mHorizonSpan, this.mVerticalSpan, this.mColor, this.mShowLastLine);
        }

        public Builder setColor(@ColorInt int i) {
            this.mColor = i;
            return this;
        }

        public Builder setColorResource(@ColorRes int i) {
            setColor(ContextCompat.getColor(this.mContext, i));
            return this;
        }

        public Builder setHorizontalSpan(float f) {
            this.mHorizonSpan = (int) TypedValue.applyDimension(0, f, this.mResources.getDisplayMetrics());
            return this;
        }

        public Builder setHorizontalSpan(@DimenRes int i) {
            this.mHorizonSpan = this.mResources.getDimensionPixelSize(i);
            return this;
        }

        public Builder setShowLastLine(boolean z) {
            this.mShowLastLine = z;
            return this;
        }

        public Builder setVerticalSpan(float f) {
            this.mVerticalSpan = (int) TypedValue.applyDimension(0, f, this.mResources.getDisplayMetrics());
            return this;
        }

        public Builder setVerticalSpan(@DimenRes int i) {
            this.mVerticalSpan = this.mResources.getDimensionPixelSize(i);
            return this;
        }
    }

    private GridItemDecoration(int i, int i2, int i3, boolean z) {
        this.mHorizonSpan = i;
        this.mShowLastLine = z;
        this.mVerticalSpan = i2;
        this.mDivider = new ColorDrawable(i3);
    }

    private void drawHorizontal(Canvas canvas, RecyclerView recyclerView) {
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            if (!isLastRaw(recyclerView, i, getSpanCount(recyclerView), childCount) || this.mShowLastLine) {
                RecyclerView.LayoutParams layoutParams = childAt.getLayoutParams();
                int left = childAt.getLeft();
                int i2 = layoutParams.leftMargin;
                int right = childAt.getRight();
                int i3 = layoutParams.rightMargin;
                int bottom = layoutParams.bottomMargin + childAt.getBottom();
                this.mDivider.setBounds(left - i2, bottom, right + i3, this.mHorizonSpan + bottom);
                this.mDivider.draw(canvas);
            }
        }
    }

    private void drawVertical(Canvas canvas, RecyclerView recyclerView) {
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            if ((recyclerView.getChildViewHolder(childAt).getAdapterPosition() + 1) % getSpanCount(recyclerView) != 0) {
                RecyclerView.LayoutParams layoutParams = childAt.getLayoutParams();
                int top2 = childAt.getTop();
                int i2 = layoutParams.topMargin;
                int bottom = childAt.getBottom();
                int i3 = layoutParams.bottomMargin;
                int i4 = this.mHorizonSpan;
                int right = childAt.getRight() + layoutParams.rightMargin;
                int i5 = this.mVerticalSpan + right;
                if (i == childCount - 1) {
                    i5 -= this.mVerticalSpan;
                }
                this.mDivider.setBounds(right, top2 - i2, i5, bottom + i3 + i4);
                this.mDivider.draw(canvas);
            }
        }
    }

    private boolean getResult(int i, int i2, int i3) {
        int i4 = i3 % i2;
        if (i4 != 0) {
            return i >= i3 - i4;
        }
        if (i >= i3 - i2) {
            return true;
        }
    }

    private int getSpanCount(RecyclerView recyclerView) {
        GridLayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            return layoutManager.getSpanCount();
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return -1;
    }

    private boolean isLastRaw(RecyclerView recyclerView, int i, int i2, int i3) {
        StaggeredGridLayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            return getResult(i, i2, i3);
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            if (layoutManager.getOrientation() == 1) {
                return getResult(i, i2, i3);
            }
            if ((i + 1) % i2 == 0) {
                return true;
            }
        }
        return false;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int spanCount = getSpanCount(recyclerView);
        int itemCount = recyclerView.getAdapter().getItemCount();
        int viewLayoutPosition = view.getLayoutParams().getViewLayoutPosition();
        if (viewLayoutPosition >= 0) {
            int i = viewLayoutPosition % spanCount;
            rect.set((this.mVerticalSpan * i) / spanCount, 0, this.mVerticalSpan - (((i + 1) * this.mVerticalSpan) / spanCount), isLastRaw(recyclerView, viewLayoutPosition, spanCount, itemCount) ? this.mShowLastLine ? this.mHorizonSpan : 0 : this.mHorizonSpan);
        }
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        drawHorizontal(canvas, recyclerView);
        drawVertical(canvas, recyclerView);
    }
}
