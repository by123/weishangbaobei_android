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

public class FloatWindowDialog {
    private LinearLayout bottomWhiteTopLayout;
    private TextView btn_pos;
    private Context context;
    /* access modifiers changed from: private */
    public Dialog dialog;
    private Display display;
    private LinearLayout lLayout_bg;
    private boolean showMsg = false;
    private boolean showTitle = false;
    private TextView txt_msg;
    private TextView txt_title;

    public enum TitleColor {
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

    public FloatWindowDialog(Context context2) {
        this.context = context2;
        this.display = ((WindowManager) context2.getSystemService("window")).getDefaultDisplay();
    }

    private void setLayout() {
        if (this.showTitle) {
            this.txt_title.setVisibility(0);
        }
        if (this.showMsg) {
            this.txt_msg.setVisibility(0);
        }
        this.btn_pos.setVisibility(0);
    }

    public FloatWindowDialog builder() {
        View inflate = LayoutInflater.from(this.context).inflate(2131427526, (ViewGroup) null);
        this.lLayout_bg = (LinearLayout) inflate.findViewById(2131296881);
        this.txt_title = (TextView) inflate.findViewById(2131297603);
        this.txt_title.setVisibility(8);
        this.txt_msg = (TextView) inflate.findViewById(2131297602);
        this.txt_msg.setVisibility(8);
        this.btn_pos = (TextView) inflate.findViewById(2131296445);
        this.btn_pos.setVisibility(8);
        this.dialog = new Dialog(this.context, 2131755018);
        this.dialog.setContentView(inflate);
        this.lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams(this.display.getWidth() * 1, -2));
        this.dialog.getWindow().setGravity(80);
        this.bottomWhiteTopLayout = (LinearLayout) inflate.findViewById(2131296408);
        this.bottomWhiteTopLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FloatWindowDialog.this.dialog.dismiss();
            }
        });
        return this;
    }

    public FloatWindowDialog setCancelable(boolean z) {
        this.dialog.setCancelable(z);
        return this;
    }

    public FloatWindowDialog setMsg(CharSequence charSequence) {
        this.showMsg = true;
        if ("".equals(charSequence)) {
            this.showMsg = false;
        } else {
            this.txt_msg.setText(charSequence);
        }
        return this;
    }

    public FloatWindowDialog setPositiveButton(String str, TitleColor titleColor, final View.OnClickListener onClickListener) {
        this.btn_pos.setText(str);
        this.btn_pos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
                FloatWindowDialog.this.dialog.dismiss();
            }
        });
        return this;
    }

    public FloatWindowDialog setTitle(String str) {
        this.showTitle = true;
        if ("".equals(str) || str == null) {
            this.showTitle = false;
        } else {
            this.txt_title.setText(str);
        }
        return this;
    }

    public void show() {
        setLayout();
        try {
            this.dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
