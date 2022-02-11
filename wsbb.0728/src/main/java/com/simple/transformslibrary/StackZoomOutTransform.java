package com.simple.transformslibrary;

import android.view.View;

public class StackZoomOutTransform extends TransformAdapter {
    public void onLeftScorlling(View view, float f) {
        view.setTranslationX(((float) view.getWidth()) * (-f));
        float f2 = 1.0f + f;
        view.setScaleX(f2);
        view.setScaleY(f2);
    }
}
