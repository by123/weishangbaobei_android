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

    public FlowGroupView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public FlowGroupView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FlowGroupView(Context context) {
        super(context);
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
        int i4 = 0;
        ArrayList arrayList = new ArrayList();
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i4 < childCount) {
            View childAt = getChildAt(i4);
            measureChild(childAt, i, i2);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
            int i9 = size2;
            int measuredWidth = childAt.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            int i10 = i8;
            int measuredHeight = childAt.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            int i11 = i5 + measuredWidth;
            if (i11 > size) {
                int max = Math.max(i5, measuredWidth);
                this.mAllViews.add(arrayList);
                this.mLineHeight.add(Integer.valueOf(measuredHeight));
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(childAt);
                i7 += measuredHeight;
                Log.e("需要换行", "hight--" + i7);
                Log.e("onMeasure", "AllViews.size()  --  > " + this.mAllViews.size());
                i3 = size;
                arrayList = arrayList2;
                i10 = max;
                i5 = measuredWidth;
            } else {
                StringBuilder sb = new StringBuilder();
                i3 = size;
                sb.append("hight--");
                sb.append(i7);
                Log.e("不需要换行", sb.toString());
                arrayList.add(childAt);
                i5 = i11;
            }
            if (i4 == childCount - 1) {
                int max2 = Math.max(i5, measuredWidth);
                i7 += measuredHeight;
                Log.e("最后一个view", "hight--" + i7);
                i8 = max2;
            } else {
                i8 = i10;
            }
            i4++;
            i6 = measuredHeight;
            size2 = i9;
            size = i3;
        }
        int i12 = size;
        int i13 = size2;
        int i14 = i8;
        this.mLineHeight.add(Integer.valueOf(i6));
        this.mAllViews.add(arrayList);
        setMeasuredDimension(mode == 1073741824 ? i12 : i14, mode2 == 1073741824 ? i13 : i7);
        Log.e("onMeasure", "mAllViews.size() -- > " + this.mAllViews.size() + "   mLineHeight.size() -- > " + this.mLineHeight.size() + "Height -- > " + i7);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
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
                if (view.getVisibility() != 8) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                    int i9 = marginLayoutParams.leftMargin + i7;
                    int i10 = marginLayoutParams.topMargin + i5;
                    view.layout(i9, i10, view.getMeasuredWidth() + i9, view.getMeasuredHeight() + i10);
                    i7 += view.getMeasuredWidth() + marginLayoutParams.rightMargin + marginLayoutParams.leftMargin;
                }
            }
            i5 += intValue;
        }
        Log.v("onLayout", "onLayout   mAllViews.size() -- > " + this.mAllViews.size() + "   mLineHeight.size() -- > " + this.mLineHeight.size());
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }
}
