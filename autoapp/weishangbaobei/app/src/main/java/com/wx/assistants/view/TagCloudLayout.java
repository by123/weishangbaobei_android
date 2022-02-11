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

    private void init(Context context, AttributeSet attributeSet, int i) {
        TagCloudConfiguration tagCloudConfiguration = new TagCloudConfiguration(context, attributeSet);
        this.mLineSpacing = tagCloudConfiguration.getLineSpacing();
        this.mTagSpacing = tagCloudConfiguration.getTagSpacing();
    }

    public void setNilAdapter() {
        this.mAdapter = null;
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

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3 = i;
        int i4 = i2;
        int resolveSize = resolveSize(0, i3);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i5 = paddingLeft;
        int i6 = paddingTop;
        int i7 = 0;
        for (int i8 = 0; i8 < getChildCount(); i8++) {
            View childAt = getChildAt(i8);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            childAt.measure(getChildMeasureSpec(i3, paddingLeft + paddingRight, layoutParams.width), getChildMeasureSpec(i4, paddingTop + paddingBottom, layoutParams.height));
            int measuredHeight = childAt.getMeasuredHeight();
            int measuredWidth = childAt.getMeasuredWidth();
            i7 = Math.max(measuredHeight, i7);
            if (i5 + measuredWidth + paddingRight > resolveSize) {
                i6 += this.mLineSpacing + measuredHeight;
                i7 = measuredHeight;
                i5 = paddingLeft;
            }
            i5 += measuredWidth + this.mTagSpacing;
        }
        setMeasuredDimension(resolveSize, resolveSize(0 + i6 + i7 + paddingBottom, i4));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int i6 = paddingLeft;
        int i7 = paddingTop;
        int i8 = 0;
        for (int i9 = 0; i9 < getChildCount(); i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                i8 = Math.max(measuredHeight, i8);
                if (i6 + measuredWidth + paddingRight > i5) {
                    i7 += this.mLineSpacing + i8;
                    i6 = paddingLeft;
                    i8 = measuredHeight;
                }
                childAt.layout(i6, i7, i6 + measuredWidth, measuredHeight + i7);
                i6 += measuredWidth + this.mTagSpacing;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.LayoutParams(getContext(), attributeSet);
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
}
