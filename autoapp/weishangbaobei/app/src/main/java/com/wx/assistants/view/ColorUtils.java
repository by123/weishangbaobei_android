package com.wx.assistants.view;

import android.content.res.ColorStateList;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class ColorUtils {
    private static final int CHECKED_ATTR = 16842912;
    private static final int ENABLE_ATTR = 16842910;
    private static final int PRESSED_ATTR = 16842919;

    static ColorStateList generateThumbColorWithTintColor(int i) {
        int[][] iArr = {new int[]{-16842910, CHECKED_ATTR}, new int[]{-16842910}, new int[]{PRESSED_ATTR, -16842912}, new int[]{PRESSED_ATTR, CHECKED_ATTR}, new int[]{CHECKED_ATTR}, new int[]{-16842912}};
        int i2 = i - SystemBarTintManager.DEFAULT_TINT_COLOR;
        return new ColorStateList(iArr, new int[]{i - -1442840576, -4539718, i2, i2, i | -16777216, -1118482});
    }

    static ColorStateList generateBackColorWithTintColor(int i) {
        int i2 = i - -805306368;
        return new ColorStateList(new int[][]{new int[]{-16842910, CHECKED_ATTR}, new int[]{-16842910}, new int[]{CHECKED_ATTR, PRESSED_ATTR}, new int[]{-16842912, PRESSED_ATTR}, new int[]{CHECKED_ATTR}, new int[]{-16842912}}, new int[]{i - -520093696, 268435456, i2, 536870912, i2, 536870912});
    }
}
