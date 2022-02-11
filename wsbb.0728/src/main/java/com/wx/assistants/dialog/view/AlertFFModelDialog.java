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
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.utils.KeyBroadUtils;
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

    public AlertFFModelDialog setCancelable(boolean z) {
        this.dialog.setCancelable(z);
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

    public void setOnEditTextListener(OnEditTextListener onEditTextListener) {
        this.listener = onEditTextListener;
    }

    public AlertFFModelDialog setPositiveButton(final OnEditTextListener onEditTextListener) {
        this.btn_pos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    int parseInt = Integer.parseInt(AlertFFModelDialog.this.editText.getText().toString());
                    int parseInt2 = Integer.parseInt(AlertFFModelDialog.this.editText2.getText().toString());
                    if (onEditTextListener == null) {
                        return;
                    }
                    if (parseInt > AlertFFModelDialog.this.maxNum || parseInt2 > AlertFFModelDialog.this.maxNum) {
                        if (parseInt > AlertFFModelDialog.this.maxNum) {
                            Context access$300 = AlertFFModelDialog.this.context;
                            ToastUtils.showToast(access$300, "左边可最大设置" + AlertFFModelDialog.this.maxNum);
                            return;
                        }
                        Context access$3002 = AlertFFModelDialog.this.context;
                        ToastUtils.showToast(access$3002, "右边可最大设置" + AlertFFModelDialog.this.maxNum);
                    } else if (parseInt < AlertFFModelDialog.this.minNum) {
                        Context access$3003 = AlertFFModelDialog.this.context;
                        ToastUtils.showToast(access$3003, "左边可最小设置" + AlertFFModelDialog.this.minNum);
                    } else if (parseInt > parseInt2) {
                        ToastUtils.showToast(AlertFFModelDialog.this.context, "左边值 不能大于 右边值");
                    } else if (parseInt >= AlertFFModelDialog.this.minNum) {
                        onEditTextListener.result(parseInt, parseInt2);
                        try {
                            KeyBroadUtils.hide_keyboard_from(MyApplication.getConText(), AlertFFModelDialog.this.btn_pos);
                        } catch (Exception e) {
                        }
                        AlertFFModelDialog.this.dialog.dismiss();
                    }
                } catch (Exception e2) {
                    Context access$3004 = AlertFFModelDialog.this.context;
                    ToastUtils.showToast(access$3004, "可最小设置" + AlertFFModelDialog.this.minNum);
                }
            }
        });
        return this;
    }

    public AlertFFModelDialog setTitle(String str) {
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
