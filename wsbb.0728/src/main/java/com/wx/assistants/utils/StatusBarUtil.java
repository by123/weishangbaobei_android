package com.wx.assistants.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import ezy.assist.compat.RomUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

public class StatusBarUtil {
    public static final int DEFAULT_STATUS_BAR_ALPHA = 112;
    private static final int FAKE_STATUS_BAR_VIEW_ID = 2131297432;
    private static final int FAKE_TRANSLUCENT_VIEW_ID = 2131297434;
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final int SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT = 16;
    public static final String TAG = "StatusBarUtil";
    private static final int TAG_KEY_HAVE_SET_OFFSET = -123;
    private static Drawable mStatusDrawable;
    public static boolean sIsStatusBarLight;

    private StatusBarUtil() {
    }

    private static void addTranslucentView(Activity activity, @IntRange(from = 0, to = 255) int i) {
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(16908290);
        View findViewById = viewGroup.findViewById(FAKE_TRANSLUCENT_VIEW_ID);
        if (findViewById != null) {
            if (findViewById.getVisibility() == 8) {
                findViewById.setVisibility(0);
            }
            findViewById.setBackgroundColor(Color.argb(i, 0, 0, 0));
            return;
        }
        viewGroup.addView(createTranslucentStatusBarView(activity, i));
    }

    private static void addTranslucentView(Activity activity, Drawable drawable, @IntRange(from = 0, to = 255) int i) {
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(16908290);
        View findViewById = viewGroup.findViewById(FAKE_TRANSLUCENT_VIEW_ID);
        if (findViewById != null) {
            if (findViewById.getVisibility() == 8) {
                findViewById.setVisibility(0);
            }
            findViewById.setBackgroundDrawable(drawable);
            return;
        }
        viewGroup.addView(createTranslucentStatusBarView(activity, drawable, i));
    }

    private static int calculateStatusColor(@ColorInt int i, int i2) {
        if (i2 == 0) {
            return i;
        }
        float f = 1.0f - (((float) i2) / 255.0f);
        double d = (double) (((float) ((i >> 16) & 255)) * f);
        Double.isNaN(d);
        int i3 = (int) (d + 0.5d);
        double d2 = (double) (((float) ((i >> 8) & 255)) * f);
        Double.isNaN(d2);
        double d3 = (double) (f * ((float) (i & 255)));
        Double.isNaN(d3);
        return ((int) (d3 + 0.5d)) | (i3 << 16) | -16777216 | (((int) (d2 + 0.5d)) << 8);
    }

    @TargetApi(19)
    private static void clearPreviousSetting(Activity activity) {
        ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
        View findViewById = viewGroup.findViewById(FAKE_STATUS_BAR_VIEW_ID);
        if (findViewById != null) {
            viewGroup.removeView(findViewById);
            ((ViewGroup) ((ViewGroup) activity.findViewById(16908290)).getChildAt(0)).setPadding(0, 0, 0, 0);
        }
    }

    private static View createStatusBarView(Activity activity, @ColorInt int i) {
        return createStatusBarView(activity, i, 0);
    }

