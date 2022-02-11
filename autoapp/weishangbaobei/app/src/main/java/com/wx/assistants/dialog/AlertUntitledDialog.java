package com.wx.assistants.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AlertUntitledDialog {
    private TextView btn_neg;
    private TextView btn_pos;
    private Context context;
    /* access modifiers changed from: private */
    public Dialog dialog;
    private Display display;
    ErrorShowListener errorShowListener;
    private ImageView img_line;
    private LinearLayout lLayout_bg;
    private boolean showMsg = false;
    private boolean showNegBtn = false;
    private boolean showPosBtn = false;
    private TextView txt_msg;

    interface ErrorShowListener {
        void errorShowListener();
    }

    public AlertUntitledDialog(Context context2) {
        this.context = context2;
        this.display = ((WindowManager) context2.getSystemService("window")).getDefaultDisplay();
    }

    public AlertUntitledDialog builder() {
        View inflate = LayoutInflater.from(this.context).inflate(2131427730, (ViewGroup) null);
        this.lLayout_bg = (LinearLayout) inflate.findViewById(2131296881);
        this.txt_msg = (TextView) inflate.findViewById(2131297602);
        this.txt_msg.setVisibility(8);
        this.btn_neg = (TextView) inflate.findViewById(2131296444);
        this.btn_neg.setVisibility(8);
        this.btn_pos = (TextView) inflate.findViewById(2131296445);
        this.btn_pos.setVisibility(8);
        this.img_line = (ImageView) inflate.findViewById(2131296802);
        this.img_line.setVisibility(8);
        this.dialog = new Dialog(this.context, 2131755016);
        this.dialog.setContentView(inflate);
        LinearLayout linearLayout = this.lLayout_bg;
        double width = (double) this.display.getWidth();
        Double.isNaN(width);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams((int) (width * 0.68d), -2));
        return this;
    }

    public AlertUntitledDialog setMsg(CharSequence charSequence) {
        this.showMsg = true;
        if ("".equals(charSequence)) {
            this.showMsg = false;
        } else {
            this.txt_msg.setText(charSequence);
        }
        return this;
    }

    public AlertUntitledDialog setCancelable(boolean z) {
        this.dialog.setCancelable(z);
        return this;
    }

    public AlertUntitledDialog setPositiveButton(String str, TitleColor titleColor, final View.OnClickListener onClickListener) {
        this.showPosBtn = true;
        if ("".equals(str)) {
            this.showPosBtn = false;
        } else {
            this.btn_pos.setText(str);
        }
        if (titleColor != null) {
            this.btn_pos.setTextColor(Color.parseColor(titleColor.getName()));
        }
        this.btn_pos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
                AlertUntitledDialog.this.dialog.dismiss();
            }
        });
        return this;
    }

    public AlertUntitledDialog setNegativeButton(String str, TitleColor titleColor, final View.OnClickListener onClickListener) {
        this.showNegBtn = true;
        if ("".equals(str)) {
            this.showNegBtn = false;
        } else {
            this.btn_neg.setText(str);
        }
        if (titleColor != null) {
            this.btn_neg.setTextColor(Color.parseColor(titleColor.getName()));
        }
        this.btn_neg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
                AlertUntitledDialog.this.dialog.dismiss();
            }
        });
        return this;
    }

    private void setLayout() {
        if (this.showMsg) {
            this.txt_msg.setVisibility(0);
        }
        if (this.showPosBtn && this.showNegBtn) {
            this.btn_pos.setVisibility(0);
            this.btn_neg.setVisibility(0);
            this.img_line.setVisibility(0);
        } else if (this.showNegBtn) {
            this.btn_neg.setVisibility(0);
            this.btn_neg.setBackgroundResource(2131230820);
        } else {
            this.btn_pos.setVisibility(0);
            this.btn_pos.setBackgroundResource(2131230820);
        }
    }

    public void show() {
        setLayout();
        try {
            this.dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
            if (this.errorShowListener != null) {
                this.errorShowListener.errorShowListener();
            }
        }
    }

    public boolean isShowing() {
        return this.dialog.isShowing();
    }

    public enum TitleColor {
        Blue("#037BFF"),
        Black("#000000"),
        Green("#FB4A4A"),
        Red("#E64C49"),
        GRAY("#666666");
        
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

    public void setErrorShowListener(ErrorShowListener errorShowListener2) {
        this.errorShowListener = errorShowListener2;
    }
}
