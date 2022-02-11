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
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.utils.KeyBroadUtils;
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

    public AlertEditDialog setCancelable(boolean z) {
        this.dialog.setCancelable(z);
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

    public void setOnEditTextListener(OnEditTextListener onEditTextListener) {
        this.listener = onEditTextListener;
    }

    public AlertEditDialog setPositiveButton(final OnEditTextListener onEditTextListener) {
        this.btn_pos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    int parseInt = Integer.parseInt(AlertEditDialog.this.editText.getText().toString());
                    if (onEditTextListener != null) {
                        if (parseInt >= AlertEditDialog.this.minNum && parseInt > AlertEditDialog.this.maxNum) {
                            onEditTextListener.result(AlertEditDialog.this.maxNum);
                            Context access$100 = AlertEditDialog.this.context;
                            ToastUtils.showToast(access$100, "可最大设置" + AlertEditDialog.this.maxNum);
                        } else if (parseInt >= AlertEditDialog.this.minNum) {
                            onEditTextListener.result(parseInt);
                        } else {
                            onEditTextListener.result(AlertEditDialog.this.minNum);
                            Context access$1002 = AlertEditDialog.this.context;
                            ToastUtils.showToast(access$1002, "可最小设置" + AlertEditDialog.this.minNum);
                        }
                        try {
                            KeyBroadUtils.hide_keyboard_from(MyApplication.getConText(), AlertEditDialog.this.btn_pos);
                        } catch (Exception e) {
                        }
                        AlertEditDialog.this.dialog.dismiss();
                    }
                } catch (Exception e2) {
                    Context access$1003 = AlertEditDialog.this.context;
                    ToastUtils.showToast(access$1003, "可最小设置" + AlertEditDialog.this.minNum);
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

    public void show() {
        try {
            this.dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
