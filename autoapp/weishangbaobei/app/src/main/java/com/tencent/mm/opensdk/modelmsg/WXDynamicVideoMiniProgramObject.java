package com.tencent.mm.opensdk.modelmsg;

import android.os.Bundle;
import com.tencent.mm.opensdk.utils.Log;
import com.tencent.mm.opensdk.utils.d;

public class WXDynamicVideoMiniProgramObject extends WXMiniProgramObject {
    private static final String TAG = "MicroMsg.SDK.WXDynamicVideoMiniProgramObject";
    public String appThumbUrl;
    public String videoSource;

    public boolean checkArgs() {
        String str;
        String str2;
        if (d.i(this.webpageUrl)) {
            str = TAG;
            str2 = "webPageUrl is null";
        } else if (d.i(this.userName)) {
            str = TAG;
            str2 = "userName is null";
        } else if (this.miniprogramType >= 0 && this.miniprogramType <= 2) {
            return true;
        } else {
            str = TAG;
            str2 = "miniprogram type should between MINIPTOGRAM_TYPE_RELEASE and MINIPROGRAM_TYPE_PREVIEW";
        }
        Log.e(str, str2);
        return false;
    }

    public void serialize(Bundle bundle) {
        bundle.putString("_wxminiprogram_webpageurl", this.webpageUrl);
        bundle.putString("_wxminiprogram_username", this.userName);
        bundle.putString("_wxminiprogram_path", this.path);
        bundle.putString("_wxminiprogram_videoSource", this.videoSource);
        bundle.putString("_wxminiprogram_appThumbUrl", this.appThumbUrl);
        bundle.putBoolean("_wxminiprogram_withsharetiket", this.withShareTicket);
        bundle.putInt("_wxminiprogram_type", this.miniprogramType);
    }

    public int type() {
        return 46;
    }

    public void unserialize(Bundle bundle) {
        this.webpageUrl = bundle.getString("_wxminiprogram_webpageurl");
        this.userName = bundle.getString("_wxminiprogram_username");
        this.path = bundle.getString("_wxminiprogram_path");
        this.videoSource = bundle.getString("_wxminiprogram_videoSource");
        this.appThumbUrl = bundle.getString("_wxminiprogram_appThumbUrl");
        this.withShareTicket = bundle.getBoolean("_wxminiprogram_withsharetiket");
        this.miniprogramType = bundle.getInt("_wxminiprogram_type");
    }
}
