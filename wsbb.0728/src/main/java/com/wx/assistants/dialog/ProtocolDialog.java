package com.wx.assistants.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wx.assistants.view.AutoLinkStyleTextView;
import com.wx.assistants.webview.WebViewActivity;

public class ProtocolDialog implements CompoundButton.OnCheckedChangeListener {
    /* access modifiers changed from: private */
    public CheckBox agreeCheckBox;
    private LinearLayout agreeCheckBoxLayout;
    private AutoLinkStyleTextView autoLinkStyleTextView;
    private TextView btn_left;
    private TextView btn_pos;
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public Dialog dialog;
    private Display display;
    private LinearLayout lLayout_bg;
    private boolean showMsg = false;
    private boolean showTitle = false;
    private TextView txt_msg;
    private TextView txt_title;

    public ProtocolDialog(Context context2) {
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

    public ProtocolDialog builder() {
        View inflate = LayoutInflater.from(this.context).inflate(2131427685, (ViewGroup) null);
        this.lLayout_bg = (LinearLayout) inflate.findViewById(2131296881);
        this.txt_title = (TextView) inflate.findViewById(2131297603);
        this.txt_msg = (TextView) inflate.findViewById(2131297602);
        this.btn_pos = (TextView) inflate.findViewById(2131296445);
        this.btn_left = (TextView) inflate.findViewById(2131296443);
        this.agreeCheckBox = (CheckBox) inflate.findViewById(2131296326);
        this.agreeCheckBoxLayout = (LinearLayout) inflate.findViewById(2131296327);
        this.autoLinkStyleTextView = (AutoLinkStyleTextView) inflate.findViewById(2131296363);
        this.autoLinkStyleTextView.setOnClickCallBack(new AutoLinkStyleTextView.ClickCallBack() {
            public void onClick(int i) {
                if (i == 0) {
                    WebViewActivity.startActivity(ProtocolDialog.this.context, "微商宝贝用户服务协议", "file:///android_asset/use_need_know_1.html");
                } else if (i == 1) {
                    WebViewActivity.startActivity(ProtocolDialog.this.context, "微商宝贝隐私政策", "file:///android_asset/use_need_know.html");
                }
            }
        });
        this.dialog = new Dialog(this.context, 2131755016);
        this.dialog.setContentView(inflate);
        LinearLayout linearLayout = this.lLayout_bg;
        double width = (double) this.display.getWidth();
        Double.isNaN(width);
        int i = (int) (width * 0.95d);
        double height = (double) this.display.getHeight();
        Double.isNaN(height);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(i, (int) (height * 0.9d)));
        this.agreeCheckBox.setOnCheckedChangeListener(this);
        this.agreeCheckBoxLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ProtocolDialog.this.agreeCheckBox.isChecked()) {
                    ProtocolDialog.this.agreeCheckBox.setChecked(false);
                } else {
                    ProtocolDialog.this.agreeCheckBox.setChecked(true);
                }
            }
        });
        return this;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            this.btn_pos.setEnabled(true);
        } else {
            this.btn_pos.setEnabled(false);
        }
    }

    public ProtocolDialog setCancelable(boolean z) {
        this.dialog.setCancelable(z);
        return this;
    }

    public ProtocolDialog setNilAgree(final View.OnClickListener onClickListener) {
        this.btn_left.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onClickListener.onClick(view);
                ProtocolDialog.this.dialog.dismiss();
            }
        });
        return this;
    }

    public ProtocolDialog setPositiveButton(final View.OnClickListener onClickListener) {
        this.btn_pos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onClickListener.onClick(view);
                ProtocolDialog.this.dialog.dismiss();
            }
        });
        return this;
    }

    public ProtocolDialog setTitle(String str) {
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
