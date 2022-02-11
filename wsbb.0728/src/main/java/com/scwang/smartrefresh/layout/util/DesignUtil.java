package com.scwang.smartrefresh.layout.util;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.view.ViewGroup;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.listener.CoordinatorLayoutListener;

public class DesignUtil {
    public static void checkCoordinatorLayout(View view, RefreshKernel refreshKernel, CoordinatorLayoutListener coordinatorLayoutListener) {
        try {
            if (view instanceof CoordinatorLayout) {
                refreshKernel.getRefreshLayout().setEnableNestedScroll(false);
                wrapperCoordinatorLayout((ViewGroup) view, coordinatorLayoutListener);
            }
        } catch (Throwable th) {
        }
    }

    private static void wrapperCoordinatorLayout(ViewGroup viewGroup, final CoordinatorLayoutListener coordinatorLayoutListener) {
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            AppBarLayout childAt = viewGroup.getChildAt(childCount);
            if (childAt instanceof AppBarLayout) {
                childAt.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                        boolean z = true;
                        CoordinatorLayoutListener coordinatorLayoutListener = coordinatorLayoutListener;
                        boolean z2 = i >= 0;
                        if (appBarLayout.getTotalScrollRange() + i > 0) {
                            z = false;
                        }
                        coordinatorLayoutListener.onCoordinatorUpdate(z2, z);
                    }
                });
            }
        }
    }
}
