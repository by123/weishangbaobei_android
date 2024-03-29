package com.meiqia.meiqiasdk.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import com.meiqia.meiqiasdk.R;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MQEmotionUtil {
    public static final String REGEX_EMOJI = ":[一-龥\\w]+:";
    public static final String REGEX_GROUP = "(:[一-龥\\w]+:)";
    public static final String[] sEmotionKeyArr = {":smile:", ":smiley:", ":grinning:", ":blush:", ":relaxed:", ":wink:", ":heart_eyes:", ":kissing_heart:", ":kissing_closed_eyes:", ":kissing:", ":kissing_smiling_eyes:", ":stuck_out_tongue_winking_eye:", ":stuck_out_tongue_closed_eyes:", ":stuck_out_tongue:", ":flushed:", ":grin:", ":pensive:", ":relieved:", ":unamused:", ":disappointed:", ":persevere:", ":cry:", ":joy:", ":sob:", ":sleepy:", ":disappointed_relieved:", ":cold_sweat:", ":sweat_smile:", ":sweat:", ":weary:", ":tired_face:", ":fearful:", ":scream:", ":angry:", ":rage:", ":dog:"};
    public static final Map<String, Integer> sEmotionMap = new HashMap();
    public static final int[] sEmotionValueArr = {R.drawable.mq_emoji_1, R.drawable.mq_emoji_2, R.drawable.mq_emoji_3, R.drawable.mq_emoji_4, R.drawable.mq_emoji_5, R.drawable.mq_emoji_6, R.drawable.mq_emoji_7, R.drawable.mq_emoji_8, R.drawable.mq_emoji_9, R.drawable.mq_emoji_10, R.drawable.mq_emoji_11, R.drawable.mq_emoji_12, R.drawable.mq_emoji_13, R.drawable.mq_emoji_14, R.drawable.mq_emoji_15, R.drawable.mq_emoji_16, R.drawable.mq_emoji_17, R.drawable.mq_emoji_18, R.drawable.mq_emoji_19, R.drawable.mq_emoji_20, R.drawable.mq_emoji_21, R.drawable.mq_emoji_22, R.drawable.mq_emoji_23, R.drawable.mq_emoji_24, R.drawable.mq_emoji_25, R.drawable.mq_emoji_26, R.drawable.mq_emoji_27, R.drawable.mq_emoji_28, R.drawable.mq_emoji_29, R.drawable.mq_emoji_30, R.drawable.mq_emoji_31, R.drawable.mq_emoji_32, R.drawable.mq_emoji_33, R.drawable.mq_emoji_34, R.drawable.mq_emoji_35, R.drawable.mq_emoji_36};

    static {
        int length = sEmotionKeyArr.length;
        for (int i = 0; i < length; i++) {
            sEmotionMap.put(sEmotionKeyArr[i], Integer.valueOf(sEmotionValueArr[i]));
        }
    }

    private MQEmotionUtil() {
    }

    public static SpannableString getEmotionText(Context context, String str, int i) {
        ImageSpan imageSpan;
        SpannableString spannableString = new SpannableString(str);
        Matcher matcher = Pattern.compile(REGEX_GROUP).matcher(spannableString);
        if (matcher.find()) {
            matcher.reset();
        }
        while (matcher.find()) {
            String group = matcher.group(1);
            if (!(group == null || (imageSpan = getImageSpan(context, group, i)) == null)) {
                int start = matcher.start(1);
                spannableString.setSpan(imageSpan, start, group.length() + start, 33);
            }
        }
        return spannableString;
    }

    public static ImageSpan getImageSpan(Context context, String str, int i) {
        Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), getImgByName(str));
        if (decodeResource == null) {
            return null;
        }
        int dip2px = MQUtils.dip2px(context, (float) i);
        return new ImageSpan(context, Bitmap.createScaledBitmap(decodeResource, dip2px, dip2px, true));
    }

    public static int getImgByName(String str) {
        Integer num = sEmotionMap.get(str);
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }
}
