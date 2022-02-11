package com.meiqia.meiqiasdk.third.photoview;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

public class Compat {
    private static final int SIXTY_FPS_INTERVAL = 16;

    public static int getPointerIndex(int i) {
        return Build.VERSION.SDK_INT >= 11 ? getPointerIndexHoneyComb(i) : getPointerIndexEclair(i);
    }

    @TargetApi(5)
    private static int getPointerIndexEclair(int i) {
        return (65280 & i) >> 8;
    }

    @TargetApi(11)
    private static int getPointerIndexHoneyComb(int i) {
        return (65280 & i) >> 8;
    }

    public static void postOnAnimation(View view, Runnable runnable) {
        if (Build.VERSION.SDK_INT >= 16) {
            postOnAnimationJellyBean(view, runnable);
        } else {
            view.postDelayed(runnable, 16);
        }
    }

    @TargetApi(16)
    private static void postOnAnimationJellyBean(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }
}
