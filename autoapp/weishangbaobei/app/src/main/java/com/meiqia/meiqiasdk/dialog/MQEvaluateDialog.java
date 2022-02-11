package com.meiqia.meiqiasdk.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.util.MQUtils;

public class MQEvaluateDialog extends Dialog implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private Callback mCallback;
    private TextView mConfirmTv;
    private EditText mContentEt = ((EditText) findViewById(R.id.et_evaluate_content));
    private RadioGroup mContentRg = ((RadioGroup) findViewById(R.id.rg_evaluate_content));
    private TextView mTipTv = ((TextView) findViewById(R.id.tv_evaluate_tip));

    public interface Callback {
        void executeEvaluate(int i, String str);
    }

    public MQEvaluateDialog(Activity activity, String str) {
        super(activity, R.style.MQDialog);
        setContentView(R.layout.mq_dialog_evaluate);
        getWindow().setLayout(-1, -2);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        this.mContentRg.setOnCheckedChangeListener(this);
        findViewById(R.id.tv_evaluate_cancel).setOnClickListener(this);
        this.mConfirmTv = (TextView) findViewById(R.id.tv_evaluate_confirm);
        this.mConfirmTv.setOnClickListener(this);
        if (!TextUtils.isEmpty(str)) {
            this.mTipTv.setText(str);
        }
    }

    public void onClick(View view) {
        MQUtils.closeKeyboard((Dialog) this);
        dismiss();
        if (view.getId() == R.id.tv_evaluate_confirm && this.mCallback != null) {
            int i = 2;
            int checkedRadioButtonId = this.mContentRg.getCheckedRadioButtonId();
            if (checkedRadioButtonId == R.id.rb_evaluate_medium) {
                i = 1;
            } else if (checkedRadioButtonId == R.id.rb_evaluate_bad) {
                i = 0;
            }
            this.mCallback.executeEvaluate(i, this.mContentEt.getText().toString().trim());
        }
        this.mContentEt.setText("");
        this.mContentEt.clearFocus();
        this.mContentRg.check(R.id.rb_evaluate_good);
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        this.mContentEt.clearFocus();
        MQUtils.closeKeyboard((Dialog) this);
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }
}
