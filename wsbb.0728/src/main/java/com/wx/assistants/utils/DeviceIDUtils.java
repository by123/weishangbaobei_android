package com.wx.assistants.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.UUID;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DeviceIDUtils {
    private static final String FILE_PATH = "data/.cache/.1270f37f6c8";
    private static final String LOACL_DEVICE_ID = "my_device_loacl_device_id";
    private static final String LOACL_DEVICE_IMEI = "my_device_local_device_imei";
    private static final String LOACL_FILENAME_KEY = "loacl_filename_key";
    private static final String LOACL_IMEI = "my_device_local_imei";
    private static final String LOACL_MAC = "my_device_local_mac";
    private static final String LOACL_UUID = "my_device_localuuid";
    private static String MODE = "";
    private static String ModeCode = "PKCS5Padding";
    private static final String SHARED_PREFERENCES_NAME = "deviceid_cache";
    private static String WAYS = "AES";
    private static boolean isPwd = false;
    private static String ivParameter = "1234567890123456";
    private static final String key = "cch@1234sis9876~";
    private static Context mContext = null;
    private static int pwdLenght = 16;
    private static int type = 0;
    private static String val = "0";

    private enum AESType {
        ECB("ECB", "0"),
        CBC("CBC", "1"),
        CFB("CFB", "2"),
        OFB("OFB", "3");
        
        private String k;
        private String v;

        private AESType(String str, String str2) {
            this.k = str;
            this.v = str2;
        }

        public static AESType get(int i) {
            AESType[] values = values();
            for (AESType aESType : values) {
                if (aESType.key().equals(Integer.valueOf(i))) {
                    return aESType;
                }
            }
            return CBC;
        }

        public String key() {
            return this.k;
        }

        public String value() {
            return this.v;
        }
    }

    private static String decrypt(String str) {
        if (str == null) {
            return null;
        }
        try {
            return decrypt(str, key, 0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String decrypt(String str, String str2, int i) throws Exception {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(toMakekey(str2, pwdLenght, val).getBytes("ASCII"), WAYS);
            Cipher instance = Cipher.getInstance(selectMod(i));
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivParameter.getBytes());
            if (!isPwd) {
                instance.init(2, secretKeySpec);
            } else {
                instance.init(2, secretKeySpec, ivParameterSpec);
            }
            return new String(instance.doFinal(Base64.decode(str.getBytes(), 0)), "utf-8");
        } catch (Exception e) {
            return null;
        }
    }

    private static String encrypt(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new String(encrypt(str, key, 0));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] encrypt(String str, String str2, int i) throws Exception {
        String makekey = toMakekey(str2, pwdLenght, val);
        Cipher instance = Cipher.getInstance(selectMod(i));
        SecretKeySpec secretKeySpec = new SecretKeySpec(makekey.getBytes(), WAYS);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivParameter.getBytes());
        if (!isPwd) {
            instance.init(1, secretKeySpec);
        } else {
            instance.init(1, secretKeySpec, ivParameterSpec);
        }
        return Base64.encode(instance.doFinal(str.getBytes("utf-8")), 0);
    }

    public static String getDeviceId(Context context) {
        mContext = context;
        getDiviceInfoIMEI();
        String localDeviceId = getLocalDeviceId();
        if (TextUtils.isEmpty(localDeviceId)) {
            localDeviceId = getimei();
            if (TextUtils.isEmpty(localDeviceId)) {
                localDeviceId = getMacid();
            }
            if (TextUtils.isEmpty(localDeviceId)) {
                localDeviceId = getDiviceInfoIMEI();
            }
            if (TextUtils.isEmpty(localDeviceId)) {
                localDeviceId = getLocalUUID();
            }
            Log.d("DeviceIDUtils", "return diviceid:" + localDeviceId);
            saveDeviceId(localDeviceId);
        }
        return localDeviceId;
    }

    public static String getDiviceInfoIMEI() {
        String savaString = getSavaString(LOACL_DEVICE_IMEI, "");
        if (TextUtils.isEmpty(savaString)) {
            savaString = Settings.Secure.getString(mContext.getContentResolver(), SocializeProtocolConstants.PROTOCOL_KEY_ANDROID_ID) + Build.SERIAL;
            LogUtils.log("WS_BABY_" + savaString);
            if (!TextUtils.isEmpty(savaString)) {
                savaString(LOACL_DEVICE_IMEI, savaString);
            }
        }
        return savaString;
    }

    private static String getFileName() {
        String savaString = getSavaString(LOACL_FILENAME_KEY, "");
        if (TextUtils.isEmpty(savaString)) {
            String replace = UUID.randomUUID().toString().replace("-", "");
            savaString(LOACL_FILENAME_KEY, replace);
            Log.d("DeviceIDUtils", "getFileName:" + replace);
            return replace;
        }
        Log.d("DeviceIDUtils", "getFileName:" + savaString);
        return savaString;
    }

    private static String getLocalDeviceId() {
        String savaString = getSavaString(LOACL_DEVICE_ID, "");
        if (!TextUtils.isEmpty(savaString)) {
            return savaString;
        }
        String readSDFile = readSDFile();
        if (TextUtils.isEmpty(readSDFile)) {
            return null;
        }
        savaString(LOACL_DEVICE_ID, readSDFile);
        return readSDFile;
    }

    private static String getLocalUUID() {
        String savaString = getSavaString(LOACL_UUID, "");
        if (!TextUtils.isEmpty(savaString)) {
            return savaString;
        }
        String replace = UUID.randomUUID().toString().replace("-", "");
        savaString(LOACL_UUID, replace);
        return replace;
    }

    private static String getMac60() {
        String mac60_1 = getMac60_1();
        return (TextUtils.isEmpty(mac60_1) || "02:00:00:00:00:00".equals(mac60_1)) ? getMac60_2() : mac60_1;
    }

    private static String getMac60_1() {
        String str;
        String str2 = "";
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ").getInputStream()));
            while (true) {
                if (str2 != null) {
                    str2 = lineNumberReader.readLine();
                    if (str2 != null) {
                        str = str2.trim();
                        break;
                    }
                } else {
                    str = "";
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            str = "";
        }
        if (str == null || "".equals(str)) {
            try {
                return loadFileAsString("/sys/class/net/eth0/address").toUpperCase().substring(0, 17);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return !TextUtils.isEmpty(str) ? str.toLowerCase() : str;
    }

    private static String getMac60_2() {
        try {
            for (T t : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (t.getName().equalsIgnoreCase("wlan0")) {
                    byte[] hardwareAddress = t.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "";
                    }
                    StringBuilder sb = new StringBuilder();
                    int length = hardwareAddress.length;
                    for (int i = 0; i < length; i++) {
                        sb.append(String.format("%02X:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    String sb2 = sb.toString();
                    return !TextUtils.isEmpty(sb2) ? sb2.toLowerCase() : sb2;
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    private static String getMacid() {
        String savaString = getSavaString(LOACL_MAC, "");
        if (TextUtils.isEmpty(savaString)) {
            if (Build.VERSION.SDK_INT >= 23) {
                savaString = getMac60();
            } else {
                savaString = ((WifiManager) mContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
                if (TextUtils.isEmpty(savaString) || "02:00:00:00:00:00".equals(savaString)) {
                    savaString = getMac60();
                }
            }
            if ("02:00:00:00:00:00".equals(savaString)) {
                savaString = null;
            }
            if (!TextUtils.isEmpty(savaString)) {
                savaString(LOACL_MAC, savaString);
            }
        }
        return savaString;
    }

    private static String getSavaString(String str, String str2) {
        return mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).getString(str, str2);
    }

    @SuppressLint({"MissingPermission"})
    private static String getimei() {
        String savaString = getSavaString(LOACL_IMEI, "");
        if (!TextUtils.isEmpty(savaString)) {
            return savaString;
        }
        try {
            String deviceId = ((TelephonyManager) mContext.getSystemService("phone")).getDeviceId();
            if (TextUtils.isEmpty(deviceId)) {
                return deviceId;
            }
            savaString(LOACL_IMEI, deviceId);
            return deviceId;
        } catch (Exception e) {
            return null;
        }
    }

    private static String loadFileAsString(String str) throws Exception {
        FileReader fileReader = new FileReader(str);
        String loadReaderAsString = loadReaderAsString(fileReader);
        fileReader.close();
        return loadReaderAsString;
    }

    private static String loadReaderAsString(Reader reader) throws Exception {
        StringBuilder sb = new StringBuilder();
        char[] cArr = new char[4096];
        int read = reader.read(cArr);
        while (read >= 0) {
            sb.append(cArr, 0, read);
            read = reader.read(cArr);
        }
        return sb.toString();
    }

    private static String readSDFile() {
        String str;
        try {
            if (Environment.getExternalStorageState().equals("mounted")) {
                str = Environment.getExternalStorageDirectory().toString() + File.separator + FILE_PATH + File.separator + getFileName();
            } else {
                str = Environment.getDownloadCacheDirectory().toString() + File.separator + FILE_PATH + File.separator + getFileName();
            }
            FileInputStream fileInputStream = new FileInputStream(new File(str));
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            String str2 = new String(bArr, "utf-8");
            fileInputStream.close();
            Log.d("DeviceIDUtils", "readSDFile filePath:" + str);
            Log.d("DeviceIDUtils", "readSDFile res:" + str2);
            return decrypt(str2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static void savaString(String str, String str2) {
        mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).edit().putString(str, str2).commit();
    }

    private static void saveDeviceId(String str) {
        savaString(LOACL_DEVICE_ID, str);
        saveFile(encrypt(str));
    }

    private static void saveFile(String str) {
        String str2;
        if (Environment.getExternalStorageState().equals("mounted")) {
            str2 = Environment.getExternalStorageDirectory().toString() + File.separator + FILE_PATH + File.separator + getFileName();
        } else {
            str2 = Environment.getDownloadCacheDirectory().toString() + File.separator + FILE_PATH + File.separator + getFileName();
        }
        try {
            File file = new File(str2);
            if (!file.exists()) {
                Log.d("DeviceIDUtils", "saveFile filePath:" + str2);
                new File(file.getParent()).mkdirs();
                file.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(str.getBytes());
                fileOutputStream.close();
                return;
            }
            Log.d("DeviceIDUtils", "saveFile 文件已存在:" + str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String selectMod(int i) {
        switch (i) {
            case 0:
                isPwd = false;
                MODE = WAYS + "/" + AESType.ECB.key() + "/" + ModeCode;
                break;
            case 1:
                isPwd = true;
                MODE = WAYS + "/" + AESType.CBC.key() + "/" + ModeCode;
                break;
            case 2:
                isPwd = true;
                MODE = WAYS + "/" + AESType.CFB.key() + "/" + ModeCode;
                break;
            case 3:
                isPwd = true;
                MODE = WAYS + "/" + AESType.OFB.key() + "/" + ModeCode;
                break;
        }
        return MODE;
    }

    private static String toMakekey(String str, int i, String str2) {
        int length = str.length();
        if (length < i) {
            while (length < i) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(str);
                stringBuffer.append(str2);
                str = stringBuffer.toString();
                length = str.length();
            }
        }
        return str;
    }
}
