package com.luck.picture.lib.dialog;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.luck.picture.lib.R;

public class PictureSpinView extends ImageView implements PictureIndeterminate {
    /* access modifiers changed from: private */
    public int mFrameTime;
    /* access modifiers changed from: private */
    public boolean mNeedToUpdateView;
    /* access modifiers changed from: private */
    public float mRotateDegrees;
    private Runnable mUpdateViewRunnable;

    public PictureSpinView(Context context) {
        super(context);
        init();
    }

    public PictureSpinView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        setImageResource(R.drawable.kprogresshud_spinner);
        this.mFrameTime = 83;
        this.mUpdateViewRunnable = new Runnable() {
            public void run() {
                float unused = PictureSpinView.this.mRotateDegrees = PictureSpinView.this.mRotateDegrees + 30.0f;
                float unused2 = PictureSpinView.this.mRotateDegrees = PictureSpinView.this.mRotateDegrees < 360.0f ? PictureSpinView.this.mRotateDegrees : PictureSpinView.this.mRotateDegrees - 360.0f;
                PictureSpinView.this.invalidate();
                if (PictureSpinView.this.mNeedToUpdateView) {
                    PictureSpinView.this.postDelayed(this, (long) PictureSpinView.this.mFrameTime);
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mNeedToUpdateView = true;
        post(this.mUpdateViewRunnable);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.mNeedToUpdateView = false;
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        canvas.rotate(this.mRotateDegrees, (float) (getWidth() / 2), (float) (getHeight() / 2));
        super.onDraw(canvas);
    }

    public void setAnimationSpeed(float f) {
        this.mFrameTime = (int) (83.0f / f);
    }
}
