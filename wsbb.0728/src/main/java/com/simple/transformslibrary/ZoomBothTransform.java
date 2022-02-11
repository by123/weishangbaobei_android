package com.simple.transformslibrary;

import android.view.View;

public class ZoomBothTransform extends TransformAdapter {
    public void onLeftScorlling(View view, float f) {
        float f2 = (f / 2.0f) + 1.0f;
        view.setScaleX(f2);
        view.setScaleY(f2);
    }

    public void onRightScorlling(View view, float f) {
        float f2 = 1.0f - (f / 2.0f);
        view.setScaleX(f2);
        view.setScaleY(f2);
    }
}
