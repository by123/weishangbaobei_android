package com.meiqia.meiqiasdk.third.photoview.gestures;

import android.view.MotionEvent;

public interface GestureDetector {
    boolean isDragging();

    boolean isScaling();

    boolean onTouchEvent(MotionEvent motionEvent);

    void setOnGestureListener(OnGestureListener onGestureListener);
}
