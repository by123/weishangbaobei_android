package com.meiqia.meiqiasdk.third.photoview.gestures;

import android.content.Context;
import android.os.Build;

public final class VersionedGestureDetector {
    public static GestureDetector newInstance(Context context, OnGestureListener onGestureListener) {
        int i = Build.VERSION.SDK_INT;
        GestureDetector cupcakeGestureDetector = i < 5 ? new CupcakeGestureDetector(context) : i < 8 ? new EclairGestureDetector(context) : new FroyoGestureDetector(context);
        cupcakeGestureDetector.setOnGestureListener(onGestureListener);
        return cupcakeGestureDetector;
    }
}
