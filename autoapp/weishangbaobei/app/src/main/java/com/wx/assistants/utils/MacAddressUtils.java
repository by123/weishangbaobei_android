package com.wx.assistants.utils;

import android.content.Context;
import com.blankj.utilcode.util.DeviceUtils;

public class MacAddressUtils {
    public static String getMacAddress(Context context) {
        try {
            String str = (String) SPUtils.get(context, "iphone_mac_address", "");
            if (str != null && !"".equals(str)) {
                return str;
            }
            String macAddress = DeviceUtils.getMacAddress();
            if (macAddress == null || "".equals(macAddress)) {
                macAddress = DeviceIDUtils.getDeviceId(context);
            }
            SPUtils.put(context, "iphone_mac_address", macAddress);
            return macAddress;
        } catch (Exception unused) {
            String macAddress2 = DeviceUtils.getMacAddress();
            return (macAddress2 == null || "".equals(macAddress2)) ? DeviceIDUtils.getDeviceId(context) : macAddress2;
        }
    }
}
