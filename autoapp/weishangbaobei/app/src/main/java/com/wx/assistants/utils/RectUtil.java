package com.wx.assistants.utils;

import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;

public class RectUtil {
    public static void scaleRect(RectF rectF, float f) {
        float width = rectF.width();
        float height = rectF.height();
        float f2 = ((f * width) - width) / 2.0f;
        float f3 = ((f * height) - height) / 2.0f;
        rectF.left -= f2;
        rectF.top -= f3;
        rectF.right += f2;
        rectF.bottom += f3;
    }

    public static void rotateRect(RectF rectF, float f, float f2, float f3) {
        float centerX = rectF.centerX();
        float centerY = rectF.centerY();
        double d = (double) f3;
        float sin = (float) Math.sin(Math.toRadians(d));
        float cos = (float) Math.cos(Math.toRadians(d));
        float f4 = centerX - f;
        float f5 = centerY - f2;
        rectF.offset(((f + (f4 * cos)) - (f5 * sin)) - centerX, ((f2 + (f5 * cos)) + (f4 * sin)) - centerY);
    }

    public static void rotatePoint(Point point, float f, float f2, float f3) {
        double d = (double) f3;
        float sin = (float) Math.sin(Math.toRadians(d));
        float cos = (float) Math.cos(Math.toRadians(d));
        point.set((int) ((((((float) point.x) - f) * cos) + f) - ((((float) point.y) - f2) * sin)), (int) (f2 + ((((float) point.y) - f2) * cos) + ((((float) point.x) - f) * sin)));
    }

    public static void rectAddV(RectF rectF, RectF rectF2, int i) {
        if (rectF != null && rectF2 != null) {
            float f = rectF.left;
            float f2 = rectF.top;
            float f3 = rectF.right;
            float f4 = rectF.bottom;
            if (rectF.width() <= rectF2.width()) {
                f3 = rectF2.width() + f;
            }
            rectF.set(f, f2, f3, f4 + ((float) i) + rectF2.height());
        }
    }

    public static void rectAddV(RectF rectF, Rect rect, int i, int i2) {
        if (rectF != null && rect != null) {
            float f = rectF.left;
            float f2 = rectF.top;
            float f3 = rectF.right;
            float f4 = rectF.bottom;
            if (rectF.width() <= ((float) rect.width())) {
                f3 = ((float) rect.width()) + f;
            }
            rectF.set(f, f2, f3, f4 + ((float) (i + Math.max(rect.height(), i2))));
        }
    }
}
