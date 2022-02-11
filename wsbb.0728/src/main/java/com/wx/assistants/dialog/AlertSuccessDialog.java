package com.wx.assistants.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AlertSuccessDialog {
    private Context context;
    private long delayTime = 2000;
    /* access modifiers changed from: private */
    public Dialog dialog;
    private Display display;
    private LinearLayout lLayout_bg;
    /* access modifiers changed from: private */
    public DelayedListener listener;
    private ImageView successImg;
    private TextView successText;

    public interface DelayedListener {
        void delayedEnd();
    }

    public enum TitleColor {
        Withe("#FFFFFF"),
        Blue("#037BFF"),
        Black("#000000"),
        Green("#FB4A4A"),
        Red("#E64C49"),
        GRAY("#878787");
        
        private String name;

        private TitleColor(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }
    }

    public AlertSuccessDialog(Context context2) {
        this.context = context2;
        this.display = ((WindowManager) context2.getSystemService("window")).getDefaultDisplay();
    }

    private void timerCloseDialog() {
        this.lLayout_bg.postDelayed(new Runnable() {
            public void run() {
                AlertSuccessDialog.this.dialog.dismiss();
                if (AlertSuccessDialog.this.listener != null) {
                    AlertSuccessDialog.this.listener.delayedEnd();
                }
            }
        }, this.delayTime);
    }

    public AlertSuccessDialog builder() {
        View inflate = LayoutInflater.from(this.context).inflate(2131427732, (ViewGroup) null);
        this.lLayout_bg = (LinearLayout) inflate.findViewById(2131296881);
        this.successImg = (ImageView) inflate.findViewById(2131297440);
        this.successText = (TextView) inflate.findViewById(2131297441);
        this.dialog = new Dialog(this.context, 2131755016);
        this.dialog.setContentView(inflate);
        this.lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        return this;
    }

    public boolean isShowing() {
        return this.dialog.isShowing();
    }

    public AlertSuccessDialog setCancelable(boolean z) {
        this.dialog.setCancelable(z);
        return this;
    }

    public AlertSuccessDialog setDelayTime(long j) {
        this.delayTime = j;
        return this;
    }

    public AlertSuccessDialog setDelayedListener(DelayedListener delayedListener) {
        this.listener = delayedListener;
        return this;
    }

    public AlertSuccessDialog setSuccessImg(@DrawableRes int i) {
        this.successImg.setBackgroundResource(i);
        return this;
    }

    public AlertSuccessDialog setSuccessText(String str, TitleColor titleColor) {
        if (!TextUtils.isEmpty(str)) {
            this.successText.setText(str);
        }
        if (titleColor != null) {
            this.successText.setTextColor(Color.parseColor(titleColor.getName()));
        }
        return this;
    }

    public void show() {
        try {
            this.dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        timerCloseDialog();
    }
}
