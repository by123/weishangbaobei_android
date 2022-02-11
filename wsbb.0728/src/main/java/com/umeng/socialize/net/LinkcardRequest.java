package com.umeng.socialize.net;

import android.content.Context;
import android.text.TextUtils;
import com.luck.picture.lib.config.PictureConfig;
import com.umeng.socialize.Config;
import com.umeng.socialize.media.BaseMediaObject;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.net.base.SocializeRequest;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.net.utils.URequest;
import com.umeng.socialize.utils.SocializeUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LinkcardRequest extends SocializeRequest {
    private static final String a = "/share/linkcard/";
    private String b;
    private BaseMediaObject c;

    public LinkcardRequest(Context context) {
        super(context, "", LinkCardResponse.class, 0, URequest.RequestMethod.POST);
    }

    private JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("display_name", this.c.getTitle());
            jSONObject.put("image", f());
            jSONObject.put("summary", d());
            jSONObject.put(SocializeProtocolConstants.FULL_IMAGE, h());
            jSONObject.put("url", this.c.toUrl());
            jSONObject.put(SocializeProtocolConstants.LINKS, i());
            jSONObject.put(SocializeProtocolConstants.TAGS, e());
            jSONObject.put(SocializeProtocolConstants.CREATE_AT, c());
            jSONObject.put(SocializeProtocolConstants.OBJECT_TYPE, b());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private String b() {
        return this.c instanceof UMWeb ? "webpage" : this.c instanceof UMVideo ? PictureConfig.VIDEO : this.c instanceof UMusic ? "audio" : "webpage";
    }

    private String c() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
    }

    private String d() {
        return (TextUtils.isEmpty(this.c.getDescription()) || this.c.getDescription().length() <= 300) ? this.c.getDescription() : this.c.getDescription().substring(0, 300);
    }

    private JSONArray e() {
        JSONArray jSONArray = new JSONArray();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("display_name", Config.Descriptor);
            jSONArray.put(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONArray;
    }

    private JSONObject f() {
        JSONObject jSONObject = new JSONObject();
        try {
            UMImage thumbImage = this.c.getThumbImage();
            if (thumbImage == null || !thumbImage.isUrlMedia()) {
                jSONObject.put("url", "https://mobile.umeng.com/images/pic/home/social/img-1.png");
            } else {
                jSONObject.put("url", thumbImage.asUrlImage());
            }
            int[] g = g();
            jSONObject.put(SocializeProtocolConstants.WIDTH, g[0]);
            jSONObject.put(SocializeProtocolConstants.HEIGHT, g[1]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private int[] g() {
        int[] iArr = {120, 120};
        if (!(this.c == null || this.c.getmExtra() == null)) {
            Map<String, Object> map = this.c.getmExtra();
            if (map.containsKey(SocializeProtocolConstants.WIDTH)) {
                iArr[0] = ((Integer) map.get(SocializeProtocolConstants.WIDTH)).intValue();
            }
            if (map.containsKey(SocializeProtocolConstants.HEIGHT)) {
                iArr[1] = ((Integer) map.get(SocializeProtocolConstants.HEIGHT)).intValue();
            }
        }
        return iArr;
    }

    private JSONObject h() {
        JSONObject jSONObject = new JSONObject();
        try {
            UMImage thumbImage = this.c.getThumbImage();
            if (thumbImage == null || !thumbImage.isUrlMedia()) {
                jSONObject.put("url", "https://mobile.umeng.com/images/pic/home/social/img-1.png");
            } else {
                jSONObject.put("url", thumbImage.asUrlImage());
            }
            int[] g = g();
            jSONObject.put(SocializeProtocolConstants.WIDTH, g[0]);
            jSONObject.put(SocializeProtocolConstants.HEIGHT, g[1]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private JSONObject i() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("url", this.c.toUrl());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    /* access modifiers changed from: protected */
    public String getPath() {
        return a + SocializeUtils.getAppkey(this.mContext) + "/" + Config.EntityKey + "/";
    }

    public void onPrepareRequest() {
        super.onPrepareRequest();
        addStringParams("linkcard_info", a().toString());
    }

    public void setMedia(BaseMediaObject baseMediaObject) {
        this.c = baseMediaObject;
    }
}
