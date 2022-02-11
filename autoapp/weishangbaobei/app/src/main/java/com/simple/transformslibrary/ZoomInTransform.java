package com.simple.transformslibrary;

import android.view.View;

public class ZoomInTransform extends TransformAdapter {
    public void onRightScorlling(View view, float f) {
        float f2 = 1.0f - f;
        view.setScaleX(f2);
        view.setScaleY(f2);
    }
}
