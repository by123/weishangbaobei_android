package com.simple.transformslibrary;

import android.view.View;

public class RotateDownTransformer extends TransformAdapter {
    private static final float ROT_MOD = -15.0f;

    public void onTransform(View view, float f) {
        view.setPivotX(((float) view.getWidth()) * 0.5f);
        view.setPivotY((float) view.getHeight());
        view.setRotation(ROT_MOD * f * -1.25f);
    }
}
