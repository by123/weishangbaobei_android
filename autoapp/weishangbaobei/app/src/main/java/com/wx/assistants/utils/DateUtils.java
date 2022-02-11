package com.wx.assistants.utils;

import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.wx.assistants.application.MyApplication;
import com.wx.assistants.utils.fileutil.ListUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    private static final String datePattern = "yyyy年MM月dd日";
    private static final String datePatternYear = "yyyy年";

    public static final String now() {
        return convertDate2String(datePattern);
    }

    public static final String nowYear() {
        return convertDate2String(datePatternYear);
    }

    public static final String yestoday() {
        return add(5, -1);
    }

    public static final String get_yestoday() {
        return add2(5, -1);
    }

    public static final String tomorrow() {
        return add(5, 1);
    }

    public static final String add(int i, int i2) {
        Calendar calendar = getCalendar();
        calendar.add(i, i2);
        return convertDate2String(calendar.getTime());
    }

    public static final String add2(int i, int i2) {
        Calendar calendar = getCalendar();
        calendar.add(i, i2);
        return convertDate2String(calendar.getTime(), "MM月#dd日");
    }

    public static final String today() {
        return convertDate2String("yyyy-MM-dd");
    }

    public static final int year() {
        return getCalendar().get(1);
    }

    public static final int year(Date date) {
        return getCalendar(date).get(1);
    }

    public static final int month() {
        return getCalendar().get(2) + 1;
    }

    public static final int month(Date date) {
        return getCalendar(date).get(2) + 1;
    }

    public static final int day() {
        return getCalendar().get(5);
    }

    public static final int day(Date date) {
        return getCalendar(date).get(5);
    }

    public static final int dayInMonth() {
        return getCalendar().get(5);
    }

    public static final int dayInMonth(Date date) {
        return getCalendar(date).get(5);
    }

    public static final int week() {
        return getCalendar().get(7) - 1;
    }

    public static final int week(Date date) {
        return getCalendar(date).get(7) - 1;
    }

    public static final int dayOfWeekInMonth() {
        return getCalendar().get(8);
    }

    public static final int dayOfWeekInMonth(Date date) {
        return getCalendar(date).get(8);
    }

    public static final int dayInYear() {
        return getCalendar().get(6);
    }

    public static final int dayInYear(Date date) {
        return getCalendar(date).get(6);
    }

    public static final String convertDate2String(Date date) {
        return convertDate2String(date, datePattern);
    }

    public static final boolean endJump(String str, String str2) {
        if (str2 == null || str2.equals("")) {
            return false;
        }
        if (!str2.contains("日")) {
            if (str2.contains("昨日") || str2.contains("昨天")) {
                str2 = yestoday();
            } else {
                str2 = now();
            }
        }
        long diff = diff(convertString2Date(str), convertString2Date(str2), 5);
        LogUtils.log("WS_BABY.time0=" + str2 + ListUtils.DEFAULT_JOIN_SEPARATOR + str);
        StringBuilder sb = new StringBuilder();
        sb.append("WS_BABY.time1=");
        sb.append(diff);
        LogUtils.log(sb.toString());
        if (diff < 0) {
            return true;
        }
        return false;
    }

    public static final boolean isScroll(String str, String str2) {
        long diff = diff(convertString2Date(str2), convertString2Date(str), 5);
        LogUtils.log("WS_BABY_SCROLL" + diff);
        return diff > 0;
    }

    public static final boolean isSatisfactory(String str, String str2, String str3) {
        if (str3 == null || str3.equals("")) {
            return false;
        }
        if (!str3.contains("日")) {
            if (str3.contains("昨日") || str3.contains("昨天")) {
                str3 = yestoday();
            } else {
                str3 = now();
            }
        }
        Date convertString2Date = convertString2Date(str3);
        Date convertString2Date2 = convertString2Date(str);
        long diff = diff(convertString2Date(str2), convertString2Date, 5);
        long diff2 = diff(convertString2Date2, convertString2Date, 5);
        LogUtils.log("WS_BABY.time2=" + diff2 + "#" + diff);
        if (diff > 0 || diff2 < 0) {
            return false;
        }
        return true;
    }

    public static final String convertDate2String(String str) {
        return convertDate2String(new Date(), str);
    }

    public static final String convertDate2String(String str, String str2) {
        return convertDate2String(str, datePattern, str2);
    }

    public static final String convertDate2String(String str, String str2, String str3) {
        return convertDate2String(convertString2Date(str, str2), str3);
    }

    public static final String convertDate2String(Date date, String str) {
        if (date != null) {
            return new SimpleDateFormat(str).format(date);
        }
        return "";
    }

    public static String timeToString(long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return simpleDateFormat.format(simpleDateFormat.parse(simpleDateFormat.format(new Date(j))));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String timeToString(long j, String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        try {
            return simpleDateFormat.format(simpleDateFormat.parse(simpleDateFormat.format(new Date(j))));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static final Date convertString2Date(String str) {
        return convertString2Date(str, datePattern);
    }

    public static final Date convertString2Date(String str, String str2) {
        if (str != null) {
            try {
                return new SimpleDateFormat(str2).parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Calendar getCalendar() {
        return Calendar.getInstance();
    }

    public static Calendar getCalendar(Date date) {
        Calendar calendar = getCalendar();
        calendar.setTime(date);
        return calendar;
    }

    public static Date getDate(int i, int i2, int i3) {
        Calendar calendar = getCalendar();
        calendar.set(1, i);
        calendar.set(2, i2);
        calendar.set(5, i3);
        return calendar.getTime();
    }

    public static long diff(Date date, Date date2, int i) {
        int i2;
        long j = 0;
        try {
            long time = date2.getTime() - date.getTime();
            if (!getCalendar(date2).before(getCalendar(date))) {
                Date date3 = date2;
                date2 = date;
                date = date3;
            }
            if (i == 5) {
                return time / 86400000;
            }
            if (i == 10) {
                return time / 3600000;
            }
            switch (i) {
                case 1:
                    Calendar calendar = getCalendar(date);
                    calendar.set(1, year(date2));
                    j = (long) ((year(date) - year(date2)) - (calendar.before(getCalendar(date2)) ? 1 : 0));
                    break;
                case 2:
                    int year = year(date) - year(date2);
                    Calendar calendar2 = getCalendar(date);
                    calendar2.set(1, year(date2));
                    calendar2.set(2, month(date2) - 1);
                    if (month(date) >= month(date2)) {
                        i2 = month(date) - month(date2);
                    } else {
                        year--;
                        i2 = (((month(date) + 12) - month(date2)) % 12) + (year * 0);
                    }
                    j = (long) ((i2 + (year * 12)) - (calendar2.before(getCalendar(date2)) ? 1 : 0));
                    break;
                case 3:
                    return time / 604800000;
                default:
                    switch (i) {
                        case 12:
                            return time / 60000;
                        case 13:
                            return time / 1000;
                        case 14:
                            return time;
                    }
            }
            return j;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static class Lunar {
        private static final String[] Animals = {"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};
        private static final String[] Gan = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
        private static final int MAX_YEAR = 2049;
        private static final int MIN_YEAR = 1900;
        private static final String[] Zhi = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
        private static final String[] chineseDay = {"一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"};
        private static final String[] chineseMonth = {"正月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "腊月"};
        private static final String[] chineseTen = {"初", "十", "廿", "卅"};
        private static final long[] lunarInfo = {19416, 19168, 42352, 21717, 53856, 55632, 91476, 22176, 39632, 21970, 19168, 42422, 42192, 53840, 119381, 46400, 54944, 44450, 38320, 84343, 18800, 42160, 46261, 27216, 27968, 109396, 11104, 38256, 21234, 18800, 25958, 54432, 59984, 28309, 23248, 11104, 100067, 37600, 116951, 51536, 54432, 120998, 46416, 22176, 107956, 9680, 37584, 53938, 43344, 46423, 27808, 46416, 86869, 19872, 42448, 83315, 21200, 43432, 59728, 27296, 44710, 43856, 19296, 43748, 42352, 21088, 62051, 55632, 23383, 22176, 38608, 19925, 19152, 42192, 54484, 53840, 54616, 46400, 46496, 103846, 38320, 18864, 43380, 42160, 45690, 27216, 27968, 44870, 43872, 38256, 19189, 18800, 25776, 29859, 59984, 27480, 21952, 43872, 38613, 37600, 51552, 55636, 54432, 55888, 30034, 22176, 43959, 9680, 37584, 51893, 43344, 46240, 47780, 44368, 21977, 19360, 42416, 86390, 21168, 43312, 31060, 27296, 44368, 23378, 19296, 42726, 42208, 53856, 60005, 54576, 23200, 30371, 38608, 19415, 19152, 42192, 118966, 53840, 54560, 56645, 46496, 22224, 21938, 18864, 42359, 42160, 43600, 111189, 27936, 44448};

        public static int days4YearInLunar(int i) {
            int i2 = 348;
            for (int i3 = 32768; i3 > 8; i3 >>= 1) {
                if ((lunarInfo[i - 1900] & ((long) i3)) != 0) {
                    i2++;
                }
            }
            return i2 + days4LeapInLunar(i);
        }

        public static int days4LeapInLunar(int i) {
            if (leapMonth(i) != 0) {
                return (lunarInfo[i + -1900] & 65536) != 0 ? 30 : 29;
            }
            return 0;
        }

        public static int days4MonthInLunar(int i, int i2) {
            return (((long) (WXMediaMessage.THUMB_LENGTH_LIMIT >> i2)) & lunarInfo[i + -1900]) == 0 ? 29 : 30;
        }

        public static int leapMonth(int i) {
            return (int) (lunarInfo[i - 1900] & 15);
        }

        public static String getLunar(Date date) {
            return getLunar(date, false);
        }

        public static String getLunar(Date date, boolean z) {
            boolean z2;
            int time = (int) ((date.getTime() - DateUtils.getCalendar(DateUtils.convertString2Date("1900-01-31", "yyyy-mm-dd")).getTime().getTime()) / 86400000);
            int i = time + 40;
            int i2 = MIN_YEAR;
            int i3 = 14;
            int i4 = 0;
            while (i2 <= 2049 && time > 0) {
                i4 = days4YearInLunar(i2);
                time -= i4;
                i3 += 12;
                i2++;
            }
            if (time < 0) {
                time += i4;
                i2--;
                i3 -= 12;
            }
            int i5 = (i2 - 1900) + 36;
            int leapMonth = leapMonth(i2);
            int i6 = i3;
            int i7 = i4;
            boolean z3 = false;
            int i8 = time;
            int i9 = 1;
            while (i9 <= 12 && i8 > 0) {
                if (leapMonth <= 0 || i9 != leapMonth + 1 || z3) {
                    i7 = days4MonthInLunar(i2, i9);
                } else {
                    i9--;
                    i7 = days4LeapInLunar(i2);
                    z3 = true;
                }
                if (z3 && i9 == leapMonth + 1) {
                    z3 = false;
                }
                i8 -= i7;
                if (!z3) {
                    i6++;
                }
                i9++;
            }
            if (i8 != 0 || leapMonth <= 0 || i9 != leapMonth + 1) {
                z2 = z3;
            } else if (z3) {
                z2 = false;
            } else {
                i9--;
                i6--;
                z2 = true;
            }
            if (i8 < 0) {
                i8 += i7;
                i9--;
                i6--;
            }
            return getChinaString(i2, i9, i8 + 1, i5, i6, i, z2, z);
        }

        public static String animalsYear(int i) {
            return Animals[(i - 4) % 12];
        }

        public static String cyclicalm(int i) {
            return Gan[i % 10] + Zhi[i % 12];
        }

        private static String getChinaString(int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2) {
            StringBuffer stringBuffer = new StringBuffer();
            if (z2) {
                stringBuffer.append(cyclicalm(i4));
                stringBuffer.append("年【");
                stringBuffer.append(getYearInChinese(i));
                stringBuffer.append("】");
                stringBuffer.append(z ? "闰" : "");
                stringBuffer.append(getMonthInChinese(i2));
                stringBuffer.append(getDayInChinese(i3));
                stringBuffer.append("（");
                stringBuffer.append(cyclicalm(i5));
                stringBuffer.append("月 ");
                stringBuffer.append(cyclicalm(i6));
                stringBuffer.append("日");
                stringBuffer.append("）");
            } else {
                stringBuffer.append(getYearInChinese(i));
                stringBuffer.append(z ? " 闰" : " ");
                stringBuffer.append(getMonthInChinese(i2));
                stringBuffer.append(getDayInChinese(i3));
            }
            return stringBuffer.toString();
        }

        private static String getYearInChinese(int i) {
            if (i <= MIN_YEAR || i > 2049) {
                return "出错";
            }
            return animalsYear(i) + "年";
        }

        private static String getMonthInChinese(int i) {
            return (i <= 0 || i > 12) ? "出错" : chineseMonth[i - 1];
        }

        private static String getDayInChinese(int i) {
            int i2 = i % 10;
            int i3 = i2 == 0 ? 9 : i2 - 1;
            if (i > 30) {
                return "出错";
            }
            if (i == 10) {
                return "初十";
            }
            return chineseTen[i / 10] + chineseDay[i3];
        }
    }

    public static void main(String[] strArr) {
        LogUtils.log("today is " + today());
        LogUtils.log("now is " + now());
        LogUtils.log("年份： " + year());
        LogUtils.log("月份：" + month());
        LogUtils.log("日期： " + day());
        LogUtils.log("几号： " + dayInMonth());
        LogUtils.log("星期几： " + week());
        LogUtils.log("这个月第几周： " + dayOfWeekInMonth());
        LogUtils.log("今年第几天： " + dayInYear());
        LogUtils.log("属相年：" + Lunar.animalsYear(year()));
        LogUtils.log("农历：" + Lunar.getLunar(convertString2Date("2009-06-27", "yyyy-MM-dd")));
        LogUtils.log("农历：" + Lunar.getLunar(convertString2Date("2009-06-27", "yyyy-MM-dd"), true));
        LogUtils.log("农历：" + Lunar.getLunar(convertString2Date(today(), "yyyy-MM-dd"), true));
        LogUtils.log(diff(convertString2Date("2005-11-01", "yyyy-MM-dd"), convertString2Date("2015-11-01", "yyyy-MM-dd"), 1) + "");
        LogUtils.log(diff(convertString2Date("2005-11-01 10:00:00"), convertString2Date("2015-11-01 10:00:00"), 2) + "");
    }

    private static boolean isSameDate(Date date, Date date2) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date2);
        if (!((instance.get(1) == instance2.get(1)) && instance.get(2) == instance2.get(2)) || instance.get(5) != instance2.get(5)) {
            return false;
        }
        return true;
    }

    public static boolean isSameDate(long j, long j2) {
        return isSameDate(new Date(j), new Date(j2));
    }

    public static void putCameraNum(int i) {
        if (isSameDate(((Long) SPUtils.get(MyApplication.getConText(), "addCameraTJTime", Long.valueOf(System.currentTimeMillis()))).longValue(), System.currentTimeMillis())) {
            SPUtils.put(MyApplication.getConText(), "addCameraTJNum", Integer.valueOf(((Integer) SPUtils.get(MyApplication.getConText(), "addCameraTJNum", 0)).intValue() + i));
            SPUtils.put(MyApplication.getConText(), "addCameraTJTime", Long.valueOf(System.currentTimeMillis()));
            return;
        }
        SPUtils.put(MyApplication.getConText(), "addCameraTJNum", Integer.valueOf(i));
        SPUtils.put(MyApplication.getConText(), "addCameraTJTime", Long.valueOf(System.currentTimeMillis()));
    }

    public static int getCameraNum() {
        if (isSameDate(((Long) SPUtils.get(MyApplication.getConText(), "addCameraTJTime", Long.valueOf(System.currentTimeMillis()))).longValue(), System.currentTimeMillis())) {
            return ((Integer) SPUtils.get(MyApplication.getConText(), "addCameraTJNum", 0)).intValue();
        }
        return 0;
    }

    public static String switchCreateDateTime(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(str).toString()));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String switchCreateDate(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(str).toString()));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String switchCreateTime(String str) {
        try {
            return new SimpleDateFormat("HH:mm:ss").format(new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(str).toString()));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
