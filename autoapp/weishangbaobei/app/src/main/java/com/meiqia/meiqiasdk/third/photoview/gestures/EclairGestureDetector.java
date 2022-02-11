package com.meiqia.meiqiasdk.third.photoview.gestures;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.MotionEvent;

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
        } catch (Exception unused) {
            return motionEvent.getX();
        }
    }

    /* access modifiers changed from: package-private */
    public float getActiveY(MotionEvent motionEvent) {
        try {
            return motionEvent.getY(this.mActivePointerIndex);
        } catch (Exception unused) {
            return motionEvent.getY();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0048  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            int r0 = r7.getAction()
            r0 = r0 & 255(0xff, float:3.57E-43)
            r1 = 3
            r2 = 1
            r3 = -1
            r4 = 0
            if (r0 == r1) goto L_0x0042
            r1 = 6
            if (r0 == r1) goto L_0x001a
            switch(r0) {
                case 0: goto L_0x0013;
                case 1: goto L_0x0042;
                default: goto L_0x0012;
            }
        L_0x0012:
            goto L_0x0044
        L_0x0013:
            int r0 = r7.getPointerId(r4)
            r6.mActivePointerId = r0
            goto L_0x0044
        L_0x001a:
            int r0 = r7.getAction()
            int r0 = com.meiqia.meiqiasdk.third.photoview.Compat.getPointerIndex(r0)
            int r1 = r7.getPointerId(r0)
            int r5 = r6.mActivePointerId
            if (r1 != r5) goto L_0x0044
            if (r0 != 0) goto L_0x002e
            r0 = 1
            goto L_0x002f
        L_0x002e:
            r0 = 0
        L_0x002f:
            int r1 = r7.getPointerId(r0)
            r6.mActivePointerId = r1
            float r1 = r7.getX(r0)
            r6.mLastTouchX = r1
            float r0 = r7.getY(r0)
            r6.mLastTouchY = r0
            goto L_0x0044
        L_0x0042:
            r6.mActivePointerId = r3
        L_0x0044:
            int r0 = r6.mActivePointerId
            if (r0 == r3) goto L_0x004a
            int r4 = r6.mActivePointerId
        L_0x004a:
            int r0 = r7.findPointerIndex(r4)
            r6.mActivePointerIndex = r0
            boolean r7 = super.onTouchEvent(r7)     // Catch:{ IllegalArgumentException -> 0x0055 }
            return r7
        L_0x0055:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meiqia.meiqiasdk.third.photoview.gestures.EclairGestureDetector.onTouchEvent(android.view.MotionEvent):boolean");
    }
}
