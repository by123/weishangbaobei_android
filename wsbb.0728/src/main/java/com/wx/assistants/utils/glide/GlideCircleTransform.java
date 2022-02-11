package com.wx.assistants.utils.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

public class GlideCircleTransform extends BitmapTransformation {
    public GlideCircleTransform(Context context) {
        super(context);
    }

    private static Bitmap circleCrop(BitmapPool bitmapPool, Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        int min = Math.min(bitmap.getWidth(), bitmap.getHeight());
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, (bitmap.getWidth() - min) / 2, (bitmap.getHeight() - min) / 2, min, min);
        Bitmap bitmap2 = bitmapPool.get(min, min, Bitmap.Config.ARGB_8888);
        if (bitmap2 == null) {
            bitmap2 = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap2);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(createBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        float f = ((float) min) / 2.0f;
        canvas.drawCircle(f, f, f, paint);
        return bitmap2;
    }

    public String getId() {
        return getClass().getName();
    }

    /* access modifiers changed from: protected */
    public Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int i, int i2) {
        return circleCrop(bitmapPool, bitmap);
    }
}
