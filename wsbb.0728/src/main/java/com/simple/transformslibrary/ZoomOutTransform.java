package com.simple.transformslibrary;

import android.view.View;

public class ZoomOutTransform extends TransformAdapter {
    public void onCenterIdle(View view) {
    }

    public void onLeftScorlling(View view, float f) {
        float f2 = 1.0f + f;
        view.setScaleX(f2);
        view.setScaleY(f2);
    }

    public void onRightScorlling(View view, float f) {
    }
}