    private static View createStatusBarView(Activity activity, @ColorInt int i, int i2) {
        View view = new View(activity);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, getStatusBarHeight(activity)));
        view.setBackgroundColor(calculateStatusColor(i, i2));
        view.setId(FAKE_STATUS_BAR_VIEW_ID);
        return view;
    }

    public static View createTranslucentStatusBarView(Activity activity, int i) {
        View view = new View(activity);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, getStatusBarHeight(activity)));
        view.setBackgroundColor(Color.argb(i, 0, 0, 0));
        view.setId(FAKE_TRANSLUCENT_VIEW_ID);
        return view;
    }

    public static View createTranslucentStatusBarView(Activity activity, Drawable drawable, int i) {
        View view = new View(activity);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, getStatusBarHeight(activity)));
        view.setBackgroundDrawable(drawable);
        view.setId(FAKE_TRANSLUCENT_VIEW_ID);
        return view;
    }

    public static boolean flymeSetStatusBarLightMode(Window window, boolean z) {
        if (window != null) {
            try {
                WindowManager.LayoutParams attributes = window.getAttributes();
                Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                declaredField.setAccessible(true);
                declaredField2.setAccessible(true);
                int i = declaredField.getInt((Object) null);
                int i2 = declaredField2.getInt(attributes);
                declaredField2.setInt(attributes, z ? i | i2 : (i ^ -1) & i2);
                window.setAttributes(attributes);
                return true;
            } catch (Exception e) {
            }
        }
        return false;
    }

    public static int getStatusBarHeight(Context context) {
        return context.getResources().getDimensionPixelSize(context.getResources().getIdentifier("status_bar_height", "dimen", "android"));
    }

    public static int getStatusBarLightMode(Window window) {
        if (Build.VERSION.SDK_INT >= 19) {
            if (isMIUI()) {
                if (miuisetstatusbarlightmode(window, true)) {
                    return 1;
                }
            } else if (isFlyme()) {
                if (flymeSetStatusBarLightMode(window, true)) {
                    return 2;
                }
            } else if (isOppo()) {
                return 4;
            } else {
                if (Build.VERSION.SDK_INT >= 23) {
                    window.getDecorView().setSystemUiVisibility(8192);
                    return 3;
                }
            }
        }
        return 0;
    }

    public static void hideFakeStatusBarView(Activity activity) {
        ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
        View findViewById = viewGroup.findViewById(FAKE_STATUS_BAR_VIEW_ID);
        if (findViewById != null) {
            findViewById.setVisibility(8);
        }
        View findViewById2 = viewGroup.findViewById(FAKE_TRANSLUCENT_VIEW_ID);
        if (findViewById2 != null) {
            findViewById2.setVisibility(8);
        }
    }

    public static boolean isFlyme() {
        try {
            return Build.class.getMethod("hasSmartBar", new Class[0]) != null;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isMIUI() {
        if (!Build.MANUFACTURER.equals("Xiaomi")) {
            return false;
        }
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            return (properties.getProperty(KEY_MIUI_VERSION_CODE, (String) null) == null && properties.getProperty(KEY_MIUI_VERSION_NAME, (String) null) == null && properties.getProperty(KEY_MIUI_INTERNAL_STORAGE, (String) null) == null) ? false : true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isOppo() {
        try {
            return Build.MANUFACTURER.equalsIgnoreCase(RomUtil.ROM_OPPO);
        } catch (Exception e) {
        }
    }

    public static boolean miuisetstatusbarlightmode(Window window, boolean z) {
        boolean z2 = true;
        if (window == null) {
            return false;
        }
        Class<?> cls = window.getClass();
        try {
            Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
            Method method = cls.getMethod("setExtraFlags", new Class[]{Integer.TYPE, Integer.TYPE});
            if (z) {
                method.invoke(window, new Object[]{Integer.valueOf(i), Integer.valueOf(i)});
            } else {
                method.invoke(window, new Object[]{0, Integer.valueOf(i)});
            }
            try {
                if (Build.VERSION.SDK_INT < 23) {
                    return true;
                }
                if (z) {
                    window.getDecorView().setSystemUiVisibility(9216);
                    return true;
                }
                window.getDecorView().setSystemUiVisibility(0);
                return true;
            } catch (Exception e) {
                e = e;
            }
        } catch (Exception e2) {
            e = e2;
            z2 = false;
        }
        Log.e(TAG, "miuisetstatusbarlightmode ", e);
        return z2;
    }

    public static void setColor(Activity activity, @ColorInt int i) {
        setColor(activity, i, 112);
    }

    public static void setColor(Activity activity, @ColorInt int i, @IntRange(from = 0, to = 255) int i2) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.getWindow().addFlags(Integer.MIN_VALUE);
            activity.getWindow().clearFlags(67108864);
            activity.getWindow().setStatusBarColor(calculateStatusColor(i, i2));
        } else if (Build.VERSION.SDK_INT >= 19) {
            activity.getWindow().addFlags(67108864);
            ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
            View findViewById = viewGroup.findViewById(FAKE_STATUS_BAR_VIEW_ID);
            if (findViewById != null) {
                if (findViewById.getVisibility() == 8) {
                    findViewById.setVisibility(0);
                }
                findViewById.setBackgroundColor(calculateStatusColor(i, i2));
            } else {
                viewGroup.addView(createStatusBarView(activity, i, i2));
            }
            setRootView(activity);
        }
    }

    @Deprecated
    public static void setColorDiff(Activity activity, @ColorInt int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            transparentStatusBar(activity);
            ViewGroup viewGroup = (ViewGroup) activity.findViewById(16908290);
            View findViewById = viewGroup.findViewById(FAKE_STATUS_BAR_VIEW_ID);
            if (findViewById != null) {
                if (findViewById.getVisibility() == 8) {
                    findViewById.setVisibility(0);
                }
                findViewById.setBackgroundColor(i);
            } else {
                viewGroup.addView(createStatusBarView(activity, i));
            }
            setRootView(activity);
        }
    }

    public static void setColorForDrawerLayout(Activity activity, DrawerLayout drawerLayout, @ColorInt int i) {
        setColorForDrawerLayout(activity, drawerLayout, i, 112);
    }

    public static void setColorForDrawerLayout(Activity activity, DrawerLayout drawerLayout, @ColorInt int i, @IntRange(from = 0, to = 255) int i2) {
        if (Build.VERSION.SDK_INT >= 19) {
            if (Build.VERSION.SDK_INT >= 21) {
                activity.getWindow().addFlags(Integer.MIN_VALUE);
                activity.getWindow().clearFlags(67108864);
                activity.getWindow().setStatusBarColor(0);
            } else {
                activity.getWindow().addFlags(67108864);
            }
            ViewGroup viewGroup = (ViewGroup) drawerLayout.getChildAt(0);
            View findViewById = viewGroup.findViewById(FAKE_STATUS_BAR_VIEW_ID);
            if (findViewById != null) {
                if (findViewById.getVisibility() == 8) {
                    findViewById.setVisibility(0);
                }
                findViewById.setBackgroundColor(i);
            } else {
                viewGroup.addView(createStatusBarView(activity, i), 0);
            }
            if (!(viewGroup instanceof LinearLayout) && viewGroup.getChildAt(1) != null) {
                viewGroup.getChildAt(1).setPadding(viewGroup.getPaddingLeft(), getStatusBarHeight(activity) + viewGroup.getPaddingTop(), viewGroup.getPaddingRight(), viewGroup.getPaddingBottom());
            }
            setDrawerLayoutProperty(drawerLayout, viewGroup);
            addTranslucentView(activity, i2);
        }
    }

    @Deprecated
    public static void setColorForDrawerLayoutDiff(Activity activity, DrawerLayout drawerLayout, @ColorInt int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            activity.getWindow().addFlags(67108864);
            ViewGroup viewGroup = (ViewGroup) drawerLayout.getChildAt(0);
            View findViewById = viewGroup.findViewById(FAKE_STATUS_BAR_VIEW_ID);
            if (findViewById != null) {
                if (findViewById.getVisibility() == 8) {
                    findViewById.setVisibility(0);
                }
                findViewById.setBackgroundColor(calculateStatusColor(i, 112));
            } else {
                viewGroup.addView(createStatusBarView(activity, i), 0);
            }
            if (!(viewGroup instanceof LinearLayout) && viewGroup.getChildAt(1) != null) {
                viewGroup.getChildAt(1).setPadding(0, getStatusBarHeight(activity), 0, 0);
            }
            setDrawerLayoutProperty(drawerLayout, viewGroup);
        }
    }

    public static void setColorForSwipeBack(Activity activity, int i) {
        setColorForSwipeBack(activity, i, 112);
    }

    public static void setColorForSwipeBack(Activity activity, @ColorInt int i, @IntRange(from = 0, to = 255) int i2) {
        if (Build.VERSION.SDK_INT >= 19) {
            ViewGroup viewGroup = (ViewGroup) activity.findViewById(16908290);
            CoordinatorLayout childAt = viewGroup.getChildAt(0);
            int statusBarHeight = getStatusBarHeight(activity);
            if (childAt == null || !(childAt instanceof CoordinatorLayout)) {
                viewGroup.setPadding(0, statusBarHeight, 0, 0);
                viewGroup.setBackgroundColor(calculateStatusColor(i, i2));
            } else {
                final CoordinatorLayout coordinatorLayout = childAt;
                if (Build.VERSION.SDK_INT < 21) {
                    coordinatorLayout.setFitsSystemWindows(false);
                    viewGroup.setBackgroundColor(calculateStatusColor(i, i2));
                    if (viewGroup.getPaddingTop() < statusBarHeight) {
                        viewGroup.setPadding(0, statusBarHeight, 0, 0);
                        coordinatorLayout.post(new Runnable() {
                            public void run() {
                                coordinatorLayout.requestLayout();
                            }
                        });
                    }
                } else {
                    coordinatorLayout.setStatusBarBackgroundColor(calculateStatusColor(i, i2));
                }
            }
            setTransparentForWindow(activity);
        }
    }

    public static void setColorNoTranslucent(Activity activity, @ColorInt int i) {
        setColor(activity, i, 0);
    }

    public static void setColorNoTranslucentForDrawerLayout(Activity activity, DrawerLayout drawerLayout, @ColorInt int i) {
        setColorForDrawerLayout(activity, drawerLayout, i, 0);
    }

    private static void setDrawerLayoutProperty(DrawerLayout drawerLayout, ViewGroup viewGroup) {
        drawerLayout.setFitsSystemWindows(false);
        viewGroup.setFitsSystemWindows(false);
        viewGroup.setClipToPadding(true);
        ((ViewGroup) drawerLayout.getChildAt(1)).setFitsSystemWindows(false);
    }

    public static void setIsStatusBarLight(boolean z) {
        sIsStatusBarLight = z;
    }

    private static void setOPPOStatusTextColor(Window window, boolean z) {
        window.addFlags(Integer.MIN_VALUE);
        int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
        if (Build.VERSION.SDK_INT >= 23) {
            systemUiVisibility = z ? systemUiVisibility | 8192 : systemUiVisibility & -8193;
        } else if (Build.VERSION.SDK_INT >= 21) {
            systemUiVisibility = z ? systemUiVisibility | 16 : systemUiVisibility & -17;
        }
        window.getDecorView().setSystemUiVisibility(systemUiVisibility);
    }

    private static void setRootView(Activity activity) {
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(16908290);
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                childAt.setFitsSystemWindows(true);
                ((ViewGroup) childAt).setClipToPadding(true);
            }
        }
    }

    public static void setStatusBarDarkMode(Window window) {
        int statusBarLightMode = getStatusBarLightMode(window);
        if (statusBarLightMode == 1) {
            miuisetstatusbarlightmode(window, false);
        } else if (statusBarLightMode == 2) {
            flymeSetStatusBarLightMode(window, false);
        } else if (statusBarLightMode == 3) {
            window.getDecorView().setSystemUiVisibility(0);
        }
    }

    public static void setStatusBarLightMode(Window window) {
        int statusBarLightMode = getStatusBarLightMode(window);
        if (statusBarLightMode == 1) {
            miuisetstatusbarlightmode(window, true);
        } else if (statusBarLightMode == 2) {
            flymeSetStatusBarLightMode(window, true);
        } else if (statusBarLightMode == 3) {
            window.getDecorView().setSystemUiVisibility(8192);
        } else if (statusBarLightMode == 4) {
            setOPPOStatusTextColor(window, true);
        }
    }

    public static void setTranslucent(Activity activity) {
        setTranslucent(activity, 112);
    }

    public static void setTranslucent(Activity activity, @IntRange(from = 0, to = 255) int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            setTransparent(activity);
            addTranslucentView(activity, i);
        }
    }

    @Deprecated
    public static void setTranslucentDiff(Activity activity) {
        if (Build.VERSION.SDK_INT >= 19) {
            activity.getWindow().addFlags(67108864);
            setRootView(activity);
        }
    }

    public static void setTranslucentForCoordinatorLayout(Activity activity, @IntRange(from = 0, to = 255) int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            transparentStatusBar(activity);
            addTranslucentView(activity, i);
        }
    }

    public static void setTranslucentForDrawerLayout(Activity activity, DrawerLayout drawerLayout) {
        setTranslucentForDrawerLayout(activity, drawerLayout, 112);
    }

    public static void setTranslucentForDrawerLayout(Activity activity, DrawerLayout drawerLayout, @IntRange(from = 0, to = 255) int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            setTransparentForDrawerLayout(activity, drawerLayout);
            addTranslucentView(activity, i);
        }
    }

    @Deprecated
    public static void setTranslucentForDrawerLayoutDiff(Activity activity, DrawerLayout drawerLayout) {
        if (Build.VERSION.SDK_INT >= 19) {
            activity.getWindow().addFlags(67108864);
            ViewGroup viewGroup = (ViewGroup) drawerLayout.getChildAt(0);
            viewGroup.setFitsSystemWindows(true);
            viewGroup.setClipToPadding(true);
            ((ViewGroup) drawerLayout.getChildAt(1)).setFitsSystemWindows(false);
            drawerLayout.setFitsSystemWindows(false);
        }
    }

    public static void setTranslucentForImageView(Activity activity, @IntRange(from = 0, to = 255) int i, View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            setTransparentForWindow(activity);
            addTranslucentView(activity, i);
            if (view != null) {
                Object tag = view.getTag(TAG_KEY_HAVE_SET_OFFSET);
                if (tag == null || !((Boolean) tag).booleanValue()) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                    marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin + getStatusBarHeight(activity), marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
                    view.setTag(TAG_KEY_HAVE_SET_OFFSET, true);
                }
            }
        }
    }

    public static void setTranslucentForImageView(Activity activity, View view) {
        setTranslucentForImageView(activity, 112, view);
    }

    public static void setTranslucentForImageViewInFragment(Activity activity, @IntRange(from = 0, to = 255) int i, View view) {
        setTranslucentForImageView(activity, i, view);
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            clearPreviousSetting(activity);
        }
    }

    public static void setTranslucentForImageViewInFragment(Activity activity, View view) {
        setTranslucentForImageViewInFragment(activity, 112, view);
    }

    public static void setTranslucentWithDrawble(Activity activity, Drawable drawable, @IntRange(from = 0, to = 255) int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            setTransparent(activity);
            mStatusDrawable = drawable;
            addTranslucentView(activity, mStatusDrawable, i);
        }
    }

    public static void setTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT >= 19) {
            transparentStatusBar(activity);
            setRootView(activity);
        }
    }

    public static void setTransparentForDrawerLayout(Activity activity, DrawerLayout drawerLayout) {
        if (Build.VERSION.SDK_INT >= 19) {
            if (Build.VERSION.SDK_INT >= 21) {
                activity.getWindow().addFlags(Integer.MIN_VALUE);
                activity.getWindow().clearFlags(67108864);
                activity.getWindow().setStatusBarColor(0);
            } else {
                activity.getWindow().addFlags(67108864);
            }
            ViewGroup viewGroup = (ViewGroup) drawerLayout.getChildAt(0);
            if (!(viewGroup instanceof LinearLayout) && viewGroup.getChildAt(1) != null) {
                viewGroup.getChildAt(1).setPadding(0, getStatusBarHeight(activity), 0, 0);
            }
            setDrawerLayoutProperty(drawerLayout, viewGroup);
        }
    }

    public static void setTransparentForImageView(Activity activity, View view) {
        setTranslucentForImageView(activity, 0, view);
    }

    public static void setTransparentForImageViewInFragment(Activity activity, View view) {
        setTranslucentForImageViewInFragment(activity, 0, view);
    }

    private static void setTransparentForWindow(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.getWindow().setStatusBarColor(0);
            activity.getWindow().getDecorView().setSystemUiVisibility(1280);
        } else if (Build.VERSION.SDK_INT >= 19) {
            activity.getWindow().setFlags(67108864, 67108864);
        }
    }

    @TargetApi(19)
    private static void transparentStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.getWindow().addFlags(Integer.MIN_VALUE);
            activity.getWindow().clearFlags(67108864);
            activity.getWindow().addFlags(134217728);
            activity.getWindow().setStatusBarColor(0);
            return;
        }
        activity.getWindow().addFlags(67108864);
    }
}
