package com.umeng.social.tool;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import com.yalantis.ucrop.view.CropImageView;

public class ComposeTool {
    public static int backgroundColor = -1;
    public static ComposeDirection direction = ComposeDirection.CUSTOM;
    public static int textColor = -16777216;
    public static int textsize = 18;
    public static Typeface typeface = Typeface.DEFAULT;

    public enum ComposeDirection {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        LEFTUP,
        LEFTDOWN,
        RIGHTUP,
        RIGHTDOWN,
        CUSTOM
    }

    public static Bitmap createCompose(Bitmap bitmap, Bitmap bitmap2, boolean z, int i) {
        int i2;
        if (bitmap == null || bitmap2 == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int width2 = bitmap2.getWidth();
        int height2 = bitmap2.getHeight();
        int max = z ? Math.max(width, width2) : width2 + width + i;
        if (z) {
            i2 = height2 + height + i;
        } else {
            i2 = Math.max(height, height2);
        }
        Bitmap createBitmap = Bitmap.createBitmap(max, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (Paint) null);
        if (z) {
            canvas.drawBitmap(bitmap2, CropImageView.DEFAULT_ASPECT_RATIO, (float) (height + i), (Paint) null);
        } else {
            canvas.drawBitmap(bitmap2, (float) (width + i), CropImageView.DEFAULT_ASPECT_RATIO, (Paint) null);
        }
        canvas.save(31);
        canvas.restore();
        return createBitmap;
    }

    public static Bitmap createWaterMask(Bitmap bitmap, Bitmap bitmap2, int i, int i2) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int width2 = bitmap2.getWidth();
        int height2 = bitmap2.getHeight();
        int i3 = (width / 2) - (width2 / 2);
        int i4 = (height / 2) - (height2 / 2);
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (Paint) null);
        if (direction == ComposeDirection.CUSTOM) {
            canvas.drawBitmap(bitmap2, (float) i, (float) i2, (Paint) null);
        } else if (direction == ComposeDirection.UP) {
            canvas.drawBitmap(bitmap2, (float) i3, CropImageView.DEFAULT_ASPECT_RATIO, (Paint) null);
        } else if (direction == ComposeDirection.DOWN) {
            canvas.drawBitmap(bitmap2, (float) i3, (float) (height - height2), (Paint) null);
        } else if (direction == ComposeDirection.LEFT) {
            canvas.drawBitmap(bitmap2, CropImageView.DEFAULT_ASPECT_RATIO, (float) i4, (Paint) null);
        } else if (direction == ComposeDirection.RIGHT) {
            canvas.drawBitmap(bitmap2, (float) (width - width2), (float) i4, (Paint) null);
        } else if (direction == ComposeDirection.LEFTUP) {
            canvas.drawBitmap(bitmap2, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (Paint) null);
        } else if (direction == ComposeDirection.LEFTDOWN) {
            canvas.drawBitmap(bitmap2, CropImageView.DEFAULT_ASPECT_RATIO, (float) (height - height2), (Paint) null);
        } else if (direction == ComposeDirection.RIGHTUP) {
            canvas.drawBitmap(bitmap2, (float) (width - width2), CropImageView.DEFAULT_ASPECT_RATIO, (Paint) null);
        } else if (direction == ComposeDirection.RIGHTDOWN) {
            canvas.drawBitmap(bitmap2, (float) (width - width2), (float) (height - height2), (Paint) null);
        }
        canvas.save(31);
        canvas.restore();
        return createBitmap;
    }

    public static Bitmap createTextImage(String str, Bitmap bitmap, int i, int i2) {
        int i3 = i;
        int i4 = i2;
        Bitmap.Config config = bitmap.getConfig();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        TextPaint textPaint = new TextPaint(1);
        textPaint.setColor(textColor);
        textPaint.setTextSize((float) textsize);
        textPaint.setDither(true);
        textPaint.setFilterBitmap(true);
        textPaint.setTypeface(typeface);
        StaticLayout staticLayout = new StaticLayout(str, textPaint, width, Layout.Alignment.ALIGN_NORMAL, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, true);
        String str2 = str;
        textPaint.getTextBounds(str2, 0, str.length(), new Rect());
        Bitmap copy = bitmap.copy(config, true);
        Bitmap createBitmap = Bitmap.createBitmap(width + (i3 * 2), height + staticLayout.getHeight() + (i4 * 4), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(backgroundColor);
        float f = (float) i3;
        canvas.drawBitmap(copy, f, (float) (staticLayout.getHeight() + (i4 * 3)), (Paint) null);
        canvas.translate(f, (float) i4);
        staticLayout.draw(canvas);
        canvas.save(31);
        canvas.restore();
        return createBitmap;
    }
}
