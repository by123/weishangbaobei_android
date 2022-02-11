package com.scwang.smartrefresh.layout.util;

import android.graphics.PointF;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import com.yalantis.ucrop.view.CropImageView;

public class ScrollBoundaryUtil {
    public static boolean canLoadMore(@NonNull View view, PointF pointF, boolean z) {
        if (canScrollDown(view) && view.getVisibility() == 0) {
            return false;
        }
        if ((view instanceof ViewGroup) && pointF != null && !SmartUtil.isScrollableView(view)) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            PointF pointF2 = new PointF();
            int i = 0;
            while (i < childCount) {
                View childAt = viewGroup.getChildAt(i);
                if (!isTransformedTouchPointInView(viewGroup, childAt, pointF.x, pointF.y, pointF2)) {
                    i++;
                } else if ("fixed".equals(childAt.getTag())) {
                    return false;
                } else {
                    pointF.offset(pointF2.x, pointF2.y);
                    boolean canLoadMore = canLoadMore(childAt, pointF, z);
                    pointF.offset(-pointF2.x, -pointF2.y);
                    return canLoadMore;
                }
            }
        }
        return z || canScrollUp(view);
    }

    public static boolean canRefresh(@NonNull View view, PointF pointF) {
        if (canScrollUp(view) && view.getVisibility() == 0) {
            return false;
        }
        if ((view instanceof ViewGroup) && pointF != null) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            PointF pointF2 = new PointF();
            while (childCount > 0) {
                View childAt = viewGroup.getChildAt(childCount - 1);
                if (!isTransformedTouchPointInView(viewGroup, childAt, pointF.x, pointF.y, pointF2)) {
                    childCount--;
                } else if ("fixed".equals(childAt.getTag())) {
                    return false;
                } else {
                    pointF.offset(pointF2.x, pointF2.y);
                    boolean canRefresh = canRefresh(childAt, pointF);
                    pointF.offset(-pointF2.x, -pointF2.y);
                    return canRefresh;
                }
            }
        }
        return true;
    }

    public static boolean canScrollDown(@NonNull View view) {
        boolean z;
        if (Build.VERSION.SDK_INT >= 14) {
            return view.canScrollVertically(1);
        }
        if (!(view instanceof AbsListView)) {
            return view.getScrollY() < 0;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        AbsListView absListView = (AbsListView) view;
        int childCount = viewGroup.getChildCount();
        if (childCount > 0) {
            int i = childCount - 1;
            if (absListView.getLastVisiblePosition() < i) {
                z = true;
                return z;
            } else if (viewGroup.getChildAt(i).getBottom() > view.getPaddingBottom()) {
                return true;
            }
        }
        z = false;
        return z;
    }

    public static boolean canScrollUp(@NonNull View view) {
        boolean z;
        if (Build.VERSION.SDK_INT >= 14) {
            return view.canScrollVertically(-1);
        }
        if (!(view instanceof AbsListView)) {
            return view.getScrollY() > 0;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        AbsListView absListView = (AbsListView) view;
        if (viewGroup.getChildCount() > 0) {
            if (absListView.getFirstVisiblePosition() > 0) {
                z = true;
                return z;
            } else if (viewGroup.getChildAt(0).getTop() < view.getPaddingTop()) {
                return true;
            }
        }
        z = false;
        return z;
    }

    public static boolean isTransformedTouchPointInView(@NonNull View view, @NonNull View view2, float f, float f2, PointF pointF) {
        if (view2.getVisibility() != 0) {
            return false;
        }
        float[] fArr = {f, f2};
        fArr[0] = fArr[0] + ((float) (view.getScrollX() - view2.getLeft()));
        fArr[1] = fArr[1] + ((float) (view.getScrollY() - view2.getTop()));
        boolean z = fArr[0] >= CropImageView.DEFAULT_ASPECT_RATIO && fArr[1] >= CropImageView.DEFAULT_ASPECT_RATIO && fArr[0] < ((float) view2.getWidth()) && fArr[1] < ((float) view2.getHeight());
        if (z && pointF != null) {
            pointF.set(fArr[0] - f, fArr[1] - f2);
        }
        return z;
    }
}
