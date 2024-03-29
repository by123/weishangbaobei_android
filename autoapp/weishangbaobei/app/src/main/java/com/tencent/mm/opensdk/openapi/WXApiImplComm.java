package com.tencent.mm.opensdk.openapi;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import com.tencent.mm.opensdk.utils.Log;

class WXApiImplComm {
    private static final String TAG = "MicroMsg.SDK.WXMsgImplComm";
    private static final String WX_APP_SIGNATURE = "308202eb30820254a00302010202044d36f7a4300d06092a864886f70d01010505003081b9310b300906035504061302383631123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e31353033060355040a132c54656e63656e7420546563686e6f6c6f6779285368656e7a68656e2920436f6d70616e79204c696d69746564313a3038060355040b133154656e63656e74204775616e677a686f7520526573656172636820616e6420446576656c6f706d656e742043656e7465723110300e0603550403130754656e63656e74301e170d3131303131393134333933325a170d3431303131313134333933325a3081b9310b300906035504061302383631123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e31353033060355040a132c54656e63656e7420546563686e6f6c6f6779285368656e7a68656e2920436f6d70616e79204c696d69746564313a3038060355040b133154656e63656e74204775616e677a686f7520526573656172636820616e6420446576656c6f706d656e742043656e7465723110300e0603550403130754656e63656e7430819f300d06092a864886f70d010101050003818d0030818902818100c05f34b231b083fb1323670bfbe7bdab40c0c0a6efc87ef2072a1ff0d60cc67c8edb0d0847f210bea6cbfaa241be70c86daf56be08b723c859e52428a064555d80db448cdcacc1aea2501eba06f8bad12a4fa49d85cacd7abeb68945a5cb5e061629b52e3254c373550ee4e40cb7c8ae6f7a8151ccd8df582d446f39ae0c5e930203010001300d06092a864886f70d0101050500038181009c8d9d7f2f908c42081b4c764c377109a8b2c70582422125ce545842d5f520aea69550b6bd8bfd94e987b75a3077eb04ad341f481aac266e89d3864456e69fba13df018acdc168b9a19dfd7ad9d9cc6f6ace57c746515f71234df3a053e33ba93ece5cd0fc15f3e389a3f365588a9fcb439e069d3629cd7732a13fff7b891499";

    private WXApiImplComm() {
        throw new RuntimeException(getClass().getSimpleName() + " should not be instantiated");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0004, code lost:
        r2 = r2.getStringExtra(com.tencent.mm.opensdk.constants.ConstantsAPI.Token.WX_TOKEN_KEY);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isIntentFromWx(android.content.Intent r2, java.lang.String r3) {
        /*
            r0 = 0
            if (r2 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r1 = "wx_token_key"
            java.lang.String r2 = r2.getStringExtra(r1)
            if (r2 == 0) goto L_0x0015
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0013
            goto L_0x0015
        L_0x0013:
            r2 = 1
            return r2
        L_0x0015:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mm.opensdk.openapi.WXApiImplComm.isIntentFromWx(android.content.Intent, java.lang.String):boolean");
    }

    public static boolean validateAppSignature(Context context, Signature[] signatureArr, boolean z) {
        String str;
        String str2;
        if (!z) {
            str = TAG;
            str2 = "ignore wechat app signature validation";
        } else {
            int length = signatureArr.length;
            int i = 0;
            while (i < length) {
                String lowerCase = signatureArr[i].toCharsString().toLowerCase();
                Log.d(TAG, "check signature:" + lowerCase);
                if (lowerCase.equals(WX_APP_SIGNATURE)) {
                    str = TAG;
                    str2 = "pass";
                } else {
                    i++;
                }
            }
            return false;
        }
        Log.d(str, str2);
        return true;
    }

    public static boolean validateAppSignatureForPackage(Context context, String str, boolean z) {
        if (!z) {
            Log.d(TAG, "ignore wechat app signature validation");
            return true;
        }
        try {
            return validateAppSignature(context, context.getPackageManager().getPackageInfo(str, 64).signatures, z);
        } catch (PackageManager.NameNotFoundException | Exception unused) {
            return false;
        }
    }
}
