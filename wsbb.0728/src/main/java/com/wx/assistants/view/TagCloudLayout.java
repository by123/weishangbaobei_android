package com.wx.assistants.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class TagCloudLayout extends ViewGroup {
    /* access modifiers changed from: private */
    public View lastClickNodeView;
    private BaseAdapter mAdapter;
    private int mLineSpacing;
    /* access modifiers changed from: private */
    public TagItemClickListener mListener;
    private DataChangeObserver mObserver;
    private int mTagSpacing;
    /* access modifiers changed from: private */
    public int selectIndex = -1;

    class DataChangeObserver extends DataSetObserver {
        DataChangeObserver() {
        }

        public void onChanged() {
            TagCloudLayout.this.drawLayout();
        }

        public void onInvalidated() {
            super.onInvalidated();
        }
    }

    public interface TagItemClickListener {
        void itemClick(int i);
    }

    public TagCloudLayout(Context context) {
        super(context);
        init(context, (AttributeSet) null, 0);
    }

    public TagCloudLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0);
    }

    public TagCloudLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i);
    }

    /* access modifiers changed from: private */
    public void drawLayout() {
        if (this.mAdapter != null && this.mAdapter.getCount() != 0) {
            removeAllViews();
            for (final int i = 0; i < this.mAdapter.getCount(); i++) {
                final View view = this.mAdapter.getView(i, (View) null, (ViewGroup) null);
                if (this.selectIndex == i) {
                    view.setSelected(true);
                    this.lastClickNodeView = view;
                    if (this.mListener != null) {
                        this.mListener.itemClick(this.selectIndex);
                    }
                } else {
                    view.setSelected(false);
                }
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (TagCloudLayout.this.mListener != null) {
                            int unused = TagCloudLayout.this.selectIndex = i;
                            TagCloudLayout.this.mListener.itemClick(i);
                            if (!(TagCloudLayout.this.lastClickNodeView == null || TagCloudLayout.this.lastClickNodeView == view)) {
                                TagCloudLayout.this.lastClickNodeView.setSelected(false);
                            }
                            view.setSelected(true);
                            View unused2 = TagCloudLayout.this.lastClickNodeView = view;
                        }
                    }
                });
                addView(view);
            }
        }
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        TagCloudConfiguration tagCloudConfiguration = new TagCloudConfiguration(context, attributeSet);
        this.mLineSpacing = tagCloudConfiguration.getLineSpacing();
        this.mTagSpacing = tagCloudConfiguration.getTagSpacing();
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int i5 = 0;
        int i6 = paddingLeft;
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                i5 = Math.max(measuredHeight, i5);
                if (i6 + measuredWidth + paddingRight > i3 - i) {
                    paddingTop += i5 + this.mLineSpacing;
                    i6 = paddingLeft;
                    i5 = measuredHeight;
                }
                childAt.layout(i6, paddingTop, i6 + measuredWidth, measuredHeight + paddingTop);
                i6 += this.mTagSpacing + measuredWidth;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int resolveSize = resolveSize(0, i);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i3 = paddingTop;
        int i4 = 0;
        int i5 = 0;
        int i6 = paddingLeft;
        while (i4 < getChildCount()) {
            View childAt = getChildAt(i4);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            childAt.measure(getChildMeasureSpec(i, paddingLeft + paddingRight, layoutParams.width), getChildMeasureSpec(i2, paddingTop + paddingBottom, layoutParams.height));
            int measuredHeight = childAt.getMeasuredHeight();
            int measuredWidth = childAt.getMeasuredWidth();
            int max = Math.max(measuredHeight, i5);
            if (i6 + measuredWidth + paddingRight > resolveSize) {
                i3 += this.mLineSpacing + measuredHeight;
                i6 = paddingLeft;
            } else {
                measuredHeight = max;
            }
            i6 += this.mTagSpacing + measuredWidth;
            i4++;
            i5 = measuredHeight;
        }
        setMeasuredDimension(resolveSize, resolveSize(i3 + i5 + paddingBottom + 0, i2));
    }

    public void setAdapter(BaseAdapter baseAdapter) {
        if (this.mAdapter == null) {
            this.mAdapter = baseAdapter;
            if (this.mObserver == null) {
                this.mObserver = new DataChangeObserver();
                this.mAdapter.registerDataSetObserver(this.mObserver);
            }
            drawLayout();
        }
    }

    public void setAdapter(BaseAdapter baseAdapter, int i) {
        if (this.mAdapter == null) {
            this.mAdapter = baseAdapter;
            this.selectIndex = i;
            if (this.mObserver == null) {
                this.mObserver = new DataChangeObserver();
                this.mAdapter.registerDataSetObserver(this.mObserver);
            }
            drawLayout();
        }
    }

    public void setItemClickListener(TagItemClickListener tagItemClickListener) {
        this.mListener = tagItemClickListener;
    }

    public void setNilAdapter() {
        this.mAdapter = null;
    }
}
