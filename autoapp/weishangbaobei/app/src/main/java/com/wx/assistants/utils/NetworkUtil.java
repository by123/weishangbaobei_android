package com.wx.assistants.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class NetworkUtil {
    public static final int MOBILE = 2;
    public static final int NONE_NET = -1;
    public static final int WIFI = 1;

    public static boolean isAvailable(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
    }

    public static int getNetType(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected() || activeNetworkInfo.getState() != NetworkInfo.State.CONNECTED) {
                return -1;
            }
            return activeNetworkInfo.getType() == 1 ? 1 : 2;
        } catch (Exception unused) {
            return -1;
        }
    }

    public static boolean isWIFIActivate(Context context) {
        return ((WifiManager) context.getSystemService("wifi")).isWifiEnabled();
    }

    public static String get3GIp() {
        InetAddress nextElement;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            loop0:
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (true) {
                    if (inetAddresses.hasMoreElements()) {
                        nextElement = inetAddresses.nextElement();
                        if (nextElement.isLoopbackAddress() || (!(nextElement instanceof Inet4Address) && !(nextElement instanceof Inet6Address))) {
                        }
                    }
                }
                return nextElement.getHostAddress().toString();
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
