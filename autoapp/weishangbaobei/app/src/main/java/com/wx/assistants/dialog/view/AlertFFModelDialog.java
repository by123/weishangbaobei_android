package com.wx.assistants.dialog.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wx.assistants.utils.ToastUtils;

public class AlertFFModelDialog {
    /* access modifiers changed from: private */
    public Button btn_pos;
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public Dialog dialog;
    private Display display;
    /* access modifiers changed from: private */
    public EditText editText;
    /* access modifiers changed from: private */
    public EditText editText2;
    private LinearLayout lLayout_bg;
    public OnEditTextListener listener;
    /* access modifiers changed from: private */
    public int maxNum = 7200;
    /* access modifiers changed from: private */
    public int minNum = 0;
    private TextView txt_title;

    public interface OnEditTextListener {
        void result(int i, int i2);
    }

    public void setOnEditTextListener(OnEditTextListener onEditTextListener) {
        this.listener = onEditTextListener;
    }

    public AlertFFModelDialog(Context context2) {
        this.context = context2;
        this.display = ((WindowManager) context2.getSystemService("window")).getDefaultDisplay();
    }

    public AlertFFModelDialog builder() {
        View inflate = LayoutInflater.from(this.context).inflate(2131427750, (ViewGroup) null);
        this.lLayout_bg = (LinearLayout) inflate.findViewById(2131296881);
        this.txt_title = (TextView) inflate.findViewById(2131297603);
        this.txt_title.setVisibility(0);
        this.editText = (EditText) inflate.findViewById(2131296625);
        this.editText2 = (EditText) inflate.findViewById(2131296626);
        this.btn_pos = (Button) inflate.findViewById(2131296445);
        this.btn_pos.setVisibility(0);
        this.dialog = new Dialog(this.context, 2131755016);
        this.dialog.setContentView(inflate);
        LinearLayout linearLayout = this.lLayout_bg;
        double width = (double) this.display.getWidth();
        Double.isNaN(width);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams((int) (width * 0.68d), -2));
        return this;
    }

    public AlertFFModelDialog setEditText(int i, int i2) {
        if (i >= this.minNum) {
            if (this.editText != null) {
                EditText editText3 = this.editText;
                editText3.setText(i + "");
                this.editText.setSelection(this.editText.getText().length());
            }
            if (this.editText2 != null) {
                EditText editText4 = this.editText2;
                editText4.setText(i2 + "");
                this.editText2.setSelection(this.editText2.getText().length());
            }
        } else {
            if (this.editText != null) {
                EditText editText5 = this.editText;
                editText5.setText(this.minNum + "");
                this.editText.setSelection(this.editText.getText().length());
            }
            Context context2 = this.context;
            ToastUtils.showToast(context2, "最小是" + this.minNum);
        }
        return this;
    }

    public AlertFFModelDialog setTitle(String str) {
        if (str != null) {
            this.txt_title.setText(str);
        }
        return this;
    }

    public AlertFFModelDialog setMaxNum(int i) {
        if (i != 0) {
            this.maxNum = i;
        }
        return this;
    }

    public AlertFFModelDialog setMinNum(int i) {
        if (i > -1) {
            this.minNum = i;
        }
        return this;
    }

    public AlertFFModelDialog setCancelable(boolean z) {
        this.dialog.setCancelable(z);
        return this;
    }

    public AlertFFModelDialog setPositiveButton(final OnEditTextListener onEditTextListener) {
        this.btn_pos.setOnClickListener(new View.OnClickListener() {
            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x008c */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onClick(android.view.View r3) {
                /*
                    r2 = this;
                    com.wx.assistants.dialog.view.AlertFFModelDialog r3 = com.wx.assistants.dialog.view.AlertFFModelDialog.this     // Catch:{ Exception -> 0x00e0 }
                    android.widget.EditText r3 = r3.editText     // Catch:{ Exception -> 0x00e0 }
                    android.text.Editable r3 = r3.getText()     // Catch:{ Exception -> 0x00e0 }
                    java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x00e0 }
                    int r3 = java.lang.Integer.parseInt(r3)     // Catch:{ Exception -> 0x00e0 }
                    com.wx.assistants.dialog.view.AlertFFModelDialog r0 = com.wx.assistants.dialog.view.AlertFFModelDialog.this     // Catch:{ Exception -> 0x00e0 }
                    android.widget.EditText r0 = r0.editText2     // Catch:{ Exception -> 0x00e0 }
                    android.text.Editable r0 = r0.getText()     // Catch:{ Exception -> 0x00e0 }
                    java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x00e0 }
                    int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ Exception -> 0x00e0 }
                    com.wx.assistants.dialog.view.AlertFFModelDialog$OnEditTextListener r1 = r3     // Catch:{ Exception -> 0x00e0 }
                    if (r1 == 0) goto L_0x0100
                    com.wx.assistants.dialog.view.AlertFFModelDialog r1 = com.wx.assistants.dialog.view.AlertFFModelDialog.this     // Catch:{ Exception -> 0x00e0 }
                    int r1 = r1.maxNum     // Catch:{ Exception -> 0x00e0 }
                    if (r3 > r1) goto L_0x0096
                    com.wx.assistants.dialog.view.AlertFFModelDialog r1 = com.wx.assistants.dialog.view.AlertFFModelDialog.this     // Catch:{ Exception -> 0x00e0 }
                    int r1 = r1.maxNum     // Catch:{ Exception -> 0x00e0 }
                    if (r0 <= r1) goto L_0x0039
                    goto L_0x0096
                L_0x0039:
                    com.wx.assistants.dialog.view.AlertFFModelDialog r1 = com.wx.assistants.dialog.view.AlertFFModelDialog.this     // Catch:{ Exception -> 0x00e0 }
                    int r1 = r1.minNum     // Catch:{ Exception -> 0x00e0 }
                    if (r3 >= r1) goto L_0x0063
                    com.wx.assistants.dialog.view.AlertFFModelDialog r3 = com.wx.assistants.dialog.view.AlertFFModelDialog.this     // Catch:{ Exception -> 0x00e0 }
                    android.content.Context r3 = r3.context     // Catch:{ Exception -> 0x00e0 }
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00e0 }
                    r0.<init>()     // Catch:{ Exception -> 0x00e0 }
                    java.lang.String r1 = "左边可最小设置"
                    r0.append(r1)     // Catch:{ Exception -> 0x00e0 }
                    com.wx.assistants.dialog.view.AlertFFModelDialog r1 = com.wx.assistants.dialog.view.AlertFFModelDialog.this     // Catch:{ Exception -> 0x00e0 }
                    int r1 = r1.minNum     // Catch:{ Exception -> 0x00e0 }
                    r0.append(r1)     // Catch:{ Exception -> 0x00e0 }
                    java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x00e0 }
                    com.wx.assistants.utils.ToastUtils.showToast(r3, r0)     // Catch:{ Exception -> 0x00e0 }
                    goto L_0x0100
                L_0x0063:
                    if (r3 <= r0) goto L_0x0072
                    com.wx.assistants.dialog.view.AlertFFModelDialog r3 = com.wx.assistants.dialog.view.AlertFFModelDialog.this     // Catch:{ Exception -> 0x00e0 }
                    android.content.Context r3 = r3.context     // Catch:{ Exception -> 0x00e0 }
                    java.lang.String r0 = "左边值 不能大于 右边值"
                    com.wx.assistants.utils.ToastUtils.showToast(r3, r0)     // Catch:{ Exception -> 0x00e0 }
                    goto L_0x0100
                L_0x0072:
                    com.wx.assistants.dialog.view.AlertFFModelDialog r1 = com.wx.assistants.dialog.view.AlertFFModelDialog.this     // Catch:{ Exception -> 0x00e0 }
                    int r1 = r1.minNum     // Catch:{ Exception -> 0x00e0 }
                    if (r3 < r1) goto L_0x0100
                    com.wx.assistants.dialog.view.AlertFFModelDialog$OnEditTextListener r1 = r3     // Catch:{ Exception -> 0x00e0 }
                    r1.result(r3, r0)     // Catch:{ Exception -> 0x00e0 }
                    android.content.Context r3 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x008c }
                    com.wx.assistants.dialog.view.AlertFFModelDialog r0 = com.wx.assistants.dialog.view.AlertFFModelDialog.this     // Catch:{ Exception -> 0x008c }
                    android.widget.Button r0 = r0.btn_pos     // Catch:{ Exception -> 0x008c }
                    com.wx.assistants.utils.KeyBroadUtils.hide_keyboard_from(r3, r0)     // Catch:{ Exception -> 0x008c }
                L_0x008c:
                    com.wx.assistants.dialog.view.AlertFFModelDialog r3 = com.wx.assistants.dialog.view.AlertFFModelDialog.this     // Catch:{ Exception -> 0x00e0 }
                    android.app.Dialog r3 = r3.dialog     // Catch:{ Exception -> 0x00e0 }
                    r3.dismiss()     // Catch:{ Exception -> 0x00e0 }
                    goto L_0x0100
                L_0x0096:
                    com.wx.assistants.dialog.view.AlertFFModelDialog r0 = com.wx.assistants.dialog.view.AlertFFModelDialog.this     // Catch:{ Exception -> 0x00e0 }
                    int r0 = r0.maxNum     // Catch:{ Exception -> 0x00e0 }
                    if (r3 <= r0) goto L_0x00bf
                    com.wx.assistants.dialog.view.AlertFFModelDialog r3 = com.wx.assistants.dialog.view.AlertFFModelDialog.this     // Catch:{ Exception -> 0x00e0 }
                    android.content.Context r3 = r3.context     // Catch:{ Exception -> 0x00e0 }
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00e0 }
                    r0.<init>()     // Catch:{ Exception -> 0x00e0 }
                    java.lang.String r1 = "左边可最大设置"
                    r0.append(r1)     // Catch:{ Exception -> 0x00e0 }
                    com.wx.assistants.dialog.view.AlertFFModelDialog r1 = com.wx.assistants.dialog.view.AlertFFModelDialog.this     // Catch:{ Exception -> 0x00e0 }
                    int r1 = r1.maxNum     // Catch:{ Exception -> 0x00e0 }
                    r0.append(r1)     // Catch:{ Exception -> 0x00e0 }
                    java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x00e0 }
                    com.wx.assistants.utils.ToastUtils.showToast(r3, r0)     // Catch:{ Exception -> 0x00e0 }
                    goto L_0x0100
                L_0x00bf:
                    com.wx.assistants.dialog.view.AlertFFModelDialog r3 = com.wx.assistants.dialog.view.AlertFFModelDialog.this     // Catch:{ Exception -> 0x00e0 }
                    android.content.Context r3 = r3.context     // Catch:{ Exception -> 0x00e0 }
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00e0 }
                    r0.<init>()     // Catch:{ Exception -> 0x00e0 }
                    java.lang.String r1 = "右边可最大设置"
                    r0.append(r1)     // Catch:{ Exception -> 0x00e0 }
                    com.wx.assistants.dialog.view.AlertFFModelDialog r1 = com.wx.assistants.dialog.view.AlertFFModelDialog.this     // Catch:{ Exception -> 0x00e0 }
                    int r1 = r1.maxNum     // Catch:{ Exception -> 0x00e0 }
                    r0.append(r1)     // Catch:{ Exception -> 0x00e0 }
                    java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x00e0 }
                    com.wx.assistants.utils.ToastUtils.showToast(r3, r0)     // Catch:{ Exception -> 0x00e0 }
                    goto L_0x0100
                L_0x00e0:
                    com.wx.assistants.dialog.view.AlertFFModelDialog r3 = com.wx.assistants.dialog.view.AlertFFModelDialog.this
                    android.content.Context r3 = r3.context
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    r0.<init>()
                    java.lang.String r1 = "可最小设置"
                    r0.append(r1)
                    com.wx.assistants.dialog.view.AlertFFModelDialog r1 = com.wx.assistants.dialog.view.AlertFFModelDialog.this
                    int r1 = r1.minNum
                    r0.append(r1)
                    java.lang.String r0 = r0.toString()
                    com.wx.assistants.utils.ToastUtils.showToast(r3, r0)
                L_0x0100:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.dialog.view.AlertFFModelDialog.AnonymousClass1.onClick(android.view.View):void");
            }
        });
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
