package com.youth.banner.transformer;

import android.view.View;

public class RotateDownTransformer extends ABaseTransformer {
    private static final float ROT_MOD = -15.0f;

    /* access modifiers changed from: protected */
    public boolean isPagingEnabled() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onTransform(View view, float f) {
        view.setPivotX(((float) view.getWidth()) * 0.5f);
        view.setPivotY((float) view.getHeight());
        view.setRotation(f * ROT_MOD * -1.25f);
    }
}
