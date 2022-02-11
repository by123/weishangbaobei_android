package com.wx.assistants.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wx.assistants.utils.ToastUtils;

public class AlertEditDialog {
    /* access modifiers changed from: private */
    public Button btn_pos;
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public Dialog dialog;
    private Display display;
    /* access modifiers changed from: private */
    public EditText editText;
    private LinearLayout lLayout_bg;
    public OnEditTextListener listener;
    /* access modifiers changed from: private */
    public int maxNum = 5000;
    /* access modifiers changed from: private */
    public int minNum = 1;
    private TextView txt_title;

    public interface OnEditTextListener {
        void result(int i);
    }

    public void setOnEditTextListener(OnEditTextListener onEditTextListener) {
        this.listener = onEditTextListener;
    }

    public AlertEditDialog(Context context2) {
        this.context = context2;
        this.display = ((WindowManager) context2.getSystemService("window")).getDefaultDisplay();
    }

    public AlertEditDialog builder() {
        View inflate = LayoutInflater.from(this.context).inflate(2131427729, (ViewGroup) null);
        this.lLayout_bg = (LinearLayout) inflate.findViewById(2131296881);
        this.txt_title = (TextView) inflate.findViewById(2131297603);
        this.txt_title.setVisibility(0);
        this.editText = (EditText) inflate.findViewById(2131296625);
        this.btn_pos = (Button) inflate.findViewById(2131296445);
        this.btn_pos.setVisibility(0);
        this.dialog = new Dialog(this.context, 2131755016);
        this.dialog.setContentView(inflate);
        LinearLayout linearLayout = this.lLayout_bg;
        double width = (double) this.display.getWidth();
        Double.isNaN(width);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams((int) (width * 0.8d), -2));
        this.dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                if (AlertEditDialog.this.editText != null) {
                    AlertEditDialog.this.editText.postDelayed(new Runnable() {
                        public void run() {
                            ((InputMethodManager) AlertEditDialog.this.context.getSystemService("input_method")).showSoftInput(AlertEditDialog.this.editText, 1);
                        }
                    }, 100);
                }
            }
        });
        return this;
    }

    public AlertEditDialog setTitle(String str) {
        if (str != null) {
            this.txt_title.setText(str);
        }
        return this;
    }

    public AlertEditDialog setMaxNum(int i) {
        if (i != 0) {
            this.maxNum = i;
        }
        return this;
    }

    public AlertEditDialog setMinNum(int i) {
        if (i > -1) {
            this.minNum = i;
        }
        return this;
    }

    public AlertEditDialog setEditText(int i) {
        if (i < this.minNum) {
            if (this.editText != null) {
                EditText editText2 = this.editText;
                editText2.setText(this.minNum + "");
                this.editText.setSelection(this.editText.getText().length());
            }
            Context context2 = this.context;
            ToastUtils.showToast(context2, "最小是" + this.minNum);
        } else if (this.editText != null) {
            EditText editText3 = this.editText;
            editText3.setText(i + "");
            this.editText.setSelection(this.editText.getText().length());
        }
        return this;
    }

    public AlertEditDialog setCancelable(boolean z) {
        this.dialog.setCancelable(z);
        return this;
    }

    public AlertEditDialog setPositiveButton(final OnEditTextListener onEditTextListener) {
        this.btn_pos.setOnClickListener(new View.OnClickListener() {
            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0098 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onClick(android.view.View r3) {
                /*
                    r2 = this;
                    com.wx.assistants.dialog.AlertEditDialog r3 = com.wx.assistants.dialog.AlertEditDialog.this     // Catch:{ Exception -> 0x00a2 }
                    android.widget.EditText r3 = r3.editText     // Catch:{ Exception -> 0x00a2 }
                    android.text.Editable r3 = r3.getText()     // Catch:{ Exception -> 0x00a2 }
                    java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x00a2 }
                    int r3 = java.lang.Integer.parseInt(r3)     // Catch:{ Exception -> 0x00a2 }
                    com.wx.assistants.dialog.AlertEditDialog$OnEditTextListener r0 = r3     // Catch:{ Exception -> 0x00a2 }
                    if (r0 == 0) goto L_0x00c2
                    com.wx.assistants.dialog.AlertEditDialog r0 = com.wx.assistants.dialog.AlertEditDialog.this     // Catch:{ Exception -> 0x00a2 }
                    int r0 = r0.minNum     // Catch:{ Exception -> 0x00a2 }
                    if (r3 < r0) goto L_0x0052
                    com.wx.assistants.dialog.AlertEditDialog r0 = com.wx.assistants.dialog.AlertEditDialog.this     // Catch:{ Exception -> 0x00a2 }
                    int r0 = r0.maxNum     // Catch:{ Exception -> 0x00a2 }
                    if (r3 <= r0) goto L_0x0052
                    com.wx.assistants.dialog.AlertEditDialog$OnEditTextListener r3 = r3     // Catch:{ Exception -> 0x00a2 }
                    com.wx.assistants.dialog.AlertEditDialog r0 = com.wx.assistants.dialog.AlertEditDialog.this     // Catch:{ Exception -> 0x00a2 }
                    int r0 = r0.maxNum     // Catch:{ Exception -> 0x00a2 }
                    r3.result(r0)     // Catch:{ Exception -> 0x00a2 }
                    com.wx.assistants.dialog.AlertEditDialog r3 = com.wx.assistants.dialog.AlertEditDialog.this     // Catch:{ Exception -> 0x00a2 }
                    android.content.Context r3 = r3.context     // Catch:{ Exception -> 0x00a2 }
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a2 }
                    r0.<init>()     // Catch:{ Exception -> 0x00a2 }
                    java.lang.String r1 = "可最大设置"
                    r0.append(r1)     // Catch:{ Exception -> 0x00a2 }
                    com.wx.assistants.dialog.AlertEditDialog r1 = com.wx.assistants.dialog.AlertEditDialog.this     // Catch:{ Exception -> 0x00a2 }
                    int r1 = r1.maxNum     // Catch:{ Exception -> 0x00a2 }
                    r0.append(r1)     // Catch:{ Exception -> 0x00a2 }
                    java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x00a2 }
                    com.wx.assistants.utils.ToastUtils.showToast(r3, r0)     // Catch:{ Exception -> 0x00a2 }
                    goto L_0x008b
                L_0x0052:
                    com.wx.assistants.dialog.AlertEditDialog r0 = com.wx.assistants.dialog.AlertEditDialog.this     // Catch:{ Exception -> 0x00a2 }
                    int r0 = r0.minNum     // Catch:{ Exception -> 0x00a2 }
                    if (r3 < r0) goto L_0x0060
                    com.wx.assistants.dialog.AlertEditDialog$OnEditTextListener r0 = r3     // Catch:{ Exception -> 0x00a2 }
                    r0.result(r3)     // Catch:{ Exception -> 0x00a2 }
                    goto L_0x008b
                L_0x0060:
                    com.wx.assistants.dialog.AlertEditDialog$OnEditTextListener r3 = r3     // Catch:{ Exception -> 0x00a2 }
                    com.wx.assistants.dialog.AlertEditDialog r0 = com.wx.assistants.dialog.AlertEditDialog.this     // Catch:{ Exception -> 0x00a2 }
                    int r0 = r0.minNum     // Catch:{ Exception -> 0x00a2 }
                    r3.result(r0)     // Catch:{ Exception -> 0x00a2 }
                    com.wx.assistants.dialog.AlertEditDialog r3 = com.wx.assistants.dialog.AlertEditDialog.this     // Catch:{ Exception -> 0x00a2 }
                    android.content.Context r3 = r3.context     // Catch:{ Exception -> 0x00a2 }
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a2 }
                    r0.<init>()     // Catch:{ Exception -> 0x00a2 }
                    java.lang.String r1 = "可最小设置"
                    r0.append(r1)     // Catch:{ Exception -> 0x00a2 }
                    com.wx.assistants.dialog.AlertEditDialog r1 = com.wx.assistants.dialog.AlertEditDialog.this     // Catch:{ Exception -> 0x00a2 }
                    int r1 = r1.minNum     // Catch:{ Exception -> 0x00a2 }
                    r0.append(r1)     // Catch:{ Exception -> 0x00a2 }
                    java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x00a2 }
                    com.wx.assistants.utils.ToastUtils.showToast(r3, r0)     // Catch:{ Exception -> 0x00a2 }
                L_0x008b:
                    android.content.Context r3 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x0098 }
                    com.wx.assistants.dialog.AlertEditDialog r0 = com.wx.assistants.dialog.AlertEditDialog.this     // Catch:{ Exception -> 0x0098 }
                    android.widget.Button r0 = r0.btn_pos     // Catch:{ Exception -> 0x0098 }
                    com.wx.assistants.utils.KeyBroadUtils.hide_keyboard_from(r3, r0)     // Catch:{ Exception -> 0x0098 }
                L_0x0098:
                    com.wx.assistants.dialog.AlertEditDialog r3 = com.wx.assistants.dialog.AlertEditDialog.this     // Catch:{ Exception -> 0x00a2 }
                    android.app.Dialog r3 = r3.dialog     // Catch:{ Exception -> 0x00a2 }
                    r3.dismiss()     // Catch:{ Exception -> 0x00a2 }
                    goto L_0x00c2
                L_0x00a2:
                    com.wx.assistants.dialog.AlertEditDialog r3 = com.wx.assistants.dialog.AlertEditDialog.this
                    android.content.Context r3 = r3.context
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    r0.<init>()
                    java.lang.String r1 = "可最小设置"
                    r0.append(r1)
                    com.wx.assistants.dialog.AlertEditDialog r1 = com.wx.assistants.dialog.AlertEditDialog.this
                    int r1 = r1.minNum
                    r0.append(r1)
                    java.lang.String r0 = r0.toString()
                    com.wx.assistants.utils.ToastUtils.showToast(r3, r0)
                L_0x00c2:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.dialog.AlertEditDialog.AnonymousClass2.onClick(android.view.View):void");
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
