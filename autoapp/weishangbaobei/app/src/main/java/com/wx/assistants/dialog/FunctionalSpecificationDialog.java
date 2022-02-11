package com.wx.assistants.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FunctionalSpecificationDialog {
    private TextView btn_pos;
    private Context context;
    /* access modifiers changed from: private */
    public Dialog dialog;
    private Display display;
    private LinearLayout lLayout_bg;
    private TextView txt_msg;
    private TextView txt_title;

    public FunctionalSpecificationDialog(Context context2) {
        this.context = context2;
        this.display = ((WindowManager) context2.getSystemService("window")).getDefaultDisplay();
    }

    public FunctionalSpecificationDialog builder() {
        View inflate = LayoutInflater.from(this.context).inflate(2131427513, (ViewGroup) null);
        this.lLayout_bg = (LinearLayout) inflate.findViewById(2131296881);
        this.txt_title = (TextView) inflate.findViewById(2131297603);
        this.txt_msg = (TextView) inflate.findViewById(2131297602);
        this.btn_pos = (TextView) inflate.findViewById(2131296445);
        this.btn_pos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FunctionalSpecificationDialog.this.dialog.dismiss();
            }
        });
        this.dialog = new Dialog(this.context, 2131755016);
        this.dialog.setContentView(inflate);
        LinearLayout linearLayout = this.lLayout_bg;
        double width = (double) this.display.getWidth();
        Double.isNaN(width);
        double height = (double) this.display.getHeight();
        Double.isNaN(height);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams((int) (width * 0.9d), (int) (height * 0.7d)));
        return this;
    }

    public FunctionalSpecificationDialog setTitle(String str) {
        this.txt_title.setText(str);
        return this;
    }

    public FunctionalSpecificationDialog setMsg(String str) {
        this.txt_msg.setText(str);
        return this;
    }

    public FunctionalSpecificationDialog setCancelable(boolean z) {
        this.dialog.setCancelable(z);
        return this;
    }

    public void show() {
        try {
            this.dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
