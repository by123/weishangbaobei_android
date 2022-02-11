package com.wx.assistants.utils.manager;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

public class DialogManager {
    private RelativeLayout imgBg;
    private RelativeLayout imgBg2;
    private Context mContext;
    private Dialog mDialog;
    private TextView tipsTxt;
    private TextView tipsTxt2;

    public DialogManager(Context context) {
        this.mContext = context;
    }

    public void showRecordingDialog() {
        this.mDialog = new Dialog(this.mContext, 2131755441);
        View inflate = LayoutInflater.from(this.mContext).inflate(2131427515, (ViewGroup) null);
        this.mDialog.setContentView(inflate);
        this.imgBg = (RelativeLayout) inflate.findViewById(2131296604);
        this.tipsTxt = (TextView) inflate.findViewById(2131296606);
        this.imgBg2 = (RelativeLayout) inflate.findViewById(2131296605);
        this.tipsTxt2 = (TextView) inflate.findViewById(2131296607);
        this.mDialog.setCanceledOnTouchOutside(false);
        this.mDialog.show();
    }

    public void recording() {
        if (this.mDialog != null && this.mDialog.isShowing()) {
            this.imgBg.setVisibility(0);
            this.tipsTxt.setVisibility(0);
            this.imgBg2.setVisibility(8);
            this.tipsTxt2.setVisibility(8);
            this.imgBg.setBackgroundDrawable(this.mContext.getResources().getDrawable(2131231474));
            this.tipsTxt.setText(2131689868);
        }
    }

    public void wantToCancel() {
        if (this.mDialog != null && this.mDialog.isShowing()) {
            this.imgBg.setVisibility(8);
            this.tipsTxt.setVisibility(8);
            this.imgBg2.setVisibility(0);
            this.tipsTxt2.setVisibility(0);
            this.imgBg2.setBackgroundDrawable(this.mContext.getResources().getDrawable(2131231472));
            this.tipsTxt2.setText(2131689878);
            this.tipsTxt2.setBackgroundColor(this.mContext.getResources().getColor(2131099734));
        }
    }

    public void tooShort() {
        if (this.mDialog != null && this.mDialog.isShowing()) {
            this.imgBg2.setVisibility(0);
            this.tipsTxt2.setVisibility(0);
            this.imgBg.setVisibility(8);
            this.tipsTxt.setVisibility(8);
            this.imgBg2.setBackgroundDrawable(this.mContext.getResources().getDrawable(2131231473));
            this.tipsTxt2.setText(2131689840);
            new Timer().schedule(new TimerTask() {
                public void run() {
                    DialogManager.this.dimissDialog();
                }
            }, 1000);
        }
    }

    public void dimissDialog() {
        if (this.mDialog != null && this.mDialog.isShowing()) {
            this.mDialog.dismiss();
            this.mDialog = null;
        }
    }

    public void updateVoiceLevel(int i) {
        if (i <= 0 || i >= 6) {
            i = 5;
        }
        if (this.mDialog != null && this.mDialog.isShowing()) {
            Resources resources = this.mContext.getResources();
            this.imgBg.setBackgroundResource(resources.getIdentifier("yuyin_voice_" + i, "drawable", this.mContext.getPackageName()));
        }
    }

    public TextView getTipsTxt() {
        return this.tipsTxt;
    }

    public void setTipsTxt(TextView textView) {
        this.tipsTxt = textView;
    }
}
