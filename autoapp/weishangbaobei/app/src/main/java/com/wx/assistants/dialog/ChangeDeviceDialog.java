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
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChangeDeviceDialog {
    public static boolean isShowing;
    public static ChangeDeviceDialog versionUpdateDialog;
    private TextView btn_neg;
    private TextView btn_pos;
    private Context context;
    /* access modifiers changed from: private */
    public Dialog dialog;
    private Display display;
    private LinearLayout lLayout_bg;
    private TextView open_member;
    private TextView txt_msg;
    private TextView txt_title;

    private ChangeDeviceDialog() {
    }

    public static ChangeDeviceDialog newInstance() {
        if (versionUpdateDialog == null) {
            versionUpdateDialog = new ChangeDeviceDialog();
        }
        return versionUpdateDialog;
    }

    public ChangeDeviceDialog init(Context context2) {
        this.context = context2;
        this.display = ((WindowManager) context2.getSystemService("window")).getDefaultDisplay();
        return this;
    }

    private ChangeDeviceDialog(Context context2) {
        this.context = context2;
        this.display = ((WindowManager) context2.getSystemService("window")).getDefaultDisplay();
    }

    public ChangeDeviceDialog builder() {
        View inflate = LayoutInflater.from(this.context).inflate(2131427494, (ViewGroup) null);
        this.lLayout_bg = (LinearLayout) inflate.findViewById(2131296881);
        this.txt_title = (TextView) inflate.findViewById(2131297603);
        this.txt_msg = (TextView) inflate.findViewById(2131297602);
        this.btn_neg = (TextView) inflate.findViewById(2131296444);
        this.open_member = (TextView) inflate.findViewById(2131297090);
        this.btn_pos = (TextView) inflate.findViewById(2131296445);
        this.dialog = new Dialog(this.context, 2131755016);
        this.dialog.setContentView(inflate);
        LinearLayout linearLayout = this.lLayout_bg;
        double width = (double) this.display.getWidth();
        Double.isNaN(width);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams((int) (width * 0.8d), -2));
        return this;
    }

    public ChangeDeviceDialog setTitle(String str) {
        if (!"".equals(str) && str != null) {
            this.txt_title.setText(str);
        }
        return this;
    }

    public ChangeDeviceDialog setMsg(CharSequence charSequence) {
        if (!"".equals(charSequence)) {
            this.txt_msg.setText(charSequence);
        }
        return this;
    }

    public ChangeDeviceDialog setCancelable(boolean z) {
        this.dialog.setCancelable(z);
        return this;
    }

    public ChangeDeviceDialog setPositiveButton(String str, TitleColor titleColor, final View.OnClickListener onClickListener) {
        if (!"".equals(str)) {
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
                ChangeDeviceDialog.isShowing = false;
                ChangeDeviceDialog.this.dialog.dismiss();
            }
        });
        return this;
    }

    public ChangeDeviceDialog setNegativeButton(String str, TitleColor titleColor, final View.OnClickListener onClickListener) {
        if (!"".equals(str)) {
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
                ChangeDeviceDialog.isShowing = false;
                ChangeDeviceDialog.this.dialog.dismiss();
            }
        });
        return this;
    }

    public ChangeDeviceDialog setVersionNegativeButton(String str, TitleColor titleColor, final View.OnClickListener onClickListener) {
        if (!"".equals(str)) {
            this.btn_neg.setText(str);
        }
        if (titleColor != null) {
            this.btn_neg.setTextColor(Color.parseColor(titleColor.getName()));
        }
        this.btn_neg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                    ChangeDeviceDialog.isShowing = false;
                    ChangeDeviceDialog.this.dialog.dismiss();
                }
            }
        });
        return this;
    }

    public ChangeDeviceDialog setOpenMemeberButton(String str, TitleColor titleColor, final View.OnClickListener onClickListener) {
        if (!"".equals(str)) {
            this.open_member.setText(str);
        }
        if (titleColor != null) {
            this.open_member.setTextColor(Color.parseColor(titleColor.getName()));
        }
        this.open_member.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                    ChangeDeviceDialog.isShowing = false;
                    ChangeDeviceDialog.this.dialog.dismiss();
                    return;
                }
                ChangeDeviceDialog.isShowing = false;
                ChangeDeviceDialog.this.dialog.dismiss();
            }
        });
        return this;
    }

    public ChangeDeviceDialog setVersionPositiveButton(String str, final int i, TitleColor titleColor, final View.OnClickListener onClickListener) {
        if (!"".equals(str)) {
            this.btn_pos.setText(str);
        }
        if (titleColor != null) {
            this.btn_pos.setTextColor(Color.parseColor(titleColor.getName()));
        }
        this.btn_pos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                    if (i == 1) {
                        ChangeDeviceDialog.isShowing = false;
                        ChangeDeviceDialog.this.dialog.dismiss();
                    }
                }
            }
        });
        return this;
    }

    private void setLayout() {
        this.txt_title.setVisibility(0);
        this.txt_msg.setVisibility(0);
        this.btn_pos.setVisibility(0);
        this.btn_neg.setVisibility(0);
        this.open_member.setVisibility(0);
    }

    public void show() {
        if (!this.dialog.isShowing()) {
            setLayout();
            try {
                isShowing = true;
                this.dialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public enum TitleColor {
        Blue("#037BFF"),
        Black("#000000"),
        Green("#FB4A4A"),
        Red("#ff5857"),
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
}
