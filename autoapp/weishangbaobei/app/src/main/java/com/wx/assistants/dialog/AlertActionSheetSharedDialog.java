package com.wx.assistants.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class AlertActionSheetSharedDialog implements View.OnClickListener {
    private Context context;
    private Dialog dialog;
    private Display display;
    private OnSheetItemClickListener listener;
    private LinearLayout sharedQQLayout;
    private LinearLayout sharedQzoneLayout;
    private LinearLayout sharedWechatCircleLayout;
    private LinearLayout sharedWechatLayout;

    public interface OnSheetItemClickListener {
        void onClick(int i);
    }

    public AlertActionSheetSharedDialog(Context context2, OnSheetItemClickListener onSheetItemClickListener) {
        this.context = context2;
        this.display = ((WindowManager) context2.getSystemService("window")).getDefaultDisplay();
        this.listener = onSheetItemClickListener;
    }

    public AlertActionSheetSharedDialog builder() {
        View inflate = LayoutInflater.from(this.context).inflate(2131427775, (ViewGroup) null);
        inflate.setMinimumWidth(this.display.getWidth());
        this.sharedWechatLayout = (LinearLayout) inflate.findViewById(2131297368);
        this.sharedWechatCircleLayout = (LinearLayout) inflate.findViewById(2131297367);
        this.sharedQQLayout = (LinearLayout) inflate.findViewById(2131297365);
        this.sharedQzoneLayout = (LinearLayout) inflate.findViewById(2131297366);
        this.sharedWechatLayout.setOnClickListener(this);
        this.sharedWechatCircleLayout.setOnClickListener(this);
        this.sharedQQLayout.setOnClickListener(this);
        this.sharedQzoneLayout.setOnClickListener(this);
        this.dialog = new Dialog(this.context, 2131755012);
        this.dialog.setContentView(inflate);
        this.dialog.setCanceledOnTouchOutside(true);
        setGravity(83);
        return this;
    }

    public AlertActionSheetSharedDialog setGravity(int i) {
        Window window = this.dialog.getWindow();
        window.setGravity(i);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.x = 0;
        attributes.y = 0;
        window.setAttributes(attributes);
        return this;
    }

    public AlertActionSheetSharedDialog setAnimation(int i) {
        this.dialog.getWindow().setWindowAnimations(i);
        return this;
    }

    public AlertActionSheetSharedDialog setCancelable(boolean z) {
        this.dialog.setCancelable(z);
        return this;
    }

    public AlertActionSheetSharedDialog setCanceledOnTouchOutside(boolean z) {
        this.dialog.setCanceledOnTouchOutside(z);
        return this;
    }

    public AlertActionSheetSharedDialog show() {
        this.dialog.show();
        return this;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case 2131297365:
                if (this.listener != null) {
                    this.listener.onClick(2);
                }
                this.dialog.dismiss();
                return;
            case 2131297366:
                if (this.listener != null) {
                    this.listener.onClick(3);
                }
                this.dialog.dismiss();
                return;
            case 2131297367:
                if (this.listener != null) {
                    this.listener.onClick(1);
                }
                this.dialog.dismiss();
                return;
            case 2131297368:
                if (this.listener != null) {
                    this.listener.onClick(0);
                }
                this.dialog.dismiss();
                return;
            default:
                return;
        }
    }

    public Dialog getDialog() {
        return this.dialog;
    }
}
