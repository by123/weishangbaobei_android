package ezy.assist.compat;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RomUtil {
    private static final String KEY_EMUI_VERSION_CODE = "ro.build.hw_emui_api_level";
    private static final String KEY_FLYME_FLYME = "ro.meizu.setupwizard.flyme";
    private static final String KEY_FLYME_ICON_FALG = "persist.sys.use.flyme.icon";
    private static final String KEY_FLYME_PUBLISHED = "ro.flyme.published";
    private static final String KEY_FLYME_PUBLISH_FALG = "ro.flyme.published";
    private static final String KEY_FLYME_SETUP_FALG = "ro.meizu.setupwizard.flyme";
    private static final String KEY_MIUI_HANDY_MODE_SF = "ro.miui.has_handy_mode_sf";
    private static final String KEY_MIUI_REAL_BLUR = "ro.miui.has_real_blur";
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_VERSION_EMUI = "ro.build.version.emui";
    private static final String KEY_VERSION_FLYME = "ro.build.display.id";
    private static final String KEY_VERSION_GIONEE = "ro.gn.sv.version";
    private static final String KEY_VERSION_LENOVO = "ro.lenovo.lvp.version";
    private static final String KEY_VERSION_MIUI = "ro.miui.ui.version.name";
    private static final String KEY_VERSION_OPPO = "ro.build.version.opporom";
    private static final String KEY_VERSION_SMARTISAN = "ro.smartisan.version";
    private static final String KEY_VERSION_VIVO = "ro.vivo.os.version";
    private static final String KEY_VIVO_OS_NAME = "ro.vivo.os.name";
    private static final String KEY_VIVO_OS_VERSION = "ro.vivo.os.version";
    private static final String KEY_VIVO_ROM_VERSION = "ro.vivo.rom.version";
    public static final String ROM_EMUI = "EMUI";
    public static final String ROM_FLYME = "FLYME";
    public static final String ROM_LENOVO = "LENOVO";
    public static final String ROM_MIUI = "MIUI";
    public static final String ROM_OPPO = "OPPO";
    public static final String ROM_QIKU = "QIKU";
    public static final String ROM_SAMSUNG = "SAMSUNG";
    public static final String ROM_SMARTISAN = "SMARTISAN";
    public static final String ROM_VIVO = "VIVO";
    private static final String TAG = "RomUtil";
    private static String sName;
    private static String sVersion;

    public static boolean check(String str) {
        if (sName != null) {
            return sName.equals(str);
        }
        String prop = getProp(KEY_VERSION_MIUI);
        sVersion = prop;
        if (!TextUtils.isEmpty(prop)) {
            sName = ROM_MIUI;
        } else {
            String prop2 = getProp(KEY_VERSION_EMUI);
            sVersion = prop2;
            if (!TextUtils.isEmpty(prop2)) {
                sName = ROM_EMUI;
            } else {
                String prop3 = getProp(KEY_VERSION_OPPO);
                sVersion = prop3;
                if (!TextUtils.isEmpty(prop3)) {
                    sName = ROM_OPPO;
                } else {
                    String prop4 = getProp("ro.vivo.os.version");
                    sVersion = prop4;
                    if (!TextUtils.isEmpty(prop4)) {
                        sName = ROM_VIVO;
                    } else {
                        String prop5 = getProp(KEY_VERSION_SMARTISAN);
                        sVersion = prop5;
                        if (!TextUtils.isEmpty(prop5)) {
                            sName = ROM_SMARTISAN;
                        } else {
                            sVersion = Build.DISPLAY;
                            if (sVersion.toUpperCase().contains(ROM_FLYME)) {
                                sName = ROM_FLYME;
                            } else {
                                sVersion = "unknown";
                                sName = Build.MANUFACTURER.toUpperCase();
                            }
                        }
                    }
                }
            }
        }
        return sName.equals(str);
    }

    public static String getName() {
        if (sName == null) {
            check("");
        }
        return sName;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0056 A[SYNTHETIC, Splitter:B:15:0x0056] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0063 A[SYNTHETIC, Splitter:B:22:0x0063] */
    /* JADX WARNING: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    public static String getProp(String str) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()), WXMediaMessage.DESCRIPTION_LENGTH_LIMIT);
            try {
                String readLine = bufferedReader.readLine();
                bufferedReader.close();
                try {
                    bufferedReader.close();
                    return readLine;
                } catch (IOException e) {
                    e.printStackTrace();
                    return readLine;
                }
            } catch (IOException e2) {
                e = e2;
                try {
                    Log.e(TAG, "Unable to read prop " + str, e);
                    if (bufferedReader != null) {
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                    }
                    throw th;
                }
            }
        } catch (IOException e3) {
            e = e3;
            bufferedReader = null;
            Log.e(TAG, "Unable to read prop " + str, e);
            if (bufferedReader != null) {
                return null;
            }
            try {
                bufferedReader.close();
                return null;
            } catch (IOException e4) {
                e4.printStackTrace();
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static String getVersion() {
        if (sVersion == null) {
            check("");
        }
        return sVersion;
    }

    public static boolean isEmui() {
        return check(ROM_EMUI);
    }

    public static boolean isFlyme() {
        return check(ROM_FLYME);
    }

    public static boolean isMiui() {
        return check(ROM_MIUI);
    }

    public static boolean isOppo() {
        return check(ROM_OPPO);
    }

    public static boolean isQiku() {
        return check(ROM_QIKU) || check("360");
    }

    public static boolean isSmartisan() {
        return check(ROM_SMARTISAN);
    }

    public static boolean isVivo() {
        return check(ROM_VIVO);
    }
}
