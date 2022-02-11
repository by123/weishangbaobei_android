package com.wx.assistants.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import java.lang.reflect.Field;

public class AndroidBarUtils {
    private static final String NAV_BAR_HEIGHT_RES_NAME = "navigation_bar_height";
    private static final String NAV_BAR_WIDTH_RES_NAME = "navigation_bar_width";
    private static final String STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height";

    @TargetApi(21)
    private static void createNavBar(Activity activity) {
        FrameLayout.LayoutParams layoutParams;
        int navigationBarHeight = getNavigationBarHeight(activity);
        int navigationBarWidth = getNavigationBarWidth(activity);
        if (navigationBarHeight > 0 && navigationBarWidth > 0) {
            View view = new View(activity);
            if (activity.getResources().getConfiguration().orientation == 1) {
                layoutParams = new FrameLayout.LayoutParams(-1, navigationBarHeight);
                layoutParams.gravity = 80;
            } else {
                layoutParams = new FrameLayout.LayoutParams(navigationBarWidth, -1);
                layoutParams.gravity = 8388613;
            }
            view.setLayoutParams(layoutParams);
            if (Build.VERSION.SDK_INT >= 26) {
                view.setBackgroundColor(Color.parseColor("#fffafafa"));
            } else {
                view.setBackgroundColor(Color.parseColor("#40000000"));
            }
            ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
            viewGroup.addView(view);
            ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(16908290);
            if (activity.getResources().getConfiguration().orientation == 1) {
                viewGroup2.setPaddingRelative(0, 0, 0, navigationBarHeight);
            } else {
                viewGroup2.setPaddingRelative(0, 0, navigationBarWidth, 0);
            }
        }
    }

    private static int getBarHeight(Context context, String str) {
        return context.getResources().getDimensionPixelSize(context.getResources().getIdentifier(str, "dimen", "android"));
    }

    private static int getNavigationBarHeight(Activity activity) {
        if (hasNavBar(activity)) {
            return getBarHeight(activity, NAV_BAR_HEIGHT_RES_NAME);
        }
        return 0;
    }

    private static int getNavigationBarWidth(Activity activity) {
        if (hasNavBar(activity)) {
            return getBarHeight(activity, NAV_BAR_WIDTH_RES_NAME);
        }
        return 0;
    }

    private static int getStatusBarHeight(Activity activity) {
        return getBarHeight(activity, STATUS_BAR_HEIGHT_RES_NAME);
    }

    private static boolean hasNavBar(Activity activity) {
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= 19) {
            defaultDisplay.getRealMetrics(displayMetrics);
        }
        int i = displayMetrics.heightPixels;
        int i2 = displayMetrics.widthPixels;
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics2);
        return i2 - displayMetrics2.widthPixels > 0 || i - displayMetrics2.heightPixels > 0;
    }

    public static void setBarDarkMode(Activity activity, boolean z) {
        Window window = activity.getWindow();
        if (window != null) {
            if (!z) {
                int i = 0;
                if (Build.VERSION.SDK_INT >= 23) {
                    i = window.getDecorView().getSystemUiVisibility() & -8193;
                }
                if (Build.VERSION.SDK_INT >= 26) {
                    window.getDecorView().setSystemUiVisibility(i | 16);
                } else {
                    window.getDecorView().setSystemUiVisibility(i);
                }
            } else if (Build.VERSION.SDK_INT >= 23) {
                if (Build.VERSION.SDK_INT >= 26) {
                    window.getDecorView().setSystemUiVisibility(8208);
                } else {
                    window.getDecorView().setSystemUiVisibility(8192);
                }
            }
            setHuaWeiStatusBar(z, window);
        }
    }

    public static void setBarPaddingTop(Activity activity, View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            int paddingStart = view.getPaddingStart();
            int paddingEnd = view.getPaddingEnd();
            int paddingBottom = view.getPaddingBottom();
            int statusBarHeight = getStatusBarHeight(activity);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height += statusBarHeight;
            view.setLayoutParams(layoutParams);
            view.setPaddingRelative(paddingStart, statusBarHeight, paddingEnd, paddingBottom);
        }
    }

    private static void setHuaWeiStatusBar(boolean z, Window window) {
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                Field declaredField = Class.forName("com.android.internal.policy.DecorView").getDeclaredField("mSemiTransparentStatusBarColor");
                declaredField.setAccessible(z);
                declaredField.setInt(window.getDecorView(), 0);
            } catch (ClassNotFoundException e) {
                Log.e("setHuaWeiStatusBar", "HuaWei status bar 模式设置失败");
            } catch (IllegalAccessException e2) {
                Log.e("setHuaWeiStatusBar", "HuaWei status bar 模式设置失败");
            } catch (NoSuchFieldException e3) {
                Log.e("setHuaWeiStatusBar", "HuaWei status bar 模式设置失败");
            }
        }
    }

    public static void setTranslucent(Activity activity) {
        setTranslucentStatusBar(activity);
    }

    public static void setTranslucentDrawerLayout(DrawerLayout drawerLayout) {
        if (drawerLayout != null && Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            drawerLayout.setFitsSystemWindows(true);
            drawerLayout.setClipToPadding(false);
        }
    }

    private static void setTranslucentStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= 19) {
            Window window = activity.getWindow();
            window.setFlags(WXMediaMessage.TITLE_LENGTH_LIMIT, WXMediaMessage.TITLE_LENGTH_LIMIT);
            if (Build.VERSION.SDK_INT >= 21) {
                createNavBar(activity);
            } else {
                window.addFlags(67108864);
            }
        }
    }
}
