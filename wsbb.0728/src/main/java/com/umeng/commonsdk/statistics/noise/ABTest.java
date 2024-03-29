package com.umeng.commonsdk.statistics.noise;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.idtracking.Envelope;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.statistics.internal.d;
import com.wx.assistants.utils.fileutil.ListUtils;
import com.yalantis.ucrop.view.CropImageView;

public class ABTest implements d {
    private static ABTest instance;
    private Context context = null;
    private boolean isInTest = false;
    private int mGroup = -1;
    private int mInterval = -1;
    private String mPoli = null;
    private int mPolicy = -1;
    private float mProb07 = CropImageView.DEFAULT_ASPECT_RATIO;
    private float mProb13 = CropImageView.DEFAULT_ASPECT_RATIO;

    private ABTest(Context context2, String str, int i) {
        this.context = context2;
        onExperimentChanged(str, i);
    }

    public static ABTest getService(Context context2) {
        ABTest aBTest;
        synchronized (ABTest.class) {
            try {
                if (instance == null) {
                    instance = new ABTest(context2, UMEnvelopeBuild.imprintProperty(context2, "client_test", (String) null), Integer.valueOf(UMEnvelopeBuild.imprintProperty(context2, "test_report_interval", "0")).intValue());
                }
                aBTest = instance;
            } catch (Throwable th) {
                Class<ABTest> cls = ABTest.class;
                throw th;
            }
        }
        return aBTest;
    }

    private void parseFIXED(String str) {
        if (str != null) {
            String[] split = str.split("\\|");
            float f = CropImageView.DEFAULT_ASPECT_RATIO;
            if (split[2].equals("SIG13")) {
                f = Float.valueOf(split[3]).floatValue();
            }
            if (this.mProb13 > f) {
                this.isInTest = false;
                return;
            }
            int intValue = split[0].equals("FIXED") ? Integer.valueOf(split[1]).intValue() : -1;
            int[] iArr = null;
            if (split[4].equals("RPT")) {
                this.mPoli = "RPT";
                String[] split2 = split[5].split(ListUtils.DEFAULT_JOIN_SEPARATOR);
                int[] iArr2 = new int[split2.length];
                for (int i = 0; i < split2.length; i++) {
                    iArr2[i] = Integer.valueOf(split2[i]).intValue();
                }
                iArr = iArr2;
            } else if (split[4].equals("DOM")) {
                this.mPoli = "DOM";
                this.isInTest = true;
                try {
                    String[] split3 = split[5].split(ListUtils.DEFAULT_JOIN_SEPARATOR);
                    iArr = new int[split3.length];
                    int i2 = 0;
                    while (i2 < split3.length) {
                        try {
                            iArr[i2] = Integer.valueOf(split3[i2]).intValue();
                            i2++;
                        } catch (Exception e) {
                        }
                    }
                } catch (Exception e2) {
                }
            }
            if (intValue != -1) {
                this.isInTest = true;
                this.mGroup = intValue;
                if (iArr != null) {
                    this.mPolicy = iArr[intValue - 1];
                    return;
                }
                return;
            }
            this.isInTest = false;
        }
    }

