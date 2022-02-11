package com.wx.assistants.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wx.assistants.application.MyApplication;

@SuppressLint({"AppCompatCustomView"})
public class CustomImageView extends ImageView {
    private boolean isAttachedToWindow;
    private String url;

    public CustomImageView(Context context) {
        super(context);
    }

    public CustomImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onAttachedToWindow() {
        this.isAttachedToWindow = true;
        setImageUrl(this.url);
        super.onAttachedToWindow();
    }

    public void onDetachedFromWindow() {
        this.isAttachedToWindow = false;
        setImageBitmap((Bitmap) null);
        super.onDetachedFromWindow();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                Drawable drawable = getDrawable();
                if (drawable != null) {
                    drawable.mutate().setColorFilter(-7829368, PorterDuff.Mode.MULTIPLY);
                    break;
                }
                break;
            case 1:
            case 3:
                Drawable drawable2 = getDrawable();
                if (drawable2 != null) {
                    drawable2.mutate().clearColorFilter();
                    break;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setImageUrl(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.url = str;
            if (this.isAttachedToWindow) {
                Glide.with(MyApplication.getConText()).load(str).diskCacheStrategy(DiskCacheStrategy.ALL).thumbnail(0.5f).placeholder(new ColorDrawable(Color.parseColor("#f5f5f5"))).into(this);
            }
        }
    }
}
