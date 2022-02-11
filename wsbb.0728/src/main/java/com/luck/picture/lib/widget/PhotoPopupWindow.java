package com.luck.picture.lib.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.luck.picture.lib.R;

public class PhotoPopupWindow extends PopupWindow implements View.OnClickListener {
    private Animation animationIn;
    private Animation animationOut;
    private FrameLayout fl_content;
    /* access modifiers changed from: private */
    public boolean isDismiss = false;
    private LinearLayout ll_root;
    private OnItemClickListener onItemClickListener;
    private TextView picture_tv_cancel;
    private TextView picture_tv_photo;
    private TextView picture_tv_video;

    public interface OnItemClickListener {
        void onItemClick(int i);
    }

    public PhotoPopupWindow(Context context) {
        super(context);
        View inflate = LayoutInflater.from(context).inflate(R.layout.picture_camera_pop_layout, (ViewGroup) null);
        setWidth(-1);
        setHeight(-1);
        setBackgroundDrawable(new ColorDrawable());
        setFocusable(true);
        setOutsideTouchable(true);
        update();
        setBackgroundDrawable(new ColorDrawable());
        setContentView(inflate);
        this.animationIn = AnimationUtils.loadAnimation(context, R.anim.up_in);
        this.animationOut = AnimationUtils.loadAnimation(context, R.anim.down_out);
        this.ll_root = (LinearLayout) inflate.findViewById(R.id.ll_root);
        this.fl_content = (FrameLayout) inflate.findViewById(R.id.fl_content);
        this.picture_tv_photo = (TextView) inflate.findViewById(R.id.picture_tv_photo);
        this.picture_tv_cancel = (TextView) inflate.findViewById(R.id.picture_tv_cancel);
        this.picture_tv_video = (TextView) inflate.findViewById(R.id.picture_tv_video);
        this.picture_tv_video.setOnClickListener(this);
        this.picture_tv_cancel.setOnClickListener(this);
        this.picture_tv_photo.setOnClickListener(this);
        this.fl_content.setOnClickListener(this);
    }

    /* access modifiers changed from: private */
    public void dismiss4Pop() {
        new Handler().post(new Runnable() {
            public void run() {
                PhotoPopupWindow.super.dismiss();
            }
        });
    }

    public void dismiss() {
        if (!this.isDismiss) {
            this.isDismiss = true;
            this.ll_root.startAnimation(this.animationOut);
            dismiss();
            this.animationOut.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationEnd(Animation animation) {
                    boolean unused = PhotoPopupWindow.this.isDismiss = false;
                    if (Build.VERSION.SDK_INT <= 16) {
                        PhotoPopupWindow.this.dismiss4Pop();
                    } else {
                        PhotoPopupWindow.super.dismiss();
                    }
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }
            });
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.picture_tv_photo && this.onItemClickListener != null) {
            this.onItemClickListener.onItemClick(0);
            super.dismiss();
        }
        if (id == R.id.picture_tv_video && this.onItemClickListener != null) {
            this.onItemClickListener.onItemClick(1);
            super.dismiss();
        }
        dismiss();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener2) {
        this.onItemClickListener = onItemClickListener2;
    }

    public void showAsDropDown(View view) {
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                int[] iArr = new int[2];
                view.getLocationOnScreen(iArr);
                showAtLocation(view, 80, iArr[0], iArr[1] + view.getHeight());
            } else {
                showAtLocation(view, 80, 0, 0);
            }
            this.isDismiss = false;
            this.ll_root.startAnimation(this.animationIn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
