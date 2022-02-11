package com.simple.transformslibrary;

import android.view.View;

public class RotateDownTransformer extends TransformAdapter {
    private static final float ROT_MOD = -15.0f;

    public void onTransform(View view, float f) {
        view.setPivotX(((float) view.getWidth()) * 0.5f);
        view.setPivotY((float) view.getHeight());
        view.setRotation(f * ROT_MOD * -1.25f);
    }
}