    private void parseSig7(String str) {
        float[] fArr;
        int[] iArr;
        if (str != null) {
            String[] split = str.split("\\|");
            if (this.mProb13 > (split[2].equals("SIG13") ? Float.valueOf(split[3]).floatValue() : 0.0f)) {
                this.isInTest = false;
                return;
            }
            if (split[0].equals("SIG7")) {
                String[] split2 = split[1].split(ListUtils.DEFAULT_JOIN_SEPARATOR);
                float[] fArr2 = new float[split2.length];
                for (int i = 0; i < split2.length; i++) {
                    fArr2[i] = Float.valueOf(split2[i]).floatValue();
                }
                fArr = fArr2;
            } else {
                fArr = null;
            }
            if (split[4].equals("RPT")) {
                this.mPoli = "RPT";
                String[] split3 = split[5].split(ListUtils.DEFAULT_JOIN_SEPARATOR);
                int[] iArr2 = new int[split3.length];
                for (int i2 = 0; i2 < split3.length; i2++) {
                    iArr2[i2] = Integer.valueOf(split3[i2]).intValue();
                }
                iArr = iArr2;
            } else if (split[4].equals("DOM")) {
                this.isInTest = true;
                this.mPoli = "DOM";
                try {
                    String[] split4 = split[5].split(ListUtils.DEFAULT_JOIN_SEPARATOR);
                    iArr = new int[split4.length];
                    int i3 = 0;
                    while (i3 < split4.length) {
                        try {
                            iArr[i3] = Integer.valueOf(split4[i3]).intValue();
                            i3++;
                        } catch (Exception e) {
                        }
                    }
                } catch (Exception e2) {
                    iArr = null;
                }
            } else {
                iArr = null;
            }
            int i4 = 0;
            float f = 0.0f;
            while (true) {
                if (i4 >= fArr.length) {
                    i4 = -1;
                    break;
                }
                f += fArr[i4];
                if (this.mProb07 < f) {
                    break;
                }
                i4++;
            }
            if (i4 != -1) {
                this.isInTest = true;
                this.mGroup = i4 + 1;
                if (iArr != null) {
                    this.mPolicy = iArr[i4];
                    return;
                }
                return;
            }
            this.isInTest = false;
        }
    }

    private float prob(String str, int i) {
        int i2 = i * 2;
        return str == null ? CropImageView.DEFAULT_ASPECT_RATIO : ((float) Integer.valueOf(str.substring(i2, i2 + 5), 16).intValue()) / 1048576.0f;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003e, code lost:
        r3 = r2[5].split(com.wx.assistants.utils.fileutil.ListUtils.DEFAULT_JOIN_SEPARATOR).length;
        r2 = java.lang.Integer.parseInt(r2[1]);
     */
    public static boolean validate(String str) {
        int length;
        int parseInt;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] split = str.split("\\|");
        if (split.length != 6) {
            return false;
        }
        if (!split[0].startsWith("SIG7") || split[1].split(ListUtils.DEFAULT_JOIN_SEPARATOR).length != split[5].split(ListUtils.DEFAULT_JOIN_SEPARATOR).length) {
            return split[0].startsWith("FIXED") && length >= parseInt && parseInt >= 1;
        }
        return true;
    }

    public int getGroup() {
        return this.mGroup;
    }

    public String getGroupInfo() {
        return !this.isInTest ? "error" : String.valueOf(this.mGroup);
    }

    public int getTestInterval() {
        return this.mInterval;
    }

    public String getTestName() {
        return this.mPoli;
    }

    public int getTestPolicy() {
        return this.mPolicy;
    }

    public boolean isInTest() {
        return this.isInTest;
    }

    public void onExperimentChanged(String str, int i) {
        this.mInterval = i;
        String signature = Envelope.getSignature(this.context);
        if (TextUtils.isEmpty(signature) || TextUtils.isEmpty(str)) {
            this.isInTest = false;
            return;
        }
        try {
            this.mProb13 = prob(signature, 12);
            this.mProb07 = prob(signature, 6);
            if (str.startsWith("SIG7")) {
                parseSig7(str);
            } else if (str.startsWith("FIXED")) {
                parseFIXED(str);
            }
        } catch (Exception e) {
            this.isInTest = false;
            MLog.e("v:" + str, (Throwable) e);
        }
    }

    public void onImprintChanged(ImprintHandler.a aVar) {
        onExperimentChanged(aVar.a("client_test", (String) null), Integer.valueOf(aVar.a("test_report_interval", "0")).intValue());
    }

    public String toString() {
        return " p13:" + this.mProb13 + " p07:" + this.mProb07 + " policy:" + this.mPolicy + " interval:" + this.mInterval;
    }
}
