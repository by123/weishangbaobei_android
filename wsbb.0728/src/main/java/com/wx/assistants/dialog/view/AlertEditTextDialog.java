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
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.utils.KeyBroadUtils;
import com.wx.assistants.utils.ToastUtils;

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

    public AlertEditTextDialog setCancelable(boolean z) {
        this.dialog.setCancelable(z);
        return this;
    }

    public AlertEditTextDialog setEditText(String str) {
        if (this.editText != null) {
            this.editText.setText(str);
            this.editText.setSelection(this.editText.getText().length());
        }
        return this;
    }

    public void setOnEditTextListener(OnEditTextListener onEditTextListener) {
        this.listener = onEditTextListener;
    }

    public AlertEditTextDialog setPositiveButton(final OnEditTextListener onEditTextListener) {
        this.btn_pos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    String obj = AlertEditTextDialog.this.editText.getText().toString();
                    if (onEditTextListener == null || obj == null) {
                        Context access$100 = AlertEditTextDialog.this.context;
                        ToastUtils.showToast(access$100, "请设置" + AlertEditTextDialog.this.txt_title.getText().toString());
                        return;
                    }
                    onEditTextListener.result(obj);
                    try {
                        KeyBroadUtils.hide_keyboard_from(MyApplication.getConText(), AlertEditTextDialog.this.btn_pos);
                    } catch (Exception e) {
                    }
                    AlertEditTextDialog.this.dialog.dismiss();
                } catch (Exception e2) {
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

    public void show() {
        try {
            this.dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
