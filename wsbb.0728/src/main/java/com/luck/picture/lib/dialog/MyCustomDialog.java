package com.luck.picture.lib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.fb.jjyyzjy.videocomplex.R;

public class MyCustomDialog extends Dialog {
    private final Context context;
    /* access modifiers changed from: private */
    public OnCancelClickListener onCancelClickListener;
    /* access modifiers changed from: private */
    public OnOkClickListener onOkClickListener;
    private TextView tvCancel;
    private TextView tvContent;
    private TextView tvOk;

    public interface OnCancelClickListener {
        void onCancelClick();
    }

    public interface OnOkClickListener {
        void onOkClick();
    }

    public MyCustomDialog(Context context2) {
        this(context2, R.style.DialogStyle);
    }

    private MyCustomDialog(Context context2, int i) {
        super(context2, i);
        this.context = context2;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.custom_dialog);
        this.tvContent = (TextView) findViewById(R.id.tv_content);
        this.tvCancel = (TextView) findViewById(R.id.tv_cancel);
        this.tvOk = (TextView) findViewById(R.id.tv_ok);
        this.tvCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MyCustomDialog.this.onCancelClickListener != null) {
                    MyCustomDialog.this.onCancelClickListener.onCancelClick();
                }
                MyCustomDialog.this.dismiss();
            }
        });
        this.tvOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MyCustomDialog.this.onOkClickListener != null) {
                    MyCustomDialog.this.onOkClickListener.onOkClick();
                }
                MyCustomDialog.this.dismiss();
            }
        });
        setCancelable(true);
    }

    public void setTvCancel(String str) {
        if (!TextUtils.isEmpty(str) && this.tvCancel != null) {
            this.tvCancel.setText(str);
        }
    }

    public void setTvOk(String str) {
        if (!TextUtils.isEmpty(str) && this.tvOk != null) {
            this.tvOk.setText(str);
        }
    }

    public void show(String str, String str2, String str3, OnOkClickListener onOkClickListener2, OnCancelClickListener onCancelClickListener2) {
        super.show();
        this.onOkClickListener = onOkClickListener2;
        this.onCancelClickListener = onCancelClickListener2;
        this.tvContent.setText(str);
        if (!TextUtils.isEmpty(str2)) {
            this.tvCancel.setText(str2);
        } else {
            this.tvCancel.setText(R.string.cancel);
        }
        if (!TextUtils.isEmpty(str3)) {
            this.tvOk.setText(str3);
        } else {
            this.tvOk.setText(R.string.ok);
        }
    }
}
