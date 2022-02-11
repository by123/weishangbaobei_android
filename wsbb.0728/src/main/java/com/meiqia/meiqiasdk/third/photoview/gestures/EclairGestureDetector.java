package com.meiqia.meiqiasdk.third.photoview.gestures;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.MotionEvent;
import com.meiqia.meiqiasdk.third.photoview.Compat;

@TargetApi(5)
public class EclairGestureDetector extends CupcakeGestureDetector {
    private static final int INVALID_POINTER_ID = -1;
    private int mActivePointerId = -1;
    private int mActivePointerIndex = 0;

    public EclairGestureDetector(Context context) {
        super(context);
    }

    /* access modifiers changed from: package-private */
    public float getActiveX(MotionEvent motionEvent) {
        try {
            return motionEvent.getX(this.mActivePointerIndex);
        } catch (Exception e) {
            return motionEvent.getX();
        }
    }

    /* access modifiers changed from: package-private */
    public float getActiveY(MotionEvent motionEvent) {
        try {
            return motionEvent.getY(this.mActivePointerIndex);
        } catch (Exception e) {
            return motionEvent.getY();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x0016  */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i = 0;
        int action = motionEvent.getAction() & 255;
        if (action != 3) {
            if (action != 6) {
                switch (action) {
                    case 0:
                        this.mActivePointerId = motionEvent.getPointerId(0);
                        break;
                    case 1:
                        break;
                }
            } else {
                int pointerIndex = Compat.getPointerIndex(motionEvent.getAction());
                if (motionEvent.getPointerId(pointerIndex) == this.mActivePointerId) {
                    int i2 = pointerIndex == 0 ? 1 : 0;
                    this.mActivePointerId = motionEvent.getPointerId(i2);
                    this.mLastTouchX = motionEvent.getX(i2);
                    this.mLastTouchY = motionEvent.getY(i2);
                }
            }
            if (this.mActivePointerId != -1) {
                i = this.mActivePointerId;
            }
            this.mActivePointerIndex = motionEvent.findPointerIndex(i);
            return super.onTouchEvent(motionEvent);
        }
        this.mActivePointerId = -1;
        if (this.mActivePointerId != -1) {
        }
        this.mActivePointerIndex = motionEvent.findPointerIndex(i);
        try {
            return super.onTouchEvent(motionEvent);
        } catch (IllegalArgumentException e) {
            return true;
        }
    }
}
