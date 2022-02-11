package com.wx.assistants.utils.glide;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RSRuntimeException;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

public class RSBlur {
    @TargetApi(17)
    public static Bitmap blur(Context context, Bitmap bitmap, int i) throws RSRuntimeException {
        try {
            RenderScript create = RenderScript.create(context);
            Allocation createFromBitmap = Allocation.createFromBitmap(create, bitmap, Allocation.MipmapControl.MIPMAP_NONE, 1);
            Allocation createTyped = Allocation.createTyped(create, createFromBitmap.getType());
            ScriptIntrinsicBlur create2 = ScriptIntrinsicBlur.create(create, Element.U8_4(create));
            create2.setInput(createFromBitmap);
            create2.setRadius((float) i);
            create2.forEach(createTyped);
            createTyped.copyTo(bitmap);
            create.destroy();
            return bitmap;
        } catch (RSRuntimeException unused) {
            return FastBlur.blur(bitmap, i, true);
        }
    }
}
