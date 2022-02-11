package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.os.Environment;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class r extends a {
    private static final String a = "utdid";
    private static final String b = "android.permission.WRITE_EXTERNAL_STORAGE";
    private static final Pattern c = Pattern.compile("UTDID\">([^<]+)");
    private Context d;

    public r(Context context) {
        super(a);
        this.d = context;
    }

    private String b(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = c.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private String g() {
        FileInputStream fileInputStream;
        File h = h();
        if (h == null || !h.exists()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(h);
            String b2 = b(HelperUtils.readStreamToString(fileInputStream));
            HelperUtils.safeClose((InputStream) fileInputStream);
            return b2;
        } catch (Exception e) {
            return null;
        } catch (Throwable th) {
            HelperUtils.safeClose((InputStream) fileInputStream);
            throw th;
        }
    }

    private File h() {
        if (!DeviceConfig.checkPermission(this.d, b) || !Environment.getExternalStorageState().equals("mounted")) {
            return null;
        }
        try {
            return new File(Environment.getExternalStorageDirectory().getCanonicalPath(), ".UTSystemConfig/Global/Alvin2.xml");
        } catch (Exception e) {
            return null;
        }
    }

    public String f() {
        try {
            return (String) Class.forName("com.ut.device.UTDevice").getMethod("getUtdid", new Class[]{Context.class}).invoke((Object) null, new Object[]{this.d});
        } catch (Exception e) {
            return g();
        }
    }
}
