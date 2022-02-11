package com.wx.assistants.dialog.view;

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

public class AlertEditTextDialog {
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
    public TextView txt_title;

    public interface OnEditTextListener {
        void result(String str);
    }

    public void setOnEditTextListener(OnEditTextListener onEditTextListener) {
        this.listener = onEditTextListener;
    }

    public AlertEditTextDialog(Context context2) {
        this.context = context2;
        this.display = ((WindowManager) context2.getSystemService("window")).getDefaultDisplay();
    }

    public AlertEditTextDialog builder() {
        View inflate = LayoutInflater.from(this.context).inflate(2131427728, (ViewGroup) null);
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
                if (AlertEditTextDialog.this.editText != null) {
                    AlertEditTextDialog.this.editText.postDelayed(new Runnable() {
                        public void run() {
                            ((InputMethodManager) AlertEditTextDialog.this.context.getSystemService("input_method")).showSoftInput(AlertEditTextDialog.this.editText, 1);
                        }
                    }, 100);
                }
            }
        });
        return this;
    }

    public AlertEditTextDialog setTitle(String str) {
        if (str != null) {
            this.txt_title.setText(str);
        }
        return this;
    }

    public AlertEditTextDialog setEditText(String str) {
        if (this.editText != null) {
            this.editText.setText(str);
            this.editText.setSelection(this.editText.getText().length());
        }
        return this;
    }

    public AlertEditTextDialog setCancelable(boolean z) {
        this.dialog.setCancelable(z);
        return this;
    }

    public AlertEditTextDialog setPositiveButton(final OnEditTextListener onEditTextListener) {
        this.btn_pos.setOnClickListener(new View.OnClickListener() {
            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0026 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onClick(android.view.View r3) {
                /*
                    r2 = this;
                    com.wx.assistants.dialog.view.AlertEditTextDialog r3 = com.wx.assistants.dialog.view.AlertEditTextDialog.this     // Catch:{ Exception -> 0x0058 }
                    android.widget.EditText r3 = r3.editText     // Catch:{ Exception -> 0x0058 }
                    android.text.Editable r3 = r3.getText()     // Catch:{ Exception -> 0x0058 }
                    java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0058 }
                    com.wx.assistants.dialog.view.AlertEditTextDialog$OnEditTextListener r0 = r3     // Catch:{ Exception -> 0x0058 }
                    if (r0 == 0) goto L_0x0030
                    if (r3 == 0) goto L_0x0030
                    com.wx.assistants.dialog.view.AlertEditTextDialog$OnEditTextListener r0 = r3     // Catch:{ Exception -> 0x0058 }
                    r0.result(r3)     // Catch:{ Exception -> 0x0058 }
                    android.content.Context r3 = com.wx.assistants.application.MyApplication.getConText()     // Catch:{ Exception -> 0x0026 }
                    com.wx.assistants.dialog.view.AlertEditTextDialog r0 = com.wx.assistants.dialog.view.AlertEditTextDialog.this     // Catch:{ Exception -> 0x0026 }
                    android.widget.Button r0 = r0.btn_pos     // Catch:{ Exception -> 0x0026 }
                    com.wx.assistants.utils.KeyBroadUtils.hide_keyboard_from(r3, r0)     // Catch:{ Exception -> 0x0026 }
                L_0x0026:
                    com.wx.assistants.dialog.view.AlertEditTextDialog r3 = com.wx.assistants.dialog.view.AlertEditTextDialog.this     // Catch:{ Exception -> 0x0058 }
                    android.app.Dialog r3 = r3.dialog     // Catch:{ Exception -> 0x0058 }
                    r3.dismiss()     // Catch:{ Exception -> 0x0058 }
                    goto L_0x0058
                L_0x0030:
                    com.wx.assistants.dialog.view.AlertEditTextDialog r3 = com.wx.assistants.dialog.view.AlertEditTextDialog.this     // Catch:{ Exception -> 0x0058 }
                    android.content.Context r3 = r3.context     // Catch:{ Exception -> 0x0058 }
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0058 }
                    r0.<init>()     // Catch:{ Exception -> 0x0058 }
                    java.lang.String r1 = "请设置"
                    r0.append(r1)     // Catch:{ Exception -> 0x0058 }
                    com.wx.assistants.dialog.view.AlertEditTextDialog r1 = com.wx.assistants.dialog.view.AlertEditTextDialog.this     // Catch:{ Exception -> 0x0058 }
                    android.widget.TextView r1 = r1.txt_title     // Catch:{ Exception -> 0x0058 }
                    java.lang.CharSequence r1 = r1.getText()     // Catch:{ Exception -> 0x0058 }
                    java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0058 }
                    r0.append(r1)     // Catch:{ Exception -> 0x0058 }
                    java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0058 }
                    com.wx.assistants.utils.ToastUtils.showToast(r3, r0)     // Catch:{ Exception -> 0x0058 }
                L_0x0058:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.wx.assistants.dialog.view.AlertEditTextDialog.AnonymousClass2.onClick(android.view.View):void");
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
