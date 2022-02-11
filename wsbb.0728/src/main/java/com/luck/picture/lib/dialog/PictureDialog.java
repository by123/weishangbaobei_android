package com.luck.picture.lib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import com.luck.picture.lib.R;

public class PictureDialog extends Dialog {
    public Context context;

    public PictureDialog(Context context2) {
        super(context2, R.style.picture_alert_dialog);
        this.context = context2;
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        getWindow().setWindowAnimations(R.style.DialogWindowStyle);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.picture_alert_dialog);
    }
}
