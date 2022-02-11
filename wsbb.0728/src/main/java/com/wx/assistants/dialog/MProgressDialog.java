package com.wx.assistants.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wx.assistants.dialog.config.MDialogConfig;
import com.wx.assistants.dialog.utils.MSizeUtils;
import com.wx.assistants.dialog.view.MNHudProgressWheel;

public class MProgressDialog {
    private static final String LoadingDefaultMsg = "加载中";
    private static RelativeLayout dialog_view_bg;
    private static RelativeLayout dialog_window_background;
    private static Dialog mDialog;
    /* access modifiers changed from: private */
    public static MDialogConfig mDialogConfig;
    private static MNHudProgressWheel progress_wheel;
    private static TextView tv_show;

    private static void configView(Context context) {
        if (mDialogConfig == null) {
            mDialogConfig = new MDialogConfig.Builder().build();
        }
        if (!(mDialogConfig.animationID == 0 || mDialog.getWindow() == null)) {
            try {
                mDialog.getWindow().setWindowAnimations(mDialogConfig.animationID);
            } catch (Exception e) {
            }
        }
        mDialog.setCanceledOnTouchOutside(mDialogConfig.canceledOnTouchOutside);
        mDialog.setCancelable(mDialogConfig.cancelable);
        dialog_window_background.setBackgroundColor(mDialogConfig.backgroundWindowColor);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(mDialogConfig.backgroundViewColor);
        gradientDrawable.setStroke(MSizeUtils.dp2px(context, mDialogConfig.strokeWidth), mDialogConfig.strokeColor);
        gradientDrawable.setCornerRadius((float) MSizeUtils.dp2px(context, mDialogConfig.cornerRadius));
        if (Build.VERSION.SDK_INT >= 16) {
            dialog_view_bg.setBackground(gradientDrawable);
        } else {
            dialog_view_bg.setBackgroundDrawable(gradientDrawable);
        }
        dialog_view_bg.setPadding(MSizeUtils.dp2px(context, (float) mDialogConfig.paddingLeft), MSizeUtils.dp2px(context, (float) mDialogConfig.paddingTop), MSizeUtils.dp2px(context, (float) mDialogConfig.paddingRight), MSizeUtils.dp2px(context, (float) mDialogConfig.paddingBottom));
        progress_wheel.setBarColor(mDialogConfig.progressColor);
        progress_wheel.setBarWidth(MSizeUtils.dp2px(context, mDialogConfig.progressWidth));
        progress_wheel.setRimColor(mDialogConfig.progressRimColor);
        progress_wheel.setRimWidth(mDialogConfig.progressRimWidth);
        tv_show.setTextColor(mDialogConfig.textColor);
        tv_show.setTextSize(mDialogConfig.textSize);
        dialog_window_background.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MProgressDialog.mDialogConfig != null && MProgressDialog.mDialogConfig.canceledOnTouchOutside) {
                    MProgressDialog.dismissProgress();
                }
            }
        });
    }

    public static void dismissProgress() {
        try {
            if (mDialog != null && mDialog.isShowing()) {
                if (mDialogConfig.onDialogDismissListener != null) {
                    mDialogConfig.onDialogDismissListener.onDismiss();
                    mDialogConfig.onDialogDismissListener = null;
                }
                mDialogConfig = null;
                dialog_window_background = null;
                dialog_view_bg = null;
                progress_wheel = null;
                tv_show = null;
                mDialog.dismiss();
                mDialog = null;
            }
        } catch (Exception e) {
        }
    }

    private static void initDialog(Context context) {
        View inflate = LayoutInflater.from(context).inflate(2131427588, (ViewGroup) null);
        tv_show = (TextView) inflate.findViewById(2131297592);
        mDialog = new Dialog(context, 2131755193);
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setContentView(inflate);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        WindowManager.LayoutParams attributes = mDialog.getWindow().getAttributes();
        attributes.width = i;
        attributes.height = i2;
        mDialog.getWindow().setAttributes(attributes);
        dialog_window_background = (RelativeLayout) inflate.findViewById(2131296596);
        dialog_view_bg = (RelativeLayout) inflate.findViewById(2131296594);
        progress_wheel = (MNHudProgressWheel) inflate.findViewById(2131297169);
        progress_wheel.spin();
        configView(context);
    }

    public static boolean isShowing() {
        if (mDialog != null) {
            return mDialog.isShowing();
        }
        return false;
    }

    public static void showProgress(Context context) {
        showProgress(context, LoadingDefaultMsg);
    }

    public static void showProgress(Context context, MDialogConfig mDialogConfig2) {
        showProgress(context, LoadingDefaultMsg, mDialogConfig2);
    }

    public static void showProgress(Context context, String str) {
        showProgress(context, str, (MDialogConfig) null);
    }

    public static void showProgress(Context context, String str, MDialogConfig mDialogConfig2) {
        try {
            dismissProgress();
            if (mDialogConfig2 == null) {
                mDialogConfig2 = new MDialogConfig.Builder().build();
            }
            mDialogConfig = mDialogConfig2;
            initDialog(context);
            if (mDialog != null && tv_show != null) {
                if (TextUtils.isEmpty(str)) {
                    tv_show.setVisibility(8);
                } else {
                    tv_show.setVisibility(0);
                    tv_show.setText(str);
                }
                mDialog.show();
            }
        } catch (Exception e) {
        }
    }
}
