package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.proguard.e;
import com.umeng.commonsdk.proguard.f;
import com.umeng.commonsdk.proguard.s;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.b;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMUtils;
import java.io.File;
import org.json.JSONObject;

public class Envelope {
    private final int CODEX_ENCRYPT = 1;
    private final int CODEX_NORMAL = 0;
    private final byte[] SEED = {0, 0, 0, 0, 0, 0, 0, 0};
    private boolean encrypt = false;
    private byte[] identity = null;
    private String mAddress = null;
    private byte[] mChecksum = null;
    private byte[] mEntity = null;
    private byte[] mGuid = null;
    private int mLength = 0;
    private int mSerialNo = 0;
    private byte[] mSignature = null;
    private int mTimestamp = 0;
    private String mVersion = "1.0";

    private Envelope(byte[] bArr, String str, byte[] bArr2) throws Exception {
        if (bArr == null || bArr.length == 0) {
            throw new Exception("entity is null or empty");
        }
        this.mAddress = str;
        this.mLength = bArr.length;
        this.mEntity = b.a(bArr);
        this.mTimestamp = (int) (System.currentTimeMillis() / 1000);
        this.identity = bArr2;
    }

    public static String getSignature(Context context) {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
        if (sharedPreferences == null) {
            return null;
        }
        return sharedPreferences.getString("signature", (String) null);
    }

    public void setSignature(String str) {
        this.mSignature = DataHelper.reverseHexString(str);
    }

    public String getSignature() {
        return DataHelper.toHexString(this.mSignature);
    }

    public void setSerialNumber(int i) {
        this.mSerialNo = i;
    }

    public void setEncrypt(boolean z) {
        this.encrypt = z;
    }

    public static Envelope genEnvelope(Context context, String str, byte[] bArr) {
        try {
            String mac = DeviceConfig.getMac(context);
            String deviceId = DeviceConfig.getDeviceId(context);
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            String string = sharedPreferences.getString("signature", (String) null);
            int i = sharedPreferences.getInt("serial", 1);
            Envelope envelope = new Envelope(bArr, str, (deviceId + mac).getBytes());
            envelope.setSignature(string);
            envelope.setSerialNumber(i);
            envelope.seal();
            sharedPreferences.edit().putInt("serial", i + 1).putString("signature", envelope.getSignature()).commit();
            envelope.export(context);
            return envelope;
        } catch (Exception e) {
            UMCrashManager.reportCrash(context, e);
            return null;
        }
    }

    public static Envelope genEncryptEnvelope(Context context, String str, byte[] bArr) {
        try {
            String mac = DeviceConfig.getMac(context);
            String deviceId = DeviceConfig.getDeviceId(context);
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            String string = sharedPreferences.getString("signature", (String) null);
            int i = sharedPreferences.getInt("serial", 1);
            Envelope envelope = new Envelope(bArr, str, (deviceId + mac).getBytes());
            envelope.setEncrypt(true);
            envelope.setSignature(string);
            envelope.setSerialNumber(i);
            envelope.seal();
            sharedPreferences.edit().putInt("serial", i + 1).putString("signature", envelope.getSignature()).commit();
            envelope.export(context);
            return envelope;
        } catch (Exception e) {
            UMCrashManager.reportCrash(context, e);
            return null;
        }
    }

    public void seal() {
        if (this.mSignature == null) {
            this.mSignature = genSignature();
        }
        if (this.encrypt) {
            byte[] bArr = new byte[16];
            try {
                System.arraycopy(this.mSignature, 1, bArr, 0, 16);
                this.mEntity = DataHelper.encrypt(this.mEntity, bArr);
            } catch (Exception unused) {
            }
        }
        this.mGuid = genGuid(this.mSignature, this.mTimestamp);
        this.mChecksum = genCheckSum();
    }

    private byte[] genGuid(byte[] bArr, int i) {
        byte[] hash = DataHelper.hash(this.identity);
        byte[] hash2 = DataHelper.hash(this.mEntity);
        int length = hash.length;
        byte[] bArr2 = new byte[(length * 2)];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            bArr2[i3] = hash2[i2];
            bArr2[i3 + 1] = hash[i2];
        }
        for (int i4 = 0; i4 < 2; i4++) {
            bArr2[i4] = bArr[i4];
            bArr2[(bArr2.length - i4) - 1] = bArr[(bArr.length - i4) - 1];
        }
        byte[] bArr3 = {(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) (i >>> 24)};
        for (int i5 = 0; i5 < bArr2.length; i5++) {
            bArr2[i5] = (byte) (bArr2[i5] ^ bArr3[i5 % 4]);
        }
        return bArr2;
    }

    private byte[] genSignature() {
        return genGuid(this.SEED, (int) (System.currentTimeMillis() / 1000));
    }

    private byte[] genCheckSum() {
        return DataHelper.hash((DataHelper.toHexString(this.mSignature) + this.mSerialNo + this.mTimestamp + this.mLength + DataHelper.toHexString(this.mGuid)).getBytes());
    }

    public byte[] toBinary() {
        f fVar = new f();
        fVar.a(this.mVersion);
        fVar.b(this.mAddress);
        fVar.c(DataHelper.toHexString(this.mSignature));
        fVar.a(this.mSerialNo);
        fVar.b(this.mTimestamp);
        fVar.c(this.mLength);
        fVar.a(this.mEntity);
        fVar.d(this.encrypt ? 1 : 0);
        fVar.d(DataHelper.toHexString(this.mGuid));
        fVar.e(DataHelper.toHexString(this.mChecksum));
        try {
            return new s().a(fVar);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void export(Context context) {
        String str = this.mAddress;
        String imprintProperty = UMEnvelopeBuild.imprintProperty(context, e.f, (String) null);
        String hexString = DataHelper.toHexString(this.mSignature);
        byte[] bArr = new byte[16];
        System.arraycopy(this.mSignature, 2, bArr, 0, 16);
        String hexString2 = DataHelper.toHexString(DataHelper.hash(bArr));
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appkey", str);
            if (imprintProperty != null) {
                jSONObject.put(e.f, imprintProperty);
            }
            jSONObject.put("signature", hexString);
            jSONObject.put("checksum", hexString2);
            File file = new File(context.getFilesDir(), ".umeng");
            if (!file.exists()) {
                file.mkdir();
            }
            HelperUtils.writeFile(new File(file, "exchangeIdentity.json"), jSONObject.toString());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("appkey", str);
            jSONObject2.put("channel", UMUtils.getChannel(context));
            if (imprintProperty != null) {
                jSONObject2.put(e.f, HelperUtils.getUmengMD5(imprintProperty));
            }
            HelperUtils.writeFile(new File(context.getFilesDir(), "exid.dat"), jSONObject2.toString());
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    public String toString() {
        return String.format("version : %s\n", new Object[]{this.mVersion}) + String.format("address : %s\n", new Object[]{this.mAddress}) + String.format("signature : %s\n", new Object[]{DataHelper.toHexString(this.mSignature)}) + String.format("serial : %s\n", new Object[]{Integer.valueOf(this.mSerialNo)}) + String.format("timestamp : %d\n", new Object[]{Integer.valueOf(this.mTimestamp)}) + String.format("length : %d\n", new Object[]{Integer.valueOf(this.mLength)}) + String.format("guid : %s\n", new Object[]{DataHelper.toHexString(this.mGuid)}) + String.format("checksum : %s ", new Object[]{DataHelper.toHexString(this.mChecksum)}) + String.format("codex : %d", new Object[]{Integer.valueOf(this.encrypt ? 1 : 0)});
    }
}
