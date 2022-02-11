package com.wx.assistants.view;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;

public class MoveTextView extends AppCompatButton {
    private boolean isMoved;
    private WindowManager.LayoutParams lp;
    private float mRawX;
    private float mRawY;
    private float mStartX;
    private float mStartY;
    private WindowManager wm;

    public MoveTextView(Context context) {
        super(context);
    }

    public MoveTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MoveTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [com.wx.assistants.view.MoveTextView, android.view.View] */
    private void updateWindowPosition() {
        Point point = new Point();
        this.wm.getDefaultDisplay().getSize(point);
        int i = point.y;
        if (this.lp != null) {
            this.lp.x = (int) (this.mRawX - this.mStartX);
            this.lp.y = (int) (this.mRawY - this.mStartY);
            Log.d("tag", "updateWindowPosition: " + this.mRawY + "\n" + this.mStartY + "\n" + this.lp.y);
            this.wm.updateViewLayout(this, this.lp);
        }
    }

    public float getRawX() {
        return this.mRawX;
    }

    public float getRawY() {
        return this.mRawY;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        this.mRawX = motionEvent.getRawX();
        this.mRawY = motionEvent.getRawY();
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mStartX = motionEvent.getX();
            this.mStartY = motionEvent.getY();
            this.isMoved = false;
        } else if (action == 2 && (Math.abs(this.mStartX - motionEvent.getX()) > 10.0f || Math.abs(this.mStartY - motionEvent.getY()) > 10.0f)) {
            updateWindowPosition();
            this.isMoved = true;
            return true;
        }
        if (this.isMoved || MoveTextView.super.onTouchEvent(motionEvent)) {
            z = true;
        }
        return z;
    }

    public void setWm(WindowManager windowManager, WindowManager.LayoutParams layoutParams) {
        this.wm = windowManager;
        this.lp = layoutParams;
    }
}
