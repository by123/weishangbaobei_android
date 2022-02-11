package com.wx.assistants.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wx.assistants.dialog.config.MDialogConfig;
import com.wx.assistants.dialog.utils.MSizeUtils;

public class MStatusDialog {
    private RelativeLayout dialog_view_bg;
    private RelativeLayout dialog_window_background;
    private ImageView imageStatus;
    private Context mContext;
    private Dialog mDialog;
    private MDialogConfig mDialogConfig;
    private Handler mHandler;
    private TextView tvShow;

    public MStatusDialog(Context context) {
        this(context, new MDialogConfig.Builder().build());
    }

    public MStatusDialog(Context context, MDialogConfig mDialogConfig2) {
        this.mContext = context;
        this.mDialogConfig = mDialogConfig2;
        this.mHandler = new Handler(Looper.getMainLooper());
        initDialog();
    }

    private void initDialog() {
        View inflate = LayoutInflater.from(this.mContext).inflate(2131427589, (ViewGroup) null);
        this.mDialog = new Dialog(this.mContext, 2131755193);
        this.mDialog.setCancelable(false);
        this.mDialog.setCanceledOnTouchOutside(false);
        this.mDialog.setContentView(inflate);
        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        WindowManager.LayoutParams attributes = this.mDialog.getWindow().getAttributes();
        attributes.width = i;
        attributes.height = i2;
        this.mDialog.getWindow().setAttributes(attributes);
        this.dialog_window_background = (RelativeLayout) inflate.findViewById(2131296596);
        this.dialog_view_bg = (RelativeLayout) inflate.findViewById(2131296594);
        this.imageStatus = (ImageView) inflate.findViewById(2131296765);
        this.tvShow = (TextView) inflate.findViewById(2131297542);
        configView();
    }

    private void configView() {
        if (this.mDialogConfig == null) {
            this.mDialogConfig = new MDialogConfig.Builder().build();
        }
        this.dialog_window_background.setBackgroundColor(this.mDialogConfig.backgroundWindowColor);
        this.tvShow.setTextColor(this.mDialogConfig.textColor);
        this.tvShow.setTextSize(this.mDialogConfig.textSize);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(this.mDialogConfig.backgroundViewColor);
        gradientDrawable.setStroke(MSizeUtils.dp2px(this.mContext, this.mDialogConfig.strokeWidth), this.mDialogConfig.strokeColor);
        gradientDrawable.setCornerRadius((float) MSizeUtils.dp2px(this.mContext, this.mDialogConfig.cornerRadius));
        if (Build.VERSION.SDK_INT >= 16) {
            this.dialog_view_bg.setBackground(gradientDrawable);
        } else {
            this.dialog_view_bg.setBackgroundDrawable(gradientDrawable);
        }
        this.dialog_view_bg.setPadding(MSizeUtils.dp2px(this.mContext, (float) this.mDialogConfig.paddingLeft), MSizeUtils.dp2px(this.mContext, (float) this.mDialogConfig.paddingTop), MSizeUtils.dp2px(this.mContext, (float) this.mDialogConfig.paddingRight), MSizeUtils.dp2px(this.mContext, (float) this.mDialogConfig.paddingBottom));
        if (!(this.mDialogConfig.animationID == 0 || this.mDialog.getWindow() == null)) {
            try {
                this.mDialog.getWindow().setWindowAnimations(this.mDialogConfig.animationID);
            } catch (Exception unused) {
            }
        }
        if (this.mDialogConfig.imgWidth > 0 && this.mDialogConfig.imgHeight > 0) {
            ViewGroup.LayoutParams layoutParams = this.imageStatus.getLayoutParams();
            layoutParams.width = MSizeUtils.dp2px(this.mContext, (float) this.mDialogConfig.imgWidth);
            layoutParams.height = MSizeUtils.dp2px(this.mContext, (float) this.mDialogConfig.imgHeight);
            this.imageStatus.setLayoutParams(layoutParams);
        }
    }

    public void show(String str, Drawable drawable) {
        show(str, drawable, 2000);
    }

    public void show(String str, Drawable drawable, long j) {
        this.imageStatus.setImageDrawable(drawable);
        this.tvShow.setText(str);
        this.mDialog.show();
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                MStatusDialog.this.dismiss();
            }
        }, j);
    }

    public void dismiss() {
        try {
            this.mContext = null;
            if (this.mHandler != null) {
                this.mHandler.removeCallbacksAndMessages((Object) null);
                this.mHandler = null;
            }
            if (this.mDialog != null) {
                this.mDialog.dismiss();
                this.mDialog = null;
            }
            if (this.mDialogConfig != null && this.mDialogConfig.onDialogDismissListener != null) {
                this.mDialogConfig.onDialogDismissListener.onDismiss();
            }
        } catch (Exception unused) {
        }
    }
}
