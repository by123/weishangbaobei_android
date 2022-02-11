package com.wx.assistants.view;

import android.content.res.ColorStateList;

public class ColorUtils {
    private static final int CHECKED_ATTR = 16842912;
    private static final int ENABLE_ATTR = 16842910;
    private static final int PRESSED_ATTR = 16842919;

    static ColorStateList generateBackColorWithTintColor(int i) {
        int i2 = 805306368 + i;
        return new ColorStateList(new int[][]{new int[]{-16842910, CHECKED_ATTR}, new int[]{-16842910}, new int[]{CHECKED_ATTR, PRESSED_ATTR}, new int[]{-16842912, PRESSED_ATTR}, new int[]{CHECKED_ATTR}, new int[]{-16842912}}, new int[]{520093696 + i, 268435456, i2, 536870912, i2, 536870912});
    }

    static ColorStateList generateThumbColorWithTintColor(int i) {
        int i2 = 1728053248 + i;
        return new ColorStateList(new int[][]{new int[]{-16842910, CHECKED_ATTR}, new int[]{-16842910}, new int[]{PRESSED_ATTR, -16842912}, new int[]{PRESSED_ATTR, CHECKED_ATTR}, new int[]{CHECKED_ATTR}, new int[]{-16842912}}, new int[]{1442840576 + i, -4539718, i2, i2, -16777216 | i, -1118482});
    }
}
