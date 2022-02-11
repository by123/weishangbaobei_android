package com.umeng.socialize.net;

import android.content.Context;
import com.umeng.socialize.Config;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.net.base.SocializeRequest;
import com.umeng.socialize.net.base.SocializeReseponse;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.net.utils.URequest;
import com.umeng.socialize.utils.SocializeUtils;

public class SharePostRequest extends SocializeRequest {
    private static final String a = "/share/add/";
    private static final int b = 9;
    private String c;
    private String d;
    private ShareContent e;

    public SharePostRequest(Context context, String str, String str2, ShareContent shareContent) {
        super(context, "", SocializeReseponse.class, 9, URequest.RequestMethod.POST);
        this.mContext = context;
        this.c = str;
        this.d = str2;
        this.e = shareContent;
    }

    /* access modifiers changed from: protected */
    public String getPath() {
        return a + SocializeUtils.getAppkey(this.mContext) + "/" + Config.EntityKey + "/";
    }

    public void onPrepareRequest() {
        addStringParams("to", this.c);
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_COMMENT_TEXT, this.e.mText);
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_SHARE_USID, this.d);
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_AK, SocializeUtils.getAppkey(this.mContext));
        addStringParams(SocializeProtocolConstants.PROTOCOL_KEY_ENTITY_KEY, Config.EntityKey);
        addMediaParams(this.e.mMedia);
    }
}
