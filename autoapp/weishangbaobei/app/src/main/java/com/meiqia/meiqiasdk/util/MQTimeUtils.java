package com.meiqia.meiqiasdk.util;

import android.content.Context;
import com.meiqia.meiqiasdk.R;
import com.meiqia.meiqiasdk.model.BaseMessage;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class MQTimeUtils {
    private static final String HOURS_MINUTE = "H:mm";
    private static final String MONTH_DAY = "M-d";
    private static final long TIME_INTERNAL_LIMIT = 120000;
    private static String TODAY = "today";
    private static String YESTERDAY = "yesterday";

    public static void init(Context context) {
        TODAY = context.getResources().getString(R.string.mq_timeline_today);
        YESTERDAY = context.getResources().getString(R.string.mq_timeline_yesterday);
    }

    public static void refreshMQTimeItem(List<BaseMessage> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            if (list.get(size).getItemViewType() == 2) {
                list.remove(size);
            }
        }
        addMQTimeItem(list);
    }

    private static void addMQTimeItem(List<BaseMessage> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            if (size != 0) {
                long createdOn = list.get(size).getCreatedOn();
                if (createdOn - list.get(size - 1).getCreatedOn() > TIME_INTERNAL_LIMIT && list.get(size).getItemViewType() != 2) {
                    BaseMessage baseMessage = new BaseMessage();
                    baseMessage.setItemViewType(2);
                    baseMessage.setCreatedOn(createdOn);
                    list.add(size, baseMessage);
                }
            }
        }
    }

    public static String parseTime(long j) {
        Date date = new Date(j);
        String format = new SimpleDateFormat(HOURS_MINUTE, Locale.getDefault()).format(date);
        if (j > getTodayZeroTime()) {
            return TODAY + " " + format;
        } else if (j <= getYesterdayZeroTime() || j >= getTodayZeroTime()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(MONTH_DAY, Locale.getDefault());
            return simpleDateFormat.format(date) + " " + format;
        } else {
            return YESTERDAY + " " + format;
        }
    }

    private static long getTodayZeroTime() {
        Calendar instance = Calendar.getInstance();
        instance.set(11, 0);
        instance.set(13, 0);
        instance.set(12, 0);
        instance.set(14, 0);
        return instance.getTimeInMillis();
    }

    private static long getYesterdayZeroTime() {
        Calendar instance = Calendar.getInstance();
        instance.set(11, -24);
        instance.set(13, 0);
        instance.set(12, 0);
        instance.set(14, 0);
        return instance.getTimeInMillis();
    }

    public static long parseTimeToLong(String str) {
        if (str == null) {
            return System.currentTimeMillis();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CHINA);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return simpleDateFormat.parse(str).getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return System.currentTimeMillis();
        }
    }

    public static String partLongToTime(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date(j));
    }
}
