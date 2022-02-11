package com.wx.assistants.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class FlowGroupView extends ViewGroup {
    private String TAG = "TAG";
    private List<List<View>> mAllViews = new ArrayList();
    private List<Integer> mLineHeight = new ArrayList();

    public FlowGroupView(Context context) {
        super(context);
    }

    public FlowGroupView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FlowGroupView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth;
        new ArrayList();
        int size = this.mAllViews.size();
        int i5 = 0;
        for (int i6 = 0; i6 < size; i6++) {
            List list = this.mAllViews.get(i6);
            int intValue = this.mLineHeight.get(i6).intValue();
            Log.e("onLayout", "第" + i6 + "行 ：" + list.size() + "-------lineHeight" + intValue);
            int i7 = 0;
            for (int i8 = 0; i8 < list.size(); i8++) {
                View view = (View) list.get(i8);
                if (view.getVisibility() == 8) {
                    measuredWidth = i7;
                } else {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                    int i9 = marginLayoutParams.leftMargin + i7;
                    int i10 = marginLayoutParams.topMargin + i5;
                    view.layout(i9, i10, view.getMeasuredWidth() + i9, view.getMeasuredHeight() + i10);
                    measuredWidth = marginLayoutParams.leftMargin + view.getMeasuredWidth() + marginLayoutParams.rightMargin + i7;
                }
                i7 = measuredWidth;
            }
            i5 += intValue;
        }
        Log.v("onLayout", "onLayout   mAllViews.size() -- > " + this.mAllViews.size() + "   mLineHeight.size() -- > " + this.mLineHeight.size());
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        this.mAllViews.clear();
        this.mLineHeight.clear();
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int childCount = getChildCount();
        ArrayList arrayList = new ArrayList();
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i8 < childCount) {
            View childAt = getChildAt(i8);
            measureChild(childAt, i, i2);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
            int measuredWidth = marginLayoutParams.rightMargin + childAt.getMeasuredWidth() + marginLayoutParams.leftMargin;
            int measuredHeight = childAt.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            int i9 = i7 + measuredWidth;
            if (i9 > size) {
                i5 = Math.max(i7, measuredWidth);
                this.mAllViews.add(arrayList);
                this.mLineHeight.add(Integer.valueOf(measuredHeight));
                arrayList = new ArrayList();
                arrayList.add(childAt);
                i3 = i4 + measuredHeight;
                Log.e("需要换行", "hight--" + i3);
                Log.e("onMeasure", "AllViews.size()  --  > " + this.mAllViews.size());
                i9 = measuredWidth;
            } else {
                Log.e("不需要换行", "hight--" + i4);
                arrayList.add(childAt);
                i3 = i4;
            }
            if (i8 == childCount - 1) {
                i5 = Math.max(i9, measuredWidth);
                i3 += measuredHeight;
                Log.e("最后一个view", "hight--" + i3);
            }
            i8++;
            i6 = measuredHeight;
            i7 = i9;
            i4 = i3;
        }
        this.mLineHeight.add(Integer.valueOf(i6));
        this.mAllViews.add(arrayList);
        if (mode == 1073741824) {
            i5 = size;
        }
        setMeasuredDimension(i5, mode2 == 1073741824 ? size2 : i4);
        Log.e("onMeasure", "mAllViews.size() -- > " + this.mAllViews.size() + "   mLineHeight.size() -- > " + this.mLineHeight.size() + "Height -- > " + i4);
    }
}
