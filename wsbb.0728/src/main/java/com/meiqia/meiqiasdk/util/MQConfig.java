package com.meiqia.meiqiasdk.util;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import com.meiqia.core.MQManager;
import com.meiqia.core.callback.OnInitCallback;
import com.meiqia.meiqiasdk.callback.MQActivityLifecycleCallback;
import com.meiqia.meiqiasdk.callback.MQSimpleActivityLifecyleCallback;
import com.meiqia.meiqiasdk.callback.OnLinkClickCallback;
import com.meiqia.meiqiasdk.controller.ControllerImpl;
import com.meiqia.meiqiasdk.controller.MQController;
import com.meiqia.meiqiasdk.imageloader.MQImageLoader;
import com.stub.StubApp;

public final class MQConfig {
    public static final int DEFAULT = -1;
    public static boolean isEvaluateSwitchOpen = true;
    public static boolean isLoadMessagesFromNativeOpen = false;
    public static boolean isShowClientAvatar = false;
    public static boolean isSoundSwitchOpen = true;
    public static boolean isVoiceSwitchOpen = true;
    private static MQActivityLifecycleCallback sActivityLifecycleCallback;
    private static MQController sController;
    private static OnLinkClickCallback sOnLinkClickCallback;

    public static final class ui {
        @DrawableRes
        public static int backArrowIconResId = -1;
        @ColorRes
        public static int leftChatBubbleColorResId = -1;
        @ColorRes
        public static int leftChatTextColorResId = -1;
        @ColorRes
        public static int rightChatBubbleColorResId = -1;
        @ColorRes
        public static int rightChatTextColorResId = -1;
        @ColorRes
        public static int robotEvaluateTextColorResId = -1;
        @ColorRes
        public static int robotMenuItemTextColorResId = -1;
        @ColorRes
        public static int robotMenuTipTextColorResId = -1;
        @ColorRes
        public static int titleBackgroundResId = -1;
        public static MQTitleGravity titleGravity = MQTitleGravity.CENTER;
        @ColorRes
        public static int titleTextColorResId = -1;

        public enum MQTitleGravity {
            LEFT,
            CENTER
        }
    }

    public static MQActivityLifecycleCallback getActivityLifecycleCallback() {
        if (sActivityLifecycleCallback == null) {
            sActivityLifecycleCallback = new MQSimpleActivityLifecyleCallback();
        }
        return sActivityLifecycleCallback;
    }

    public static MQController getController(Context context) {
        if (sController == null) {
            synchronized (MQConfig.class) {
                try {
                    if (sController == null) {
                        sController = new ControllerImpl(StubApp.getOrigApplicationContext(context.getApplicationContext()));
                    }
                } catch (Throwable th) {
                    while (true) {
                        Class<MQConfig> cls = MQConfig.class;
                        throw th;
                    }
                }
            }
        }
        return sController;
    }

    public static OnLinkClickCallback getOnLinkClickCallback() {
        return sOnLinkClickCallback;
    }

    public static void init(Context context, String str, OnInitCallback onInitCallback) {
        MQManager.init(context, str, onInitCallback);
    }

    @Deprecated
    public static void init(Context context, String str, MQImageLoader mQImageLoader, OnInitCallback onInitCallback) {
        MQManager.init(context, str, onInitCallback);
    }

    public static void registerController(MQController mQController) {
        sController = mQController;
    }

    public static void setActivityLifecycleCallback(MQActivityLifecycleCallback mQActivityLifecycleCallback) {
        sActivityLifecycleCallback = mQActivityLifecycleCallback;
    }

    public static void setOnLinkClickCallback(OnLinkClickCallback onLinkClickCallback) {
        sOnLinkClickCallback = onLinkClickCallback;
    }
}
