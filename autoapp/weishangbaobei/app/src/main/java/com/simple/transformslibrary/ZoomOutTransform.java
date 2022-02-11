package com.simple.transformslibrary;

import android.view.View;

public class ZoomOutTransform extends TransformAdapter {
    public void onCenterIdle(View view) {
    }

    public void onRightScorlling(View view, float f) {
    }

    public void onLeftScorlling(View view, float f) {
        float f2 = f + 1.0f;
        view.setScaleX(f2);
        view.setScaleY(f2);
    }
}
