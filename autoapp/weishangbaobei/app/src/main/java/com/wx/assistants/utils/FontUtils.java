package com.wx.assistants.utils;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import com.luck.picture.lib.R;

public class FontUtils {
    public static void changeFontColor(Context context, TextView textView) {
        if (textView != null) {
            try {
                if (textView.getText() != null && textView.getText().toString() != null && !"".equals(textView.getText().toString())) {
                    String charSequence = textView.getText().toString();
                    SpannableString spannableString = new SpannableString(charSequence);
                    spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.main_color)), charSequence.indexOf("第") + 1, charSequence.indexOf("个"), 34);
                    textView.setText(spannableString);
                }
            } catch (Exception unused) {
            }
        }
    }

    public static void changeFontColor(Context context, TextView textView, String str, String str2) {
        if (textView != null) {
            try {
                if (textView.getText() != null && textView.getText().toString() != null && !"".equals(textView.getText().toString())) {
                    String charSequence = textView.getText().toString();
                    SpannableString spannableString = new SpannableString(charSequence);
                    spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.main_color)), charSequence.indexOf(str) + 1, charSequence.indexOf(str2), 34);
                    textView.setText(spannableString);
                }
            } catch (Exception unused) {
            }
        }
    }
}
