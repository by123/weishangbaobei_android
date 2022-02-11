package com.umeng.socialize.bean;

import com.umeng.socialize.utils.UrlUtil;
import com.youth.banner.BannerConfig;

public enum UmengErrorCode {
    UnKnowCode(BannerConfig.TIME),
    AuthorizeFailed(2002),
    ShareFailed(2003),
    RequestForUserProfileFailed(2004),
    ShareDataNil(2004),
    ShareDataTypeIllegal(2004),
    NotInstall(2008);
    
    private final int a;

    private UmengErrorCode(int i) {
        this.a = i;
    }

    public String getMessage() {
        if (this == UnKnowCode) {
            return a() + "未知错误----";
        } else if (this == AuthorizeFailed) {
            return a() + "授权失败----";
        } else if (this == ShareFailed) {
            return a() + "分享失败----";
        } else if (this == RequestForUserProfileFailed) {
            return a() + "获取用户资料失败----";
        } else if (this == ShareDataNil) {
            return a() + "分享内容为空";
        } else if (this == ShareDataTypeIllegal) {
            return a() + "分享内容不合法----";
        } else if (this != NotInstall) {
            return "unkonw";
        } else {
            return a() + "没有安装应用" + " 点击查看错误：" + UrlUtil.ALL_NOT_INSTALL;
        }
    }

    private String a() {
        return "错误码：" + this.a + " 错误信息：";
    }
}
