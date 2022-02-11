package com.wx.assistants.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import com.yalantis.ucrop.view.CropImageView;

public class DragFloatActionButton extends AppCompatImageView {
    private boolean isDrag;
    private int lastX;
    private int lastY;
    private ViewGroup parent;
    private int parentHeight;
    private int parentWidth;

    public DragFloatActionButton(Context context) {
        super(context);
    }

    public DragFloatActionButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DragFloatActionButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private boolean isNotDrag() {
        return !this.isDrag && (getX() == CropImageView.DEFAULT_ASPECT_RATIO || getX() == ((float) (this.parentWidth - getWidth())));
    }

    private void moveHide(int i) {
        if (i >= this.parentWidth / 2) {
            animate().setInterpolator(new DecelerateInterpolator()).setDuration(500).xBy(((float) (this.parentWidth - getWidth())) - getX()).start();
            return;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "x", new float[]{getX(), 0.0f});
        ofFloat.setInterpolator(new DecelerateInterpolator());
        ofFloat.setDuration(500);
        ofFloat.start();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        float f = CropImageView.DEFAULT_ASPECT_RATIO;
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();
        switch (motionEvent.getAction() & 255) {
            case 0:
                setAlpha(0.9f);
                setPressed(true);
                this.isDrag = false;
                getParent().requestDisallowInterceptTouchEvent(true);
                this.lastX = rawX;
                this.lastY = rawY;
                if (getParent() != null) {
                    this.parent = (ViewGroup) getParent();
                    this.parentHeight = this.parent.getHeight();
                    this.parentWidth = this.parent.getWidth();
                    break;
                }
                break;
            case 1:
                if (!isNotDrag()) {
                    setPressed(false);
                    moveHide(rawX);
                    break;
                }
                break;
            case 2:
                if (((double) this.parentHeight) > 0.2d && ((double) this.parentWidth) > 0.2d) {
                    this.isDrag = true;
                    setAlpha(0.9f);
                    int i = rawX - this.lastX;
                    int i2 = rawY - this.lastY;
                    if (((int) Math.sqrt((double) ((i * i) + (i2 * i2)))) != 0) {
                        float x = ((float) i) + getX();
                        float y = ((float) i2) + getY();
                        if (x < CropImageView.DEFAULT_ASPECT_RATIO) {
                            x = 0.0f;
                        } else if (x > ((float) (this.parentWidth - getWidth()))) {
                            x = (float) (this.parentWidth - getWidth());
                        }
                        if (getY() >= CropImageView.DEFAULT_ASPECT_RATIO) {
                            f = getY() + ((float) getHeight()) > ((float) this.parentHeight) ? (float) (this.parentHeight - getHeight()) : y;
                        }
                        setX(x);
                        setY(f);
                        this.lastX = rawX;
                        this.lastY = rawY;
                        Log.i("aa", "isDrag=" + this.isDrag + "getX=" + getX() + ";getY=" + getY() + ";parentWidth=" + this.parentWidth);
                        break;
                    } else {
                        this.isDrag = false;
                        break;
                    }
                } else {
                    this.isDrag = false;
                    break;
                }
                break;
        }
        if (!isNotDrag()) {
            z = true;
        } else if (DragFloatActionButton.super.onTouchEvent(motionEvent)) {
            return true;
        } else {
            z = false;
        }
        return z;
    }
}
