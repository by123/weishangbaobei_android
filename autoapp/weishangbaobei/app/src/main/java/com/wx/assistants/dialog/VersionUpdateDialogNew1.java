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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wx.assistants.application.MyApplication;

public class VersionUpdateDialogNew1 {
    public static boolean isShowing;
    public static VersionUpdateDialogNew1 versionUpdateDialog;
    private TextView btn_neg;
    private TextView btn_pos;
    private Context context;
    /* access modifiers changed from: private */
    public Dialog dialog;
    private Display display;
    private LinearLayout lLayout_bg;
    private boolean showMsg = false;
    private boolean showNegBtn = false;
    private boolean showPosBtn = false;
    private boolean showTitle = false;
    private ImageView topImg;
    private TextView txt_msg;
    private TextView versionNum;

    private VersionUpdateDialogNew1() {
    }

    public static VersionUpdateDialogNew1 newInstance() {
        if (versionUpdateDialog == null) {
            versionUpdateDialog = new VersionUpdateDialogNew1();
        }
        return versionUpdateDialog;
    }

    public VersionUpdateDialogNew1 init(Context context2) {
        this.context = context2;
        this.display = ((WindowManager) context2.getSystemService("window")).getDefaultDisplay();
        return this;
    }

    private VersionUpdateDialogNew1(Context context2) {
        this.context = context2;
        this.display = ((WindowManager) context2.getSystemService("window")).getDefaultDisplay();
    }

    public VersionUpdateDialogNew1 builder() {
        View inflate = LayoutInflater.from(this.context).inflate(2131427723, (ViewGroup) null);
        this.lLayout_bg = (LinearLayout) inflate.findViewById(2131296881);
        this.versionNum = (TextView) inflate.findViewById(2131297631);
        this.txt_msg = (TextView) inflate.findViewById(2131297602);
        this.btn_neg = (TextView) inflate.findViewById(2131296444);
        this.btn_pos = (TextView) inflate.findViewById(2131296445);
        this.topImg = (ImageView) inflate.findViewById(2131297632);
        this.versionNum = (TextView) inflate.findViewById(2131297631);
        this.dialog = new Dialog(this.context, 2131755016);
        this.dialog.setContentView(inflate);
        LinearLayout linearLayout = this.lLayout_bg;
        double width = (double) this.display.getWidth();
        Double.isNaN(width);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams((int) (width * 0.8d), -2));
        Glide.with(MyApplication.getConText()).load(2131623980).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(this.topImg);
        return this;
    }

    public VersionUpdateDialogNew1 setTitle(String str) {
        this.showTitle = true;
        if ("".equals(str) || str == null) {
            this.showTitle = false;
        } else {
            this.versionNum.setText(str);
        }
        return this;
    }

    public VersionUpdateDialogNew1 setMsg(CharSequence charSequence) {
        this.showMsg = true;
        if ("".equals(charSequence)) {
            this.showMsg = false;
        } else {
            this.txt_msg.setText(charSequence);
        }
        return this;
    }

    public VersionUpdateDialogNew1 setCancelable(boolean z) {
        this.dialog.setCancelable(z);
        if (!z && this.btn_neg != null) {
            this.showNegBtn = false;
        }
        return this;
    }

    public VersionUpdateDialogNew1 setPositiveButton(String str, TitleColor titleColor, final View.OnClickListener onClickListener) {
        this.showPosBtn = true;
        if ("".equals(str)) {
            this.showPosBtn = false;
        } else {
            this.btn_pos.setText(str);
        }
        this.btn_pos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
                VersionUpdateDialogNew1.isShowing = false;
                VersionUpdateDialogNew1.this.dialog.dismiss();
            }
        });
        return this;
    }

    public VersionUpdateDialogNew1 setVersionNegativeButton(String str, TitleColor titleColor, final View.OnClickListener onClickListener) {
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
                    VersionUpdateDialogNew1.isShowing = false;
                    VersionUpdateDialogNew1.this.dialog.dismiss();
                }
            }
        });
        return this;
    }

    public VersionUpdateDialogNew1 setVersionPositiveButton(String str, final int i, TitleColor titleColor, final View.OnClickListener onClickListener) {
        this.showPosBtn = true;
        if ("".equals(str)) {
            this.showPosBtn = false;
        } else {
            this.btn_pos.setText(str);
        }
        this.btn_pos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                    if (i == 1) {
                        VersionUpdateDialogNew1.isShowing = false;
                        VersionUpdateDialogNew1.this.dialog.dismiss();
                    }
                }
            }
        });
        return this;
    }

    private void setLayout() {
        if (this.showTitle) {
            this.versionNum.setVisibility(8);
        }
        if (this.showMsg) {
            this.txt_msg.setVisibility(0);
        }
        if (this.showPosBtn && this.showNegBtn) {
            this.btn_pos.setVisibility(0);
            this.btn_neg.setVisibility(0);
            this.btn_neg.setBackgroundResource(2131230921);
        } else if (this.showNegBtn) {
            this.btn_neg.setVisibility(0);
            this.btn_pos.setVisibility(8);
        } else {
            this.btn_pos.setVisibility(0);
            this.btn_neg.setVisibility(8);
        }
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
