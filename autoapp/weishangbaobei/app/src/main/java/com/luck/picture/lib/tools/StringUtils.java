package com.luck.picture.lib.tools;

import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;
import com.luck.picture.lib.R;
import com.luck.picture.lib.config.PictureMimeType;

public class StringUtils {
    public static boolean isCamera(String str) {
        return (!TextUtils.isEmpty(str) && str.startsWith("相机胶卷")) || str.startsWith("CameraRoll") || str.startsWith("所有音频") || str.startsWith("All audio");
    }

    public static void tempTextFont(TextView textView, int i) {
        String str;
        String trim = textView.getText().toString().trim();
        if (i == PictureMimeType.ofAudio()) {
            str = textView.getContext().getString(R.string.picture_empty_audio_title);
        } else {
            str = textView.getContext().getString(R.string.picture_empty_title);
        }
        String str2 = str + trim;
        SpannableString spannableString = new SpannableString(str2);
        spannableString.setSpan(new RelativeSizeSpan(0.8f), str.length(), str2.length(), 33);
        textView.setText(spannableString);
    }

    public static void modifyTextViewDrawable(TextView textView, Drawable drawable, int i) {
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        if (i == 0) {
            textView.setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
        } else if (i == 1) {
            textView.setCompoundDrawables((Drawable) null, drawable, (Drawable) null, (Drawable) null);
        } else if (i == 2) {
            textView.setCompoundDrawables((Drawable) null, (Drawable) null, drawable, (Drawable) null);
        } else {
            textView.setCompoundDrawables((Drawable) null, (Drawable) null, (Drawable) null, drawable);
        }
    }
}
