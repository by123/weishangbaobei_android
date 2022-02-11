package com.wx.assistants.floatwindow;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class FloatWindow {
    private static B mBuilder = null;
    private static final String mDefaultTag = "default_float_window_tag";
    /* access modifiers changed from: private */
    public static Map<String, IFloatWindow> mFloatWindowMap;

    public static class B {
        int gravity = 8388659;
        boolean isNoTouch = true;
        boolean isScreenAlwaysLight = true;
        Class[] mActivities;
        Context mApplicationContext;
        boolean mDesktopShow;
        long mDuration = 300;
        int mHeight = -2;
        TimeInterpolator mInterpolator;
        private int mLayoutId;
        int mMoveType = 3;
        PermissionListener mPermissionListener;
        boolean mShow = true;
        int mSlideLeftMargin;
        int mSlideRightMargin;
        private String mTag = FloatWindow.mDefaultTag;
        View mView;
        ViewStateListener mViewStateListener;
        int mWidth = -2;
        int xOffset;
        int yOffset;

        private B() {
        }

        B(Context context) {
            this.mApplicationContext = context;
        }

        public void build() {
            if (FloatWindow.mFloatWindowMap == null) {
                Map unused = FloatWindow.mFloatWindowMap = new HashMap();
            }
            if (FloatWindow.mFloatWindowMap.containsKey(this.mTag)) {
                throw new IllegalArgumentException("FloatWindow of this tag has been added, Please set a new tag for the new FloatWindow");
            } else if (this.mView == null && this.mLayoutId == 0) {
                throw new IllegalArgumentException("View has not been set!");
            } else {
                if (this.mView == null) {
                    this.mView = Util.inflate(this.mApplicationContext, this.mLayoutId);
                }
                FloatWindow.mFloatWindowMap.put(this.mTag, new IFloatWindowImpl(this));
            }
        }

        public B setDesktopShow(boolean z) {
            this.mDesktopShow = z;
            return this;
        }

        public B setFilter(boolean z, @NonNull Class... clsArr) {
            this.mShow = z;
            this.mActivities = clsArr;
            return this;
        }

        public B setGravity(int i) {
            this.gravity = i;
            return this;
        }

        public B setHeight(int i) {
            this.mHeight = i;
            return this;
        }

        public B setHeight(int i, float f) {
            this.mHeight = (int) (((float) (i == 0 ? Util.getScreenWidth(this.mApplicationContext) : Util.getScreenHeight(this.mApplicationContext))) * f);
            return this;
        }

        public B setMoveStyle(long j, @Nullable TimeInterpolator timeInterpolator) {
            this.mDuration = j;
            this.mInterpolator = timeInterpolator;
            return this;
        }

        public B setMoveType(int i) {
            return setMoveType(i, 0, 0);
        }

        public B setMoveType(int i, int i2, int i3) {
            this.mMoveType = i;
            this.mSlideLeftMargin = i2;
            this.mSlideRightMargin = i3;
            return this;
        }

        public B setPermissionListener(PermissionListener permissionListener) {
            this.mPermissionListener = permissionListener;
            return this;
        }

        public B setScreenLight(boolean z) {
            this.isScreenAlwaysLight = z;
            return this;
        }

        public B setTag(@NonNull String str) {
            this.mTag = str;
            return this;
        }

        public B setTouchEnable(boolean z) {
            this.isNoTouch = z;
            return this;
        }

        public B setView(@LayoutRes int i) {
            this.mLayoutId = i;
            return this;
        }

        public B setView(@NonNull View view) {
            this.mView = view;
            return this;
        }

        public B setViewStateListener(ViewStateListener viewStateListener) {
            this.mViewStateListener = viewStateListener;
            return this;
        }

        public B setWidth(int i) {
            this.mWidth = i;
            return this;
        }

        public B setWidth(int i, float f) {
            this.mWidth = (int) (((float) (i == 0 ? Util.getScreenWidth(this.mApplicationContext) : Util.getScreenHeight(this.mApplicationContext))) * f);
            return this;
        }

        public B setX(int i) {
            this.xOffset = i;
            return this;
        }

        public B setX(int i, float f) {
            this.xOffset = (int) (((float) (i == 0 ? Util.getScreenWidth(this.mApplicationContext) : Util.getScreenHeight(this.mApplicationContext))) * f);
            return this;
        }

        public B setY(int i) {
            this.yOffset = i;
            return this;
        }

        public B setY(int i, float f) {
            this.yOffset = (int) (((float) (i == 0 ? Util.getScreenWidth(this.mApplicationContext) : Util.getScreenHeight(this.mApplicationContext))) * f);
            return this;
        }
    }

    private FloatWindow() {
    }

    public static void destroy() {
        destroy(mDefaultTag);
    }

    public static void destroy(String str) {
        PrintStream printStream = System.out;
        printStream.println("WS_BABY_DESTROY_" + str);
        if (mFloatWindowMap != null && mFloatWindowMap.containsKey(str)) {
            mFloatWindowMap.get(str).dismiss();
            mFloatWindowMap.remove(str);
        }
    }

    public static IFloatWindow get() {
        return get(mDefaultTag);
    }

    public static IFloatWindow get(@NonNull String str) {
        if (mFloatWindowMap == null) {
            return null;
        }
        return mFloatWindowMap.get(str);
    }

    @MainThread
    public static B with(@NonNull Context context) {
        B b = new B(context);
        mBuilder = b;
        return b;
    }
}
