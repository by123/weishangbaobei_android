package com.wx.assistants.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MoneyUtils {
    public static String changeF2Y(int i) {
        return BigDecimal.valueOf(Long.valueOf((long) i).longValue()).divide(new BigDecimal(100)).toString();
    }

    public static int changeY2F(double d) {
        return (int) (Double.valueOf(new DecimalFormat("#.00").format(d)).doubleValue() * 100.0d);
    }

    public static String differenceComputation(String str, String str2) {
        if ("".equals(str) || str == null || "".equals(str2) || str2 == null) {
            return "";
        }
        try {
            double parseDouble = Double.parseDouble(str);
            double parseDouble2 = Double.parseDouble(str2);
            return (parseDouble - parseDouble2) + "";
        } catch (Exception e) {
            return "";
        }
    }

    public static String removeDecimalPoints(String str) {
        if ("".equals(str) || str == null) {
            return "";
        }
        try {
            return str.endsWith(".0") ? str.substring(0, str.indexOf(".0")) : str.endsWith(".00") ? str.substring(0, str.indexOf(".00")) : str;
        } catch (Exception e) {
            return str;
        }
    }
}
