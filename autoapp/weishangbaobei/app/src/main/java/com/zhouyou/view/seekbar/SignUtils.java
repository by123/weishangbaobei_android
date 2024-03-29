package com.zhouyou.view.seekbar;

import android.content.res.Resources;
import android.os.Environment;
import android.util.TypedValue;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SignUtils {
    private static final File BUILD_PROP_FILE = new File(Environment.getRootDirectory(), "build.prop");
    private static Properties sBuildProperties;
    private static final Object sBuildPropertiesLock = new Object();

    private static Properties getBuildProperties() {
        synchronized (sBuildPropertiesLock) {
            if (sBuildProperties == null) {
                sBuildProperties = new Properties();
                try {
                    sBuildProperties.load(new FileInputStream(BUILD_PROP_FILE));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sBuildProperties;
    }

    static int dp2px(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, Resources.getSystem().getDisplayMetrics());
    }

    static int sp2px(int i) {
        return (int) TypedValue.applyDimension(2, (float) i, Resources.getSystem().getDisplayMetrics());
    }
}
