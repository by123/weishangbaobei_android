package com.wx.assistants.dialog;

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
import com.luck.picture.lib.R;
import com.wx.assistants.utils.KeyBroadUtils;

public class AlertEditDefaultDialog {
    private Button btn_cancel;
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
    private TextView txt_title;

    public interface OnEditTextListener {
        void result(String str);
    }

    public AlertEditDefaultDialog(Context context2) {
        this.context = context2;
        this.display = ((WindowManager) context2.getSystemService("window")).getDefaultDisplay();
    }

    public AlertEditDefaultDialog builder() {
        View inflate = LayoutInflater.from(this.context).inflate(2131427727, (ViewGroup) null);
        this.lLayout_bg = (LinearLayout) inflate.findViewById(2131296881);
        this.txt_title = (TextView) inflate.findViewById(2131297603);
        this.txt_title.setVisibility(0);
        this.editText = (EditText) inflate.findViewById(2131296625);
        this.btn_pos = (Button) inflate.findViewById(2131296445);
        this.btn_pos.setVisibility(0);
        this.btn_cancel = (Button) inflate.findViewById(R.id.btn_cancel);
        this.btn_cancel.setVisibility(0);
        this.dialog = new Dialog(this.context, 2131755016);
        this.dialog.setContentView(inflate);
        LinearLayout linearLayout = this.lLayout_bg;
        double width = (double) this.display.getWidth();
        Double.isNaN(width);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams((int) (width * 0.75d), -2));
        this.btn_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    KeyBroadUtils.hide_keyboard_from(AlertEditDefaultDialog.this.context, AlertEditDefaultDialog.this.btn_pos);
                } catch (Exception e) {
                }
                AlertEditDefaultDialog.this.dialog.dismiss();
            }
        });
        return this;
    }

    public AlertEditDefaultDialog setCancelable(boolean z) {
        this.dialog.setCancelable(z);
        return this;
    }

    public AlertEditDefaultDialog setEditText(int i) {
        if (this.editText != null) {
            EditText editText2 = this.editText;
            editText2.setText(i + "");
            this.editText.setSelection(this.editText.getText().length());
        }
        return this;
    }

    public void setOnEditTextListener(OnEditTextListener onEditTextListener) {
        this.listener = onEditTextListener;
    }

    public AlertEditDefaultDialog setPositiveButton(final OnEditTextListener onEditTextListener) {
        this.btn_pos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    String obj = AlertEditDefaultDialog.this.editText.getText().toString();
                    if (onEditTextListener != null) {
                        onEditTextListener.result(obj);
                        try {
                            KeyBroadUtils.hide_keyboard_from(AlertEditDefaultDialog.this.context, AlertEditDefaultDialog.this.btn_pos);
                        } catch (Exception e) {
                        }
                        AlertEditDefaultDialog.this.dialog.dismiss();
                    }
                } catch (Exception e2) {
                }
            }
        });
        return this;
    }

    public AlertEditDefaultDialog setTitle(String str) {
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
