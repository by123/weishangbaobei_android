package com.umeng.socialize.common;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.stub.StubApp;
import com.umeng.socialize.utils.UmengText;
import com.umeng.socialize.utils.UrlUtil;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public final class ResContainer {
    private static ResContainer R = null;
    private static String mPackageName = "";
    private Context context = null;
    private Map<String, SocializeResource> mResources;
    private Map<String, Integer> map = new HashMap();

    public static class SocializeResource {
        public int mId;
        public boolean mIsCompleted = false;
        public String mName;
        public String mType;

        public SocializeResource(String str, String str2) {
            this.mType = str;
            this.mName = str2;
        }
    }

    private ResContainer(Context context2) {
        this.context = StubApp.getOrigApplicationContext(context2.getApplicationContext());
    }

    public ResContainer(Context context2, Map<String, SocializeResource> map2) {
        this.mResources = map2;
        this.context = context2;
    }

    public static ResContainer get(Context context2) {
        ResContainer resContainer;
        synchronized (ResContainer.class) {
            try {
                if (R == null) {
                    R = new ResContainer(context2);
                }
                resContainer = R;
            } catch (Throwable th) {
                Class<ResContainer> cls = ResContainer.class;
                throw th;
            }
        }
        return resContainer;
    }

    private static final int[] getResourceDeclareStyleableIntArray(Context context2, String str) {
        try {
            for (Field field : Class.forName(context2.getPackageName() + ".R$styleable").getFields()) {
                if (field.getName().equals(str)) {
                    return (int[]) field.get((Object) null);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return null;
    }

    public static int getResourceId(Context context2, String str, String str2) {
        Resources resources = context2.getResources();
        if (TextUtils.isEmpty(mPackageName)) {
            mPackageName = context2.getPackageName();
        }
        int identifier = resources.getIdentifier(str2, str, mPackageName);
        if (identifier > 0) {
            return identifier;
        }
        throw new RuntimeException(UmengText.errorWithUrl(UmengText.resError(mPackageName, str, str2), UrlUtil.ALL_NO_RES));
    }

    public static String getString(Context context2, String str) {
        return context2.getString(getResourceId(context2, "string", str));
    }

    public static int[] getStyleableArrts(Context context2, String str) {
        return getResourceDeclareStyleableIntArray(context2, str);
    }

    public int anim(String str) {
        return getResourceId(this.context, "anim", str);
    }

    public Map<String, SocializeResource> batch() {
        Map<String, SocializeResource> map2;
        synchronized (this) {
            if (this.mResources == null) {
                map2 = this.mResources;
            } else {
                for (String str : this.mResources.keySet()) {
                    SocializeResource socializeResource = this.mResources.get(str);
                    socializeResource.mId = getResourceId(this.context, socializeResource.mType, socializeResource.mName);
                    socializeResource.mIsCompleted = true;
                }
                map2 = this.mResources;
            }
        }
        return map2;
    }

    public int color(String str) {
        return getResourceId(this.context, "color", str);
    }

    public int dimen(String str) {
        return getResourceId(this.context, "dimen", str);
    }

    public int drawable(String str) {
        return getResourceId(this.context, "drawable", str);
    }

    public int id(String str) {
        return getResourceId(this.context, "id", str);
    }

    public int layout(String str) {
        return getResourceId(this.context, "layout", str);
    }

    public int raw(String str) {
        return getResourceId(this.context, "raw", str);
    }

    public int string(String str) {
        return getResourceId(this.context, "string", str);
    }

    public int style(String str) {
        return getResourceId(this.context, "style", str);
    }

    public int styleable(String str) {
        return getResourceId(this.context, "styleable", str);
    }
}
