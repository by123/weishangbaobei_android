package com.simple.transformslibrary;

import android.view.View;

public class RotationTransform extends TransformAdapter {
    public void onLeftScorlling(View view, float f) {
        view.setPivotX((float) (view.getWidth() / 2));
        view.setPivotY((float) view.getHeight());
        view.setRotation(f * 90.0f);
    }
}
