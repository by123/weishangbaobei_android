package com.meiqia.meiqiasdk.widget;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;
import android.widget.TextView;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.model.MessageFormInputModel;

public class MQMessageFormInputLayout extends MQBaseCustomCompositeView {
    private EditText mContentEt;
    private TextView mTipTv;

    public MQMessageFormInputLayout(Context context, MessageFormInputModel messageFormInputModel) {
        super(context);
        setFormInputModel(messageFormInputModel);
    }

    private void setFormInputModel(MessageFormInputModel messageFormInputModel) {
        this.mTipTv.setText(messageFormInputModel.tip);
        this.mContentEt.setHint(messageFormInputModel.hint);
        if (messageFormInputModel.inputType != 0) {
            this.mContentEt.setInputType(messageFormInputModel.inputType);
        }
        if (messageFormInputModel.required) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.mTipTv.getText() + " *");
            spannableStringBuilder.setSpan(new ForegroundColorSpan(-65536), this.mTipTv.getText().length() + 1, spannableStringBuilder.length(), 33);
            this.mTipTv.setText(spannableStringBuilder);
        }
        if (messageFormInputModel.singleLine) {
            this.mContentEt.setSingleLine();
            return;
        }
        this.mContentEt.setSingleLine(false);
        this.mContentEt.setMaxLines(4);
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.mq_layout_form_input;
    }

    public String getText() {
        return this.mContentEt.getText().toString().trim();
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.mTipTv = (TextView) getViewById(R.id.tip_tv);
        this.mContentEt = (EditText) getViewById(R.id.content_et);
    }

    /* access modifiers changed from: protected */
    public void processLogic() {
    }

    /* access modifiers changed from: protected */
    public void setListener() {
    }
}
