package com.luck.picture.lib.tools;

import java.text.SimpleDateFormat;

public class DateUtils {
    private static SimpleDateFormat msFormat = new SimpleDateFormat("mm:ss");

    public static String cdTime(long j, long j2) {
        long j3 = j2 - j;
        if (j3 > 1000) {
            return (j3 / 1000) + "秒";
        }
        return j3 + "毫秒";
    }

    public static int dateDiffer(long j) {
        try {
            return (int) Math.abs(Long.parseLong(String.valueOf(System.currentTimeMillis()).substring(0, 10)) - j);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String timeParse(long j) {
        String str = "";
        if (j > 1000) {
            return timeParseMinute(j);
        }
        long j2 = j / 60000;
        long round = (long) Math.round(((float) (j % 60000)) / 1000.0f);
        if (j2 < 10) {
            str = "" + "0";
        }
        String str2 = str + j2 + ":";
        if (round < 10) {
            str2 = str2 + "0";
        }
        return str2 + round;
    }

    public static String timeParseMinute(long j) {
        try {
            return msFormat.format(Long.valueOf(j));
        } catch (Exception e) {
            e.printStackTrace();
            return "0:00";
        }
    }
}
