package com.wx.assistants.utils.fileutil;

import android.content.ContentValues;
import android.util.Log;
import java.lang.reflect.Field;

public class ObjectUtils {
    private ObjectUtils() {
        throw new AssertionError();
    }

    public static boolean isEquals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null ? obj.equals(obj2) : obj2 == null);
    }

    public static String nullStrToEmpty(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj instanceof String ? (String) obj : obj.toString();
    }

    public static Long[] transformLongArray(long[] jArr) {
        Long[] lArr = new Long[jArr.length];
        for (int i = 0; i < jArr.length; i++) {
            lArr[i] = Long.valueOf(jArr[i]);
        }
        return lArr;
    }

    public static long[] transformLongArray(Long[] lArr) {
        long[] jArr = new long[lArr.length];
        for (int i = 0; i < lArr.length; i++) {
            jArr[i] = lArr[i].longValue();
        }
        return jArr;
    }

    public static Integer[] transformIntArray(int[] iArr) {
        Integer[] numArr = new Integer[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            numArr[i] = Integer.valueOf(iArr[i]);
        }
        return numArr;
    }

    public static int[] transformIntArray(Integer[] numArr) {
        int[] iArr = new int[numArr.length];
        for (int i = 0; i < numArr.length; i++) {
            iArr[i] = numArr[i].intValue();
        }
        return iArr;
    }

    public static <V> int compare(V v, V v2) {
        if (v == null) {
            return v2 == null ? 0 : -1;
        }
        if (v2 == null) {
            return 1;
        }
        return ((Comparable) v).compareTo(v2);
    }

    public static ContentValues getContentValues(Object obj) {
        try {
            Field[] declaredFields = obj.getClass().getDeclaredFields();
            String[] strArr = new String[declaredFields.length];
            ContentValues contentValues = new ContentValues();
            for (int i = 0; i < declaredFields.length; i++) {
                contentValues.put(declaredFields[i].getName(), getFieldValueByName(declaredFields[i].getName(), obj).toString());
            }
            return contentValues;
        } catch (SecurityException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getFieldValueByName(String str, Object obj) {
        try {
            String upperCase = str.substring(0, 1).toUpperCase();
            return obj.getClass().getMethod("get" + upperCase + str.substring(1), new Class[0]).invoke(obj, new Object[0]);
        } catch (Exception unused) {
            Log.e("error", "属性名不存在");
            return null;
        }
    }
}
