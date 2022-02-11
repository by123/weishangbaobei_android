package com.wx.assistants.utils;

import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

public class CountDownTimerUtils extends CountDownTimer {
    private TextView mTextView;

    public CountDownTimerUtils(TextView textView, long j, long j2) {
        super(j, j2);
        this.mTextView = textView;
    }

    public void onFinish() {
        this.mTextView.setText("重新获取验证码");
        this.mTextView.setClickable(true);
    }

    public void onTick(long j) {
        this.mTextView.setClickable(false);
        TextView textView = this.mTextView;
        textView.setText((j / 1000) + "秒后可重新发送");
        SpannableString spannableString = new SpannableString(this.mTextView.getText().toString());
        spannableString.setSpan(new ForegroundColorSpan(-65536), 0, 2, 17);
        this.mTextView.setText(spannableString);
    }
}
