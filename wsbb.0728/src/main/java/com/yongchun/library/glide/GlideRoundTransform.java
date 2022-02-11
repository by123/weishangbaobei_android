package com.yongchun.library.glide;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.yalantis.ucrop.view.CropImageView;

public class GlideRoundTransform extends BitmapTransformation {
    private static float radius;

    public GlideRoundTransform(Context context) {
        this(context, 4);
    }

    public GlideRoundTransform(Context context, int i) {
        super(context);
        radius = Resources.getSystem().getDisplayMetrics().density * ((float) i);
    }

    private static Bitmap roundCrop(BitmapPool bitmapPool, Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        Bitmap bitmap2 = bitmapPool.get(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        if (bitmap2 == null) {
            bitmap2 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap2);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        canvas.drawRoundRect(new RectF(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (float) bitmap.getWidth(), (float) bitmap.getHeight()), radius, radius, paint);
        return bitmap2;
    }

    public String getId() {
        return getClass().getName() + Math.round(radius);
    }

    /* access modifiers changed from: protected */
    public Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int i, int i2) {
        return roundCrop(bitmapPool, bitmap);
    }
}
